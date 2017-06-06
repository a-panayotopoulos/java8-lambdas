package com.lambdaherding.edi.mssw.ch03;

import java.util.stream.Stream;

import com.lambdaherding.edi.mssw.shared.Artist;

public class Main {

	public static void main(String... args) {

		System.out.println( "addUp: " + Question1.addUp(Stream.of(1, 2, 3, 4, 5)));
		
		System.out.println( "artists" + Question1.artistOrigins(
				Stream.of(
						new Artist("Zula Temesgen", "Switzerland"),
						new Artist( "Ka'Cho Kular", "Denmark"),
						new Artist( "Фекла Ермакова", "Uruguay"),
						new Artist( "Freddie Hunter", "Australia"),
						new Artist( "Chidubem Akabueze", "Greenland"),
						new Artist( "Denice Lund", "Norway"),
						new Artist( "Jadwiga Jaworska", "Spain"),
						new Artist( "red", "41.395828, -72.978627")
						)
				)
		);
	}
}
