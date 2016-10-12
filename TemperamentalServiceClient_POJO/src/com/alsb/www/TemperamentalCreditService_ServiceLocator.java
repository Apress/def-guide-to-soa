/**
 * TemperamentalCreditService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.alsb.www;

public class TemperamentalCreditService_ServiceLocator extends org.apache.axis.client.Service implements com.alsb.www.TemperamentalCreditService_Service {

    public TemperamentalCreditService_ServiceLocator() {
    }


    public TemperamentalCreditService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TemperamentalCreditService_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TemperamentalCreditSoapPort
    private java.lang.String TemperamentalCreditSoapPort_address = "http://JDAVIES01.amer.bea.com:7001/esb/TemperamentalCredit";

    public java.lang.String getTemperamentalCreditSoapPortAddress() {
        return TemperamentalCreditSoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TemperamentalCreditSoapPortWSDDServiceName = "TemperamentalCreditSoapPort";

    public java.lang.String getTemperamentalCreditSoapPortWSDDServiceName() {
        return TemperamentalCreditSoapPortWSDDServiceName;
    }

    public void setTemperamentalCreditSoapPortWSDDServiceName(java.lang.String name) {
        TemperamentalCreditSoapPortWSDDServiceName = name;
    }

    public com.alsb.www.TemperamentalCreditService_PortType getTemperamentalCreditSoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TemperamentalCreditSoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTemperamentalCreditSoapPort(endpoint);
    }

    public com.alsb.www.TemperamentalCreditService_PortType getTemperamentalCreditSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.alsb.www.TemperamentalCreditServiceSoapBindingStub _stub = new com.alsb.www.TemperamentalCreditServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getTemperamentalCreditSoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTemperamentalCreditSoapPortEndpointAddress(java.lang.String address) {
        TemperamentalCreditSoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.alsb.www.TemperamentalCreditService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.alsb.www.TemperamentalCreditServiceSoapBindingStub _stub = new com.alsb.www.TemperamentalCreditServiceSoapBindingStub(new java.net.URL(TemperamentalCreditSoapPort_address), this);
                _stub.setPortName(getTemperamentalCreditSoapPortWSDDServiceName());
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
        if ("TemperamentalCreditSoapPort".equals(inputPortName)) {
            return getTemperamentalCreditSoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.alsb.com", "TemperamentalCreditService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.alsb.com", "TemperamentalCreditSoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TemperamentalCreditSoapPort".equals(portName)) {
            setTemperamentalCreditSoapPortEndpointAddress(address);
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
