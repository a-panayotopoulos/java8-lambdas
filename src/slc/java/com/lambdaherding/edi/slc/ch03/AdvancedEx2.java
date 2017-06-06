package com.lambdaherding.edi.slc.ch03;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdvancedEx2 {
	
	public static Stream<String> filterOnlyUppercase(Stream<String> strings) {
		return strings.reduce(Stream.builder(), (a, b) -> {
			if (b.chars().noneMatch(i -> Character.isLowerCase(i))) {
				a.add(b);
			}
			return a;
		}, (a, b) -> {
			return a;
		}).build().map(Object::toString);
	}

	public static void main(String[] args) {
		System.out.println(filterOnlyUppercase(Stream.of("sad", "ADS", "bHfdeH", "PPOOOTT", "24a5"))
				.collect(Collectors.joining(",")));
	}
}
