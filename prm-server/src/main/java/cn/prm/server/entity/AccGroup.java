package cn.prm.server.entity;

import java.io.Serializable;

public class AccGroup extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2397694915747959786L;
	
	private Boolean isPrivate;

	public Boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

}
