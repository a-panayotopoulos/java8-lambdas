package com.lambdaherding.edi.qli.ch04;

import java.util.stream.Stream;

import com.lambdaherding.edi.ch04.Artist;

/** A Performance by some musicians - e.g., an Album or Gig. */
public interface Performance {

    public String getName();

    public Stream<Artist> getMusicians();
    
    public default Stream<Artist> getAllMusicians(){
    	return Stream.concat( getMusicians(), getMusicians().flatMap( a -> a.getMembers()) );
    }

}