package com.lambdaherding.edi.abs.ch03;

import com.lambdaherding.edi.abs.ch03.model.Album;
import com.lambdaherding.edi.abs.ch03.model.Artist;
import com.lambdaherding.edi.abs.ch03.model.Track;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by E070699 on 5/15/2017.
 */
public class CommonStreamOperations {

	public int addUp(Stream<Integer> numbers) {
		 return numbers
				 .reduce(0, (total,number) -> total + number);
	}

	public List getNameAndPlaceListByArtistList(Stream<Artist> artists) {
		return artists
				.flatMap( artist -> Stream.of(artist.getName(),artist.getPlace()))
				.collect(toList());
	}

	public List getAlbumsWithAtMostThreeTracks(Stream<Album> albums) {
			return albums
					.filter(album -> album.getTracks().size() <= 3 )
					.map(album -> album.getName())
					.collect(toList());
	}

	public static void main(String[] args){
		CommonStreamOperations ops = new CommonStreamOperations();
		System.out.println(ops.addUp(Stream.of(10,20,30,40,50)));
		System.out.println(ops.addUp(Stream.of(11,22,33,44,55)));
		System.out.println(ops.addUp(Stream.of(12,23,34,45,56)));

		Stream<Album> albumStream = Stream.of(
				new Album("21",Arrays.asList(new Track(),new Track(),new Track())),
				new Album("25",Arrays.asList(new Track(),new Track()))
		);
		System.out.println(ops.getAlbumsWithAtMostThreeTracks(albumStream));

		Stream<Artist> artists = Stream.of(new Artist("Hank Williams", "USA"), new Artist("Waylon Jennings","UK"));
		System.out.println(ops.getNameAndPlaceListByArtistList(artists));

	}
}
