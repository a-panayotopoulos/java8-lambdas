package com.lambdaherding.edi.red.ch05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {

	private final Function<T, K> grouper;

	public GroupingBy( Function<T, K> grouper ) {
		this.grouper = grouper;
	}

	@Override
	public Supplier<Map<K, List<T>>> supplier() {
		return HashMap<K, List<T>>::new;
	}

	@Override
	public BiConsumer<Map<K, List<T>>, T> accumulator() {
		return (accum, item) -> accum
			.computeIfAbsent( grouper.apply( item ), k -> new ArrayList<T>() )
			.add( item );
	}

	@Override
	public BinaryOperator<Map<K, List<T>>> combiner() {
		return (a, b) -> {
			b.forEach( (key, ls) -> a.merge( key, ls, (oldLs, newLs) -> {
				oldLs.addAll( newLs );
				return oldLs;
			} ));
			return a;
		};
	}

	@Override
	public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
		return t -> t;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return new HashSet<>( Arrays.asList(
			Characteristics.IDENTITY_FINISH,
			Characteristics.UNORDERED ) );
	}

}
