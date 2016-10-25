package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: AccToGroup.java<br>
 * @Package: cn.prm.server.entity<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:34:30<br>
 * @version v1.0<br>
 */
public class AccToGroup extends BaseEntity implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 767462832674120395L;

    private String            accId;
    private String            groupId;

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
     * @return the groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     *            the groupId to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

}
