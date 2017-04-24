package com.lamdaherding.edi.ram.ch02;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalDateFormat {

	// https://docs.oracle.com/javase/8/docs/api/java/lang/ThreadLocal.html
	// https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html
	// 
	// Long way to do it:
	//
	// private static Supplier<DateFormat> DFSUPPLIER = () -> new SimpleDateFormat("dd-MMM-yyyy");
	// private static ThreadLocal<DateFormat> DF = ThreadLocal.withInitial(DFSUPPLIER);
	//
	// Short way to do it:
	private static ThreadLocal<DateFormat> DF = ThreadLocal.withInitial( () -> new SimpleDateFormat( "dd-MMM-yyyy") );
	
	public static void main(String... args) {
		DateFormat df = DF.get();
		Date d = new Date();
		
		System.out.println( "The date is " + df.format(d) );
	}

}
