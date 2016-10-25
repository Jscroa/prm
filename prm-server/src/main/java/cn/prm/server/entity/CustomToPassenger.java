package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: CustomToPassenger.java<br>
 * @Package: cn.prm.server.entity<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:36:31<br>
 * @version v1.0<br>
 */
public class CustomToPassenger extends BaseEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7376494362859355051L;

    private String            customId;
    private String            passengerId;

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
     * @return the passengerId
     */
    public String getPassengerId() {
        return passengerId;
    }

    /**
     * @param passengerId
     *            the passengerId to set
     */
    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }
}
