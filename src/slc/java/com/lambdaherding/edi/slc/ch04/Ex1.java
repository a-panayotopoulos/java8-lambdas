package com.lambdaherding.edi.slc.ch04;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lambdaherding.edi.ch04.Artist;
import com.lambdaherding.edi.ch04.Performance;
import com.lambdaherding.edi.ch04.SampleData;

public class Ex1 implements Performance {

	@Override
	public String getName() {
		return "The best performance...";
	}

	@Override
	public Stream<Artist> getMusicians() {
		return SampleData.threeArtists();
	}

	public Stream<Artist> getAllMusicians() {
		return getMusicians().flatMap(a -> Stream.concat(Stream.of(a), a.getMembers())).distinct();
	}


	public static void main(String[] args) {
		Ex1 ex1 = new Ex1();
		System.out.println(ex1.getName());
		System.out.println(ex1.getMusicians().map(a -> a.getName()).collect(Collectors.joining(", ")));
		System.out.println(ex1.getAllMusicians().map(a -> a.getName()).collect(Collectors.joining(", ")));
	}
}
