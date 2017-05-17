package com.lambdaherding.edi.ch04;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

/** Make sure this still succeeds after refactoring {@link Artists}! */
public class TestArtists {
	private Artists artists;

	@Before
	public void setup() {
		artists = new Artists( Arrays.asList( new Artist[] {
			new Musician( "Elton John" ),
			new Group( "The Beatles" )
				.addMember( new Musician( "John Lennon" ) )
				.addMember( new Musician( "Ringo Starr" ) )
				.addMember( new Musician( "Paul McCartney" ) )
				.addMember( new Musician( "George Harrison" ) ),
			new Musician( "David Bowie" )
		}));
	}

	@Test
	public void testGetArtistName() {
		assertEquals( "unknown", artists.getArtistName( -1 ) );
		assertEquals( "Elton John", artists.getArtistName( 0 ) );
		assertEquals( "The Beatles", artists.getArtistName( 1 ) );
		assertEquals( "David Bowie", artists.getArtistName( 2 ) );
		assertEquals( "unknown", artists.getArtistName( 3 ) );
	}
}
