package cn.easybuy.service.order;

import java.util.List;

import cn.easybuy.entity.Order;
import cn.easybuy.entity.User;
import cn.easybuy.parames.OrderParames;
import cn.easybuy.uitls.ShoppingCart;

/**
 * 订单信息业务逻辑接口
 * @author 青云 .ltd
 *
 */
public interface OrderService {
	/**
	 * 处理订单信息的方法
	 * @param user 登录的用户
	 * @param address 订单的收货地址
	 * @param cart 购物车对象
	 * @return 订单对象
	 * @throws Exception
	 */
	public Order payShoppingCart(User user,String address,ShoppingCart cart)throws Exception;

	/**
	  * 获取指定用户所有订单信息的方法
	 * @param prames
	 * @return
	 * @throws Exception
	 */
	public List<Order> getAllOrderFromUser(OrderParames prames)throws Exception;
	
	/**
	 * 获取指定用户订单总记录数
	 * @param userId
	 * @return 订单总记录数
	 * @throws Exception
	 */
	public int getOrderCountFromUser(int userId)throws Exception;
}
