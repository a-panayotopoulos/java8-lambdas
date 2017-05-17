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
				return Stream.of(
					new Musician( "Elton John" ),
					new Group( "The Beatles" )
						.addMember( new Musician( "John Lennon" ) )
						.addMember( new Musician( "Ringo Starr" ) )
						.addMember( new Musician( "Paul McCartney" ) )
						.addMember( new Musician( "George Harrison" ) ),
					new Musician( "David Bowie" ) );
			}
		};
	}

	@Test
	public void testGetMusicians() {
		assertEquals(
				Arrays.asList( new String[] { "Elton John", "The Beatles", "David Bowie" } ),
				performance.getMusicians().map( a -> a.getName() ).collect( Collectors.toList() ) );
	}

	@Test
	public void testGetAllMusicians() {
		List<String> everyone = Arrays.asList( new String[] {
				"Elton John", "The Beatles", "John Lennon", "Ringo Starr", "Paul McCartney", "George Harrison", "David Bowie" } );

		// TODO: This doesn't work. Needs to be getAllMusicians()
		assertEquals( everyone, performance.getMusicians().map( a -> a.getName() ).collect( Collectors.toList() ) );
	}
}
