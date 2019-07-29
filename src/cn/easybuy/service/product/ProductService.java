package cn.easybuy.service.product;

import java.util.List;

import cn.easybuy.entity.Product;
import cn.easybuy.parames.ProductPrames;

/**
  * 商品信息业务逻辑接口
 * @author 青云 .ltd
 *
 */
public interface ProductService {
	/**
	 * 查询商品信息的方法
	 * @param params 商品信息查询参数辅助类对象
	 * @return 商品信息列表
	 */
	public List<Product> queryProductList(ProductPrames params);
	
	/**
	  * 查询商品的总数量
	 * @param params 商品信息查询参数辅助类对象
	 * @return 商品总记录数
	 */
	public int getProductCount(ProductPrames params);
}
