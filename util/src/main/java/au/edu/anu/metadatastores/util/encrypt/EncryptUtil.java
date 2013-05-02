/*******************************************************************************
 * Australian National University Metadata Stores
 * Copyright (C) 2013  The Australian National University
 * 
 * This file is part of Australian National University Metadata Stores.
 * 
 * Australian National University Metadata Stores is free software: you
 * can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package au.edu.anu.metadatastores.util.encrypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.jasypt.properties.PropertyValueEncryptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EncryptUtils
 * 
 * The Australian National University
 * 
 * Encryption util class
 * 
 * @author Genevieve Turner
 *
 */
public class EncryptUtil {
	static final Logger LOGGER = LoggerFactory.getLogger(EncryptUtil.class);
	
	/**
	 * Decrypt a word.  This method is usually used for the hibernate configuration password
	 * 
	 * @param encryptedWord The word to be decrypted
	 * @return The decrypted word
	 */
	public static String decrypt(String encryptedWord) {
		SimplePBEConfig config = new SimplePBEConfig();
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations(1000);
		//Set the encryption password from either a java opt named 'app.pwd' or use 'DefaultPassword'
		String appPassword = System.getProperty("app.pwd");
		if (appPassword != null && appPassword.length() > 0) {
			config.setPassword(appPassword);
		}
		else {
			config.setPassword("DefaultPassword");
		}
		
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setConfig(config);
		encryptor.initialize();
		
		String decryptedWord = encryptedWord;
		if (encryptedWord.startsWith("ENC(") && encryptedWord.endsWith(")")) {
			decryptedWord = PropertyValueEncryptionUtils.decrypt(encryptedWord, encryptor);
		}
		return decryptedWord;
	}
}
