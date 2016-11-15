/**
 * 
 */
package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: AccToTip.java<br>
 * @Package: cn.prm.server.entity<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月14日 下午3:46:17<br>
 * @version v1.0<br>
 */
public class AccToTip extends BaseEntity implements Serializable {

    /**
     * @field serialVersionUID :
     */
    private static final long serialVersionUID = -6808411408688292585L;

    private String            accId;
    private String            tipId;

    /**
     * @return the accId
     */
    public String getAccId() {
        return accId;
    }

    /**
     * @param accId
     *            the accId to set
     */
    public void setAccId(String accId) {
        this.accId = accId;
    }

    /**
     * @return the tipId
     */
    public String getTipId() {
        return tipId;
    }

    /**
     * @param tipId
     *            the tipId to set
     */
    public void setTipId(String tipId) {
        this.tipId = tipId;
    }

}
