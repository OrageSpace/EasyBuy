package cn.easybuy.service.user;

import java.sql.Connection;
import java.sql.SQLException;

import cn.easybuy.dao.user.UserDao;
import cn.easybuy.dao.user.UserDaoImpl;
import cn.easybuy.entity.User;
import cn.easybuy.uitls.DataBaseUtil;

public class UserServiceImpl implements UserService {
	//注入用户数据访问类
	private UserDao userDao;
	
	private Connection conn;//数据库连接对象

	@Override
	public User getUserInfoByLoginName(String loginName) {
		
		User user=null;
		
		try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			//实例化数据访问类对象
			userDao=new UserDaoImpl(conn);
			
			user=userDao.getUserInfoByLoginName(loginName);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//业务完成后统一 释放数据库连接
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return user;
	}

	@Override
	public boolean register(User user) {
		boolean flag=false;
		
		try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			//实例化数据访问类对象
			userDao=new UserDaoImpl(conn);
			
			int result=userDao.register(user);
			
			flag=result>0;//判断是否新增成功
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//业务完成后统一 释放数据库连接
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return flag;
	}

}
