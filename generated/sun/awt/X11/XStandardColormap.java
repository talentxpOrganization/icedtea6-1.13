// This file is an automatically generated file, please do not edit this file, modify the WrapperGenerator.java file instead !

package sun.awt.X11;

import sun.misc.*;

import java.util.logging.*;
public class XStandardColormap extends XWrapperBase { 
	private Unsafe unsafe = XlibWrapper.unsafe; 
	private final boolean should_free_memory;
	public static int getSize() { return ((XlibWrapper.dataModel == 32)?(40):(80)); }
	public int getDataSize() { return getSize(); }

	long pData;

	public long getPData() { return pData; }


	public XStandardColormap(long addr) {
		log.finest("Creating");
		pData=addr;
		should_free_memory = false;
	}


	public XStandardColormap() {
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
	public long get_colormap() { log.finest("");return (Native.getLong(pData+0)); }
	public void set_colormap(long v) { log.finest(""); Native.putLong(pData+0, v); }
	public long get_red_max() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(4):(8)))); }
	public void set_red_max(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(4):(8)), v); }
	public long get_red_mult() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(8):(16)))); }
	public void set_red_mult(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(8):(16)), v); }
	public long get_green_max() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(12):(24)))); }
	public void set_green_max(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(12):(24)), v); }
	public long get_green_mult() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(16):(32)))); }
	public void set_green_mult(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(16):(32)), v); }
	public long get_blue_max() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(20):(40)))); }
	public void set_blue_max(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(20):(40)), v); }
	public long get_blue_mult() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(24):(48)))); }
	public void set_blue_mult(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(24):(48)), v); }
	public long get_base_pixel() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(28):(56)))); }
	public void set_base_pixel(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(28):(56)), v); }
	public long get_visualid() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(32):(64)))); }
	public void set_visualid(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(32):(64)), v); }
	public long get_killid() { log.finest("");return (Native.getLong(pData+((XlibWrapper.dataModel == 32)?(36):(72)))); }
	public void set_killid(long v) { log.finest(""); Native.putLong(pData+((XlibWrapper.dataModel == 32)?(36):(72)), v); }


	String getName() {
		return "XStandardColormap"; 
	}


	String getFieldsAsString() {
		String ret="";

		ret += ""+"colormap = " + get_colormap() +", ";
		ret += ""+"red_max = " + get_red_max() +", ";
		ret += ""+"red_mult = " + get_red_mult() +", ";
		ret += ""+"green_max = " + get_green_max() +", ";
		ret += ""+"green_mult = " + get_green_mult() +", ";
		ret += ""+"blue_max = " + get_blue_max() +", ";
		ret += ""+"blue_mult = " + get_blue_mult() +", ";
		ret += ""+"base_pixel = " + get_base_pixel() +", ";
		ret += ""+"visualid = " + get_visualid() +", ";
		ret += ""+"killid = " + get_killid() +", ";
		return ret;
	}


}



