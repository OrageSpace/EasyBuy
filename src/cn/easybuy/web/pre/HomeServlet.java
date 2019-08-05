package cn.easybuy.web.pre;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.easybuy.entity.News;
import cn.easybuy.entity.ProductCategory;
import cn.easybuy.service.news.NewsService;
import cn.easybuy.service.news.NewsServiceImpl;
import cn.easybuy.service.product.ProductCategoryService;
import cn.easybuy.service.product.ProductCategoryServiceImpl;
import cn.easybuy.uitls.ProductCategoryVo;
import cn.easybuy.web.AbstracterServlet;
/**
 * 访问首页控制类
 * @author 青云 .ltd
 *
 */
@WebServlet(urlPatterns= {"/Home"},name="Home")
public class HomeServlet extends AbstracterServlet {
	
	private ProductCategoryService pcs=null;
	private NewsService newsService=null;
	
	private static Logger logger=Logger.getLogger(HomeServlet.class.getName());
	
	@Override
	public void init() throws ServletException {
		pcs=new ProductCategoryServiceImpl();
		newsService=new NewsServiceImpl();
	}
	
	/**
	 * 获取首页中需要的数据并返回页面
	 * @param request
	 * @param response
	 * @return 首页路径
	 */
	public String index(HttpServletRequest request,HttpServletResponse response) {
		
		//调用查询商品分类信息的方法
		List<ProductCategoryVo> pcvList=pcs.queryAllProductCategory();
		
		//调用查询最新5条新闻资讯的方法
		List<News> newsList=newsService.queryAllNews();
		
		//将查询到的内容保存到request作用域中
		request.setAttribute("pcvList", pcvList);
		request.setAttribute("newsList", newsList);
		
		return "/pre/index";
	}

	@Override
	public Class getServletClass() {
		return this.getClass();
	}
}
