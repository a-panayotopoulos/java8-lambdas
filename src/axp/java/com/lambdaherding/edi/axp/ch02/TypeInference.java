package com.lambdaherding.edi.axp.ch02;

import java.util.function.Predicate;

import javax.swing.JButton;

@SuppressWarnings("unused")
public class TypeInference {
	static interface IntPred {
		boolean test( Integer value );
	}

	public static boolean check( Predicate<Integer> predicate, int value ) {
		return predicate.test( value );
	}

	public static boolean check( IntPred predicate, int value ) {
		return predicate.test( value );
	}

	static {
		// 1. No problems here
		Runnable helloWorld = () -> System.out.println( "hello world" );

		// 2. No problems here either
		new JButton().addActionListener( event -> System.out.println( event.getActionCommand() ) );

		// 3. Uh-oh, it can't distinguish!
		/*
		 * boolean output = check( x -> x > 5, 10 );
		 * 
		 * The method check(Predicate<Integer>, int) is ambiguous for the type TypeInference
		 */
	}
}
