package cn.easybuy.dao.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.BaseDao;
import cn.easybuy.entity.OrderDetail;
import cn.easybuy.parames.OrderDetailsParames;
import cn.easybuy.uitls.DataBaseUtil;
import cn.easybuy.uitls.EmptyUtil;

/**
 * 
 * @author 青云 .ltd
 *
 */
public class OrderDetailsDaoImpl extends BaseDao implements OrderDetailsDao{

	public OrderDetailsDaoImpl(Connection conn) {
		super(conn);
	}

	@Override
	public int addOrderDetailsInfo(OrderDetail orderDetail) throws Exception {
		int result=0;
		
		String sql="INSERT INTO `easybuy_order_detail`(`orderId`,`productId`,`quantity`,`cost`) "
				+ "VALUES(?,?,?,?)";
		result=super.executeUpdate(sql, orderDetail.getOrderId(),orderDetail.getProductId(),orderDetail.getQuantity(),orderDetail.getCost());
		
		return result;
	}

	@Override
	public int getOrderDetailsCountByOrderId(int orderId)
			throws Exception {
		ResultSet rs=null;
		int count=0;
		
		try {
			String sql="SELECT COUNT(`id`) FROM `easybuy_order_detail` WHERE `orderId`=?";
			rs=super.executeQuery(sql, orderId);
			
			//获取第一行第一列的数据
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源 数据库连接需要在业务逻辑层关闭
			DataBaseUtil.closeAll(null, pstmt, rs);
		}
		
		return count;
	}

	@Override
	public List<OrderDetail> queryOrderDetails(OrderDetailsParames params)
			throws Exception {
		ResultSet rs=null;//结果集对象
		List<OrderDetail> orderDetails=new ArrayList<OrderDetail>();
		
		try {
			StringBuffer sql=new StringBuffer("SELECT `id`,`orderId`,`productId`,`quantity`,`cost` FROM `easybuy_order_detail` WHERE 1=1");
			
			List<Object> paramsArr=new ArrayList<Object>();
			
			if(EmptyUtil.isNotEmpty(params.getOrderId())) {
				sql.append(" AND `orderId`=?");
				paramsArr.add(params.getOrderId());
			}
			
			if(EmptyUtil.isNotEmpty(params.getSort())) {
				sql.append(" ORDER BY "+params.getSort()+" DESC");
			}
			
			if(params.isPage()) {
				sql.append(" LIMIT ?,?");
				paramsArr.add(params.getStartIndex());
				paramsArr.add(params.getPageSize());
			}
			
			rs=super.executeQuery(sql.toString(), paramsArr);
			
			//循环读取结果集中的数据
			while (rs.next()) {
				orderDetails.add(this.tableToClass(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源 数据库连接需要在业务逻辑层关闭
			DataBaseUtil.closeAll(null, pstmt, rs);
		}
		
		return orderDetails;
	}

	//解析结果集中订单详情对象的方法
	private OrderDetail tableToClass(ResultSet rs)throws Exception {
		OrderDetail orderDetail=new OrderDetail();
		
		orderDetail.setCost(rs.getDouble("cost"));
		orderDetail.setId(rs.getInt("id"));
		orderDetail.setOrderId(rs.getInt("orderId"));
		orderDetail.setProductId(rs.getInt("productId"));
		orderDetail.setQuantity(rs.getInt("quantity"));
		
		return orderDetail;
	}

}
