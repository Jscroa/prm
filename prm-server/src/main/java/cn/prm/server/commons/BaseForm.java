/**
 * 
 */
package cn.prm.server.commons;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.prm.server.bean.FormLimit;
import cn.prm.server.commons.FormLimitAnnotation.FormFieldHelper;
import cn.prm.server.exception.BusinessException;

/**
 * @Title: BaseForm.java<br>
 * @Package: cn.prm.server.commons<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月20日 上午11:17:21
 * @version v1.0<br>
 */
public abstract class BaseForm {

    /**
     * @Title: checkFields<br>
     * @Description: 检查form的字段长度<br>
     * @throws BusinessException
     */
    public void checkFields() throws BusinessException {
        try {
            Map<Field, FormLimit> map = FormFieldHelper.getValues(this.getClass().getName());
            for (Field field : map.keySet()) {
                FormLimit limit = map.get(field);
                field.setAccessible(true);
                String value = (String) field.get(this);
                if (value == null) {
                    if (limit.getMinLength() <= 0) {
                        continue;
                    }
                    else {
                        throw new BusinessException("字段[" + field.getName() + "]不能为空");
                    }
                }
                if (limit.getMinLength() > 0) {
                    if (value.length() < limit.getMinLength()) {
                        throw new BusinessException(
                                "字段[" + field.getName() + "]太短，必须大于" + limit.getMinLength() + "个字符");
                    }
                }
                if (limit.getMaxLength() > 0) {
                    if (value.length() > limit.getMaxLength()) {
                        throw new BusinessException(
                                "字段[" + field.getName() + "]太长，必须小于" + limit.getMaxLength() + "个字符");
                    }
                }
                Pattern pattern = Pattern.compile(limit.getCharSupport().regex);
                Matcher matcher = pattern.matcher(value);
                if (!matcher.matches()) {
                    throw new BusinessException("字段[" + field.getName() + "]不满足条件");
                }

            }
        }
        catch (SecurityException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }
}
