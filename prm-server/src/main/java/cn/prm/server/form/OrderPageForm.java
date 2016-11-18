/**
 * 
 */
package cn.prm.server.form;

import cn.prm.server.commons.FormLimitAnnotation;
import cn.prm.server.commons.FormLimitAnnotation.CharSupport;

/**
 * @Title: OrderPageForm.java<br>
 * @Package: cn.prm.server.form<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月17日 下午5:25:19<br>
 * @version v1.0<br>
 */
public class OrderPageForm extends PageBaseForm {
    
    @FormLimitAnnotation(minLength = 0, maxLength = 100, charCheck = CharSupport.OnlyLetter)
    private String s;

    /**
     * @return the s
     */
    public String getS() {
        return s;
    }

    /**
     * @param s the s to set
     */
    public void setS(String s) {
        this.s = s;
    }
    
}
