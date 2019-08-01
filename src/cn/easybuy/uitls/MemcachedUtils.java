package cn.easybuy.uitls;
/**
 * MemcachedUtil操作类
 * @author 青云 .ltd
 *
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

import cn.easybuy.entity.Product;

public class MemcachedUtils {
	private static  MemCachedClient client;
	static List<String> server = new ArrayList<String>();
	
	private static Logger logger=Logger.getLogger(MemcachedUtils.class.getName());
	
	static {//静态代码块 用于初始化Memcached客户端
		init();
		
		client=new MemCachedClient();
		
		//实例化连接池对象
		SockIOPool pool=SockIOPool.getInstance();
		
		String[]attr=server.toArray(new String[server.size()]);
		pool.setServers(attr);
		pool.setWeights(new Integer[] {3});//设置服务器优先级
		pool.setInitConn(10);//设置初始化连接数
		pool.setMinConn(10);//设置最小连接数
		pool.setMaxConn(100);//设置最大连接数
		pool.setMaxIdle(1000*30*30);//设置连接超时事件
		pool.setMaintSleep(30);//设置连接池维护线程休眠时间
		pool.setNagle(false);//是否启用Nagle算法
		pool.setSocketTO(30);
		
		//初始化连接池
		pool.initialize();
	}

	private static void init() {
		//创建Properties配置文件对象
		Properties prop=new Properties();
		
		String configFile="resources/memcached.properties";
		
		
		//将配置文件信息读取到输入流中
		InputStream is=MemcachedUtils.class.getClassLoader().getResourceAsStream(configFile);
	
		try {
			//将文件流中的配置文件信息加载到配置文件对象中去
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//根据键值获取指定的配置信息
		int quantity=Integer.valueOf(prop.getProperty("quantity"));
		
		for (int i = 0; i < quantity; i++) {//遍历服务器配置文件
			server.add(prop.getProperty("server"+i));
		}	
	}
	
	/**
	 * 向缓存中添加新的键值对 如果键已经存在 则之前的值将会被替换
	 * @param key 键
	 * @param value 值
	 * @return true 成功  false失败
	 */
	public static boolean set(String key,Object value) {
		System.out.println(value);
		return setExp(key,value,null);
	}
	
	/**
	 * 向缓存中添加新的键值对 如果键已经存在 则之前的值将会被替换
	 * @param key 键
	 * @param value 值
	 * @return true 成功  false失败
	 */
	public static boolean set(String key,Object value,Date expire) {
		return setExp(key,value,expire);
	}

	/**
	 * 向缓存中添加新的键值对 如果键已经存在 则之前的值将会被替换
	 * @param key 键
	 * @param value 值
	 * @return true 成功  false失败
	 */
	private static boolean setExp(String key, Object value,Date expire) {
		boolean flag=false;
		
		try {
			flag=client.set(key, value, 20);
			
		} catch (Exception e) {
			e.getStackTrace();
			//记录Memcached日志
			MecachedLog.writeLog("Memcached set方法报错："+key+"\r\n"+exceptionWrite(e));
		}
		
		return flag;
	}
	
	/**
	 * 仅当缓存中不存在键时，add命令才会向缓存中添加一个键值对
	 * @param key 键
	 * @param value 值 
	 * @param expire 过期时间
	 * @return 是否添加成功 true / false
	 */
	public static boolean add(String key,Object value) {
		return addExp(key,value,null);
	}
	
	/**
	 * 仅当缓存中不存在键时，add命令才会向缓存中添加一个键值对
	 * @param key 键
	 * @param value 值 
	 * @param expire 过期时间
	 * @return 是否添加成功 true / false
	 */
	public static boolean add(String key,Object value,Date expire) {
		return addExp(key,value,expire);
	}
	
	/**
	 * 仅当缓存中不存在键时，add命令才会向缓存中添加一个键值对
	 * @param key 键
	 * @param value 值 
	 * @param expire 过期时间
	 * @return 是否添加成功 true / false
	 */
	private static boolean addExp(String key, Object value, Date expire) {
		boolean flag=false;
		
		try {
			flag=client.add(key, value, expire);
		} catch (Exception e) {
			MecachedLog.writeLog("Memcached add方法报错，key值"+key+"\r\n"+exceptionWrite(e));
		}
		
		return flag;
	}
	
	/**
	  * 仅当键以存在是，replace命令才会替换缓存中的值
	 * @param key 键
	 * @param value 值
	 * @param expire 过期时间
	 * @return  是否替换成功
	 */
	public static boolean replace(String key,Object value) {
		return replaceExp(key,value,null);
	}
	
	/**
	  * 仅当键以存在是，replace命令才会替换缓存中的值
	 * @param key 键
	 * @param value 值
	 * @param expire 过期时间
	 * @return  是否替换成功
	 */
	public static boolean replace(String key,Object value,Date expire) {
		return replaceExp(key,value,expire);
	}

	/**
	  * 仅当键以存在是，replace命令才会替换缓存中的值
	 * @param key 键
	 * @param value 值
	 * @param expire 过期时间
	 * @return  是否替换成功
	 */
	private static boolean replaceExp(String key, Object value, Date expire) {
		
		return false;
	}
	
	/**
	 * get命令用于检索与之前添加的键值对相关的值
	 * @param key 键
	 * @return 获取的值
	 */
	public static Object get(String key) {
		Object obj=null;
		try {
			obj=client.get(key);
		} catch (Exception e) {
			//记录Memcached日志
			MecachedLog.writeLog("Memcached get方法报错，key值"+key+"\r\n"+exceptionWrite(e));
		}
		
		return obj;
	}
	
	/**
	 * 删除memcached中的任何现有值
	 * @param key 键
	 * @return 是否删除成功
	 */
	public static boolean delete(String key) {
		return deleteExp(key,null);
	}
	
	/**
	 * 删除memcached中的任何现有值
	 * @param key 键
	 * @return 是否删除成功
	 */
	public static boolean delete(String key,Date expire) {
		return deleteExp(key,expire);
	}

	/**
	 * 删除memcached中的任何现有值
	 * @param key 键
	 * @return 是否删除成功
	 */
	private static boolean deleteExp(String key, Date expire) {
		boolean flag=false;
		
		try {
			flag=client.delete(key, expire);
		} catch (Exception e) {
			//记录Memcached日志
			MecachedLog.writeLog("Memcached delete方法报错，key值"+key+"\r\n"+exceptionWrite(e));
		}
		
		return flag;
	}
	
	/**
	 * 清除缓存中的所有键值对
	 * @return 是否清除成功
	 */
	public static boolean flashAll() {
		boolean flag=false;
		
		try {
			client.flushAll();
		} catch (Exception e) {
			//记录Memcached日志
			MecachedLog.writeLog("Memcached flashAll方法报错\r\n"+exceptionWrite(e));
		}
		
		return flag;
	}

	/**
	 * 获取异常栈信息的方法 String类型
	 * @param e 异常对象
	 * @return 异常栈信息
	 */
	private static String exceptionWrite(Exception e) {
		StringWriter sw=new StringWriter();
		PrintWriter pw=new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.flush();
		
		return sw.toString();
	}
	
	/**
	 * Memcached日志记录类
	 * @author 青云 .ltd
	 *
	 */
	private static class MecachedLog{
		private final static String MEMCACHED_LOG="\\memcached.log";
		private final static String LINUX_MEMCACHED_LOG="/usr/local/logs/memcached.log";
		private static FileWriter fileWriter;
		private static BufferedWriter logWriter;
		//获取PID 用于找到对应的JVM进程
		private final static RuntimeMXBean RUNTIME_MX_BEAN=ManagementFactory.getRuntimeMXBean();
		private final static String PID=RUNTIME_MX_BEAN.getName();
		
		/**
		 * 初始化写入流
		 */
		static {
			//获取当前系统的名称
			String osName=System.getProperty("os.name");
			
			try {
				if(osName.indexOf("Windows")==-1) {
					fileWriter=new FileWriter(MEMCACHED_LOG,true);
				}else {
					fileWriter=new FileWriter(LINUX_MEMCACHED_LOG,true);
				}
				
				logWriter=new BufferedWriter(fileWriter);
			} catch (IOException e) {
				logger.error("memcached 日志初始化失败",e);
				closeLogStream();
			}
		}
		
		/**
		 * 写入日志信息的方法 
		 * @param content 日志内容
		 */
		public static void writeLog(String content) {
			try {
				logWriter.write("["+PID+"]"+"["+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"]\r\n"+content);
				logWriter.newLine();
				logWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		  * 关闭流的方法
		 */
		public static void closeLogStream() {
			try {
				fileWriter.close();
				logWriter.close();
			} catch (IOException e) {
				logger.error("memecached 日期对象关闭失败",e);
			}
		}
	}
	
	@Test
	public void run1() {
		List<Product> list=new ArrayList<Product>();
		System.out.println(MemcachedUtils.set("fListF599437F2F1B9CE2B63DCEB96795A0CD",list));
	}
}
