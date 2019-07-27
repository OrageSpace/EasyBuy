package cn.easybuy.uitls;

import java.io.File;
import java.util.Collection;
import java.util.Map;

/**
  * 通用非空判断工具类
 * @author 青云 .ltd
 *
 */
public class EmptyUtil {
	
	//重写它的默认构造函数 让他不能被其他类实例化
	private EmptyUtil() {}
	
	/**
	 * 判断对象是否为非空 ！isEmpty(objs...);
	 * @param objs
	 * @return true 为空，false非空
	 */
	public static boolean isNotEmptys(Object...objs) {
		return !isEmptys(objs);
	}
	
	/**
	 * 判断对象是否是空  传入的对象由一个为空就返回false
	 * @param objs 对象
	 * @return true 表示为空 ，false表示不为空
	 */
	public static boolean isEmptys(Object...objs) {
		//循环判断 如果有一个对象为空则返回true
		for (Object object : objs) {
			if(isEmpty(object)) {
				return true;
			}
		}
		
		//全部通过则返回false 表示没有为空的对象
		return false;
	}
	
	/**
	 * 判断对象数组是否为空 判断 objects==null || object.length==0
	 * @param objects 数组对象
	 * @return true为空，false为非空
	 */
	public static boolean isEmpty(Object[]objs) {
		return objs==null||objs.length==0;
	}
	
	/**
	 * 判断对象是否为非空 !isEmpty(objs...)
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
	
	/**
	 * 判断对象是否空 判断 object==null
	 * @param obj 对象
	 * @return true表示为空，false表示为非空
	 */
	public static boolean isEmpty(Object obj) {
		//判断对象类型是用instanceof关键字
		if(obj==null) {
			return true;
		}
		
		//字符串类型
		if(obj instanceof String) {
			return isEmpty((String)obj);
		}
		
		//字节数组
		if(obj instanceof byte[]) {
			return isEmpty((byte[])obj);
		}
		
		//泛型集合
		if(obj instanceof Collection<?>) {
			return isEmpty((Collection<?>)obj);
		}
		
		//Map集合
		if(obj instanceof Map<?, ?>) {
			return isEmpty((Map<?, ?>)obj);
		}
		
		//Object[]
		if(obj instanceof Object[]) {
			return isEmpty((Object[])obj);
		}
		
		//int[]
		if(obj instanceof int[]) {
			return isEmpty((int[])obj);
		}
		
		//字符序列
		if(obj instanceof CharSequence) {
			return isEmpty((CharSequence)obj);
		}
		
		return false;
	}
	
	/**
	  * 判断文件是否空 判断 file==null || file.exists()
	 * @param file 对象
	 * @return true为空,false为非空
	 */
	public static boolean isEmpty(File file) {
		return file==null||file.exists();
	}
	
	/**
	  * 判断字节数组是否为空 判断 b==null||b.length==0
	 * @param b 字节数组
	 * @return true为空，false非空
	 */
	public static boolean isEmpty(byte[]b) {
		return b==null||b.length==0;
	}
	
	/**
	  * 判断集合是否为空  判断 c==null||c.size()==0
	 * @param c 实现Colleaction接口的集合对象
	 * @return true为空 false非空
	 */
	public static boolean isEmpty(Collection<?> c) {
		return c==null||c.size()==0;
	}
	
	/**
	  * 返回Map是否为空 判断 m==null||m.size()==0
	 * @param m 实现Map接口集合
	 * @return true为空，false为非空
	 */
	public static boolean isEmpty(Map<?, ?> m) {
		return m==null||m.size()==0;
	}
	
	/**
	  * 返回int数组是否为空 判断 objects==null || objects.lenth==null
	 * @param objs
	 * @return
	 */
	public static boolean isEmpty(int[] objs) {
		return objs==null||objs.length==0;
	}
	
	/**
	 * 返回字符串是否为空 判断 str==null||str.length()==0 
	 * @param str 字符串
	 * @return true为空 ，false非空
	 */
	public static boolean isEmpty(String str) {
		return str==null||str.length()==0;
	}
	
	/**
	 * 返回字符序列是否为空 判断 cs==null||cs.length()==0
	 * @param cs CharSequence接口与子类对象
	 * @return true为空 ，false非空
	 */
	public static boolean isEmpty(CharSequence cs) {
		return cs==null||cs.length()==0;
	}
}
