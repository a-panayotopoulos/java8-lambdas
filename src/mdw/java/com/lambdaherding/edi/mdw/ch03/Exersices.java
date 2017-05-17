package com.lambdaherding.edi.mdw.ch03;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exersices {
	
	public static int addUp( Stream<Integer> numbers ) {
		return numbers.reduce( 0, (x,y) -> x+y );
	}
	
	public static List<String> getArtistsNameAndOrigin(Stream<Artist> artists) {
		return artists.flatMap( x -> Arrays.asList( x.getName(), x.getNationality() ).stream() ).collect( Collectors.toList() );
	}
	
	public static Stream<Album> getAlbumWithThreeTracks( Stream<Album> albums ) {
		return albums.filter( x -> x.getTrackList().size() < 4 );
	}
	
	public static int countMembers( Stream<Artist> bands ) {
		return (int) bands.flatMap( x -> x.getMembers() ).count();
	}

	public static int countLowercaseLetters( String string ) {
		return (int) Stream.of(string.split( "")).filter( x -> Character.isLowerCase( x.charAt( 0 ) ) ).count();
	}
	
	public static Optional<String> returnStringWithMostLowrcaseLetters( Stream<String> strings ) {
		return strings.max( (x, y) -> Exersices.countLowercaseLetters( x ) );
	}
	
}
