package com.lambdaherding.edi.axp.ch05;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import com.lambdaherding.edi.ch05.Continent;
import com.lambdaherding.edi.ch05.Countries;
import com.lambdaherding.edi.ch05.Country;

public class GroupingByCollectorTest {
	GroupingByCollector<Country,String> collector;

	@Before
	public void setup() {
		collector = new GroupingByCollector<>( c -> c.continent().name() );
	}

	@Test
	public void testInUse() {
		Map<String,List<Country>> grouped = Countries.all().collect( collector );

		assertThat( "Got result", grouped, notNullValue() );
		assertThat( "Africa", grouped.get( "Africa" ), allOf( listWithLength( 54 ), containsCountry( "Egypt" ) ) );
		assertThat( "Asia", grouped.get( "Asia" ), allOf( listWithLength( 47 ), containsCountry( "Malaysia" ) ) );
		assertThat( "Europe", grouped.get( "Europe" ), allOf( listWithLength( 45 ), containsCountry( "Vatican City" ) ) );
		assertThat( "North America", grouped.get( "North America" ), allOf( listWithLength( 26 ), containsCountry( "Cuba" ) ) );
		assertThat( "South America", grouped.get( "South America" ), allOf( listWithLength( 13 ), containsCountry( "Paraguay" ) ) );
		assertThat( "Oceania", grouped.get( "Oceania" ), allOf( listWithLength( 14 ), containsCountry( "Fiji" ) ) );
	}

	@Test
	public void testMerge() {
		Continent europe = new Continent().name( "Europe" );
		Continent africa = new Continent().name( "Africa" );
		Continent asia = new Continent().name( "Asia" );

		Map<String,List<Country>> map1 = collector.supplier().get();
		Map<String,List<Country>> map2 = collector.supplier().get();

		BiConsumer<Map<String, List<Country>>, Country> accumulator = collector.accumulator();
		accumulator.accept( map1, new Country().continent( europe ).name( "France" ).capital( "Paris" ) );
		accumulator.accept( map2, new Country().continent( europe ).name( "Italy" ).capital( "Rome" ) );
		accumulator.accept( map1, new Country().continent( africa ).name( "Ethiopia" ).capital( "Addis Ababa" ) );
		accumulator.accept( map1, new Country().continent( africa ).name( "Egypt" ).capital( "Cairo" ) );
		accumulator.accept( map2, new Country().continent( asia ).name( "China" ).capital( "Beijing" ) );
		accumulator.accept( map2, new Country().continent( asia ).name( "Japan" ).capital( "Tokyo" ) );

		Map<String,List<Country>> map = collector.combiner().apply( map1, map2 );
		assertThat( "Europe", map.get( "Europe" ), allOf( listWithLength( 2 ), containsCountry( "France" ), containsCountry( "Italy" ) ) );
		assertThat( "Africa", map.get( "Africa" ), allOf( listWithLength( 2 ), containsCountry( "Ethiopia" ), containsCountry( "Egypt" ) ) );
		assertThat( "Asia", map.get( "Asia" ), allOf( listWithLength( 2 ), containsCountry( "China" ), containsCountry( "Japan" ) ) );
	}

	private static Matcher<List<Country>> containsCountry( String countryName ) {
		return new BaseMatcher<List<Country>>() {
			@Override
			public boolean matches( Object item ) {
				return Optional.ofNullable( item )
						.filter( List.class::isInstance )
						.map( o -> (List<?>) o )
						.filter( l -> !l.isEmpty() )
						.orElse( Collections.emptyList() )
						.stream()
						.filter( Country.class::isInstance )
						.map( Country.class::cast )
						.filter( c -> countryName.equals( c.name() ) )
						.findAny()
						.isPresent();
			}

			@Override
			public void describeTo( Description description ) {
				description.appendText( "List of countries including " ).appendValue( countryName );
			}
		};
	}

	private static Matcher<List<?>> listWithLength( int expectedLength ) {
		return new BaseMatcher<List<?>>() {
			@Override
			public boolean matches( Object item ) {
				return Optional.ofNullable( item )
					.filter( List.class::isInstance )
					.map( List.class::cast )
					.map( l -> l.size() == expectedLength )
					.orElse( false );
			}

			@Override
			public void describeTo( Description description ) {
				description.appendText( "List of length" ).appendValue( expectedLength );
			}
		};
	}
}
