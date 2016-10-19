package cn.prm.server.form;

/**
 * @Title: UserLoginForm.java
 * @Package: cn.prm.server.form
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:39:01
 * @version v1.0
 */
public class UserLoginForm {
	private String email;
	private String password;
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
