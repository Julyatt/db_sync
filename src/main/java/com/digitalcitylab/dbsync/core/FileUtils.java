package com.digitalcitylab.dbsync.core;

import com.digitalcitylab.dbsync.constants.SqlOperateEnum;
import com.digitalcitylab.dbsync.domain.Field;
import com.digitalcitylab.dbsync.domain.SqlOperate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Description:
 * @Author: guosh
 * @Email: guosh@digitalcitylab.com.cn
 * @Date: 2023/3/4
 **/
public final class FileUtils {

	public static void generateScriptFile(List<SqlOperate> sqlOperateList) {
		SqlOperateEnum sqlOperateEnum = null;
		for (SqlOperate sqlOperate : sqlOperateList)
		{
			if (sqlOperateEnum == null)
			{
				writeToSqlScript(String.format("###########数据同步脚本,生成时间: %s########### \r\n",
						LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))));
			}
			sqlOperateEnum = sqlOperate.getOperateType();
			if (sqlOperateEnum.equals(sqlOperate.getOperateType()))
			{
				if (sqlOperate.getOperateType() == SqlOperateEnum.CREATE_TB)
				{
					writeToSqlScript( String.format("###########新建表%s########### \r\n", sqlOperate.getTableName()));
				}else if (sqlOperate.getOperateType() == SqlOperateEnum.DROP_TB)
				{
					writeToSqlScript(String.format("###########删除表%s########### \r\n", sqlOperate.getTableName()));
				} else if (sqlOperate.getOperateType() == SqlOperateEnum.ADD_FILED)
				{
					writeToSqlScript(String.format("###########%s表增加字段%s########### \r\n", sqlOperate.getTableName(), sqlOperate.getField().getFieldName()));
				} else if (sqlOperate.getOperateType() == SqlOperateEnum.DROP_FIELD)
				{
					writeToSqlScript(String.format("###########%s表删除字段%s########### \r\n", sqlOperate.getTableName(), sqlOperate.getField().getFieldName()));
				}
				else if (sqlOperate.getOperateType() == SqlOperateEnum.MODIFY_FIELD)
				{
					writeToSqlScript(String.format("###########%s表修改字段%s########### \r\n", sqlOperate.getTableName(), sqlOperate.getField().getFieldName()));
				} else if (sqlOperate.getOperateType() == SqlOperateEnum.ADD_INDEX)
				{
					writeToSqlScript("###########增加索引########### \r\n");
				}
				else if (sqlOperate.getOperateType() == SqlOperateEnum.DROP_INDEX)
				{
					writeToSqlScript("###########删除索引########### \r\n");
				} else if (sqlOperate.getOperateType() == SqlOperateEnum.MODIFY_INDEX)
				{
					writeToSqlScript("###########修改索引########### \r\n");
				}
			}

			String sqlScript = sqlOperate.generateSql();

			writeToSqlScript(sqlScript);

		}
	}

	public static void writeToSqlScript(String text) {
		if (text == null || text == "")
		{
			return;
		}
		FileWriter fw = null;
		BufferedWriter bufferedWriter = null;
		try
		{
			String filePath = "./dml" + ".sql";

			File file = new File(filePath);

			if (!file.exists())
			{
				file.createNewFile();

			}
			fw = new FileWriter(filePath, true);
			bufferedWriter = new BufferedWriter(fw);
			bufferedWriter.write(text);
			bufferedWriter.write("\r\n");

		} catch (IOException e)
		{
			throw new RuntimeException(e);
		} finally
		{
			if (bufferedWriter != null)
			{
				try
				{
					bufferedWriter.close();
				} catch (IOException e)
				{
					throw new RuntimeException(e);
				}
			}

			if (fw != null)
			{
				try
				{
					fw.close();
				} catch (IOException e)
				{
					throw new RuntimeException(e);
				}
			}

		}
	}

}
