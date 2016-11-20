/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/11/20 16:47:38                          */
/*==============================================================*/


drop table if exists cms_article;

drop table if exists cms_article_category;

drop table if exists cms_article_tag;

drop table if exists cms_category;

drop table if exists cms_category_tag;

drop table if exists cms_comment;

drop table if exists cms_tag;

drop table if exists test_book;

drop table if exists test_user;

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

alter table cms_article comment 'cms_article 文章表';

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

alter table cms_article_category comment 'cms_article_category 文章类目关联表';

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

alter table cms_article_tag comment 'cms_article_tag 文章标签关联表';

/*==============================================================*/
/* Table: cms_category                                          */
/*==============================================================*/
create table cms_category
(
   category_id          int(10) unsigned not null auto_increment comment '类目编号',
   pid                  int(10) unsigned comment '上级编号',
   level                tinyint(3) not null comment '层级',
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

alter table cms_category comment 'cms_category 类目表';

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

alter table cms_category_tag comment 'cms_category_tag 类目标签关联表';

/*==============================================================*/
/* Table: cms_comment                                           */
/*==============================================================*/
create table cms_comment
(
   comment_id           int(10) unsigned not null auto_increment comment '编号',
   pid                  int(10) unsigned default 0 comment '回复楼中楼编号回复楼中楼编号',
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

alter table cms_comment comment 'cms_comment 评论表';

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

alter table cms_tag comment 'cms_tag 标签表';

/*==============================================================*/
/* Table: test_book                                             */
/*==============================================================*/
create table test_book
(
   book_id              int(10) unsigned not null auto_increment comment '编号',
   user_id              int(10) unsigned not null comment '用户编号',
   name                 varchar(45) not null comment '书名',
   primary key (book_id),
   key FK_book_1 (user_id)
)
ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COMMENT='用户书籍表';

alter table test_book comment 'test_book 书';

/*==============================================================*/
/* Table: test_user                                             */
/*==============================================================*/
create table test_user
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

alter table test_user comment 'test_user 用户';

alter table cms_article_category add constraint FK_Reference_7 foreign key (category_id)
      references cms_category (category_id) on delete cascade on update cascade;

alter table cms_article_category add constraint FK_Reference_8 foreign key (article_id)
      references cms_article (article_id) on delete cascade on update cascade;

alter table cms_article_tag add constraint FK_Reference_3 foreign key (article_id)
      references cms_article (article_id) on delete cascade on update cascade;

alter table cms_article_tag add constraint FK_Reference_4 foreign key (tag_id)
      references cms_tag (tag_id) on delete cascade on update cascade;

alter table cms_category add constraint FK_Reference_10 foreign key (pid)
      references cms_category (category_id) on delete set null on update restrict;

alter table cms_category_tag add constraint FK_Reference_5 foreign key (category_id)
      references cms_category (category_id) on delete cascade on update cascade;

alter table cms_category_tag add constraint FK_Reference_6 foreign key (tag_id)
      references cms_tag (tag_id) on delete cascade on update cascade;

alter table cms_comment add constraint FK_Reference_1 foreign key (article_id)
      references cms_article (article_id) on delete cascade on update cascade;

alter table cms_comment add constraint FK_Reference_2 foreign key (pid)
      references cms_comment (comment_id) on delete set null;

alter table test_book add constraint FK_Reference_9 foreign key (user_id)
      references test_user (user_id) on delete cascade on update cascade;

