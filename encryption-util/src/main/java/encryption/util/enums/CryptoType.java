package encryption.util.enums;

/**
 * @author rshrivastava
 *
 * Enum for the type of operation be it encryption or decryption
 */
public enum CryptoType {

	ENCRYPT("encrypt"),DECRYPT("decrypt");
	
	String value;
	
	CryptoType(String value){
		this.value  = value;
	}
	
	public String value() {
		return this.value;
	}
}
