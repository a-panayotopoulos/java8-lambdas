package com.lambdaherding.edi.axp.ch04;

import java.util.stream.Stream;

import com.lambdaherding.edi.ch04.Artist;

public interface Performance {
	public String getName();

	public Stream<Artist> getMusicians();

	public default Stream<Artist> getAllMusicians() {
		return getMusicians().flatMap( a -> Stream.concat( Stream.of( a ), a.getMembers() ) );
	}
}
