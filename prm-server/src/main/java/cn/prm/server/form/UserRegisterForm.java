package cn.prm.server.form;

import cn.prm.server.commons.BaseForm;
import cn.prm.server.commons.FormLimitAnnotation;
import cn.prm.server.commons.FormLimitAnnotation.CharSupport;

/**
 * @Title: UserRegisterForm.java<br>
 * @Package: cn.prm.server.form<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:39:12<br>
 * @version v1.0<br>
 */
public class UserRegisterForm extends BaseForm {

    @FormLimitAnnotation(minLength = 2, maxLength = 20, charCheck = CharSupport.AllChar)
    private String userName;
    @FormLimitAnnotation(minLength = 2, maxLength = 20, charCheck = CharSupport.Email)
    private String email;
    @FormLimitAnnotation(minLength = 2, maxLength = 20, charCheck = CharSupport.Password)
    private String password;
    @FormLimitAnnotation(minLength = 2, maxLength = 20, charCheck = CharSupport.OnlyNumber)
    private String phone;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     *            the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
