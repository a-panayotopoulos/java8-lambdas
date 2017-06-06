package com.lambdaherding.edi.slc.ch03;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lambdaherding.edi.slc.ch03.assets.Album;
import com.lambdaherding.edi.slc.ch03.assets.Artist;

public class Ex1 {

	public static int addUp(Stream<Integer> numbers) {
		return numbers.reduce(0, Integer::sum);
	}

	public static List<String> prettyPrintArtist(Stream<Artist> artists) {
		return artists.map(a -> a.getName() + " : " + a.getPlaceOfOrigin()).collect(Collectors.toList());
	}

	public static List<Album> getAlbumsWithLessThan3Songs(List<Album> albums) {
		return albums.stream().filter(a -> a.getTracks().size() < 4).collect(Collectors.toList());
	}

	public static void main(String[] args) {
		// ex1
		System.out.println(addUp(Stream.of(1, 2, 3, 4)));

		// ex2
		System.out.println(prettyPrintArtist(getArtists()));

		// ex3
		System.out.println(getAlbumsWithLessThan3Songs(getAlbums()));
	}

	public static Stream<Artist> getArtists() {
		return Stream.of(new Artist("Bob", "Ireland"), new Artist("Sean", "Scotland"), new Artist("Joe", "England"),
				new Artist("Barry", "Wales"));
	}

	public static List<Album> getAlbums() {
		return Arrays.asList(new Album(Arrays.asList("Bon Bon")), new Album(Arrays.asList("1", "2", "3", "4")));
	}

}
