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
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }
    
    /**
     * @Title: Order.java<br>
     * @Package: cn.prm.server.entity<br>
     * @Description: <br>
     * @author yyao<br>
     * @date 2016年11月15日 下午3:48:01<br>
     * @version v1.0<br>
     */
    public static class OrderWithPay extends Order{

        /**
         * @field serialVersionUID : 
         */
        private static final long serialVersionUID = -7169685493308072886L;
        
        private Boolean isPay;

        /**
         * @return the isPay
         */
        public Boolean getIsPay() {
            return isPay;
        }

        /**
         * @param isPay the isPay to set
         */
        public void setIsPay(Boolean isPay) {
            this.isPay = isPay;
        }
        
    }

}
