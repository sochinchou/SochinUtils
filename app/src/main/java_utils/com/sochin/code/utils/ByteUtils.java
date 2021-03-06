package com.sochin.code.utils;

import java.util.Arrays;

import android.util.Log;

/**
 *
 * @author zhoushaoqing
 *
 */
public class ByteUtils {

	private static final String TAG = "ByteUtils";

	public static final short BYTE_SHIFT = 8;
	public static final short BYTE_MASK = 0xff;

	public static byte[] shortToBytes(short number) {
		int temp = number;
		byte[] b = new byte[2];
		for (int i = 0; i < b.length; i++) {
//			b[b.length - 1 - i] = Integer.valueOf(temp & 0xff).byteValue();
			b[b.length - 1 - i] = (byte)(temp & 0xff);
			temp = temp >> BYTE_SHIFT;
		}
		return b;
	}

	public static byte[] intToBytes(int number) {
		int temp = number;
		byte[] b = new byte[4];
		for (int i = 0; i < b.length; i++) {
//			b[b.length - 1 - i] = Integer.valueOf(temp & 0xff).byteValue();
			b[b.length - 1 - i] = (byte)(temp & 0xff);
			temp = temp >> BYTE_SHIFT;
		}
		return b;
	}

	public static byte[] longToBytes(long number) {
		long temp = number;
		byte[] b = new byte[8];
		for (int i = 0; i < b.length; i++) {
//			b[b.length - 1 - i] = Long.valueOf(temp & 0xff).byteValue();
			b[b.length - 1 - i] = (byte)(temp & 0xff);
			temp = temp >> BYTE_SHIFT;
		}
		return b;
	}

	public static short bytesToShort(byte[] b) {
		int temp = 0;
		int length = b.length;
		if (length > 2)
			length = 2;

		for (int i = 0; i < length; i++) {
			temp = (temp << BYTE_SHIFT) | (b[i] & 0xff);
		}

		return (short)temp;
	}

	public static int bytesToInt(byte[] b) {
		int temp = 0;
		int length = b.length;
		if (length > 4)
			length = 4;

		for (int i = 0; i < length; i++) {
			temp = (temp << BYTE_SHIFT) | (b[i] & 0xff);
		}

		return temp;
	}

		
	public static long bytesToLong(byte[] b) {
		long temp = 0;
		int length = b.length;
		if (length > 8)
			length = 8;

		for (int i = 0; i < length; i++) {
			temp = (temp << BYTE_SHIFT) | (b[i] & 0xff);
		}

		return temp;
	}

	// *************************************************
	// other styles
	// *************************************************

