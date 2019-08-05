package cn.easybuy.service.city;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.easybuy.dao.city.CityDao;
import cn.easybuy.dao.city.CityDaoImpl;
import cn.easybuy.entity.City;
import cn.easybuy.uitls.CityVo;
import cn.easybuy.uitls.DataBaseUtil;

public class CityServiceImpl implements CityService {
	
	private Connection conn;//数据库连接对象
	private CityDao cityDao=null;

	@Override
	public List<CityVo> queryAllCityInfo() {
		List<CityVo> cvList1=new ArrayList<CityVo>();//城市层级分类集合
		List<City> city1=queryAllCityInfo(-1);//获取一级城市分类
		CityVo cv1=null;
		
		try {
			for (City item1 : city1) {
				cv1=new CityVo();
				cv1.setCity(item1);
				
				List<CityVo> cvList2=new ArrayList<CityVo>();
				//根据父级id获取city2的数据
				List<City> city2=queryAllCityInfo(item1.getId());
				CityVo cv2=null;
				
				for (City item2 : city2) {
					cv2=new CityVo();
					cv2.setCity(item2);
					
					List<CityVo> cvList3=new ArrayList<CityVo>();
					//根据父级id获取city3的数据
					List<City> city3=queryAllCityInfo(item2.getId());
					CityVo cv3=null;
					
					for (City item3 : city3) {
						cv3=new CityVo();
						cv3.setCity(item3);
						
						cvList3.add(cv3);
					}
					
					cv2.setItems(cvList3);
					cvList2.add(cv2);
				}
				
				cv1.setItems(cvList2);
				cvList1.add(cv1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cvList1;
	}

	@Override
	public List<City> queryAllCityInfo(int parentId) {
		List<City> cities=null;
		
		try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			//实例化城市信息数据访问类接口
			cityDao=new CityDaoImpl(conn);
			
			if(parentId<0) {
				parentId=100000;
			}
			
			cities=cityDao.queryAllCityInfo(parentId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//业务完成后统一关闭数据库连接
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return cities;
	}
}
