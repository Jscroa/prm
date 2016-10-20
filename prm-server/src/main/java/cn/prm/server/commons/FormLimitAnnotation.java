/**
 * 
 */
package cn.prm.server.commons;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import cn.prm.server.bean.FormLimit;

/**
 * @Title: FormLimitAnnotation.java
 * @Package: cn.prm.server.commons
 * @Description:
 * @author yyao
 * @date 2016年10月20日 上午10:31:09
 * @version v1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormLimitAnnotation {
	
	/**
	 * @Title: FormLimitAnnotation.java
	 * @Package: cn.prm.server.commons
	 * @Description:
	 * @author yyao
	 * @date 2016年10月20日 上午11:26:29
	 * @version v1.0
	 */
	public static class FormFieldHelper{
		/**
		 * 
		 * @Title: getValues 
		 * @Description: 
		 * @param className
		 * @return
		 * @throws SecurityException
		 * @throws ClassNotFoundException
		 * @throws NoSuchMethodException
		 * @throws IllegalAccessException
		 * @throws IllegalArgumentException
		 * @throws InvocationTargetException
		 * @throws
		 */
		public static Map<Field, FormLimit> getValues(String className) throws SecurityException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
			Field[] fields = Class.forName(className).getDeclaredFields();
			Map<Field, FormLimit> map = new HashMap<>();
			for (Field field : fields) {
				if(field.isAnnotationPresent(FormLimitAnnotation.class)){
					Annotation anno = field.getAnnotation(FormLimitAnnotation.class);
					Method minLengthMethod = anno.getClass().getDeclaredMethod("minLength", null);
					Method maxLengthMethod = anno.getClass().getDeclaredMethod("maxLength", null);
					Method charCheckMethod = anno.getClass().getDeclaredMethod("charCheck", null);
					
					int minLength = (int) minLengthMethod.invoke(anno, null);
					int maxLength = (int) maxLengthMethod.invoke(anno, null);
					CharSupport charCheck = (CharSupport) charCheckMethod.invoke(anno, null);
					System.out.println(field.getName() + "-> ");
					System.out.println("minLength: "+minLength);
					System.out.println("maxLength: "+maxLength);
					System.out.println("charCheck: "+charCheck.name());
					FormLimit formLimit = new FormLimit();
					formLimit.setMinLength(minLength);
					formLimit.setMaxLength(maxLength);
					formLimit.setCharSupport(charCheck);
					map.put(field, formLimit);
				}
			}
			return map;
		}
	}
	
	/**
	 * @Title: FormLimitAnnotation.java
	 * @Package: cn.prm.server.commons
	 * @Description:
	 * @author yyao
	 * @date 2016年10月20日 上午11:12:09
	 * @version v1.0
	 */
	public static enum CharSupport{
		
		/**
		 * @field OnlyLetter : 只有字母
		 */
		OnlyLetter("[a-zA-Z]*"),
		/**
		 * @field OnlyNumber : 只有数字字符
		 */
		OnlyNumber("[0-9]*"),
		/**
		 * @field LetterAndNumber : 字母加数字
		 */
		LetterAndNumber("[a-zA-Z0-9]*"),
		/**
		 * @field NaturalNumber : 自然数(正整数+0)
		 */
		NaturalNumber("^[1-9]\\d*|0$"),
		/**
		 * @field Password : 密码
		 */
		Password("^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{6,22}$"),
		/**
		 * @field AllChar : 所有字符
		 */
		AllChar(".*");
		
		String regex;

		/** 
		 * <p>Title: </p> 
		 * <p>Description: </p> 
		 * @param regex 
		 */
		private CharSupport(String regex) {
			this.regex = regex;
		}
		
	}
	
	/**
	 * 
	 * @Title: minLength 
	 * @Description: 最小长度
	 * @return <0,无限制
	 * @throws
	 */
	int minLength() default -1;
	
	/**
	 * 
	 * @Title: maxLength 
	 * @Description: 最大长度
	 * @return <0,无限制
	 * @throws
	 */
	int maxLength() default -1;
	
	/**
	 * 
	 * @Title: charCheck 
	 * @Description: 字符检测
	 * @return
	 * @throws
	 */
	CharSupport charCheck() default CharSupport.AllChar;
	
}
