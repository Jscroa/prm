package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: Country.java
 * @Package: cn.prm.server.entity
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:35:36
 * @version v1.0
 */
public class Country extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3674228516713091945L;
	
	private String enName;
	private String cnName;
	
	/**
	 * @return the enName
	 */
	public String getEnName() {
		return enName;
	}
	/**
	 * @param enName the enName to set
	 */
	public void setEnName(String enName) {
		this.enName = enName;
	}
	/**
	 * @return the cnName
	 */
	public String getCnName() {
		return cnName;
	}
	/**
	 * @param cnName the cnName to set
	 */
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
}
