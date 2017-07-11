package com.lambdaherding.edi.red.ch05;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lambdaherding.edi.ch05.Country;
import com.lambdaherding.edi.ch05.WorldMap;

public class WorldMapCollector implements Collector<Country, WorldMap, WorldMap>{

	@Override
	public Supplier<WorldMap> supplier() {
		return WorldMap::new;
	}

	@Override
	public BiConsumer<WorldMap, Country> accumulator() {
		return WorldMap::add;
	}

	@Override
	public BinaryOperator<WorldMap> combiner() {
		return (a, b) -> {
			a.mergeDataFrom(b);
			return a;
		};
	}

	@Override
	public Function<WorldMap, WorldMap> finisher() {
		return Function.identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Stream.of( Characteristics.IDENTITY_FINISH,
			Characteristics.UNORDERED ).collect( Collectors.toSet() );
	}
}
