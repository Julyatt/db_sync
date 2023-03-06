package com.digitalcitylab.dbsync.domain;

import java.util.List;

/**
 * @Description: 表
 * @Author: guosh
 * @Email: guosh@digitalcitylab.com.cn
 * @Date: 2023/3/3
 **/
public class Table {

	private boolean matchFlag;

	private String tableName;

	/**
	 * 建表语句
	 */
	private String createTbSql;

	private List<Field> fieldList;

	private List<Index> indexList;

	public String getTableName() {
		return tableName;
	}

	public boolean isMatchFlag() {
		return matchFlag;
	}

	public void setMatchFlag(boolean matchFlag) {
		this.matchFlag = matchFlag;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Index> getIndexList() {
		return indexList;
	}

	public void setIndexList(List<Index> indexList) {
		this.indexList = indexList;
	}

	public String getCreateTbSql() {
		return createTbSql;
	}

	public void setCreateTbSql(String createTbSql) {
		this.createTbSql = createTbSql;
	}

	public List<Field> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}
}
