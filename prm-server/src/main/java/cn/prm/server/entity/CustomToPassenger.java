package cn.prm.server.entity;

import java.io.Serializable;

public class CustomToPassenger extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7376494362859355051L;
	
	private String customId;
	private String passengerId;
	
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	public String getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
	
}
