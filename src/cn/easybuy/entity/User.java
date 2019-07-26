package cn.easybuy.entity;
/**
 * 用户实体类
 * @author 青云 .ltd
 *
 */
public class User {
	private int id;//用户编号
	private String userName;//用户真实姓名
	private String loginName;//用户登录用户名
	private String password;//用户密码
	private int sex;//性别
	private String identityCode;//身份证号
	private String email;//电子邮箱
	private String mobile;//手机号码
	private int type;//用户类型
	
	public int getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public String getLoginName() {
		return loginName;
	}
	public String getPassword() {
		return password;
	}
	public int getSex() {
		return sex;
	}
	public String getIdentityCode() {
		return identityCode;
	}
	public String getEmail() {
		return email;
	}
	public String getMobile() {
		return mobile;
	}
	public int getType() {
		return type;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setType(int type) {
		this.type = type;
	}
}
