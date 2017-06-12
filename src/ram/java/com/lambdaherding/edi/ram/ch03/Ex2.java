package com.lambdaherding.edi.ram.ch03;

import java.util.List;
import java.util.stream.Stream;

public class Ex2 {

	// TODO: Still needs changing
	// TODO: Write unit test
	public int getTotalMembers(List<Artist> artists) {
		int totalMembers = 0;
		for (Artist artist : artists) {
			Stream<Artist> members = artist.getMembers();
			totalMembers += members.count();
		}
		
		return totalMembers;
	}
}

