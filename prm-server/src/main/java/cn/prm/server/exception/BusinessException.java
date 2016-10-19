package cn.prm.server.exception;

/**
 * @Title: BusinessException.java
 * @Package: cn.prm.server.exception
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:37:41
 * @version v1.0
 */
public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9122660397178348279L;

	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p>  
	 */
	public BusinessException() {
		super();
	}

	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace 
	 */
	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 * @param message
	 * @param cause 
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 * @param message 
	 */
	public BusinessException(String message) {
		super(message);
	}

	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 * @param cause 
	 */
	public BusinessException(Throwable cause) {
		super(cause);
	}

}
