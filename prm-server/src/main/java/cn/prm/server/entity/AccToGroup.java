package cn.prm.server.entity;

import java.io.Serializable;

public class AccToGroup extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 767462832674120395L;
	
	private String accId;
	private String groupId;

	public String getAccId() {
		return accId;
	}

	public void setAccId(String accId) {
		this.accId = accId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}
