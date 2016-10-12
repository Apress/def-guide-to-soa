/**
 * SOAPwithAttachmentLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.alsb.www.SOAPwithAttachment;

public class SOAPwithAttachmentLocator extends org.apache.axis.client.Service implements com.alsb.www.SOAPwithAttachment.SOAPwithAttachment {

    public SOAPwithAttachmentLocator() {
    }


    public SOAPwithAttachmentLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SOAPwithAttachmentLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SOAPwithAttachmentSOAP
    private java.lang.String SOAPwithAttachmentSOAP_address = "http://JDAVIES01.amer.bea.com:7001/SOAPwithAttachments_SB/SOAPwithAttachment";

    public java.lang.String getSOAPwithAttachmentSOAPAddress() {
        return SOAPwithAttachmentSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SOAPwithAttachmentSOAPWSDDServiceName = "SOAPwithAttachmentSOAP";

    public java.lang.String getSOAPwithAttachmentSOAPWSDDServiceName() {
        return SOAPwithAttachmentSOAPWSDDServiceName;
    }

    public void setSOAPwithAttachmentSOAPWSDDServiceName(java.lang.String name) {
        SOAPwithAttachmentSOAPWSDDServiceName = name;
    }

    public com.alsb.www.SOAPwithAttachment.SOAPwithAttachmentPort getSOAPwithAttachmentSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SOAPwithAttachmentSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSOAPwithAttachmentSOAP(endpoint);
    }

    public com.alsb.www.SOAPwithAttachment.SOAPwithAttachmentPort getSOAPwithAttachmentSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.alsb.www.SOAPwithAttachment.SOAPwithAttachmentSOAPStub _stub = new com.alsb.www.SOAPwithAttachment.SOAPwithAttachmentSOAPStub(portAddress, this);
            _stub.setPortName(getSOAPwithAttachmentSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSOAPwithAttachmentSOAPEndpointAddress(java.lang.String address) {
        SOAPwithAttachmentSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.alsb.www.SOAPwithAttachment.SOAPwithAttachmentPort.class.isAssignableFrom(serviceEndpointInterface)) {
                com.alsb.www.SOAPwithAttachment.SOAPwithAttachmentSOAPStub _stub = new com.alsb.www.SOAPwithAttachment.SOAPwithAttachmentSOAPStub(new java.net.URL(SOAPwithAttachmentSOAP_address), this);
                _stub.setPortName(getSOAPwithAttachmentSOAPWSDDServiceName());
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
        if ("SOAPwithAttachmentSOAP".equals(inputPortName)) {
            return getSOAPwithAttachmentSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.alsb.com/SOAPwithAttachment/", "SOAPwithAttachment");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.alsb.com/SOAPwithAttachment/", "SOAPwithAttachmentSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SOAPwithAttachmentSOAP".equals(portName)) {
            setSOAPwithAttachmentSOAPEndpointAddress(address);
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
