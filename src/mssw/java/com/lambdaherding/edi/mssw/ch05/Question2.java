package com.lambdaherding.edi.mssw.ch05;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO: Javadoc
 */
public final class Question2 {

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
    }
}