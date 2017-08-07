package com.lambdaherding.edi.axp.ch08.factory.common;

import java.awt.Point;

import com.lambdaherding.edi.axp.ch08.factory.common.shape.Shape;

/**
 * The base factory interface. This is a Method Factory pattern with the method in question being {@link #create(Point)}.
 */
public interface ShapeFactory {
	/**
	 * Abstract method that all concrete implementations must implement
	 * 
	 * @param centre the centre of the shape, i.e. where the user clicked
	 * @return a new {@link Shape} around that centre. Factors such as size and colour may vary.
	 */
	public abstract Shape create( Point centre );
}
