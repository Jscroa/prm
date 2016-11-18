/**
 * 
 */
package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: Order.java<br>
 * @Package: cn.prm.server.entity<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月14日 下午3:48:14<br>
 * @version v1.0<br>
 */
public class Order extends BaseEntity implements Serializable {

    /**
     * @field serialVersionUID :
     */
    private static final long serialVersionUID = 2657322163792888266L;

    private String            customId;
    private String            orderType;
    private String            address;
    private Double            price;

    /**
     * @return the customId
     */
    public String getCustomId() {
        return customId;
    }

    /**
     * @param customId
     *            the customId to set
     */
    public void setCustomId(String customId) {
        this.customId = customId;
    }

    /**
     * @return the orderType
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * @param orderType
     *            the orderType to set
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

}
