package com.lambdaherding.edi.drjt.ch03;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lambdaherding.edi.drjt.artistmodel.Album;
import com.lambdaherding.edi.drjt.artistmodel.Artist;

public class Ex1to7<T, R> {

	// 1.1
	int addUp( Stream<Integer> numbers ) {
		return numbers.reduce( 0, ( a, b ) -> a + b );
	}

	// 1.2
	List<String> namesAndPlacesOfOrigin( Stream<Artist> artists ) {

		return artists
				.map( artist -> String.format( "%s, %s", artist.getName(), artist.getNationality() ) )
				.collect( Collectors.toList() );
	}

	// 1.3
	List<Album> albumsWithAtMost3Tracks( Stream<Album> albums ) {
		return albums.filter( album -> (album.getTrackList().size() <= 3) ).collect( Collectors.toList() );
	}

	// 2 
	long totalMembers( List<Artist> artists ) {
		return artists.stream().flatMap( artist -> artist.getMembers() ).count();
	}

	// 3
	//	 1. yes
	//	 2. no

	// 4
	//	 1. yes - takes a function as a parameter
	//	 2. no - does not

	// 5
	//	 1. effect-free
	//	 2. mutates count

	// 6
	long countLowercaseCharacters( String str ) {
		return str.chars().filter( ch -> Character.isLowerCase( ch ) ).count();
	}

	// 7
	Optional<String> findMostLowercaseCharacters( List<String> strings ) {
		return strings.stream().max( Comparator.comparing( string -> countLowercaseCharacters( string ) ) );
	}

	Stream<R> map( Stream<T> input, Function<T, R> function ) {
		return input.reduce(
				Stream.empty(),
				( stream, arg ) -> Stream.concat( stream, Stream.of( function.apply( arg ) ) ),
				( a, b ) -> Stream.concat( a, b ) );
	}

	Stream<T> filter( Stream<T> input, Predicate<T> test ) {
		return input.reduce( Stream.empty(),
				( stream, arg ) -> test.test( arg ) ? Stream.concat( stream, Stream.of( arg ) ) : stream,
				( a, b ) -> Stream.concat( a, b ) );
	}

}
