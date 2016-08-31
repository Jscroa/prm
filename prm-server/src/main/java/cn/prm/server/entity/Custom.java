package cn.prm.server.entity;

import java.io.Serializable;
import java.sql.Date;

public class Custom extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4800415409041691492L;
	
	private Boolean sex;
	private Date birthday;
	
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
