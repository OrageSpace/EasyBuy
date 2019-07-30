package cn.easybuy.service.product;

import java.sql.Connection;
import java.util.List;

import cn.easybuy.dao.product.ProductDao;
import cn.easybuy.dao.product.ProductDaoImpl;
import cn.easybuy.entity.Product;
import cn.easybuy.parames.ProductPrames;
import cn.easybuy.uitls.DataBaseUtil;
/**
 * 商品信息业务逻辑处理接口实现类
 * @author 青云 .ltd
 *
 */
public class ProductServiceImpl implements ProductService {
	
	private Connection conn=null;//数据库连接对象
	//注入商品信息访问类对象
	private ProductDao productDao=null;

	@Override
	public List<Product> queryProductList(ProductPrames params) {
		List<Product> products=null;
		
		try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			//实例化商品信息数据访问对象
			productDao=new ProductDaoImpl(conn);
			
			products=productDao.queryProductList(params);
		}  catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			//在业务完成后统一关闭 数据库连接对象
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return products;
	}

	@Override
	public int getProductCount(ProductPrames params) {
		int count=0;
		
		try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			//实例化商品信息数据访问对象
			productDao=new ProductDaoImpl(conn);
			
			count=productDao.getProductCount(params);
		}  catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			//在业务完成后统一关闭 数据库连接对象
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return count;
	}

	@Override
	public Product getProductById(int id){
		Product product=null;

		try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			//实例化商品信息数据访问对象
			productDao=new ProductDaoImpl(conn);
			
			product=productDao.getProductById(id);
		}  catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			//在业务完成后统一关闭 数据库连接对象
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return product;
	}

}
