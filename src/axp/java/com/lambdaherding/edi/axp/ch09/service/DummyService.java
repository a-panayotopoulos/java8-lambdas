package com.lambdaherding.edi.axp.ch09.service;

public abstract class DummyService {
	private final long delay;

	public DummyService( long delay ) {
		this.delay = delay;
	}

	void spinWheels() {
		try {
			Thread.sleep( delay );
		}
		catch ( InterruptedException e ) {
			e.printStackTrace();
		}
	}
}
