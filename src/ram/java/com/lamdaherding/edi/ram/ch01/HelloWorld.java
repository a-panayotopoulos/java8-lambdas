package com.lamdaherding.edi.ram.ch01;

import java.util.function.BinaryOperator;

public class HelloWorld {
	
	static BinaryOperator<Integer> add = (x, y) -> x + y;

	public static void main(String... args) {
		System.out.println( "Adding 1 and 2 = " + add.apply(1, 2) );
	}

}
