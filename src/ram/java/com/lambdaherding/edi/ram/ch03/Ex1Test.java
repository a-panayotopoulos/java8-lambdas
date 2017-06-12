package com.lambdaherding.edi.ram.ch03;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Ex1Test {

	@Test
	public void testAddUp() {
		Ex1 ex1 = new Ex1();
		assertEquals("Test 1, 2 should be 3", 3, ex1.addUp(Arrays.asList(1, 2).stream()));
		assertEquals("Test 1, 2, 3 should be 6", 6, ex1.addUp(Arrays.asList(1, 2, 3).stream()));
		assertEquals("Test 1, 2, 1 should be 4", 4, ex1.addUp(Arrays.asList(1, 2, 1).stream()));
	}
	
	@Test
	public void testGetNamesAndOrigin() {
		Ex1 ex1 = new Ex1();
		List<Artist> artists = Arrays.asList(
				new Artist("Bon Jovi", "New Jersey"), 
				new Artist("AC/DC", "England")
			);
		
		assertEquals("Expecting list of names combined with place of origin", Arrays.asList("Bon Jovi, New Jersey", "AC/DC, England"), ex1.getNamesAndOrigin(artists.stream()));	
	}

	@Test
	public void testGetAlbumsWithAtMost3Tracks() {
		Ex1 ex1 = new Ex1();
		Track t = new Track("A track", 180);
		Album smallAlbum = new Album("Small Album", Arrays.asList(t));
		Album medAlbum = new Album("Medium Album", Arrays.asList(t, t, t));
		Album largeAlbum = new Album("Large Album", Arrays.asList(t, t, t, t));
		Album xlAlbum = new Album("Extra Large Album", Arrays.asList(t, t, t, t, t, t, t, t, t, t, t, t));
		
		List<Album> albums = Arrays.asList(smallAlbum, medAlbum, largeAlbum, xlAlbum);
		
		assertEquals("Expecting only small and med albums", Arrays.asList(smallAlbum, medAlbum), ex1.getAlbumsWithAtMost3Tracks(albums.stream()));
	}
}
