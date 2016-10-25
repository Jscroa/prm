package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: Account.java<br>
 * @Package: cn.prm.server.entity<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:33:51<br>
 * @version v1.0<br>
 */
public class Account extends BaseEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2962316124468998852L;

    private String            acc;
    private String            pwd;
    private String            phone;
    private String            email;
    private Boolean           sex;
    private Integer           age;

    /**
     * @return the acc
     */
    public String getAcc() {
        return acc;
    }

    /**
     * @param acc
     *            the acc to set
     */
    public void setAcc(String acc) {
        this.acc = acc;
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd
     *            the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
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
     * @return the sex
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     *            the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }

}
