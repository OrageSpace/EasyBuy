package cn.easybuy.uitls;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 数据库访问工具类
 * @author 青云 .ltd
 *
 */
public class DataBaseUtil {
	private static String driver;//数据库驱动
	private static String url;//数据库连接字符串
	private static String userName;//数据库用户名
	private static String pwd;//数据库用户密码
	
	private static Logger logger=Logger.getLogger(DataBaseUtil.class.getName());
	
	static {//静态代码块用于初始化数据
		
		//初始化数据库配置信息
		init();
		
		try {
			//加载数据库驱动
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage()+"（数据库驱动加载错误！）");
		}
	}

	/**
	 * 初始化数据库配置信息的方法
	 */
	private static void init() {
		String configFile="resources/database.properties";
		//配置信息对象
		Properties properties=new Properties();
		InputStream is=DataBaseUtil.class.getClassLoader().getResourceAsStream(configFile);
		
		try {
			//将配置信息保存到properties对象中
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage()+"（配置文件加载出错！）");
		}
		
		//获取对应配置信息
		driver=properties.getProperty("driver");
		url=properties.getProperty("url");
		userName=properties.getProperty("userName");
		pwd=properties.getProperty("pwd");
	}
	
	/**
	 * 获取数据库连接的方法
	 * @return 数据库连接对象
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn=null;//数据库连接对象
		
		if(conn==null||conn.isClosed()) {
			conn=DriverManager.getConnection(url, userName, pwd);
		}
		
		return conn;
	}
	
	/**
	 * 释放数据库连接资源
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public static void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
			
			if(pstmt!=null) {
				pstmt.close();
			}
			
			if(conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage()+"（数据库资源释放是发生异常！）");
		}
	}
}
