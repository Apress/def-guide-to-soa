package com.alsb.www;

public class TemperamentalCustomerServiceProxy implements com.alsb.www.TemperamentalCustomerService_PortType {
  private String _endpoint = null;
  private com.alsb.www.TemperamentalCustomerService_PortType temperamentalCustomerService_PortType = null;
  
  public TemperamentalCustomerServiceProxy() {
    _initTemperamentalCustomerServiceProxy();
  }
  
  private void _initTemperamentalCustomerServiceProxy() {
    try {
      temperamentalCustomerService_PortType = (new com.alsb.www.TemperamentalCustomerService_ServiceLocator()).getTemperamentalCustomerSoapPort();
      if (temperamentalCustomerService_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)temperamentalCustomerService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)temperamentalCustomerService_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (temperamentalCustomerService_PortType != null)
      ((javax.xml.rpc.Stub)temperamentalCustomerService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.alsb.www.TemperamentalCustomerService_PortType getTemperamentalCustomerService_PortType() {
    if (temperamentalCustomerService_PortType == null)
      _initTemperamentalCustomerServiceProxy();
    return temperamentalCustomerService_PortType;
  }
  
  public java.lang.String variableOpC(java.lang.String arg0) throws java.rmi.RemoteException{
    if (temperamentalCustomerService_PortType == null)
      _initTemperamentalCustomerServiceProxy();
    return temperamentalCustomerService_PortType.variableOpC(arg0);
  }
  
  public java.lang.String getRapidCustomer(int id) throws java.rmi.RemoteException{
    if (temperamentalCustomerService_PortType == null)
      _initTemperamentalCustomerServiceProxy();
    return temperamentalCustomerService_PortType.getRapidCustomer(id);
  }
  
  public int setDelay(int delayInSeconds) throws java.rmi.RemoteException{
    if (temperamentalCustomerService_PortType == null)
      _initTemperamentalCustomerServiceProxy();
    return temperamentalCustomerService_PortType.setDelay(delayInSeconds);
  }
  
  public java.lang.String variableOpB(java.lang.String arg0) throws java.rmi.RemoteException{
    if (temperamentalCustomerService_PortType == null)
      _initTemperamentalCustomerServiceProxy();
    return temperamentalCustomerService_PortType.variableOpB(arg0);
  }
  
  public java.lang.String getVariableCustomer(int id) throws java.rmi.RemoteException{
    if (temperamentalCustomerService_PortType == null)
      _initTemperamentalCustomerServiceProxy();
    return temperamentalCustomerService_PortType.getVariableCustomer(id);
  }
  
  public java.lang.String variableOpA(java.lang.String arg0) throws java.rmi.RemoteException{
    if (temperamentalCustomerService_PortType == null)
      _initTemperamentalCustomerServiceProxy();
    return temperamentalCustomerService_PortType.variableOpA(arg0);
  }
  
  
}