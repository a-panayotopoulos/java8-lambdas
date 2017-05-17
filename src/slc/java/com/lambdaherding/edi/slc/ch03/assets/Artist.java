package com.lambdaherding.edi.slc.ch03.assets;

import java.util.stream.Stream;

public class Artist {
	private String name;
	private String placeOfOrigin;
	private Stream<Artist> members;

	public Artist(String name) {
		this.name = name;
	}

	public Artist(String name, String placeOfOrigin) {
		super();
		this.name = name;
		this.placeOfOrigin = placeOfOrigin;
	}

	public Artist(String name, Stream<Artist> members) {
		super();
		this.name = name;
		this.members = members;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the placeOfOrigin
	 */
	public String getPlaceOfOrigin() {
		return placeOfOrigin;
	}

	/**
	 * @param placeOfOrigin
	 *            the placeOfOrigin to set
	 */
	public void setPlaceOfOrigin(String placeOfOrigin) {
		this.placeOfOrigin = placeOfOrigin;
	}

	/**
	 * @return the members
	 */
	public Stream<Artist> getMembers() {
		return members;
	}

	/**
	 * @param members
	 *            the members to set
	 */
	public void setMembers(Stream<Artist> members) {
		this.members = members;
	}

}