package com.lambdaherding.edi.drjt.ch05;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

public class Ex2 {

	Stream<String> names = Stream.of( "John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe" );

	public String longestName_reduce( Stream<String> names ) {
		return names.reduce( "", ( a, b ) -> a.length() > b.length() ? a : b );
	}

	public String longestName_collect( Stream<String> names ) {
		return names.collect( Collectors.maxBy( Comparator.comparing( String::length ) ) ).get();
	}

	public Map<String, Long> countNames( Stream<String> names ) {
		return names.collect( Collectors.groupingBy( String::toString, Collectors.counting() ) );
	}


	public Map<String, Long> countNamesCustom( Stream<String> names ) {
		return names.collect( new GroupingByWithCollector<>( String::toString, Collectors.counting() ) );
	}

	public static class GroupingBy<T, K> extends GroupingByWithCollector<T, K, List<T>> {

		public GroupingBy( Function<T, K> function ) {
			super( function, Collectors.toList() );
		}

	}

	public static class GroupingByWithCollector<T, K, R> implements Collector<T, Map<K, Stream.Builder<T>>, Map<K, R>> {

		private Function<T, K> function;
		private Collector<T, ?, R> collector;

		public GroupingByWithCollector( Function<T, K> function, Collector<T, ?, R> collector ) {
			this.function = function;
			this.collector = collector;
		}

		@Override
		public Supplier<Map<K, Stream.Builder<T>>> supplier() {
			return HashMap::new;
		}

		@Override
		public BiConsumer<Map<K, Stream.Builder<T>>, T> accumulator() {
			return ( map, value ) -> {
				K key = function.apply( value );
				if ( !map.containsKey( key ) ) {
					map.put( key, Stream.builder() );
				}
				map.get( key ).add( value );
			};
		}

		@Override
		public BinaryOperator<Map<K, Builder<T>>> combiner() {
			return ( mapA, mapB ) -> {
				for ( Entry<K, Stream.Builder<T>> entry : mapB.entrySet() ) {
					mapA.merge( entry.getKey(), entry.getValue(),
					    ( builderX, builderY ) -> {
						    builderY.build().forEach( builderX::accept );
						    return builderX;
					    } );
				}
				return mapA;
			};
		}

		@Override
		public Function<Map<K, Builder<T>>, Map<K, R>> finisher() {
			return ( map ) -> {
				Map<K, R> finalMap = new HashMap<>();
				map.forEach( ( key, value ) -> finalMap.put( key, value.build().collect( collector ) ) );
				return finalMap;
			};
		}

		@Override
		public Set<Characteristics> characteristics() {
			return new HashSet<>();
		}

	}

}
