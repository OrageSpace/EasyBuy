package cn.easybuy.service.product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import cn.easybuy.dao.product.ProductCategoryDao;
import cn.easybuy.dao.product.ProductCategoryDaoImpl;
import cn.easybuy.entity.ProductCategory;
import cn.easybuy.parames.ProductCategoryParames;
import cn.easybuy.uitls.DataBaseUtil;
import cn.easybuy.uitls.ProductCategoryVo;
/**
  * 商品分类业务逻辑接口实现类
 * @author 青云 .ltd
 *
 */
public class ProductCategoryServiceImpl implements ProductCategoryService{
	private Connection conn=null;//数据库连接对象
	private ProductCategoryDao pcd;
	
	private Logger logger=Logger.getLogger(ProductCategoryServiceImpl.class.getName());

	@Override
	public List<ProductCategory> queryAllProductCategory(String parentId) {
		List<ProductCategory> pcList=null;
		
		try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			//实例化商品分类数据访问实现类
			pcd=new ProductCategoryDaoImpl(conn);
			
			//调用查询所有商品分类信息的方法
			pcList=pcd.queryAllProductCategory(parentId); 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//在业务逻辑完成后，释放数据库连接对象
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return pcList;
	}

	@Override
	public List<ProductCategoryVo> queryAllProductCategory() {
		List<ProductCategoryVo> pcv1List=new ArrayList<ProductCategoryVo>();

		try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			//实例化商品分类数据访问实现类
			pcd=new ProductCategoryDaoImpl(conn);
			
			ProductCategoryVo pcv1=null;
			//一级分类集合
			List<ProductCategory> pc1=pcd.queryAllProductCategory(null);
			
			//遍历查询到的数据
			for (ProductCategory productCategory : pc1) {
				pcv1=new ProductCategoryVo();
				
				pcv1.setProductCategory(productCategory);
				
				//获取其子项的信息
				ProductCategoryVo pcv2=null;
				
				//二级分类
				List<ProductCategoryVo> pcv2List=new ArrayList<ProductCategoryVo>();
				//查询二级分类信息
				List<ProductCategory> pc2=pcd.queryAllProductCategory(pcv1.getProductCategory().getId()+"");
				
				//遍历查询到的二级分类信息
				for (ProductCategory productCategory2 : pc2) {
					pcv2=new ProductCategoryVo();
					
					pcv2.setProductCategory(productCategory2);
					
					//获取其子项的信息
					ProductCategoryVo pcv3=null;
					
					//三级分类
					List<ProductCategoryVo> pcv3List=new ArrayList<ProductCategoryVo>();
					//查询三级分类信息
					List<ProductCategory> pc3=pcd.queryAllProductCategory(pcv2.getProductCategory().getId()+"");
					
					//遍历查询到的三级分类信息
					for (ProductCategory productCategory3 : pc3) {
						pcv3=new ProductCategoryVo();
						pcv3.setProductCategory(productCategory3);
						System.out.println(pcv3.getProductCategory().getName());
						pcv3.setProductCategoryVoList(null);
						pcv2List.add(pcv3);
					}
					
					pcv1.setProductCategory(productCategory);
					pcv2.setProductCategoryVoList(pcv2List);
					
					pcv2List.add(pcv2);
				}
				
				pcv1.setProductCategoryVoList(pcv2List);
				pcv1List.add(pcv1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//在业务逻辑完成后，释放数据库连接对象
			DataBaseUtil.closeAll(conn, null, null);
		}
		return pcv1List;
	}

	@Override
	public List<ProductCategory> pageQueryProductCategory(ProductCategoryParames parames) throws Exception {
		 List<ProductCategory> pcList=new ArrayList<ProductCategory>();
		
		 try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			//实例化商品分类数据访问实现类
			pcd=new ProductCategoryDaoImpl(conn);
			
			//调用查询所有商品分类信息的方法
			pcList=pcd.pageQueryProductCategory(parames);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//在业务逻辑完成后，释放数据库连接对象
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return pcList;
	}

	@Override
	public int getProductCategoryCount() throws Exception {
		int count=0;
		
		try {
			//获取数据库连接
			conn=DataBaseUtil.getConnection();
			//实例化商品分类数据访问实现类
			pcd=new ProductCategoryDaoImpl(conn);
			
			count=pcd.getProductCategoryCount(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//在业务逻辑完成后，释放数据库连接对象
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return count;
	}
}
