package cn.easybuy.dao.news;
/**
 * 新闻资讯数据访问接口
 * @author 青云 .ltd
 *
 */

import java.util.List;

import cn.easybuy.entity.News;

public interface NewsDao {
	
	/**
	 * 获取最新的5条新闻资讯的方法
	 * @return 新闻资讯集合
	 * @throws Exception 
	 */
	public List<News> queryAllNews() throws Exception;
}
