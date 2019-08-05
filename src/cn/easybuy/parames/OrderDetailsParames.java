package cn.easybuy.parames;

import cn.easybuy.entity.OrderDetail;
/**
 * 
 * @author 青云 .ltd
 *
 */
public class OrderDetailsParames extends OrderDetail {
	private boolean isPage=false;//是否进行分页
	private int startIndex;//开始页码
	private int pageSize;//页面大小
	private String sort;//排序字段
	
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
}
