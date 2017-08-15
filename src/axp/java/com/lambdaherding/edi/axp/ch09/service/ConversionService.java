package com.lambdaherding.edi.axp.ch09.service;

import java.util.Currency;

public interface ConversionService {
	public float getRelativeValue( Currency base, Currency target );
}
