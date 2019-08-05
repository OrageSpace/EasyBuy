package cn.easybuy.web.pre;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.Product;
import cn.easybuy.parames.ProductParames;
import cn.easybuy.service.product.ProductCategoryService;
import cn.easybuy.service.product.ProductCategoryServiceImpl;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.uitls.EmptyUtil;
import cn.easybuy.uitls.Pager;
import cn.easybuy.uitls.ProductCategoryVo;
import cn.easybuy.web.AbstracterServlet;

/**
  * 商品信息处理控制类
 * @author 青云 .ltd
 *
 */
@WebServlet(urlPatterns= {"/Product"},name="Product")
public class ProductServlet extends AbstracterServlet {
	
	private ProductService productService=null;
	private ProductCategoryService pcs=null;

	@Override
	public Class getServletClass() {
		return ProductServlet.class;
	}
	
	@Override
	public void init() throws ServletException {
		productService=new ProductServiceImpl();
		pcs=new ProductCategoryServiceImpl();
	}
	
	/**
	 * 获取商品信息的方法
	 * @param request
	 * @param response
	 * @return 商品信息展示页面路径
	 * @throws Exception
	 */
	public String queryProductList(HttpServletRequest request,HttpServletResponse response)
		throws Exception{
		ProductParames prames=new  ProductParames();
		
		//获取请求中的参数值
		String keyWords=EmptyUtil.isEmpty(request.getParameter("keyWords"))
				?null:request.getParameter("keyWords");
		
		String sort=EmptyUtil.isEmpty(request.getParameter("sort"))
				?null:request.getParameter("sort");
		
		int categoryId=EmptyUtil.isEmpty(request.getParameter("categoryId"))
				?0:Integer.valueOf(request.getParameter("categoryId"));
		
		boolean isPage=EmptyUtil.isEmpty(request.getParameter("isPage"))
				?true:Boolean.valueOf(request.getParameter("isPage"));
		
		int currPageNo=EmptyUtil.isEmpty(request.getParameter("currPageNo"))
				?1:Integer.valueOf(request.getParameter("currPageNo"));
		
		int pageSize=EmptyUtil.isEmpty(request.getParameter("pageSize"))
				?16:Integer.valueOf(request.getParameter("pageSize"));
		
		prames.setCategoryId(categoryId);
		prames.setKeyWords(keyWords);
		prames.setPage(isPage);
		prames.setPageSize(pageSize);
		prames.setSort(sort);
		prames.setStateIndex((currPageNo-1)*pageSize);
		
		//获取总记录数
		int count=productService.getProductCount(prames);
		
		List<Product> products=productService.queryProductList(prames);
		
		Pager page=new Pager(currPageNo, count, pageSize,products);
		page.setUrl("/Product?action=queryProductList&keyWords="+(EmptyUtil.isEmpty(keyWords)?"":keyWords)+"&categoryId="+categoryId);
		
		List<ProductCategoryVo> pcv=pcs.queryAllProductCategory();//获取商品分类集合
		
		//将数据保存到request作用域中
		request.setAttribute("pageObj", page);
		request.setAttribute("pcvList", pcv);
		request.setAttribute("keyWords", keyWords);
		 
		return "pre/product/productList";
	}
	
	/**
	 * 获取商品详情信息的方法
	 * @param request
	 * @param response
	 * @return 商品详情信息展示页面路径
	 */
	public String queryProductDetails(HttpServletRequest request,HttpServletResponse response) {
		//获取请求中的参数值
		int id=Integer.valueOf(request.getParameter("id"));
		//查询指定商品信息
		Product product=productService.getProductById(id);
		//查询所有商品分类对象
		List<ProductCategoryVo> pcvList=pcs.queryAllProductCategory();
		
		//将查询到的信息保存到request作用域中
		request.setAttribute("pcvList", pcvList);
		request.setAttribute("product", product);
		return "/pre/product/productDetails";
	}
}
