package cn.prm.server.form;

import cn.prm.server.commons.BaseForm;
import cn.prm.server.commons.FormLimitAnnotation;
import cn.prm.server.commons.FormLimitAnnotation.CharSupport;

/**
 * @Title: CountryForm.java<br>
 * @Package: cn.prm.server.form<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:38:18<br>
 * @version v1.0<br>
 */
public class CountryForm extends BaseForm {

    @FormLimitAnnotation(minLength = 1, maxLength = 20, charCheck = CharSupport.AllChar)
    private String stdName;
    @FormLimitAnnotation(minLength = 1, maxLength = 20, charCheck = CharSupport.AllChar)
    private String enName;
    @FormLimitAnnotation(minLength = 1, maxLength = 20, charCheck = CharSupport.AllChar)
    private String cnName;
    private int    code;

    /**
     * @return the stdName
     */
    public String getStdName() {
        return stdName;
    }

    /**
     * @param stdName
     *            the stdName to set
     */
    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    /**
     * @return the enName
     */
    public String getEnName() {
        return enName;
    }

    /**
     * @param enName
     *            the enName to set
     */
    public void setEnName(String enName) {
        this.enName = enName;
    }

    /**
     * @return the cnName
     */
    public String getCnName() {
        return cnName;
    }

    /**
     * @param cnName
     *            the cnName to set
     */
    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }
}
