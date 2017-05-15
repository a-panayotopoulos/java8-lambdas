package com.lambdaherding.edi.mssw.ch03;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Question7 {

	public static Optional<String> findStringWithMostLowercase( List<String> strings){
		return strings.stream()
				.max(Comparator.comparingLong(Question6::lowercaseLetters ) );
	}
}