	public static byte[] short2Bytes(short number) {
		byte[] b = new byte[2];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (number >> (b.length - 1 - i) * BYTE_SHIFT);
		}
		return b;
	}

	public static byte[] int2Bytes(int number) {
		byte[] b = new byte[4];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (number >> (b.length - 1 - i) * BYTE_SHIFT);
		}
		return b;
	}

	public static byte[] long2Bytes(long number) {
		byte[] b = new byte[8];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (number >> (b.length - 1 - i) * BYTE_SHIFT);
		}
		return b;
	}

	public static short bytes2Short(byte[] b) {
		int temp = 0;
		int length = b.length;
		if (length > 2)
			length = 2;

		for (int i = 0; i < length; i++) {
			int i2 = b[length - 1 - i] & 0xff;
			temp |= i2 << BYTE_SHIFT * i;
		}

		return (short) temp;
	}

	public static int bytes2Int(byte[] b) {
		int temp = 0;
		int length = b.length;
		if (length > 4)
			length = 4;

		for (int i = 0; i < length; i++) {
			int i2 = b[length - 1 - i] & 0xff;
			temp |= i2 << BYTE_SHIFT * i; 
		}

		return temp;
	}

	public static long bytes2Long(byte[] b) {
		long temp = 0;
		int length = b.length;
		if (length > 8)
			length = 8;

		for (int i = 0; i < length; i++) {
			long i2 = b[length - 1 - i] & 0xff;
			temp |= i2 << BYTE_SHIFT * i; 
		}
		
		return temp;
	}

	
	public static int unsignedBytes2Short(byte[] b) {

        int temp = 0;
        int length = b.length;
        if(length > 2)
        	length = 2;
        
		for (int i = 0; i < length; i++) {
			int i2 = b[length - 1 - i] & 0xff;
			temp |= i2 << BYTE_SHIFT * i; 
		}
        
        return temp;
    }
	
	public static long unsignedBytes2Int(byte[] b) {

        long temp = 0;
        int length = b.length;
        if(length > 4)
        	length = 4;
        
		for (int i = 0; i < length; i++) {
			long i2 = b[length - 1 - i] & 0xff;
			temp |= i2 << BYTE_SHIFT * i; 
		}
        
        return temp;
    }
	
	
	
	public static int encodeIntBigEndian(byte[] dst, long val, int offset, int size) {
		for (int i = 0; i < size; i++) {
			dst[offset++] = (byte) (val >> ((size - i - 1) * Byte.SIZE));
		}
		return offset;
	}


	public static long decodeIntBigEndian(byte[] val, int offset, int size) {
		long rtn = 0;
		for (int i = 0; i < size; i++) {
			rtn = (rtn << Byte.SIZE) | (val[offset + i] & BYTE_MASK);
		}
		return rtn;
	}
		  

	public static String bytes2StrHex(byte[] bytes) {
		if(bytes != null){
			StringBuilder sb = new StringBuilder();
			for (byte b : bytes)
				sb.append(String.format("%02x ", b));
			return sb.toString();
		}else{
			return "null";
		}
	}

	public static String bytes2StrDecimal(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes)
			sb.append(b & BYTE_MASK).append(" ");
		return sb.toString();
	}

	public static byte[] join(byte[] a1, byte[] a2) {
		byte[] result = new byte[a1.length + a2.length];
		System.arraycopy(a1, 0, result, 0, a1.length);
		System.arraycopy(a2, 0, result, a1.length, a2.length);
		return result;
	}

	public static byte[] SubByteArray(byte[] bytes, int start, int end) {
		return Arrays.copyOfRange(bytes, start, end);
	}

	public static byte hiByte(short in) {
		return (byte)((in & 0xFF00) >> 8);
	}

	public static byte lowByte(short in) {
		return (byte)((in & 0xFF));
	}

	public static short parseShort(String str) {
		if (str == null) {
			return 0;
		}
		short value = 0;
		try {
			value = Short.parseShort(str);
		} catch (NumberFormatException e) {
		}
		return value;
	}

	public static int parseInt(String str) {
		if (str == null) {
			return 0;
		}
		int value = 0;
		try {
			value = Integer.parseInt(str);
		} catch (NumberFormatException e) {

		}
		return value;
	}

	public static long parseLong(String str) {
		if (str == null) {
			return 0;
		}
		long value = 0;
		try {
			value = Long.parseLong(str);
		} catch (NumberFormatException e) {

		}
		return value;
	}

	public static double parseFloat(String str) {
		if (str == null) {
			return 0;
		}
		double value = 0;
		try {
			value = Float.parseFloat(str);
		} catch (NumberFormatException e) {

		}
		return value;
	}


	public static double parseDouble(String str) {
		if (str == null) {
			return 0;
		}
		double value = 0;
		try {
			value = Double.parseDouble(str);
		} catch (NumberFormatException e) {

		}
		return value;
	}




	public static byte asciiToBcd(byte asci) {
		byte bcd = 0;
		if (asci >= '0' && asci <= '9') {
			bcd = (byte) (asci - '0');
		} else if (asci >= 'A' && asci <= 'F') {
			bcd = (byte) (asci - 'A' + 0x0A);
		} else if (asci >= 'a' && asci <= 'f') {
			bcd = (byte) (asci - 'a' + 0x0A);
		}
		return bcd;
	}

	public static byte bcdToAscii(byte bcd) {
		byte asc = 0;
		if (bcd >= 0x00 && bcd <= 0x09) {
			asc = (byte) ('0' + bcd);
		} else if (bcd >= 0x0A && bcd <= 0x0F) {
			asc = (byte) ('A' + bcd - 0x0A);
		}
		return asc;
	}

	public static boolean isCanConvertToBcd(byte cha){
		return ( (cha >= '0' && cha <= '9')
				|| (cha >= 'A' && cha <= 'F')
				|| (cha >= 'a' && cha <= 'f'));
	}

	public static byte[] strToBcd(String str){

		if(str == null || str.length() <= 0){
//			System.out.println("str is empty, return");
			return null;
		}

		if(str.length() % 2 == 1){
			str = '0' + str;
		}

		byte[] asciBytes = str.getBytes();

		for(byte strByte : asciBytes){
			if(!isCanConvertToBcd(strByte)){
//				System.out.println("str contains invalid char, return");
				return null;
			}
		}

		int lenAsci = asciBytes.length;
		for(int i = 0; i < lenAsci; i++){
			asciBytes[i] = asciiToBcd(asciBytes[i]);
		}

		int lenBcd = lenAsci/2;
		byte[] bcdBytes = new byte[lenBcd];

		for(int i = 0; i < lenBcd; i++){
			bcdBytes[i] = (byte)(asciBytes[2 * i] << 4 | asciBytes[2 * i + 1]);
		}
		return bcdBytes;
	}

	public static String bcdToStr(byte[] bcdBytes){
		if(bcdBytes == null || bcdBytes.length <= 0){
//			System.out.println("bytes is null, return");
			return null;
		}

		int lenAsci = 2 * bcdBytes.length;
		byte[] asciBytes = new byte[lenAsci];

		for(int i = 0; i < lenAsci - 1; i += 2){
			asciBytes[i] = bcdToAscii((byte)(bcdBytes[i / 2] >> 4 & 0x0F));
			asciBytes[i + 1] = bcdToAscii((byte)(bcdBytes[i / 2] & 0x0F));
		}
		return new String(asciBytes);
	}


	public static byte[] dataToAscii(byte[] dataBytes){
		if(dataBytes == null || dataBytes.length <= 0){
//			System.out.println("bytes is null, return");
			return null;
		}

		int lenAsci = 2 * dataBytes.length;
		byte[] asciBytes = new byte[lenAsci];

		for(int i = 0; i < lenAsci - 1; i += 2){
			asciBytes[i] = bcdToAscii((byte)(dataBytes[i / 2] >> 4 & 0x0F));
			asciBytes[i + 1] = bcdToAscii((byte)(dataBytes[i / 2] & 0x0F));
		}
		return asciBytes;
	}
		  
	/* test code
	short ss = -32767;
	Log.e(TAG, "s1 = " + ByteUtils.bytes2StrHex(ByteUtils.short2Bytes(ss)) + " && " + ByteUtils.bytes2StrHex(ByteUtils.shortToBytes(ss)) + " && " +String.format("%04x", ss));
	int ii = -32767;
	Log.e(TAG, "i1 = " + ByteUtils.bytes2StrHex(ByteUtils.int2Bytes(ii)) + " && " + ByteUtils.bytes2StrHex(ByteUtils.intToBytes(ii)) + " && " +String.format("%08x", ii));
	long ll = -32767L;
	Log.e(TAG, "l1 = " + ByteUtils.bytes2StrHex(ByteUtils.long2Bytes(ll)) + " && " + ByteUtils.bytes2StrHex(ByteUtils.longToBytes(ll)) + " && " +String.format("%016x", ll));


	
	byte[] s1 = {0x7f, (byte)0xff};
	byte[] s2 = {(byte)0x80, 0x01};
	Log.e(TAG, "s1 = " + ByteUtils.bytes2Short(s1) + " && " + ByteUtils.bytesToShort(s1));
	Log.e(TAG, "s2 = " + ByteUtils.bytes2Short(s2) + " && " + ByteUtils.bytesToShort(s2));
	Log.e(TAG, "s1 = " + ByteUtils.bytes2StrHex(s1));
	Log.e(TAG, "s2 = " + ByteUtils.bytes2StrHex(s2));

	byte[] i1 = {0x00, 0x00, 0x7f, (byte)0xff};
	byte[] i2 = {(byte)0xff, (byte)0xff, (byte)0x80, 0x01};
	Log.e(TAG, "i1 = " + ByteUtils.bytes2Int(i1) + " && " + ByteUtils.bytesToInt(i1));
	Log.e(TAG, "i2 = " + ByteUtils.bytes2Int(i2) + " && " + ByteUtils.bytesToInt(i2));
	Log.e(TAG, "i1 = " + ByteUtils.bytes2StrHex(i1));
	Log.e(TAG, "i2 = " + ByteUtils.bytes2StrHex(i2));
	
	
	byte[] l1 = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x7f, (byte)0xff};
	byte[] l2 = {(byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x80, 0x01};
	Log.e(TAG, "l1 = " + ByteUtils.bytes2Long(l1) + " && " + ByteUtils.bytesToLong(l1));
	Log.e(TAG, "l2 = " + ByteUtils.bytes2Long(l2) + " && " + ByteUtils.bytesToLong(l2));
	Log.e(TAG, "l1 = " + ByteUtils.bytes2StrHex(l1));
	Log.e(TAG, "l2 = " + ByteUtils.bytes2StrHex(l2));
	*/
}