package encryption.util.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import encryption.util.crypto.Cryptography;
import encryption.util.enums.CryptoType;
import encryption.util.exception.EncryptionException;
import encryption.util.model.TestEncryption;

public class EncryptionUtilTest {

	static String testName = "C5O1siB8IRhLww==";
	static String testNotName ="HelloWorld";
	
	
    @Test
    public void testEncryptionUtil() throws EncryptionException {
    	TestEncryption testEncryption = new TestEncryption();
    	testEncryption.setName("HelloWorld");
    	Cryptography.crypt(testEncryption, CryptoType.ENCRYPT);
        assertEquals(testName, testEncryption.getName());
    }

    
    @Test
    public void testEncryption() throws EncryptionException {
    	TestEncryption testEncryption = new TestEncryption();
    	testEncryption.setName("HelloWorld");
    	Cryptography.crypt(testEncryption, CryptoType.ENCRYPT);
        assertNotEquals(testNotName, testEncryption.getName());
    }
    
   
    @Test
    public void testDecryption() throws EncryptionException {
    	TestEncryption testEncryption = new TestEncryption();
    	testEncryption.setName("C5O1siB8IRhLww==");
    	Cryptography.crypt(testEncryption, CryptoType.DECRYPT);
        assertEquals(testNotName, testEncryption.getName());
    }
	
    
    @Test
    public void testNotDecryption() throws EncryptionException {
    	TestEncryption testEncryption = new TestEncryption();
    	testEncryption.setName("C5O1siB8IRhLww==");
    	Cryptography.crypt(testEncryption, CryptoType.DECRYPT);
        assertNotEquals(testName, testEncryption.getName());
    }
}