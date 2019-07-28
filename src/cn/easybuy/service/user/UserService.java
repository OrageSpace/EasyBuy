package cn.easybuy.service.user;

import cn.easybuy.entity.User;

public interface UserService {
	/**
	 * 根据名称获取用户信息的方法
	 * @param loginName 用户登录名
	 * @return 用户对象
	 */
	public User getUserInfoByLoginName(String loginName);
}
