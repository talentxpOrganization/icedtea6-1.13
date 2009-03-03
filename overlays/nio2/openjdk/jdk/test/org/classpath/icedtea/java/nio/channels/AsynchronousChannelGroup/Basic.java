/*
 * Copyright 2007-2008 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

/* @test
 * @bug 4607272
 * @summary Unit test for AsynchronousChannelGroup
 * @build Basic
 * @run main/othervm -XX:-UseVMInterruptibleIO Basic
 */

import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.io.IOException;

import org.classpath.icedtea.java.nio.channels.AsynchronousChannelGroup;

public class Basic {
    static final Random rand = new Random();

    public static void main(String[] args) throws Exception {
        shutdownTests();
        shutdownNowTests();
        afterShutdownTests();
        miscTests();
    }

    static void shutdownTests() throws Exception {
        System.out.println("-- test shutdown --");

        // test shutdown with no channels in groups
        for (int i=0; i<500; i++) {
            ExecutorService pool;
            AsynchronousChannelGroup group;
            if (rand.nextBoolean()) {
                pool = Executors.newCachedThreadPool();
                group = AsynchronousChannelGroup.withCachedThreadPool(pool, rand.nextInt(5));
            } else {
                int nThreads = 1 + rand.nextInt(8);
                pool = Executors.newFixedThreadPool(nThreads);
                group = AsynchronousChannelGroup.withFixedThreadPool(pool, nThreads);
            }
            group.shutdown();
            if (!group.isShutdown())
                throw new RuntimeException("Group should be shutdown");
            // group should terminate quickly
            boolean terminated = group.awaitTermination(3, TimeUnit.SECONDS);
            if (!terminated)
                throw new RuntimeException("Group should have terminated");
            if (!pool.isTerminated())
                throw new RuntimeException("Executor should have terminated");
        }

        // shutdown with channel in group
        for (int i=0; i<500; i++) {
            ExecutorService pool;
            AsynchronousChannelGroup group;
            if (rand.nextBoolean()) {
                pool = Executors.newCachedThreadPool();
                group = AsynchronousChannelGroup.withCachedThreadPool(pool, rand.nextInt(10));
            } else {
                int nThreads = 1 + rand.nextInt(8);
                pool = Executors.newFixedThreadPool(nThreads);
                group = AsynchronousChannelGroup.withFixedThreadPool(pool, nThreads);
            }
            // create channel that is bound to group
            AsynchronousChannel ch;
            switch (rand.nextInt(3)) {
                case 0 : ch = AsynchronousSocketChannel.open(group); break;
                case 1 : ch = AsynchronousServerSocketChannel.open(group); break;
                case 2 : ch = AsynchronousDatagramChannel.open(null, group); break;
                default : throw new AssertionError();
            }
            group.shutdown();
            if (!group.isShutdown())
                throw new RuntimeException("Group should be shutdown");

            // last channel so should terminate after this channel is closed
            ch.close();

            // group should terminate quickly
            boolean terminated = group.awaitTermination(3, TimeUnit.SECONDS);
            if (!terminated)
                throw new RuntimeException("Group should have terminated");
            if (!pool.isTerminated())
                throw new RuntimeException("Executor should have terminated");
        }
    }

    static void shutdownNowTests() throws Exception {
        System.out.println("-- test shutdownNow --");

        for (int i=0; i< 10; i++) {
            ExecutorService pool;
            AsynchronousChannelGroup group;
            if (rand.nextBoolean()) {
                pool = Executors.newCachedThreadPool();
                group = AsynchronousChannelGroup
                    .withCachedThreadPool(pool, rand.nextInt(5));
            } else {
                int nThreads = 1 + rand.nextInt(8);
                pool = Executors.newFixedThreadPool(nThreads);
                group = AsynchronousChannelGroup.withFixedThreadPool(pool, nThreads);
            }

            // I/O in progress
            AsynchronousChannel ch;
            if (rand.nextBoolean()) {
                AsynchronousServerSocketChannel listener = AsynchronousServerSocketChannel
                    .open(group).bind(new InetSocketAddress(0));
                listener.accept();
                ch = listener;
            } else {
                AsynchronousDatagramChannel adc =
                    AsynchronousDatagramChannel.open(null, group);
                adc.receive(ByteBuffer.allocate(100));
                ch = adc;
            }

            // forceful shutdown
            group.shutdownNow();

            // shutdownNow is required to close all channels
            if (ch.isOpen())
                throw new RuntimeException("Channel should be closed");

            boolean terminated = group.awaitTermination(3, TimeUnit.SECONDS);
            if (!terminated)
                throw new RuntimeException("Group should have terminated");
            if (!pool.isTerminated())
                throw new RuntimeException("Executor should have terminated");
        }
    }

