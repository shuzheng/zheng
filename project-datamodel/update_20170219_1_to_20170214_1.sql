/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/2/24 12:46:03                           */
/*==============================================================*/


drop table if exists tmp_upms_role;

rename table upms_role to tmp_upms_role;

/*==============================================================*/
/* Table: upms_role                                             */
/*==============================================================*/
create table upms_role
(
   role_id              int(10) unsigned not null auto_increment comment '编号',
   name                 varchar(20) comment '角色名称',
   title                varchar(20) comment '角色标题',
   description          varchar(1000) comment '角色描述',
   ctime                bigint(20) not null comment '创建时间',
   orders               bigint(20) not null comment '排序',
   primary key (role_id)
);

alter table upms_role comment '角色';

insert into upms_role (role_id, name, description, ctime, orders)
select role_id, name, description, ctime, orders
from tmp_upms_role;

alter table upms_role_permission add constraint FK_Reference_23 foreign key (role_id)
      references upms_role (role_id) on delete restrict on update restrict;

alter table upms_user_role add constraint FK_Reference_21 foreign key (role_id)
      references upms_role (role_id) on delete restrict on update restrict;

