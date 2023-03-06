package com.digitalcitylab.dbsync.domain;

import com.digitalcitylab.dbsync.core.DbConnection;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description:
 * @Author: guosh
 * @Email: guosh@digitalcitylab.com.cn
 * @Date: 2023/3/3
 **/
public class Config {

	/**
	 * 源数据配置
	 */
	public static final String SOUR_URL_KEY = "source.url";

	public static final String SOUR_USER_NAME_KEY = "source.username";

	public static final String SOUR_PWD_KEY = "source.password";

	public static final String SOUR_DIRVER_CLASS_NAME_KEY = "source.driver-class-name";

	public static final String SOUR_TABLE_NAME_KEY = "source.table.name";

	/**
	 * 目标数据配置
	 */
	public static final String TAR_URL_KEY = "target.url";

	public static final String TAR_USER_NAME_KEY = "target.username";

	public static final String TAR_PWD_KEY = "target.password";

	public static final String TAR_DIRVER_CLASS_NAME_KEY = "target.driver-class-name";

	public static final String TAR_TABLE_NAME_KEY = "target.table.name";

	private static final String[] KEYS = {
			SOUR_URL_KEY,
			SOUR_USER_NAME_KEY,
			SOUR_PWD_KEY,
			SOUR_DIRVER_CLASS_NAME_KEY,
			SOUR_TABLE_NAME_KEY,

			TAR_URL_KEY,
			TAR_USER_NAME_KEY,
			TAR_PWD_KEY,
			TAR_DIRVER_CLASS_NAME_KEY,
			TAR_TABLE_NAME_KEY };

	private static final String[] sourKey = {
			SOUR_URL_KEY,
			SOUR_USER_NAME_KEY,
			SOUR_PWD_KEY,
			SOUR_DIRVER_CLASS_NAME_KEY
	};

	private static final String[] tarKey = {
			TAR_URL_KEY,
			TAR_USER_NAME_KEY,
			TAR_PWD_KEY,
			TAR_DIRVER_CLASS_NAME_KEY
	};


	private static Map<String, String> configMap = new HashMap<String, String>();

	private static Map<String, String> sourConfigMap = new HashMap<String, String>();

	private static Map<String, String> tarConfigMap = new HashMap<String, String>();

	/**
	 * 源数据配置值
	 */
	public static String SOUR_URL_VALUE ;

	public static String SOUR_USER_NAME_VALUE ;

	public static String SOUR_PWD_VALUE ;

	public static String SOUR_DIRVER_CLASS_NAME_VALUE ;

	public static  String SOUR_TABLE_NAME_VALUE ;

	/**
	 * 目标数据配置值
	 */
	public static String TAR_URL_VALUE ;

	public static  String TAR_USER_NAME_VALUE;

	public static  String TAR_PWD_VALUE ;

	public static  String TAR_DIRVER_CLASS_NAME_VALUE ;

	public static  String TAR_TABLE_NAME_VALUE ;

	public static void loadConfig() throws IOException {
		Properties properties = new Properties();

		ClassLoader classLoader = DbConnection.class.getClassLoader();
		InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc.properties");
		properties.load(resourceAsStream);
		for (String key : KEYS)
		{
			if (SOUR_TABLE_NAME_KEY.equals(key))
			{
				configMap.put(key, properties.getProperty(key, "ALL"));
			} else
			{
				configMap.put(key, properties.getProperty(key));
			}

		}

		for (String key : sourKey)
		{
			sourConfigMap.put(key, properties.getProperty(key));

		}
		for (String key : tarKey)
		{
			tarConfigMap.put(key, properties.getProperty(key));
		}
	}

	public static Map<String, String> getAllConfig() {
		return configMap;
	}

	public static String getConfig(String key) {
		return configMap.get(key);
	}

	public static Map<String, String> getSourConfig() {
		return sourConfigMap;
	}

	public static Map<String, String> getTarConfigMap() {
		return tarConfigMap;
	}

}
