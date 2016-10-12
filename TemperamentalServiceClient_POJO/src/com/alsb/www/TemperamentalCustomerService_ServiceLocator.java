/**
 * TemperamentalCustomerService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.alsb.www;

public class TemperamentalCustomerService_ServiceLocator extends org.apache.axis.client.Service implements com.alsb.www.TemperamentalCustomerService_Service {

    public TemperamentalCustomerService_ServiceLocator() {
    }


    public TemperamentalCustomerService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TemperamentalCustomerService_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TemperamentalCustomerSoapPort
    private java.lang.String TemperamentalCustomerSoapPort_address = "http://JDAVIES01.amer.bea.com:7001/esb/TemperamentalCustomer";

    public java.lang.String getTemperamentalCustomerSoapPortAddress() {
        return TemperamentalCustomerSoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TemperamentalCustomerSoapPortWSDDServiceName = "TemperamentalCustomerSoapPort";

    public java.lang.String getTemperamentalCustomerSoapPortWSDDServiceName() {
        return TemperamentalCustomerSoapPortWSDDServiceName;
    }

    public void setTemperamentalCustomerSoapPortWSDDServiceName(java.lang.String name) {
        TemperamentalCustomerSoapPortWSDDServiceName = name;
    }

    public com.alsb.www.TemperamentalCustomerService_PortType getTemperamentalCustomerSoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TemperamentalCustomerSoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTemperamentalCustomerSoapPort(endpoint);
    }

    public com.alsb.www.TemperamentalCustomerService_PortType getTemperamentalCustomerSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.alsb.www.TemperamentalCustomerServiceSoapBindingStub _stub = new com.alsb.www.TemperamentalCustomerServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getTemperamentalCustomerSoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTemperamentalCustomerSoapPortEndpointAddress(java.lang.String address) {
        TemperamentalCustomerSoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.alsb.www.TemperamentalCustomerService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.alsb.www.TemperamentalCustomerServiceSoapBindingStub _stub = new com.alsb.www.TemperamentalCustomerServiceSoapBindingStub(new java.net.URL(TemperamentalCustomerSoapPort_address), this);
                _stub.setPortName(getTemperamentalCustomerSoapPortWSDDServiceName());
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
        if ("TemperamentalCustomerSoapPort".equals(inputPortName)) {
            return getTemperamentalCustomerSoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.alsb.com", "TemperamentalCustomerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.alsb.com", "TemperamentalCustomerSoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TemperamentalCustomerSoapPort".equals(portName)) {
            setTemperamentalCustomerSoapPortEndpointAddress(address);
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
