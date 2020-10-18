CREATE DATABASE lin_cms DEFAULT CHARSET utf8mb4;
USE lin_cms;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
select * from sys_roles;
-- ----------------------------
-- 药店信息表
-- ----------------------------
DROP TABLE IF EXISTS fun_medicinal_store;
CREATE TABLE fun_medicinal_store
(
    id          int unsigned NOT NULL AUTO_INCREMENT,
    store_name  varchar(500)     NOT NULL,
    tel        varchar(10)       COMMENT '联系电话',
	adress      varchar(200)   COMMENT  '地址',    
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    created_by  int          ,
	last_updated_by int,
    PRIMARY KEY (id),
    UNIQUE KEY str_name_u1 (store_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
  

-- ----------------------------
-- 药品信息表
-- ----------------------------
DROP TABLE IF EXISTS fun_medicinal;
CREATE TABLE fun_medicinal
(
    id          int unsigned NOT NULL AUTO_INCREMENT,
	medicinal_name  varchar(200) not null COMMENT '药品名称',
	pinyinma         varchar(30) not null COMMENT '药品拼音码',
	spec             varchar(30) not null COMMENT '药品规格',
	unit             varchar(30) not null COMMENT '药品单位',
    description     varchar(200) not null COMMENT '药品描述',
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    created_by  int          ,
	last_updated_by int,
    PRIMARY KEY (id),
    UNIQUE KEY  pin_yin_u1(pinyinma)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci; 
  -- ----------------------------
-- 药品入库信息表
-- ----------------------------
DROP TABLE IF EXISTS fun_medicinal_instock;
CREATE TABLE fun_medicinal_instock
(
    id          int unsigned NOT NULL AUTO_INCREMENT,
	fun_medi_id      int not null COMMENT '药品id',
	batch_id         varchar(200) COMMENT '药品批号',
	approve_batch_id varchar(200) COMMENT '批准文号',
	factory          varchar(200)  COMMENT '生产厂家',
	source_from      varchar(200)  COMMENT '进货来源',
	amount           int(5)  COMMENT '入库数量',
	price            decimal(18,2) COMMENT '进货单价',
	in_stock_date    datetime(3) COMMENT '入库时间',
	produce_date     datetime(3)  COMMENT '药品生产日期',
	invalid_dade     datetime(3) COMMENT '药品过期日期',
	operater         int    COMMENT  '入库员',
	description      varchar(1000) COMMENT '备注',
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    created_by  int          ,
	last_updated_by int,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci; 

-- ----------------------------
-- 药品库存信息表
-- ----------------------------
DROP TABLE IF EXISTS fun_medicinal_instock;
CREATE TABLE fun_medicinal_stock
(
    id          int unsigned NOT NULL AUTO_INCREMENT,
	fun_medi_id      int not null COMMENT '药品id',
	batch_id         varchar(200)  COMMENT '药品批号',
	approve_batch_id varchar(200)  COMMENT '批准文号',
	factory          varchar(200) COMMENT  '生产厂家',
	stock_numbers           int(5) COMMENT '库存数量',
	price            decimal(18,2)  COMMENT '进货单价',
	produce_date     datetime(3)  COMMENT '药品生产日期',
	invalid_dade     datetime(3)  COMMENT '药品过期日期',
	description      varchar(1000)  COMMENT '备注',
    create_time datetime(3)     DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    created_by  int          ,
	last_updated_by int,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci; 
-- ----------------------------
-- 药品销售信息表
-- ----------------------------
DROP TABLE IF EXISTS fun_medicinal_sell;
CREATE TABLE fun_medicinal_sell
(
    id          int unsigned NOT NULL AUTO_INCREMENT,
	fun_medi_id      int not null COMMENT  '药品id',
	fun_prescribe_id int COMMENT  '问诊单id',
	customer          varchar(200) COMMENT   '购买方',
	sell_numbers           int(5)  COMMENT '销售数量',
	sell_price       decimal(18,2)  COMMENT '销售单价',
	sell_dade     datetime(3)  COMMENT '药品销售日期',
	description      varchar(1000) COMMENT  '备注',
    create_time datetime(3)     DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    created_by  int          ,
	last_updated_by int,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci; 

-- ----------------------------
-- 问诊单信息表
-- ----------------------------
DROP TABLE IF EXISTS fun_prescribe;
CREATE TABLE fun_prescribe
(
    id          int unsigned NOT NULL AUTO_INCREMENT,
	customer          varchar(200)  COMMENT '顾客',
	description      varchar(1000)  COMMENT '备注',
    create_time datetime(3)     DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    created_by  int          ,
	last_updated_by int,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci; 

COMMIT;
