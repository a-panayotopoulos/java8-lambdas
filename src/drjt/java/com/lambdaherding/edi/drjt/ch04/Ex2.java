package com.lambdaherding.edi.drjt.ch04;


public class Ex2 {

	// Based on the resolution rules described earlier, can you ever override equals or hashCode in a default method?

	// No - Object is a class, and takes priority over default methods

	/*
	interface Ex2Interface {
		public default boolean equals( Object other ) {
			System.out.println( "equals" );
			return false;
		}
	
		public default int hashCode() {
			System.out.println( "hashCo(veri)de" );
			return 1;
		}
	}
	*/
}
