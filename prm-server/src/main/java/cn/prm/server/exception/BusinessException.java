package cn.prm.server.exception;

/**
 * @Title: BusinessException.java<br>
 * @Package: cn.prm.server.exception<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:37:41<br>
 * @version v1.0<br>
 */
public class BusinessException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 9122660397178348279L;

    /**
     * Title: <br>
     * Description: <br>
     */
    public BusinessException() {
        super();
    }

    /**
     * Title: <br>
     * Description: <br>
     * 
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Title: <br>
     * Description: <br>
     * 
     * @param message
     * @param cause
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Title: <br>
     * Description: <br>
     * 
     * @param message
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Title: <br>
     * Description: <br>
     * 
     * @param cause
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }

}
