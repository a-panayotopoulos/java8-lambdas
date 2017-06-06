package com.lambdaherding.edi.dtj.ch03;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Exercise01 {

	public static void main(String[] args) {
		q1();
		q2();
		q3();
	}

	/*
	 * =================================================================
	 * Question 1
	 * =================================================================
	 */

	public static void q1() {
		System.out.println( "Question 1" );
		System.out.println(addUp(IntStream.range(0, 51).boxed()));
		System.out.println(addUp(IntStream.range(-50, 51).boxed()));
		System.out.println(addUp(IntStream.range(1, 6).boxed()));
	}

	public static int addUp(Stream<Integer> numbers) {
		return numbers.reduce(0, (x, y) -> x + y);
	}

	/*
	 * =================================================================
	 * Question 2
	 * =================================================================
	 */

	public static void q2() {
		System.out.println( "Question 2" );
		List<String> artists = artists( Stream.of(
				new Artist()
					.setArtist("An artist")
					.setName("A name")
					.setOrigin("A place"),
				new Artist()
					.setArtist("A second artist")
					.setName("With a second name")
					.setOrigin("From a Second Place")));
		artists.forEach(System.out::println);
	}

	public static List<String> artists(Stream<Artist> artists) {
		return artists.map(artist -> "Name: " + artist.getName() + ", origin: " + artist.getOrigin())
				.collect(Collectors.toList());
	}

	/*
	 * =================================================================
	 * Question 3
	 * =================================================================
	 */

	public static void q3() {
		System.out.println( "Question 3" );
		List<Album> albums = threeOrLess( Stream.of(
				new Album()
					.setTitle( "Marks Songs to Twerk to" )
					.setTracksCount(7),
				new Album()
					.setTitle( "Reds Crazy Synths" )
					.setTracksCount(3),
				new Album()
					.setTitle( "Daniels Amazing Bass Solos" )
					.setTracksCount(25),
				new Album()
				.setTitle( "The Robotic Noises of Qian" )
				.setTracksCount(2)
				) );
		
		albums.forEach(album -> System.out.println(album.getTitle()));
	}
	
	public static List<Album> threeOrLess( Stream<Album> albums ) {
		return albums.filter(album -> album.getTracksCount() <= 3).collect(Collectors.toList());
	}
	
	public static class Album {
		private String title;
		private int tracksCount;
		
		public String getTitle() {
			return title;
		}
		
		public Album setTitle(String title) {
			this.title = title;
			return this;
		}
		
		public int getTracksCount() {
			return tracksCount;
		}
		
		public Album setTracksCount(int tracksCount) {
			this.tracksCount = tracksCount;
			return this;
		}
		
		
	}

}
