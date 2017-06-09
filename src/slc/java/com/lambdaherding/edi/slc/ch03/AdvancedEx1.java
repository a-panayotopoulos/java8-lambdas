package com.lambdaherding.edi.slc.ch03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class AdvancedEx1 {

	public static List<Integer> mapStringToInteger(Stream<String> strings) {
		return strings.reduce(new ArrayList<Integer>(), (ArrayList<Integer> a, String b) -> {
			a.add(Integer.parseInt(b));
			return a;
		}, (ArrayList<Integer> a, ArrayList<Integer> b) -> {
			return a;
		});
	}

	public static void main(String[] args) {

		List<String> strings = Arrays.asList("12", "2", "6");
		System.out.println(strings);
		System.out.println(mapStringToInteger(strings.stream()));
	}

}
