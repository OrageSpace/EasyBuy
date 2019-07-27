package cn.easybuy.dao.product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.easybuy.dao.BaseDao;
import cn.easybuy.entity.ProductCategory;
import cn.easybuy.uitls.DataBaseUtil;

/**
 * 商品分类数据访问接口实现类
 * @author 青云 .ltd
 *
 */
public class ProductCategoryDaoImpl extends BaseDao implements ProductCategoryDao{

	public ProductCategoryDaoImpl(Connection conn) {
		super(conn);
	}
	
	private Logger logger=Logger.getLogger(ProductCategory.class.getName());

	@Override
	public List<ProductCategory> queryAllProductCategory(String parentId)
			throws SQLException {
		ResultSet rs=null;//结果集对象
		List<ProductCategory> productCategories=new ArrayList<ProductCategory>();
		ProductCategory productCategory=null;
		
		StringBuffer sql=new StringBuffer("SELECT `id`,`name`,`parentId`,`type`,`iconClass` FROM `easybuy_product_category` WHERE 1=1");
		
		if(parentId!=null&&!"".equals(parentId)) {//判断是否有其他参数
			sql.append(" and `type`=?");
			rs=super.executeQuery(sql.toString(), parentId);
		}else {
			rs=super.executeQuery(sql.toString(),null);
		}
		
		try {
			//循环读取结果集中的数据
			while (rs.next()) {
				productCategory=new ProductCategory();
				productCategory.setId(rs.getInt("id"));
				productCategory.setName(rs.getString("name"));
				productCategory.setParentId(rs.getInt("parentId"));
				productCategory.setType(rs.getInt("type"));
				
				productCategories.add(productCategory);
			}
		} catch (SQLException e) {
			throw e;
		}finally {
			//释放预编译sql命令执行对象 与结果集对象 数据库连接对象需要在业务逻辑层关闭
			DataBaseUtil.closeAll(null, pstmt, rs);
		}
		
		return productCategories;
	}

}
