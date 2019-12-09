package encryption.util.crypto;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import encryption.util.exception.EncryptionException;

class CryptoGraphyUtil {

	private static String INITIAL_VECTOR = "0123456789123456";
	private static String SECRET_KEY = "RishiShrivastava";

	/**
	 *  Method to change the value of keys if user enter else use the default keys
	 * 
	 * @param secretKey
	 * @param initialVector
	 */
	public static void  initializeKeys(String secretKey,String initialVector) {
		if (!secretKey.equals(""))
			SECRET_KEY = secretKey;
		if (!initialVector.equals(""))
			INITIAL_VECTOR = initialVector;
	}
	
	
	/**
	 * @param input
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static String md5(final String input) throws NoSuchAlgorithmException {
        final MessageDigest md = MessageDigest.getInstance("MD5");
        final byte[] messageDigest = md.digest(input.getBytes());
        final BigInteger number = new BigInteger(1, messageDigest);
        return String.format("%032x", number);
    }

	/**
	 * Initializing cipher
	 * 
	 * @param mode
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 */
	private static Cipher initCipher(final int mode)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        final SecretKeySpec skeySpec = new SecretKeySpec(md5(SECRET_KEY).getBytes(), "AES");
        final IvParameterSpec initialVector = new IvParameterSpec(INITIAL_VECTOR.getBytes());
        final Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        cipher.init(mode, skeySpec, initialVector);
        return cipher;
    }
	
    /**
     * Mehtod to encrypt the string
     * 
     * @param dataToEncrypt
     * @return
     * @throws EncryptionException
     */
    private static String encrypt(final String dataToEncrypt) throws EncryptionException {
        String encryptedData = null;
        try {
            // Initialize the cipher
            final Cipher cipher = initCipher(Cipher.ENCRYPT_MODE);
            // Encrypt the data
            final byte[] encryptedByteArray = cipher.doFinal(dataToEncrypt.getBytes());
            // Encode using Base64
            encryptedData = Base64.getEncoder().encodeToString(encryptedByteArray);
        } catch (Exception e) {
        	throw new EncryptionException(e);
        }
        return encryptedData;
    }

    /**
     * Method to decrypt the string 
     * 
     * @param encryptedData
     * @return
     * @throws EncryptionException
     */
    private static String decrypt(final String encryptedData) throws EncryptionException {
        String decryptedData = null;
        try {
            // Initialize the cipher
            final Cipher cipher = initCipher(Cipher.DECRYPT_MODE);
            // Decode using Base64
            final byte[] encryptedByteArray = Base64.getDecoder().decode(encryptedData);
            // Decrypt the data
            final byte[] decryptedByteArray = cipher.doFinal(encryptedByteArray);
            decryptedData = new String(decryptedByteArray, "UTF8");
        }  catch (Exception e) {
        	throw new EncryptionException(e);
        }
        return decryptedData;
    }
}
