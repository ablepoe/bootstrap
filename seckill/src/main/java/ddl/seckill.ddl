-- ���ݿ⽨�����
-- �������ݿ�
CREATE DATABASE
IF NOT EXISTS seckillCus DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

-- ָ�����ݿ�
USE seckillCus;

-- �����(����ʱʹ��)
DROP TABLE
IF EXISTS seckill;

DROP TABLE
IF EXISTS seckillRecord;

-- ����
CREATE TABLE `seckill` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT '��ɱid',
	`name` VARCHAR (255) NOT NULL COMMENT '��ɱ��Ʒ��',
	`num` BIGINT (20) NOT NULL COMMENT '��ɱ��Ʒ����',
	`startTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '��ɱ��ʼʱ��',
	`endTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '��ɱ����ʱ��',
	`creatTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '���ݴ���ʱ��',
	PRIMARY KEY (id),
	KEY _indexId (id),
	KEY _indexName (NAME),
	KEY _indexStartTime (startTime)
) ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '��ɱ����';

-- �ͻ���ɱ��¼��
CREATE TABLE `seckillRecord` (
	`id` BIGINT (20) NOT NULL COMMENT '��ɱid',
	`user_phone` BIGINT (20) NOT NULL COMMENT '�û��ֻ�',
	`state` INT (10) DEFAULT 0 COMMENT '��ɱ״̬',
	`creatTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '���ݴ���ʱ��',
	PRIMARY KEY (id, user_phone),
	KEY _indexState (state)
) ENGINE = INNODB DEFAULT charset = utf8 COMMENT = '��ɱ��¼��'