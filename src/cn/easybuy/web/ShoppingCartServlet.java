package cn.easybuy.web;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.Product;
import cn.easybuy.service.order.CartService;
import cn.easybuy.service.order.CartServiceImpl;
import cn.easybuy.service.product.ProductCategoryService;
import cn.easybuy.service.product.ProductCategoryServiceImpl;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.uitls.EmptyUtil;
import cn.easybuy.uitls.ProductCategoryVo;
import cn.easybuy.uitls.ReturnResult;
import cn.easybuy.uitls.ShoppingCart;
import cn.easybuy.uitls.ShoppingCartItem;

@WebServlet(urlPatterns= {"/Cart"},name="Cart")
public class ShoppingCartServlet extends AbstracterServlet {
	//注入商品信息业务逻辑类对象
	private ProductService productService=null;
	//注入商品分类信息VO类对象
	private ProductCategoryService productCategoryService=null;
	//注入购物车业务逻辑类对象
	private CartService cartService=null;
	
	@Override
	public Class getServletClass() {
		
		return ShoppingCartServlet.class;
	}
	
	@Override
	public void init() throws ServletException {
		productService=new ProductServiceImpl();
		productCategoryService=new ProductCategoryServiceImpl();
		cartService=new CartServiceImpl();
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
	
	/**
	  * 跳转到结算页面的方法
	 * @param request
	 * @param response
	 * @return 结算页面的路径
	 * @throws Exception
	 */
	public String toSettlement(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		//获取商品分类VO类集合对象
		List<ProductCategoryVo> pcvList=productCategoryService.queryAllProductCategory();
		//将查询到到的分类Vo集合对象保存到request作用域中
		request.setAttribute("pcvList", pcvList);
		return "/pre/settlement/toSettlement";
	}
	
	/**
	  *  获取settlement1.jsp页面的HTML源码的方法
	 * @param request
	 * @param response
	 * @return settlement1.jsp页面的HTML源码
	 * @throws Exception
	 */
	public String settlement1(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		return "/pre/settlement/settlement1";
	}
	
	/**
	  * 修改商品数量
	 * @param request
	 * @param response
	 * @return
	 */
	public ReturnResult modifyQuantity(HttpServletRequest request,HttpServletResponse response)
			throws Exception{
		ReturnResult resultResult=new ReturnResult();
		
		//获取请求中的参数值
		String id=request.getParameter("id");
		String quantity=request.getParameter("quantity");
		
		if(EmptyUtil.isEmpty(id)||EmptyUtil.isEmpty(quantity)) {
			return resultResult.returnFail("请求参数异常，参数不能为空！");
		}
		
		int pid;
		int mQuantity;
		
		try {
			pid=Integer.valueOf(id);
			mQuantity=Integer.valueOf(quantity);
		}catch(NumberFormatException e) {
			return resultResult.returnFail("请求参数异常，请求参数的类型不正确，请与系统管理员联系！");
		}
		
		//获取session中保存的购物车对象
		ShoppingCart cart=this.getCartFromSession(request);
		
		//获取对应商品的相关信息
		Product product=productService.getProductById(pid);
		
		if(product.getStock()==0) {
			return resultResult.returnFail("商品已售罄，系统已通知商家，您可以将该商品加入收藏，待商家更新库存信息时，系统会第一时间提醒您！");
		}
		
		if(product.getStock()<mQuantity) {//判断库存是否充足
			return resultResult.returnFail("商品数量不足！");
		}
		
		cart=cartService.modifyShoppingCart(pid, mQuantity, cart);
		
		//将购物车对象保存到session中去
		request.getSession().setAttribute("cart", cart);
		
		return resultResult.returnSuccess("{itemCost:"+mQuantity*product.getPrice()+",sumCost:"+cart.getSumCost()+"}","操作成功！");
	}
}
