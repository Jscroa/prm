package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: AccToGroup.java
 * @Package: cn.prm.server.entity
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:34:30
 * @version v1.0
 */
public class AccToGroup extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 767462832674120395L;
	
	private String accId;
	private String groupId;
	
	/**
	 * @return the accId
	 */
	public String getAccId() {
		return accId;
	}
	/**
	 * @param accId the accId to set
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
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
}
