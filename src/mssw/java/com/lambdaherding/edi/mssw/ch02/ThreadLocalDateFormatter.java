/**
 * 
 */
package com.lambdaherding.edi.mssw.ch02;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.DateFormatter;

/**
 * @author Mike
 *
 */
public class ThreadLocalDateFormatter {

	public void printDate(){
		ThreadLocal<DateFormatter> thread = ThreadLocal.withInitial( 
				() -> new DateFormatter(
						new SimpleDateFormat("dd-MMM-YYYY")
					)	
				);
		
		System.out.println(thread.get().getFormat().format(new Date()));
	}
}
