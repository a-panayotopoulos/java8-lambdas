package com.lambdaherding.edi.qli.ch04;

import java.util.stream.Stream;

import com.lambdaherding.edi.ch04.Artist;
import com.lambdaherding.edi.ch04.SampleData;

public class PerformanceImpl implements Performance {
	
	@Override
	public String getName() {
		return "PerformanceImpl";
	}

	@Override
	public Stream<Artist> getMusicians() {		
		return SampleData.threeArtists();
	}

}
