package com.lambdaherding.edi.dtj.ch02;

import java.text.SimpleDateFormat;

import javax.swing.JButton;

public class QuestionThree {

	public static void main(String[] args) {
		/*
		 * The first one will compile
		 */
		new Thread( () -> System.out.println( "hello world" ) ).start();
		
		/*
		 * The second should compile as well.
		 */
		JButton button = new JButton();
		button.addActionListener(event ->
		  System.out.println(event.getActionCommand()));
	}
	
}
