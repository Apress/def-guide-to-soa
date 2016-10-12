package com.alsb.pojo;

public class TaxPojo {
	/**
	 * ALSB can only call static methods in a POJO.
	 * 
	 * @param taxableAmount
	 * @return The additional tax to be paid
	 */
	public static double calculateTax(double taxableAmount) {
		double taxRate = 0.08;
		return taxableAmount * taxRate;
	}
}
