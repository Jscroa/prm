package cn.prm.server.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Title: Custom.java
 * @Package: cn.prm.server.entity
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:35:49
 * @version v1.0
 */
public class Custom extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4800415409041691492L;
	
	private Boolean sex;
	private Date birthday;
	
	/**
	 * @return the sex
	 */
	public Boolean getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
