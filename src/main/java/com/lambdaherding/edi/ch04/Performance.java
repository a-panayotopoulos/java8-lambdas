package com.lambdaherding.edi.ch04;

import java.util.stream.Stream;

public interface Performance {

    public String getName();

    public Stream<Artist> getMusicians();

    // TODO: create getAllMusicians()
}
