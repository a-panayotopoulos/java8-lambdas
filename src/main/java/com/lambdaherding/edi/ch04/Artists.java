package com.lambdaherding.edi.ch04;

import java.util.List;

public class Artists {

    private List<Artist> artists;

    public Artists(List<Artist> artists) {
        this.artists = artists;
    }

    public Artist getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            indexException(index);
        }
        return artists.get(index);
    }

    private static void indexException(int index) {
        throw new IllegalArgumentException(index + 
                                           " doesn't correspond to an Artist");
    }

    public String getArtistName(int index) {
        try {
            Artist artist = getArtist(index);
            return artist.getName();
        } catch (IllegalArgumentException e) {
            return "unknown";
        }
    }

	public List<Artist> getArtists() {
		return artists;
	}
}
