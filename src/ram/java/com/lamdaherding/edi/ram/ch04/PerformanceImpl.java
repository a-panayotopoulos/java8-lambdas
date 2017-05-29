package com.lamdaherding.edi.ram.ch04;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.lambdaherding.edi.ch04.Artist;

public class PerformanceImpl implements Performance {

	private String name;
	
	private List<Artist> artists = new ArrayList<>();
	
	public PerformanceImpl(String name, List<Artist> artists) {
		super();
		this.name = name;
		this.artists = artists;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Stream<Artist> getMusicians() {
		return artists.stream();
	}

}
