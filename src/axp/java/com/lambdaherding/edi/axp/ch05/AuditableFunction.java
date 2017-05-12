package com.lambdaherding.edi.axp.ch05;

import java.util.HashMap;

@FunctionalInterface
public interface AuditableFunction<T,R> {
	HashMap<AuditableFunction<?,?>,Integer> COUNTERS = new HashMap<>();

	R functionToCall( T t );

	default R apply( T t ) {
		COUNTERS.put( this, COUNTERS.getOrDefault( this, 0 ) + 1 );
		return functionToCall( t );
	}

	default int auditNumberOfCalls() {
		return COUNTERS.getOrDefault( this, 0 );
	}
}
