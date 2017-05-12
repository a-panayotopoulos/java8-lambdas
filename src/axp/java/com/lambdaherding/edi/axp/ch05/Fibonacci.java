package com.lambdaherding.edi.axp.ch05;

import java.util.HashMap;

public class Fibonacci {
	private HashMap<Integer,Integer> values;
	private AuditableFunction<Integer,Integer> computeFn;

	public Fibonacci() {
		values = new HashMap<>();
		values.put( 0, 1 );
		values.put( 1, 1 );

		computeFn = p -> values.computeIfAbsent( p - 1, computeFn::apply ) + values.get( p - 2 );
	}

	public int get( int position ) {
		if ( position < 0 ) {
			throw new IllegalArgumentException( "Position must be positive" );
		}

		return values.computeIfAbsent( position, computeFn::apply );
	}

	protected int auditNumberOfComputations() {
		return computeFn.auditNumberOfCalls();
	}
}
