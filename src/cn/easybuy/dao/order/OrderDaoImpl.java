package cn.easybuy.dao.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.BaseDao;
import cn.easybuy.entity.Order;
import cn.easybuy.parames.OrderParames;
import cn.easybuy.uitls.DataBaseUtil;
import cn.easybuy.uitls.EmptyUtil;
/**
 * 订单信息数据访问接口实现类
 * @author 青云 .ltd
 *
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

	public OrderDaoImpl(Connection conn) {
		super(conn);
	}

	@Override
	public int addOrderInfo(Order order) throws Exception {
		int id=0;
		
		String sql="INSERT INTO `easybuy_order`(`userId`,`loginName`,`userAddress`,`createTime`,`cost`,`serialNumber`) "
				+ "VALUES(?,?,?,?,?,?)";
		id=super.executeInsert(sql, order.getUserId(),order.getLoginName(),order.getUserAddress(),order.getCreateTime(),order.getCost(),order.getSerialNumber());
		
		return id;
	}

	@Override
	public List<Order> getAllOrderFromUser(OrderParames params) throws Exception {
		ResultSet rs=null;//结果集对象
		List<Order> orders=new ArrayList<Order>();
		
		try {
			StringBuffer sql=new StringBuffer("SELECT `id`,`userId`,`loginName`,`userAddress`,`createTime`,`cost`,`status`,`serialNumber` FROM `easybuy_order` WHERE 1=1");
			
			List<Object> paramArr=new ArrayList<Object>();
			
			if(EmptyUtil.isNotEmpty(params.getUserId())) {
				sql.append(" AND `userId`=?");
				paramArr.add(params.getUserId());
			}
			
			if(EmptyUtil.isNotEmpty(params.getSort())) {
				sql.append(" ORDER BY "+params.getSort()+" DESC");
			}
			
			if(params.isPage()) {
				sql.append(" LIMIT ?,?");
				paramArr.add(params.getStartIndex());
				paramArr.add(params.getPageSize());
			}
			
			rs=super.executeQuery(sql.toString(), paramArr.toArray());
			
			//循环读取结果集中的数据
			while (rs.next()) {
				orders.add(this.tableToClass(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源  数据库连接对象需要在业务逻辑层关闭
			DataBaseUtil.closeAll(null, pstmt, rs);
		}
		
		return orders;
	}

	/**
	  * 解析结果集中订单信息对象的方法
	 * @param rs
	 * @return 订单信息对象
	 * @throws Exception
	 */
	private Order tableToClass(ResultSet rs)throws Exception{
		Order order=new Order();
		
		order.setCost(rs.getDouble("cost"));
		order.setCreateTime(rs.getDate("createTime"));
		order.setId(rs.getInt("id"));
		order.setLoginName(rs.getString("loginName"));
		order.setSerialNumber(rs.getString("serialNumber"));
		order.setStatus(rs.getInt("status"));
		order.setUserAddress(rs.getString("userAddress"));
		order.setUserId(rs.getInt("userId"));
		
		return order;
	}

	@Override
	public int getOrderCountFromUser(int userId) throws Exception {
		ResultSet rs=null;//结果集对象
		int count=0;
		
		try {
			String sql="SELECT COUNT(`id`) FROM `easybuy_order` WHERE `userId`=?";
			rs=super.executeQuery(sql, userId);
			
			//读取第一行第一列的数据
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源  数据库连接对象需要在业务逻辑层关闭
			DataBaseUtil.closeAll(null, pstmt, rs);
		}
		
		return count;
	}

}
