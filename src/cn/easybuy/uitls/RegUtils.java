package cn.easybuy.uitls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * @author 青云 .ltd
 *
 */
public class RegUtils {
	
	static String emailReg="^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9] {2,4})$";
	static String mobileReg="^\\d{5,11}&";
	static String identityCodeReg="^\\w{18}$";
	
	/**
	 * 验证邮箱格式是否正确
	 * @param email
	 * @return 格式是否正确 ture正确  false不正确
	 */
	public static boolean checkEmail(String email) {
		Pattern pattern=Pattern.compile(emailReg);
		Matcher matcher=pattern.matcher(email);
		boolean flag=true;
		
		if(EmptyUtil.isEmpty(email)&&!matcher.matches()) {
			flag=false;
		}
		
		return flag;
	}
	
	/**
	 * 验证手机号码格式是否正确
	 * @param moblie
	 * @return 格式是否正确 ture正确  false不正确
	 */
	public static boolean checkMoblie(String moblie) {
		Pattern pattern=Pattern.compile(mobileReg);
		Matcher matcher=pattern.matcher(moblie);
		boolean flag=true;
		
		if(EmptyUtil.isEmpty(moblie)&&!matcher.matches()) {
			flag=false;
		}
		
		return flag;
	}
	
	/**
	 * 验证身份证号码格式是否正确
	 * @param identityCode
	 * @return 格式是否正确 ture正确  false不正确
	 */
	public static boolean checkIdentityCode(String identityCode) {
		Pattern pattern=Pattern.compile(identityCodeReg);
		Matcher matcher=pattern.matcher(identityCode);
		boolean flag=true;
		
		if(EmptyUtil.isEmpty(identityCode)&&!matcher.matches()) {
			flag=false;
		}
		
		return flag;
	}
}
