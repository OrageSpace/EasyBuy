package cn.easybuy.service.news;

import java.util.List;

import cn.easybuy.entity.News;

/**
 * 新闻信息业务逻辑操作接口
 * @author 青云 .ltd
 *
 */
public interface NewsService {
	
	/**
	 * 获取最新的5条新闻资讯的方法
	 * @return 新闻资讯集合
	 * @throws Exception 
	 */
	public List<News> queryAllNews();
}
