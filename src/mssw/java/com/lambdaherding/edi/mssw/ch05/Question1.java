package com.lambdaherding.edi.mssw.ch05;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Created by E047027 on 13/06/2017.
 */
public class Question1 {

    @Test
    public void mapToUpperCase(){
        List<String> collected = Stream.of("a", "b", "hello")
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        assertEquals(Arrays.asList("A", "B", "HELLO"), collected);
    }




}
