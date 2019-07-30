package cn.easybuy.uitls;
/**
 * 购物车子项类对象
 * @author 青云 .ltd
 *
 */

import cn.easybuy.entity.Product;

public class ShoppingCartItem {
	
	private Product product;//商品对象
	private Integer quantity;//数量
	
	//构造函数
	public ShoppingCartItem(Product product,Integer quantity) {
		this.product=product;
		this.quantity=quantity;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
