package cn.easybuy.dao.order;
/**
 * 获取用户订单地址信息的数据访问接口
 * @author 青云 .ltd
 *
 */

import java.util.List;

import cn.easybuy.entity.UserAddress;

public interface OrderAddressDao {
	/**
	 * 根据ID获取指定用户的地址信息
	 * @return 用户地址集合对象
	 * @throws Exception 
	 */
	public List<UserAddress> queryAllAddressOfUser(int id) throws Exception;
	
	/**
	 * 新增用户地址的方法
	 * @param userId 用户编号
	 * @param newAddress 地址
	 * @param remark 备注
	 * @return 受影响行数
	 * @throws Exception
	 */
	public int addAddress(int userId,String newAddress,String remark) throws Exception;
}
