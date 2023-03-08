package com.digitalcitylab.dbsync.domain;

import com.digitalcitylab.dbsync.constants.SqlOperateEnum;

import static com.digitalcitylab.dbsync.constants.SqlConst.*;

/**
 * @Description:
 * @Author: guosh
 * @Email: guosh@digitalcitylab.com.cn
 * @Date: 2023/3/4
 **/
public class SqlOperate {

	private String sqlScript;

	private SqlOperateEnum operateType;

	private String tableName;

	private Field field;

	private Index index;

	private String createTbSql;

	/**
	 * 生成sql脚本
	 * @return
	 */
	public String generateSql() {
		if (operateType == SqlOperateEnum.CREATE_TB)
		{
			// 创建表
			sqlScript = createTbSql;
		} else if (operateType == SqlOperateEnum.DROP_TB)
		{
			// 删除表
			sqlScript = String.format(DROP_TB_SQL, tableName);
		} else if (operateType == SqlOperateEnum.MODIFY_FIELD)
		{
			// 修改表字段
			sqlScript = String.format(MODIFY_COL_SQL, tableName, field.getFieldName(), field.getType());
		} else if (operateType == SqlOperateEnum.ADD_FILED)
		{
			// 新增表字段
			sqlScript = String.format(ADD_COL_SQL, tableName, field.getFieldName(), field.getType());
		} else if (operateType == SqlOperateEnum.DROP_FIELD)
		{
			// 删除表字段
			sqlScript = String.format(DROP_COL_SQL, tableName, field.getFieldName(), field.getType());
		}
		return sqlScript;
	}

	public String getSqlScript() {
		return sqlScript;
	}

	public SqlOperateEnum getOperateType() {
		return operateType;
	}

	public void setOperateType(SqlOperateEnum operateType) {
		this.operateType = operateType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Index getIndex() {
		return index;
	}

	public void setIndex(Index index) {
		this.index = index;
	}

	public String getCreateTbSql() {
		return createTbSql;
	}

	public void setCreateTbSql(String createTbSql) {
		this.createTbSql = createTbSql;
	}
}
