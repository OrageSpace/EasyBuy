package cn.easybuy.service.order;
/**
  * 购物车业务逻辑接口
 * @author 青云 .ltd
 *
 */

import cn.easybuy.uitls.ShoppingCart;

public interface CartService {
	/**
	 * 实现购物车信息更新的方法
	 * @param id
	 * @param quantity
	 * @return 购物车对象
	 */
	public ShoppingCart modifyShoppingCart(int id,int quantity,ShoppingCart cart) throws Exception;
	
	/**
	  * 计算总金额的方法
	 * @param cart
	 * @return 购物车对象
	 * @throws Exception
	 */
	public ShoppingCart calculate(ShoppingCart cart) throws Exception;
}
