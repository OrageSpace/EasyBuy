package cn.easybuy.service.city;
/**
 * 城市信息业务逻辑接口
 * @author 青云 .ltd
 *
 */

import java.util.List;

import cn.easybuy.entity.City;
import cn.easybuy.uitls.CityVo;

public interface CityService {
	/**
	  * 根据父级编号获取所有城市信息的方法
	 * @return 城市信息VO类集合对象
	 */
	public List<City> queryAllCityInfo(int parentId);
	
	/**
	  * 获取所有城市信息的方法
	 * @return 城市信息VO类集合对象
	 */
	public List<CityVo> queryAllCityInfo();
}
