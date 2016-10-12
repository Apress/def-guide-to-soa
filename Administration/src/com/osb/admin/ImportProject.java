/**
 * ImportProject.java
 * This file shows how to connect to OSB via the JMX interfaces
 * to import projects and configre projects. Many thanks to Tolga Urhan
 * for his help with this.
 */
package com.osb.admin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;

import weblogic.management.jmx.MBeanServerInvocationHandler;
import weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean;
import com.bea.wli.sb.management.configuration.SessionManagementMBean;
import com.bea.wli.sb.management.configuration.ALSBConfigurationMBean;
import com.bea.wli.sb.management.importexport.ALSBJarInfo;
import com.bea.wli.sb.management.importexport.ALSBImportPlan;
import com.bea.wli.sb.util.Refs;
import com.bea.wli.sb.util.EnvValueTypes;
import com.bea.wli.sb.transports.URITableType;
import com.bea.wli.sb.transports.URITableElementType;
import com.bea.wli.config.importexport.ImportResult;
import com.bea.wli.config.resource.Diagnostics;
import com.bea.wli.config.component.NotFoundException;
import com.bea.wli.config.customization.EnvValueCustomization;
import com.bea.wli.config.customization.Customization;
import com.bea.wli.config.Ref;
import com.bea.wli.config.env.QualifiedEnvValue;

import java.io.File;
import java.io.ByteArrayOutputStream;
import java.util.Collections;

public class ImportProject {
	//static private final File CONFIG_JAR_FILE = new File("DeployCluster/HelloWorld_sbconfig.jar");
	
	/** The properties read in from the import.properties file */
	private Properties configProps = null;
	
	// These variables are all set by the connectToServer operation
	/** The JMX connection */
	private JMXConnector jmxConn = null;
	
	/** The MBean that accesses the domain runtime */
	DomainRuntimeServiceMBean domainService = null;
	
