package com.lambdaherding.edi.axp.ch08.factory.lambda;

import static com.lambdaherding.edi.axp.ch08.factory.lambda.ShapeUtil.randColour;
import static com.lambdaherding.edi.axp.ch08.factory.lambda.ShapeUtil.randRotation;
import static com.lambdaherding.edi.axp.ch08.factory.lambda.ShapeUtil.varySize;

import com.lambdaherding.edi.axp.ch08.factory.common.controller.Stamper;
import com.lambdaherding.edi.axp.ch08.factory.common.shape.Circle;
import com.lambdaherding.edi.axp.ch08.factory.common.shape.Snowflake;
import com.lambdaherding.edi.axp.ch08.factory.common.shape.Square;
import com.lambdaherding.edi.axp.ch08.factory.common.ui.StamperFrame;

/**
 * Create a stamper controller, its factories, and UI.
 */
public class RunStamperLambdas {
	private static final int CIRCLE_RADIUS = 15;
	private static final int SQUARE_EDGE = 26;
	private static final int SNOWFLAKE_ARM_LENGTH = 18;

	public static void main( String[] args ) {
		Stamper stamper = new Stamper();

		stamper.addShape( "circle", ctr -> new Circle( ctr, randColour(), varySize( CIRCLE_RADIUS ) ) );
		stamper.addShape( "square", ctr -> new Square( ctr, randColour(), varySize( SQUARE_EDGE ) ) );
		stamper.addShape( "snowflake", ctr ->
			new Snowflake( ctr, randColour( 0.8f ), randRotation(), varySize( SNOWFLAKE_ARM_LENGTH ) ) );

		new StamperFrame( stamper, 600, 400 ).setVisible( true );
	}
}
