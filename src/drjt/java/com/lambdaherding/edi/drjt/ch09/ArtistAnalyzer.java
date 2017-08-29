package com.lambdaherding.edi.drjt.ch09;

import java.util.function.Consumer;

public interface ArtistAnalyzer {

	void isLargerGroup( String artistName, String otherArtistName,
	    Consumer<Boolean> handler );

}
