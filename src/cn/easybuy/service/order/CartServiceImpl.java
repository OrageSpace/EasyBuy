package cn.easybuy.service.order;

import cn.easybuy.uitls.ShoppingCart;
import cn.easybuy.uitls.ShoppingCartItem;

/**
 * 购物车业务逻辑接口实现类
 * @author 青云 .ltd
 *
 */
public class CartServiceImpl implements CartService{

	@Override
	public ShoppingCart modifyShoppingCart(int id, int quantity,ShoppingCart cart) throws Exception{

		for(ShoppingCartItem item:cart.getItems()) {//遍历找出id与需要修改数量的商品相同的对象
			if(item.getProduct().getId()==id) {
				if(quantity>0) {
					item.setQuantity(quantity);
				}else {
					//如果修改后的数量为0则移除商品
					cart.getItems().remove(item);
					break;
				}
			}
		}
		
		//更新购物车总金额
		cart=this.calculate(cart);
		
		return cart;
	}

	@Override
	public ShoppingCart calculate(ShoppingCart cart) throws Exception {
		double sumCost=0;
		
		//遍历购物车
		for(ShoppingCartItem item:cart.getItems()) {//遍历找出id与需要修改数量的商品相同的对象
			sumCost+=item.getCost();
		}
		
		cart.setSumCost(sumCost);
		
		return cart;
	}

}
