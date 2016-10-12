package com.alsb.async.ws;

import java.util.Date;
import javax.jws.WebService;
import weblogic.jws.*;

/**
 * AsyncBusinessImpl class implements web service endpoint interface
 * AsyncBusiness
 */

@WebService(serviceName = "AsyncBusiness", 
		targetNamespace = "http://www.alsb.com/AsyncBusiness/", 
		endpointInterface = "com.alsb.async.ws.AsyncBusiness")
@WLJmsTransport(serviceUri = "AsyncBusiness", 
		queue = "jms.WebServiceQueue", 
		connectionFactory = "jms.wsConnectionFactory", 
		portName = "AsyncBusiness")
public class AsyncBusinessImpl implements AsyncBusiness {

	private static long thirtySeconds = 30000;

	public AsyncBusinessImpl() {

	}

	public void submitAsyncOrder(com.alsb.order.Order submitOrder) {
		Date now = new Date();
		System.out.println("JWS: Starting to process the async order id:  "
				+ submitOrder.getId() + " at " + now.toLocaleString());
		try {
			Thread.sleep(thirtySeconds);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			now = new Date();
			System.out.println("JWS: Finished processing the async order id:  "
					+ submitOrder.getId() + " at " + now.toLocaleString());
		}
	}
}