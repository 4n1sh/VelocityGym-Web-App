package com.velocityGym.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utility class for password encryption and decryption using AES-GCM. This
 * class provides methods to securely store and retrieve passwords using
 * encryption and decryption with AES algorithm in GCM mode. It uses PBKDF2 for
 * password-based key derivation and employs a salt and nonce (IV) for added
 * security.
 */

public class PasswordUtil {

	private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
	private static final int TAG_LENGTH_BIT = 128; // must be one of {128, 120, 112, 104, 96}
	private static final int IV_LENGTH_BYTE = 12;
	private static final int SALT_LENGTH_BYTE = 16;
	private static final Charset UTF_8 = StandardCharsets.UTF_8;

	/**
	 * Generates a random nonce (number used once) of the specified length. Nonces
	 * are used to ensure that encrypted data is unique each time it is processed.
	 * 
	 * @param numBytes The length of the nonce in bytes.
	 * @return A byte array containing the generated nonce.
	 */

	public static byte[] getRandomNonce(int numBytes) {
		byte[] nonce = new byte[numBytes];
		new SecureRandom().nextBytes(nonce);
		return nonce;
	}

	/**
	 * Generates a new AES key of the specified size (in bits).
	 * 
	 * @param keysize The size of the key (e.g., 128, 192, or 256 bits).
	 * @return The generated AES secret key.
	 * @throws NoSuchAlgorithmException If the AES algorithm is not available.
	 */

	public static SecretKey getAESKey(int keysize) throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(keysize, SecureRandom.getInstanceStrong());
		return keyGen.generateKey();
	}

	/**
	 * Derives an AES key from a password and a salt using PBKDF2 with HMAC SHA-256.
	 * This method uses the password to generate a secure key suitable for AES
	 * encryption.
	 * 
	 * @param password The password used for key derivation.
	 * @param salt     The salt value used in the key derivation process.
	 * @return The derived AES key.
	 */

	public static SecretKey getAESKeyFromPassword(char[] password, byte[] salt) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
			SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
			return secret;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	/**
	 * Encrypts a password using AES encryption in GCM mode. The password is first
	 * derived into an AES key from the provided username using PBKDF2.
	 * 
	 * @param username The username, which is used to derive the AES key.
	 * @param password The password to be encrypted.
	 * @return The encrypted password in Base64-encoded format.
	 */

	public static String encrypt(String username, String password) {
		try {
			byte[] salt = getRandomNonce(SALT_LENGTH_BYTE);
			byte[] iv = getRandomNonce(IV_LENGTH_BYTE);

			SecretKey aesKeyFromPassword = getAESKeyFromPassword(username.toCharArray(), salt);

			Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
			cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

			byte[] cipherText = cipher.doFinal(password.getBytes());

			byte[] cipherTextWithIvSalt = ByteBuffer.allocate(iv.length + salt.length + cipherText.length).put(iv)
					.put(salt).put(cipherText).array();

			return Base64.getEncoder().encodeToString(cipherTextWithIvSalt);
		} catch (Exception ex) {
			Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	/**
	 * Decrypts an encrypted password using AES decryption in GCM mode. The method
	 * takes an encrypted password, extracts the salt and nonce (IV) used during
	 * encryption, and uses the username to derive the AES key for decryption.
	 * 
	 * @param encryptedPassword The encrypted password in Base64-encoded format.
	 * @param username          The username used to derive the AES key for
	 *                          decryption.
	 * @return The decrypted password as a string.
	 */

	public static String decrypt(String encryptedPassword, String username) {
		try {
			byte[] decode = Base64.getDecoder().decode(encryptedPassword.getBytes(UTF_8));

			ByteBuffer bb = ByteBuffer.wrap(decode);

			byte[] iv = new byte[IV_LENGTH_BYTE];
			bb.get(iv);

			byte[] salt = new byte[SALT_LENGTH_BYTE];
			bb.get(salt);

			byte[] cipherText = new byte[bb.remaining()];
			bb.get(cipherText);

			SecretKey aesKeyFromPassword = PasswordUtil.getAESKeyFromPassword(username.toCharArray(), salt);

			Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
			cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

			byte[] plainText = cipher.doFinal(cipherText);

			return new String(plainText, UTF_8);
		} catch (Exception ex) {
			Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
}
