package com.lambdaherding.edi.slc.ch03;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Ex7 {

	public static Optional<String> getStringWithMostLowercase(List<String> strings) {
		// I would probably use a pair or something
		return strings.stream()
				.map(s -> new SimpleEntry<>(Optional.of(s), s.chars().filter(i -> Character.isLowerCase(i)).count()))
				.reduce(new SimpleEntry<>(Optional.empty(), 0L), (a, b) -> a.getValue() > b.getValue() ? a : b)
				.getKey();
	}

	public static void main(String[] args) {
		System.out.println(getStringWithMostLowercase(Arrays.asList("abTTTYYY", "kooGJHGFJHF", "PoooNNppibv", "aH")));
		System.out.println(getStringWithMostLowercase(Arrays.asList()));
	}

}
