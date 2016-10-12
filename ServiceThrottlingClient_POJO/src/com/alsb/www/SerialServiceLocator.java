/**
 * SerialServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.alsb.www;

public class SerialServiceLocator extends org.apache.axis.client.Service implements com.alsb.www.SerialService {

    public SerialServiceLocator() {
    }


    public SerialServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SerialServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SerialService
    private java.lang.String SerialService_address = "http://JDAVIES01.amer.bea.com:7001/ServiceThrottling_SB/SerialService";

    public java.lang.String getSerialServiceAddress() {
        return SerialService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SerialServiceWSDDServiceName = "SerialService";

    public java.lang.String getSerialServiceWSDDServiceName() {
        return SerialServiceWSDDServiceName;
    }

    public void setSerialServiceWSDDServiceName(java.lang.String name) {
        SerialServiceWSDDServiceName = name;
    }

    public com.alsb.www.SerialPortType getSerialService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SerialService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSerialService(endpoint);
    }

    public com.alsb.www.SerialPortType getSerialService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.alsb.www.SerialServiceSoapBindingStub _stub = new com.alsb.www.SerialServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSerialServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSerialServiceEndpointAddress(java.lang.String address) {
        SerialService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.alsb.www.SerialPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.alsb.www.SerialServiceSoapBindingStub _stub = new com.alsb.www.SerialServiceSoapBindingStub(new java.net.URL(SerialService_address), this);
                _stub.setPortName(getSerialServiceWSDDServiceName());
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
        if ("SerialService".equals(inputPortName)) {
            return getSerialService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.alsb.com/", "SerialService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.alsb.com/", "SerialService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SerialService".equals(portName)) {
            setSerialServiceEndpointAddress(address);
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
