package com.lambdaherding.edi.mdw.ch04;

import java.util.stream.Stream;

import com.lambdaherding.edi.ch04.Artist;

public class Show implements Performance {

	private Stream<Artist> bands;

	@Override
	public Stream<Artist> getMusicians() {
		return bands;
	}
	
	@Override
	public void setMusicians( Stream<Artist> bands) {
		this.bands = bands;
	}
	
}
