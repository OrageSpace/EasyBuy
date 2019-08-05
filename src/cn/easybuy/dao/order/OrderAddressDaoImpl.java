package cn.easybuy.dao.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.easybuy.dao.BaseDao;
import cn.easybuy.entity.UserAddress;
import cn.easybuy.uitls.DataBaseUtil;
/**
 * 用户地址接口实现类
 * @author 青云 .ltd
 *
 */
public class OrderAddressDaoImpl extends BaseDao implements OrderAddressDao {

	public OrderAddressDaoImpl(Connection conn) {
		super(conn);
	}

	@Override
	public List<UserAddress> queryAllAddressOfUser(int id)
			throws Exception{
		
		ResultSet rs=null;//结果集对象
		List<UserAddress> addressList=new ArrayList<UserAddress>();
		
		try {
			String sql="SELECT `id`,`address`,`createTime`,`userId`,`isDefault`,`remark` FROM `easybuy_user_address` WHERE `userId`=?";
			
			rs=super.executeQuery(sql, id);
			
			//循环读取结果集中的数据
			while (rs.next()) {
				addressList.add(this.tableToClass(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//释放资源  数据库连接需要在业务逻辑层关闭
			DataBaseUtil.closeAll(null, pstmt, rs);
		}
		
		return addressList;
	}

	/**
	 * 用于解析结果集中用户地址信息的方法
	 * @param rs
	 * @return 用户地址信息对象
	 */
	private UserAddress tableToClass(ResultSet rs)
			throws Exception{
		
		UserAddress address=new UserAddress();
		address.setAddress(rs.getString("address"));
		address.setCreateTime(rs.getDate("createTime"));
		address.setId(rs.getInt("id"));
		address.setIsDefault(rs.getInt("isDefault"));
		address.setRemark(rs.getString("remark"));
		address.setUserId(rs.getInt("userId"));
		
		return address;
	}

	@Override
	public int addAddress(int userId, String newAddress, String remark) throws Exception {
		int result=0;
		
		try {
			String sql="INSERT INTO `easybuy_user_address`(`address`,`createTime`,`userId`,`remark`) VALUES(?,?,?,?)";
			result=super.executeUpdate(sql, newAddress,new SimpleDateFormat("yyyy-MM-dd").format(new Date()),userId,remark);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
