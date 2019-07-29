package cn.easybuy.uitls;

import java.util.List;

import cn.easybuy.entity.Product;

/**
 * 分页查询辅助类
 * @author 青云 .ltd
 *
 */
public class Pager {
	private int currPageNo;//当前页码
	private int totalCount;//总记录数
	private int pageSize;//页面大小
	private int totalPageCount;//总记录数
	private String url;//请求页面地址
	private List<Product> list;//查询到的数据
	
	public Pager(int currPageNo,int totalCount,int pageSize,List<Product> list) {
		this.currPageNo=currPageNo;
		this.totalCount=totalCount;
		this.pageSize=pageSize;
		this.list=list;
		
		this.totalPageCount=this.totalCount%this.pageSize==0
				?(this.totalCount/this.pageSize):(this.totalCount/this.pageSize)+1;
		
		if(this.totalCount%this.pageSize<0) {
			this.totalPageCount=0;
		}
	}

	public int getCurrPageNo() {
		return currPageNo;
	}

	public void setCurrPageNo(int currPageNo) {
		this.currPageNo = currPageNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
