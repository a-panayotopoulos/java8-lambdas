package com.lambdaherding.edi.ch04;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

/** Test the {@link Performance} class. Currently fails due to unimplemented methods */
public class TestPerformance {
	private Performance performance;

	@Before
	public void setup() {
		performance = new Performance() {
			@Override
			public String getName() {
				return "Live at Bertie Hall";
			}

			@Override
			public Stream<Artist> getMusicians() {
				return SampleData.threeArtists();
			}
		};
	}

	@Test
	public void testGetMusicians() {
		assertEquals(
				Arrays.asList( new String[] { "John Coltrane", "John Lennon", "The Beatles" } ),
				performance.getMusicians().map( a -> a.getName() ).collect( Collectors.toList() ) );
	}

	@Test
	public void testGetAllMusicians() {
		List<String> everyone = Arrays.asList( new String[] {
				"John Coltrane", "John Lennon", "The Beatles", "John Lennon",
				"Ringo Starr", "Paul McCartney", "George Harrison" } );

		// TODO: This doesn't work. Needs to be getAllMusicians()
		assertEquals( everyone, performance.getMusicians().map( a -> a.getName() ).collect( Collectors.toList() ) );
	}
}
