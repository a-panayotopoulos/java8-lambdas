package com.lambdaherding.edi.ch04;

import java.util.stream.Stream;

import com.lambdaherding.edi.ch04.Artist;

/** A Performance by some musicians - e.g., an Album or Gig. This could use extending (see Exercise 1). */
public interface Performance {
	public String getName();

	public Stream<Artist> getMusicians();
}