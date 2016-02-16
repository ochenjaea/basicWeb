package com.skoh.sample.common.util;

public class NumberUtil {
	 /**
     *  double을 0이 앞에 붙은 String으로 변환하는 method
     *
     * @param value String으로 변환하고자하는 integer
     * @param length 변환된 String의 총 길이
     * @return 변환된 String
     */
    public static String toZeroString(double value, int length)
    {
        StringBuffer sb = new StringBuffer() ;

        int i = 0 ;
        if (value < 0) {
            sb.append("-") ;
            i++ ;
        }

        for ( ; i < length ; i++) {
            sb.append("0") ;

        }
        String s = Double.toString(Math.abs(value)) ;
        sb.replace(length - s.length(), length, s) ;
        return sb.toString() ;
    }

    /**
     *  integer를 0이 앞에 붙은 String으로 변환하는 method
     *
     * @param value String으로 변환하고자하는 integer
     * @param length 변환된 String의 총 길이
     * @return 변환된 String
     */
    public static String toZeroString(int value, int length)
    {
        StringBuffer sb = new StringBuffer() ;

        int i = 0 ;
        if (value < 0) {
            sb.append("-") ;
            i++ ;
        }

        for ( ; i < length ; i++) {
            sb.append("0") ;

        }
        String s = Integer.toString(Math.abs(value)) ;
        sb.replace(length - s.length(), length, s) ;
        return sb.toString() ;
    }

    /**
     *  String을 0이 앞에 붙은 String으로 변환하는 method
     *
     * @param s 변환하고자하는 String
     * @param length 변환된 String의 총 길이
     * @return 변환된 String
     */
    public static String toZeroString(String s, int length)
    {
        byte[] dest = new byte[length] ;
        int i ;

        for (i = 0 ; i < dest.length ; i++) {
            dest[i] = 0x30 ;

        }
        if (s == null) {
            s = "" ;

        }
        byte[] src = s.getBytes() ;
        if (src.length > length) {
            System.arraycopy(src, 0, dest, 0, length) ;
        }
        else {
            System.arraycopy(src, 0, dest, length - src.length, src.length) ;

        }
        return new String(dest) ;
    }

    /**
     *  integer를 space가 앞에 붙은 String으로 변환하는 method
     *
     * @param value String으로 변환하고자하는 integer
     * @param length 변환된 String의 총 길이
     * @return 변환된 String
     */
    public static String toAppendSpaceString(int value, int length)
    {
        StringBuffer sb = new StringBuffer() ;
        for (int i = 0 ; i < length ; i++) {
            sb.append(" ") ;

        }
        String s = Integer.toString(value) ;
        sb.replace(length - s.length(), length, s) ;
        return sb.toString() ;
    }

    /**
     *  String을 space가 앞에 붙은 String으로 변환하는 method
     *
     * @param s 변환하고자하는 String
     * @param length 변환된 String의 총 길이
     * @return 변환된 String
     */
    public static String toAppendSpaceString(String s, int length)
    {
        byte[] dest = new byte[length] ;
        int i ;

        for (i = 0 ; i < dest.length ; i++) {
            dest[i] = 0x20 ;

        }
        if (s == null) {
            s = "" ;

        }
        byte[] src = s.getBytes() ;

        if (src.length > length) {
            System.arraycopy(src, 0, dest, 0, length) ;
        }
        else {
            System.arraycopy(src, 0, dest, length - src.length, src.length) ;

        }
        return new String(dest) ;
    }

    /**
     *  integer를 space가 뒤에 붙은 String으로 변환하는 method
     *
     * @param value String으로 변환하고자하는 integer
     * @param length 변환된 String의 총 길이
     * @return 변환된 String
     */
    public static String toPrependSpaceString(int value, int length)
    {
        StringBuffer sb = new StringBuffer() ;
        for (int i = 0 ; i < length ; i++) {
            sb.append(" ") ;

        }
        String s = Integer.toString(value) ;
        sb.replace(0, s.length(), s) ;
        return sb.toString() ;
    }

    /**
     *  String을 space가 뒤에 붙은 String으로 변환하는 method
     *
     * @param s 변환하고자하는 String
     * @param length 변환된 String의 총 길이
     * @return 변환된 String
     */
    public static String toPrependSpaceString(String s, int length)
    {
        byte[] dest = new byte[length] ;
        int i ;

        for (i = 0 ; i < dest.length ; i++) {
            dest[i] = 0x20 ;

        }
        if (s == null) {
            s = "" ;

        }
        byte[] src = s.getBytes() ;
        if (src.length > length) {
            System.arraycopy(src, 0, dest, 0, length) ;
        }
        else {
            System.arraycopy(src, 0, dest, 0, src.length) ;

        }
        return new String(dest) ;
    }

    /**
     *	byte array의 일부를 copy하여 String으로 return하는 method.
     *	한글의 경우 byte로 계산하면 2byte이지만 String은 길이가 1이므로
     *	substring을 사용하면 둘이 서로 length가 달라지게 된다.
     *	따라서 byte[]로 받은 자료를 substring으로 잘라내지 말고
     *	copyArray를 사용하여 잘라내야 한다.
     */
    public static String copyArray(byte[] src, int index, int length)
    {
        byte[] b = new byte[length] ;
        System.arraycopy(src, index, b, 0, b.length) ;

        return new String(b) ;
    }

    public static String copyArray(byte[] src, int index)
    {
        int length = src.length - index ;

        return copyArray(src, index, length) ;
    }

    public static byte[] copyByteArray(byte[] src, int index, int length)
    {
        byte[] b = new byte[length] ;
        System.arraycopy(src, index, b, 0, b.length) ;
        return b ;
    }

    public static byte[] copyByteArray(byte[] src, int index)
    {
        int length = src.length - index ;
        return copyByteArray(src, index, length) ;
    }

    public static String copyCString(byte[] src, int index, int length)
    {
        byte[] b = new byte[length] ;
        int i ;

        for (i = 0 ; i < b.length ; i++) {
            b[i] = src[index + i] ;
            if (b[i] == (byte) 0x00) {
                break ;
            }
        }

        byte[] t = new byte[i + 1] ;
        System.arraycopy(b, 0, t, 0, t.length) ;

        return new String(t) ;
    }
}
