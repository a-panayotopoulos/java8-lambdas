package com.lambdaherding.edi.ram.ch03;

public class Track {
	
	private String name;
	private Integer length;
	
	public Track(String name, Integer length) {
		super();
		this.name = name;
		this.length = length;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getLength() {
		return length;
	}
	
	
}
