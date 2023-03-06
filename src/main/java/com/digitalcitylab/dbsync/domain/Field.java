package com.digitalcitylab.dbsync.domain;

import java.util.Objects;

/**
 * @Description: 字段
 * @Author: guosh
 * @Email: guosh@digitalcitylab.com.cn
 * @Date: 2023/3/3
 **/
public class Field {

	private boolean matchFlag;

	private String tableName;

	/**
	 * 字段名
	 */
	private String fieldName;

	/**
	 * 字段类型
	 */
	private String type;

	/**
	 * 是否可为空
	 */
	private String nullFlag;

	/**
	 * 是否是主键
	 */
	private String keyFlag;

	/**
	 * 默认值
	 */
	private String defaultVal;

	/**
	 * 注解
	 */
	private String comment;

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

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNullFlag() {
		return nullFlag;
	}

	public void setNullFlag(String nullFlag) {
		this.nullFlag = nullFlag;
	}

	public String getKeyFlag() {
		return keyFlag;
	}

	public void setKeyFlag(String keyFlag) {
		this.keyFlag = keyFlag;
	}

	public String getDefaultVal() {
		return defaultVal;
	}

	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
		Field obj1 = (Field) obj;

		return Objects.equals(tableName, obj1.getTableName())
				&& Objects.equals(fieldName, obj1.getFieldName())
				&& Objects.equals(nullFlag, obj1.getNullFlag())
				&& Objects.equals(type, obj1.getType())
				&& Objects.equals(keyFlag, obj1.getKeyFlag())
				&& Objects.equals(defaultVal, obj1.getDefaultVal())
				&& Objects.equals(comment, obj1.getComment());
	}

}
