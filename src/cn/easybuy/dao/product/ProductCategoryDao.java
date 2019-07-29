package cn.easybuy.dao.product;
/**
 * 商品分类数据访问接口
 * @author 青云 .ltd
 *
 */

import java.sql.SQLException;
import java.util.List;

import cn.easybuy.entity.ProductCategory;

public interface ProductCategoryDao{
	/**
	  *  根据父级id 获取所有商品分类的方法
	 * @param parentId 所属父级分类id
	 * @return 查询到的商品分类集合
	 * @throws SQLException 
	 */
	public List<ProductCategory> queryAllProductCategory(String parentId) throws SQLException;
}
