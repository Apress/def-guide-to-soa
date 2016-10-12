package com.alsb.serial;

import javax.jws.WebService;
import weblogic.jws.*;

/**
 * SerialPortTypeImpl class implements web service endpoint interface
 * SerialPortType
 */
@WebService(serviceName = "SerialService", 
		targetNamespace = "http://www.alsb.com/", 
		endpointInterface = "com.alsb.serial.SerialPortType")
@WLHttpTransport(serviceUri = "SerialService", 
		portName = "SerialService")
public class SerialPortTypeImpl implements SerialPortType {

	private static long thirtySeconds = 30000;
	private static long timeDelay = thirtySeconds;
	private static int requestID = 1;
	
	public SerialPortTypeImpl() {

	}

	public void processCustomer(com.alsb.CustomerRequestType parameters)
	{
		System.out.println("Starting to process request " + requestID + " from customer " + parameters.getCustomerID());
		try {
			Thread.sleep(timeDelay);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			System.out.println("Completed processing the request " + requestID++ + " from customer " + parameters.getCustomerID());
		}
		return;
	}
}