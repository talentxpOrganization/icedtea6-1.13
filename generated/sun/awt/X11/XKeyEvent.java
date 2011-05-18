// This file is an automatically generated file, please do not edit this file, modify the WrapperGenerator.java file instead !

package sun.awt.X11;

import sun.misc.*;

import java.util.logging.*;
public class XKeyEvent extends XWrapperBase { 
	private Unsafe unsafe = XlibWrapper.unsafe; 
	private final boolean should_free_memory;
	public static int getSize() { return ((XlibWrapper.dataModel == 32)?(60):(96)); }
	public int getDataSize() { return getSize(); }

	long pData;

	public long getPData() { return pData; }


	public XKeyEvent(long addr) {
		log.finest("Creating");
		pData=addr;
		should_free_memory = false;
	}


	public XKeyEvent() {
		log.finest("Creating");
		pData = unsafe.allocateMemory(getSize());
		should_free_memory = true;
	}


	public void dispose() {
		log.finest("Disposing");
		if (should_free_memory) {
			log.finest("freeing memory");
			unsafe.freeMemory(pData); 
	}
		}
	public int get_type() { log.finest("");return (Native.getInt(pData+0)); }
	public void set_type(int v) { log.finest(""); Native.putInt(pData+0, v); }
	public long get_serial() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(4):(8)))); }
	public void set_serial(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(4):(8)), v); }
	public boolean get_send_event() { log.finest("");return (Native.getBool(pData+((XlibWrapper.dataModel == 32)?(8):(16)))); }
	public void set_send_event(boolean v) { log.finest(""); Native.putBool(pData+((XlibWrapper.dataModel == 32)?(8):(16)), v); }
	public long get_display() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(12):(24)))); }
	public void set_display(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(12):(24)), v); }
	public long get_window() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(16):(32)))); }
	public void set_window(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(16):(32)), v); }
	public long get_root() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(20):(40)))); }
	public void set_root(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(20):(40)), v); }
	public long get_subwindow() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(24):(48)))); }
	public void set_subwindow(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(24):(48)), v); }
	public long get_time() { log.finest("");return (Native.getULong(pData+((XlibWrapper.dataModel == 32)?(28):(56)))); }
	public void set_time(long v) { log.finest(""); Native.putULong(pData+((XlibWrapper.dataModel == 32)?(28):(56)), v); }
	public int get_x() { log.finest("");return (Native.getInt(pData+((XlibWrapper.dataModel == 32)?(32):(64)))); }
	public void set_x(int v) { log.finest(""); Native.putInt(pData+((XlibWrapper.dataModel == 32)?(32):(64)), v); }
	public int get_y() { log.finest("");return (Native.getInt(pData+((XlibWrapper.dataModel == 32)?(36):(68)))); }
	public void set_y(int v) { log.finest(""); Native.putInt(pData+((XlibWrapper.dataModel == 32)?(36):(68)), v); }
	public int get_x_root() { log.finest("");return (Native.getInt(pData+((XlibWrapper.dataModel == 32)?(40):(72)))); }
	public void set_x_root(int v) { log.finest(""); Native.putInt(pData+((XlibWrapper.dataModel == 32)?(40):(72)), v); }
	public int get_y_root() { log.finest("");return (Native.getInt(pData+((XlibWrapper.dataModel == 32)?(44):(76)))); }
	public void set_y_root(int v) { log.finest(""); Native.putInt(pData+((XlibWrapper.dataModel == 32)?(44):(76)), v); }
	public int get_state() { log.finest("");return (Native.getInt(pData+((XlibWrapper.dataModel == 32)?(48):(80)))); }
	public void set_state(int v) { log.finest(""); Native.putInt(pData+((XlibWrapper.dataModel == 32)?(48):(80)), v); }
	public int get_keycode() { log.finest("");return (Native.getInt(pData+((XlibWrapper.dataModel == 32)?(52):(84)))); }
	public void set_keycode(int v) { log.finest(""); Native.putInt(pData+((XlibWrapper.dataModel == 32)?(52):(84)), v); }
	public boolean get_same_screen() { log.finest("");return (Native.getBool(pData+((XlibWrapper.dataModel == 32)?(56):(88)))); }
	public void set_same_screen(boolean v) { log.finest(""); Native.putBool(pData+((XlibWrapper.dataModel == 32)?(56):(88)), v); }


	String getName() {
		return "XKeyEvent"; 
	}


	String getFieldsAsString() {
		String ret="";

		ret += ""+"type = " + XlibWrapper.eventToString[get_type()] +", ";
		ret += ""+"serial = " + get_serial() +", ";
		ret += ""+"send_event = " + get_send_event() +", ";
		ret += ""+"display = " + get_display() +", ";
		ret += ""+"window = " + getWindow(get_window()) + ", ";
		ret += ""+"root = " + get_root() +", ";
		ret += ""+"subwindow = " + get_subwindow() +", ";
		ret += ""+"time = " + get_time() +", ";
		ret += ""+"x = " + get_x() +", ";
		ret += ""+"y = " + get_y() +", ";
		ret += ""+"x_root = " + get_x_root() +", ";
		ret += ""+"y_root = " + get_y_root() +", ";
		ret += ""+"state = " + get_state() +", ";
		ret += ""+"keycode = " + get_keycode() +", ";
		ret += ""+"same_screen = " + get_same_screen() +", ";
		return ret;
	}


}



