package cn.easybuy.entity;
/**
 * 资讯实体类
 * @author 青云 .ltd
 *
 */
public class News {
	private int id;//资讯编号
	private String title;//资讯标题
	private String content;//资讯
	private String createTime;//录入时间
	
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
