/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/3/18 18:17:42                           */
/*==============================================================*/


drop table if exists tmp_cms_article;

rename table cms_article to tmp_cms_article;

drop table if exists tmp_cms_page;

rename table cms_page to tmp_cms_page;

/*==============================================================*/
/* Table: cms_article                                           */
/*==============================================================*/
create table cms_article
(
   article_id           int(10) unsigned not null auto_increment comment '文章编号',
   topic_id             int comment '所属专题',
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

insert into cms_article (article_id, title, author, fromurl, image, keywords, description, type, allowcomments, status, content, user_id, readnumber, ctime, orders)
select article_id, title, author, fromurl, image, keywords, description, type, allowcomments, status, content, user_id, readnumber, ctime, orders
from tmp_cms_article;

/*==============================================================*/
/* Table: cms_menu                                              */
/*==============================================================*/
create table cms_menu
(
   page_id              int unsigned not null auto_increment comment '编号',
   pid                  int comment '父菜单',
   name                 varchar(20) comment '名称',
   url                  varchar(100) comment '链接',
   target               varchar(10) comment '打开方式',
   orders               bigint comment '排序',
   primary key (page_id)
);

alter table cms_menu comment '菜单';

/*==============================================================*/
/* Table: cms_page                                              */
/*==============================================================*/
create table cms_page
(
   page_id              int(10) unsigned not null auto_increment comment '编码',
   pid                  int(10) comment '父页面',
   title                varchar(20) comment '标题',
   alias                varchar(20) comment '别名',
   content              mediumtext comment '页面内容',
   keywords             varchar(100) comment '关键字',
   description          varchar(300) comment '描述',
   ctime                bigint(20) comment '创建时间',
   orders               bigint(20) comment '排序',
   primary key (page_id)
);

alter table cms_page comment '页面';

insert into cms_page (page_id, pid, alias, content, keywords, description, ctime, orders)
select page_id, pid, alias, content, keywords, description, ctime, orders
from tmp_cms_page;

/*==============================================================*/
/* Table: cms_topic                                             */
/*==============================================================*/
create table cms_topic
(
   topic_id             int unsigned not null auto_increment comment '编号',
   title                varchar(100) comment '标题',
   description          varchar(300) comment '描述',
   url                  varchar(100) comment '链接',
   ctime                bigint comment '创建时间',
   primary key (topic_id)
);

alter table cms_topic comment '专题';

alter table cms_article add constraint FK_Reference_30 foreign key (topic_id)
      references cms_topic (topic_id) on delete restrict on update restrict;

alter table cms_article_category add constraint FK_Reference_8 foreign key (article_id)
      references cms_article (article_id) on delete cascade on update cascade;

alter table cms_article_tag add constraint FK_Reference_3 foreign key (article_id)
      references cms_article (article_id) on delete cascade on update cascade;

alter table cms_comment add constraint FK_Reference_1 foreign key (article_id)
      references cms_article (article_id) on delete cascade on update cascade;

