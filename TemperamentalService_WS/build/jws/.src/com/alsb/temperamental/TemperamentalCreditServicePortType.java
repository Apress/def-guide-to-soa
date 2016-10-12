package com.alsb.temperamental;

/**
 * Generated interface, please do not edit.
 * Date: [Fri Nov 07 15:42:32 PST 2008]
 */

public interface TemperamentalCreditServicePortType extends java.rmi.Remote {

  /**
   * Web Method: variableCreditCheck ...
   */
  java.lang.String variableCreditCheck(java.lang.String arg0)
      throws java.rmi.RemoteException;
  /**
   * Web Method: setDelay ...
   */
  int setDelay(int delayInSeconds)
      throws java.rmi.RemoteException;
  /**
   * Web Method: variableOpB ...
   */
  java.lang.String variableOpB(java.lang.String arg0)
      throws java.rmi.RemoteException;
  /**
   * Web Method: variableOpA ...
   */
  java.lang.String variableOpA(java.lang.String arg0)
      throws java.rmi.RemoteException;
  /**
   * Web Method: variableOpC ...
   */
  java.lang.String variableOpC(java.lang.String arg0)
      throws java.rmi.RemoteException;
  /**
   * Web Method: rapidCreditCheck ...
   */
  java.lang.String rapidCreditCheck(java.lang.String arg0)
      throws java.rmi.RemoteException;
}
