package cn.easybuy.uitls;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * md5加密工具类
 * @author 青云 .ltd
 *
 */
public class SecrityUtils {
	
	/**
	 * md5加密的方法
	 * @param value 要加密的值
	 * @return md5加密后的值
	 */
	public static String md5Hex(String value) {
		return DigestUtils.md5Hex(value);
	}
	
	/**
	 * 对密码进行3次md5加密操作
	 * @param value 要加密的值
	 * @return md5加密后的值
	 */
	public static String md5Hex3(String value) {
		for (int i = 0; i < 3; i++) {
			value=DigestUtils.md5Hex(value);
		}
		return value;
	}
}
