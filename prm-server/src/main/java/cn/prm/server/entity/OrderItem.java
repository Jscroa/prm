package cn.prm.server.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Title: OrderItem.java<br>
 * @Package: cn.prm.server.entity<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:37:06<br>
 * @version v1.0<br>
 */
public class OrderItem extends BaseEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1505361954151572566L;

    private String            orderId;
    private String            passengerId;
    private String            addrFrom;
    private String            addrTo;
    private Integer           tripType;
    private Timestamp         goTime;
    private Timestamp         returnTime;
    private Double            price;

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
     * @return the passengerId
     */
    public String getPassengerId() {
        return passengerId;
    }

    /**
     * @param passengerId
     *            the passengerId to set
     */
    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    /**
     * @return the addrFrom
     */
    public String getAddrFrom() {
        return addrFrom;
    }

    /**
     * @param addrFrom
     *            the addrFrom to set
     */
    public void setAddrFrom(String addrFrom) {
        this.addrFrom = addrFrom;
    }

    /**
     * @return the addrTo
     */
    public String getAddrTo() {
        return addrTo;
    }

    /**
     * @param addrTo
     *            the addrTo to set
     */
    public void setAddrTo(String addrTo) {
        this.addrTo = addrTo;
    }

    /**
     * @return the tripType
     */
    public Integer getTripType() {
        return tripType;
    }

    /**
     * @param tripType
     *            the tripType to set
     */
    public void setTripType(Integer tripType) {
        this.tripType = tripType;
    }

    /**
     * @return the goTime
     */
    public Timestamp getGoTime() {
        return goTime;
    }

    /**
     * @param goTime
     *            the goTime to set
     */
    public void setGoTime(Timestamp goTime) {
        this.goTime = goTime;
    }

    /**
     * @return the returnTime
     */
    public Timestamp getReturnTime() {
        return returnTime;
    }

    /**
     * @param returnTime
     *            the returnTime to set
     */
    public void setReturnTime(Timestamp returnTime) {
        this.returnTime = returnTime;
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
