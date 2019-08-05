package cn.easybuy.dao.product;

import java.util.List;

import cn.easybuy.entity.Product;
import cn.easybuy.parames.ProductParames;

/**
 * 商品信息数据库访问接口
 * @author 青云 .ltd
 *
 */
public interface ProductDao {
	/**
	 * 查询商品信息的方法
	 * @param params 商品信息查询参数辅助类对象
	 * @return 商品信息列表
	 * @throws Exception 
	 */
	public List<Product> queryProductList(ProductParames params) throws Exception;
	
	/**
	  * 查询商品的总数量
	 * @param params 商品信息查询参数辅助类对象
	 * @return 商品总记录数
	 * @throws Exception 
	 */
	public int getProductCount(ProductParames params) throws Exception;
	
	/**
	  * 根据商品id获取商品信息的方法
	 * @param id 商品id
	 * @return 商品对象
	 * @throws Exception
	 */
	public Product getProductById(int id) throws Exception;
	
	/**
	 * 更新库存信息的方法
	 * @param id 商品编号
	 * @param quantity 修改后的数量
	 * @return 
	 * @throws Exception
	 */
	public int updateProductInfo(int id,int quantity) throws Exception;
}
