/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/3/28 21:32:32                           */
/*==============================================================*/


drop table if exists tmp_upms_organization;

rename table upms_organization to tmp_upms_organization;

drop table if exists tmp_upms_user_permission;

rename table upms_user_permission to tmp_upms_user_permission;

alter table upms_log
   modify column parameter mediumtext;

alter table upms_log
   modify column result mediumtext;

/*==============================================================*/
/* Table: upms_organization                                     */
/*==============================================================*/
create table upms_organization
(
   organization_id      int(10) unsigned not null auto_increment comment '编号',
   pid                  int(10) comment '所属上级',
   name                 varchar(20) comment '组织名称',
   description          varchar(1000) comment '组织描述',
   ctime                bigint(20) comment '创建时间',
   primary key (organization_id)
);

alter table upms_organization comment '组织';

insert into upms_organization (organization_id, name, description, ctime)
select organization_id, name, description, ctime
from tmp_upms_organization;

/*==============================================================*/
/* Table: upms_user_permission                                  */
/*==============================================================*/
create table upms_user_permission
(
   user_permission_id   int(10) unsigned not null auto_increment comment '编号',
   user_id              int(10) unsigned not null comment '用户编号',
   permission_id        int(10) unsigned not null comment '权限编号',
   type                 tinyint(4) not null comment '权限类型(-1:减权限,1:增权限)',
   primary key (user_permission_id)
);

alter table upms_user_permission comment '用户权限关联表';

#WARNING: The following insert order will fail because it cannot give value to mandatory columns
insert into upms_user_permission (user_permission_id, user_id, permission_id, type)
select user_permission_id, user_id, permission_id, ?
from tmp_upms_user_permission;

alter table upms_user_organization add constraint FK_Reference_19 foreign key (organization_id)
      references upms_organization (organization_id) on delete restrict on update restrict;

alter table upms_user_permission add constraint FK_Reference_24 foreign key (user_id)
      references upms_user (user_id) on delete restrict on update restrict;

alter table upms_user_permission add constraint FK_Reference_25 foreign key (permission_id)
      references upms_permission (permission_id) on delete restrict on update restrict;

