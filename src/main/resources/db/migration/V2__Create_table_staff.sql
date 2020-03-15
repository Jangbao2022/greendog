CREATE TABLE `staff` -- 工作人员--
(
  id           BIGINT(10) PRIMARY KEY auto_increment,
  customer_id  BIGINT(10) COMMENT '预约人id',
  `name`       VARCHAR(30) NOT NULL COMMENT '名字',
  sex          INT(1) COMMENT '性别',
  age          INT(3) COMMENT '年龄',
  pic          varchar(100) COMMENT '照片',
  address      VARCHAR(100) COMMENT '地址',
  contact      VARCHAR(100) COMMENT '联系方式',

  gmt_created  datetime COMMENT '创建时间',
  gmt_modified datetime COMMENT '修改时间'
);
