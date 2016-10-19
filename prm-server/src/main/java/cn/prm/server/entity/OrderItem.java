package cn.prm.server.entity;

import java.io.Serializable;

public class OrderItem extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1505361954151572566L;
	
	private String orderId;
	private String passengerId;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
	
}
