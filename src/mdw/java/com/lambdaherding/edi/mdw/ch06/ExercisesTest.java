package com.lambdaherding.edi.mdw.ch06;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

import com.lambdaherding.edi.ch06.Benchmark;


public class ExercisesTest {
	
	@Test
	public void sequentialSumOfSquaresTest(){
		IntStream range = IntStream.of(1,2,3);
		assertTrue(14 == Exercises.sequentialSumOfSquares(range));
	}
	
	@Test
	public void multiplyThroughTest(){
		List<Integer> range = new ArrayList<Integer>();
		range.add( 1 );
		range.add( 2 );
		range.add( 3 );
		assertTrue(30 == Exercises.multiplyThrough(range));
	}
	
	@Test
	public void checkSpeed(){
		LinkedList<Integer> list = new LinkedList<Integer>();
		for ( int x = 1; x < 100000; x++) {
		list.add( x );
		}
	
		Exercises.setlinkedListOfNumbers(list);
		Benchmark.benchmark( Exercises::slowSumOfSquares );
		Benchmark.benchmark( Exercises::fastSumOfSquares );
		
	}

}
