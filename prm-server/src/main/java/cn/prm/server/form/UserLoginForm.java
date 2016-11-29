package cn.prm.server.form;

import cn.prm.server.commons.BaseForm;
import cn.prm.server.commons.FormLimitAnnotation;
import cn.prm.server.commons.FormLimitAnnotation.CharSupport;

/**
 * @Title: UserLoginForm.java<br>
 * @Package: cn.prm.server.form<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:39:01<br>
 * @version v1.0<br>
 */
public class UserLoginForm extends BaseForm {
    
    @FormLimitAnnotation(minLength = 2, maxLength = 20, charCheck = CharSupport.Email)
    private String email;
    @FormLimitAnnotation(minLength = 2, maxLength = 20, charCheck = CharSupport.Password)
    private String password;

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
