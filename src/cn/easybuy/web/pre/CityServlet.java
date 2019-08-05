package cn.easybuy.web.pre;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.easybuy.service.city.CityService;
import cn.easybuy.service.city.CityServiceImpl;
import cn.easybuy.uitls.CityVo;
import cn.easybuy.uitls.MemcachedUtils;
import cn.easybuy.uitls.ReturnResult;
import cn.easybuy.web.AbstracterServlet;
/**
 * 城市信息获取控制类
 * @author 青云 .ltd
 *
 */
@WebServlet(urlPatterns= {"/City"},name="City")
public class CityServlet extends AbstracterServlet {
	
	private CityService cityService=null;

	@Override
	public Class getServletClass() {
		return CityServlet.class;
	}
	
	@Override
	public void init() throws ServletException {
		cityService=new CityServiceImpl();
	}
	
	/**
	 * 获取省市信息的方法
	 * @param request
	 * @param response
	 * @return ajax请求结果对象
	 * @throws Exception
	 */
	public ReturnResult getCityInfo(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		ReturnResult returnResult=new ReturnResult();
		//获取Memcached中保存的City信息
		String cityJSON=(String)MemcachedUtils.get("cityJSON");
		
		if(cityJSON==null||cityJSON.equals("")) {//如果Memcachedutils中没有数据则向数据库中查询
			List<CityVo> citys=cityService.queryAllCityInfo();
			cityJSON=JSONObject.toJSONString(citys);
			
			//将查询到的cityJSON保存到Memcached中去
			MemcachedUtils.add("cityJSON", cityJSON);
		}
		
		return returnResult.returnSuccess(cityJSON, "请求成功");
	}
}
