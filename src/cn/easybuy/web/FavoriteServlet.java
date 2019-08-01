package cn.easybuy.web;
/**
 * 商品收藏控制类
 * @author 青云 .ltd
 *
 */

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.Product;
import cn.easybuy.entity.User;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.uitls.EmptyUtil;
import cn.easybuy.uitls.MemcachedUtils;
import cn.easybuy.uitls.ReturnResult;

@WebServlet(urlPatterns= {"/Favorite"},name="Favorite")
public class FavoriteServlet extends AbstracterServlet {
	
	//注入商品业务逻辑处理类对象
	private ProductService productService=null;
	
	@Override
	public void init() throws ServletException {
		productService=new ProductServiceImpl();
	}

	@Override
	public Class getServletClass() {
		return FavoriteServlet.class;
	}
	
	/**
	 * 添加收藏的方法
	 * @param request
	 * @param response
	 * @return ajax请求结果对象
	 * @throws Exception
	 */
	public ReturnResult addFavorite(HttpServletRequest request,HttpServletResponse response)
			throws Exception{
		ReturnResult resteResult=new ReturnResult();
		
		//获取Memcached中保存的集合
		List<Product> favoriteList=getFavorite(request);
		
		//获取请求中的参数值
		String id=request.getParameter("id");
		
		if(EmptyUtil.isEmpty(id)) {
			return resteResult.returnFail("操作失败，请求参数异常，请与系统管理员联系！");
		}
		
		//根据请求参数中的id查询商品信息
		Product product=productService.getProductById(Integer.valueOf(id));
		
		//添加商品收藏  共有两种情况 1. 如果收藏中已经存在5条记录 则删除最先添加的记录
		if(favoriteList.size()==5) {
			favoriteList.remove(0);//删除一个
		}
		
		boolean flag=false;
		
		for (Product item : favoriteList) {
			if(item.getId()==product.getId()) {
				flag=true;
				break;
			}
		}
		
		if(!flag) {
			favoriteList.add(product);
		}
		
		try {
			//将信息保存到Memcached中
			MemcachedUtils.set(this.getFavoriteKey(request), favoriteList);
		} catch (Exception e) {
			e.printStackTrace();
			return resteResult.returnFail("添加收藏时发生异常，请与系统管理员联系！");
		}
		
		//将商品收藏集合对象保存到session作用域中
		request.getSession().setAttribute("favoriteList", favoriteList);
		
		return resteResult.returnSuccess();
	} 
	
	/**
	 * 获取Memcached中保存的收藏
	 * @param request
	 * @return 获取到的收藏集合对象
	 * @throws Exception
	 */
	public List<Product> getFavorite(HttpServletRequest request)
			throws Exception{
		
		//判断用户是否登录 如果用于未登录则以sessionId查询 
		String key=this.getFavoriteKey(request);
		
		List<Product> favoriteList=(List<Product>)MemcachedUtils.get(key);
		
		if(EmptyUtil.isEmpty(favoriteList)) {
			favoriteList=new  ArrayList<Product>();
		}
		
		return favoriteList;
	}
	
	/**
	  * 获取查询的key值的方法
	 * @param request
	 * @return key值
	 */
	public String getFavoriteKey(HttpServletRequest request) {
		User user=(User)request.getSession().getAttribute("loginUser");
		//判断用户是否登录 如果用于未登录则以sessionId查询 
		String key=EmptyUtil.isEmpty(user)
				?request.getSession().getId():user.getId()+"";
				
		return "fList"+key;
	}
	
	public String refreshFavorite(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		//获取Memcached中保存的收藏集合对象
		return "/common/pre/favoriteList";
	}
}
