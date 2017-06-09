package com.lambdaherding.edi.slc.ch04;

import java.util.Optional;

import com.lambdaherding.edi.ch04.Artist;
import com.lambdaherding.edi.ch04.Artists;
import com.lambdaherding.edi.ch04.SampleData;

public class Ex3 extends Artists {

	public Ex3() {
		super(SampleData.getThreeArtists());
	}

	@Override
	public Artist getArtist(int index) {
		return getOptionalArtist(index)
				.orElseThrow(() -> new IllegalArgumentException(index + " doesn't correspond to an Artist"));
	}

	public Optional<Artist> getOptionalArtist(int index) {
		return getArtists().stream().skip(index).findFirst();
	}

	public static void main(String[] args) {
		Ex3 ex3 = new Ex3();
		System.out.println(ex3.getArtist(0));
		System.out.println(ex3.getArtist(1));
		System.out.println(ex3.getArtist(2));
		// error here
		System.out.println(ex3.getArtist(3));
	}
}
