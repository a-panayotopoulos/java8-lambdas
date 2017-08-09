package com.lambdaherding.edi.axp.ch08.factory.nolambda;

import java.awt.Point;

import com.lambdaherding.edi.axp.ch08.factory.common.shape.Circle;
import com.lambdaherding.edi.axp.ch08.factory.common.shape.Shape;

/**
 * Create a circle of a random colour and varying radius
 */
public class CircleFactory extends AbstractShapeFactory {
	private final int circleRadius;

	public CircleFactory( int circleRadius ) {
		this.circleRadius = circleRadius;
	}

	@Override
	public Shape create( Point centre ) {
		return new Circle( centre, randColour(), varySize( this.circleRadius ) );
	}
}
