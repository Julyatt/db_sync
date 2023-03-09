package com.digitalcitylab.dbsync;

import com.digitalcitylab.dbsync.core.DbConnection;
import com.digitalcitylab.dbsync.core.FileUtils;
import com.digitalcitylab.dbsync.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * @Description:
 * @Author: guosh
 * @Email: guosh@digitalcitylab.com.cn
 * @Date: 2023/3/3
 **/
public class DbSyncApplication {

	private static final Logger LOG = LoggerFactory.getLogger(DbSyncApplication.class);

	public static void main(String[] args) {
		// 初始化读取配置

		try
		{
			Config.loadConfig();
			LOG.info(Config.getAllConfig().toString());
		} catch (IOException e)
		{
			LOG.error(e.getMessage());
			throw new RuntimeException(e);
		}

		// 校验配置

		// 创建数据库连接
		Connection tar = DbConnection.buildConnect("tar");
		
		Connection sour = DbConnection.buildConnect("sour");

		List<Table> sourTable = SyncService.getTables(sour);

		List<Table> tarTable = SyncService.getTables(tar);

		try
		{
			// 比较表,比较字段,比较索引
			List<SqlOperate> compare = CompareService.compare(sourTable, tarTable);

			// 导出脚本文件
			FileUtils.generateScriptFile(compare);
		} catch (Exception exception)
		{
			LOG.error(exception.getMessage());
			throw exception;
		}

	}


}
