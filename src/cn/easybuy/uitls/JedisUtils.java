package cn.easybuy.uitls;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis（NoSQL）访问工具类
 * @author 青云 .ltd
 *
 */
public class JedisUtils {
	private static String host;//redis服务器端ip地址
	private static int port;//redis端口号
	private static String auth;//连接密匙
	private static final JedisPool POOL;//Jedis连接池对象
	
	private static Logger logger=Logger.getLogger(JedisUtils.class.getName());
	
	static {
		init();
		//JedisPoolConfig连接池配置对象
		JedisPoolConfig config=new JedisPoolConfig();
		config.setMaxActive(50);
		config.setMaxIdle(10);
		
		//实例化JedisPool对象
		POOL=new JedisPool(config, host, port);
	}

	/**
	 * 初始化Jedis配置信息的方法
	 */
	private static void init() {
		String configFile="resources/jedis.properties";
		
		Properties properties=new Properties();//配置信息对象
		//将配置文件以流的信息读取到输入流中
		InputStream is=DataBaseUtil.class.getClassLoader().getResourceAsStream(configFile);
		
		try {
			//将文件流加载到配置对象中
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取Jedis连接的方法
	 * @return
	 */
	public static Jedis getJedis() {
		Jedis jedis=null;//Jedis连接对象
		
		if(jedis==null) {
			jedis=POOL.getResource();//获取Jedis连接对象
		}
		
		return jedis;
	}
}
