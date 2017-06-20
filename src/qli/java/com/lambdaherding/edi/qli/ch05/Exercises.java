package com.lambdaherding.edi.qli.ch05;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class Exercises {

	/**
	 * Exercises 1 Method references. Take a look back at the examples in
	 * Chapter 3 and try rewriting the following using method references:
	 * 
	 */
	@Test
	public void ex1() {
		/* 1. The map to uppercase */
		List<String> collected = Stream.of("a", "b", "hello")
				.map(String::toUpperCase).collect(toList());
		assertEquals(asList("A", "B", "HELLO"), collected);

		/* 2. The implementation of count using reduce */
		int count = Stream.of(1, 2, 3).reduce(0, this::add);
		assertEquals(6, count);

		/* 3. The flatMap approach to concatenating lists */
		List<Integer> together = Stream.of(asList(1, 2), asList(3, 4))
				.flatMap(List::stream).collect(toList());
		assertEquals(asList(1, 2, 3, 4), together);
	}

	private int add(int x, int y) {
		return x + y;
	}

	/**
	 * Exercise 2 Collectors
	 * 
	 * 2.1 Find the artist with the longest name. You should implement this
	 * using a Collector and the reduce higher-order function from Chapter 3.
	 * Then compare the differences in your implementation: which was easier to
	 * write and which was easier to read? The following example should return
	 * "Stuart Sutcliffe":
	 * 
	 */
	@Test
	public void ex2_1() {
		Stream<String> names = Stream.of("John Lennon", "Paul McCartney",
				"George Harrison", "Ringo Starr", "Pete Best",
				"Stuart Sutcliffe");

		Optional<String> maxName = names.collect(Collectors.maxBy(Comparator
				.comparing(String::length)));
		assertEquals("Stuart Sutcliffe", maxName.get());

	}

	/**
	 * 2.2 Given a Stream where each element is a word, count the number of
	 * times each word appears. So, if you were given the following input, you
	 * would return a Map of [John → 3, Paul → 2, George → 1]:
	 */
	@Test
	public void ex2_2() {
		Stream<String> names = Stream.of("John", "Paul", "George", "John",
				"Paul", "John");

		Map<String, Long> nameCount = names.collect(Collectors.groupingBy(
				String::toString, Collectors.counting()));

		assertEquals(3, nameCount.get("John").intValue());
		assertEquals(2, nameCount.get("Paul").intValue());
		assertEquals(1, nameCount.get("George").intValue());
	}

	/**
	 * 2.3 Implement Collectors.groupingBy as a custom collector. You don’t need
	 * to provide a downstream collector, so just implementing the simplest
	 * variant is fine. If you look at the JDK source code, you’re cheating!
	 * Hint: you might want to start with public class GroupingBy<T, K>
	 * implements Collector<T, Map<K, List<T>>, Map<K, List<T>>>. This is an
	 * advanced exercise, so you might want to attempt it last.
	 */
	@Test
	public void ex2_3() {
	}

	/**
	 * Exercise 3 Map enhancements.
	 * 
	 * Efficiently calculate a Fibonacci sequence using just the computeIfAbsent
	 * method on a Map. By “efficiently,” I mean that you don’t repeatedly
	 * recalculate the Fibonacci sequence of smaller numbers.
	 */
	@Test
	public void ex3() {
	}
}
