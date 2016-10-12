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
import java.security.KeyStore as jks
import java.util.regex as RE
import java.security

print "Connecting to the server ..."
connect('weblogic','weblogic','t3://localhost:7001')
edit()
startEdit()

servermb=getMBean("Servers/AdminServer")
if servermb is None:
    print 'servermb Value is Null'

else:
	pw=encrypt('keypassword', 'D:/domains/alsb30_book')
	print "keypassword encrypted into %s" % pw
	pw=encrypt('storepassword', 'D:/domains/alsb30_book')
	print "storepassword encrypted into %s" % pw
	pw=encrypt('alsbserverkey', 'D:/domains/alsb30_book')
	print "alsbserverkey encrypted into %s" % pw
	print "Setting up the keystore information"
	keystores=servermb.getKeyStores()
	print "Current keytore is: %s" % keystores
	servermb.setKeyStores('CustomIdentityAndCustomTrust')
	servermb.setCustomIdentityKeyStoreFileName('ALSBServerIdentity.jks')
	servermb.setCustomIdentityKeyStoreType('JKS')
	#servermb.set('CustomIdentityKeyStorePassPhraseEncrypted', 'password')
	setEncrypted('CustomIdentityKeyStorePassPhrase', 'CustomIdentityKeyStorePassPhrase_1193935170023', 'D:/domains/alsb30_book/Script1193934964083Config', 'D:/domains/alsb30_book/Script1193934964083Secret')
	servermb.setCustomTrustKeyStoreFileName('ALSBServerIdentity.jks')
	servermb.setCustomTrustKeyStoreType('JKS')
	servermb.setEncrypted('CustomTrustKeyStorePassPhrase', 'CustomTrustKeyStorePassPhrase_1193935170264', 'D:/domains/alsb30_book/Script1193934964083Config', 'D:/domains/alsb30_book/Script1193934964083Secret')
try:
    save()
    activate(block="true")
    print "script returns SUCCESS"   
except:
    print "Error while trying to save and/or activate!!!"
    dumpStack()