package com.lambdaherding.edi.red.ch02;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Date;

public class ThreadLocalSample {
	private static final ThreadLocal<DateFormat> FORMATTER =
		ThreadLocal.withInitial( () -> new SimpleDateFormat( "dd-MM-yyyy" ) );

	private static void show( int day, Month month, int year ) {
		show( LocalDate.of( year, month, day ).atStartOfDay( ZoneOffset.UTC ).toEpochSecond() * 1000 );
	}

	private static void show( long epochMillis ) {
		System.out.println( FORMATTER.get().format( new Date( epochMillis ) ) );
	}

	public static void main( String[] args ) {
		for ( int i = 1; i <= 3; i++ ) {
			final int day = i; // Must be final or effectively final!
			new Thread( () -> {
				ThreadLocalSample.show( day, Month.MARCH, 2017 );
				ThreadLocalSample.show( 1491004800_000L * new Long(day) ); // little bit silly, but shows up nicely in the comparative output
			}).start();
		}
	}
}
