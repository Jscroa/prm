/**
 * 
 */
package cn.prm.server.exception;

/**
 * @Title: PermissionException.java<br>
 * @Package: cn.prm.server.exception<br>
 * @Description: 权限不足异常<br>
 * @author yyao<br>
 * @date 2016年11月3日 下午1:59:07<br>
 * @version v1.0<br>
 */
public class PermissionException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 7789045656468150748L;

    /** 
     * Title: <br>
     * Description: <br> 
     */
    public PermissionException() {
        super();
    }

    /** 
     * Title: <br>
     * Description: <br>
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace 
     */
    public PermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /** 
     * Title: <br>
     * Description: <br>
     * @param message
     * @param cause 
     */
    public PermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    /** 
     * Title: <br>
     * Description: <br>
     * @param message 
     */
    public PermissionException(String message) {
        super(message);
    }

    /** 
     * Title: <br>
     * Description: <br>
     * @param cause 
     */
    public PermissionException(Throwable cause) {
        super(cause);
    }

}
