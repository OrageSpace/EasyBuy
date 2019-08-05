package cn.easybuy.web.pre;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.dao.user.UserDao;
import cn.easybuy.entity.User;
import cn.easybuy.service.user.UserService;
import cn.easybuy.service.user.UserServiceImpl;
import cn.easybuy.uitls.Constans.UserType;
import cn.easybuy.web.AbstracterServlet;
import cn.easybuy.uitls.RegUtils;
import cn.easybuy.uitls.ReturnResult;
import cn.easybuy.uitls.SecrityUtils;

/**
 * 注册控制类
 * @author 青云 .ltd
 *
 */
@WebServlet(urlPatterns={"/Register"},name="Register")
public class RegisterServlet extends AbstracterServlet {
	
	//注入用户业务逻辑类对象
	private UserService userService=new UserServiceImpl();

	@Override
	public Class getServletClass() {
		return RegisterServlet.class;
	}
	
	/**
	 * 返回注册页面的路径的方法
	 * @param request
	 * @param response
	 * @return 注册页面路径
	 */
	public String toRegister(HttpServletRequest request,HttpServletResponse response) {
		return "/pre/register";
	}
	
	/**
	 * 实现注册用户的方法
	 * @param request
	 * @param response
	 * @return ajax请求结果 JSON数据
	 */
	public ReturnResult doRegister(HttpServletRequest request,HttpServletResponse response) {
		ReturnResult returnResult=new ReturnResult();
		
		User user=new User();
		//获取请求中的参数值
		user.setEmail(request.getParameter("email"));
		user.setIdentityCode(request.getParameter("identityCode"));
		user.setLoginName(request.getParameter("loginName"));
		user.setMobile(request.getParameter("mobile"));
		user.setPassword(request.getParameter("password"));
		user.setSex(Integer.valueOf(request.getParameter("sex")));
		user.setType(UserType.PRE);
		user.setUserName(request.getParameter("userName"));
		
		//调用检查提交信息是否正确的方法  提升系统的安全性  
		//因为B/S架构的js处理可以通过些手段避开 所以可以在后台二次验证
		this.checkUser(user,returnResult);
		
		if(returnResult.getStatus()==1) {
			
			//对密码进行md5加密
			user.setPassword(SecrityUtils.md5Hex(user.getPassword()));
			
			boolean result=userService.register(user);
			
			if(!result) {
				returnResult=returnResult.returnFail("注册失败，请稍后重试或与系统管理员联系！");
			}
		}
		
		return returnResult;
	}

	/**
	  * 检查用户信息是否正确的方法
	 * @param user
	 * @return
	 */
	private ReturnResult checkUser(User user,ReturnResult returnResult) {
		//检查用户名是否存在于数据库中
		if(userService.getUserInfoByLoginName(user.getLoginName())!=null) {
			return returnResult.returnFail("用户名"+user.getLoginName()+"已被注册，请重新输入！");
		}
		
		//验证邮箱格式
		if(!RegUtils.checkEmail(user.getEmail())) {
			return returnResult.returnFail("邮箱格式不正确！");
		}
		
		//验证手机号码格式
		if(!RegUtils.checkMoblie(user.getMobile())) {
			return returnResult.returnFail("手机号码格式不正确！");
		}
		
		//验证身份证号码格式格式
		if(!RegUtils.checkIdentityCode(user.getIdentityCode())) {
			return returnResult.returnFail("手机号码格式不正确！");
		}
		
		return returnResult.returnSuccess();
	}
}
