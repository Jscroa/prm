package cn.prm.server.dto;

/**
 * @Title: BaseDto.java<br>
 * @Package: cn.prm.server.dto<br>
 * @Description: 作为一个base，或者错误信息返回<br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:32:27<br>
 * @version v1.0<br>
 */
public class BaseDto {
    private int    code;
    private String msg;

    /**
     * Title: <br>
     * Description: <br>
     */
    public BaseDto() {
        super();
    }

    /**
     * Title: <br>
     * Description: <br>
     * 
     * @param code
     */
    public BaseDto(int code) {
        super();
        this.code = code;
    }

    /**
     * Title: <br>
     * Description: <br>
     * 
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
     * @param code
     *            the code to set
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
     * @param msg
     *            the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

}
