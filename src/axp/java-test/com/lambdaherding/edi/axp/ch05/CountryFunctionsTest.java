package com.lambdaherding.edi.axp.ch05;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Function;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import com.lambdaherding.edi.ch05.Continent;
import com.lambdaherding.edi.ch05.Countries;
import com.lambdaherding.edi.ch05.Country;

@SuppressWarnings("static-method")
public class CountryFunctionsTest {
	@Test
	public void testCapitals() {
		Map<String,String> mapping = CountryFunctions.capitals( Countries.all() );
		assertThat( "Returned result", mapping, notNullValue() );
		assertThat( "Number of results", mapping.size(), is( (int) Countries.all().count() ) );
		assertThat( "Algeria", mapping.get( "Algiers" ), is( "Algeria" ) );
		assertThat( "Macedonia", mapping.get( "Skopje" ), is( "Macedonia" ) );
		assertThat( "Tuvalu", mapping.get( "Funafuti" ), is( "Tuvalu" ) );
	}

	@Test
	public void testContinentWithMostCountries() {
		Continent cont = CountryFunctions.continentWithMostCountries( Countries.all() );
		assertThat( "Correct continent", cont, isContinentWithName( "Africa" ) );
	}

	@Test
	public void testHowManyMoreAContinentCountries() {
		long diff = CountryFunctions.howManyMoreAContinentCountries( Countries.all() );
		assertThat( "Difference", diff, is( 3L ) );
	}

	@Test
	public void testWriteToProperties() {
		Properties props = new Properties();
		CountryFunctions.writeToProperties( Countries.all(), props );

		assertTrue( props.containsKey( "Africa" ) );
		assertTrue( props.containsKey( "Asia" ) );
		assertTrue( props.containsKey( "Europe" ) );
		assertTrue( props.containsKey( "North America" ) );
		assertTrue( props.containsKey( "South America" ) );
		assertThat( props.getProperty( "Oceania" ), is(
				"Australia:New Zealand:Fiji:Papua New Guinea:Solomon Islands:Vanuatu:"
				+ "Kiribati:Marshall Islands:Micronesia:Nauru:Palau:Samoa:Tonga:Tuvalu" ) );
	}

	@Test
	public void testWriteToPropertiesSorted() {
		Properties props = new Properties();
		CountryFunctions.writeToPropertiesSorted( Countries.all(), props );

		assertTrue( props.containsKey( "Africa" ) );
		assertTrue( props.containsKey( "Asia" ) );
		assertTrue( props.containsKey( "Europe" ) );
		assertTrue( props.containsKey( "North America" ) );
		assertTrue( props.containsKey( "South America" ) );
		assertThat( props.getProperty( "Oceania" ), is(
				"Australia:Fiji:Kiribati:Marshall Islands:Micronesia:Nauru:New Zealand:"
				+ "Palau:Papua New Guinea:Samoa:Solomon Islands:Tonga:Tuvalu:Vanuatu" ) );
	}

	@Test
	public void testFirstAlphabeticallyForEachContinent() {
		Map<String,Country> mapping = CountryFunctions.firstAlphabeticallyForEachContinent( Countries.all() );
		assertThat( "Returned result", mapping, notNullValue() );
		assertThat( "Africa",        mapping.get( "Africa" ),        isCountryWithName( "Algeria" ) );
		assertThat( "Asia",          mapping.get( "Asia" ),          isCountryWithName( "Afghanistan" ) );
		assertThat( "Europe",        mapping.get( "Europe" ),        isCountryWithName( "Albania" ) );
		assertThat( "North America", mapping.get( "North America" ), isCountryWithName( "Antigua and Barbuda" ) );
		assertThat( "South America", mapping.get( "South America" ), isCountryWithName( "Argentina" ) );
		assertThat( "Oceania",       mapping.get( "Oceania" ),       isCountryWithName( "Australia" ) );
	}

	@Test
	public void testBuildWorldMap() {
		WorldMap map = CountryFunctions.buildWorldMap( Countries.all() );
		assertThat( "Returned result", map, notNullValue() );
		assertTrue( "Oceania", map.containsContinent( "Oceania" ) );
		assertTrue( "Kiribati", map.contains( "Oceania", "Kiribati" ) );
		assertTrue( "South Tarawa", map.contains( "Kiribati", "South Tarawa" ) );
	}

	@Test
	public void testBuildWorldMapReduce() {
		WorldMap map = CountryFunctions.buildWorldMapReduce( Countries.all() );
		assertThat( "Returned result", map, notNullValue() );
		assertTrue( "Oceania", map.containsContinent( "Oceania" ) );
		assertTrue( "Kiribati", map.contains( "Oceania", "Kiribati" ) );
		assertTrue( "South Tarawa", map.contains( "Kiribati", "South Tarawa" ) );
	}

	private static Matcher<Continent> isContinentWithName( String name ) {
		return isTypeWithName( name, Continent.class, Continent::name );
	}

	private static Matcher<Country> isCountryWithName( String name ) {
		return isTypeWithName( name, Country.class, Country::name );
	}

	private static <T> Matcher<T> isTypeWithName( String name, Class<T> klass, Function<T,String> nameGetter ) {
		return new BaseMatcher<T>() {
			@Override
			public boolean matches( Object item ) {
				return Optional.ofNullable( item )
					.filter( klass::isInstance )
					.map( klass::cast )
					.map( nameGetter )
					.map( n -> n.equals( name ) )
					.orElse( false );
			}

			@Override
			public void describeTo( Description description ) {
				description.appendText( klass.getSimpleName() ).appendText( " with name " ).appendValue( name );
			}
		};
	}
}
