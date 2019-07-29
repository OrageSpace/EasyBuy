package cn.easybuy.dao.product;

import java.util.List;

import cn.easybuy.entity.Product;
import cn.easybuy.parames.ProductPrames;

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
	public List<Product> queryProductList(ProductPrames params) throws Exception;
	
	/**
	  * 查询商品的总数量
	 * @param params 商品信息查询参数辅助类对象
	 * @return 商品总记录数
	 * @throws Exception 
	 */
	public int getProductCount(ProductPrames params) throws Exception;
}
