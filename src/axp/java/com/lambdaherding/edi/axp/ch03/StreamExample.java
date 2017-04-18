package com.lambdaherding.edi.axp.ch03;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {
	
	
	public int addUp( Stream<Integer> numbers ) {
		return numbers.collect( Collectors.summingInt( i -> i ) );
	}
}
