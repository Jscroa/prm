/**
 * 
 */
package cn.prm.server.dto.bean;

/**
 * @Title: OrderDto.java<br>
 * @Package: cn.prm.server.dto.bean<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月15日 下午4:27:26<br>
 * @version v1.0<br>
 */
public class OrderDto {

    private String  id;
    private String  orderNum;
    private String  customId;
    private String  customName;
    private String  address;
    private String  addressStr;
    private String  orderTime;
    private Double  price;
    private Boolean isPay;

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
     * @return the orderNum
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * @param orderNum
     *            the orderNum to set
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

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
     * @return the customName
     */
    public String getCustomName() {
        return customName;
    }

    /**
     * @param customName
     *            the customName to set
     */
    public void setCustomName(String customName) {
        this.customName = customName;
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
     * @return the addressStr
     */
    public String getAddressStr() {
        return addressStr;
    }

    /**
     * @param addressStr
     *            the addressStr to set
     */
    public void setAddressStr(String addressStr) {
        this.addressStr = addressStr;
    }

    /**
     * @return the orderTime
     */
    public String getOrderTime() {
        return orderTime;
    }

    /**
     * @param orderTime
     *            the orderTime to set
     */
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
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

    /**
     * @return the isPay
     */
    public Boolean getIsPay() {
        return isPay;
    }

    /**
     * @param isPay
     *            the isPay to set
     */
    public void setIsPay(Boolean isPay) {
        this.isPay = isPay;
    }

}
