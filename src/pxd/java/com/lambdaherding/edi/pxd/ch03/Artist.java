package com.lambdaherding.edi.pxd.ch03;

import java.util.Collections;
import java.util.List;

public class Artist {

	private String name;

	private String placeOfOrigin;

	private List<String> members;

	public Artist( String name, String place ) {
		this.name = name;
		this.placeOfOrigin = place;
		this.members = Collections.emptyList();
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getPlaceOfOrigin() {
		return placeOfOrigin;
	}

	public void setPlaceOfOrigin( String placeOfOrigin ) {
		this.placeOfOrigin = placeOfOrigin;
	}

	public List<String> getMembers() {
		return members;
	}

	public void setMembers( List<String> members ) {
		this.members = members;
	}
}
