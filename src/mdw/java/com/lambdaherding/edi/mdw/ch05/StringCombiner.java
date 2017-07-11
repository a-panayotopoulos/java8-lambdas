package com.lambdaherding.edi.mdw.ch05;


public class StringCombiner {
	
	String builder = new String("");
	
	public StringCombiner merge(StringCombiner other) {
    add(other.builder);
    return this;
}
	public StringCombiner add(String element) {
    if (builder.length() < element.length()) {
        builder = element;
    }
    return this;
}

	public String toString() {
		return builder;
	}
	
}
