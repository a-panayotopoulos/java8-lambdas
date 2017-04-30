package com.lambdaherding.edi.ch02;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * This class is not thread-safe! Turn the static formatter into a ThreadLocal version of it,
 * using lambdas for simplicity.
 * 
 * Comparrison Output:
 * 
 * Thread safe:
 * <code>
 * 02-03-2017
 * 01-03-2017
 * 01-04-2017
 * 03-03-2017
 * 30-06-2064
 * 30-09-2111
 * </code>
 * 
 * Not thread safe:
 * 01-03-2017
 * 02-03-2017
 * 01-03-2017
 * 01-04-2017
 * 01-04-2017
 * 01-04-2017
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
		for ( int i = 1; i <= 3; i++ ) {
			final int day = i; // Must be final or effectively final!
			new Thread( () -> {
				ThreadLocalExample.show( day, Month.MARCH, 2017 );
				ThreadLocalExample.show( 1491004800_000L * new Long(day) ); // little bit silly, but shows up nicely in the comparative output
			}).start();
		}
	}
}
