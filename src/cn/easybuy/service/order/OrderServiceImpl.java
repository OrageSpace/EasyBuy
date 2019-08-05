package cn.easybuy.service.order;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import cn.easybuy.dao.order.OrderDao;
import cn.easybuy.dao.order.OrderDaoImpl;
import cn.easybuy.dao.order.OrderDetailsDao;
import cn.easybuy.dao.order.OrderDetailsDaoImpl;
import cn.easybuy.dao.product.ProductDao;
import cn.easybuy.dao.product.ProductDaoImpl;
import cn.easybuy.entity.Order;
import cn.easybuy.entity.OrderDetail;
import cn.easybuy.entity.User;
import cn.easybuy.parames.OrderParames;
import cn.easybuy.uitls.DataBaseUtil;
import cn.easybuy.uitls.ShoppingCart;
import cn.easybuy.uitls.ShoppingCartItem;
import cn.easybuy.uitls.StringUtils;

public class OrderServiceImpl implements OrderService {
	private Connection conn;//数据库连接对象
	private OrderDao orderDao;
	private OrderDetailsDao orderDetailsDao;
	private ProductDao productDao;

	@Override
	public Order payShoppingCart(User user, String address, ShoppingCart cart)
			throws Exception {
		Order order=new Order();
		
		try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			orderDao=new OrderDaoImpl(conn);
			orderDetailsDao=new OrderDetailsDaoImpl(conn);
			productDao=new ProductDaoImpl(conn);
			//关闭自动事务提交并开启一个事务 
			conn.setAutoCommit(false);
			
			order.setCost(cart.getSumCost());
			order.setCreateTime(new Date());
			order.setLoginName(user.getLoginName());
			order.setSerialNumber(StringUtils.getRandomUUID());
			order.setUserAddress(address);
			order.setUserId(user.getId());
			//生成订单
			order.setId(orderDao.addOrderInfo(order));
			
			//创建订单详情信息
			for (ShoppingCartItem item : cart.getItems()) {
				OrderDetail orderDetail=new OrderDetail();
				orderDetail.setCost(item.getCost());
				orderDetail.setOrderId(order.getId());
				orderDetail.setProductId(item.getProduct().getId());
				orderDetail.setQuantity(item.getQuantity());
				
				orderDetailsDao.addOrderDetailsInfo(orderDetail);
				
				//更新商品库存信息
				productDao.updateProductInfo(item.getProduct().getId(), item.getQuantity());
			}
			
			//所有命令如果都执行成功 则提交事务
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(conn!=null) {//如果命令执行错误 则事务回滚 保证数据的完整性
				conn.rollback();
			}
			
			return null;
		}finally {
			//业务完成后统一关闭数据库连接
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return order;
	}

	@Override
	public List<Order> getAllOrderFromUser(OrderParames prames) throws Exception {
		List<Order> orders=null;
		
		try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			//实例化订单信息数据访问对象
			orderDao=new OrderDaoImpl(conn);
			
			orders=orderDao.getAllOrderFromUser(prames);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//业务完成后统一关闭数据库连接
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return orders;
	}

	@Override
	public int getOrderCountFromUser(int userId) throws Exception {
		int count=0;
		
		try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			//实例化订单信息数据访问对象
			orderDao=new OrderDaoImpl(conn);
			
			count=orderDao.getOrderCountFromUser(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//业务完成后统一关闭数据库连接
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return count;
	}
}
