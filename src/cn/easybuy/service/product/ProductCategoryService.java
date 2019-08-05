package cn.easybuy.service.product;

import java.sql.SQLException;
import java.util.List;

import cn.easybuy.entity.ProductCategory;
import cn.easybuy.parames.ProductCategoryParames;
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
	

	/**
	 * 获取商品分类信息的总记录数的方法
	 * @return 商品分类信息的总记录数
	 * @throws Exception
	 */
	public int getProductCategoryCount()throws Exception;
	
	/**
	  * 分页查询所有的商品分类信息
	 * @param parames
	 * @return 商品分类信息集合对象
	 * @throws Exception
	 */
	public List<ProductCategory> pageQueryProductCategory(ProductCategoryParames parames)throws Exception;
}
