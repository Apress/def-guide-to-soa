from java.io import FileInputStream
from java.io import FileOutputStream
from java.util import ArrayList
from java.util import Collections

from com.bea.wli.sb.util import EnvValueTypes
from com.bea.wli.config.env import EnvValueQuery;
from com.bea.wli.config import Ref
from com.bea.wli.config.customization import Customization
from com.bea.wli.config.customization import FindAndReplaceCustomization

import sys

#=======================================================================================
# Exports a project
#=======================================================================================
def exportAll(exportConfigFile):
    try:
        print "Loading export config from :", exportConfigFile
        exportConfigProp = loadProps(exportConfigFile)
        adminUrl = exportConfigProp.get("adminUrl")
        exportUser = exportConfigProp.get("exportUser")
        exportPasswd = exportConfigProp.get("exportPassword")

        exportJar = exportConfigProp.get("exportJar")
        customFile = exportConfigProp.get("customizationFile")

        passphrase = exportConfigProp.get("passphrase")
        project = exportConfigProp.get("project")

        connectToServer(exportUser, exportPasswd, adminUrl)

        ALSBConfigurationMBean = findService("ALSBConfiguration", "com.bea.wli.sb.management.configuration.ALSBConfigurationMBean")
        print "ALSBConfiguration MBean found"

        print project
        if project == None :
            ref = Ref.DOMAIN
            collection = Collections.singleton(ref)
            if passphrase == None :
                print "Export the config"
                theBytes = ALSBConfigurationMBean.export(collection, true, None)
            else :
                print "Export and encrypt the config"
                theBytes = ALSBConfigurationMBean.export(collection, true, passphrase)
        else :
            ref = Ref.makeProjectRef(project);
            print "Export the project", project
            collection = Collections.singleton(ref)
            theBytes = ALSBConfigurationMBean.exportProjects(collection, passphrase)

        aFile = File(exportJar)
        out = FileOutputStream(aFile)
        out.write(theBytes)
        out.close()
        print "ALSB Configuration file: "+ exportJar + " has been exported"

        if customFile != None:
            print collection
            customList = ArrayList()
            query = EnvValueQuery(None, Collections.singleton(EnvValueTypes.WORK_MANAGER), collection, false, None, false)
            customEnv = FindAndReplaceCustomization('Set the right Work Manager', query, 'Production System Work Manager')
            customList.add(customEnv)
            
            # Uncomment the next three lines to update the server and port to the 
            # alsb30_prod environment correctly
            #query = EnvValueQuery(None, Collections.singleton(EnvValueTypes.SERVICE_URI), collection, false, 'localhost:7001', false)
            #customEnv = FindAndReplaceCustomization('Update to the correct server and port number', query, 'localhost:7101')
            #customList.add(customEnv)
            
            print 'EnvValueCustomization created'
            print customList
            aFile = File(customFile)
            out = FileOutputStream(aFile)
            Customization.toXML(customList, out)
            out.close()

        print "ALSB Customization file: "+ customFile + " has been created"
    except:
        raise

#=======================================================================================
# Utility function to load properties from a config file
#=======================================================================================

def loadProps(configPropFile):
    propInputStream = FileInputStream(configPropFile)
    configProps = Properties()
    configProps.load(propInputStream)
    return configProps

#=======================================================================================
# Connect to the Admin Server
#=======================================================================================

def connectToServer(username, password, url):
    connect(username, password, url)
    domainRuntime()


# EXPORT script init
try:
    exportAll(sys.argv[1])

except:
    print "Unexpected error: ", sys.exc_info()[0]
    dumpStack()
    raise