package com.lambdaherding.edi.mssw.ch03;

public class Question6 {

	public static long lowercaseLetters(String string) {

		return string.chars()
				// wtf why is it a stream of ints?!
				.mapToObj(ch -> (char) ch)
				.filter(Character::isLowerCase)
				.count();
	}
}
