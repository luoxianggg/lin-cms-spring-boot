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
    tel          varchar(30)       COMMENT '联系电话',
	address      varchar(200)   COMMENT  '地址',
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    created_by  int          ,
	last_updated_by int,
    PRIMARY KEY (id),
    UNIQUE KEY str_name_u1 (store_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

insert into fun_medicinal_store(store_name,tel,address) values('杭州市恩培诊所','13681940247','杭州市下城区朝晖街道');
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
    instock_number   varchar(30)  comment '入库单号',
	fun_medi_id      int not null COMMENT '药品id',
	batch_id         varchar(200) COMMENT '药品批号',
	approve_batch_id varchar(200) COMMENT '批准文号',
	factory          varchar(200)  COMMENT '生产厂家',
	source_from      varchar(200)  COMMENT '进货来源',
	amount           int  COMMENT '入库数量',
	price            decimal(18,2) COMMENT '进货单价',
	in_stock_date    date  COMMENT '入库时间',
	produce_date     date  COMMENT '药品生产日期',
	invalid_dade     date COMMENT '药品过期日期',
  medicinal_store_id         int    COMMENT  '药店id',
	description      varchar(1000) COMMENT '备注',
  flow_number     LONG COMMENT '药品编号流水号',
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
DROP TABLE IF EXISTS fun_medicinal_stock;
CREATE TABLE fun_medicinal_stock
(
  id          int unsigned NOT NULL AUTO_INCREMENT,
  fun_medi_id      int not null COMMENT '药品id',
  medicinal_store_id         int    COMMENT  '药店id',
  stock_num           int(5) COMMENT '库存数量',
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
	customer_id          varchar(200) COMMENT   '购买方',
    medicinal_store_id  int comment '药店id',
	sell_num           int  COMMENT '销售数量',
	sell_price       decimal(18,2)  COMMENT '销售单价',
	sell_dade     date  COMMENT '药品销售日期',
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
    prescribe_number varchar(30) not null comment '问诊单号',
	customer_id          varchar(30)  COMMENT '顾客',
	description      varchar(1000)  COMMENT '备注',
    medicinal_store_id         int    COMMENT  '药店id',
    amount decimal(18,2) comment '金额',
    create_time datetime(3)     DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    created_by  int          ,
	last_updated_by int,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- 顾客信息表
-- ----------------------------
DROP TABLE IF EXISTS fnd_customer;
CREATE TABLE fnd_customer
(
    id       int unsigned NOT NULL AUTO_INCREMENT,
	name     varchar(200)  COMMENT '姓名',
	sex      varchar(5)  COMMENT '性别',
    age      int comment '年龄',
    address  varchar(200) comment '地址',
    create_time datetime(3)     DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    created_by  int          ,
	last_updated_by int,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
  DROP TABLE IF EXISTS fnd_doc_numbers;
CREATE TABLE fnd_doc_numbers
(
    id       int unsigned NOT NULL AUTO_INCREMENT,
	pre     varchar(200)  COMMENT '单据前缀',
	dates      varchar(6)  COMMENT '日期',
    flow_number      int comment '单据流水号',
    functions     varchar(30) comment'功能代码',
    create_time datetime(3)     DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)     DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    created_by  int          ,
	last_updated_by int,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
COMMIT;
