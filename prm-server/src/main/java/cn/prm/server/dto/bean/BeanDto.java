/**
 * 
 */
package cn.prm.server.dto.bean;

import cn.prm.server.commons.Constants.RESPONSE_CODE;
import cn.prm.server.dto.BaseDto;


/**
 * @Title: BeanDto.java<br>
 * @Package: cn.prm.server.dto.bean<br>
 * @Description: bean类的返回<br>
 * @author yyao<br>
 * @date 2016年11月10日 上午11:58:38<br>
 * @version v1.0<br>
 * @param <T>
 */
public class BeanDto<T> extends BaseDto {
    private T t;

    /** 
     * Title: <br>
     * Description: <br>
     * @param t 
     */
    public BeanDto(T t) {
        super(RESPONSE_CODE.CODE_SUCCESS);
        this.t = t;
    }

    /**
     * @return the t
     */
    public T getT() {
        return t;
    }

    /**
     * @param t the t to set
     */
    public void setT(T t) {
        this.t = t;
    }
    
}
