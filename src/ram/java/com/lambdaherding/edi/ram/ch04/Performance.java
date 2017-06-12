package com.lambdaherding.edi.ram.ch04;

import java.util.stream.Stream;

import com.lambdaherding.edi.ch04.Artist;

public interface Performance {

    public String getName();

    public Stream<Artist> getMusicians();

    public default Stream<Artist> getAllMusicians() {
    	return getMusicians()
    		.flatMap(artist -> 
    			(artist.getMembers().count() > 0) ? artist.getMembers() : Stream.of(artist)
    	);
    };
    
    // A default method cannot override a method from java.lang.Object
    // so can't add equals or hashCode methods
}
