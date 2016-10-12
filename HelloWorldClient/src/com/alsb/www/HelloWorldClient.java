package com.alsb.www;

import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;

public class HelloWorldClient {

	/**
	 * A simple Java client that invokes the proxy service
	 * @param args This parameter is unused.
	 */
	public static void main(String[] args) {
		HelloWorldService service = new HelloWorldServiceLocator();
		try {
			HelloWorld port = service.getHelloWorldSoapPort();
			String greeting = port.getGreeting("Test");
			System.out.println("Greeting returned was: " + greeting);
		} catch (ServiceException ex) {
			ex.printStackTrace();
		} 
		catch(RemoteException ex) {
			ex.printStackTrace();
		}
	}
}