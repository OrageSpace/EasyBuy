package cn.easybuy.dao.city;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.BaseDao;
import cn.easybuy.entity.City;
import cn.easybuy.uitls.DataBaseUtil;
/**
 * 城市信息数据访问接口实现类
 * @author 青云 .ltd
 *
 */
public class CityDaoImpl extends BaseDao implements CityDao {

	public CityDaoImpl(Connection conn) {
		super(conn);
	}

	@Override
	public List<City> queryAllCityInfo(int parentId) throws Exception{
		ResultSet rs=null;//结果集对象
		List<City> cities=new ArrayList<City>();
		
		try {
			StringBuffer sql=new StringBuffer("SELECT `id`,`name`,`parentid`,`shortname`,`leveltype`,`citycode`,`zipcode`,`lng`,`lat`,`pinyin`,`status` FROM `city` WHERE 1=1");
			
			List<Object> params=new ArrayList<Object>();
			
			if(parentId>=0) {
				sql.append(" AND `parentid`=?");
				params.add(parentId);
			}
			
			rs=super.executeQuery(sql.toString(), params);
			
			//循环读取数据
			while (rs.next()) {
				cities.add(this.tableToClass(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源 数据库连接对象需要在业务逻辑层关闭
			DataBaseUtil.closeAll(null, pstmt, rs);
		}
		
		return cities;
	}

	/**
	 * 解析结果集中的城市信息的方法
	 * @param rs
	 * @return 城市信息对象
	 */
	private City tableToClass(ResultSet rs)
			throws Exception{
		City city=new City();
		
		city.setCityCode(rs.getInt("cityCode"));
		city.setId(rs.getInt("id"));
		city.setLat(rs.getString("lat"));
		city.setLeveltype(rs.getInt("leveltype"));
		city.setLng(rs.getString("lng"));
		city.setName(rs.getString("name"));
		city.setParentid(rs.getInt("parentid"));
		city.setPinYin(rs.getString("pinYin"));
		city.setShortName(rs.getString("shortName"));
		city.setStatus(rs.getInt("status"));
		city.setZipcode(rs.getInt("zipcode"));
		
		return city;
	}

}
