package com.sunbeam.utils;

public class DateTimeUtil {
	
	public static java.util.Date sqlDateToUtilDate(java.sql.Date sqlDate)
	{
		return new java.util.Date(sqlDate.getTime());
	}
	
	public static java.sql.Date utilDateToSqlDate(java.util.Date uDate)
	{
		return new java.sql.Date(uDate.getTime());
	}
}
