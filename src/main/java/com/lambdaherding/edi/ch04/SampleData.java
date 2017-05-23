package com.lambdaherding.edi.ch04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public interface SampleData {
	public Artist JOHN_COLTRANE = new Artist("John Coltrane", "US");
	public Artist JOHN_LENNON = new Artist("John Lennon", "UK");
	public Artist PAUL_MCCARTNEY = new Artist("Paul McCartney", "UK");
	public Artist GEORGE_HARRISON = new Artist("George Harrison", "UK");
	public Artist RINGO_STARR = new Artist("Ringo Starr", "UK");

	public List<Artist> MEMBERS_OF_THE_BEATLES =
			Arrays.asList( JOHN_LENNON, PAUL_MCCARTNEY, GEORGE_HARRISON, RINGO_STARR );

	public Artist THE_BEATLES = new Artist("The Beatles", MEMBERS_OF_THE_BEATLES, "UK");

	public Album A_LOVE_SUPREME =
			new Album("A Love Supreme", asList(
					new Track("Acknowledgement", 467),
					new Track("Resolution", 442)),
					asList(JOHN_COLTRANE));

	public Album SAMPLE_SHORT_ALBUM =
			new Album("sample Short Album", asList(new Track("short track", 30)), asList(JOHN_COLTRANE));

	public Album MANY_TRACK_ALBUM =
			new Album("sample Short Album", asList(
					new Track("short track", 30),
					new Track("short track 2", 30),
					new Track("short track 3", 30),
					new Track("short track 4", 30),
					new Track("short track 5", 30)),
					asList(JOHN_COLTRANE));

	public static Stream<Album> ALBUMS = Stream.of(A_LOVE_SUPREME);

	public static Stream<Artist> threeArtists() {
		return Stream.of(JOHN_COLTRANE, JOHN_LENNON, THE_BEATLES);
	}

	public static List<Artist> getThreeArtists() {
		return Arrays.asList(JOHN_COLTRANE, JOHN_LENNON, THE_BEATLES);
	}
}
