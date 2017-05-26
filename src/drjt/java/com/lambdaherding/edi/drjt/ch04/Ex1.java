package com.lambdaherding.edi.drjt.ch04;

import java.util.stream.Stream;

import com.lambdaherding.edi.ch04.Artist;
import com.lambdaherding.edi.ch04.Performance;

public interface Ex1 extends Performance {

	public default Stream<Artist> getAllMusicians() {
		return getMusicians().flatMap( artist -> Stream.concat( Stream.of( artist ), artist.getMembers() ) );
	}

}
