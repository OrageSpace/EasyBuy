package cn.easybuy.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.easybuy.entity.ProductCategory;
import cn.easybuy.service.product.ProductCategoryService;
import cn.easybuy.service.product.ProductCategoryServiceImpl;
/**
 * 访问首页控制类
 * @author 青云 .ltd
 *
 */
@WebServlet(urlPatterns= {"/Home"},name="Home")
public class HomeServlet extends AbstracterServlet {
	
	private ProductCategoryService pcs=new ProductCategoryServiceImpl();	;
	
	private static Logger logger=Logger.getLogger(HomeServlet.class.getName());
	
	/*@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置请求的编码 防止请求中文乱码
		request.setCharacterEncoding("UTF-8");
		//设置响应内容类型 以及编码格式
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//获取请求中的参数值
		String parentId="1";
		
		pcs=new ProductCategoryServiceImpl();
		
		//调用查询商品分类信息的方法
		List<ProductCategory> pcList=pcs.queryAllProductCategory(parentId);
		
		//将查询到的内容保存到request作用域中
		request.setAttribute("pcList", pcList);
		
		//将页面转发到/pre/index.jsp
		request.getRequestDispatcher("pre/index.jsp").forward(request, response);
	}*/
	
	public String index(HttpServletRequest request,HttpServletResponse response) {
		//获取请求中的参数值
		String parentId="1";
		
		//调用查询商品分类信息的方法
		List<ProductCategory> pcList=pcs.queryAllProductCategory(parentId);
		
		//将查询到的内容保存到request作用域中
		request.setAttribute("pcList", pcList);
		
		return "/pre/index";
	}

	@Override
	public Class getServletClass() {
		return this.getClass();
	}
}
