package cn.easybuy.dao.user;

import java.sql.SQLException;

import cn.easybuy.entity.User;

/**
 *用户数据库访问/操作接口
 * @author 青云 .ltd
 *
 */
public interface UserDao {
	/**
	 * 根据名称获取用户信息的方法
	 * @param loginName 用户登录名
	 * @return 用户对象
	 * @throws Exception 
	 */
	public User getUserInfoByLoginName(String loginName) throws Exception;
	
	/**
	 * 用户注册的方法
	 * @param user 待保存的用户信息对象
 	 * @return 受影响行数
	 * @throws SQLException 
	 */
	public int register(User user) throws SQLException;
}
