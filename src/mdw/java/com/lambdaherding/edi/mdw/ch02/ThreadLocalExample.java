package com.lambdaherding.edi.mdw.ch02;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * This class is not thread-safe! Turn the static formatter into a ThreadLocal version of it,
 * using lambdas for simplicity.
 */
public class ThreadLocalExample {
	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat( "dd-MM-yyyy" );

	private static void show( int day, Month month, int year ) {
		show( LocalDate.of( year, month, day ).atStartOfDay( ZoneOffset.UTC ).toEpochSecond() * 1000 );
	}

	private static void show( long epochMillis ) {
		System.out.println( FORMATTER.format( new Date( epochMillis ) ) );
	}

	public static void main( String[] args ) {
		ThreadLocalExample.show( 31, Month.MARCH, 2017 );
		ThreadLocalExample.show( 1491004800_000L );
	}
}
