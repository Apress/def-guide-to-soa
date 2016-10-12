package com.alsb.pojo;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class MailClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String smtpServer="localhost";
			String to="proxy@mydomain.com";
			String from="mailclient@mydomain.com";
			String subject="Order";
			String body= "John\tDoe\tX\t75\r";

			send(smtpServer, to, from, subject, body);
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	    System.exit(0);
	}
	
	/**
	  * Send the message.
	  */
	public static void send(String smtpServer, String to, String from, String subject, String body) {
		try {
			Properties props = System.getProperties();

			props.put("mail.smtp.host", smtpServer);
			Session session = Session.getDefaultInstance(props, null);

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			msg.setSubject(subject);
			msg.setText(body);

			// Set some other header information for who sent this email
			msg.setHeader("X-Mailer", "MailClient");
			msg.setSentDate(new Date());
			Transport.send(msg);
			System.out.println("Message sent OK.");
			System.out.println("Body = " + body);
	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }
	}
}

