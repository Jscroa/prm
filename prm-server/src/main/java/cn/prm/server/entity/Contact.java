package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: Contact.java<br>
 * @Package: cn.prm.server.entity<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:35:14<br>
 * @version v1.0<br>
 */
public class Contact extends BaseEntity implements Serializable {

    /**
     * @field serialVersionUID :
     */
    private static final long serialVersionUID = -7982505181077830181L;

    private String customId;
    private String            phone;
    private String            email;
    private String            qq;
    private String            weixin;

    /**
     * @return the customId
     */
    public String getCustomId() {
        return customId;
    }

    /**
     * @param customId the customId to set
     */
    public void setCustomId(String customId) {
        this.customId = customId;
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

}
