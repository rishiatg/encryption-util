package encryption.util.exception;

/**
 * @author rshrivastava
 *
 * Custom exception thrown by library in case of any exception occurs
 * 
 */
public class EncryptionException extends Exception{

	/**
	 * Serialization Id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public EncryptionException() {
	}
	
	public EncryptionException(Throwable e) {
		super();
		this.throwable = e;	
	}
	
	public EncryptionException(String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public EncryptionException(String errorCode, Throwable throwable) {
		super();
		this.errorCode = errorCode;
		this.throwable = throwable;
	}
	
	public EncryptionException(String errorCode,String errorMessage) {
		super();
		this.errorCode = errorCode;
	}
	
	private String errorCode;
	
	private String errorMessage;
	
	private Throwable throwable;
	
}
