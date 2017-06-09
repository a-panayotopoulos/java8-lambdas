package com.lambdaherding.edi.qli.ch04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import java.util.Arrays;

import org.junit.Test;

import com.lambdaherding.edi.ch04.SampleData;

public class Ch04Test {

	@Test
	public void testPerforamnce() {
		assertArrayEquals(
				Arrays.asList("John Coltrane", "John Lennon",
				"The Beatles", "John Lennon", "Paul McCartney",
				"George Harrison", "Ringo Starr").toArray(), 
				new PerformanceImpl().getAllMusicians().map( a -> a.getName() ).toArray()
				);
	}
	
	@Test
	public void testArtists(){
		Artists artists = new Artists( SampleData.getThreeArtists() );
		assertEquals( "John Coltrane", artists.getArtistName(0) );
		assertEquals( "John Lennon", artists.getArtistName(1) );
		assertEquals( "The Beatles", artists.getArtistName(2) );
		assertEquals( "unknown", artists.getArtistName(-1) );
		assertEquals( "unknown", artists.getArtistName(5) );
	}
	
}
