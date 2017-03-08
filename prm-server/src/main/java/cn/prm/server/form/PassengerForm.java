/**
 * 
 */
package cn.prm.server.form;

import cn.prm.server.commons.BaseForm;
import cn.prm.server.commons.FormLimitAnnotation;
import cn.prm.server.commons.FormLimitAnnotation.CharSupport;

/**
 * @Title: PassengerForm.java<br>
 * @Package: cn.prm.server.form<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2017年3月8日 上午10:59:22<br>
 * @version v1.0<br>
 */
public class PassengerForm extends BaseForm {

    @FormLimitAnnotation(charCheck = CharSupport.LetterAndNumber)
    private String customId;
    @FormLimitAnnotation(minLength = 2, maxLength = 20, charCheck = CharSupport.AllChar)
    private String  name;
    @FormLimitAnnotation(minLength = 18, maxLength = 18, charCheck = CharSupport.IdCard)
    private String idCard;
    private Boolean sex;
    private String countryId;
    
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
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the idCard
     */
    public String getIdCard() {
        return idCard;
    }
    /**
     * @param idCard the idCard to set
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
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
     * @return the countryId
     */
    public String getCountryId() {
        return countryId;
    }
    /**
     * @param countryId the countryId to set
     */
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
    
}
