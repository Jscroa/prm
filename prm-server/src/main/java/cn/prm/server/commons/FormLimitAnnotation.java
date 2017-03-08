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
 * @Title: FormLimitAnnotation.java<br>
 * @Package: cn.prm.server.commons<br>
 * @Description: 为form中需要检查的字段添加的注解<br>
 * @author yyao<br>
 * @date 2016年10月20日 上午10:31:09<br>
 * @version v1.0<br>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormLimitAnnotation {

    /**
     * @Title: FormLimitAnnotation.java<br>
     * @Package: cn.prm.server.commons<br>
     * @Description: <br>
     * @author yyao<br>
     * @date 2016年10月20日 上午11:26:29<br>
     * @version v1.0<br>
     */
    public static class FormFieldHelper {

        /**
         * @Title: getValues<br>
         * @Description: <br>
         * @param className
         * @return
         * @throws SecurityException
         * @throws ClassNotFoundException
         * @throws NoSuchMethodException
         * @throws IllegalAccessException
         * @throws IllegalArgumentException
         * @throws InvocationTargetException
         */
        public static Map<Field, FormLimit> getValues(String className)
                throws SecurityException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
                IllegalArgumentException, InvocationTargetException {
            Field[] fields = Class.forName(className).getDeclaredFields();
            Map<Field, FormLimit> map = new HashMap<>();
            for (Field field : fields) {
                if (field.isAnnotationPresent(FormLimitAnnotation.class)) {
                    Annotation anno = field.getAnnotation(FormLimitAnnotation.class);
                    Method minLengthMethod = anno.getClass().getDeclaredMethod("minLength");
                    Method maxLengthMethod = anno.getClass().getDeclaredMethod("maxLength");
                    Method charCheckMethod = anno.getClass().getDeclaredMethod("charCheck");

                    int minLength = (int) minLengthMethod.invoke(anno);
                    int maxLength = (int) maxLengthMethod.invoke(anno);
                    CharSupport charCheck = (CharSupport) charCheckMethod.invoke(anno);
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
     * @Title: FormLimitAnnotation.java<br>
     * @Package: cn.prm.server.commons<br>
     * @Description: <br>
     * @author yyao<br>
     * @date 2016年10月20日 上午11:12:09<br>
     * @version v1.0<br>
     */
    public static enum CharSupport {

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
         * @field Email : 邮箱
         */
        Email("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?"),
        /**
         * 身份证号
         */
        IdCard("^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$"),
        /**
         * @field AllChar : 所有字符
         */
        AllChar(".*");

        String regex;

        private CharSupport(String regex) {
            this.regex = regex;
        }

    }

    /**
     * @Title: minLength<br>
     * @Description: 最小长度<br>
     * @return <0,无限制
     */
    int minLength() default -1;

    /**
     * @Title: maxLength<br>
     * @Description: 最大长度<br>
     * @return <0,无限制
     */
    int maxLength() default -1;

    /**
     * @Title: charCheck<br>
     * @Description: 字符检测<br>
     * @return
     */
    CharSupport charCheck() default CharSupport.AllChar;

}
