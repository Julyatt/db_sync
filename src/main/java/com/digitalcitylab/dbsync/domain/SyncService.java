package com.digitalcitylab.dbsync.domain;

import com.digitalcitylab.dbsync.constants.SqlConst;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: guosh
 * @Email: guosh@digitalcitylab.com.cn
 * @Date: 2023/3/3
 **/
public class SyncService {

	public static List<Table> getTables(Connection connection) {
		List<Table> sourTableList = new ArrayList<Table>();
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(SqlConst.GET_ALL_TABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				Table table = new Table();
				String tbName = resultSet.getString(1);
				table.setTableName(tbName);

				String createTbSql = getCreateTbSql(connection, tbName);
				table.setCreateTbSql(createTbSql);

				List<Field> fieldFromTb = getFieldFromTb(connection, tbName);
				table.setFieldList(fieldFromTb);

				List<Index> indexFromTb = getIndexFromTb(connection, tbName);
				table.setIndexList(indexFromTb);

				sourTableList.add(table);
			}
		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		} finally
		{
			try
			{
				connection.close();
			} catch (SQLException e)
			{
				throw new RuntimeException(e);
			}
			return sourTableList;
		}

	}

	public static String getCreateTbSql(Connection connection, String tableName) {
		PreparedStatement preparedStatement = null;
		String createTbSql = null;
		try
		{
			preparedStatement = connection.prepareStatement(String.format(SqlConst.GET_CREATE_TB_SQL, tableName));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				createTbSql = resultSet.getString("Create Table") + ";";
			}
		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		return createTbSql;



	}

	public static List<Field> getFieldFromTb(Connection connection, String tableName) {
		List<Field> sourFieldList = new ArrayList<Field>();
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(String.format(SqlConst.GET_COLUMN_FROM_TB, tableName) );
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				Field field = new Field();
				field.setTableName(tableName);
				field.setFieldName(resultSet.getString("Field"));
				field.setType(resultSet.getString("Type"));
				field.setNullFlag(resultSet.getString("Null"));
				field.setKeyFlag(resultSet.getString("Key"));
				field.setDefaultVal(resultSet.getString("Default"));
				field.setComment("");
				sourFieldList.add(field);
			}
		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		} finally
		{
			return sourFieldList;
		}
	}

	public static List<Index> getIndexFromTb(Connection connection, String tableName) {
		List<Index> sourIndexList = new ArrayList<Index>();
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(String.format(SqlConst.GET_INDEX_FROM_TB, tableName) );
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				Index index = new Index();
				index.setTableName(tableName);
				index.setUniqueFlag(resultSet.getString("Non_unique"));
				index.setKeyName(resultSet.getString("Key_name"));
				index.setColumnName(resultSet.getString("Column_name"));
				index.setComment(resultSet.getString("Comment"));
				index.setIndexComment(resultSet.getString("Index_comment"));

				sourIndexList.add(index);
			}
		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		} finally
		{
			return sourIndexList;
		}
	}



}
