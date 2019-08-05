package cn.easybuy.web.backed;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.ProductCategory;
import cn.easybuy.parames.ProductCategoryParames;
import cn.easybuy.service.product.ProductCategoryService;
import cn.easybuy.service.product.ProductCategoryServiceImpl;
import cn.easybuy.uitls.EmptyUtil;
import cn.easybuy.uitls.Pager;
import cn.easybuy.web.AbstracterServlet;
/**
  * 后台管理--商品分类业务控制类
 * @author 青云 .ltd
 *
 */
@WebServlet(urlPatterns= {"/Category"},name="Category")
public class AdminProductCategoryServlet extends AbstracterServlet {
	
	private ProductCategoryService pcs=null;

	@Override
	public Class getServletClass() {
		return AdminProductCategoryServlet.class;
	}
	
	@Override
	public void init() throws ServletException {
		pcs=new ProductCategoryServiceImpl();
	}
	
	public String toProductCategory(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		//获取请求中的参数值
		String currPageNoStr=EmptyUtil.isNotEmpty(request.getParameter("currPageNo"))
				?request.getParameter("currPageNo"):"1";
		String sort=EmptyUtil.isNotEmpty(request.getParameter("sort"))
				?request.getParameter("sort"):null;
		
		ProductCategoryParames parames=new ProductCategoryParames();
		parames.openPage((Integer.valueOf(currPageNoStr)), 15);
		parames.setSort(sort);
		
		//获取总记录数
		int count=pcs.getProductCategoryCount();
		
		List<ProductCategory> pcList=pcs.pageQueryProductCategory(parames);
		
		Pager page=new Pager(parames.getStartIndex(), count, parames.getPageSize(), pcList);
		page.setUrl("/Category");
		
		//将page对象保存到request作用域中
		request.setAttribute("pageObj", page);
		request.setAttribute("action", "toProductCategory");
				
		return "/backend/member/member_Category";
	}
}
