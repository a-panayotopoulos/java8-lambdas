package com.lambdaherding.edi.mssw.ch04;

import java.util.List;
import java.util.stream.Stream;

import com.lambdaherding.edi.ch04.Artist;
import com.lambdaherding.edi.ch04.Performance;

public class PerformanceImpl implements Performance {
	private List<Artist> artists;
	
	public PerformanceImpl(List<Artist> artists) {
		this.artists = artists;
	}

	@Override
	public String getName() {
		return "Purple Hot";
	}

	@Override
	public Stream<Artist> getMusicians() {
		return artists.stream();
	}

	public Stream<String> getAllMusicians(){
		return getMusicians().flatMap(
				artists -> Stream.concat(
						artists.getMembers().map(artist -> artist.getName() ),
						Stream.of(artists.getName()) )
				);
	}
}
