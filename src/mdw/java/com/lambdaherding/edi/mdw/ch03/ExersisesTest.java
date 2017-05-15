package com.lambdaherding.edi.mdw.ch03;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

public class ExersisesTest {
	
	@Test
	public void testAddUp() {
		Stream<Integer> intStream = Stream.of( 1, 2, 3 );
		assertEquals( 6, Exersices.addUp( intStream ) );
		
		intStream = Stream.of( 1, 2, 3, 4, 7 );
		assertEquals( 17, Exersices.addUp( intStream ) );
	}
	
	@Test
	public void testGetArtistsNameAndOrigin() {
		Artist belafonte = new Artist("Belafonte", "America");
		Artist aqua = new Artist("Aqua", "Danish-Norwegian");
		Artist jumpteam = new Artist("Jump Team", "UK");
		Stream<Artist> artStream = Stream.of( belafonte, aqua, jumpteam );
		List<String> expected = Arrays.asList( new String[] {
				"Belafonte", "America", "Aqua", "Danish-Norwegian", "Jump Team", "UK" } ); 
		assertEquals( expected, Exersices.getArtistsNameAndOrigin( artStream ) );
		
	}
	
	@Test
	public void testGetAlbumWithThreeTracks() {
		Artist pinkFloyd = new Artist("Pink Floyd", "UK");
		Track anotherBrickPartOne = new Track ("Another Brick in the Wall (Part I)", 1 );
		Track numb = new Track ("Comfortably Numb", 1 );
		Track anotherBrickPartTwo = new Track ("Another Brick in the Wall (Part II)", 1 );
		Album theWall = new Album("The Wall", Arrays.asList( anotherBrickPartOne, numb, anotherBrickPartTwo ), Arrays.asList( pinkFloyd ) );
		
		Artist jumpteam = new Artist("Jump Team", "UK");
		Track jumpBack = new Track ("Jump Back", 1 );
		Album theJump = new Album("Jump Team", Arrays.asList( jumpBack ), Arrays.asList( jumpteam ) );
		
		Artist aqua = new Artist("Aqua", "Danish-Norwegian");
		Track barbieGirl = new Track ("Barbie Girl", 1 );
		Track happyBoysAndGirls = new Track ("Happy Boys & Girls", 1 );
		Track myOhMy = new Track ("My Oh My", 1 );
		Track drJones = new Track ("Doctor Jones", 1 );
		Album aquarium = new Album("Jump Team", Arrays.asList( barbieGirl, happyBoysAndGirls, myOhMy, drJones ), Arrays.asList( aqua ) );
		
		Stream<Album> albumStream = Stream.of( theWall, theJump, aquarium );
		Stream<Album> expected = Stream.of( theWall, theJump );
		
		assertTrue(Arrays.deepEquals( expected.toArray(), Exersices.getAlbumWithThreeTracks( albumStream ).toArray() ));
		
	}
	
	@Test
	public void testCountTotalMembers() {
		Artist john = new Artist("John Lennon", "UK");
		Artist ringo = new Artist("Ringo Star", "UK");
		Artist paul = new Artist("Sir Paul Mccarthy", "UK");
		Artist george = new Artist("George Harrison", "UK");
		Artist beatles = new Artist("The Beatles", Arrays.asList( john, paul, ringo, george ), "UK");
		
		Artist peter = new Artist("Peter Gabrial", "UK");
		Artist phil = new Artist("Phil Collins", "UK");
		Artist mike = new Artist("Mike Somebody", "UK");
		Artist genisis = new Artist("The Beatles", Arrays.asList( mike, phil, peter ), "UK");
		
		Stream<Artist> bands = Stream.of( genisis, beatles );
		assertEquals( 7, Exersices.countMembers( bands ) );
	}
	
	@Test
	public void testCountLowercaseLetters() {
		assertEquals( 3, Exersices.countLowercaseLetters( "Test" ) );
		assertEquals( 13, Exersices.countLowercaseLetters( "Test That This Works" ) );
		assertEquals( 0, Exersices.countLowercaseLetters( "TEST" ) );
	}
	
	@Test
	public void testReturnStringWithMostLowercaseLetters() {
		Stream<String> strings = Stream.of( "Testing Test Test Tesssssssssssst", "Test", "testing", "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTEST THIS MY FRIEND!!!!!" );
		assertEquals( Optional.of( "Testing Test Test Tesssssssssssst" ), Exersices.returnStringWithMostLowrcaseLetters( strings ) );
	}

}
