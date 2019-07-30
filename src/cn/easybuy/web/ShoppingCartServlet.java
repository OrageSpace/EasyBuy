package cn.easybuy.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.Product;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.uitls.EmptyUtil;
import cn.easybuy.uitls.ReturnResult;
import cn.easybuy.uitls.ShoppingCart;

@WebServlet(urlPatterns= {"/Cart"},name="Cart")
public class ShoppingCartServlet extends AbstracterServlet {
	//注入商品信息业务逻辑类对象
	private ProductService productService=null;
 
	@Override
	public Class getServletClass() {
		
		return ShoppingCartServlet.class;
	}
	
	@Override
	public void init() throws ServletException {
		productService=new ProductServiceImpl();
	}
	
	/**
	  * 将商品添加到购物车的方法
	 * @param request
	 * @param response
	 * @return ajax请求结果对象
	 * @throws Exception
	 */
	public ReturnResult addCartItem(HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
		ReturnResult returnResult=new ReturnResult();
		
		//获取请求中的参数值
		int id=Integer.valueOf(request.getParameter("id"));
		int quantity=Integer.valueOf(request.getParameter("quantity"));
		
		//获取session中保存的购物车对象
		ShoppingCart cart=this.getCartFromSession(request);
		
		//获取商品信息
		Product product=productService.getProductById(id);
		
		returnResult=cart.addCartItem(product, quantity);
		
		//将购物车对象保存到session作用域中
		request.getSession().setAttribute("cart", cart);
		
		return returnResult;
	}
	
	/**
	 * 获取会话中保存的购物车对象 如果有则返回购物车对象 如果没有则先创建一个购物车对象 
	 * @param request
	 * @return 购物车对象
	 */
	public ShoppingCart getCartFromSession(HttpServletRequest request) {
		
		ShoppingCart cart=(ShoppingCart) request.getSession().getAttribute("cart");
		
		if(EmptyUtil.isEmpty(cart)) {//如果session中没有购物车对象 则创建一个
			cart=new ShoppingCart();
		}
		
		return cart;
	}
	
	/**
	 * 刷新购物车模块的HTML内容的方法
	 * @param request
	 * @param response
	 * @return ajax请求结果对象
	 * @throws Exception
	 */
	public String refreshCart(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		return "/common/pre/searchBar";
	}
}
