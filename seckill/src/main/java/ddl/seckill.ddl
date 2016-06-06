-- 数据库建表相关
-- 建立数据库
CREATE DATABASE
IF NOT EXISTS seckillCus DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

-- 指定数据库
USE seckillCus;

-- 清除表(测试时使用)
DROP TABLE
IF EXISTS seckill;

DROP TABLE
IF EXISTS seckillRecord;

-- 建表
CREATE TABLE `seckill` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT '秒杀id',
	`name` VARCHAR (255) NOT NULL COMMENT '秒杀商品名',
	`num` BIGINT (20) NOT NULL COMMENT '秒杀商品数量',
	`startTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀开始时间',
	`endTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀结束时间',
	`creatTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
	PRIMARY KEY (id),
	KEY _indexId (id),
	KEY _indexName (NAME),
	KEY _indexStartTime (startTime)
) ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '秒杀库存表';

-- 客户秒杀记录表
CREATE TABLE `seckillRecord` (
	`id` BIGINT (20) NOT NULL COMMENT '秒杀id',
	`user_phone` BIGINT (20) NOT NULL COMMENT '用户手机',
	`state` INT (10) DEFAULT 0 COMMENT '秒杀状态',
	`creatTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
	PRIMARY KEY (id, user_phone),
	KEY _indexState (state)
) ENGINE = INNODB DEFAULT charset = utf8 COMMENT = '秒杀记录表'