CREATE TABLE customer -- 用户表--
(
  id           BIGINT(10) PRIMARY KEY auto_increment,
  `name`       VARCHAR(30) NOT NULL COMMENT '名字',
  `nickname`   VARCHAR(30) NOT NULL COMMENT '昵称',
  sex          INT(1) COMMENT '性别',
  age          INT(3) COMMENT '年龄',
  address      VARCHAR(100) COMMENT '地址',
  contact      VARCHAR(100) COMMENT '联系方式',
  `account`    VARCHAR(20) NOT NULL COMMENT '账号',
  `password`   VARCHAR(20) NOT NULL COMMENT '密码',

  gmt_created  datetime COMMENT '创建时间',
  gmt_modified datetime COMMENT '修改时间'
);


CREATE TABLE pet -- 宠物表--
(
  id              BIGINT(10) PRIMARY KEY auto_increment,
  `name`          VARCHAR(30) NOT NULL COMMENT '名字',
  sex             INT(1) COMMENT '性别',
  age             INT(3) COMMENT '年龄',
  breed           VARCHAR(100) COMMENT '品种',
  master_id       BIGINT(10) COMMENT '主人id',
  `status`        INT(1) COMMENT '状态',
  picture         VARCHAR(100) COMMENT '照片',
  master_nickname VARCHAR(30) COMMENT '主人昵称',

  gmt_created     datetime COMMENT '创建时间',
  gmt_modified    datetime COMMENT '修改时间',

  foreign key (master_id) references customer (id)
);

CREATE TABLE apply -- 申请信息--
(
  id            BIGINT(10) PRIMARY KEY auto_increment,
  `customer_id` BIGINT(10) NOT NULL COMMENT '申请人id',
  `receiver_id` BIGINT(10) COMMENT '被申请者id',
  `type`        INT(1) COMMENT '申请类别',
  `handle`      INT(1) COMMENT '处理状态',
  reply         INT(1) COMMENT '回复',

  gmt_created   datetime COMMENT '创建时间',
  gmt_modified  datetime COMMENT '修改时间',

  foreign key (customer_id) references customer (id)
);


CREATE TABLE administrator -- 管理员表--
(
  id           BIGINT(10) PRIMARY KEY auto_increment,
  `name`       VARCHAR(30) NOT NULL COMMENT '名字',
  `nickname`   VARCHAR(30) NOT NULL COMMENT '昵称',
  `account`    VARCHAR(20) NOT NULL COMMENT '账号',
  `password`   VARCHAR(20) NOT NULL COMMENT '密码',

  gmt_created  datetime COMMENT '创建时间',
  gmt_modified datetime COMMENT '修改时间'
);


CREATE TABLE bulletin -- 公告表--
(
  id             BIGINT(10) PRIMARY KEY auto_increment,
  `publisher_id` BIGINT(10) NOT NULL COMMENT '名字',
  `title`        VARCHAR(30) NOT NULL COMMENT '标题',
  `contant`      text        NOT NULL COMMENT '内容',

  gmt_created    datetime COMMENT '创建时间',
  gmt_modified   datetime COMMENT '修改时间',

  foreign key (publisher_id) references administrator (id)
);



