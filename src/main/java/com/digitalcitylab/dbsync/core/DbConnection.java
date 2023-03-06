package com.digitalcitylab.dbsync.core;

import com.digitalcitylab.dbsync.domain.Config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * @Description:
 * @Author: guosh
 * @Email: guosh@digitalcitylab.com.cn
 * @Date: 2023/3/3
 **/
public class DbConnection {

	public static Connection buildConnect(String type) {
		Connection connection = null;
		if ("tar".equals(type))
		{
			Map<String, String> tarConfigMap = Config.getTarConfigMap();

			try
			{
				Class.forName(tarConfigMap.get(Config.TAR_DIRVER_CLASS_NAME_KEY));
				connection = DriverManager.getConnection(tarConfigMap.get(Config.TAR_URL_KEY),
						tarConfigMap.get(Config.TAR_USER_NAME_KEY),
						tarConfigMap.get(Config.TAR_PWD_KEY));
			} catch (ClassNotFoundException e)
			{
				throw new RuntimeException(e);
			} catch (SQLException e)
			{
				throw new RuntimeException(e);
			} finally
			{
				return connection;
			}
		} else if ("sour".equals(type))
		{
			Map<String, String> sourConfigMap = Config.getSourConfig();

			try
			{
				Class.forName(sourConfigMap.get(Config.SOUR_DIRVER_CLASS_NAME_KEY));
				connection = DriverManager.getConnection(sourConfigMap.get(Config.SOUR_URL_KEY),
						sourConfigMap.get(Config.SOUR_USER_NAME_KEY),
						sourConfigMap.get(Config.SOUR_PWD_KEY));
			} catch (ClassNotFoundException e)
			{
				throw new RuntimeException(e);
			} catch (SQLException e)
			{
				throw new RuntimeException(e);
			} finally
			{
				return connection;
			}
		}
		return connection;
	}

}
