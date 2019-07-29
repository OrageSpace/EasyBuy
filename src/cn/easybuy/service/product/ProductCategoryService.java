package cn.easybuy.service.product;

import java.sql.SQLException;
import java.util.List;

import cn.easybuy.entity.ProductCategory;
import cn.easybuy.uitls.ProductCategoryVo;

/**
 * 
 * @author 青云 .ltd
 *
 */
public interface ProductCategoryService {
	/**
	  * 根据父级id 获取所有商品分类的方法
	 * @param parentId 所属父级分类id
	 * @return 查询到的商品分类集合
	 * @throws SQLException 
	 */
	public List<ProductCategory> queryAllProductCategory(String parentId);
	
	/**
	  * 获取所有商品分类的方法
	 * @return ProductCategVo集合对象
	 */
	public List<ProductCategoryVo> queryAllProductCategory();
}
