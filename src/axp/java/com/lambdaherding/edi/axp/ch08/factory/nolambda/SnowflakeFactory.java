package com.lambdaherding.edi.axp.ch08.factory.nolambda;

import java.awt.Point;

import com.lambdaherding.edi.axp.ch08.factory.common.shape.Shape;
import com.lambdaherding.edi.axp.ch08.factory.common.shape.Snowflake;

/**
 * Create a "snowflake" (actually just a six-pointed cross) of random colour, rotation, and varying arm length
 */
public class SnowflakeFactory extends AbstractShapeFactory {
	private final int armLength;

	public SnowflakeFactory( int armLength ) {
		this.armLength = armLength;
	}

	@Override
	public Shape create( Point centre ) {
		return new Snowflake( centre, randColour( 0.8f ), randRotation(), varySize( this.armLength ) );
	}
}
