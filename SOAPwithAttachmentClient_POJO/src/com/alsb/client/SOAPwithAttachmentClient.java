package com.alsb.client;

import javax.activation.*;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import com.alsb.www.SOAPwithAttachment.*;

public class SOAPwithAttachmentClient {

	/**
	 * Simple test code to exercise the SOAPwithAttachment 
	 * web service
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SOAPwithAttachment service = new SOAPwithAttachmentLocator();
			SOAPwithAttachmentPort port = service.getSOAPwithAttachmentSOAP();

			// Create a DataHandler to read the file
			FileDataSource ds = new FileDataSource("c:\\test.zip");
			DataHandler dh = new DataHandler(ds);
			SubmitAttachmentRequestType req = new SubmitAttachmentRequestType("testX.zip");
			
			String newFileName = port.submitAttachment(req, dh);
			System.out.println("The web service has created the file " + newFileName);
		} catch(ServiceException ex) {
			ex.printStackTrace();
		} catch(RemoteException ex) {
			ex.printStackTrace();
			System.out.println("Exception! Ensure that the root directory of your " +
					"C:\\ drive contains a file named test.zip");
		} 
	}

}
