package com.lambdaherding.edi.axp.ch08.factory.lambda;

import java.awt.Color;
import java.util.Random;

/**
 * Utilities for shapes
 */
public interface ShapeUtil {
	Random rand = new Random();

	/** Random colour of the rainbow */
	static Color randColour() {
		return randColour( 1f );
	}

	/** Random colour of the rainbow, custom darkness */
	static Color randColour( float value ) {
		float hue = rand.nextFloat();
		return Color.getHSBColor( hue, 1f, value );
	}

	/** Random rotation in radians */
	static double randRotation() {
		return rand.nextDouble() * 2 * Math.PI; // Two pie?
	}

	/** Randomly scale a base size to between 75% to 175% */
	static int varySize( int baseSize ) {
		float multiplier = 0.75f + rand.nextFloat();
		return (int) ( baseSize * multiplier );
	}
}
