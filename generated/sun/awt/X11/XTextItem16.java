// This file is an automatically generated file, please do not edit this file, modify the WrapperGenerator.java file instead !

package sun.awt.X11;

import sun.misc.*;

import java.util.logging.*;
public class XTextItem16 extends XWrapperBase { 
	private Unsafe unsafe = XlibWrapper.unsafe; 
	private final boolean should_free_memory;
	public static int getSize() { return ((XlibWrapper.dataModel == 32)?(16):(24)); }
	public int getDataSize() { return getSize(); }

	long pData;

	public long getPData() { return pData; }


	public XTextItem16(long addr) {
		log.finest("Creating");
		pData=addr;
		should_free_memory = false;
	}


	public XTextItem16() {
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
	public XChar2b get_chars(int index) { log.finest(""); return (Native.getLong(pData+0) != 0)?(new XChar2b(Native.getLong(pData+0)+index*2)):(null); }
	public long get_chars() { log.finest("");return Native.getLong(pData+0); }
	public void set_chars(long v) { log.finest(""); Native.putLong(pData + 0, v); }
	public int get_nchars() { log.finest("");return (Native.getInt(pData+((XlibWrapper.dataModel == 32)?(4):(8)))); }
	public void set_nchars(int v) { log.finest(""); Native.putInt(pData+((XlibWrapper.dataModel == 32)?(4):(8)), v); }
	public int get_delta() { log.finest("");return (Native.getInt(pData+((XlibWrapper.dataModel == 32)?(8):(12)))); }
	public void set_delta(int v) { log.finest(""); Native.putInt(pData+((XlibWrapper.dataModel == 32)?(8):(12)), v); }
	public long get_font() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(12):(16)))); }
	public void set_font(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(12):(16)), v); }


	String getName() {
		return "XTextItem16"; 
	}


	String getFieldsAsString() {
		String ret="";

		ret += ""+"chars = " + get_chars() +", ";
		ret += ""+"nchars = " + get_nchars() +", ";
		ret += ""+"delta = " + get_delta() +", ";
		ret += ""+"font = " + get_font() +", ";
		return ret;
	}


}



