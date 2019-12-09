package encryption.util.crypto;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import encryption.util.annotation.Crypto;
import encryption.util.annotation.CryptoKeys;
import encryption.util.enums.CryptoType;
import encryption.util.exception.EncryptionException;

/**
 * @author rshrivastava
 *
 *	Interface with default static method as crypt  
 */
public interface Cryptography {
	
	/**
	 * Method for encrytion of the object with the @Crypto annotation on the fields
	 * Only the field annotated with the @Crypto will be used forencryption or decryptin
	 * 
	 * @param object
	 * @param type
	 * @throws EncryptionException
	 * 
	 */
	public static void crypt(Object object,CryptoType type) throws EncryptionException {
		Class cls = object.getClass();
		if (cls.isAnnotationPresent(CryptoKeys.class)) {
			CryptoKeys keys  =  (CryptoKeys) cls.getAnnotation(CryptoKeys.class);
			CryptoGraphyUtil.initializeKeys(keys.secretKey(),keys.initialVector());
		}
		Field[] fields = cls.getDeclaredFields();
		for(Field field : fields) {
			if (field.isAnnotationPresent(Crypto.class)) {
				PropertyDescriptor pd;
				try {
					pd = new PropertyDescriptor(field.getName(),cls);
					Object value = pd.getReadMethod().invoke(object);
					if (type.equals(CryptoType.ENCRYPT)) {
						Method encryptMehtod = CryptoGraphyUtil.class.getDeclaredMethod("encrypt", String.class);
						encryptMehtod.setAccessible(true);
						value = encryptMehtod.invoke(null,value.toString());
					}else if(type.equals(CryptoType.DECRYPT)) {
						Method decryptMehtod = CryptoGraphyUtil.class.getDeclaredMethod("decrypt", String.class);
						decryptMehtod.setAccessible(true);
						value = decryptMehtod.invoke(null,value.toString());
					}
					pd.getWriteMethod().invoke(object, value);
				} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException  | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
					throw new EncryptionException(e.getMessage()); 
				}
			}
		}
	}
}
