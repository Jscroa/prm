package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: Address.java
 * @Package: cn.prm.server.entity
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:34:44
 * @version v1.0
 */
public class Address extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 265941801189830644L;
	
	private Boolean lastUse;

	/**
	 * @return the lastUse
	 */
	public Boolean getLastUse() {
		return lastUse;
	}
	/**
	 * @param lastUse the lastUse to set
	 */
	public void setLastUse(Boolean lastUse) {
		this.lastUse = lastUse;
	}
}
