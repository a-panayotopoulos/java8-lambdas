package com.lambdaherding.edi.red.ch03;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * For the Chapter 3 advanced exercises.
 *
 * @param <T> element type
 */
public class StreamDecorator<T> {
	private final Stream<T> stream;

	public StreamDecorator( Stream<T> stream ) {
		this.stream = stream;
	}

	// 1. Write an implementation of the Stream function map using only reduce
	// and lambda expressions. You can return a List instead of a Stream if
	// you want.
	public <R> StreamDecorator<R> map( Function<T, R> mapper ) {
		return new StreamDecorator<>( stream.reduce( Stream.<R>empty(),
			(a, b) -> Stream.concat(a, Stream.of(mapper.apply(b))),
			Stream::concat ));
	}

	// 2. Write an implementation of the Stream function filter using only
	// reduce and lambda expressions. Again, you can return a List instead
	// of a Stream if you want.
	public StreamDecorator<T> filter( Predicate<T> predicate ) {
		return new StreamDecorator<>( stream.reduce( Stream.<T>empty(),
			(a, b) -> predicate.test( b ) ? Stream.concat( a, Stream.of(b) ) : a,
			Stream::concat ));
	}

	public void each( Consumer<T> consumer ) {
		stream.forEach( consumer );
	}

	public List<T> asList() {
		return stream.collect( Collectors.toList() );
	}
}
