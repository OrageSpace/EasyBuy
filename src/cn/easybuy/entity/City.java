package cn.easybuy.entity;
/**
 * 城市实体类
 * @author 青云 .ltd
 *
 */
public class City {
	private int id;//编号
	private String name;//名称
	private int parentid;//所属父级编号
	private String shortName;//缩写
	private int leveltype;//层级编号
	private int cityCode;//行政区代码
	private int zipcode;//邮政编码
	private String lng;//纬度
	private String lat;//经度
	private String PinYin;//名称拼音全称
	private int status;//状态
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public int getLeveltype() {
		return leveltype;
	}
	public void setLeveltype(int leveltype) {
		this.leveltype = leveltype;
	}
	public int getCityCode() {
		return cityCode;
	}
	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getPinYin() {
		return PinYin;
	}
	public void setPinYin(String pinYin) {
		PinYin = pinYin;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
