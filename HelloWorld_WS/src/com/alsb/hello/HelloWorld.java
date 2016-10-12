package com.alsb.hello;

import javax.jws.*;
import weblogic.jws.WLHttpTransport;
import weblogic.jws.WSDL;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace="http://www.alsb.com/")
@WLHttpTransport(portName="HelloWorldSoapPort", serviceUri = "HelloWorldService")
@WSDL(exposed=true)
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
public class HelloWorld {
	@WebMethod
	public String getGreeting(String name) {
		return "Hello " + name;
	}
}