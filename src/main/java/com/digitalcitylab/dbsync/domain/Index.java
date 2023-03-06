package com.digitalcitylab.dbsync.domain;

import java.util.Objects;

/**
 * @Description: 索引
 * @Author: guosh
 * @Email: guosh@digitalcitylab.com.cn
 * @Date: 2023/3/3
 **/
public class Index {

	private boolean matchFlag;

	private String tableName;

	/**
	 * 唯一标记
	 */
	private String uniqueFlag;

	/**
	 * 索引名
	 */
	private String keyName;

	/**
	 * 所属字段
	 */
	private String columnName;

	/**
	 * 注解
	 */
	private String comment;

	private String indexComment;

	public boolean isMatchFlag() {
		return matchFlag;
	}

	public void setMatchFlag(boolean matchFlag) {
		this.matchFlag = matchFlag;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getUniqueFlag() {
		return uniqueFlag;
	}

	public void setUniqueFlag(String uniqueFlag) {
		this.uniqueFlag = uniqueFlag;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getIndexComment() {
		return indexComment;
	}

	public void setIndexComment(String indexComment) {
		this.indexComment = indexComment;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
		{
			return true;
		}
		if (obj == null || getClass() != obj.getClass())
		{
			return false;
		}
		Index obj1 = (Index) obj;
		return Objects.equals(tableName, obj1.getTableName())
				&& Objects.equals(columnName, obj1.getColumnName())
				&& Objects.equals(keyName, obj1.getKeyName())
				&& Objects.equals(indexComment, obj1.getIndexComment())
				&& Objects.equals(comment, obj1.getComment());
	}
}
