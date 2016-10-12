package com.alsb.www;

public class SerialPortTypeProxy implements com.alsb.www.SerialPortType {
  private String _endpoint = null;
  private com.alsb.www.SerialPortType serialPortType = null;
  
  public SerialPortTypeProxy() {
    _initSerialPortTypeProxy();
  }
  
  private void _initSerialPortTypeProxy() {
    try {
      serialPortType = (new com.alsb.www.SerialServiceLocator()).getSerialService();
      if (serialPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)serialPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)serialPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (serialPortType != null)
      ((javax.xml.rpc.Stub)serialPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.alsb.www.SerialPortType getSerialPortType() {
    if (serialPortType == null)
      _initSerialPortTypeProxy();
    return serialPortType;
  }
  
  public void processCustomer(com.alsb.www.CustomerRequestType parameters) throws java.rmi.RemoteException{
    if (serialPortType == null)
      _initSerialPortTypeProxy();
    serialPortType.processCustomer(parameters);
  }
  
  
}