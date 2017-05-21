/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/4/26 23:13:39                           */
/*==============================================================*/


/*==============================================================*/
/* Table: ucenter_oauth                                         */
/*==============================================================*/
create table ucenter_oauth
(
   oauth_id             int unsigned not null auto_increment comment '编号',
   name                 varchar(20) comment '认证方式名称',
   primary key (oauth_id)
);

alter table ucenter_oauth comment '认证方式表';

/*==============================================================*/
/* Table: ucenter_user                                          */
/*==============================================================*/
create table ucenter_user
(
   user_id              int unsigned not null auto_increment comment '编号',
   password             varchar(32) comment '密码(MD5(密码+盐))',
   salt                 varchar(32) comment '盐',
   nickname             varchar(20) comment '昵称',
   sex                  tinyint(4) default 0 comment '性别(0:未知,1:男,2:女)',
   avatar               varchar(100) comment '头像',
   create_time          timestamp default CURRENT_TIMESTAMP comment '注册时间',
   create_ip            varchar(50) comment '注册IP地址',
   last_login_time      timestamp comment '最后登录时间',
   last_login_ip        varchar(50) comment '最后登录IP地址',
   primary key (user_id)
);

alter table ucenter_user comment '用户表';

/*==============================================================*/
/* Table: ucenter_user_details                                  */
/*==============================================================*/
create table ucenter_user_details
(
   user_id              int unsigned not null comment '编号',
   signature            varchar(300) comment '个性签名',
   real_name            varchar(20) comment '真实姓名',
   birthday             timestamp comment '出生日期',
   question             varchar(100) comment '帐号安全问题',
   answer               varchar(100) comment '帐号安全答案',
   primary key (user_id)
);

alter table ucenter_user_details comment '用户详情表';

/*==============================================================*/
/* Table: ucenter_user_log                                      */
/*==============================================================*/
create table ucenter_user_log
(
   user_log_id          int unsigned not null auto_increment comment '编号',
   user_id              int unsigned comment '用户编号',
   content              varbinary(100) comment '内容',
   ip                   varchar(20) comment '操作IP地址',
   agent                varbinary(200) comment '操作环境',
   create_time          timestamp default CURRENT_TIMESTAMP comment '操作时间',
   primary key (user_log_id)
);

alter table ucenter_user_log comment '用户操作日志表';

/*==============================================================*/
/* Table: ucenter_user_oauth                                    */
/*==============================================================*/
create table ucenter_user_oauth
(
   user_oauth_id        int unsigned not null auto_increment comment '编号',
   user_id              int unsigned not null comment '帐号编号',
   oauth_id             int unsigned not null comment '认证方式编号',
   open_id              varbinary(50) not null comment '第三方ID',
   status               tinyint(4) unsigned comment '绑定状态(0:解绑,1:绑定)',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   primary key (user_oauth_id)
);

alter table ucenter_user_oauth comment '用户认证方式表';

alter table ucenter_user_details add constraint FK_Reference_41 foreign key (user_id)
      references ucenter_user (user_id) on delete restrict on update restrict;

alter table ucenter_user_log add constraint FK_Reference_44 foreign key (user_id)
      references ucenter_user (user_id) on delete restrict on update restrict;

alter table ucenter_user_oauth add constraint FK_Reference_42 foreign key (user_id)
      references ucenter_user (user_id) on delete restrict on update restrict;

alter table ucenter_user_oauth add constraint FK_Reference_43 foreign key (oauth_id)
      references ucenter_oauth (oauth_id) on delete restrict on update restrict;

