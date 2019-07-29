package cn.easybuy.uitls;
/**
  * 用于处理商品分类信息的VO类 因为商品分类有层级关系 所以使用VO类来简化处理过程
 * @author 青云 .ltd
 *
 */

import java.util.List;

import cn.easybuy.entity.ProductCategory;

public class ProductCategoryVo {
	private ProductCategory productCategory;//商品分类对象
	private List<ProductCategoryVo> productCategoryVoList;//所属子项
	
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	public List<ProductCategoryVo> getProductCategoryVoList() {
		return productCategoryVoList;
	}
	public void setProductCategoryVoList(List<ProductCategoryVo> productCategoryVoList) {
		this.productCategoryVoList = productCategoryVoList;
	}
}
