package cn.easybuy.entity;
/**
 * 订单详情实体类
 * @author 青云 .ltd
 *
 */
public class OrderDetail {
	private int id;//订单详情编号
	private int orderId;//订单编号
	private int productId;//商品编号
	private int quantity;//数量
	private double cost;//订单金额
	
	public int getId() {
		return id;
	}
	public int getOrderId() {
		return orderId;
	}
	public int getProductId() {
		return productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getCost() {
		return cost;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
}
