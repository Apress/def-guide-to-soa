package com.alsb.xmlwithwsdl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Construct the XML document that we will POST
		String doc = "<?xml version='1.0'?><name>John</name>";
		String hostname = "localhost";
		int port = 7001;
		try {
			InetAddress  addr = InetAddress.getByName(hostname);
			Socket sock = new Socket(addr, port);
			
			//Send the header
			String path = "ServiceTypes_SB/XMLwithWSDL/GetGreeting";
			BufferedWriter  wr = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(),"UTF-8"));
			wr.write("POST " + path + " HTTP/1.0\r\n");
			wr.write("Host: " + hostname + "\r\n");
			wr.write("Content-Length: " + doc.length() + "\r\n");
			wr.write("Content-Type: text/xml; charset=\"utf-8\"\r\n");
			wr.write("\r\n");
					
			//Send the document
			wr.write(doc);
			wr.flush();
			
			// Read the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			String line;
			while((line = rd.readLine()) != null)
				System.out.println(line);
		} catch(UnknownHostException ex) {
			ex.printStackTrace();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
