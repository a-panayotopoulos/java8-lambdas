package com.lambdaherding.edi.mssw.ch04;

import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.lambdaherding.edi.ch04.Artist;

public class ArtistsTest {
	private Artists artists = new Artists(Arrays.asList(
			new Artist("Liisa Riutta","UK" ),
			new Artist("Darijo Živković", "Brazil"),
			new Artist("Zbyněk Svoboda", "Spain"),
			new Artist("Gerth Davidsen", "Portugal"),
			new Artist("洪邕", "Estonia"),
			new Artist("Emilian Salas Carrillo", "Germany"),
			new Artist("Catherine Kazakova","Austria"),
			new Artist("Wisław Sobczak", "Poland"),
			new Artist("Alen Axelsson", "South Africa"),
			new Artist("Donglu Yeh", "Greenland"),
			new Artist("Traful Concepción Mejía", "Slovenia"),
			new Artist("Bura Simparri", "Austria"),
			new Artist("Melampus Roper", "Italy")
			));

	@Test
	public void testGetArtistName(){
		assertEquals("Wrong name selected", "洪邕", artists.getArtistName(4));
		assertEquals("Wrong name selected", "Donglu Yeh", artists.getArtistName(9));
		assertEquals("Wrong name selected", "Liisa Riutta", artists.getArtistName(0));
		assertEquals("Wrong name selected", "unknown", artists.getArtistName(356));
	}
}
