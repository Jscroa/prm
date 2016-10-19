package cn.prm.server.dto;

/**
 * @Title: BaseDto.java
 * @Package: cn.prm.server.dto
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:32:27
 * @version v1.0
 */
public class BaseDto {
	private int code;
	private String msg;
	
	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p>  
	 */
	public BaseDto() {
		super();
	}

	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 * @param code
	 * @param msg 
	 */
	public BaseDto(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
