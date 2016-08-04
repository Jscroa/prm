package cn.prm.server.entity;

import java.io.Serializable;

public class GroupToCustom extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1067465757415899549L;
	
	private String groupId;
	private String customId;
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	
}
