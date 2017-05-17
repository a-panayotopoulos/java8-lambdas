package com.lambdaherding.edi.ch04;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/** A group can contain individual musicians as members */
public class Group extends Artist {
	public final List<Musician> members = new ArrayList<Musician>();

	public Group( String name ) {
		super( name );
	}

	public Group addMember( Musician member ) {
		members.add( member );
		return this;
	}

	public boolean removeMember( Musician member ) {
		return members.remove( member );
	}

	@Override
	public Stream<? extends Artist> members() {
		return members.stream();
	}
}
