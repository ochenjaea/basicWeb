
package com.skoh.sample.common.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Compress {

	/**
	 * 압축 알고리즘
	 * 압축할 문자열을 byte로 받은뒤 byte로 리턴
	 * String으로 변환하면 압축 해제에서 문제생김
	 * @param bytes
	 * @return
	 */
	public static byte[] compressByteArray(byte[] bytes){
        
        ByteArrayOutputStream baos = null;
        Deflater dfl = new Deflater();
      
        dfl.setInput(bytes);
        dfl.finish();
        baos = new ByteArrayOutputStream();
        byte[] tmp = new byte[4*1024];
        try{
            while(!dfl.finished()){
                int size = dfl.deflate(tmp);
                baos.write(tmp, 0, size);
            }
        } catch (Exception ex){
             
        } finally {
            try{
                if(baos != null) baos.close();
            } catch(Exception ex){}
        }
         
        return baos.toByteArray();
    }
	
	public static byte[] compressStringArray(String strings){
		return compressByteArray(strings.getBytes());
	}
	
	/**
	 * 압축 해제 알고리즘
	 * byte로 압축된 데이터를 받은뒤 해제
     * 
	 * @param bytes
	 * @return
	 */
	
	public static byte[] decompressByteArray(byte[] bytes){
        
        ByteArrayOutputStream baos = null;
        Inflater iflr = new Inflater();
        iflr.setInput(bytes);
        baos = new ByteArrayOutputStream();
        byte[] tmp = new byte[4*1024];
        try{
            while(!iflr.finished()){
                int size = iflr.inflate(tmp);
                baos.write(tmp, 0, size);
            }
        } catch (Exception ex){
             
        } finally {
            try{
                if(baos != null) baos.close();
            } catch(Exception ex){}
        }
         
        return baos.toByteArray();
    }
	
	public static String decompressStringArray(byte[] bytes){
		return new String(decompressByteArray(bytes));
		
	}
}



