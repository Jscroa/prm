package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: CustomToContact.java
 * @Package: cn.prm.server.entity
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:36:18
 * @version v1.0
 */
public class CustomToContact extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3748014752887879829L;
	
	private String customId;
	private String contactId;
	
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
	 * @return the contactId
	 */
	public String getContactId() {
		return contactId;
	}
	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
}
