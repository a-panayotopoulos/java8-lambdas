package com.lambdaherding.edi.mssw.ch05;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO: Javadoc
 */
public final class Question2 {

    @Test
    public void longestNamedArtist(){
        Stream<String> names = Stream.of("John Lennon", "Paul McCartney",
                "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");

        String longestName = names.collect(Collectors.reducing("", (acc, element) -> {
            if (element.length() > acc.length()) {
                return element;
            }
            return acc;
        }));

        System.out.println("Longest name: " + longestName);
        Assert.assertEquals("Stuart Sutcliffe", longestName);
    }

    @Test
    public void numberOfOccurrences() {
        Stream<String> names = Stream.of("John", "Paul", "George", "John", "Paul", "John");

        Map<String, Integer> collect = names.collect(
                Collectors.groupingBy((name -> name),
                        Collectors.reducing(0, (name -> 1), (a, b) -> a + b)));

        System.out.println("Number of occurrences of names: " + collect);
        Assert.assertEquals(1, collect.get("George").longValue());
        Assert.assertEquals(3, collect.get("John").longValue());
        Assert.assertEquals(2, collect.get("Paul").longValue());
    }
}