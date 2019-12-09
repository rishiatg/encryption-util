# encryption-util
Library for  Encryption and Decryption using reflection

This library is for encryting or decryption the particular field of any class based on the custom annotation that i have created.

@Crypto :- Use this anootation at the field of the bean if you want that field to be encrypted.

@CryptoKeys :-  Use this annotation to define your own secret key and initail vecotr to be used in encryptin or decryption.



@CryptoKeys(secretKey= "RishiShrivastava",initialVector = "1023451141141110")
public class YourClassName {

	@Crypto
	private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  
  }
  
  
  If you want to encryt your bean then just call the method with the parameter.
  
      		YourClass yourclass = new YourClass();
			yourclass.setName("Rishi");
			Cryptography.crypt(yourclass, CryptoType.ENCRYPT);
      
  To decytpt use :- CryptoType.DECRYPT in place of encrypt.
      
