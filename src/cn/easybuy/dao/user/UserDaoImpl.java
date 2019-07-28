package cn.easybuy.dao.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cn.easybuy.dao.BaseDao;
import cn.easybuy.entity.User;
import cn.easybuy.uitls.DataBaseUtil;
import cn.easybuy.uitls.EmptyUtil;
/**
 * 用户数据库访问/操作接口实现类
 * @author 青云 .ltd
 *
 */
public class UserDaoImpl extends BaseDao implements UserDao {

	public UserDaoImpl(Connection conn) {
		super(conn);
	}
	
	private static Logger logger=Logger.getLogger(UserDaoImpl.class.getName());

	@Override
	public User getUserInfoByLoginName(String loginName) throws Exception{
		ResultSet rs=null;//结果集对象
		User user=null;
	
		try {
			//查询sql语句
			StringBuffer sql=new StringBuffer("SELECT `id`,`userName`,`loginName`,`password`,`sex`,`identityCode`,`email`,`mobile`,`type` FROM `easybuy_user` WHERE 1=1");
			
			if(!EmptyUtil.isEmpty(loginName)) {
				sql.append(" AND `loginName`=?");
				rs=super.executeQuery(sql.toString(),loginName);
			}else {
				rs=super.executeQuery(sql.toString(), null);
			}
			
			//读取结果集中的数据
			if(rs.next()) { //获取第一行第一列的数据
				user=this.tableToClass(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源  数据库连接对象需要在业务层关闭
			DataBaseUtil.closeAll(null, pstmt, rs);
		}
	
		return user;
	}

	private User tableToClass(ResultSet rs) throws SQLException {
		
		User user=new User();
		user.setEmail(rs.getString("email"));
		user.setId(rs.getInt("id"));
		user.setIdentityCode("identityCode");
		user.setLoginName("loginName");
		user.setMobile(rs.getString("mobile"));
		user.setPassword(rs.getString("password"));
		user.setSex(rs.getInt("sex"));
		user.setType(rs.getInt("type"));
		user.setUserName(rs.getString("userName"));
		
		return user;
	}

	@Override
	public int register(User user) throws SQLException {
		int result=0;
		
		StringBuilder sql=new StringBuilder("INSERT INTO `easybuy_user`(`loginName`,`userName`,`password`,`sex`,`identityCode`,`email`,`mobile`,`type`) ");
		sql.append(" VALUES(?,?,?,?,?,?,?,?)");
		
		result=super.executeUpdate(sql.toString(), user.getLoginName(),user.getUserName(),
				user.getPassword(),user.getSex(),user.getIdentityCode(),user.getEmail(),
				user.getMobile(),user.getType());
		
		return result;
	}

}
