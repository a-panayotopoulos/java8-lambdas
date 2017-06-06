package com.lambdaherding.edi.axp.ch04;

import java.util.List;
import java.util.Optional;

import com.lambdaherding.edi.ch04.Artist;

public class Artists {
	private List<Artist> artists;

	public Artists( List<Artist> artists ) {
		this.artists = artists;
	}

	public Optional<Artist> getArtist( int index ) {
		return Optional.of( index )
				.filter( i -> i >= 0 && i < artists.size() )
				.map( i -> artists.get( i ) );
	}

	public String getArtistName( int index ) {
		return getArtist( index ).map( a -> a.getName() ).orElse( "unknown" );
	}
}
