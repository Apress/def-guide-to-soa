"""
This script starts an edit session in ALSB and creates the various
non-ALSB resources that some of the ALSB projects will rely on. 
Specifically, it creates a JMS server and a queue thta are used in 
the MessageFLow project in Chapter 6.
This script also serves as a good example for how you can automate
parts of your management of WebLogic Server.
"""

import sys
from java.lang import System

def createJMS():
	print "Creating WebServiceJMSServer..."
	jmsserver1mb = create('WebServiceJMSServer','JMSServer')
	jmsserver1mb.addTarget(servermb) 
	jmsserver1mb.setNotes("This JMS server is used by the asynchronous messageing exercise in chapter 6")
	
	print "Creating the WebServiceResources JMS Module..."
	jmsMySystemResource = create("WebServiceResources","JMSSystemResource")
	jmsMySystemResource.addTarget(servermb) 
	jmsMySystemResource.setNotes("A JMS system module to contain the connection factory and the JMS queue")
    
	print "Creating subdeployment..."
	subDep1mb = jmsMySystemResource.createSubDeployment('WebServiceJMSSubdeploy')
	subDep1mb.addTarget(jmsserver1mb)

	theJMSResource = jmsMySystemResource.getJMSResource()
	
	print "Creating connection factory..."
	connfact1 = theJMSResource.createConnectionFactory('webServiceJMSConnectionFactory')
	connfact1.setJNDIName('jms.wsConnectionFactory')
	connfact1.setLocalJNDIName('local.jms.wsConnectionFactory')
	connfact1.setSubDeploymentName('WebServiceJMSSubdeploy')
	connfact1.setNotes("Use the JNDI name to connect to the JMS queue")
	
	print "Creating WebServiceQueue..."
	jmsqueue1 = theJMSResource.createQueue('WebServiceQueue')
	jmsqueue1.setJNDIName('jms.WebServiceQueue')
	jmsqueue1.setSubDeploymentName('WebServiceJMSSubdeploy')

	print "Creating BinaryFileQueue..."
	jmsqueue1 = theJMSResource.createTopic('BinaryFileTopic')
	jmsqueue1.setJNDIName('jms.BinaryFileTopic')
	jmsqueue1.setSubDeploymentName('WebServiceJMSSubdeploy')
	
def createBinaryFileJMS():
	print "Locating WebServiceJMSServer..."
	jmsserver1mb = find('WebServiceJMSServer','JMSServer')
	jmsserver1mb.addTarget(servermb) 
	jmsserver1mb.setNotes("This JMS server is used by the asynchronous messageing exercise in chapter 6")
	
	print "Creating the WebServiceResources JMS Module..."
	jmsMySystemResource = create("WebServiceResources","JMSSystemResource")
	jmsMySystemResource.addTarget(servermb) 
	jmsMySystemResource.setNotes("A JMS system module to contain the connection factory and the JMS queue")
    
	print "Creating subdeployment..."
	subDep1mb = jmsMySystemResource.createSubDeployment('WebServiceJMSSubdeploy')
	subDep1mb.addTarget(jmsserver1mb)

	theJMSResource = jmsMySystemResource.getJMSResource()
	
	print "Creating connection factory..."
	connfact1 = theJMSResource.createConnectionFactory('webServiceJMSConnectionFactory')
	connfact1.setJNDIName('jms.wsConnectionFactory')
	connfact1.setLocalJNDIName('local.jms.wsConnectionFactory')
	connfact1.setSubDeploymentName('WebServiceJMSSubdeploy')
	connfact1.setNotes("Use the JNDI name to connect to the JMS queue")
	
	print "Creating BinaryFileQueue..."
	jmsqueue1 = theJMSResource.createQueue('BinaryFileQueue')
	jmsqueue1.setJNDIName('jms.BinaryFileQueue')
	jmsqueue1.setSubDeploymentName('WebServiceJMSSubdeploy')
	
print "Connecting to the server ..."
connect('weblogic','weblogic','t3://localhost:7001')
edit()
startEdit()

servermb=getMBean("Servers/AdminServer")
if servermb is None:
    print 'servermb Value is Null'

else:
	createJMS()
   
try:
    save()
    activate(block="true")
    print "script returns SUCCESS"   
except:
    print "Error while trying to save and/or activate!!!"
    dumpStack()