/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package com.alsb.security;

import java.rmi.RemoteException;
import java.util.Date;
import javax.xml.rpc.Stub;
import com.alsb.securemessageservice.GetAccount;
import com.alsb.securemessageservice.GetAccountResponse;
import com.alsb.security.lib.UnsecureMessageService_PortType;
import com.alsb.security.lib.UnsecureMessageService_Service;
import com.alsb.security.lib.UnsecureMessageService_Service_Impl;

public class UnsecureClient {
	private static boolean debug = true;
	private UnsecureMessageService_PortType port = null;
	private GetAccount account = new GetAccount();
	private static int iterations = 1;
	
	public UnsecureClient() {
		account.setCustomerID(121);
		try {
			UnsecureMessageService_Service svc = 
				new UnsecureMessageService_Service_Impl();
			port = svc.getUnsecureMessageServiceSOAP();
			Stub stub = (Stub) port;
	
			// Set the endpoint to the TCPMON monitor so we can see the traffic
			stub._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY,
					"http://localhost:8001/Security_SB/UnsecureMessage");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		UnsecureClient client = new UnsecureClient();
		Date start = new Date();
		for(int x = 0; x < iterations; x++) {
			client.invokeService();
		}
		Date end = new Date();
		long millis = end.getTime() - start.getTime();
		System.out.println("Execution time for " + iterations + " = " + millis + " milliseconds");
	}
	
	private void invokeService() {
		try {
			// Make the service invocation
			GetAccountResponse response = port.getAccount(account);
			if(debug) {
				System.out.println("Account Information");
				System.out.println("===================");
				System.out.println("Customer ID: " + response.getCustomerID());
				System.out.println("       Name: " + response.getCustomerName());
				System.out.println("    Account: " + response.getAccount());
			}
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
	}
}
