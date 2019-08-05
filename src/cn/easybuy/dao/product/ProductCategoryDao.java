package cn.easybuy.dao.product;
/**
 * 商品分类数据访问接口
 * @author 青云 .ltd
 *
 */

import java.sql.SQLException;
import java.util.List;

import cn.easybuy.entity.ProductCategory;
import cn.easybuy.parames.ProductCategoryParames;

public interface ProductCategoryDao{
	/**
	  *  根据父级id 获取所有商品分类的方法
	 * @param parentId 所属父级分类id
	 * @return 查询到的商品分类集合
	 * @throws SQLException 
	 */
	public List<ProductCategory> queryAllProductCategory(String parentId) throws SQLException;
	
	/**
	 * 获取商品分类信息的总记录数的方法
	 * @return 商品分类信息的总记录数
	 * @throws Exception
	 */
	public int getProductCategoryCount(ProductCategoryParames parames)throws Exception;
	
	/**
	  * 分页查询所有的商品分类信息
	 * @param parames
	 * @return 商品分类信息集合对象
	 * @throws Exception
	 */
	public List<ProductCategory> pageQueryProductCategory(ProductCategoryParames parames)throws Exception;
}
