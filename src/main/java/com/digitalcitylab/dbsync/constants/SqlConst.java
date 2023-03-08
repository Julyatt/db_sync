package com.digitalcitylab.dbsync.constants;

/**
 * @Description:
 * @Author: guosh
 * @Email: guosh@digitalcitylab.com.cn
 * @Date: 2023/3/3
 **/
public class SqlConst {

	/**
	 * 查询库中的表
	 */
	public static final String GET_ALL_TABLE = "SHOW TABLES;";

	/**
	 * 获取表的建表语句
	 */
	public static final String GET_CREATE_TB_SQL = "SHOW CREATE TABLE %s;";

	/**
	 * 获取表的字段
	 */
	public static final String GET_COLUMN_FROM_TB = "SHOW COLUMNS FROM %s;";

	/**
	 * 获取表的索引
	 */
	public static final String GET_INDEX_FROM_TB = "SHOW INDEX FROM %s;";

	/**
	 * 删除表
	 */
	public static final String DROP_TB_SQL = "DROP TABLE %s;";

	/**
	 * 增加字段
	 */
	public static final String ADD_COL_SQL = "ALTER TABLE %s ADD COLUMN %s %s;";

	/**
	 * 删除字段
	 */
	public static final String DROP_COL_SQL = "ALTER TABLE %s DROP COLUMN %s;";

	/**
	 * 修改字段
	 */
	public static final String MODIFY_COL_SQL = "ALTER TABLE %s MODIFY %s %s;";

	/**
	 * 增加索引
	 */
	public static final String ADD_INDEX_SQL = "";

	/**
	 * 删除索引
	 */
	public static final String DROP_INDEX_SQL = "";

	/**
	 * 修改索引
	 */
	public static final String MODIFY_INDEX_SQL = "";
}
