/**
 * 
 */
package cn.prm.server.dto.bean;

/**
 * @Title: AddressDto.java<br>
 * @Package: cn.prm.server.dto<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月3日 下午5:04:19<br>
 * @version v1.0<br>
 */
public class AddressDto {

    private String id;
    private String addr;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the addr
     */
    public String getAddr() {
        return addr;
    }

    /**
     * @param addr
     *            the addr to set
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }
}
