/**
 * 
 */
package cn.prm.server.form;

import cn.prm.server.commons.BaseForm;
import cn.prm.server.commons.FormLimitAnnotation;
import cn.prm.server.commons.FormLimitAnnotation.CharSupport;

/**
 * @Title: AddressForm.java<br>
 * @Package: cn.prm.server.form<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2017年3月6日 下午6:08:52<br>
 * @version v1.0<br>
 */
public class AddressForm extends BaseForm {

    @FormLimitAnnotation(charCheck = CharSupport.LetterAndNumber)
    private String customId;
    @FormLimitAnnotation(minLength = 2, maxLength = 200, charCheck = CharSupport.AllChar)
    private String tip;
    @FormLimitAnnotation(minLength = 2, maxLength = 200, charCheck = CharSupport.AllChar)
    private String addr;
    
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
     * @return the tip
     */
    public String getTip() {
        return tip;
    }
    /**
     * @param tip the tip to set
     */
    public void setTip(String tip) {
        this.tip = tip;
    }
    /**
     * @return the addr
     */
    public String getAddr() {
        return addr;
    }
    /**
     * @param addr the addr to set
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }
    
}
