package com.lambdaherding.edi.ram.ch04;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.lambdaherding.edi.ch04.Artist;

public class PerformanceTest {
	
	private Performance perf;
	
	private List<Artist> artists;

	private List<Artist> bandMembers = new ArrayList<>();
	
	@Before
	public void setUp() throws Exception {
		artists = new ArrayList<Artist>();
		artists.add(new Artist("Individual Artist", "French"));
		
		bandMembers.add(new Artist("Band Member 1", "British"));
		bandMembers.add(new Artist("Band Member 2", "British"));
		bandMembers.add(new Artist("Band Member 3", "British"));
		bandMembers.add(new Artist("Band Member 4", "British"));
		
		artists.add(new Artist("Band", bandMembers, "British"));
		perf = new PerformanceImpl("test", artists);
		
	}

	@Test
	public void test() {
		List<Artist> allMusicians = perf.getAllMusicians().collect(Collectors.toList());
		assertEquals(
				"Expecting a list of individual artists and band members", 
				Arrays.asList(artists.get(0), bandMembers.get(0), bandMembers.get(1), bandMembers.get(2), bandMembers.get(3) ), 
				allMusicians);
		
		allMusicians.forEach(artist -> System.out.println(artist.getName()));
		
		
	}

}
