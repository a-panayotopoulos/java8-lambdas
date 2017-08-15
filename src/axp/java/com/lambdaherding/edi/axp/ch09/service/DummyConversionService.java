package com.lambdaherding.edi.axp.ch09.service;

import java.util.Currency;
import java.util.Random;

public class DummyConversionService extends DummyService implements ConversionService {
	public DummyConversionService( long delay ) {
		super( delay );
	}

	@Override
	public float getRelativeValue( Currency base, Currency target ) {
		spinWheels();

		// Whether one currency is greater than another is based on, um, their
		// currency code. Well, it is a dummy service
		int compare = base.getCurrencyCode().compareTo( target.getCurrencyCode() );
		float relativeValue;

		if ( compare == 0 ) {
			relativeValue = 1f;
		}
		else {
			Random rand = new Random( base.getCurrencyCode().hashCode() + target.getCurrencyCode().hashCode() );
			relativeValue = rand.nextFloat();

			if ( compare > 1 ) {
				relativeValue = 1 / relativeValue;
			}
		}

		System.out.printf( "1 %s is %.2f %s\n", base.getCurrencyCode(), relativeValue, target.getCurrencyCode() );
		return relativeValue;
	}
}
