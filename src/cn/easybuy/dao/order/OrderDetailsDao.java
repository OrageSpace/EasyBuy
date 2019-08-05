package cn.easybuy.dao.order;

import java.util.List;

import cn.easybuy.entity.OrderDetail;
import cn.easybuy.parames.OrderDetailsParames;

/**
 * 订单详情数据访问接口
 * @author 青云 .ltd
 *
 */
public interface OrderDetailsDao {
	
	/**
	 * 添加商品详情信息的方法
	 * @param orderDetail 商品详情信息对象 
	 * @return 受影响行数
	 * @throws Exception
	 */
	public int addOrderDetailsInfo(OrderDetail orderDetail)throws Exception;
	
	/**
	  * 根据订单编号查询该订单中有多少商品数
	 * @return 商品数量
	 * @throws Exception
	 */
	public int getOrderDetailsCountByOrderId(int orderId)throws Exception;
	
	/**
	 * 查询订单详情的方法
	 * @param params
	 * @return 订单详情集合对象
	 * @throws Exception
	 */
	public List<OrderDetail> queryOrderDetails(OrderDetailsParames params)throws Exception;
}
