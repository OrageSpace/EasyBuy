package cn.easybuy.service.order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.order.OrderDetailsDao;
import cn.easybuy.dao.order.OrderDetailsDaoImpl;
import cn.easybuy.entity.OrderDetail;
import cn.easybuy.parames.OrderDetailsParames;
import cn.easybuy.uitls.DataBaseUtil;

public class OrderDetailServiceImpl implements OrderDetailService {
	
	private Connection conn=null;//数据库连接对象
	private OrderDetailsDao orderDetailDao=null;

	@Override
	public int getOrderDetailsCountByOrderId(int orderId) {
		int count=0;
		
		try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			//实例化订单详情信息数据访问类对象
			orderDetailDao=new OrderDetailsDaoImpl(conn);
			
			count=orderDetailDao.getOrderDetailsCountByOrderId(orderId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//在业务完成后统一关闭数据库连接
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return count;
	}

	@Override
	public List<OrderDetail> queryOrderDetails(OrderDetailsParames params) {
		List<OrderDetail> orderDetails=new ArrayList<OrderDetail>();
		
		try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			//实例化订单详情信息数据访问类对象
			orderDetailDao=new OrderDetailsDaoImpl(conn);
			
			orderDetails=orderDetailDao.queryOrderDetails(params);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//在业务完成后统一关闭数据库连接
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return orderDetails;
	}

}
