package cn.easybuy.uitls;

import java.util.ArrayList;
import java.util.List;

import cn.easybuy.entity.Product;

/**
 * 购物车实体类
 * @author 青云 .ltd
 *
 */
public class ShoppingCart {
	
	private List<ShoppingCartItem> items =new ArrayList<ShoppingCartItem>();
	private double sumCost;//总金额
	
	public List<ShoppingCartItem> getItems() {
		return items;
	}
	public void setItems(List<ShoppingCartItem> items) {
		this.items = items;
	}
	public double getSumCost() {
		return sumCost;
	}
	public void setSumCost(double sumCost) {
		this.sumCost = sumCost;
	}
	
	/**
	  * 添加商品数量的方法  
	  * 两种情况：  1.购物车中没有该商品  则新创建一个商品对象  
	 * 2.购物车中已存在该商品 在原有的数量上添加
	 * @param product
	 * @param quantity
	 * @return ajax请求JSON数据结果对象
	 */
	public ReturnResult addCartItem(Product product,Integer quantity) {
		ReturnResult returnResult=new ReturnResult();
		boolean flag=false;
		
		String message="操作成功，";
		
		if(product.getStock()>0&&quantity>product.getStock()) {
			quantity-=quantity;
			message="操作成功，由于添加数量大于商品库存数量，添加数量系统自动调整，";
		}else if(product.getStock()<=0) {
			return returnResult.returnFail("操作失败，商品库存不足！");
		}
		
		//遍历原有的购物车子项集合
		
		for (ShoppingCartItem shoppingCartItem : items) {
			if(product.getName().equals(shoppingCartItem.getProduct().getId())) {
				flag=true;//表示集合中有该元素
				
				shoppingCartItem.setQuantity(shoppingCartItem.getQuantity()+quantity);
				this.sumCost+=product.getPrice()*quantity;
			}
		}
		
		if(!flag) {
			ShoppingCartItem item=new ShoppingCartItem(product, quantity);
			this.items.add(item);
			this.sumCost+=product.getPrice()*quantity;
		}
		
		return returnResult.returnSuccess(null, message+"本次共添加的数量为<b style='color:red;'>"+quantity+"</b>件商品");
	}
	
	/**
	  * 删除购物车中的某一项
	 * @param index 商品对应索引
	 * @return 
	 */
	public ReturnResult removeCartItem(int index) {
		ReturnResult returnResult=new ReturnResult();
		
		if(index>items.size()-1) {
			return returnResult.returnFail("操作失败，请稍后重试或与系统管理员联系！");
		}
		
		//按索引删除购物车中的 某一项
		items.remove(index);
		
		return returnResult.returnSuccess();
	}
}
