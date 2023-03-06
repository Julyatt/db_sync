package com.digitalcitylab.dbsync.core;

import com.digitalcitylab.dbsync.constants.SqlOperateEnum;
import com.digitalcitylab.dbsync.domain.Field;
import com.digitalcitylab.dbsync.domain.SqlOperate;
import jdk.internal.org.objectweb.asm.commons.StaticInitMerger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
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
				writeToSqlScript("###########数据同步脚本########### \r\n");
			}
			sqlOperateEnum = sqlOperate.getOperateType();
			if (sqlOperateEnum.equals(sqlOperate.getOperateType()))
			{
				if (sqlOperate.getOperateType() == SqlOperateEnum.CREATE_TB)
				{
					writeToSqlScript("###########新建表########### \r\n");
				}else if (sqlOperate.getOperateType() == SqlOperateEnum.DROP_TB)
				{
					writeToSqlScript("###########删除表########### \r\n");
				} else if (sqlOperate.getOperateType() == SqlOperateEnum.ADD_FILED)
				{
					writeToSqlScript("###########增加字段########### \r\n");
				} else if (sqlOperate.getOperateType() == SqlOperateEnum.DROP_FIELD)
				{
					writeToSqlScript("###########删除字段########### \r\n");
				}
				else if (sqlOperate.getOperateType() == SqlOperateEnum.MODIFY_FIELD)
				{
					writeToSqlScript("###########修改字段########### \r\n");
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
