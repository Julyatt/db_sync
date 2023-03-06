###########数据同步脚本########### 

###########删除表########### 

DROP TABLE datax_test3
###########新建表########### 

CREATE TABLE `datax_test2` (
  `id` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
###########修改字段########### 

ALTER TABLE hn_people MODIFY deleted_flag varchar(32)
###########修改字段########### 

ALTER TABLE hn_people MODIFY phone varchar(64)
###########修改字段########### 

ALTER TABLE datax_test MODIFY name varchar(11)
###########修改字段########### 

ALTER TABLE hn_people_migration MODIFY migration_type varchar(50)
###########修改字段########### 

ALTER TABLE tbl_china_divisions MODIFY code varchar(200)
###########修改字段########### 

ALTER TABLE tbl_china_divisions MODIFY p_code varchar(200)
###########修改字段########### 

ALTER TABLE tbl_china_divisions MODIFY name varchar(500)
###########修改字段########### 

ALTER TABLE tbl_china_divisions MODIFY is_leaf tinyint(1)
