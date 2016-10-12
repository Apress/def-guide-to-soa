"""
This script starts an edit session, creates two different JMS Servers, 
targets the jms servers to the server WLST is connected to and creates
jms topics, jms queues and jms templates in a JMS System module. The 
jms queues and topics are targeted using sub-deployments. 
"""

import sys
from java.lang import System


print "Starting the script ..."
connect('weblogic','weblogic','t3://localhost:7001')
edit()
startEdit()

servermb=getMBean("Servers/AdminServer")
if servermb is None:
    print 'servermb Value is Null'

else:
    jmsserver1mb = create('WebServiceJMSServer','JMSServer')
    jmsserver1mb.addTarget(servermb) 

    jmsMySystemResource = create("WebServiceResources","JMSSystemResource")
    jmsMySystemResource.addTarget(servermb) 
    
    subDep1mb = jmsMySystemResource.createSubDeployment('WebServiceJMSSubdeploy')
    subDep1mb.addTarget(jmsserver1mb)

    theJMSResource = jmsMySystemResource.getJMSResource()
    
    connfact1 = theJMSResource.createConnectionFactory('webServiceJMSConnectionFactory')
    connfact1.setJNDIName('jms.wsConnectionFactory')
    connfact1.setSubDeploymentName('WebServiceJMSSubdeploy')

    print "Creating MyQueue1..."
    jmsqueue1 = theJMSResource.createQueue('WebServiceQueue')
    jmsqueue1.setJNDIName('jms.WebServiceQueue')
    jmsqueue1.setSubDeploymentName('WebServiceJMSSubdeploy')
   
try:
    save()
    activate(block="true")
    print "script returns SUCCESS"   
except:
    print "Error while trying to save and/or activate!!!"
    dumpStack()