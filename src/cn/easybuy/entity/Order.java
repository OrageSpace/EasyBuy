package cn.easybuy.entity;
/**
 * 订单实体类
 * @author 青云 .ltd
 *
 */

import java.util.Date;

public class Order {
	private int id;//订单编号
	private int userId;//用户ID
	private String loginName;//登录用户名
	private String userAddress;//用户地址
	private Date createTime;//创建时间
	private double cost;//金额
	private int status;//订单状态
	private String serialNumber;//订单编号
	
	public int getId() {
		return id;
	}
	public int getUserId() {
		return userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public double getCost() {
		return cost;
	}
	public int getStatus() {
		return status;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}
