package cn.easybuy.entity;

import java.util.Date;

/**
 * 用户地址实体类
 * @author 青云 .ltd
 *
 */
public class UserAddress {
	private int id;//用户地址编号
	private String address;//地址名称
	private Date createTime;//创建时间
	private int userId;//用户主键Id
	private int isDefault;//是否存在默认地址
	private String remark;//备注
	
	public int getId() {
		return id;
	}
	public String getAddress() {
		return address;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public int getUserId() {
		return userId;
	}
	public int getIsDefault() {
		return isDefault;
	}
	public String getRemark() {
		return remark;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
