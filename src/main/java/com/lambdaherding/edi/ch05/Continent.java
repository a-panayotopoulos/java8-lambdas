package com.lambdaherding.edi.ch05;

/**
 * A continent of the world.
 */
public class Continent {
	private String name;

	public String name() {
		return name;
	}

	public Continent name(String name) {
		this.name = name;
		return this;
	}

	public String toString() {
		return "Continent:" + name;
	}
}
