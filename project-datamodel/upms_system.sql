/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : zheng

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-01-19 21:07:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for upms_system
-- ----------------------------
DROP TABLE IF EXISTS `upms_system`;
CREATE TABLE `upms_system` (
  `system_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `icon` varchar(20) DEFAULT NULL,
  `basepath` varchar(100) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `ctime` bigint(20) DEFAULT NULL,
  `orders` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`system_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='系统';

-- ----------------------------
-- Records of upms_system
-- ----------------------------
INSERT INTO `upms_system` VALUES ('1', null, 'http://upms.zhangshuzheng.cn:1113', '1', 'zheng-upms-app1', '1', '1');
INSERT INTO `upms_system` VALUES ('2', null, 'http://upms.zhangshuzheng.cn:1114', '1', 'zheng-upms-app2', '2', '2');
INSERT INTO `upms_system` VALUES ('3', null, 'http://cms.zhangshuzheng.cn:2222', '1', 'zheng-cms-admin', '3', '3');
INSERT INTO `upms_system` VALUES ('4', null, 'http://upms.zhangshuzheng.cn:1111', '1', 'zheng-upms-server', '4', '4');
