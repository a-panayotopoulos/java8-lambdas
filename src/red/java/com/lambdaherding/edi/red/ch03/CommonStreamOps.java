package com.lambdaherding.edi.red.ch03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonStreamOps {

	// 1. A function that adds up numbers, i.e., int addUp(Stream<Integer> numbers)
	public static long addUp( Stream<Integer> numbers ) {
		return numbers.mapToInt( i -> i ).sum();
	}

	// 2. A function that takes in artists and returns a list of strings with their names and places of origin
	public static List<String> artistOrigins( Stream<Artist> artists ) {
		return artists
			.map( a -> a.名前() + ": " + a.出身() )
			.collect( Collectors.toList() );
	}

	// 3. A function that takes in albums and returns a list of albums with at most three tracks
	public static List<Album> eps( Stream<Album> albums ) {
		return albums
			.filter( a -> a.trackCount() <= 3 )
			.collect( Collectors.toList() );
	}

	public static void main( String[] args ) {
		// 2.1. Convert this code sample from using external iteration to internal iteration:
		/*
	 	int totalMembers = 0;
	    for (Artist artist : artists) {
	        Stream<Artist> members = artist.getMembers();
	        totalMembers += members.count();
	    }
		 */
		List<Artist> artists = new ArrayList<>();
		long totalMembers = artists.stream()
			.flatMap( Artist::getMembers )
			.count();

		System.out.println( "Total members: " + totalMembers );

		// 3.1 Evaluation. Take a look at the signatures of these Stream methods.  Are they eager or lazy?
		// boolean anyMatch(Predicate<? super T> predicate);
		// A: eager, they consume the stream.
		//
		// Stream<T> limit(long maxSize);
		// Lazy, this adds a step to the stream pipeline, but doesn't execute it

		// 4.1 Higher-order functions. Are these Stream functions higher order, and why?
		// boolean anyMatch(Predicate<? super T> predicate);
		// Yes, it accepts a function as an argument
		//
		// Stream<T> limit(long maxSize)
		// Nope, it just passes in a regular old non-function argument


		// 5. Pure functions. Are these lambda expressions side effect-free, or do they mutate state?
		// x -> x + 1
		// Pure as the driven snow
		//
		// The lambda expression passed into forEach in the example.
		/*
		AtomicInteger count = new AtomicInteger(0);
		List<String> origins = album.musicians()
        	.forEach(musician -> count.incAndGet());
		 */
		// Impure, it mutates `count` on each iteration

		// 6. Count the number of lowercase letters in a String (hint: look at the chars method on String).
		long lower = "Damn Fine Coffee".chars()
			.filter( Character::isLowerCase )
			.count();

		System.out.println( "Number of lower case: " + lower );

		// 7. Find the String with the largest number of lowercase letters from a List<String>. You can return an Optional<String> to account for the empty list case.
		List<String> messages = Arrays.asList( "Bubblegum", "Flame", "Slime", "Lumpy Space" );
		Optional<String> max = messages.stream()
			.max( Comparator.comparing( s -> s.chars()
				.filter( Character::isLowerCase )
				.count() ) );

		max.ifPresent( System.out::println );
	}

	public static class Artist {
		private String 出身;
		private String 名前;
		private List<Artist> members = new ArrayList<>();

		public Artist 名前( String 名前 ) {
			this.名前 = 名前;
			return this;
		}

		public String 名前() {
			return 名前;
		}

		public Artist 出身( String 出身 ) {
			this.出身 = 出身;
			return this;
		}

		public String 出身() {
			return 出身;
		}

		public Artist member( Artist member ) {
			members.add( member );
			return this;
		}

		public Stream<Artist> getMembers() {
			return members.stream();
		}

		public boolean isFrom( String location ) {
			return 出身 != null && location.equals( 出身 );
		}
	}

	public static class Album {
		private List<String> trackList = new ArrayList<>();

		public Album addTrackList( String... tracks ) {
			this.trackList.addAll( Arrays.asList( tracks ) );
			return this;
		}

		public int trackCount() {
			return trackList.size();
		}
	}
}
