package com.alsb.syncbusiness;

import javax.jws.WebService;
import weblogic.jws.*;

/**
 * SyncBusinessPortTypeImpl class implements web service endpoint interface
 * SyncBusinessPortType
 */

@WebService(serviceName = "SyncBusinessService", 
		targetNamespace = "http://www.alsb.com/", 
		endpointInterface = "com.alsb.syncbusiness.SyncBusinessPortType")
@WLHttpTransport(serviceUri = "SyncBusinessService", 
		portName = "SyncBusinessService")
public class SyncBusinessPortTypeImpl implements SyncBusinessPortType {

	private static long thirtySeconds = 30000;
	private static long timeDelay = thirtySeconds;
	private static int orderID = 100;

	public SyncBusinessPortTypeImpl() {
	}

	public com.alsb.Order submitOrder(com.alsb.Order order) {
		// Assign an order ID to the order
		order.setOrderID(orderID++);
		System.out.println("Starting to process the SYNC order id "
				+ order.getOrderID());
		com.alsb.Order result = new com.alsb.Order();
		result.setOrderID(order.getOrderID());

		try {
			Thread.sleep(timeDelay);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			System.out.println("Completed processing the SYNC order id "
					+ order.getOrderID());
		}
		return result;

	}
}