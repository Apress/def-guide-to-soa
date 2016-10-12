package com.alsb.business;

/**
 * Generated interface, please do not edit.
 * Date: [Fri Nov 07 15:42:44 PST 2008]
 */

public interface SplitJoin_WSImplPortType extends java.rmi.Remote {

  /**
   * Web Method: getOrderInfo ...
   */
  com.alsb.GetOrderInfoResponse getOrderInfo(com.alsb.GetOrderInfo getOrderInfo)
      throws java.rmi.RemoteException;
  /**
   * Web Method: getBillingInfo ...
   */
  com.alsb.GetBillingInfoResponse getBillingInfo(com.alsb.GetBillingInfo getBillingInfo)
      throws java.rmi.RemoteException;
  /**
   * Web Method: getCustomerInfo ...
   */
  com.alsb.GetCustomerInfoResponse getCustomerInfo(com.alsb.GetCustomerInfo getCustomerInfo)
      throws java.rmi.RemoteException;
}
