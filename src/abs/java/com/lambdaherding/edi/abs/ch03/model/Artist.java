package com.lambdaherding.edi.abs.ch03.model;

import java.util.stream.Stream;

/**
 * Created by E070699 on 5/15/2017.
 */
public class Artist {

	private String name;
	private String place;
	private Stream<Artist> members;

	public Artist(String name, String place) {
		this.name = name;
		this.place = place;
	}

	public String getName() {
		return name;
	}

	public String getPlace() {
		return place;
	}

	public Stream<Artist> getMembers() {
		return members;
	}
}
