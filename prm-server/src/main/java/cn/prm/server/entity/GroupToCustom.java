package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: GroupToCustom.java<br>
 * @Package: cn.prm.server.entity<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:36:45<br>
 * @version v1.0<br>
 */
public class GroupToCustom extends BaseEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1067465757415899549L;

    private String            groupId;
    private String            customId;

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
}
