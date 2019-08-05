package cn.easybuy.service.order;

import java.util.List;

import cn.easybuy.entity.UserAddress;

/**
 * 用户订单信息地址业务逻辑接口
 * @author 青云 .ltd
 *
 */
public interface OrderAddressService {
	/**
	  * 根据ID获取指定用户的地址信息
	 * @return 用户地址集合对象
	 */
	public List<UserAddress> queryAllAddressOfUser(int id);
	
	/**
	 * 新增用户地址的方法
	 * @param userId 用户编号
	 * @param newAddress 地址
	 * @param remark 备注
	 * @return 受影响行数
	 * @throws Exception
	 */
	public int addAddress(int userId,String newAddress,String remark);
}
