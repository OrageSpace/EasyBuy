package cn.easybuy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cn.easybuy.uitls.DataBaseUtil;

/**
  * 数据库操作类
 * @author 青云 .ltd
 *
 */
public class BaseDao {
	public static Connection conn;//数据库连接对象
	public static PreparedStatement pstmt;//预编译SQL命令执行对象
	public static ResultSet rs;//结果集对象
	
	private static Logger logger=Logger.getLogger(BaseDao.class.getName());
	
	public BaseDao(Connection conn) {
		this.conn=conn;
	}
	
	/**
	  * 执行查询的操作
	 * @param sql 查询sql语句
	 * @param params 参数列表
	 * @return 结果集对象
	 * @throws Exception
	 */
	public static ResultSet executeQuery(String sql,Object...params) 
			throws SQLException{
		try {
			//实例化预编译sql命令执行对象
			pstmt=conn.prepareStatement(sql);
			
			//为预编译sql赋值
			if(params!=null) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i+1, params[i]);
				}
			}
			System.out.println(pstmt);
			logger.debug(pstmt);
			
			//执行查询命令
			rs=pstmt.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw e;
		}
		
		return rs;
	}
	
	/**
	  * 执行增删改操作的方法
	 * @param sql 增删改的sql语句
	 * @param params 参数列表
	 * @return 受影响行数
	 * @throws SQLException 
	 */
	public static int executeUpdate(String sql,Object...params)
			throws SQLException {
		int result=0;
		
		try {
			//实例化预编译sql命令执行对象
			pstmt=conn.prepareStatement(sql);
			
			//为预编译sql赋值
			if(params!=null) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i+1, params[i]);
				}
			}
			
			logger.debug(pstmt);
			
			//执行查询命令
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw e;
		}finally {
			//释放资源  数据库连接统一在业务逻辑层关闭
			DataBaseUtil.closeAll(null, pstmt, null);
		}
		
		return result;
	}
}
