package com.lambdaherding.edi.dtj.ch05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Ex02 {

	private static final Map<Integer, Long> fibCache = new HashMap<>();
	static {
		fibCache.put(0, 0L);
		fibCache.put(1, 1L);
	}

	public static void main( String[] args ) {
		String longestName = findLongestArtistName( Stream.of("John Lennon", "Paul McCartney",
    "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe") );

		System.out.println( "Quesation 1: Longest name is : " + longestName);

		String wordCounts = countWords(Stream.of("John", "Paul", "George", "John",
                "Paul", "John")).toString();

		System.out.println( "Question 2: word counts: " + wordCounts);

		System.out.println( "Fib 1: " + fibonacci(1) );
		System.out.println( "Fib 3: " + fibonacci(3) );
		System.out.println( "Fib 5: " + fibonacci(5) );
		System.out.println( "Fib 20: " + fibonacci(20) );
		System.out.println( "Fib 40: " + fibonacci(40) );
		System.out.println( "Fib 80: " + fibonacci(80) );

	}

	public static String findLongestArtistName( Stream<String> artists ) {
		return artists
				.collect( Collectors.maxBy(Comparator.comparing(String::length) ) ).get();

	}

	public static Map<Object, Long> countWords( Stream<String> words ) {
		return words
				.collect(Collectors.groupingBy(s -> s, Collectors.counting()));
	}

	public static Long fibonacci( Integer value ) {
		return fibCache.computeIfAbsent(value, n -> fibonacci(n-1) + fibonacci(n-2));
	}

	public class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {

		private Function<T, K> classifier;

		public GroupingBy(Function<T, K> classifier) {
			this.classifier = classifier;
		}

		@Override
		public BiConsumer<Map<K, List<T>>, T> accumulator() {
			return ( map, entry ) -> {
	            K key = classifier.apply(entry);
	            List<T> elements = map.computeIfAbsent(key, k -> new ArrayList<>());
	            elements.add(entry);
			};
		}

		@Override
		public Set<java.util.stream.Collector.Characteristics> characteristics() {
			// TODO Not so sure about this bit
			return null;
		}

		@Override
		public BinaryOperator<Map<K, List<T>>> combiner() {

			// TODO this is wrong
			return ( m1, m2 ) -> {
				m1.putAll(m2);
				return m1;
			};
		}

		@Override
		public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
			return map -> map;
		}

		@Override
		public Supplier<Map<K, List<T>>> supplier() {
			return HashMap::new;
		}

	}


}
