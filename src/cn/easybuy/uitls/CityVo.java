package cn.easybuy.uitls;
/**
 * 城市信息VO类
 * @author 青云 .ltd
 *
 */

import java.util.List;

import cn.easybuy.entity.City;

public class CityVo {
	private City city;//城市对象
	private List<CityVo> items;//所属子项
	
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public List<CityVo> getItems() {
		return items;
	}
	public void setItems(List<CityVo> items) {
		this.items = items;
	}
}
