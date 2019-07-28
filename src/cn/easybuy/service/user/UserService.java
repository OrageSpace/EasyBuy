package cn.easybuy.service.user;

import java.sql.SQLException;

import cn.easybuy.entity.User;

public interface UserService {
	/**
	 * 根据名称获取用户信息的方法
	 * @param loginName 用户登录名
	 * @return 用户对象
	 */
	public User getUserInfoByLoginName(String loginName);
	
	/**
	 * 用户注册的方法
	 * @param user 待保存的用户信息对象
 	 * @return 受影响行数
	 * @throws SQLException 
	 */
	public boolean register(User user);
}
