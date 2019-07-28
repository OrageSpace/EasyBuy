package cn.easybuy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
/**
  * 请求过滤器类
 * @author 青云 .ltd
 *
 */
@WebFilter(urlPatterns= {"/*"},filterName="EncodeFilter",initParams= {@WebInitParam(name="encode", value ="UTF-8")})
public class EncodeFilter implements Filter {
	
	private String encode=null;

	@Override
	public void destroy() {
		encode=null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		if(request.getCharacterEncoding()==null) {//通过判断可以提升代码的效率  可以避免每次请求都执行该代码
			request.setCharacterEncoding(encode);
		}
		
		response.setCharacterEncoding(encode);
		
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//获取初始化参数的值
		String encode=filterConfig.getInitParameter("encode");
		
		if(this.encode==null) {
			this.encode=encode;
		}
	}

}
