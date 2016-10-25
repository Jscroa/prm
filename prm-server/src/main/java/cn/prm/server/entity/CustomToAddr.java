package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: CustomToAddr.java<br>
 * @Package: cn.prm.server.entity<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:36:06<br>
 * @version v1.0<br>
 */
public class CustomToAddr extends BaseEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4554790492810101677L;

    private String            customId;
    private String            addrId;

    /**
     * @return the customId
     */
    public String getCustomId() {
        return customId;
    }

    /**
     * @param customId
     *            the customId to set
     */
    public void setCustomId(String customId) {
        this.customId = customId;
    }

    /**
     * @return the addrId
     */
    public String getAddrId() {
        return addrId;
    }

    /**
     * @param addrId
     *            the addrId to set
     */
    public void setAddrId(String addrId) {
        this.addrId = addrId;
    }
}
