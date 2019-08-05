package cn.easybuy.service.order;

import java.util.List;

import cn.easybuy.entity.OrderDetail;
import cn.easybuy.parames.OrderDetailsParames;

/**
 * 订单详情业务逻辑处理接口
 * @author 青云 .ltd
 *
 */
public interface OrderDetailService {
	/**
	  * 根据订单编号查询该订单中有多少商品数
	 * @return 商品数量
	 * @throws Exception
	 */
	public int getOrderDetailsCountByOrderId(int orderId);
	
	/**
	 * 查询订单详情的方法
	 * @param params
	 * @return 订单详情集合对象
	 * @throws Exception
	 */
	public List<OrderDetail> queryOrderDetails(OrderDetailsParames params);
}
