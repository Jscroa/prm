package cn.prm.server.exception;

/**
 * session异常
 * @author yyao
 *
 */
public class SessionException extends Exception{

	public SessionException() {
		super();
	}

	public SessionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SessionException(String message, Throwable cause) {
		super(message, cause);
	}

	public SessionException(String message) {
		super(message);
	}

	public SessionException(Throwable cause) {
		super(cause);
	}

}
