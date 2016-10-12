/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package com.alsb.security;

import java.io.File;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import weblogic.security.SSL.TrustManager;
import weblogic.security.utils.KeyStoreUtils;

public class ClientTrustManager implements TrustManager {
	KeyStore keyStore = null;

	public ClientTrustManager(String keystoreFileName) {
		// Open the client keystore
		File clientKeyStoreFile = new File(keystoreFileName);
		keyStore = KeyStoreUtils.load(clientKeyStoreFile, "storepassword",
				"JKS");
	}

	/**
	 * This callback method is required by the TrustManager interface. Here
	 * is where we do the work to validate the certificate that is returned
	 * to the client from the web service server.
	 */
	public boolean certificateCallback(X509Certificate[] chain, int validateErr) {
		boolean returnVal = false; // Default to validation failure
	
		if(chain == null)
			return false;
		
		// Check each certificate in the chain
		for (int i = 0; i < chain.length; i++) {
			try {
				// Check the validity of each certificate
				// and get its public key
				X509Certificate peerCert = chain[i];
				PublicKey key = peerCert.getPublicKey();

				// Check each alias in the client keystore
				// against each key in the certificate chain.
				Enumeration<String> aliasEnum = keyStore.aliases();
				while (aliasEnum.hasMoreElements() && !returnVal) {
					String alias = aliasEnum.nextElement();
					Certificate cert = keyStore.getCertificate(alias);
					boolean currentCertIsGood = true;
					try {
						cert.verify(key);
					} catch (SignatureException ex) {
						// If the key signature does not match, then this
						// particular certificate is not valid
						currentCertIsGood = false;
					}
					if (currentCertIsGood) {
						// We found at least one good signature
						returnVal = true; 
					}
				}
			} catch (Exception e) {
				// Some unexpected problem occurred. Report
				// the error to the console
				// and fail the validation
				e.printStackTrace();
				returnVal = false;
			}
		}
	
		return returnVal;
	}
}
