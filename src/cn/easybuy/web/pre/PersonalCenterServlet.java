package cn.easybuy.web.pre;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.Order;
import cn.easybuy.entity.OrderDetail;
import cn.easybuy.entity.Product;
import cn.easybuy.entity.User;
import cn.easybuy.parames.OrderDetailsParames;
import cn.easybuy.parames.OrderParames;
import cn.easybuy.parames.ProductParames;
import cn.easybuy.service.order.OrderDetailService;
import cn.easybuy.service.order.OrderDetailServiceImpl;
import cn.easybuy.service.order.OrderService;
import cn.easybuy.service.order.OrderServiceImpl;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.uitls.EmptyUtil;
import cn.easybuy.uitls.OrderDetialsVo;
import cn.easybuy.uitls.Pager;
import cn.easybuy.uitls.ReturnResult;
import cn.easybuy.web.AbstracterServlet;
/**
 * 订单信息控制类
 * @author 青云 .ltd
 *
 */
@WebServlet(urlPatterns= {"/Center"},name="Center")
public class PersonalCenterServlet extends AbstracterServlet {
	
	private static final String HttpServletRequest = null;
	private OrderService orderService;
	private OrderDetailService orderDetailService;
	private ProductService productService;

	@Override
	public Class getServletClass() {
		return PersonalCenterServlet.class;
	}
	
	@Override
	public void init() throws ServletException {
		orderService=new OrderServiceImpl();
		orderDetailService=new OrderDetailServiceImpl();
		productService=new ProductServiceImpl();
	}

	/**
	  * 返回个人中心地址的方法
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String toPersonalCenter(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		return "/pre/member/member";
	}
	
	/**
	  * 返回我的订单HTML源代码的方法
	 * @param request
	 * @param response
	 * @return 我的订单HTML源代码
	 * @throws Exception
	 */ 
	public String toMyOrder(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		ReturnResult returnResult=new ReturnResult();
		
		//获取session中保存的用户信息
		User user=this.getUserFromSession(request);
		
		//获取请求中的参数值
		String currPageNoStr=request.getParameter("currPageNo");
		
		String sort=request.getParameter("sort");
		
		if(EmptyUtil.isEmpty(currPageNoStr)) {
			currPageNoStr="1";
		}
		
		int currPageNo=Integer.valueOf(currPageNoStr);
		
		if(EmptyUtil.isEmpty(user)) { //如果当前尚未登录则跳转到首页
			return "Home?action=index";
		}
		
		OrderParames prames=new OrderParames();
		prames.setPage(true);
		prames.setPageSize(15);
		prames.setUserId(user.getId());
		prames.setSort(EmptyUtil.isEmpty(sort)?null:sort);
		
		//获取用户的订单总数
		int orderCount=orderService.getOrderCountFromUser(user.getId());
		prames.setStartIndex((currPageNo-1)*prames.getPageSize());
		
		//获取用户订单信息
		List<Order> orders=orderService.getAllOrderFromUser(prames);
		
		Pager pager=new Pager(currPageNo, orderCount, prames.getPageSize(),orders);
		pager.setUrl("/Center?action=toMyOrder");
		
		//将Pager对象保存到request作用域中
		request.setAttribute("pageObj", pager);
		
		return "/pre/member/member_Order";
	}
	
	

	/**
	  * 获取session中保存的用户信息
	 * @param httpservletrequest2
	 * @return
	 */
	private User getUserFromSession(HttpServletRequest request) {
		User user=(User)request.getSession().getAttribute("loginUser");
		return user;
	}
	
	/**
	 * 返回左侧边栏的HTML源码
	 * @param request
	 * @param response
	 * @return 左侧边栏的HTML源码
	 * @throws Exception
	 */
	public String refreshLeftBar(HttpServletRequest request,HttpServletResponse response)
			throws Exception{
		return "/common/member_leftBar";
	}
	
	/**
	  * 查看订单详情的方法
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Object toOrderDetial(HttpServletRequest request,HttpServletResponse response)
			throws Exception{
		//获取请求中的参数值
		String orderId=request.getParameter("orderId");
		String currPageNoStr=request.getParameter("currPageNo");
		String sort=EmptyUtil.isNotEmpty(request.getParameter("sort"))
				?request.getParameter("sort"):null;
		
		int currPageNo=EmptyUtil.isNotEmpty(currPageNoStr)?Integer.valueOf(currPageNoStr):1;
		
		
		OrderDetailsParames parame=new OrderDetailsParames();
		parame.setOrderId(Integer.valueOf(orderId));
		parame.setSort(sort);
	
		parame.openPage((currPageNo-1)*parame.getPageSize(), 15);
		
		List<OrderDetail> orderDetails=orderDetailService.queryOrderDetails(parame);
		
		List<OrderDetialsVo> odvList=new ArrayList<OrderDetialsVo>();
		
		//获取当前订单下商品详情的记录数
		int count=orderDetailService.getOrderDetailsCountByOrderId(parame.getOrderId());
		
		//循环读取订单详情信息  使用订单详情信息查询商品信息
		for (OrderDetail item : orderDetails) {
			OrderDetialsVo odv=new OrderDetialsVo();
			odv.setOrderDetail(item);
			odv.setProduct(productService.getProductById(item.getProductId()));
			System.out.println(item.getOrderId());
			odvList.add(odv);
		}
		
		Pager page=new Pager(parame.getStartIndex(), count, parame.getPageSize(), odvList);
		
		//将page对象保存到request作用域中
		request.setAttribute("pageObj", page);
		
		return "/pre/member/member_Detials";
	}
}
