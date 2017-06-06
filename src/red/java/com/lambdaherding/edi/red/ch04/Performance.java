package com.lambdaherding.edi.red.ch04;

import java.util.stream.Stream;

import com.lambdaherding.edi.ch04.Artist;

public interface Performance {

	public String getName();

	public Stream<Artist> getMusicians();

	// Ex. 1:
	default Stream<Artist> getAllMusicians() {
		return getMusicians()
			.flatMap(a -> Stream.concat(Stream.of(a), a.getMembers()));
	}

	// Ex. 2: Based on the resolution rules described earlier, can you ever
	// override equals or hashCode in a default method?
	//
	// A: No, equals and hashCode are defined in `Object`, which is a class,
	// and will be used preferentially.
}
