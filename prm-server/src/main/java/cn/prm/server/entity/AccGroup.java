package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: AccGroup.java
 * @Package: cn.prm.server.entity
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:33:36
 * @version v1.0
 */
public class AccGroup extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2397694915747959786L;
	
	private Boolean isPrivate;

	/**
	 * @return the isPrivate
	 */
	public Boolean getIsPrivate() {
		return isPrivate;
	}
	/**
	 * @param isPrivate the isPrivate to set
	 */
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
}
