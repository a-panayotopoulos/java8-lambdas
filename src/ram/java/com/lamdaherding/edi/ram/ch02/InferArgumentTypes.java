package com.lamdaherding.edi.ram.ch02;

import java.util.function.Predicate;

import javax.swing.JButton;

public class InferArgumentTypes {

	interface IntPred {
		boolean test(Integer value);
	}
	
	public static void main( String... args ) {
		
		// a. Runs successfully
		Runnable helloWorld = () -> System.out.println( "hello world" );
		helloWorld.run();
		
		// b. Runs successfully - modified slightly to show that there is an empty actionCommand
		JButton button = new JButton();
		button.addActionListener(event -> System.out.println( "Clicking button, command is " + (null == event.getActionCommand() ? "null" : event.getActionCommand() ) ) );
		button.doClick();

		// c. Didn't really understand. 
		Predicate<Integer> greaterThan5 = x -> x > 5;
		greaterThan5.test(6);
		
	}

}
