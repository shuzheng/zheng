/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/3/14 17:26:30                           */
/*==============================================================*/


/*==============================================================*/
/* Table: upms_log                                              */
/*==============================================================*/
create table upms_log
(
   log_id               int not null auto_increment comment '编号',
   description          varchar(100) comment '操作描述',
   username             varchar(20) comment '操作用户',
   start_time           bigint(20) comment '操作时间',
   spend_time           int comment '消耗时间',
   base_path            varchar(100) comment '根路径',
   uri                  varchar(50) comment 'URI',
   url                  varchar(200) comment 'URL',
   method               varchar(10) comment '请求类型',
   parameter            text comment '请求参数',
   user_agent           varchar(200) comment '用户标识',
   ip                   varchar(30) comment 'IP地址',
   result               text comment '响应结果',
   primary key (log_id)
);

alter table upms_log comment '操作日志';

