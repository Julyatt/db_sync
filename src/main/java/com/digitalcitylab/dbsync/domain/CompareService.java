package com.digitalcitylab.dbsync.domain;

import com.digitalcitylab.dbsync.constants.SqlOperateEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: guosh
 * @Email: guosh@digitalcitylab.com.cn
 * @Date: 2023/3/4
 **/
public class CompareService {

	// 配置信息

	public static List<SqlOperate> compare(List<Table> sour, List<Table> tar) {

		List<SqlOperate> sqlOperateList = new ArrayList<>();

		List<String> sourTableNameList = sour.stream().map(t -> t.getTableName()).collect(Collectors.toList());
		List<String> tarTableNameList = tar.stream().map(t -> t.getTableName()).collect(Collectors.toList());

		Map<String, List<Table>> sourMap = sour.stream().collect(Collectors.groupingBy(Table::getTableName));
		Map<String, List<Table>> tarMap = tar.stream().collect(Collectors.groupingBy(Table::getTableName));

		// 源库中多的表，需要删除
		List<String> dropTb = sourTableNameList.stream().filter(t -> !tarTableNameList.contains(t))
				.collect(Collectors.toList());
		for (String s : dropTb)
		{
			SqlOperate sqlOperate = new SqlOperate();
			sqlOperate.setOperateType(SqlOperateEnum.DROP_TB);
			sqlOperate.setTableName(s);
			sqlOperateList.add(sqlOperate);
		}
		// 目标库中多的表，需要新增
		List<String> createTb = tarTableNameList.stream().filter(t -> !sourTableNameList.contains(t))
				.collect(Collectors.toList());
		for (String s : createTb)
		{
			SqlOperate sqlOperate = new SqlOperate();
			sqlOperate.setOperateType(SqlOperateEnum.CREATE_TB);
			sqlOperate.setTableName(s);
			sqlOperate.setCreateTbSql(tarMap.get(s).get(0).getCreateTbSql());
			sqlOperateList.add(sqlOperate);
		}



		for (Map.Entry<String, List<Table>> tarItem : tarMap.entrySet())
		{
			// 目标和源库中同时存在的表
			if (!createTb.contains(tarItem.getKey()))
			{
				sqlOperateList.addAll(compareTbDetail(sourMap.get(tarItem.getKey()).get(0),
						tarItem.getValue().get(0)));
			}
		}

		return sqlOperateList;

	}

	public static List<SqlOperate> compareTbDetail(Table sour, Table tar) {
		List<SqlOperate> sqlOperateList = new ArrayList<>();

		// 处理字段
		List<Field> sourFieldList = sour.getFieldList();
		List<Field> tarFieldList = tar.getFieldList();

		Map<String, List<Field>> sourFieldMap = sourFieldList.stream().collect(Collectors.groupingBy(Field::getFieldName));
		Map<String, List<Field>> tarFieldMap = tarFieldList.stream().collect(Collectors.groupingBy(Field::getFieldName));



		List<String> dropFieldList = new ArrayList<>();
		// 删除字段
		for (Map.Entry<String, List<Field>> fieldItem : tarFieldMap.entrySet())
		{
			if (!sourFieldMap.containsKey(fieldItem.getKey()))
			{
				SqlOperate sqlOperate = new SqlOperate();
				sqlOperate.setOperateType(SqlOperateEnum.DROP_FIELD);
				sqlOperate.setTableName(tar.getTableName());
				sqlOperate.setField(fieldItem.getValue().get(0));
				sqlOperateList.add(sqlOperate);

				dropFieldList.add(fieldItem.getValue().get(0).getFieldName());
			}
		}

		// 新增字段
		for (Map.Entry<String, List<Field>> fieldItem : sourFieldMap.entrySet())
		{
			if (!tarFieldMap.containsKey(fieldItem.getKey()))
			{
				SqlOperate sqlOperate = new SqlOperate();
				sqlOperate.setOperateType(SqlOperateEnum.ADD_FILED);
				sqlOperate.setTableName(tar.getTableName());
				sqlOperate.setField(fieldItem.getValue().get(0));
				sqlOperateList.add(sqlOperate);
			}
		}

		// todo 修改字段名的情况



		// 修改字段
		for (Map.Entry<String, List<Field>> fieldItem : tarFieldMap.entrySet())
		{
			if (!dropFieldList.contains(fieldItem.getKey()))
			{
				sqlOperateList.addAll(
						compareTbField(sourFieldMap.get(fieldItem.getKey()).get(0), fieldItem.getValue().get(0)));
			}
		}



		// 处理索引
		List<Index> sourIndexList = sour.getIndexList();
		List<Index> tarIndexList = tar.getIndexList();

		Map<String, List<Index>> sourIndexMap = sourIndexList.stream().collect(Collectors.groupingBy(Index::getKeyName));
		Map<String, List<Index>> tarIndexMap = tarIndexList.stream().collect(Collectors.groupingBy(Index::getKeyName));

		// 删除索引

		// 新增索引

		return sqlOperateList;
	}


	private static List<SqlOperate> compareTbField(Field sour, Field tar) {
		List<SqlOperate> sqlOperateList = new ArrayList<>();

		if (!sour.equals(tar))
		{
			SqlOperate sqlOperate = new SqlOperate();
			sqlOperate.setOperateType(SqlOperateEnum.MODIFY_FIELD);
			sqlOperate.setField(sour);
			sqlOperate.setTableName(sour.getTableName());
			sqlOperateList.add(sqlOperate);
		}
		return sqlOperateList;
	}

	private static List<SqlOperate> compareTbIndex(Index sour, Index tar) {
		List<SqlOperate> sqlOperateList = new ArrayList<>();

		return sqlOperateList;
	}




}