    // test creating channels in group after group is shutdown
    static void afterShutdownTests() throws Exception {
        System.out.println("-- test operations after group is shutdown  --");
        ExecutorService pool = Executors.newFixedThreadPool(1);
        AsynchronousChannelGroup group =
            AsynchronousChannelGroup.withFixedThreadPool(pool, 1);

        AsynchronousSocketChannel ch = AsynchronousSocketChannel.open(group);
        AsynchronousServerSocketChannel listener = AsynchronousServerSocketChannel.open(group);

        // initiate accept
        listener.bind(new InetSocketAddress(0));
        Future<AsynchronousSocketChannel> result = listener.accept();

        // shutdown group
        group.shutdown();
        if (!group.isShutdown())
            throw new RuntimeException("Group should be shutdown");

        // attempt to create another channel
        try {
            AsynchronousSocketChannel.open(group);
            throw new RuntimeException("ShutdownChannelGroupException expected");
        } catch (ShutdownChannelGroupException x) {
        }
        try {
            AsynchronousServerSocketChannel.open(group);
            throw new RuntimeException("ShutdownChannelGroupException expected");
        } catch (ShutdownChannelGroupException x) {
        }

        // attempt to create another channel by connecting. This should cause
        // the accept operation to fail.
        InetAddress lh = InetAddress.getLocalHost();
        int port = ((InetSocketAddress)listener.getLocalAddress()).getPort();
        InetSocketAddress isa = new InetSocketAddress(lh, port);
        ch.connect(isa).get();
        try {
            result.get();
            throw new RuntimeException("Connection was accepted");
        } catch (ExecutionException x) {
            Throwable cause = x.getCause();
            if (!(cause instanceof IOException))
                throw new RuntimeException("Cause should be IOException");
            cause = cause.getCause();
            if (!(cause instanceof ShutdownChannelGroupException))
                throw new RuntimeException("IOException cause should be ShutdownChannelGroupException");
        }

        // initiate another accept even though channel group is shutdown.
        Future<AsynchronousSocketChannel> res = listener.accept();
        try {
            res.get(3, TimeUnit.SECONDS);
            throw new RuntimeException("TimeoutException expected");
        } catch (TimeoutException x) {
        }
        // connect to the listener which should cause the accept to complete
        AsynchronousSocketChannel.open().connect(isa);
        try {
            res.get();
            throw new RuntimeException("Connection was accepted");
        } catch (ExecutionException x) {
            Throwable cause = x.getCause();
            if (!(cause instanceof IOException))
                throw new RuntimeException("Cause should be IOException");
            cause = cause.getCause();
            if (!(cause instanceof ShutdownChannelGroupException))
                throw new RuntimeException("IOException cause should be ShutdownChannelGroupException");
        }

        // group should *not* terminate as channels are open
        boolean terminated = group.awaitTermination(3, TimeUnit.SECONDS);
        if (terminated)
            throw new RuntimeException("Group should not have terminated");

        // close channel; group should terminate quickly
        ch.close();
        listener.close();
        terminated = group.awaitTermination(3, TimeUnit.SECONDS);
        if (!terminated)
            throw new RuntimeException("Group should have terminated");
    }

    static void miscTests() throws Exception {
        System.out.println("-- miscellenous tests --");
        try {
            AsynchronousChannelGroup.withFixedThreadPool(null, 1);
            throw new RuntimeException("NPE expected");
        } catch (NullPointerException x) {
        }
        ExecutorService pool = Executors.newCachedThreadPool();
        try {
            AsynchronousChannelGroup.withFixedThreadPool(pool, 0);
            throw new RuntimeException("IAE expected");
        } catch (IllegalArgumentException e) {
        } finally {
            pool.shutdown();
        }
        try {
            AsynchronousChannelGroup.withCachedThreadPool(null, 0);
            throw new RuntimeException("NPE expected");
        } catch (NullPointerException x) {
        }
    }
}