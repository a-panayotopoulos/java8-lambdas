package com.lambdaherding.edi.abs.ch03;

import com.lambdaherding.edi.abs.ch03.model.Artist;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by E070699 on 5/15/2017.
 */
public class Iteration {

	public int getTotalMemberCast(Artist[] artists){
		return (int)Stream.of(artists)
				.mapToLong(artist -> artist.getMembers().count())
				.reduce(0, Long::sum);
	}

	public int getTotalMemberInternal(Artist[] artists){
		return Stream
				.of(artists)
				.map(artist -> artist.getMembers().count())
				.reduce(0L, Long::sum)
				.intValue();
	}
}
