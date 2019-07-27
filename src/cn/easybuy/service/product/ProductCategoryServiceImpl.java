package cn.easybuy.service.product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import cn.easybuy.dao.product.ProductCategoryDao;
import cn.easybuy.dao.product.ProductCategoryDaoImpl;
import cn.easybuy.entity.ProductCategory;
import cn.easybuy.uitls.DataBaseUtil;
/**
  * 商品分类业务逻辑接口实现类
 * @author 青云 .ltd
 *
 */
public class ProductCategoryServiceImpl implements ProductCategoryService{
	private Connection conn=null;//数据库连接对象
	private ProductCategoryDao pcd;
	
	private Logger logger=Logger.getLogger(ProductCategoryServiceImpl.class.getName());

	@Override
	public List<ProductCategory> queryAllProductCategory(String parentId) {
		List<ProductCategory> pcList=null;
		
		try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			//实例化商品分类数据访问实现类
			pcd=new ProductCategoryDaoImpl(conn);
			
			//调用查询所有商品分类信息的方法
			pcList=pcd.queryAllProductCategory(parentId); 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//在业务逻辑完成后，释放数据库连接对象
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return pcList;
	}
}
