package cn.prm.server.dto.bean;

/**
 * @Title: CustomDto.java<br>
 * @Package: cn.prm.server.dto<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:32:41<br>
 * @version v1.0<br>
 */
public class CustomDto {

    private String id;
    private String name;
    private Boolean sex;
    private String phone;
    private String email;
    private String weixin;
    private String qq;
    private String  birthday;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the sex
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
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
     * @return the weixin
     */
    public String getWeixin() {
        return weixin;
    }

    /**
     * @param weixin
     *            the weixin to set
     */
    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    /**
     * @return the qq
     */
    public String getQq() {
        return qq;
    }

    /**
     * @param qq
     *            the qq to set
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

}
