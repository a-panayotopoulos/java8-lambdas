package com.lambdaherding.edi.ch04;

import java.util.stream.Stream;

/** An artist could be a group or an individual musician */
public abstract class Artist {
	private String name;

	public Artist( String name ) {
		setName( name );
	}

	@SuppressWarnings("static-method")
	public Stream<? extends Artist> members() {
		return Stream.empty();
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}
}
