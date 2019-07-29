package cn.easybuy.service.news;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.easybuy.dao.news.NewsDao;
import cn.easybuy.dao.news.NewsDaoImpl;
import cn.easybuy.entity.News;
import cn.easybuy.uitls.DataBaseUtil;

public class NewsServiceImpl implements NewsService {
	
	private Connection conn=null;//数据库连接对象
	private NewsDao newsDao=null;//注入新闻资讯数据访问类对象

	@Override
	public List<News> queryAllNews() {
		List<News> newsList=null;
		
		try {
			//获取数据库连接对象
			conn=DataBaseUtil.getConnection();
			
			//实例化新闻资讯数据访问接口类对象
			newsDao=new NewsDaoImpl(conn);
			
			//调用查询数据的方法
			newsList=newsDao.queryAllNews();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//待业务完成后统一关闭数据库连接对象
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return newsList;
	}

}
