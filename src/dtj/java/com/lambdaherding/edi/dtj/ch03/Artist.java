package com.lambdaherding.edi.dtj.ch03;

import java.util.stream.Stream;

public class Artist {
	
	private String artist;
	private String name;
	private String origin;
	private Stream<Artist> members;

	public String getArtist() {
		return artist;
	}

	public Artist setArtist(String artist) {
		this.artist = artist;
		return this;
	}

	public String getName() {
		return name;
	}

	public Artist setName(String name) {
		this.name = name;
		return this;
	}

	public String getOrigin() {
		return origin;
	}

	public Artist setOrigin(String origin) {
		this.origin = origin;
		return this;
	}

	public Stream<Artist> getMembers() {
		return members;
	}

	public void setMembers(Stream<Artist> members) {
		this.members = members;
	}

}
