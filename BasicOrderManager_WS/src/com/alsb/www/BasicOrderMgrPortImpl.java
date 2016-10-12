package com.alsb.www;

import java.util.Vector;
import javax.jws.WebService;
import com.alsb.order.Product;
import com.alsb.order.ProductList;
import weblogic.jws.*;

/**
 * BasicOrderMgrPortImpl class implements web service endpoint interface
 * BasicOrderMgrPort
 */

@WebService(serviceName = "BasicOrderMgrService", targetNamespace = "http://www.alsb.com/", endpointInterface = "com.alsb.www.BasicOrderMgrPort")
@WLHttpTransport(serviceUri = "BasicOrderMgrService", portName = "BasicOrderMgrSoapPort")
public class BasicOrderMgrPortImpl implements BasicOrderMgrPort {

	private static Product[] productCatalog = { new Product(), new Product(),
			new Product(), new Product() };

	public BasicOrderMgrPortImpl() {
		productCatalog[0].setName("Television");
		productCatalog[0].setCreditNeeded(610);
		productCatalog[1].setName("Microwave");
		productCatalog[1].setCreditNeeded(500);
		productCatalog[2].setName("Automobile");
		productCatalog[2].setCreditNeeded(710);
		productCatalog[3].setName("Paper Hat");
		productCatalog[3].setCreditNeeded(440);
	}

	public int getCreditRating(java.lang.String firstName,
			java.lang.String lastName) {
		int rating;
		if (lastName.compareToIgnoreCase("X") > 0) {
			// If the last name starts with an X or later in the alphabet
			// the person has a bad credit rating.
			rating = 200;
		} else if (lastName.compareToIgnoreCase("S") > 0) {
			// Names starting with S or later have a poor credit rating
			rating = 600;
		} else {
			// Everyone else has a great credit rating
			rating = 750;
		}
		System.out.println("Business: The credit rating for " + firstName + " "
				+ lastName + " is " + rating);
		return rating;
	}

	public com.alsb.order.ProductList getProductCatalog(int creditRating) {
		// Iterate over the product catalog and return the products that the
		// customer can purchase
		ProductList productList = new ProductList();
		Vector<Product> customerCatalog = new Vector<Product>();

		for (int x = 0; x < productCatalog.length; x++) {
			if (productCatalog[x].getCreditNeeded() <= creditRating) {
				// Add this product to the list because the customer can buy it
				customerCatalog.add(productCatalog[x]);
			}
		}

		Product[] customerProducts = new Product[customerCatalog.size()];
		customerCatalog.copyInto(customerProducts);
		productList.setProduct(customerProducts);
		return productList;
	}

	public void processOrder(com.alsb.order.Order order) {
		System.out.println("Received a regular order from "
				+ order.getFirstName() + " " + order.getLastName()
				+ " for product "
				+ order.getLineItem()[0].getProduct().getName()
				+ " in quantity of " + order.getLineItem()[0].getQuantity());
		return;
	}

	public void processPreferredOrder(com.alsb.order.Order order) {
		System.out.println("Received a PREFERRED order from "
				+ order.getFirstName() + " " + order.getLastName()
				+ " for product "
				+ order.getLineItem()[0].getProduct().getName()
				+ " in quantity of " + order.getLineItem()[0].getQuantity());
		return;
	}
}