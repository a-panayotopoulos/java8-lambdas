package com.lambdaherding.edi.mssw.ch04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.lambdaherding.edi.ch04.Artist;

public class PerformanceImplTest {
	private PerformanceImpl performance = new PerformanceImpl(Arrays.asList(
			new Artist("Liisa Riutta","UK" ),
			new Artist("Darijo Živković", "Brazil"),
			new Artist("Zbyněk Svoboda", "Spain"),
			new Artist("Gerth Davidsen", "Portugal"),
			new Artist( "The Bunch", Arrays.asList(
					new Artist("洪邕", "Estonia"),
					new Artist("Emilian Salas Carrillo", "Germany"),
					new Artist("Catherine Kazakova","Austria"),
					new Artist("Wisław Sobczak", "Poland"),
					new Artist("Alen Axelsson", "South Africa"),
					new Artist("Donglu Yeh", "Greenland"),
					new Artist("Traful Concepción Mejía", "Slovenia"),
					new Artist("Bura Simparri", "Austria"),
					new Artist("Melampus Roper", "Italy")), "Nowhere"
					)
			));
	
	@Test
	public void testGetAllMusicians(){
		List<String> allMusicians = performance.getAllMusicians().collect(Collectors.toList());
		Assert.assertTrue("Name not in collection...", allMusicians.contains("Liisa Riutta"));
		Assert.assertTrue("Name not in collection...", allMusicians.contains("Darijo Živković"));
		Assert.assertTrue("Name not in collection...", allMusicians.contains("Zbyněk Svoboda"));
		Assert.assertTrue("Name not in collection...", allMusicians.contains("Gerth Davidsen"));
		Assert.assertTrue("Name not in collection...", allMusicians.contains("The Bunch"));
		Assert.assertTrue("Name not in collection...", allMusicians.contains("洪邕"));
		Assert.assertTrue("Name not in collection...", allMusicians.contains("Emilian Salas Carrillo"));
		Assert.assertTrue("Name not in collection...", allMusicians.contains("Catherine Kazakova"));
		Assert.assertTrue("Name not in collection...", allMusicians.contains("Wisław Sobczak"));
		Assert.assertTrue("Name not in collection...", allMusicians.contains("Alen Axelsson"));
		Assert.assertTrue("Name not in collection...", allMusicians.contains("Donglu Yeh"));
		Assert.assertTrue("Name not in collection...", allMusicians.contains("Traful Concepción Mejía"));
		Assert.assertTrue("Name not in collection...", allMusicians.contains("Bura Simparri"));
		Assert.assertTrue("Name not in collection...", allMusicians.contains("Melampus Roper"));
		
		System.out.println("All musicians:\n\t" + allMusicians );
		
		
	}
}
