package com.lambdaherding.edi.ch05;

/**
 * A country, with continent and capital.
 */
public class Country {
	private String name;
	private String capital;
	private Continent continent;

	public String name() {
		return name;
	}

	public Country name(String name) {
		this.name = name;
		return this;
	}

	public Continent continent() {
		return continent;
	}

	public Country continent(Continent continent) {
		this.continent = continent;
		return this;
	}

	public String capital() {
		return capital;
	}

	public Country capital(String capital) {
		this.capital = capital;
		return this;
	}

	public String toString() {
		return "Country:" + name;
	}
}
