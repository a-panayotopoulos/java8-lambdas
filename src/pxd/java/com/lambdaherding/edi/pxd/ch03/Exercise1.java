package com.lambdaherding.edi.pxd.ch03;

import java.util.List;
import java.util.stream.Stream;

public class Exercise1 {

	public static int addUp(Stream<Integer> numbers) {
		return numbers.reduce( 0, ( acc, element ) -> acc + element );
	}

	public static List<String> getNamesAndPlaces( List<Artist> artists ) {
		return artists.stream().flatMap( artist -> Stream.of( artist.getName(), artist.getPlaceOfOrigin() ) ).collect( toList() );
	}


}
