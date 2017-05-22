package com.lambdaherding.edi.abs.ch03;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by E070699 on 5/15/2017.
 */
public class ForEach {

	public int getLowerCaseCounter(String name) {
		return (int)name.chars()
				.filter( myChar -> Character.isLowerCase(myChar))
				.count();
	}

	public Optional<String> mostLowerString(List<String> list) {
		return list.stream()
				.min(Comparator.comparing(str -> getLowerCaseCounter(str)) );
	}



}
