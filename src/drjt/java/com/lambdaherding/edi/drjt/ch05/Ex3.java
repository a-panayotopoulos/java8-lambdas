package com.lambdaherding.edi.drjt.ch05;

import java.util.HashMap;
import java.util.Map;

public class Ex3 {

	private Map<Integer, Integer> seenFibonnacci;

	public Ex3( Integer n0, Integer n1 ) {
		seenFibonnacci = new HashMap<>();
		seenFibonnacci.put( 0, n0 );
		seenFibonnacci.put( 1, n1 );
	}

	public Ex3() {
		this( 0, 1 );
	}

	public Integer getFibonnacciNumber( Integer n ) {
		if ( n < 0 ) {
			return seenFibonnacci.computeIfAbsent( n, x -> getFibonnacciNumber( x + 2 ) - getFibonnacciNumber( x + 1 ) );
		}
		return seenFibonnacci.computeIfAbsent( n, x -> getFibonnacciNumber( x - 1 ) + getFibonnacciNumber( x - 2 ) );
	}

}
