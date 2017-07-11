package com.lambdaherding.edi.red.ch05;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lambdaherding.edi.ch04.Artist;

public class ArtistExercises {

	public Optional<Artist> longestName( Stream<Artist> artists ) {
		return artists.collect( Collectors.maxBy(
			Comparator.comparing(a -> a.getName().length()) ) );
	}

	public Map<String, Long> wordCount( Stream<String> words ) {
		return words
			.collect( Collectors.groupingBy( Function.identity(), Collectors.counting() ) );
	}
}
