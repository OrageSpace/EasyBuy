package cn.easybuy.service.order;

import java.sql.Connection;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Data;

import cn.easybuy.dao.order.OrderAddressDao;
import cn.easybuy.dao.order.OrderAddressDaoImpl;
import cn.easybuy.entity.UserAddress;
import cn.easybuy.uitls.DataBaseUtil;

public class OrderAddressServiceImpl implements OrderAddressService {
	
	private Connection conn=null;//数据库连接对象
	private OrderAddressDao orderAddressDao=null;

	@Override
	public List<UserAddress> queryAllAddressOfUser(int id){
		List<UserAddress> addressList=null;
		
		try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			//实例化订单地址数据访问类对象
			orderAddressDao=new OrderAddressDaoImpl(conn);
			
			addressList=orderAddressDao.queryAllAddressOfUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//在业务完成后统一关闭数据库连接对象
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return addressList;
	}

	@Override
	public int addAddress(int userId, String newAddress, String remark){
		int result=0;
		
		try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			//实例化订单地址数据访问类对象
			orderAddressDao=new OrderAddressDaoImpl(conn);
			
			result=orderAddressDao.addAddress(userId, newAddress, remark);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//在业务完成后统一关闭数据库连接对象
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return result;
	}

}
