package cn.prm.server.entity;

import java.io.Serializable;

public class Address extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 265941801189830644L;
	
	private Boolean lastUse;
	
	public Boolean getLastUse() {
		return lastUse;
	}
	public void setLastUse(Boolean lastUse) {
		this.lastUse = lastUse;
	}
	
}
