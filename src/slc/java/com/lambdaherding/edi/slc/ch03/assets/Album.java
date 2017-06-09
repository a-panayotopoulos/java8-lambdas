package com.lambdaherding.edi.slc.ch03.assets;

import java.util.ArrayList;
import java.util.List;

public class Album {

	private List<String> tracks = new ArrayList<>();

	public Album(List<String> tracks) {
		super();
		this.tracks = tracks;
	}

	/**
	 * @return the tracks
	 */
	public List<String> getTracks() {
		return tracks;
	}

	/**
	 * @param tracks
	 *            the tracks to set
	 */
	public void setTracks(List<String> tracks) {
		this.tracks = tracks;
	}

}
