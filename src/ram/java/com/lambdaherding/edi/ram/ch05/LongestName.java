package com.lambdaherding.edi.ram.ch05;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;
import java.util.stream.Stream;

import org.junit.Test;

public class LongestName {

	Stream<String> names = Stream.of("John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");

	public String getLongestNameReduce(Stream<String> names) {
		return names.
				reduce((x, y) -> (x.length() < y.length() ? y : x)).get();
	}
	
	public String getLongestName(Stream<String> names) {
		return names.max(Comparator.comparing(String::length)).get();
	}
	
	@Test
	public void testLongestNameReduce() {
		assertEquals(getLongestNameReduce(names), "Stuart Sutcliffe");
	}
	
	@Test
	public void testLongestName() {
		assertEquals(getLongestName(names), "Stuart Sutcliffe");
	}
	
}
