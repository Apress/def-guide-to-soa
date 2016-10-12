package com.alsb.www;

/**
 * Generated interface, please do not edit.
 * Date: [Fri Nov 07 15:42:38 PST 2008]
 */

public interface BasicOrderMgrPortImplPortType extends java.rmi.Remote {

  /**
   * Web Method: processPreferredOrder ...
   */
  void processPreferredOrder(com.alsb.order.Order order)
      throws java.rmi.RemoteException;
  /**
   * Web Method: getProductCatalog ...
   */
  com.alsb.order.ProductList getProductCatalog(int creditRating)
      throws java.rmi.RemoteException;
  /**
   * Web Method: processOrder ...
   */
  void processOrder(com.alsb.order.Order order)
      throws java.rmi.RemoteException;
  /**
   * Web Method: getCreditRating ...
   */
  int getCreditRating(java.lang.String firstName,java.lang.String lastName)
      throws java.rmi.RemoteException;
}
