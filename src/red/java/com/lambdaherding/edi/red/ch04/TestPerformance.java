package com.lambdaherding.edi.red.ch04;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.lambdaherding.edi.ch04.Artist;
import com.lambdaherding.edi.ch04.SampleData;

/** Test the {@link Performance} class. */
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
				"Paul McCartney", "George Harrison", "Ringo Starr" } );

		assertEquals( everyone, performance.getAllMusicians().map( a -> a.getName() ).collect( Collectors.toList() ) );
	}
}
