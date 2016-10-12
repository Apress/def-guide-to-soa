package com.alsb.www;

public class TemperamentalCreditServiceProxy implements com.alsb.www.TemperamentalCreditService_PortType {
  private String _endpoint = null;
  private com.alsb.www.TemperamentalCreditService_PortType temperamentalCreditService_PortType = null;
  
  public TemperamentalCreditServiceProxy() {
    _initTemperamentalCreditServiceProxy();
  }
  
  private void _initTemperamentalCreditServiceProxy() {
    try {
      temperamentalCreditService_PortType = (new com.alsb.www.TemperamentalCreditService_ServiceLocator()).getTemperamentalCreditSoapPort();
      if (temperamentalCreditService_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)temperamentalCreditService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)temperamentalCreditService_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (temperamentalCreditService_PortType != null)
      ((javax.xml.rpc.Stub)temperamentalCreditService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.alsb.www.TemperamentalCreditService_PortType getTemperamentalCreditService_PortType() {
    if (temperamentalCreditService_PortType == null)
      _initTemperamentalCreditServiceProxy();
    return temperamentalCreditService_PortType;
  }
  
  public java.lang.String variableOpC(java.lang.String arg0) throws java.rmi.RemoteException{
    if (temperamentalCreditService_PortType == null)
      _initTemperamentalCreditServiceProxy();
    return temperamentalCreditService_PortType.variableOpC(arg0);
  }
  
  public java.lang.String variableCreditCheck(java.lang.String arg0) throws java.rmi.RemoteException{
    if (temperamentalCreditService_PortType == null)
      _initTemperamentalCreditServiceProxy();
    return temperamentalCreditService_PortType.variableCreditCheck(arg0);
  }
  
  public java.lang.String rapidCreditCheck(java.lang.String arg0) throws java.rmi.RemoteException{
    if (temperamentalCreditService_PortType == null)
      _initTemperamentalCreditServiceProxy();
    return temperamentalCreditService_PortType.rapidCreditCheck(arg0);
  }
  
  public int setDelay(int delayInSeconds) throws java.rmi.RemoteException{
    if (temperamentalCreditService_PortType == null)
      _initTemperamentalCreditServiceProxy();
    return temperamentalCreditService_PortType.setDelay(delayInSeconds);
  }
  
  public java.lang.String variableOpB(java.lang.String arg0) throws java.rmi.RemoteException{
    if (temperamentalCreditService_PortType == null)
      _initTemperamentalCreditServiceProxy();
    return temperamentalCreditService_PortType.variableOpB(arg0);
  }
  
  public java.lang.String variableOpA(java.lang.String arg0) throws java.rmi.RemoteException{
    if (temperamentalCreditService_PortType == null)
      _initTemperamentalCreditServiceProxy();
    return temperamentalCreditService_PortType.variableOpA(arg0);
  }
  
  
}