package com.lambdaherding.edi.abs.ch03.model;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by E070699 on 5/15/2017.
 */
public class Album {

	public String getName() {
		return name;
	}

	private String name;

	public Album(String name, List<Track> tracks) {
		this.name = name;
		this.tracks = tracks;
	}

	private List<Track> tracks;

	public List<Track> getTracks() {
		return tracks;
	}
}
