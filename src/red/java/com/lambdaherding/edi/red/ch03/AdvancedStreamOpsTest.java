package com.lambdaherding.edi.red.ch03;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

public class AdvancedStreamOpsTest {

	@Test
	public void testSimpleMap() {
		List<Integer> ls = new StreamDecorator<>( Stream.of( 1, 2, 3 ) )
			.map( x -> x + 1 )
			.asList();

		Assert.assertArrayEquals( new Integer[]{ 2, 3, 4 }, ls.toArray() );
	}

	@Test
	public void testSimpleFilter() {
		List<Integer> ls = new StreamDecorator<>( Stream.of( 1, 2, 3, 4, 5 ) )
			.filter( x -> x % 2 == 0 )
			.asList();

		Assert.assertArrayEquals( new Integer[]{ 2, 4 }, ls.toArray() );
	}

	@Test
	public void testChainedOps() {
		List<String> ls = new StreamDecorator<>( Stream.of( 1, 2, 3, 4, 5 ) )
			.map( x -> x * x )
			.filter( x -> x <= 16 )
			.map( x -> "[" + x + "]" )
			.asList();

		Assert.assertArrayEquals( new String[]{
			"[1]", "[4]", "[9]", "[16]"
		}, ls.toArray() );
	}
}
