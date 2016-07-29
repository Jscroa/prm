package cn.prm.server.entity;

import java.io.Serializable;

public class Country extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3674228516713091945L;
	
	private String enName;
	private String cnName;

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

}
