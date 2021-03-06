package com.lambdaherding.edi.qli.ch04;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import com.lambdaherding.edi.ch04.Artist;

public class Artists {

	private List<Artist> artists;

	public Artists(List<Artist> artists) {
		this.artists = artists;
	}

	public Optional<Artist> getArtist(int index) {
		if (index < 0 || index >= artists.size()) {
			return Optional.empty();
		}
		return Optional.of(artists.get(index));
	}

	public String getArtistName(int index) {
		return getArtist(index).map(a -> a.getName()).orElse("unknown");
	}
}
