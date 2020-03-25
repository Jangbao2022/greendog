-- 修改申请只对宠物
alter table apply change receiver_id pet_id bigint(10) null comment '宠物id';

-- 修改工作人员 为 医生
rename
table
staff
to
doctor;

-- 删去医生中的 用户id 由病例表来处理
alter table doctor drop column customer_id;

-- 医生加上价格
alter table doctor
  add price float null comment '价格';

-- 公告栏命名错误
alter table bulletin change contant content text not null comment '内容';


CREATE TABLE `medicine` -- 药品--
(
  id           BIGINT(10) PRIMARY KEY auto_increment,
  `name`       VARCHAR(30) NOT NULL COMMENT '名字',
  description  text COMMENT '描述',
  pic          varchar(100) COMMENT '照片',
  price        float COMMENT '价格',

  gmt_created  datetime COMMENT '创建时间',
  gmt_modified datetime COMMENT '修改时间'
);


CREATE TABLE `disease` -- 疾病--
(
  id           BIGINT(10) PRIMARY KEY auto_increment,
  `name`       VARCHAR(30) NOT NULL COMMENT '名字',
  description  text COMMENT '描述',
  price        float COMMENT '价格',

  gmt_created  datetime COMMENT '创建时间',
  gmt_modified datetime COMMENT '修改时间'
);


CREATE TABLE `appointment` -- 预约--
(
  id           BIGINT(10) PRIMARY KEY auto_increment,
  pet_id       BIGINT(10) NOT NULL COMMENT '宠物id',
  doctor_id    BIGINT(10) NOT NULL COMMENT '医生id',
  disease_id   BIGINT(10) NOT NULL COMMENT '疾病id',
  `handle`     INT(1) COMMENT '处理状态',
  reply        INT(1) COMMENT '回复',

  gmt_created  datetime COMMENT '创建时间',
  gmt_modified datetime COMMENT '修改时间'
);


CREATE TABLE `case` -- 病例--
(

  id           BIGINT(10) PRIMARY KEY auto_increment,
  pet_id       BIGINT(10) NOT NULL COMMENT '宠物id',
  doctor_id    BIGINT(10) NOT NULL COMMENT '医生id',
  disease_id   BIGINT(10) NOT NULL COMMENT '疾病id',

  gmt_created  datetime COMMENT '创建时间',
  gmt_modified datetime COMMENT '修改时间'
);


CREATE TABLE `bill` -- 账单--
(

  id           BIGINT(10) PRIMARY KEY auto_increment,
  customer_id  BIGINT(10) NOT NULL COMMENT '客户id',
  goods_id     BIGINT(10) NOT NULL COMMENT '商品id',
  type         int(1) NOT NULL COMMENT '商品类型(药品 或 疾病+医生)',

  gmt_created  datetime COMMENT '创建时间',
  gmt_modified datetime COMMENT '修改时间'
);

CREATE TABLE `cart` -- 购物车--
(

  id           BIGINT(10) PRIMARY KEY auto_increment,
  customer_id  BIGINT(10) NOT NULL COMMENT '客户id',
  medicine_id  BIGINT(10) NOT NULL COMMENT '药品id',

  gmt_created  datetime COMMENT '创建时间',
  gmt_modified datetime COMMENT '修改时间'
);

CREATE TABLE `packet` -- 钱包--
(

  id            BIGINT(10) PRIMARY KEY auto_increment,
  customer_id   BIGINT(10) NOT NULL COMMENT '客户id',
  total_money   float NOT NULL default 0 COMMENT '总金额',
  shop_money    float NOT NULL default 0 COMMENT '消费金额',
  surplus_money float NOT NULL default 0 COMMENT '剩余金额',

  gmt_created   datetime COMMENT '创建时间',
  gmt_modified  datetime COMMENT '修改时间'
);



