package com.lambdaherding.edi.mdw.ch05;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CountNameApperences implements Collector<String, MapCombiner, Map<String, Integer>> {

	@Override
	public Supplier<MapCombiner> supplier() {
		return () -> new MapCombiner();
	}

	@Override
	public BiConsumer<MapCombiner, String> accumulator() {
		return MapCombiner::add;
	}

	@Override
	public BinaryOperator<MapCombiner> combiner() {
		return MapCombiner::merge;
	}

	@Override
	public Function<MapCombiner, Map<String, Integer>> finisher() {
		return MapCombiner::toMap;
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return EnumSet.of(Characteristics.UNORDERED);
	}

}
