package cn.easybuy.entity;
/**
 * 商品分类实体类
 * @author 青云 .ltd
 *
 */
public class ProductCategory {
	private int id;//分类编号
	private String name;//分类名称
	private int parentId;//父分类编号
	private int type;//类型
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getParentId() {
		return parentId;
	}
	public int getType() {
		return type;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public void setType(int type) {
		this.type = type;
	}
}
