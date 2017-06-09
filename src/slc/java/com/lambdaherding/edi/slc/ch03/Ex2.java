package com.lambdaherding.edi.slc.ch03;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.lambdaherding.edi.slc.ch03.assets.Artist;

public class Ex2 {

	public static int getTotalBandMembersForAllArtistsOriginal(List<Artist> artists) {
		int totalMembers = 0;
		for (Artist artist : artists) {
			Stream<Artist> members = artist.getMembers();
			totalMembers += members.count();
		}
		return totalMembers;
	}

	public static int getTotalBandMembersForAllArtists(List<Artist> artists) {
		return artists.stream().map(a -> a.getMembers().count()).reduce(0L, Long::sum).intValue();
	}

	public static void main(String[] args) {
		// old
		System.out.println(getTotalBandMembersForAllArtistsOriginal(getArtists()));

		// new
		System.out.println(getTotalBandMembersForAllArtists(getArtists()));
	}

	public static List<Artist> getArtists() {
		return Arrays.asList(new Artist("Bob", Stream.of(new Artist("Bob"), new Artist("Barry"), new Artist("Badger"))),
				new Artist("Sean", Stream.of(new Artist("Andy"), new Artist("Art"))),
				new Artist("Joe", Stream.of(new Artist("Z"))), new Artist("Barry", Stream.of(new Artist("Angus"))));
	}
}
