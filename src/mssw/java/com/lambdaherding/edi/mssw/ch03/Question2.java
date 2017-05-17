package com.lambdaherding.edi.mssw.ch03;

import java.util.List;
import java.util.stream.Stream;

import com.lambdaherding.edi.mssw.shared.Artist;

public class Question2 {

	public static int oldMethod(List<Artist> artists) {
		int totalMembers = 0;
		for (Artist artist : artists) {
			Stream<Artist> members = artist.getMembers();
			totalMembers += members.count();
		}
		return totalMembers;
	}

	public static long newMethod(List<Artist> artists) {
		return artists.stream()
				.flatMap(artist -> artist.getMembers())
				.count();
	}
}
