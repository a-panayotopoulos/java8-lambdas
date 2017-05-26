package com.lambdaherding.edi.mdw.ch04;

import java.util.stream.Stream;

import com.lambdaherding.edi.ch04.Artist;

/** A Performance by some musicians - e.g., an Album or Gig. */
public interface Performance {
	
    public Stream<Artist> getMusicians();
    public void setMusicians( Stream<Artist> bands );
    
    public default Stream<Artist> getAllMusicians(){
    	return this.getMusicians().flatMap( x -> Stream.concat(  x.getMembers(), Stream.of(  x )) );
    };
}