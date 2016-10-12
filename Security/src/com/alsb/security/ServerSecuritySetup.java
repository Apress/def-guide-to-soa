package com.alsb.security;

import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;

import utils.CertGen;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class shows how to prgramatically connect to the JMX MBeans on an ALSB
 * server (the WebLogic server, really) and to configure security settings.
 * 
 * @author jedavies
 * 
 */
public class ServerSecuritySetup {

	static MBeanServerConnection connection = null;

	private static final ObjectName service;
	private static JMXConnector connector;

	static {
		try {
			service = new ObjectName(
					"com.bea:Name=EditService,Type=weblogic.management.mbeanservers.edit.EditServiceMBean");
		} catch (MalformedObjectNameException e) {
			throw new AssertionError(e.getMessage());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initConnection();
		try {
			String domainName = connection.getDefaultDomain();
			System.out.println("Domain name = " + domainName);
			ServerSecuritySetup app = new ServerSecuritySetup();
			// Get an object name for the Configuration Manager.
			ObjectName cfgMgr = (ObjectName) connection.getAttribute(service,
					"ConfigurationManager");

			// Start an edit session.
			ObjectName cfgRoot = app.startEditSession();
			
			// Change the console application context root
			app.editConsoleContextPath(cfgRoot);
			//app.setSecurity(cfgRoot);
		    // Save and activate.
	          connection.invoke(cfgMgr, "save", null, null);
	          app.activate();

	          // Close the connection with the MBean server.
	          connector.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Print an array of ServerRuntimeMBeans. This MBean is the root of the
	 * runtime MBean hierarchy, and each server in the domain hosts its own
	 * instance.
	 */
	public static ObjectName[] getServerRuntimes() throws Exception {
		return (ObjectName[]) connection
				.getAttribute(service, "ServerRuntimes");
	}

	/**
	 * Modify the DomainMBean's ConsoleContextPath attribute.
	 */
	public void editConsoleContextPath(ObjectName cfgRoot) throws Exception {
      // The calling method passes in the object name for DomainMBean.
      // This method only needs to set the value of an attribute
      // in DomainMBean. You need to restart rthe server after this
	  // before the new context will take effect
      Attribute contextPath = new Attribute("ConsoleContextPath", new String(
         "console"));
      connection.setAttribute(cfgRoot, contextPath);
      System.out.println("Changed the Admin Console context path to " +
         "secureConsoleContext");
   }
	
	/**
	 * Modify the Domain's security seetings
	 */
	public void setSecurity(ObjectName cfgRoot) throws Exception {
      // The calling method passes in the object name for DomainMBean.
      // This method only needs to set the value of an attribute
      // in DomainMBean.
	ObjectName server = (ObjectName) connection.invoke(cfgRoot,
			      "lookupServer", new Object[] { "AdminServer" }, new String[] { "java.lang.String" });
	if(server == null) 
		System.out.println("SERVER IS NULL");

	String keys = server.getKeyPropertyListString();
	System.out.println("Keys = " + keys);
	
	/*
      Attribute keystoreType = new Attribute("Keystores", new String(
         "CustomIdentityAndCustomTrust"));
      Attribute keystoreFileName = new Attribute("CustomIdentityKeyStoreFileName", new String(
      "Test.jks"));
      connection.setAttribute(server, keystoreType);
      connection.setAttribute(server, keystoreFileName);
      System.out.println("Changed the Admin Console context path to " +
         "secureConsoleContext");
      */
   }

	private static void initConnection() {
		Map environment = new HashMap();
		environment.put(Context.SECURITY_PRINCIPAL, "weblogic");
		environment.put(Context.SECURITY_CREDENTIALS, "weblogic");
		environment.put(
						javax.management.remote.JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,
						"weblogic.management.remote");
		try {
			JMXServiceURL url = new JMXServiceURL(
					"service:jmx:t3://localhost:7001/jndi/weblogic.management.mbeanservers.edit");
			System.out.println("Getting the connector...");
			connector = javax.management.remote.JMXConnectorFactory
					.connect(url, environment);
			System.out.println("Connecting...");
			connection = connector.getMBeanServerConnection();
			System.out.println("Success!!!");
		} catch (MalformedURLException ex) {
			System.out.println(ex);
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Start an edit session.
	 */
	public ObjectName startEditSession() throws Exception {
		// Get the object name for ConfigurationManagerMBean.
		ObjectName cfgMgr = (ObjectName) connection.getAttribute(service,
				"ConfigurationManager");

		// Instruct MBeanServerConnection to invoke
		// ConfigurationManager.startEdit(int waitTime int timeout).
		// The startEdit operation returns a handle to DomainMBean, which is
		// the root of the edit hierarchy.
		ObjectName domainConfigRoot = (ObjectName) connection.invoke(cfgMgr,
				"startEdit", new Object[] { new Integer(60000),
						new Integer(120000) }, new String[] {
						"java.lang.Integer", "java.lang.Integer" });
		if (domainConfigRoot == null) {
			// Couldn't get the lock
			throw new Exception("Somebody else is editing already");
		}
		return domainConfigRoot;
	}
	
	/**
	   * ----------------------------------------------------------
	   * Method to activate edits.
	   * ----------------------------------------------------------
	   */
	   public ObjectName activate() throws Exception {
	      // Get the object name for ConfigurationManagerMBean.
	      ObjectName cfgMgr = (ObjectName) connection.getAttribute(service,
	         "ConfigurationManager");
	      // Instruct MBeanServerConnection to invoke
	      // ConfigurationManager.activate(long timeout).
	      // The activate operation returns an ActivationTaskMBean.
	      // You can use the ActivationTaskMBean to track the progress
	      // of activating changes in the domain.
	      ObjectName task = (ObjectName) connection.invoke(cfgMgr, "activate",
	         new Object[] { new Long(120000) }, new String[] { "java.lang.Long" });
	      return task;
	   }

}
