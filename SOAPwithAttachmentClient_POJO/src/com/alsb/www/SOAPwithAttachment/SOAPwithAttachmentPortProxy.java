package com.alsb.www.SOAPwithAttachment;

public class SOAPwithAttachmentPortProxy implements com.alsb.www.SOAPwithAttachment.SOAPwithAttachmentPort {
  private String _endpoint = null;
  private com.alsb.www.SOAPwithAttachment.SOAPwithAttachmentPort sOAPwithAttachmentPort = null;
  
  public SOAPwithAttachmentPortProxy() {
    _initSOAPwithAttachmentPortProxy();
  }
  
  private void _initSOAPwithAttachmentPortProxy() {
    try {
      sOAPwithAttachmentPort = (new com.alsb.www.SOAPwithAttachment.SOAPwithAttachmentLocator()).getSOAPwithAttachmentSOAP();
      if (sOAPwithAttachmentPort != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sOAPwithAttachmentPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sOAPwithAttachmentPort)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sOAPwithAttachmentPort != null)
      ((javax.xml.rpc.Stub)sOAPwithAttachmentPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.alsb.www.SOAPwithAttachment.SOAPwithAttachmentPort getSOAPwithAttachmentPort() {
    if (sOAPwithAttachmentPort == null)
      _initSOAPwithAttachmentPortProxy();
    return sOAPwithAttachmentPort;
  }
  
  public java.lang.String submitAttachment(com.alsb.www.SOAPwithAttachment.SubmitAttachmentRequestType submitAttachment, javax.activation.DataHandler zipFile) throws java.rmi.RemoteException{
    if (sOAPwithAttachmentPort == null)
      _initSOAPwithAttachmentPortProxy();
    return sOAPwithAttachmentPort.submitAttachment(submitAttachment, zipFile);
  }
  
  
}