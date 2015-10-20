package com.netdespatch.detector.util;

import java.util.Date;
/**
 * 
 * Utility class to convert Unix Date format (in Epoch) to Java Date Object
 * 
 * @author jay
 *
 */
public class DateUtil {
	
	private static final int SECOND = 1000;
	private static final int MINUTE = 60 * SECOND;
	
	public static Date epochToDate(final String date){
		return new Date(Long.parseLong(date) * SECOND);
	}
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean isTimeDifferenceEqualsOrMoreThan5Minutes(Date start, Date end){
		int diff = minutesDiff(start,end);
		if(diff >= 5){
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private static int minutesDiff(Date start, Date end)
	{
	    if(start == null || end == null) return 0;
	    long diff = end.getTime() - start.getTime();
	    return (int)(diff/MINUTE);
	}
}
