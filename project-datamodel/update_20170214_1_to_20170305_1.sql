/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/3/5 20:19:29                            */
/*==============================================================*/


drop table if exists tmp_upms_permission;

rename table upms_permission to tmp_upms_permission;

drop table if exists tmp_upms_system;

rename table upms_system to tmp_upms_system;

/*==============================================================*/
/* Table: upms_permission                                       */
/*==============================================================*/
create table upms_permission
(
   permission_id        int(10) unsigned not null auto_increment comment '编号',
   system_id            int(10) unsigned not null comment '所属系统',
   pid                  int(10) comment '所属上级',
   name                 varchar(20) comment '名称',
   type                 tinyint(4) comment '类型(1:目录,2:菜单,3:按钮)',
   permission_value     varchar(50) comment '权限值',
   uri                  varchar(100) comment '路径',
   icon                 varchar(50) comment '图标',
   status               tinyint(4) comment '状态(0:禁止,1:正常)',
   ctime                bigint(20) comment '创建时间',
   orders               bigint(20) comment '排序',
   primary key (permission_id)
);

alter table upms_permission comment '权限';

insert into upms_permission (permission_id, system_id, pid, name, type, permission_value, uri, icon, status, ctime, orders)
select permission_id, system_id, pid, name, type, permission_value, uri, icon, status, ctime, orders
from tmp_upms_permission;

/*==============================================================*/
/* Table: upms_system                                           */
/*==============================================================*/
create table upms_system
(
   system_id            int(10) unsigned not null auto_increment comment '编号',
   icon                 varchar(50) comment '图标',
   banner               varchar(50) comment '背景',
   theme                varchar(50) comment '主题',
   basepath             varchar(100) comment '根目录',
   status               tinyint(4) comment '状态(-1:黑名单,1:正常)',
   name                 varchar(20) comment '系统名称',
   title                varchar(20) comment '系统标题',
   description          varchar(300) comment '系统描述',
   ctime                bigint(20) comment '创建时间',
   orders               bigint(20) comment '排序',
   primary key (system_id)
);

alter table upms_system comment '系统';

insert into upms_system (system_id, icon, basepath, status, name, title, description, ctime, orders)
select system_id, icon, basepath, status, name, title, description, ctime, orders
from tmp_upms_system;

alter table upms_permission add constraint FK_Reference_29 foreign key (system_id)
      references upms_system (system_id) on delete restrict on update restrict;

alter table upms_role_permission add constraint FK_Reference_22 foreign key (permission_id)
      references upms_permission (permission_id) on delete restrict on update restrict;

alter table upms_user_permission add constraint FK_Reference_25 foreign key (permission_id)
      references upms_permission (permission_id) on delete restrict on update restrict;

