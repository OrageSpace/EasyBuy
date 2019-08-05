package cn.easybuy.uitls;
/**
  * 订单详情信息Vo类
 * @author 青云 .ltd
 *
 */

import java.util.List;

import cn.easybuy.entity.OrderDetail;
import cn.easybuy.entity.Product;

public class OrderDetialsVo {
	private OrderDetail orderDetail;
	private Product product;
	
	public OrderDetail getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
