package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: CustomToPassenger.java
 * @Package: cn.prm.server.entity
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:36:31
 * @version v1.0
 */
public class CustomToPassenger extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7376494362859355051L;
	
	private String customId;
	private String passengerId;
	
	/**
	 * @return the customId
	 */
	public String getCustomId() {
		return customId;
	}
	/**
	 * @param customId the customId to set
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
	 * @param passengerId the passengerId to set
	 */
	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
}
