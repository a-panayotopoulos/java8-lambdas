package com.lambdaherding.edi.red.ch02;

import java.util.function.Predicate;

import javax.swing.JButton;

public class TypeInferenceQuestion {
	public static void main( String[] args ) {
		// 1. Runnable helloWorld = () -> System.out.println("hello world");
		// Of course, no parameters to resolve
		Runnable hello = () -> System.out.println( "hello world" );

		// 2. The lambda expression being used as an ActionListener
		// Sure, it follows the types signature of the interface.
		JButton button = new JButton();
		button.addActionListener( event -> System.out.println( event.getActionCommand() ) );

		// 3. Would check(x -> x > 5) be inferred, given the following overloads for check?
		/*
			interface IntPred {
			    boolean test(Integer value);
			}

			boolean check(Predicate<Integer> predicate);

			boolean check(IntPred predicate);
		 */
		// No! It's ambiguous which interface to infer the lambda as. You can
		// explicitly ask as follows:
		check( (IntPred) x -> x > 5 );
	}

	public static interface IntPred {
		boolean test(Integer value);
	}

	public static boolean check( Predicate<Integer> predicate ) {
		return predicate.test( 5 );
	}

	public static boolean check( IntPred predicate ) {
		return predicate.test( 5 );
	}
}
