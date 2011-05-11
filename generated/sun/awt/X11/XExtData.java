// This file is an automatically generated file, please do not edit this file, modify the WrapperGenerator.java file instead !

package sun.awt.X11;

import sun.misc.*;

import java.util.logging.*;
public class XExtData extends XWrapperBase { 
	private Unsafe unsafe = XlibWrapper.unsafe; 
	private final boolean should_free_memory;
	public static int getSize() { return ((XlibWrapper.dataModel == 32)?(16):(32)); }
	public int getDataSize() { return getSize(); }

	long pData;

	public long getPData() { return pData; }


	public XExtData(long addr) {
		log.finest("Creating");
		pData=addr;
		should_free_memory = false;
	}


	public XExtData() {
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
	public int get_number() { log.finest("");return (Native.getInt(pData+0)); }
	public void set_number(int v) { log.finest(""); Native.putInt(pData+0, v); }
	public XExtData get_next(int index) { log.finest(""); return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(4):(8))) != 0)?(new XExtData(Native.getLong(pData+((XlibWrapper.dataModel == 32)?(4):(8)))+index*((XlibWrapper.dataModel == 32)?(16):(32)))):(null); }
	public long get_next() { log.finest("");return Native.getLong(pData+((XlibWrapper.dataModel == 32)?(4):(8))); }
	public void set_next(long v) { log.finest(""); Native.putLong(pData + ((XlibWrapper.dataModel == 32)?(4):(8)), v); }
	public long get_free_private(int index) { log.finest(""); return Native.getLong(pData+((XlibWrapper.dataModel == 32)?(8):(16)))+index*Native.getLongSize(); }
	public long get_free_private() { log.finest("");return Native.getLong(pData+((XlibWrapper.dataModel == 32)?(8):(16))); }
	public void set_free_private(long v) { log.finest(""); Native.putLong(pData + ((XlibWrapper.dataModel == 32)?(8):(16)), v); }
	public long get_private_data(int index) { log.finest(""); return Native.getLong(pData+((XlibWrapper.dataModel == 32)?(12):(24)))+index*Native.getLongSize(); }
	public long get_private_data() { log.finest("");return Native.getLong(pData+((XlibWrapper.dataModel == 32)?(12):(24))); }
	public void set_private_data(long v) { log.finest(""); Native.putLong(pData + ((XlibWrapper.dataModel == 32)?(12):(24)), v); }


	String getName() {
		return "XExtData"; 
	}


	String getFieldsAsString() {
		String ret="";

		ret += ""+"number = " + get_number() +", ";
		ret += ""+"next = " + get_next() +", ";
		ret += ""+"free_private = " + get_free_private() +", ";
		ret += ""+"private_data = " + get_private_data() +", ";
		return ret;
	}


}



