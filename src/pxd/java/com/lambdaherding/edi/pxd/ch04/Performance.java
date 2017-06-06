package com.lambdaherding.edi.pxd.ch04;

import java.util.stream.Stream;

import com.lambdaherding.edi.ch04.Artist;
import static java.util.stream.Stream.concat;

public interface Performance {

	public String getName();

	public Stream<Artist> getMusicians();

	public default Stream<Artist> getAllMusicians() {
		return getMusicians()
		    .flatMap( x -> concat( Stream.of( x ), x.getMembers() ) );
	}

}
