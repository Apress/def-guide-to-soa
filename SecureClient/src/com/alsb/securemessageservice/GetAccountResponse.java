/**
 * Generated from schema type t|e=getAccountResponse|d=getAccountResponse@http://www.alsb.com/SecureMessageService/
 */
package com.alsb.securemessageservice;

public class GetAccountResponse implements java.io.Serializable {

  private int customerID;

  public int getCustomerID() {
    return this.customerID;
  }

  public void setCustomerID(int customerID) {
    this.customerID = customerID;
  }

  private java.lang.String customerName;

  public java.lang.String getCustomerName() {
    return this.customerName;
  }

  public void setCustomerName(java.lang.String customerName) {
    this.customerName = customerName;
  }

  private java.lang.String account;

  public java.lang.String getAccount() {
    return this.account;
  }

  public void setAccount(java.lang.String account) {
    this.account = account;
  }

}
