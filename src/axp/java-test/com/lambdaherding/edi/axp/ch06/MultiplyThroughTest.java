package com.lambdaherding.edi.axp.ch06;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class MultiplyThroughTest {
	private List<Integer> listOfNumbers = Arrays.asList( 2, 1, 3, 1, 2 );

	@Test
	public void testMultiplyThroughSequential() {
		int result = MultiplyThrough.multiplyThroughSequential( listOfNumbers );
		assertThat( "Multiplied correctly", result, is( 60 ) );
	}

	@Ignore
	@Test
	public void testMultiplyThroughBuggy() {
		int result = MultiplyThrough.multiplyThroughBuggy( listOfNumbers );
		/*
		 * Doesn't work!
		 *
		 * > Expected: is <60>
		 * >      but: was <37500>
		 */
		assertThat( "Multiplied correctly", result, is( 60 ) );
	}

	@Test
	public void testMultiplyThroughParallel() {
		int result = MultiplyThrough.multiplyThroughParallel( listOfNumbers );
		assertThat( "Multiplied correctly", result, is( 60 ) );
	}
}
