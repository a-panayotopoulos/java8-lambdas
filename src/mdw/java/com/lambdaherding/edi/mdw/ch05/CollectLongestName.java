package com.lambdaherding.edi.mdw.ch05;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CollectLongestName implements Collector<String, StringCombiner, String> {

@Override
public Supplier<StringCombiner> supplier() {
	return () -> new StringCombiner();
}
@Override
public BiConsumer<StringCombiner, String> accumulator() {
	 return StringCombiner::add;
}
@Override
public BinaryOperator<StringCombiner> combiner() {
	return StringCombiner::merge;
}
@Override
public Function<StringCombiner, String> finisher() {
	return StringCombiner::toString;
}
@Override
public Set<java.util.stream.Collector.Characteristics> characteristics() {
	return EnumSet.of(Characteristics.UNORDERED);
}
}