package com.lambdaherding.edi.pxd.ch02;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;


public class ThreadLocalExampleTest {

	@Test
	public void test() {
		String formatted = ThreadLocalExample.FORMATTER.get().getFormat().format( new Date( 0 ) );
		assertEquals( "01-Jan-1970", formatted );
	}

}
