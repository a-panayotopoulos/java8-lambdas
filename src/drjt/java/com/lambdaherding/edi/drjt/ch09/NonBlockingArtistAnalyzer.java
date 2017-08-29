package com.lambdaherding.edi.drjt.ch09;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import com.lambdaherding.edi.drjt.artistmodel.Artist;

public class NonBlockingArtistAnalyzer implements ArtistAnalyzer {

	private final Function<String, Artist> artistLookupService;

	public NonBlockingArtistAnalyzer( Function<String, Artist> artistLookupService ) {
		this.artistLookupService = artistLookupService;
	}

	private CompletableFuture<Long> getNumberOfMembers( String artistName ) {
		return CompletableFuture.supplyAsync( () -> artistLookupService.apply( artistName ) )
		    .thenApply( Artist::getMembers )
		    .thenApply( Stream::count );
	}

	@Override
	public void isLargerGroup( String artistName, String otherArtistName, Consumer<Boolean> handler ) {
		CompletableFuture<Long> otherArtistMemberCount = getNumberOfMembers( otherArtistName );
		getNumberOfMembers( artistName )
		    .thenCombine( otherArtistMemberCount, ( a, b ) -> a > b )
		    .thenAccept( handler )
		    .join();
	}
}
