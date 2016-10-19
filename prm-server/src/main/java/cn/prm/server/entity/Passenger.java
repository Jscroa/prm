package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: Passenger.java
 * @Package: cn.prm.server.entity
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:37:24
 * @version v1.0
 */
public class Passenger extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7048816667574084801L;
	
	private String customId;
	private String countryId;
	private String idCard;
	
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
	 * @return the countryId
	 */
	public String getCountryId() {
		return countryId;
	}
	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	/**
	 * @return the idCard
	 */
	public String getIdCard() {
		return idCard;
	}
	/**
	 * @param idCard the idCard to set
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
}
