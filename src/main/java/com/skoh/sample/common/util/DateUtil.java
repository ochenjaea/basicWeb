package com.skoh.sample.common.util;

import java.text.SimpleDateFormat;

	public class DateUtil {
		
	public static String getMillisecondTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm:ss.SSS]");
		java.util.Date now = new java.util.Date();

		return sdf.format(now) ;
	}

	public static String getMillisecondTime(long milli)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm:ss.SSS]");
		java.util.Date now = new java.util.Date();
		now.setTime(milli);

		return sdf.format(now) ;
	}
}
