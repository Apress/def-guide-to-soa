/**
 * HelloWorldService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.alsb.www;

public interface HelloWorldService extends javax.xml.rpc.Service {
    public java.lang.String getHelloWorldSoapPortAddress();

    public com.alsb.www.HelloWorld getHelloWorldSoapPort() throws javax.xml.rpc.ServiceException;

    public com.alsb.www.HelloWorld getHelloWorldSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
