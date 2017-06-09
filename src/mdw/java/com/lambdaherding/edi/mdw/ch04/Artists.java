package com.lambdaherding.edi.mdw.ch04;

import java.util.List;
import java.util.Optional;

import com.lambdaherding.edi.ch04.Artist;

public class Artists {

  private List<Artist> artists;

  public Artists(List<Artist> artists) {
      this.artists = artists;
  }

  public Optional<Artist> getArtist(int index) {
      if (index < 0 || index >= artists.size()) {
          indexException(index);
      }
      return Optional.of( artists.get(index));
  }

  private void indexException(int index) {
      throw new IllegalArgumentException(index +
                                         " doesn't correspond to an Artist");
  }

  public String getArtistName(int index) {
          Optional<Artist> artist = getArtist(index);
          if (artist.isPresent() ) {
          return artist.get().getName();
          } else {
          	return "unknown";
          }
  }

}