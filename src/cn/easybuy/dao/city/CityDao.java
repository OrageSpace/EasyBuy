package cn.easybuy.dao.city;
/**
 * 城市信息数据访问接口
 * @author 青云 .ltd
 *
 */

import java.util.ArrayList;
import java.util.List;

import cn.easybuy.entity.City;

public interface CityDao {
	/**
	  * 根据父级id获取所有城市信息
	 * @param parentId
	 * @return 城市信息集合对象
	 * @throws Exception 
	 */
	public List<City> queryAllCityInfo(int parentId) throws Exception;
}
