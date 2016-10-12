package com.alsb.business;

import java.util.Calendar;
import java.util.Date;
import javax.jws.WebService;
import com.alsb.Bill;
import com.alsb.Order;
import weblogic.jws.*;

/**
 * SplitJoin_WSImpl class implements web service endpoint interface SplitJoin_WS
 */

@WebService(serviceName = "SplitJoin_WS", targetNamespace = "http://www.alsb.com/", endpointInterface = "com.alsb.business.SplitJoin_WS")
@WLHttpTransport(serviceUri = "business", portName = "SplitJoin_WSSOAP")
public class SplitJoin_WSImpl implements SplitJoin_WS {

	private static int FIVESECONDS = 5000;

	public SplitJoin_WSImpl() {

	}

	public com.alsb.GetCustomerInfoResponse getCustomerInfo(
			com.alsb.GetCustomerInfo getCustomerInfo) {
		com.alsb.GetCustomerInfoResponse response = new com.alsb.GetCustomerInfoResponse();
		int customerID = -1;
		if(getCustomerInfo != null)
			customerID = getCustomerInfo.getCustomerID();
		response.setCustomerID(customerID);
		response.setFirstName("John");
		response.setLastName("Doe");
		Date now = new Date();
		System.out.println("Getting customer information for customer  "
				+ getCustomerInfo.getCustomerID() + " at "
				+ now.toLocaleString());
		try {
			Thread.sleep(FIVESECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			now = new Date();
			System.out
					.println("Finished retreiving the customer information for customer "
							+ getCustomerInfo.getCustomerID()
							+ " at "
							+ now.toLocaleString());
		}
		return response;
	}

	public com.alsb.GetBillingInfoResponse getBillingInfo(
			com.alsb.GetBillingInfo getBillingInfo) {
		int customerID = -1;
		if(getBillingInfo != null)
			customerID = getBillingInfo.getCustomerID();
		Bill[] billArray = new Bill[2];
		billArray[0] = new Bill();
		billArray[0].setCustomerID(customerID);
		billArray[0].setFirstName("John");
		billArray[0].setLastName("Doe");
		Calendar billDate1 = Calendar.getInstance();
		billDate1.add(Calendar.MONTH, -2);
		billArray[0].setDate(billDate1);
		billArray[0].setAmount(129.54);
		
		billArray[1] = new Bill();
		billArray[1].setCustomerID(customerID);
		billArray[1].setFirstName("Jane");
		billArray[1].setLastName("Doe");
		Calendar billDate2 = Calendar.getInstance();
		billDate1.add(Calendar.MONTH, -1);
		billArray[1].setDate(billDate2);
		billArray[1].setAmount(129.54);
		com.alsb.GetBillingInfoResponse response = new com.alsb.GetBillingInfoResponse();
		response.setCustomerID(getBillingInfo.getCustomerID());
		response.setBill(billArray);
		Date now = new Date();
		System.out.println("Getting billing information for customer  "
				+ getBillingInfo.getCustomerID() + " at "
				+ now.toLocaleString());
		try {
			Thread.sleep(FIVESECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			now = new Date();
			System.out
					.println("Finished retreiving the billing information for customer "
							+ getBillingInfo.getCustomerID()
							+ " at "
							+ now.toLocaleString());
		}
		return response;
	}

	public com.alsb.GetOrderInfoResponse getOrderInfo(
			com.alsb.GetOrderInfo getOrderInfo) {
		int customerID = -1;
		if(getOrderInfo != null)
			customerID = getOrderInfo.getCustomerID();
		
		Order[] orderArray = new Order[2];
		orderArray[0] = new Order();
		orderArray[0].setCustomerID(customerID);
		orderArray[0].setProduct(new String[] { "Camera", "Phone" });
		orderArray[1] = new Order();
		orderArray[1].setCustomerID(customerID);
		orderArray[1].setProduct(new String[] { "Television", "Airplane" });
		com.alsb.GetOrderInfoResponse response = new com.alsb.GetOrderInfoResponse();
		response.setCustomerID(getOrderInfo.getCustomerID());
		response.setOrder(orderArray);
		Date now = new Date();
		System.out.println("Getting order information for customer  "
				+ getOrderInfo.getCustomerID() + " at " + now.toLocaleString());
		try {
			Thread.sleep(FIVESECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			now = new Date();
			System.out
					.println("Finished retreiving the order information for customer "
							+ getOrderInfo.getCustomerID()
							+ " at "
							+ now.toLocaleString());
		}
		return response;
	}
}