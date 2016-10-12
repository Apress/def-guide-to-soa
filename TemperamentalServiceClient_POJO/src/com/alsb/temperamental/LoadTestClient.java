package com.alsb.temperamental;

import com.alsb.www.*;

public class LoadTestClient {

	private static final String customerUrl = 
		"http://localhost:7001/esb/TempermentalCustomer?WSDL";
	private static final String creditUrl = 
		"http://localhost:7001/esb/TempermentalCredit?WSDL";
	private static TemperamentalCreditService_PortType creditPort = null;
	private static TemperamentalCustomerService_PortType customerPort = null;
	public static void main(String[] args) {
		runMonitoringTest();
	}

	/**
	 * Perform the necessary housekeeping to connect to the proxy services
	 */
	private static void loadServiceReferences() {
		try {
			System.out.println("Opening the credit service as " + creditUrl);
			TemperamentalCreditServiceProxy creditProxy = 
				new TemperamentalCreditServiceProxy();
			creditPort = creditProxy.getTemperamentalCreditService_PortType();
			
			System.out.println("Opening the customer service as " + customerUrl);
			TemperamentalCustomerServiceProxy customerProxy = 
				new TemperamentalCustomerServiceProxy();
			customerPort = customerProxy.getTemperamentalCustomerService_PortType();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void runMonitoringTest() {
		try {
			loadServiceReferences();
			System.out.println("Invoking the methods...");
			// Generate a warning on Op A
			creditPort.setDelay(2);
			creditPort.variableOpA("foo");
			// Generate a minor alert on Op B
			creditPort.setDelay(4);
			creditPort.variableOpB("foo");
			// Generate a major alert on Op C
			creditPort.setDelay(6);
			creditPort.variableOpC("foo");
			// Generates a fatal alert
			creditPort.setDelay(25);
			creditPort.variableCreditCheck("William Smith");
			// Generate a critical customer alert
			customerPort.setDelay(15);
			customerPort.variableOpA("Test");
			// Generate a fatal customer alert
			customerPort.setDelay(25);
			customerPort.getVariableCustomer(2);
			
			System.out.println("Done.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
