package cn.prm.server.entity;

import java.io.Serializable;

public class Passenger extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7048816667574084801L;
	
	private String customId;
	private String countryId;
	private String idCard;
	
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
}
