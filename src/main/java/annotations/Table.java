package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify table/collection name of the class. <br>
 * If this annotation isn't set, the class name is used as the table/collection
 * name
 * 
 * @author danielsantil
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {

	public String value();

}
