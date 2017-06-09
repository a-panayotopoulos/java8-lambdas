package com.lambdaherding.edi.qli.ch03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lambdaherding.edi.qli.ch01.Album;
import com.lambdaherding.edi.qli.ch01.Artist;
import com.lambdaherding.edi.qli.ch01.SampleData;

public class Questions {

	/**
	 * Exercises 1.1 A function that adds up numbers, i.e., int
	 * addUp(Stream<Integer> numbers)
	 */
	public int addUp(Stream<Integer> numbers) {
		return numbers.reduce(0, (acc, element) -> acc + element);
	}

	/**
	 * Exercises 1.2 A function that takes in artists and returns a list of
	 * strings with their names and places of origin
	 */
	public List<String> listNameAndOrigin(Artist artists) {
		return artists.getMembers()
				.map(a -> a.getName() + "_from_" + a.getNationality())
				.collect(Collectors.toList());
	}

	/**
	 * Exercises 1.3 A function that takes in albums and returns a list of
	 * albums with at most three tracks
	 */
	public List<Album> listSmallAlbum(Stream<Album> albums) {
		return albums.filter(a -> a.getTrackList().size() <= 3)
				.collect(Collectors.toList());
	}

	/**
	 * Exercises 2
	 */
	public long ex2(List<Artist> artists){
		
/*		int totalMembers = 0;
	    for (Artist artist : artists) {
	        Stream<Artist> members = artist.getMembers();
	        totalMembers += members.count();
	    }
		return totalMembers;*/
	    
	    //Rewrite
	    return artists.stream().mapToLong( a -> a.getMembers().count() ).sum();
	}
	
	/** 
	 * Exercises 5
	 * Pure functions. Are these lambda expressions side effect-free, or do they mutate state?
	 */
	public void ex5() {
		AtomicInteger count = new AtomicInteger(0);
		SampleData.manyTrackAlbum.getMusicians().forEach(
				musician -> count.incrementAndGet());
		System.out.println("Question5: " + count.get());

	}
	
	/**
	 * Exercises 6
	 * Count the number of lowercase letters in a String (hint: look at the chars method on String).
	 */
	public void ex6(){
		String ss = "This is a Test String!";
		long count = ss.chars().filter( Character::isLowerCase ).count();
		System.out.println("Question6: number of lower case char is [" + count+"]");
	}
	
	/**
	 * Exercises 7
	 * Find the String with the largest number of lowercase letters from a List<String>. You can return an Optional<String> to account for the empty list case.
	 */
	public void ex7(){
	
		String selectedString = Stream.of( "This", "is", "a", "Test", "String", "!", "" ).
										max( Comparator.comparing( 
												s -> s.chars().filter( Character::isLowerCase ).count()) 
											).get();
		System.out.println("Question7: the String with the largest number of lowercase is [" + selectedString+"]");
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Questions q = new Questions();

		System.out.println("Question1.1: addUp=" + q.addUp(Stream.of(1, 2, 3)));

		System.out.println("Question1.2: ");
		List<String> aList = q.listNameAndOrigin(SampleData.theBeatles);
		aList.forEach(a -> System.out.println(a));

		System.out.println("Question1.3: ");
		List<Album> albums = q.listSmallAlbum( Stream.of(SampleData.aLoveSupreme, SampleData.sampleShortAlbum, SampleData.manyTrackAlbum) );
		albums.forEach(a -> System.out.println("<"+a.getName()+"> has "+a.getTrackList().size()+" tracks"));

		
		System.out.println("Question2: "+q.ex2(SampleData.getThreeArtists()));
		
		q.ex5();
		q.ex6();
		q.ex7();
	}

}
