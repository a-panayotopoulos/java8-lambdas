package com.lambdaherding.edi.ram.ch03;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Ex1 {

	public int addUp(Stream<Integer> numbers) {
		return numbers.reduce( 0, (acc, element) -> acc + element);
	}

	public List<String> getNamesAndOrigin(Stream<Artist> artists) {
		return artists.map(artist -> (artist.getName() + ", " + artist.getPlaceOfOrigin()))
				      .collect(toList());
	}
	
	public List<Album> getAlbumsWithAtMost3Tracks(Stream<Album> albums) {
		return albums.filter(album -> album.getTracks().size() <= 3)
				.collect(toList());
	}
}
