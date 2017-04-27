package com.lambdaherding.edi.slc.ch02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.DateFormatter;

public class Ex2ThreadLocal {

	private ThreadLocal<DateFormatter> threadSafeDateFormatter = ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));
	
	public void printDateFormatter() {
		try {
			System.out.println(threadSafeDateFormatter.get().toString() + ": " + threadSafeDateFormatter.get().valueToString(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			new Thread( () -> new Ex2ThreadLocal().printDateFormatter() ).run();
		}
	}
}

//javax.swing.text.DateFormatter@119d7047: 27-Apr-2017
//javax.swing.text.DateFormatter@776ec8df: 27-Apr-2017
//javax.swing.text.DateFormatter@4eec7777: 27-Apr-2017
//javax.swing.text.DateFormatter@3b07d329: 27-Apr-2017
//javax.swing.text.DateFormatter@41629346: 27-Apr-2017
//javax.swing.text.DateFormatter@404b9385: 27-Apr-2017
//javax.swing.text.DateFormatter@6d311334: 27-Apr-2017
//javax.swing.text.DateFormatter@682a0b20: 27-Apr-2017
//javax.swing.text.DateFormatter@3d075dc0: 27-Apr-2017
//javax.swing.text.DateFormatter@214c265e: 27-Apr-2017