package com.lambdaherding.edi.dtj.ch03;

import java.util.List;

public class Exercise02 {

	public static void main(List<Artist> artists) {
		int totalMembers = artists.stream().mapToInt(a -> (int) a.getMembers().count()).sum();

		System.out.println(totalMembers);
	}

}
