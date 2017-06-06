package com.lambdaherding.edi.mdw.ch04;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Test;
import com.lambdaherding.edi.ch04.Artist;

public class PerformanceTest {

	@Test
	public void testgetAllMusicians() {
	Artist john = new Artist("John Lennon", "UK");
	Artist ringo = new Artist("Ringo Star", "UK");
	Artist paul = new Artist("Sir Paul Mccarthy", "UK");
	Artist george = new Artist("George Harrison", "UK");
	Artist beatles = new Artist("The Beatles", Arrays.asList( john, paul, ringo, george ), "UK");
	
	Artist peter = new Artist("Peter Gabrial", "UK");
	Artist phil = new Artist("Phil Collins", "UK");
	Artist mike = new Artist("Mike Somebody", "UK");
	Artist genisis = new Artist("Genisis", Arrays.asList( mike, phil, peter ), "UK");
	
	Stream<Artist> bands = Stream.of( genisis, beatles );
	Stream<Artist> expected = Stream.of(  mike, phil, peter, genisis, john, paul, ringo, george, beatles );
	Show rockShow = new Show();
	rockShow.setMusicians( bands );
	assertTrue(Arrays.deepEquals( expected.toArray(), rockShow.getAllMusicians().toArray() ));

}
}
