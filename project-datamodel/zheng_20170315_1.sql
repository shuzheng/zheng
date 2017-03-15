/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : zheng

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-03-15 13:11:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for upms_log
-- ----------------------------
DROP TABLE IF EXISTS `upms_log`;
CREATE TABLE `upms_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `description` varchar(100) DEFAULT NULL COMMENT '操作描述',
  `username` varchar(20) DEFAULT NULL COMMENT '操作用户',
  `start_time` bigint(20) DEFAULT NULL COMMENT '操作时间',
  `spend_time` int(11) DEFAULT NULL COMMENT '消耗时间',
  `base_path` varchar(100) DEFAULT NULL COMMENT '根路径',
  `uri` varchar(50) DEFAULT NULL COMMENT 'URI',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL',
  `method` varchar(10) DEFAULT NULL COMMENT '请求类型',
  `parameter` text COMMENT '请求参数',
  `user_agent` varchar(200) DEFAULT NULL COMMENT '用户标识',
  `ip` varchar(30) DEFAULT NULL COMMENT 'IP地址',
  `result` text COMMENT '响应结果',
  `permissions` varchar(100) DEFAULT NULL COMMENT '权限值',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COMMENT='操作日志';

-- ----------------------------
-- Records of upms_log
-- ----------------------------
