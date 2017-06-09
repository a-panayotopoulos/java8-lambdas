package com.lambdaherding.edi.mssw.ch03;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lambdaherding.edi.mssw.shared.Album;
import com.lambdaherding.edi.mssw.shared.Artist;

public class Question1 {

	// 1.
	public static int addUp(Stream<Integer> numbers) {
		return numbers.reduce((i, acc) -> acc + i)
				.get();
	}

	// 2.
	public static List<String> artistOrigins(Stream<Artist> artists) {
		return artists.map(artist -> String.format("%s: %s", artist.getName(), artist.getNationality()))
				.collect(Collectors.toList());
	}

	// 3.
	public static List<Album> smallAlbums(Stream<Album> albums) {
		return albums.filter(album -> album.getTracks().count() <= 3)
				.collect(Collectors.toList());
	}

}
