package com.lambdaherding.edi.ram.ch05;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.lambdaherding.edi.ch04.Album;
import com.lambdaherding.edi.ch04.SampleData;
import com.lambdaherding.edi.ch04.Track;

public class LongTracks implements SampleData {

	public Set<String> findLongTracksOld(List<Album> albums) {
		return albums.stream()
			.flatMap(album -> album.getTracks())
			.filter(track -> track.getLength() > 60)
			.map(Track::getName)
			.collect(toSet());
	}
	
	public Set<String> findLongTracksNew(List<Album> albums) {
		return albums.stream()
			.flatMap(Album::getTracks)
			.filter(track -> track.getLength() > 60)
			.map(Track::getName)
			.collect(toSet());
	}
	
	@Test
	public void testUpperCaseOld() {
		Set<String> strings = findLongTracksOld(asList(A_LOVE_SUPREME));
		assertTrue(strings.contains("Acknowledgement"));
		assertTrue(strings.contains("Resolution"));
		assertEquals(strings.size(), 2);
	}
	
	@Test
	public void testUpperCaseNew() {
		Set<String> strings = findLongTracksNew(asList(A_LOVE_SUPREME));
		assertTrue(strings.contains("Acknowledgement"));
		assertTrue(strings.contains("Resolution"));
		assertEquals(strings.size(), 2);
	}
}
