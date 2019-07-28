package cn.easybuy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.User;
import cn.easybuy.service.user.UserService;
import cn.easybuy.service.user.UserServiceImpl;
import cn.easybuy.uitls.EmptyUtil;
import cn.easybuy.uitls.ReturnResult;
import cn.easybuy.uitls.SecrityUtils;
/**
 * 登录请求控制处理类
 * @author 青云 .ltd
 *
 */
@WebServlet(urlPatterns= {"/Login"},name="Login")
public class LoginServlet extends AbstracterServlet {
	
	//注入用户业务类
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		userService=new UserServiceImpl();
	}

	@Override
	public Class getServletClass() {
		return LoginServlet.class;
	}
	
	/**
	 * 跳转到登录页面的方法
	 * @param request
	 * @param response
	 * @return 页面地址
	 */
	public String toLogin(HttpServletRequest request,HttpServletResponse response) {
		return "/pre/login";
	}
	
	/**
	 * 处理登录信息
	 * @param request
	 * @param response
	 * @return ajax请求结果对象
	 */
	public ReturnResult login(HttpServletRequest request,HttpServletResponse response) {
		ReturnResult returnResult=new ReturnResult();
		
		//获取请求中的参数值
		String loginName=request.getParameter("loginName");
		String pwd=request.getParameter("pwd");
		
		User user=userService.getUserInfoByLoginName(loginName);

		if(EmptyUtil.isEmpty(user)) {
			returnResult=returnResult.returnFail("用户不存在！");
		}else {

			if(user.getPassword().equals(SecrityUtils.md5Hex(pwd))) {
				
				//将用户信息保存到session中
				request.getSession().setAttribute("loginUser", user);
				
				returnResult=returnResult.returnSuccess(null,"登录成功！");
			}else {
				returnResult=returnResult.returnFail("用户密码不正确！");
			}
		}
		
		return returnResult;
	}
	
	/**
	  * 实现用户注销的方法
	 * @param request
	 * @param response
	 * @return 
	 */
	public String loginOut(HttpServletRequest request,HttpServletResponse response) {
		//移除session中保存的用户信息
		request.getSession().removeAttribute("loginUser");
		
		return "/pre/login";
	}
}
