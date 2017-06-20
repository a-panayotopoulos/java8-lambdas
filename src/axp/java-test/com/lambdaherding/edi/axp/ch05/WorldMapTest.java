package com.lambdaherding.edi.axp.ch05;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lambdaherding.edi.ch05.Continent;
import com.lambdaherding.edi.ch05.Country;

/**
 * Did I do that right?
 */
@SuppressWarnings("static-method")
public class WorldMapTest {
	@Test
	public void testMerge() {
		WorldMap map1 = new WorldMap();
		WorldMap map2 = new WorldMap();
		Continent europe = new Continent().name( "Europe" );
		Continent africa = new Continent().name( "Africa" );
		Continent asia = new Continent().name( "Asia" );

		map1.add( new Country().continent( europe ).name( "France" ).capital( "Paris" ) );
		map2.add( new Country().continent( europe ).name( "Italy" ).capital( "Rome" ) );
		map1.add( new Country().continent( africa ).name( "Ethiopia" ).capital( "Addis Ababa" ) );
		map1.add( new Country().continent( africa ).name( "Egypt" ).capital( "Cairo" ) );
		map2.add( new Country().continent( asia ).name( "China" ).capital( "Beijing" ) );
		map2.add( new Country().continent( asia ).name( "Japan" ).capital( "Tokyo" ) );

		map1.mergeDataFrom( map2 );
		assertTrue( "Europe", map1.containsContinent( "Europe" ) );
		assertTrue( "Africa", map1.containsContinent( "Africa" ) );
		assertTrue( "Asia", map1.containsContinent( "Asia" ) );
		assertTrue( "France", map1.contains( "Europe", "France" ) );
		assertTrue( "Italy", map1.contains( "Europe", "Italy" ) );
		assertTrue( "Ethiopia", map1.contains( "Africa", "Ethiopia" ) );
		assertTrue( "Egypt", map1.contains( "Africa", "Egypt" ) );
		assertTrue( "China", map1.contains( "Asia", "China" ) );
		assertTrue( "Japan", map1.contains( "Asia", "Japan" ) );
		assertTrue( "Paris", map1.contains( "France", "Paris" ) );
		assertTrue( "Rome", map1.contains( "Italy", "Rome" ) );
		assertTrue( "Addis Ababa", map1.contains( "Ethiopia", "Addis Ababa" ) );
		assertTrue( "Cairo", map1.contains( "Egypt", "Cairo" ) );
		assertTrue( "Beijing", map1.contains( "China", "Beijing" ) );
		assertTrue( "Tokyo", map1.contains( "Japan", "Tokyo" ) );
	}
}
