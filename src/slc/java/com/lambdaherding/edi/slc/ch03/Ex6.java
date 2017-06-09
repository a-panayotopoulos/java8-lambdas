package com.lambdaherding.edi.slc.ch03;

public class Ex6 {

	public static long countLowerCase(String s) {
		return s.chars().filter(i -> Character.isLowerCase(i)).count();
	}

	public static void main(String[] args) {
		System.out.println(countLowerCase("abcdERFGtyui"));
		System.out.println(countLowerCase("a1223B"));
		System.out.println(countLowerCase("HHHJJJJooo"));
	}
}
