package cn.easybuy.dao.product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.BaseDao;
import cn.easybuy.entity.Product;
import cn.easybuy.parames.ProductPrames;
import cn.easybuy.uitls.DataBaseUtil;
import cn.easybuy.uitls.EmptyUtil;

/**
 * 商品信息数据库访问实现类
 * @author 青云 .ltd
 *
 */
public class ProductDaoImpl extends BaseDao implements ProductDao {

	public ProductDaoImpl(Connection conn) {
		super(conn);
	}

	@Override
	public List<Product> queryProductList(ProductPrames params)
			throws Exception{
		ResultSet rs=null;//结果集对象
		List<Product> products=new ArrayList<Product>();
		
		try {
			StringBuffer sql=new StringBuffer("SELECT `id`,`name`,`description`,`price`,`stock`,`categoryLevel1`,`categoryLevel2`,`categoryLevel3`,`fileName`,`isDelete` FROM `easybuy_product` WHERE 1=1");
			
			//用于保存sql中的其它参数
			List<Object> mParames=new ArrayList<Object>();
			
			//判断sql是否还有其他查询条件限制
			if(EmptyUtil.isNotEmpty(params.getKeyWords())) {
				sql.append(" AND `name` like ? ");
				mParames.add("%"+params.getKeyWords()+"%");
			}
			
			if(params.getCategoryId()>0) {
				sql.append(" AND (`categoryLevel1`=? OR `categoryLevel2`=? OR `categoryLevel3`=?)");
				mParames.add(params.getCategoryId());
				mParames.add(params.getCategoryId());
				mParames.add(params.getCategoryId());
			}
			
			if(EmptyUtil.isNotEmpty(params.getSort())) {
				sql.append(" ORDER BY ?");
				mParames.add(params.getSort());
			}
			
			if(params.isPage()) {
				sql.append(" LIMIT ?,?");
				mParames.add(params.getStateIndex());
				mParames.add(params.getPageSize());
			}
			
			rs=super.executeQuery(sql.toString(), mParames);
			
			//循环读取结果集中的数据
			while (rs.next()) {
				products.add(this.tableToClass(rs));
			}
			
		}finally {
			//释放资源  数据库连接对象需要在业务逻辑层关闭
			DataBaseUtil.closeAll(null, pstmt, rs);
		}
		
		return products;
	}

	/**
	 * 遍历商品信息结果集的方法
	 * @param rs 结果集对象
	 * @return 商品信息对象
	 * @throws SQLException 
	 */
	private Product tableToClass(ResultSet rs) throws SQLException {
		Product product=new Product();
		
		product.setCategoryLevel1(rs.getInt("categoryLevel1"));
		product.setCategoryLevel2(rs.getInt("categoryLevel2"));
		product.setCategoryLevel3(rs.getInt("categoryLevel3"));
		product.setDescription(rs.getString("description"));
		product.setFileName(rs.getString("fileName"));
		product.setId(rs.getInt("id"));
		product.setIsDelete(rs.getInt("isDelete"));
		product.setName(rs.getString("name"));
		product.setPrice(rs.getDouble("price"));
		product.setStock(rs.getInt("stock"));
		
		return product;
	}

	@Override
	public int getProductCount(ProductPrames params) 
			throws Exception{
		ResultSet rs=null;//结果集对象
		int count=0;
		
		try {
			StringBuffer sql=new StringBuffer("SELECT COUNT(`id`) FROM `easybuy_product` WHERE 1=1");
			List<Object> mParames=new ArrayList<Object>();
			
			//判断sql是否还有其他查询条件限制
			if(EmptyUtil.isNotEmpty(params.getKeyWords())) {
				sql.append(" AND `name` LIKE ?");
				mParames.add("%"+params.getKeyWords()+"%");
			}
			
			if(params.getCategoryId()>0) {
				sql.append(" AND (`categoryLevel1`=? OR `categoryLevel2`=? OR `categoryLevel3`=?)");
				mParames.add(params.getCategoryId());
				mParames.add(params.getCategoryId());
				mParames.add(params.getCategoryId());
			}
			
			rs=super.executeQuery(sql.toString(), mParames);
			
			if(rs.next()) {
				count=rs.getInt(1);
			}
			
		} finally {
			//释放资源  数据库连接对象需要在业务逻辑层关闭
			DataBaseUtil.closeAll(null, pstmt, rs);
		}
		
		return count;
	}

}
