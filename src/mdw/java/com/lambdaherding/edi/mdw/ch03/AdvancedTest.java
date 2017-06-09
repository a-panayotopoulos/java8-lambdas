package com.lambdaherding.edi.mdw.ch03;

import static org.junit.Assert.assertArrayEquals;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.Test;

public class AdvancedTest {
	
	@Test
	public void testmapWithreduce() {
		Stream<Integer> numbers = Stream.of( 1, 2, 3, 4 );
		Function<Integer, Integer> lambda = x -> x + 2;
		assertArrayEquals( Stream.of( 3, 4, 5, 6 ).toArray(), Advanced.mapWithreduce( numbers, lambda ).toArray());
	}
	
	@Test
	public void testFilterWithreduce() {
		Stream<Integer> numbers = Stream.of( 1, 2, 3, 4 );
		Predicate<Integer> lambda = x -> x > 2;
		assertArrayEquals( Stream.of( 3, 4 ).toArray(), Advanced.filterWithreduce( numbers, lambda ).toArray());
	}

}
