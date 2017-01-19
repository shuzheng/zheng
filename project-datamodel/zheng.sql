/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/1/19 21:54:06                           */
/*==============================================================*/


drop table if exists cms_article;

drop table if exists cms_article_category;

drop table if exists cms_article_tag;

drop table if exists cms_book;

drop table if exists cms_category;

drop table if exists cms_category_tag;

drop table if exists cms_comment;

drop table if exists cms_page;

drop table if exists cms_setting;

drop table if exists cms_tag;

drop table if exists cms_user;

drop table if exists pay_in_order;

drop table if exists pay_in_order_detail;

drop table if exists pay_mch;

drop table if exists pay_out_order;

drop table if exists pay_out_order_detail;

drop table if exists pay_pay;

drop table if exists pay_type;

drop table if exists pay_vendor;

drop table if exists pay_vest;

drop table if exists upms_organization;

drop table if exists upms_permission;

drop table if exists upms_role;

drop table if exists upms_role_permission;

drop table if exists upms_system;

drop table if exists upms_user;

drop table if exists upms_user_organization;

drop table if exists upms_user_permission;

drop table if exists upms_user_role;

/*==============================================================*/
/* Table: cms_article                                           */
/*==============================================================*/
create table cms_article
(
   article_id           int(10) unsigned not null auto_increment comment '文章编号',
   title                varchar(200) not null comment '文章标题',
   author               varchar(50) default NULL comment '文章原作者',
   fromurl              varchar(300) default NULL comment '转载来源网址',
   image                varchar(300) default NULL comment '封面图',
   keywords             varchar(100) default NULL comment '关键字',
   description          varchar(500) default NULL comment '简介',
   type                 tinyint(4) not null default 1 comment '类型(1:普通,2:热门...)',
   allowcomments        tinyint(4) not null default 1 comment '是否允许评论(0:不允许,1:允许)',
   status               tinyint(4) not null default 1 comment '状态(-1:不通过,0未审核,1:通过)',
   content              mediumtext comment '内容',
   user_id              int(10) unsigned not null comment '发布人id',
   readnumber           int(10) unsigned not null default 0 comment '阅读数量',
   ctime                bigint(20) unsigned not null comment '创建时间',
   orders               bigint(20) unsigned not null comment '排序',
   primary key (article_id),
   key cms_article_orders (orders)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

alter table cms_article comment '文章表';

/*==============================================================*/
/* Table: cms_article_category                                  */
/*==============================================================*/
create table cms_article_category
(
   article_category_id  int(10) unsigned not null auto_increment comment '编号',
   article_id           int(10) unsigned not null comment '文章编号',
   category_id          int(10) unsigned not null comment '类目编号',
   primary key (article_category_id),
   key cms_article_category_article_id (article_id),
   key cms_article_category_category_id (category_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章类目表';

alter table cms_article_category comment '文章类目关联表';

/*==============================================================*/
/* Table: cms_article_tag                                       */
/*==============================================================*/
create table cms_article_tag
(
   article_tag_id       int(10) unsigned not null auto_increment comment '编号',
   article_id           int(10) unsigned not null comment '文章编号',
   tag_id               int(10) unsigned not null comment '标签编号',
   primary key (article_tag_id),
   key cms_article_tag_article_id (article_id),
   key cms_article_tag_tag_id (tag_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章标签表';

alter table cms_article_tag comment '文章标签关联表';

/*==============================================================*/
/* Table: cms_book                                              */
/*==============================================================*/
create table cms_book
(
   book_id              int(10) unsigned not null auto_increment comment '编号',
   user_id              int(10) unsigned not null comment '用户编号',
   name                 varchar(45) not null comment '书名',
   primary key (book_id),
   key FK_book_1 (user_id)
)
ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COMMENT='用户书籍表';

alter table cms_book comment '书';

/*==============================================================*/
/* Table: cms_category                                          */
/*==============================================================*/
create table cms_category
(
   category_id          int(10) unsigned not null auto_increment comment '类目编号',
   pid                  int(10) unsigned comment '上级编号',
   level                tinyint(4) not null comment '层级',
   name                 varchar(20) not null comment '名称',
   description          varchar(200) default NULL comment '描述',
   icon                 varchar(50) default NULL comment '图标',
   type                 tinyint(3) not null default 1 comment '类型(1:普通,2:热门...)',
   alias                varchar(20) default NULL comment '别名',
   ctime                bigint(20) unsigned not null comment '创建时间',
   orders               bigint(255) unsigned not null comment '排序',
   primary key (category_id),
   key cms_category_orders (orders),
   key cms_category_pid (pid),
   key cms_category_alias (alias),
   key cms_category_level (level)
)
ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='类目表';

alter table cms_category comment '类目表';

/*==============================================================*/
/* Table: cms_category_tag                                      */
/*==============================================================*/
create table cms_category_tag
(
   category_tag_id      int(10) unsigned not null auto_increment comment '编号',
   category_id          int(10) unsigned not null comment '类目编号',
   tag_id               int(10) unsigned not null comment '标签编号',
   primary key (category_tag_id),
   key cms_category_tag_tag_id (tag_id),
   key cms_category_tag_category_id (category_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT=' 分类标签表';

alter table cms_category_tag comment '类目标签关联表';

/*==============================================================*/
/* Table: cms_comment                                           */
/*==============================================================*/
create table cms_comment
(
   comment_id           int(10) unsigned not null auto_increment comment '编号',
   pid                  int(10) unsigned default NULL comment '回复楼中楼编号回复楼中楼编号',
   article_id           int(10) unsigned not null comment '文章编号',
   user_id              int(10) unsigned not null comment '用户编号',
   content              text not null comment '评论内容',
   status               tinyint(4) not null default 1 comment '状态(-1:不通过,0:未审核,1:通过)',
   ip                   varchar(30) default NULL comment '评论人ip地址',
   agent                varchar(200) default NULL comment '评论人终端信息',
   ctime                bigint(20) not null comment '创建时间',
   primary key (comment_id),
   key cms_comment_article_id (article_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

alter table cms_comment comment '评论表';

/*==============================================================*/
/* Table: cms_page                                              */
/*==============================================================*/
create table cms_page
(
   page_id              int(10) unsigned not null auto_increment,
   pid                  int(10),
   alias                varchar(20),
   content              mediumtext,
   keywords             varchar(100),
   description          varchar(300),
   ctime                bigint(20),
   orders               bigint(20),
   primary key (page_id)
);

alter table cms_page comment '页面';

/*==============================================================*/
/* Table: cms_setting                                           */
/*==============================================================*/
create table cms_setting
(
   setting_id           int(10) unsigned not null auto_increment,
   setting_key          varchar(10),
   setting_value        varchar(500),
   primary key (setting_id)
);

alter table cms_setting comment '网站配置';

/*==============================================================*/
/* Table: cms_tag                                               */
/*==============================================================*/
create table cms_tag
(
   tag_id               int(10) unsigned not null auto_increment comment '标签编号',
   name                 varchar(20) not null comment '名称',
   description          varchar(200) default NULL comment '描述',
   icon                 varchar(50) default NULL comment '图标',
   type                 tinyint(4) not null default 1 comment '类型(1:普通,2:热门...)',
   alias                varchar(20) default NULL comment '别名',
   ctime                bigint(20) unsigned not null comment '创建时间',
   orders               bigint(20) unsigned not null comment '排序',
   primary key (tag_id),
   key cms_tag_orders (orders),
   key cms_tag_alias (alias)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签表';

alter table cms_tag comment '标签表';

/*==============================================================*/
/* Table: cms_user                                              */
/*==============================================================*/
create table cms_user
(
   user_id              int(10) unsigned not null auto_increment comment '编号',
   username             varchar(32) default NULL comment '账号',
   password             varchar(32) default NULL comment '密码',
   nickname             varchar(32) default NULL comment '昵称',
   sex                  int(11) default NULL comment '0未知,1男,2女',
   ctime                bigint(20) default NULL comment '创建时间',
   content              text comment '备注',
   primary key (user_id)
)
ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='用户表';

alter table cms_user comment '用户';

/*==============================================================*/
/* Table: pay_in_order                                          */
/*==============================================================*/
create table pay_in_order
(
   pay_in_order_id      int(10) unsigned not null auto_increment,
   pay_vendor_id        int(10),
   pay_mch_id           int(10),
   amount               decimal not null,
   status               tinyint(4) not null,
   ctime                bigint(20) unsigned not null,
   primary key (pay_in_order_id)
);

alter table pay_in_order comment '收入订单表';

/*==============================================================*/
/* Table: pay_in_order_detail                                   */
/*==============================================================*/
create table pay_in_order_detail
(
   pay_in_order_detail_id int(10) unsigned not null auto_increment,
   pay_in_order_id      int(10),
   product_id           varchar(50),
   product_name         varchar(100),
   product_price        decimal,
   product_count        int(10),
   remark               varchar(500),
   primary key (pay_in_order_detail_id)
);

alter table pay_in_order_detail comment '收入订单明细表';

/*==============================================================*/
/* Table: pay_mch                                               */
/*==============================================================*/
create table pay_mch
(
   pay_mch_id           int(10) not null auto_increment,
   mch_id               varchar(20),
   name                 varchar(20),
   reqKey               varchar(50),
   resKey               varchar(50),
   primary key (pay_mch_id)
);

alter table pay_mch comment '支付中心商户管理表';

/*==============================================================*/
/* Table: pay_out_order                                         */
/*==============================================================*/
create table pay_out_order
(
   pay_out_order_id     int(10) unsigned not null auto_increment,
   pay_mch_id           int(10),
   pay_vendor_id        int(10),
   amount               decimal not null,
   status               tinyint(4) not null,
   ctime                bigint(20) unsigned not null,
   primary key (pay_out_order_id)
);

alter table pay_out_order comment '支出订单表';

/*==============================================================*/
/* Table: pay_out_order_detail                                  */
/*==============================================================*/
create table pay_out_order_detail
(
   pay_out_order_detail_id int(10) unsigned not null auto_increment,
   pay_out_order_id     int(10),
   remark               varchar(500),
   primary key (pay_out_order_detail_id)
);

alter table pay_out_order_detail comment '支出订单明细表';

/*==============================================================*/
/* Table: pay_pay                                               */
/*==============================================================*/
create table pay_pay
(
   pay_pay_id           int(10) not null auto_increment,
   pay_type_id          int(10),
   param                varchar(1000),
   primary key (pay_pay_id)
);

alter table pay_pay comment '支付参数配置表';

/*==============================================================*/
/* Table: pay_type                                              */
/*==============================================================*/
create table pay_type
(
   pay_type_id          int(10) not null auto_increment,
   pay_vendor_id        int(10),
   pay_mch_id           int(10),
   primary key (pay_type_id)
);

alter table pay_type comment '商户支持支付类型表';

/*==============================================================*/
/* Table: pay_vendor                                            */
/*==============================================================*/
create table pay_vendor
(
   pay_vendor_id        int(10) not null auto_increment,
   name                 varchar(20),
   appid                varchar(50),
   appsecret            varchar(150),
   config               varchar(1000),
   primary key (pay_vendor_id)
);

alter table pay_vendor comment '第三方支付标识表';

/*==============================================================*/
/* Table: pay_vest                                              */
/*==============================================================*/
create table pay_vest
(
   pay_vest_id          int(10) not null auto_increment,
   pay_type_id          int(10),
   prefix               varchar(20),
   param                varchar(1000),
   primary key (pay_vest_id)
);

alter table pay_vest comment '马甲支付参数配置表';

/*==============================================================*/
/* Table: upms_organization                                     */
/*==============================================================*/
create table upms_organization
(
   organization_id      int(10) unsigned not null auto_increment,
   system_id            int(10) unsigned not null,
   name                 varchar(20),
   description          varchar(1000),
   primary key (organization_id)
);

alter table upms_organization comment '组织';

/*==============================================================*/
/* Table: upms_permission                                       */
/*==============================================================*/
create table upms_permission
(
   permission_id        int(10) unsigned not null auto_increment,
   system_id            int(10) unsigned not null,
   primary key (permission_id)
);

alter table upms_permission comment '权限';

/*==============================================================*/
/* Table: upms_role                                             */
/*==============================================================*/
create table upms_role
(
   role_id              int(10) unsigned not null auto_increment,
   system_id            int(10) unsigned not null,
   name                 varchar(20),
   description          varchar(1000),
   status               tinyint(4) not null,
   ctime                bigint(20) not null,
   orders               bigint(20) not null,
   primary key (role_id)
);

alter table upms_role comment '角色';

/*==============================================================*/
/* Table: upms_role_permission                                  */
/*==============================================================*/
create table upms_role_permission
(
   role_permission_id   int(10) unsigned not null auto_increment,
   role_id              int(10) unsigned not null,
   permission_id        int(10) unsigned not null,
   primary key (role_permission_id)
);

alter table upms_role_permission comment '角色权限关联表';

/*==============================================================*/
/* Table: upms_system                                           */
/*==============================================================*/
create table upms_system
(
   system_id            int(10) unsigned not null auto_increment,
   icon                 varchar(20),
   basepath             varchar(100),
   status               tinyint(4),
   name                 varchar(20),
   ctime                bigint(20),
   orders               bigint(20),
   primary key (system_id)
);

alter table upms_system comment '系统';

/*==============================================================*/
/* Table: upms_user                                             */
/*==============================================================*/
create table upms_user
(
   user_id              int(10) unsigned not null auto_increment,
   system_id            int(10) unsigned not null,
   username             varchar(20) not null,
   password             varchar(32) not null,
   salt                 varchar(32),
   realname             varchar(20),
   avatar               varchar(50),
   phone                varchar(20),
   email                varchar(50),
   sex                  tinyint(4),
   ctime                bigint(20),
   primary key (user_id)
);

alter table upms_user comment '用户';

/*==============================================================*/
/* Table: upms_user_organization                                */
/*==============================================================*/
create table upms_user_organization
(
   user_organization_id int(10) unsigned not null auto_increment,
   user_id              int(10) unsigned not null,
   organization_id      int(10) unsigned not null,
   primary key (user_organization_id)
);

alter table upms_user_organization comment '用户组织关联表';

/*==============================================================*/
/* Table: upms_user_permission                                  */
/*==============================================================*/
create table upms_user_permission
(
   user_permission_id   int(10) unsigned not null auto_increment,
   user_id              int(10) unsigned not null,
   permission_id        int(10) unsigned not null,
   primary key (user_permission_id)
);

alter table upms_user_permission comment '用户权限关联表';

/*==============================================================*/
/* Table: upms_user_role                                        */
/*==============================================================*/
create table upms_user_role
(
   user_role_id         int(10) unsigned not null auto_increment,
   user_id              int(10) unsigned not null,
   role_id              int(10),
   role                 int(10) unsigned not null,
   primary key (user_role_id)
);

alter table upms_user_role comment '用户角色关联表';

alter table cms_article_category add constraint FK_Reference_7 foreign key (category_id)
      references cms_category (category_id) on delete cascade on update cascade;

alter table cms_article_category add constraint FK_Reference_8 foreign key (article_id)
      references cms_article (article_id) on delete cascade on update cascade;

alter table cms_article_tag add constraint FK_Reference_3 foreign key (article_id)
      references cms_article (article_id) on delete cascade on update cascade;

alter table cms_article_tag add constraint FK_Reference_4 foreign key (tag_id)
      references cms_tag (tag_id) on delete cascade on update cascade;

alter table cms_book add constraint FK_Reference_9 foreign key (user_id)
      references cms_user (user_id) on delete cascade on update cascade;

alter table cms_category add constraint FK_Reference_10 foreign key (pid)
      references cms_category (category_id) on delete set null on update restrict;

alter table cms_category_tag add constraint FK_Reference_5 foreign key (category_id)
      references cms_category (category_id) on delete cascade on update cascade;

alter table cms_category_tag add constraint FK_Reference_6 foreign key (tag_id)
      references cms_tag (tag_id) on delete cascade on update cascade;

alter table cms_comment add constraint FK_Reference_1 foreign key (article_id)
      references cms_article (article_id) on delete cascade on update cascade;

alter table cms_comment add constraint FK_Reference_2 foreign key (pid)
      references cms_comment (comment_id) on delete cascade on update cascade;

alter table pay_in_order add constraint FK_Reference_32 foreign key (pay_vendor_id)
      references pay_vendor (pay_vendor_id) on delete restrict on update restrict;

alter table pay_in_order add constraint FK_Reference_38 foreign key (pay_mch_id)
      references pay_mch (pay_mch_id) on delete restrict on update restrict;

alter table pay_in_order_detail add constraint FK_Reference_40 foreign key (pay_in_order_id)
      references pay_in_order (pay_in_order_id) on delete restrict on update restrict;

alter table pay_out_order add constraint FK_Reference_33 foreign key (pay_vendor_id)
      references pay_vendor (pay_vendor_id) on delete restrict on update restrict;

alter table pay_out_order add constraint FK_Reference_39 foreign key (pay_mch_id)
      references pay_mch (pay_mch_id) on delete restrict on update restrict;

alter table pay_out_order_detail add constraint FK_Reference_31 foreign key (pay_out_order_id)
      references pay_out_order (pay_out_order_id) on delete restrict on update restrict;

alter table pay_pay add constraint FK_Reference_35 foreign key (pay_type_id)
      references pay_type (pay_type_id) on delete restrict on update restrict;

alter table pay_type add constraint FK_Reference_34 foreign key (pay_vendor_id)
      references pay_vendor (pay_vendor_id) on delete restrict on update restrict;

alter table pay_type add constraint FK_Reference_37 foreign key (pay_mch_id)
      references pay_mch (pay_mch_id) on delete restrict on update restrict;

alter table pay_vest add constraint FK_Reference_36 foreign key (pay_type_id)
      references pay_type (pay_type_id) on delete restrict on update restrict;

alter table upms_organization add constraint FK_Reference_15 foreign key (system_id)
      references upms_system (system_id) on delete restrict on update restrict;

alter table upms_role add constraint FK_Reference_17 foreign key (system_id)
      references upms_system (system_id) on delete restrict on update restrict;

alter table upms_role_permission add constraint FK_Reference_22 foreign key (permission_id)
      references upms_permission (permission_id) on delete restrict on update restrict;

alter table upms_role_permission add constraint FK_Reference_23 foreign key (role_id)
      references upms_role (role_id) on delete restrict on update restrict;

alter table upms_user add constraint FK_Reference_16 foreign key (system_id)
      references upms_system (system_id) on delete restrict on update restrict;

alter table upms_user_organization add constraint FK_Reference_18 foreign key (user_id)
      references upms_user (user_id) on delete restrict on update restrict;

alter table upms_user_organization add constraint FK_Reference_19 foreign key (organization_id)
      references upms_organization (organization_id) on delete restrict on update restrict;

alter table upms_user_permission add constraint FK_Reference_24 foreign key (user_id)
      references upms_user (user_id) on delete restrict on update restrict;

alter table upms_user_permission add constraint FK_Reference_25 foreign key (permission_id)
      references upms_permission (permission_id) on delete restrict on update restrict;

alter table upms_user_role add constraint FK_Reference_20 foreign key (user_id)
      references upms_user (user_id) on delete restrict on update restrict;

alter table upms_user_role add constraint FK_Reference_21 foreign key (role_id)
      references upms_role (role_id) on delete restrict on update restrict;

