package com.lambdaherding.edi.axp.ch05;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class FibonacciTest {
	private Fibonacci fib;

	@Before
	public void setup() {
		fib = new Fibonacci();
	}

	@Test
	public void testIncrementsOfOne() {
		assertThat( fib.get( 0 ), is( 1 ) );
		assertThat( fib.get( 1 ), is( 1 ) );
		assertThat( fib.get( 2 ), is( 2 ) );
		assertThat( fib.get( 3 ), is( 3 ) );
		assertThat( fib.get( 4 ), is( 5 ) );
		assertThat( fib.get( 5 ), is( 8 ) );
		assertThat( fib.get( 6 ), is( 13 ) );
		assertThat( fib.get( 7 ), is( 21 ) );
		assertThat( fib.get( 8 ), is( 34 ) );
		assertThat( fib.auditNumberOfComputations(), is( 7 ) );
	}

	@Test
	public void testIncrementsOfTwo() {
		assertThat( fib.get( 3 ), is( 3 ) );
		assertThat( fib.get( 5 ), is( 8 ) );
		assertThat( fib.get( 7 ), is( 21 ) );
		assertThat( fib.get( 9 ), is( 55 ) );
		assertThat( fib.auditNumberOfComputations(), is( 8 ) );
	}

	@Test
	public void testIncrementsOfThree() {
		assertThat( fib.get( 4 ), is( 5 ) );
		assertThat( fib.get( 7 ), is( 21 ) );
		assertThat( fib.get( 10 ), is( 89 ) );
		assertThat( fib.auditNumberOfComputations(), is( 9 ) );
	}

	@Test
	public void testOnlyComputesOnce() {
		assertThat( fib.get( 4 ), is( 5 ) );
		assertThat( fib.get( 4 ), is( 5 ) );
		assertThat( fib.get( 4 ), is( 5 ) );
		assertThat( fib.get( 4 ), is( 5 ) );
		assertThat( fib.auditNumberOfComputations(), is( 3 ) );
	}
}
