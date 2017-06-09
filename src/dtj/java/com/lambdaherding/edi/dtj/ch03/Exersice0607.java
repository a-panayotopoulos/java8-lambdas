package com.lambdaherding.edi.dtj.ch03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Exersice0607 {
	
	public static void main(String[] args) {
		List<String> strings = new ArrayList<>(
				Arrays.asList(
						"aaaabbbbccccddddeeee",
						"Things and Stuff and Numbers like 123987987 or symbols like !£$%^&*(*&^%$£$%^&)",
						"AAAABBBBCCCCDDDD",
						"",
						null
						));
		
		strings.forEach(Exersice0607::printCountOf);
		
		System.out.println( "The string with the most lowercase chars is: " + mostLowerCase(strings) );
	}
	
	public static void printCountOf( String toPrint ) {
		System.out.println( "Count of: '" + toPrint + "' = " + countOfLowerCase(toPrint) );
	}
	
	/*
	 * =================================================================
	 * Exersice 6
	 * =================================================================
	 */
	
	public static long countOfLowerCase( String toCount ) {
		return toCount == null ? 0 : toCount.chars().filter( c -> c >= 'a' && c <= 'z').count();
	}
	
	/*
	 * =================================================================
	 * Exersice 7
	 * =================================================================
	 */
	
	public static String mostLowerCase( List<String> strings ) {
		return strings
			.stream()
			.max( Comparator.comparing(Exersice0607::countOfLowerCase) )
			.get();
	}


}
