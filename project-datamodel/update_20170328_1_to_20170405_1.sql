/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/4/5 22:18:07                            */
/*==============================================================*/


drop table if exists tmp_cms_article;

rename table cms_article to tmp_cms_article;

drop table if exists cms_book;

drop table if exists tmp_cms_category;

rename table cms_category to tmp_cms_category;

drop table if exists tmp_cms_comment;

rename table cms_comment to tmp_cms_comment;

drop table if exists tmp_cms_tag;

rename table cms_tag to tmp_cms_tag;

drop table if exists cms_user;

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
   top                  int not null default 0 comment '置顶等级',
   system_id            int comment '所属系统',
   ctime                bigint(20) unsigned not null comment '创建时间',
   orders               bigint(20) unsigned not null comment '排序',
   primary key (article_id),
   key cms_article_orders (orders)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

alter table cms_article comment '文章表';

insert into cms_article (article_id, topic_id, title, author, fromurl, image, keywords, description, type, allowcomments, status, content, user_id, readnumber, ctime, orders)
select article_id, topic_id, title, author, fromurl, image, keywords, description, type, allowcomments, status, content, user_id, readnumber, ctime, orders
from tmp_cms_article;

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
   system_id            int comment '所属系统',
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

insert into cms_category (category_id, pid, level, name, description, icon, type, alias, ctime, orders)
select category_id, pid, level, name, description, icon, type, alias, ctime, orders
from tmp_cms_category;

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
   system_id            int comment '所属系统',
   ctime                bigint(20) not null comment '创建时间',
   primary key (comment_id),
   key cms_comment_article_id (article_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

alter table cms_comment comment '评论表';

insert into cms_comment (comment_id, pid, article_id, user_id, content, status, ip, agent, ctime)
select comment_id, pid, article_id, user_id, content, status, ip, agent, ctime
from tmp_cms_comment;

/*==============================================================*/
/* Table: cms_system                                            */
/*==============================================================*/
create table cms_system
(
   system_id            int unsigned not null auto_increment comment '编号',
   name                 varchar(20) not null comment '系统名称',
   code                 varchar(20) comment '别名',
   description          varchar(300) comment '描述',
   ctime                bigint comment '创建时间',
   orders               bigint comment '排序',
   primary key (system_id)
);

alter table cms_system comment '系统管理';

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
   system_id            int comment '所属系统',
   ctime                bigint(20) unsigned not null comment '创建时间',
   orders               bigint(20) unsigned not null comment '排序',
   primary key (tag_id),
   key cms_tag_orders (orders),
   key cms_tag_alias (alias)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签表';

alter table cms_tag comment '标签表';

insert into cms_tag (tag_id, name, description, icon, type, alias, ctime, orders)
select tag_id, name, description, icon, type, alias, ctime, orders
from tmp_cms_tag;

alter table cms_article add constraint FK_Reference_30 foreign key (topic_id)
      references cms_topic (topic_id) on delete restrict on update restrict;

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
      references cms_comment (comment_id) on delete cascade on update cascade;

