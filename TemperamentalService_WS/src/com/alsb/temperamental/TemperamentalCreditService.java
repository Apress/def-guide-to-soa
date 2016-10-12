package com.alsb.temperamental;

import javax.jws.*;
import javax.jws.soap.SOAPBinding;
import weblogic.jws.WLHttpTransport;
import weblogic.jws.WSDL;

@WebService(targetNamespace="http://www.alsb.com", serviceName="TempermentalCreditService", name="TempermentalCreditService")
@WLHttpTransport(serviceUri="credit", portName="TempermentalCreditSoapPort")
@WSDL(exposed=true)
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
public class TemperamentalCreditService {

	/** The amount of time (in seconds) the web service will delay */
	private static int DELAYINSECONDS = 0;
	
	@WebMethod
	public String variableCreditCheck(String arg0) {
		delay();
		return arg0 + DELAYINSECONDS;
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
	public String rapidCreditCheck(String arg0) {
		return arg0;
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