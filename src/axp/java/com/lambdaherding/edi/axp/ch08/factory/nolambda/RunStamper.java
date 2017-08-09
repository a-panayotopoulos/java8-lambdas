package com.lambdaherding.edi.axp.ch08.factory.nolambda;

import com.lambdaherding.edi.axp.ch08.factory.common.controller.Stamper;
import com.lambdaherding.edi.axp.ch08.factory.common.ui.StamperFrame;

/**
 * Create a stamper controller, its factories, and UI.
 */
public class RunStamper {
	public static void main( String[] args ) {
		Stamper stamper = new Stamper();

		stamper.addShape( "circle", new CircleFactory( 15 ) );
		stamper.addShape( "square", new SquareFactory( 26 ) );
		stamper.addShape( "snowflake", new SnowflakeFactory( 18 ) );

		new StamperFrame( stamper, 600, 400 ).setVisible( true );
	}
}
