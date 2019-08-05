package cn.easybuy.dao.order;

import java.util.List;

import cn.easybuy.entity.Order;
import cn.easybuy.parames.OrderParames;

/**
 * 订单数据访问接口
 * @author 青云 .ltd
 *
 */
public interface OrderDao {
	/**
	 * 添加订单信息的方法
	 * @return 受影响行数
	 * @throws Exception
	 */
	public int addOrderInfo(Order order)throws Exception;
	
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
