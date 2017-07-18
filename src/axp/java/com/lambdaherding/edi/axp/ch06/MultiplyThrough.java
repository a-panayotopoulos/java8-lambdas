package com.lambdaherding.edi.axp.ch06;

import java.util.List;

/**
 * Exercise 2: multiply through
 */
public class MultiplyThrough {
	/**
	 * The sequential version
	 * @param range
	 * @return
	 */
	public static int multiplyThroughSequential( List<Integer> linkedListOfNumbers ) {
		return linkedListOfNumbers.stream()
				.reduce( 5, ( acc, x ) -> x * acc );
	}

	/**
	 * The buggy parallel version
	 * @param range
	 * @return
	 */
	public static int multiplyThroughBuggy( List<Integer> linkedListOfNumbers ) {
		return linkedListOfNumbers.stream()
				.parallel()
				.reduce( 5, ( acc, x ) -> x * acc );
	}

	/**
	 * The fixed parallel version
	 * 
	 * As the book says, "for this operation to work correctly in parallel, it needs to be the
	 * *identity* value of the combining function. The identity value leaves all other elements
	 * the same when reduced with them.
	 * 
	 * Let's solve for i...
	 * 
	 * i * x = x
	 * i = x / x
	 * i = 1
	 * 
	 * @param range
	 * @return
	 */
	public static int multiplyThroughParallel( List<Integer> linkedListOfNumbers ) {
		return 5 * linkedListOfNumbers.stream()
				.parallel()
				.reduce( 1, ( acc, x ) -> x * acc );
	}
}
