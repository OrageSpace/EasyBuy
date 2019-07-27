package cn.easybuy.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.easybuy.uitls.EmptyUtil;
import cn.easybuy.uitls.ReturnResult;

public abstract class AbstracterServlet extends HttpServlet {
	/**
	 * 抽象类 用于获取需要的实际类型
	 * @return 类的实例
	 */
	public abstract Class getServletClass();
	
	private Logger logger=Logger.getLogger(AbstracterServlet.class.getName());
	
	@Override
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
		
		//获取请求中的参数值 action的value
		String action=request.getParameter("action");
		Method method=null;
		Object result=null;
		
		try {
			if(EmptyUtil.isEmpty(action)) {
				request.getRequestDispatcher("/pre/index.jsp").forward(request, response);
			}else {
				method=this.getServletClass().getDeclaredMethod(action, HttpServletRequest.class,HttpServletResponse.class);
				//使用invoke()调用方法
				result=method.invoke(this, request,response);
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			//没有找到相关类 跳转至404页面
			request.getRequestDispatcher("404or500.jsp?status=404").forward(request, response);
		}  catch (Exception e) {
			e.printStackTrace();
			if(EmptyUtil.isEmpty(result)) {
				//如果发生异常 并且result为空则跳转至500页面
				request.getRequestDispatcher("404or500.jsp?status=500").forward(request, response);
			}else {
				if(result instanceof String) {
					request.getRequestDispatcher("404or500.jsp?status=500").forward(request, response);
				}else {
					ReturnResult returnResult=new ReturnResult();
					
					this.writeDate(returnResult.returnFail("系统错误,请联系系统管理员(QQ:1787286700)!"), response);
				}
			}
		}
		
		this.toView(request,response,result);
	}
	
	/**
	 * 处理结果的方法 如果result对象的类型是String的话就跳转页面
	 * @param request 
	 * @param response
	 * @param result
	 */
	private void toView(HttpServletRequest request, HttpServletResponse response, Object result) {
		if(result instanceof String) {//如果result为String类型 则表示需要跳转页面
			String path=result+".jsp";
			this.toView(request, response, result);
		}else {
			this.writeDate(result,response);
		}
	}

	/**
	 * 向客户端输出数据的方法
	 * @param result 
	 * @param response
	 */
	private void writeDate(Object result, HttpServletResponse response) {
		PrintWriter out=null;
		
		try {
			if(response!=null) {
				out=response.getWriter();
				//使用FastJSON将对象转换成JSON类型
				String str=JSONObject.toJSONString(result);
				out.print(str);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			out.close();
		}
	}
}
