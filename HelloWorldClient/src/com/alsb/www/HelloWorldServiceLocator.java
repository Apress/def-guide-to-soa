/**
 * HelloWorldServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.alsb.www;

public class HelloWorldServiceLocator extends org.apache.axis.client.Service implements com.alsb.www.HelloWorldService {

    public HelloWorldServiceLocator() {
    }


    public HelloWorldServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public HelloWorldServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for HelloWorldSoapPort
    private java.lang.String HelloWorldSoapPort_address = "http://localhost:7001/esb/HelloWorld";

    public java.lang.String getHelloWorldSoapPortAddress() {
        return HelloWorldSoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String HelloWorldSoapPortWSDDServiceName = "HelloWorldSoapPort";

    public java.lang.String getHelloWorldSoapPortWSDDServiceName() {
        return HelloWorldSoapPortWSDDServiceName;
    }

    public void setHelloWorldSoapPortWSDDServiceName(java.lang.String name) {
        HelloWorldSoapPortWSDDServiceName = name;
    }

    public com.alsb.www.HelloWorld getHelloWorldSoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(HelloWorldSoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getHelloWorldSoapPort(endpoint);
    }

    public com.alsb.www.HelloWorld getHelloWorldSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.alsb.www.HelloWorldServiceSoapBindingStub _stub = new com.alsb.www.HelloWorldServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getHelloWorldSoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setHelloWorldSoapPortEndpointAddress(java.lang.String address) {
        HelloWorldSoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.alsb.www.HelloWorld.class.isAssignableFrom(serviceEndpointInterface)) {
                com.alsb.www.HelloWorldServiceSoapBindingStub _stub = new com.alsb.www.HelloWorldServiceSoapBindingStub(new java.net.URL(HelloWorldSoapPort_address), this);
                _stub.setPortName(getHelloWorldSoapPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("HelloWorldSoapPort".equals(inputPortName)) {
            return getHelloWorldSoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.alsb.com/", "HelloWorldService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.alsb.com/", "HelloWorldSoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("HelloWorldSoapPort".equals(portName)) {
            setHelloWorldSoapPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
