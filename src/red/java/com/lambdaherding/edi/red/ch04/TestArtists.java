package com.lambdaherding.edi.red.ch04;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.lambdaherding.edi.ch04.SampleData;

@SuppressWarnings("static-method")
public class TestArtists {
	@Test
	public void testGetArtistName() {
		Artists artists = new Artists( SampleData.getThreeArtists() );

		assertEquals( "unknown", artists.getArtistName( -1 ) );
		assertEquals( "John Coltrane", artists.getArtistName( 0 ) );
		assertEquals( "John Lennon", artists.getArtistName( 1 ) );
		assertEquals( "The Beatles", artists.getArtistName( 2 ) );
		assertEquals( "unknown", artists.getArtistName( 3 ) );
	}
}
