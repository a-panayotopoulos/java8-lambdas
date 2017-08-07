package com.lambdaherding.edi.axp.ch08.factory.nolambda;

import java.awt.Point;

import com.lambdaherding.edi.axp.ch08.factory.common.shape.Shape;
import com.lambdaherding.edi.axp.ch08.factory.common.shape.Square;

/**
 * Create a square of random colour and varying edge length
 */
public class SquareFactory extends AbstractShapeFactory {
	private final int squareEdge;

	public SquareFactory( int squareEdge ) {
		this.squareEdge = squareEdge;
	}

	@Override
	public Shape create( Point centre ) {
		return new Square( centre, randColour(), varySize( this.squareEdge ) );
	}
}
