package encryption.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author rshrivastava
 *
 *	Annotation to be used at class leve to identify the secret key and initial vecto for encryption
 *	else the defalut keys will be used.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CryptoKeys {

	public String secretKey() default "" ;
	
	public String initialVector() default "";
}
