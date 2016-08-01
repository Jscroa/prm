package cn.prm.server.entity;

import java.io.Serializable;

public class Custom extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4800415409041691492L;
	
	private Boolean sex;
	private Integer age;
	
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
