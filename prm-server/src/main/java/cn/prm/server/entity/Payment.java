/**
 * 
 */
package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: Payment.java<br>
 * @Package: cn.prm.server.entity<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月14日 下午3:52:28<br>
 * @version v1.0<br>
 */
public class Payment extends BaseEntity implements Serializable {
    
    /**
     * @field serialVersionUID : 
     */
    private static final long serialVersionUID = 5349913108794746614L;
    
    private String orderId;
    private String drawee;
    private String payee;
    private Double price;

    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     *            the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the drawee
     */
    public String getDrawee() {
        return drawee;
    }

    /**
     * @param drawee
     *            the drawee to set
     */
    public void setDrawee(String drawee) {
        this.drawee = drawee;
    }

    /**
     * @return the payee
     */
    public String getPayee() {
        return payee;
    }

    /**
     * @param payee
     *            the payee to set
     */
    public void setPayee(String payee) {
        this.payee = payee;
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
