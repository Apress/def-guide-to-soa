/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package com.alsb.security;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.rpc.Stub;
import weblogic.wsee.security.bst.ClientBSTCredentialProvider;
import weblogic.xml.crypto.wss.WSSecurityContext;
import weblogic.xml.crypto.wss.provider.CredentialProvider;
import com.alsb.securemessageservice.GetAccount;
import com.alsb.securemessageservice.GetAccountResponse;
import com.alsb.security.lib.SecureMessageService_PortType;
import com.alsb.security.lib.SecureMessageService_Service;
import com.alsb.security.lib.SecureMessageService_Service_Impl;

/**
 * This client is used to connect to the secure web service
 * 
 * @author jedavies
 * 
 */
public class SecureClient {
	// Modify the file path to match the location of the
	// client_identity.jks file on your computer.
	static String clientIdentityKeystore = "client_trust.jks";
	private static boolean debug = true;
	private SecureMessageService_PortType port = null;
	private GetAccount account = new GetAccount();
	private static int iterations = 1;
	
	public SecureClient() {
		account.setCustomerID(121);
		try {
			// Create the list credential providers
			List<CredentialProvider> credProviders = new ArrayList<CredentialProvider>();

			// Create a BinarySecurityToken credential provider.
			// This is used when digitally signing the request.
			CredentialProvider cp = new ClientBSTCredentialProvider(
					clientIdentityKeystore, "storepassword", "jeff",
					"keypassword", "JKS");
			credProviders.add(cp);

			// Get the service stub so we can add the security information
			SecureMessageService_Service svc = 
				new SecureMessageService_Service_Impl();
			port = svc.getSecureMessageServiceSOAP();

			Stub stub = (Stub) port;

			// Set the endpoint to the TCPMON monitor so we can see the traffic
			stub._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY,
					"http://localhost:7001/Security_SB/SecureMessage");

			// Associate the key-pair credentials with the web service stub
			stub._setProperty(WSSecurityContext.CREDENTIAL_PROVIDER_LIST,
					credProviders);

			// The client uses the server's certificate that is embedded
			// in the WS-Policy file in the WSDL to encrypt the SOAP
			// request. The following code shows how the client can verify
			// that this certificate is valid using the TrustManager
			stub._setProperty(WSSecurityContext.TRUST_MANAGER,
					new ClientTrustManager(clientIdentityKeystore));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SecureClient client = new SecureClient();
		Date start = new Date();
		for(int x = 0; x < iterations; x++) {
			client.invokeService();
		}
		Date end = new Date();
		long millis = end.getTime() - start.getTime();
		System.out.println("SecureClient execution time for " + iterations + " = " + millis + " milliseconds");
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
