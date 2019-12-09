package encryption.util.model;

import encryption.util.annotation.Crypto;
import encryption.util.annotation.CryptoKeys;

//@CryptoKeys(secretKey = "TestForAnnotation",initialVector = "1010101010101010")
public class TestEncryption {

	@Crypto
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
