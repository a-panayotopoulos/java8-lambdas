package com.lambdaherding.edi.dtj.ch04;

import java.util.stream.Stream;

import com.lambdaherding.edi.ch04.Artist;

public interface Performance {

	public String getName();

	public Stream<Artist> getMusicians();
	
	public default Stream<Artist> getAllMusiians() {
		return getMusicians()
			.flatMap( artist -> Stream.concat(Stream.of( artist ), artist.getMembers()));
		
	}

}
