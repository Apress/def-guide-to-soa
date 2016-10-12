# This example script connects WLST to the Admin Examples Server
# * starts an edit session
# * removes a JMS system resource module.
import sys
from java.lang import System
    
print 'Deleting the system resources ....'
connect('weblogic','weblogic','t3://localhost:7001')
edit()
startEdit()
jmsMySystemResource = delete("WebServiceResources","JMSSystemResource") 
jmsMyServer1 = delete("WebServiceJMSServer","JMSServer") 
save()
activate(block="true")
print 'System Resource removed ...'
disconnect()