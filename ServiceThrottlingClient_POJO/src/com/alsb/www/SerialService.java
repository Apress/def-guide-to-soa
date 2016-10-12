/**
 * SerialService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.alsb.www;

public interface SerialService extends javax.xml.rpc.Service {
    public java.lang.String getSerialServiceAddress();

    public com.alsb.www.SerialPortType getSerialService() throws javax.xml.rpc.ServiceException;

    public com.alsb.www.SerialPortType getSerialService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
