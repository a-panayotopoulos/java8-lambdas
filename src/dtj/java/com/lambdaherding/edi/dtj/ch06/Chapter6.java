package com.lambdaherding.edi.dtj.ch06;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.lambdaherding.edi.ch06.Benchmark;

public class Chapter6 {
	
	public static void main(String[] args) {
		System.out.println("Ex1");
		ex01();
		System.out.println("Ex2");
		ex02();
		System.out.println("Ex3");
		ex03();
	}

	private static void ex01() {
		System.out.println( "1..5: " +  sequentialSumOfSquares(IntStream.range(1, 5)));
		System.out.println( "5..10: " +  sequentialSumOfSquares(IntStream.range(5, 10)));
		System.out.println( "100..500: " +  sequentialSumOfSquares(IntStream.range(100, 500)));
		System.out.println( "-50..75: " +  sequentialSumOfSquares(IntStream.range(-50, 75)));
	}
	
	public static int sequentialSumOfSquares(IntStream range) {
        return range
        		.parallel()
        		.map(x -> x * x)
                .sum();
    }
	
	private static void ex02() {
		List<Integer> array = Arrays.asList( 1, 1, 1 );
		System.out.println( array.toString() + ": " + multiplyThrough(array));
		array = Arrays.asList( 2, 2, 2 );
		System.out.println( array.toString() + ": " + multiplyThrough(array));
		array = Arrays.asList( 0, 1, 2, 3 );
		System.out.println( array.toString() + ": " + multiplyThrough(array));
	}
	
	public static int multiplyThrough(List<Integer> linkedListOfNumbers) {
	    return 5 * linkedListOfNumbers
	    		.parallelStream()
	            .reduce(1, (acc, x) -> x * acc);
	}
	
	private static void ex03() {
		LinkedList<Integer> input = new LinkedList<>(IntStream.range(1, 10_000).boxed().collect(Collectors.toList()));
		System.out.println("Slow version");
		Benchmark.benchmark(Chapter6::slowSumOfSquares, input);
		System.out.println("Faster version");
		Benchmark.benchmark(Chapter6::fastSumOfSquares, input);
	}
	
    public static int slowSumOfSquares(LinkedList<Integer> input) {
        return input.parallelStream()
                                  .map(x -> x * x)
                                  .reduce(0, (acc, x) -> acc + x);
    }
    
    public static int fastSumOfSquares(LinkedList<Integer> input) {
        return input.parallelStream()
                                  .mapToInt(x -> x * x)
                                  .sum();
    }
}
