package com.alsb.temperamental;

import javax.jws.*;
import javax.jws.soap.SOAPBinding;
import weblogic.jws.WLHttpTransport;
import weblogic.jws.WSDL;

@WebService(targetNamespace="http://www.alsb.com", serviceName = "TempermentalCustomerService", name = "TempermentalCustomerService")
@WLHttpTransport(serviceUri="customer", portName = "TempermentalCustomerSoapPort")
@WSDL(exposed=true)
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
public class TemperamentalCustomerService {

	/** The amount of time (in seconds) the web service will delay */
	private static int DELAYINSECONDS = 0;
	
	private static String customerNames[] = {
		"James Brown", "Elvis Presley", 
		"Little Richard", "Lucille Ball", 
		"Cab Calloway", "Frank Sinatra" };
	
	private String getName(int id) {
		if(id < customerNames.length)
			return customerNames[id];
		else
			return "Unknown";
	}
	
	
	@WebMethod
	public String getVariableCustomer(int id) {
		delay();
		return getName(id);
	}
	
	@WebMethod
	public String getRapidCustomer(int id) {
		delay();
		return getName(id);
	}
	
	@WebMethod
	public String variableOpA(String arg0) {
		delay();
		return arg0 + DELAYINSECONDS;
	}
	
	@WebMethod
	public String variableOpB(String arg0) {
		delay();
		return arg0 + DELAYINSECONDS;
	}
	
	@WebMethod
	public String variableOpC(String arg0) {
		delay();
		return arg0 + DELAYINSECONDS;
	}
	
	@WebMethod
	public int setDelay(int delayInSeconds) {
		DELAYINSECONDS = delayInSeconds;
		return DELAYINSECONDS;
	}
	
	private void delay() {
		try {
			Thread.sleep(DELAYINSECONDS * 1000);
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}