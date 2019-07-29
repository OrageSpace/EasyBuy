package cn.easybuy.dao.news;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.BaseDao;
import cn.easybuy.entity.News;
import cn.easybuy.uitls.DataBaseUtil;

/**
 * 新闻资讯数据访问接口实现类
 * @author 青云 .ltd
 *
 */
public class NewsDaoImpl extends BaseDao implements NewsDao {

	public NewsDaoImpl(Connection conn) {
		super(conn);
	}

	@Override
	public List<News> queryAllNews() throws Exception {
		ResultSet rs=null;//结果集对象
		List<News> newsList=new ArrayList<News>();
		
		try {
			StringBuffer sql=new StringBuffer("SELECT `id`,`title`,`content`,`createTime`  FROM `easybuy_news` LIMIT 0,5");
			
			rs=super.executeQuery(sql.toString(), null);
			
			//循环读取结果集中的数据
			while (rs.next()) {
				newsList.add(this.tableToClass(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//释放资源 数据库连接对象需要在业务逻辑层关闭
			DataBaseUtil.closeAll(null, pstmt, rs);
		}
		
		return newsList;
	}

	/**
	 * 解析结果集中的一行数据的方法
	 * @param rs
	 * @return 新闻信息对象
	 * @throws Exception
	 */
	private News tableToClass(ResultSet rs) throws Exception{
		News news=new News();
		news.setContent(rs.getString("content"));
		news.setCreateTime(rs.getDate("createTime"));
		news.setId(rs.getInt("id"));
		news.setTitle(rs.getString("title"));
		
		return news;
	}

}
