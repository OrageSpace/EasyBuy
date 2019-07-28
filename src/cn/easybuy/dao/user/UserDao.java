package cn.easybuy.dao.user;

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
}
