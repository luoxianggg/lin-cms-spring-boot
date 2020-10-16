-- ----------------------------
-- 药店信息表
-- ----------------------------
DROP TABLE IF EXISTS lin_file;
CREATE TABLE fun_medicinal_store
(
    fun_medi_store_id          int(10) unsigned NOT NULL AUTO_INCREMENT,
    store_name  varchar(500)     NOT NULL,
    tel        varchar(10)       DEFAULT '联系电话',
	adress      varchar(200)     '地址',    
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    created_by  int(10)          ,
	last_updated_by int(10),
    PRIMARY KEY (fun_medi_store_id),
    UNIQUE KEY str_name_u1 (store_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
  

-- ----------------------------
-- 药品信息表
-- ----------------------------
DROP TABLE IF EXISTS lin_file;
CREATE TABLE fun_medicinal
(
    fun_medi_id          int(10) unsigned NOT NULL AUTO_INCREMENT,
	medicinal_name  varchar(200) not null '药品名称',
	pinyinma         varchar(30) not null '药品拼音码',
	spec             varchar(30) not null  '药品规格',
	unit             varchar(30) not null  '药品单位',
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    created_by  int(10)          ,
	last_updated_by int(10),
    PRIMARY KEY (fun_medi_id),
    UNIQUE KEY  pin_yin_u1(pinyinma)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci; 
  -- ----------------------------
-- 药品入库信息表
-- ----------------------------
DROP TABLE IF EXISTS lin_file;
CREATE TABLE fun_medicinal_instock
(
    fun_medi_instock_id          int(10) unsigned NOT NULL AUTO_INCREMENT,
	fun_medi_id      int(10) not null '药品id',
	batch_id         varchar(200)  '药品批号',
	approve_batch_id varchar(200) '批准文号',
	factory          varchar(200)   '生产厂家',
	source_from      varchar(200)   '进货来源'
	amount           int(5)   '入库数量',
	price            decimal(18,2) '进货单价',
	in_stock_date    datetime(3) '入库时间',
	produce_date     datetime(3) '药品生产日期',
	invalid_dade     datetime(3) '药品过期日期',
	operater         int(10)      '入库员',
	description      varchar(1000) '备注',
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    created_by  int(10)          ,
	last_updated_by int(10),
    PRIMARY KEY (fun_medi_instock_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci; 

-- ----------------------------
-- 药品库存信息表
-- ----------------------------
DROP TABLE IF EXISTS lin_file;
CREATE TABLE fun_medicinal_stock
(
    fun_medi_stock_id          int(10) unsigned NOT NULL AUTO_INCREMENT,
	fun_medi_id      int(10) not null '药品id',
	batch_id         varchar(200)  '药品批号',
	approve_batch_id varchar(200) '批准文号',
	factory          varchar(200)   '生产厂家',
	stock_numbers           int(5)   '库存数量',
	price            decimal(18,2) '进货单价',
	produce_date     datetime(3) '药品生产日期',
	invalid_dade     datetime(3) '药品过期日期',
	description      varchar(1000) '备注',
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    created_by  int(10)          ,
	last_updated_by int(10),
    PRIMARY KEY (fun_medi_stock_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci; 
-- ----------------------------
-- 药品销售信息表
-- ----------------------------
DROP TABLE IF EXISTS lin_file;
CREATE TABLE fun_medicinal_sell
(
    fun_medi_sell_id          int(10) unsigned NOT NULL AUTO_INCREMENT,
	fun_medi_id      int(10) not null '药品id',
	fun_prescribe_id int(10)  '问诊单id',
	customer          varchar(200)   '购买方',
	sell_numbers           int(5)   '销售数量',
	sell_price       decimal(18,2) '销售单价',
	sell_dade     datetime(3) '药品销售日期',
	description      varchar(1000) '备注',
    create_time datetime(3)     DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    created_by  int(10)          ,
	last_updated_by int(10),
    PRIMARY KEY (fun_medi_sell_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci; 

-- ----------------------------
-- 问诊单信息表
-- ----------------------------
DROP TABLE IF EXISTS lin_file;
CREATE TABLE fun_prescribe
(
    fun_prescribe_id          int(10) unsigned NOT NULL AUTO_INCREMENT,
	customer          varchar(200)   '顾客',
	description      varchar(1000) '备注',
    create_time datetime(3)     DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    created_by  int(10)          ,
	last_updated_by int(10),
    PRIMARY KEY (fun_prescribe_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci; 