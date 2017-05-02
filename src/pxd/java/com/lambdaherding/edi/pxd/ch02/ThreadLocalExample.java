package com.lambdaherding.edi.pxd.ch02;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.DateFormatter;

public class ThreadLocalExample {

	public final static ThreadLocal<DateFormatter> FORMATTER = ThreadLocal.withInitial( () -> new DateFormatter( new SimpleDateFormat( "dd-MMM-yyyy" ) ) );

	public static void main( String[] args ) {
		ThreadLocalExample.FORMATTER.get().getFormat().format( new Date() );
	}


}
