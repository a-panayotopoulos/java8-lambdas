package com.lamdaherding.edi.ram.ch03;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Artist {
	private String name;
	private String placeOfOrigin;
	private List<Artist> members;
	
	public Artist(String name, String placeOfOrigin, List<Artist> members) {
		super();
		this.name = name;
		this.placeOfOrigin = placeOfOrigin;
		this.members = members;
	}

	public Artist(String name, String placeOfOrigin) {
		super();
		this.name = name;
		this.placeOfOrigin = placeOfOrigin;
		members = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public String getPlaceOfOrigin() {
		return placeOfOrigin;
	}

	public Stream<Artist> getMembers() {
		return members.stream();
	} 
	
	public void addMember(Artist member) {
		members.add(member);
	}
}
