package com.lambdaherding.edi.mdw.ch06;

import java.util.List;
import java.util.stream.IntStream;

public class Exercises {
	
	static List<Integer> linkedListOfNumbers;
	
	public static int sequentialSumOfSquares(IntStream range) {
		return range.parallel().map(x -> x * x).sum();
		}
	
	public static int multiplyThrough(List<Integer> linkedListOfNumbers) {
		return linkedListOfNumbers.stream().parallel()
			.reduce(1, (acc, x) -> x * acc) * 5;
		}
	
	public static void setlinkedListOfNumbers (List<Integer> linkedListOfNumbers){
		Exercises.linkedListOfNumbers = linkedListOfNumbers;
	}
	
  public int slowSumOfSquares() {
    return linkedListOfNumbers.parallelStream()
                              .map(x -> x * x)
                              .reduce(0, (acc, x) -> acc + x);
}
  //TODO SPEED UP 
  public int fastSumOfSquares() {
    return linkedListOfNumbers.parallelStream()
                              .mapToInt(x -> x * x)
                              .sum();
}

}