	/** The MBean that manages the session */
	SessionManagementMBean sm = null;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ImportProject project = new ImportProject();
		project.importFile();
		project.customizeProject();
		project.shutDown();
	}
	
	public ImportProject() {
		this.init("DeployCluster\\import.properties");
	}
	
	/**
     * Simply imports a jar file to be used for the rest of the tests
     * @throws Exception
     */
    public void importFile() {
    	try {
    		// create a session
    		String sessionName = createSessionName();
    		sm.createSession(sessionName);
    		
    		// obtain the ALSBConfigurationMBean instance that operates on the session that has
    		// just been created. Notice that the name of the mbean contains the session name.
    		ALSBConfigurationMBean alsbSession = (ALSBConfigurationMBean) domainService.
    			findService(ALSBConfigurationMBean.NAME + "." + sessionName, ALSBConfigurationMBean.TYPE, null);
	        
    		String importJar = (String)configProps.get("importJar");
    		File jarFile = new File(importJar);
	        byte[] bytes = readBytes(jarFile);
	        alsbSession.uploadJarFile(bytes);
	        ALSBJarInfo jarInfo = alsbSession.getImportJarInfo();
	        ALSBImportPlan importPlan = jarInfo.getDefaultImportPlan();
	        alsbSession.importUploaded(importPlan);
	        sm.activateSession(sessionName, "my import!!!");
	        
    	}  catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    
    private void init(String configFileName) {
    	System.out.println("Loading configuration properties from " + configFileName);
		configProps = loadProps(configFileName);
		String adminUrl = (String)configProps.get("adminUrl");
        String importUser = (String)configProps.get("importUser");
        String importPassword = (String)configProps.get("importPassword");
        
        
        System.out.println("Connecting to the OSB Admin Server listening on :" + adminUrl);
        connectToServer(importUser, importPassword, adminUrl);
    }
    
    /**
     * Lookup the HelloWorld_SB project and modify the business service endoints 
     */
	private void customizeProject() {
		String projectName = (String)configProps.get("project");
        Ref projectRef = Ref.makeProjectRef(projectName);
        Ref businessServiceRef = Refs.makeBusinessSvcRef(projectRef, "HelloWorldBiz");
        
        
        // We have a business service with one url "http://localhost:7101/url1".
        // we are trying to add a second url to a business service. To do that
        // we first obtain the xml representation of the existing URL, then we
        // update this xml to add a new url, finally set the environment value back

        // -----------------------------------------------------------------------
        // STEP1 - obtain the existing URL as an environment value. Notice that we
        // are not using SERVICE_URI as that would return the individual URL
        // at the specified index/location. We are using SERVICE_URI_TABLE
        // that returns all URLs as well as their weights in their entirety.
        // Also note that we pass null as location , since there is exactly one
        // instance of SERVICE_URI_TABLE. This fact is also mentioned in the javadoc
        // for the EnvValueTypes.SERVIE_URI_TABLE
        // -----------------------------------------------------------------------

        try {
        	// create a session
    		String sessionName = createSessionName();
    		sm.createSession(sessionName);
    		
    		// obtain the ALSBConfigurationMBean instance that operates on the session that has
    		// just been created. Notice that the name of the mbean contains the session name.
    		ALSBConfigurationMBean alsbSession = (ALSBConfigurationMBean) domainService.
    			findService(ALSBConfigurationMBean.NAME + "." + sessionName, ALSBConfigurationMBean.TYPE, null);
    		
        	URITableType uriTable = (URITableType)
            	alsbSession.getEnvValue(businessServiceRef,
                               EnvValueTypes.SERVICE_URI_TABLE,
                               null /* no location or index is necessary as there is one table */);
        	// -----------------------------------------------------------------------
            // STEP2 - update the URI Table element in the client , and optionally
            // adjust the weights. One could do more here, such as shuffling the URLS
            // -----------------------------------------------------------------------

            URITableElementType newUri = uriTable.addNewTableElement();
            newUri.setURI("http://localhost:7201/business/hello/HelloWorldService");

            // lets also distribute the weight between the existing and new Urls such that
            // existing one is used twice as much
            newUri.setWeight(1);
            URITableElementType existingUri = uriTable.getTableElementArray(0);
            existingUri.setURI("http://localhost:7101/business/hello/HelloWorldService");
            existingUri.setWeight(2);
            
            // -----------------------------------------------------------------------
            // STEP3 - set back the uri table via env value customization api
            // -----------------------------------------------------------------------
            QualifiedEnvValue env = new QualifiedEnvValue(businessServiceRef, EnvValueTypes.SERVICE_URI_TABLE, null, uriTable);

            EnvValueCustomization cust = new EnvValueCustomization("adds a second url for a business service",
                                                                   Collections.singletonList(env));
            alsbSession.customize(Collections.<Customization>singletonList(cust));
            // activate changes performed in the session
	        sm.activateSession(sessionName, "description");
	        
        } catch(NotFoundException ex) {
        	ex.printStackTrace();
        } catch(Exception ex) {
        	ex.printStackTrace();
        }
	}
	
	/**
	 * Shuts down the open connections.
	 */
	private void shutDown() {
		try {
			jmxConn.close();
			System.out.println("Disconnected from server.");
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Loads the given properties file into a Properties object
	 * @param configFileName The name of the properties file
	 * @return
	 */
	private Properties loadProps(String configFileName) {
		Properties configProps = new Properties();
		try {
			FileInputStream propInputStream = new FileInputStream(configFileName);
			configProps.load(propInputStream);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	    return configProps;
	}

	/**
	 * Connect to the Admin Server. This will initialize the following variables:
	 * jmxConn, domainService, sm, alsbSession, sessionName
	 * @param userName The aser name for the administrator account
	 * @param password The password for the administrator account
	 * @param URL The URL to the admin server. For example: t3://localhost:7001
	 */
	private void connectToServer(String userName, String password, String URL) {
		// Parse the URL to extract the host name and the port number
		int protocolEnd = URL.indexOf("://");
		int hostEnd = URL.lastIndexOf(":");
		String hostName = URL.substring(protocolEnd+3, hostEnd);
		String portStr = URL.substring(hostEnd+1);
		if(portStr.contains("/")) {
			//Trim off the rest of the URL after the port number
			int slashPos = portStr.indexOf('/');
			portStr = portStr.substring(0, slashPos);
		}
		int port = Integer.parseInt(portStr);
		
		try {
			jmxConn = initConnection(hostName, port, userName, password);
			MBeanServerConnection mbconn = jmxConn.getMBeanServerConnection();
			// get domain service mbean. This is the topmost mbean
			domainService = (DomainRuntimeServiceMBean) MBeanServerInvocationHandler.
			     newProxyInstance(mbconn, new ObjectName(DomainRuntimeServiceMBean.OBJECT_NAME));
			// obtain session management mbean to create a session.
			// This mbean instance can be used more than once to
			// create/discard/commit many sessions
			sm = (SessionManagementMBean) domainService.
				findService(SessionManagementMBean.NAME,
				SessionManagementMBean.TYPE, null);
		} catch(MalformedURLException ex) {
			ex.printStackTrace();
		} catch(MalformedObjectNameException ex) {
			ex.printStackTrace();
		} catch(IOException ex) {
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return;
	}
	
	
	private JMXConnector initConnection(String hostname, int port, String username, String password)
		throws IOException,MalformedURLException
	{
		JMXServiceURL serviceURL =
		new JMXServiceURL("t3", hostname, port,
		"/jndi/" + DomainRuntimeServiceMBean.MBEANSERVER_JNDI_NAME);
		
		Hashtable<String, String> h = new Hashtable<String, String>();
		h.put(Context.SECURITY_PRINCIPAL, username);
		h.put(Context.SECURITY_CREDENTIALS, password);
		h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES, "weblogic.management.remote");
		return JMXConnectorFactory.connect(serviceURL, h);
		}

	
	/**
	 Utility function to create an arbitrary session name
	 */
	private String createSessionName() {
		Date now = new Date();
		String sessionName = "SessionScript"+ now.getTime();
	    return sessionName;
	}
	

	/**
	 * Reads the given file into a byte array
	 * @param f The file to read
	 * @return A byte array that contaiins the file data
	 * @throws IOException
	 */
    private static byte[] readBytes(File f) throws IOException {
        FileInputStream io = new FileInputStream(f);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] byteBuf = new byte[1024];
        int datalen = io.read(byteBuf);
        while (datalen != -1) {
            baos.write(byteBuf, 0, datalen);
            datalen = io.read(byteBuf);
        }
        return baos.toByteArray();
    }

}
