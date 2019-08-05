package cn.easybuy.uitls;

import java.util.UUID;

/**
 * 编号自动生成工具类
 * @author 青云 .ltd
 *
 */
public class StringUtils {
	/**
	 * 获取UUID的方法
	 * @return  UUID
	 */
	public static String getRandomUUID() {
		//获取自动生成的UUID信息
		UUID uuid=UUID.randomUUID();
		String uuidStr=uuid.toString().replace("-", "").toUpperCase();
		
		return uuidStr;
	}
}
