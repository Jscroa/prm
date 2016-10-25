package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: Address.java<br>
 * @Package: cn.prm.server.entity<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:34:44<br>
 * @version v1.0<br>
 */
public class Address extends BaseEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 265941801189830644L;

    private Boolean           lastUse;

    /**
     * @return the lastUse
     */
    public Boolean getLastUse() {
        return lastUse;
    }

    /**
     * @param lastUse
     *            the lastUse to set
     */
    public void setLastUse(Boolean lastUse) {
        this.lastUse = lastUse;
    }
}
