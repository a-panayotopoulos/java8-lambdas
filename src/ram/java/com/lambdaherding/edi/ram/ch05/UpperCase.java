package com.lambdaherding.edi.ram.ch05;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import static java.util.Arrays.*;
import static org.junit.Assert.*;
import static java.util.stream.Collectors.*;

public class UpperCase {

	@Test
	public void testUpperCaseOld() {
		List<String> collected = new ArrayList<>();
		for (String string : asList("a", "b", "hello")) {
			String uppercaseString = string.toUpperCase();
			collected.add(uppercaseString);
		}
		
		assertEquals(asList("A", "B", "HELLO"), collected);
	}
	
	@Test
	public void testUpperCaseNew() {
		List<String> collected = Stream.of("a", "b", "hello")
			.map(String::toUpperCase)
			.collect(toList());
		
		assertEquals(asList("A", "B", "HELLO"), collected);
	}
}
