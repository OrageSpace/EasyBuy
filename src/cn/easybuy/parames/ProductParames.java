package cn.easybuy.parames;
/**
 * 商品信息查询参数辅助类
 * @author 青云 .ltd
 *
 */
public class ProductParames {
	private boolean isPage=false;//是否分页
	private int stateIndex;//开始行数
	private int pageSize;//页面大小
	private String keyWords;//查询关键字
	private String sort;//按那一列排序
	private Integer categoryId;//所属分类的id
	
	public boolean isPage() {
		return isPage;
	}
	public void setPage(boolean isPage) {
		this.isPage = isPage;
	}
	public int getStateIndex() {
		return stateIndex;
	}
	public void setStateIndex(int stateIndex) {
		this.stateIndex = stateIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * 开启分页查询的方法
	 * @param stateIndex 初始行数
	 * @param pageSize 页面大小
	 */
	public void openPage(int stateIndex,int pageSize) {
		this.isPage=true;
		this.stateIndex=stateIndex;
		this.pageSize=pageSize;
	}
}
