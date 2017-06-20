package com.lambdaherding.edi.ch07;

import java.awt.Point;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This object represents a collection of shapes, stored as a list of points. The shapes can be transformed
 * in a few different ways.
 * 
 * TODO: the code is very copy-and-paste, and in need of a refactor
 */
public class GridShapes {
	private List<List<Point>> shapes;

	/**
	 * Create a new collection of shapes
	 * @param shapes some collection of shapes, defined as a list of points
	 */
	public GridShapes( List<List<Point>> shapes ) {
		this.shapes = shapes;
	}

	/**
	 * Transform all shapes within this object
	 * 
	 * @param xFactor scale by which to expand shapes away from the origin on the x-axis. May be fractional.
	 * @param yFactor scale by which to expand shapes away from the origin on the y-axis. May be fractional.
	 * @param offset translation to apply *after* the scaling
	 * @return shapes after the transform
	 */
	public GridShapes transform( float xFactor, float yFactor, Point offset ) {
		return new GridShapes( shapes.stream()
				.map( s -> s.stream()
						.map( p -> new Point(
								(int) ( p.x * xFactor ) + offset.x,
								(int) ( p.y * yFactor ) + offset.y ) )
						.collect( Collectors.toList() ) )
				.collect( Collectors.toList() ) );
	}

	/**
	 * Reflect all shapes around some vertical mirror line
	 * 
	 * @param offset line about which to reflect the shapes
	 * @return shapes after reflection
	 */
	public GridShapes reflectX( int offset ) {
		return new GridShapes( shapes.stream()
				.map( s -> s.stream()
						.map( p -> new Point( offset * 2 - p.x, p.y ) )
						.collect( Collectors.toList() ) )
				.collect( Collectors.toList() ) );
	}

	/**
	 * Reflect all shapes around some horizontal mirror line
	 * 
	 * @param offset line about which to reflect the shapes
	 * @return shapes after reflection
	 */
	public GridShapes reflectY( int offset ) {
		return new GridShapes( shapes.stream()
				.map( s -> s.stream()
						.map( p -> new Point( p.x, offset * 2 - p.y ) )
						.collect( Collectors.toList() ) )
				.collect( Collectors.toList() ) );
	}

	/**
	 * Get all the shapes contained by this object
	 * @return
	 */
	public List<List<Point>> getShapes() {
		return shapes;
	}
}
