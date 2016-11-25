/**
 * 
 */
package cn.prm.server.bean;

import cn.prm.server.commons.FormLimitAnnotation.CharSupport;

/**
 * @Title: FormLimit.java<br>
 * @Package: cn.prm.server.bean<br>
 * @Description: form字段限制<br>
 * @author yyao<br>
 * @date 2016年10月20日 下午1:50:25<br>
 * @version v1.0<br>
 */
public class FormLimit {

    private int         minLength;
    private int         maxLength;
    private CharSupport charSupport;

    /**
     * @return the minLength
     */
    public int getMinLength() {
        return minLength;
    }

    /**
     * @param minLength
     *            the minLength to set
     */
    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    /**
     * @return the maxLength
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * @param maxLength
     *            the maxLength to set
     */
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * @return the charSupport
     */
    public CharSupport getCharSupport() {
        return charSupport;
    }

    /**
     * @param charSupport
     *            the charSupport to set
     */
    public void setCharSupport(CharSupport charSupport) {
        this.charSupport = charSupport;
    }

}
