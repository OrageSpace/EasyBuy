package cn.easybuy.parames;

import cn.easybuy.entity.ProductCategory;

/**
  * 商品分类查询参数 辅助类
 * @author 青云 .ltd
 *
 */
public class ProductCategoryParames extends ProductCategory{
	private boolean isPage=false;//是否进行分页
	private int startIndex;//查询起始页数
	private int pageSize;//页面大小
	private String sort;//排序字段
	
	/**
	 * 开启分页查询的方法
	 * @param stateIndex 初始行数
	 * @param pageSize 页面大小
	 */
	public void openPage(int startIndex,int pageSize) {
		this.isPage=true;
		this.startIndex=startIndex;
		this.pageSize=pageSize;
	}
	public boolean isPage() {
		return isPage;
	}
	public void setPage(boolean isPage) {
		this.isPage = isPage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
}
