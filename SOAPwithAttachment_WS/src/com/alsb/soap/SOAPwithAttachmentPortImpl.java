package com.alsb.soap;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.jws.WebService;
import weblogic.jws.*;

/**
 * SOAPwithAttachmentPortImpl class implements web service endpoint interface
 * SOAPwithAttachmentPort
 */

@WebService(serviceName = "SOAPwithAttachment", targetNamespace = "http://www.alsb.com/SOAPwithAttachment/", endpointInterface = "com.alsb.soap.SOAPwithAttachmentPort")
@WLHttpTransport(contextPath = "SOAPwithAttachment_WS", serviceUri = "SOAPwithAttachment", portName = "SOAPwithAttachmentSOAP")
public class SOAPwithAttachmentPortImpl implements SOAPwithAttachmentPort {

	public SOAPwithAttachmentPortImpl() {

	}

	public java.lang.String submitAttachment(
			com.alsb.soapwithattachment.SubmitAttachmentRequestType submitAttachment,
			javax.activation.DataHandler zipFile)
	{
		  String contentType = zipFile.getContentType();
		  System.out.println("Received file with a content type of: " + contentType);
		  String outputFileName = "c:\\" + submitAttachment.getFileName();

		  try {
			  InputStream is = zipFile.getInputStream();
			  FileOutputStream fos = new FileOutputStream(outputFileName);
			  // Read the input stream 1K at a time
			  byte[] buffer = new byte[1024];
			  while(is.available() > 0) {
				  System.out.println("Available bytes to read: " + is.available());
				  int numRead = is.read(buffer);
				  fos.write(buffer, 0, numRead);
			  }
			  fos.close();
			  is.close();
		  } catch(IOException ex) {
			  ex.printStackTrace();
		  }
	     return outputFileName;       
	}
}