package com.lambdaherding.edi.axp.ch05;

import java.util.ArrayList;
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

public class GroupingByCollector<T,K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {
	private final Function<T,K> categorizor;

	public GroupingByCollector( Function<T,K> categorizor ) {
		this.categorizor = categorizor;
	}

	@Override
	public BiConsumer<Map<K, List<T>>, T> accumulator() {
		return (m, t) -> m.computeIfAbsent( categorizor.apply( t ), f -> new ArrayList<T>() ).add( t );
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Stream.of( Characteristics.CONCURRENT, Characteristics.UNORDERED, Characteristics.IDENTITY_FINISH )
				.collect( Collectors.toSet() );
	}

	@Override
	public BinaryOperator<Map<K, List<T>>> combiner() {
		return (selfMap, otherMap) -> {
			otherMap.forEach( (key, list) -> {
				selfMap.merge( key, list, (thisList, otherList) -> {
					thisList.addAll( otherList );
					return thisList;
				});
			});

			return selfMap;
		};
	}

	@Override
	public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
		return Function.identity();
	}

	@Override
	public Supplier<Map<K, List<T>>> supplier() {
		return HashMap::new;
	}
}
