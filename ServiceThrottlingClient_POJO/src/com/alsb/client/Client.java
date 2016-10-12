package com.alsb.client;

import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import com.alsb.www.CustomerRequestType;
import com.alsb.www.SerialPortType;
import com.alsb.www.SerialService;
import com.alsb.www.SerialServiceLocator;

public class Client {
	private static CustomerRequestType customer[] = {
		new CustomerRequestType(100, "James", "Jesse"),
		new CustomerRequestType(101, "Robert", "Dalton"),
		new CustomerRequestType(102, "Harold", "Lloyd"),
		new CustomerRequestType(7, "Lucille", "Ball"),
		new CustomerRequestType(103, "Soupy", "Sales"),
		new CustomerRequestType(104, "Elvis", "Presley"),
		new CustomerRequestType(2, "Bob", "Hope"),
	};
	
	public static void main(String[] args) {
		SerialService svc = new SerialServiceLocator();
		try {
			SerialPortType port = svc.getSerialService();
			for (int i = 0; i < customer.length; i++) {
				System.out.println("Processing customer " + i);
				port.processCustomer(customer[i]);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		} catch(ServiceException ex) {
			ex.printStackTrace();
		} catch(RemoteException ex) {
			ex.printStackTrace();
		}
	}
}
