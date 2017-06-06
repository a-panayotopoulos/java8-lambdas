package com.lambdaherding.edi.drjt.ch04;

import java.util.List;
import java.util.Optional;

import com.lambdaherding.edi.ch04.Artist;

public class Ex4 {

	// Copy-pasted from Artists
	private List<Artist> artists;

	public Ex4( List<Artist> artists ) {
		this.artists = artists;
	}

//	public Artist getArtist( int index ) {
//		if ( index < 0 || index >= artists.size() ) {
//			indexException( index );
//		}
//		return artists.get( index );
//	}

	public String getArtistName( int index ) {
		return getArtist( index ).map( Artist::getName ).orElse( "unknown" );
	}
	
	public Optional<Artist> getArtist( int index ) {
      if (index < 0 || index >= artists.size()) {
          return Optional.empty();
      }
      return Optional.of( artists.get(index) );
  }
}
