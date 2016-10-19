package cn.prm.server.entity;

import java.io.Serializable;

public class AccountToMessage extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8737896935501840635L;
	
	private String accId;
	private String msgType;
	private String content;
	private Boolean isRead;
	
	public String getAccId() {
		return accId;
	}
	public void setAccId(String accId) {
		this.accId = accId;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
	
}
