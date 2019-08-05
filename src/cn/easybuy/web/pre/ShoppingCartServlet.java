package cn.easybuy.web.pre;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.Order;
import cn.easybuy.entity.Product;
import cn.easybuy.entity.User;
import cn.easybuy.entity.UserAddress;
import cn.easybuy.service.order.CartService;
import cn.easybuy.service.order.CartServiceImpl;
import cn.easybuy.service.order.OrderAddressService;
import cn.easybuy.service.order.OrderAddressServiceImpl;
import cn.easybuy.service.order.OrderService;
import cn.easybuy.service.order.OrderServiceImpl;
import cn.easybuy.service.product.ProductCategoryService;
import cn.easybuy.service.product.ProductCategoryServiceImpl;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.uitls.Constans;
import cn.easybuy.uitls.EmptyUtil;
import cn.easybuy.uitls.ProductCategoryVo;
import cn.easybuy.uitls.ReturnResult;
import cn.easybuy.uitls.ShoppingCart;
import cn.easybuy.uitls.ShoppingCartItem;
import cn.easybuy.web.AbstracterServlet;

@WebServlet(urlPatterns= {"/Cart"},name="Cart")
public class ShoppingCartServlet extends AbstracterServlet {
	//注入商品信息业务逻辑类对象
	private ProductService productService=null;
	//注入商品分类信息VO类对象
	private ProductCategoryService productCategoryService=null;
	//注入购物车业务逻辑类对象
	private CartService cartService=null;
	//注入用户地址业务逻辑处理类对象
	private OrderAddressService orderAddressService=null;
	//注入订单业务逻辑处理类对象
	private OrderService orderService=null;
	
	@Override
	public Class getServletClass() {
		
		return ShoppingCartServlet.class;
	}
	
	@Override
	public void init() throws ServletException {
		productService=new ProductServiceImpl();
		productCategoryService=new ProductCategoryServiceImpl();
		cartService=new CartServiceImpl();
		orderAddressService=new OrderAddressServiceImpl();
		orderService=new OrderServiceImpl();
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
	  *  获取settlement2.jsp页面的HTML源码的方法
	 * @param request
	 * @param response
	 * @return settlement2.jsp页面的HTML源码
	 * @throws Exception
	 */
	public String settlement2(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		
		//获取登录用户信息
		User user=(User)request.getSession().getAttribute("loginUser");
		int id=Integer.valueOf(user.getId());
		
		//获取用户地址信息
		List<UserAddress> addressList=orderAddressService.queryAllAddressOfUser(id);
		//将查询到的信息保存到request中
		request.setAttribute("addressList", addressList);
		
		return "/pre/settlement/settlement2";
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
	
	/**
	 * 清空购物车的方法
	 * @param request
	 * @param response
	 * @return ajax请求结果对象
	 */
	public ReturnResult clearCart(HttpServletRequest request,HttpServletResponse response) {
		ReturnResult result=new ReturnResult();
		request.getSession().removeAttribute("cart");
		
		return result.returnSuccess();
	}
	
	/**
	 * 生成订单的方法
	 * @param request
	 * @param response
	 * @return ajax请求结果对象 或 settlement3的路径
	 * @throws Exception
	 */
	public Object toSettlement3(HttpServletRequest request,HttpServletResponse response)
			throws Exception{
		ReturnResult returnResult=new ReturnResult();
		
		//获取session中的购物车对象
		ShoppingCart cart=this.getCartFromSession(request);
				
		//检查所要购买商品的库存数
		returnResult=this.checkProductStock(request,cart);
		
		if(returnResult.equals(Constans.ReturnResult.FAIL)) {//判断返回的信息 如果返回结果为错误信息 则结束方法 返回异常结果
			return returnResult;
		}
		
		//获取请求中的参数值
		String addressId=request.getParameter("addressId");
		String newAddress=request.getParameter("newAddress");
		String remark=request.getParameter("remark");
		
		//获取当前登录的用户
		User user=this.getUserFormSession(request);
		
		if(addressId==null||addressId.equals("")) {
			return returnResult.returnFail("请求参数异常，请与系统管理员联系(错误参数：addressId："+addressId+",newAddress:"+newAddress+")！");
		}
		
		Integer address=Integer.valueOf(addressId);
		
		if(address==-1) {//当address为-1时则表示当前地址是新添加的地址  这个时候就需要进行新增地址信息的操作了
			address=orderAddressService.addAddress(user.getId(), newAddress, remark);
		}
		
		//执行订单生产的操作
		Order order=orderService.payShoppingCart(user, addressId, cart);
		
		//将订单信息对象保存到request作用域中
		request.setAttribute("order", order);
		
		//清空购物车中的商品
		this.clearCart(request, response);
		
		return "/pre/settlement/settlement3";
	}

	/**
	 * 检查商品的库存数量
	 * @param request
	 */
	private ReturnResult checkProductStock(HttpServletRequest request,ShoppingCart cart) {
		ReturnResult reutResult=new ReturnResult();
		
		//遍历集合对象
		for (ShoppingCartItem item: cart.getItems()) {
			//根据商品id查询商品信息 进而比对商品的库存数
			Product product=productService.getProductById(item.getProduct().getId());
			
			if(product.getStock()<item.getQuantity()) {//如果购买的数量大于库存数则 返回异常信息
				return reutResult.returnFail("订单提交失败，商品："+product.getName()+"库存不足，系统已通知商家，请稍后重试！");
			}	
		}
		
		return reutResult.returnSuccess();
	}
	
	/**
	 * 获取当前登录用户的方法
	 * @param request
	 * @return 用户对象
	 * @throws Exception
	 */
	public User getUserFormSession(HttpServletRequest request)
			throws Exception {
		//获取session中登录的用户
		User user=(User)request.getSession().getAttribute("loginUser");
		
		return user;
	}
}
