/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : zheng

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-02-06 21:09:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `article_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章编号',
  `title` varchar(200) NOT NULL COMMENT '文章标题',
  `author` varchar(50) DEFAULT NULL COMMENT '文章原作者',
  `fromurl` varchar(300) DEFAULT NULL COMMENT '转载来源网址',
  `image` varchar(300) DEFAULT NULL COMMENT '封面图',
  `keywords` varchar(100) DEFAULT NULL COMMENT '关键字',
  `description` varchar(500) DEFAULT NULL COMMENT '简介',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类型(1:普通,2:热门...)',
  `allowcomments` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否允许评论(0:不允许,1:允许)',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态(-1:不通过,0未审核,1:通过)',
  `content` mediumtext COMMENT '内容',
  `user_id` int(10) unsigned NOT NULL COMMENT '发布人id',
  `readnumber` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '阅读数量',
  `ctime` bigint(20) unsigned NOT NULL COMMENT '创建时间',
  `orders` bigint(20) unsigned NOT NULL COMMENT '排序',
  PRIMARY KEY (`article_id`),
  KEY `cms_article_orders` (`orders`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

-- ----------------------------
-- Records of cms_article
-- ----------------------------

-- ----------------------------
-- Table structure for cms_article_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_article_category`;
CREATE TABLE `cms_article_category` (
  `article_category_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `article_id` int(10) unsigned NOT NULL COMMENT '文章编号',
  `category_id` int(10) unsigned NOT NULL COMMENT '类目编号',
  PRIMARY KEY (`article_category_id`),
  KEY `cms_article_category_article_id` (`article_id`),
  KEY `cms_article_category_category_id` (`category_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`category_id`) REFERENCES `cms_category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`article_id`) REFERENCES `cms_article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章类目关联表';

-- ----------------------------
-- Records of cms_article_category
-- ----------------------------

-- ----------------------------
-- Table structure for cms_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `cms_article_tag`;
CREATE TABLE `cms_article_tag` (
  `article_tag_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `article_id` int(10) unsigned NOT NULL COMMENT '文章编号',
  `tag_id` int(10) unsigned NOT NULL COMMENT '标签编号',
  PRIMARY KEY (`article_tag_id`),
  KEY `cms_article_tag_article_id` (`article_id`),
  KEY `cms_article_tag_tag_id` (`tag_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`article_id`) REFERENCES `cms_article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`tag_id`) REFERENCES `cms_tag` (`tag_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章标签关联表';

-- ----------------------------
-- Records of cms_article_tag
-- ----------------------------

-- ----------------------------
-- Table structure for cms_book
-- ----------------------------
DROP TABLE IF EXISTS `cms_book`;
CREATE TABLE `cms_book` (
  `book_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户编号',
  `name` varchar(45) NOT NULL COMMENT '书名',
  PRIMARY KEY (`book_id`),
  KEY `FK_book_1` (`user_id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`user_id`) REFERENCES `cms_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COMMENT='书';

-- ----------------------------
-- Records of cms_book
-- ----------------------------

-- ----------------------------
-- Table structure for cms_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_category`;
CREATE TABLE `cms_category` (
  `category_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '类目编号',
  `pid` int(10) unsigned DEFAULT NULL COMMENT '上级编号',
  `level` tinyint(4) NOT NULL COMMENT '层级',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '类型(1:普通,2:热门...)',
  `alias` varchar(20) DEFAULT NULL COMMENT '别名',
  `ctime` bigint(20) unsigned NOT NULL COMMENT '创建时间',
  `orders` bigint(255) unsigned NOT NULL COMMENT '排序',
  PRIMARY KEY (`category_id`),
  KEY `cms_category_orders` (`orders`),
  KEY `cms_category_pid` (`pid`),
  KEY `cms_category_alias` (`alias`),
  KEY `cms_category_level` (`level`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`pid`) REFERENCES `cms_category` (`category_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='类目表';

-- ----------------------------
-- Records of cms_category
-- ----------------------------

-- ----------------------------
-- Table structure for cms_category_tag
-- ----------------------------
DROP TABLE IF EXISTS `cms_category_tag`;
CREATE TABLE `cms_category_tag` (
  `category_tag_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `category_id` int(10) unsigned NOT NULL COMMENT '类目编号',
  `tag_id` int(10) unsigned NOT NULL COMMENT '标签编号',
  PRIMARY KEY (`category_tag_id`),
  KEY `cms_category_tag_tag_id` (`tag_id`),
  KEY `cms_category_tag_category_id` (`category_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`category_id`) REFERENCES `cms_category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`tag_id`) REFERENCES `cms_tag` (`tag_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='类目标签关联表';

-- ----------------------------
-- Records of cms_category_tag
-- ----------------------------

-- ----------------------------
-- Table structure for cms_comment
-- ----------------------------
DROP TABLE IF EXISTS `cms_comment`;
CREATE TABLE `cms_comment` (
  `comment_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pid` int(10) unsigned DEFAULT NULL COMMENT '回复楼中楼编号回复楼中楼编号',
  `article_id` int(10) unsigned NOT NULL COMMENT '文章编号',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户编号',
  `content` text NOT NULL COMMENT '评论内容',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态(-1:不通过,0:未审核,1:通过)',
  `ip` varchar(30) DEFAULT NULL COMMENT '评论人ip地址',
  `agent` varchar(200) DEFAULT NULL COMMENT '评论人终端信息',
  `ctime` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`comment_id`),
  KEY `cms_comment_article_id` (`article_id`),
  KEY `FK_Reference_2` (`pid`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`article_id`) REFERENCES `cms_article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`pid`) REFERENCES `cms_comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

-- ----------------------------
-- Records of cms_comment
-- ----------------------------

-- ----------------------------
-- Table structure for cms_page
-- ----------------------------
DROP TABLE IF EXISTS `cms_page`;
CREATE TABLE `cms_page` (
  `page_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pid` int(10) DEFAULT NULL,
  `alias` varchar(20) DEFAULT NULL,
  `content` mediumtext,
  `keywords` varchar(100) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `ctime` bigint(20) DEFAULT NULL,
  `orders` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`page_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='页面';

-- ----------------------------
-- Records of cms_page
-- ----------------------------

-- ----------------------------
-- Table structure for cms_setting
-- ----------------------------
DROP TABLE IF EXISTS `cms_setting`;
CREATE TABLE `cms_setting` (
  `setting_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `setting_key` varchar(10) DEFAULT NULL,
  `setting_value` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`setting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='网站配置';

-- ----------------------------
-- Records of cms_setting
-- ----------------------------

-- ----------------------------
-- Table structure for cms_tag
-- ----------------------------
DROP TABLE IF EXISTS `cms_tag`;
CREATE TABLE `cms_tag` (
  `tag_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '标签编号',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类型(1:普通,2:热门...)',
  `alias` varchar(20) DEFAULT NULL COMMENT '别名',
  `ctime` bigint(20) unsigned NOT NULL COMMENT '创建时间',
  `orders` bigint(20) unsigned NOT NULL COMMENT '排序',
  PRIMARY KEY (`tag_id`),
  KEY `cms_tag_orders` (`orders`),
  KEY `cms_tag_alias` (`alias`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签表';

-- ----------------------------
-- Records of cms_tag
-- ----------------------------

-- ----------------------------
-- Table structure for cms_user
-- ----------------------------
DROP TABLE IF EXISTS `cms_user`;
CREATE TABLE `cms_user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(32) DEFAULT NULL COMMENT '账号',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `sex` int(11) DEFAULT NULL COMMENT '0未知,1男,2女',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `content` text COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of cms_user
-- ----------------------------

-- ----------------------------
-- Table structure for pay_in_order
-- ----------------------------
DROP TABLE IF EXISTS `pay_in_order`;
CREATE TABLE `pay_in_order` (
  `pay_in_order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pay_vendor_id` int(10) DEFAULT NULL,
  `pay_mch_id` int(10) DEFAULT NULL,
  `amount` decimal(10,0) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `ctime` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`pay_in_order_id`),
  KEY `FK_Reference_32` (`pay_vendor_id`),
  KEY `FK_Reference_38` (`pay_mch_id`),
  CONSTRAINT `FK_Reference_32` FOREIGN KEY (`pay_vendor_id`) REFERENCES `pay_vendor` (`pay_vendor_id`),
  CONSTRAINT `FK_Reference_38` FOREIGN KEY (`pay_mch_id`) REFERENCES `pay_mch` (`pay_mch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收入订单表';

-- ----------------------------
-- Records of pay_in_order
-- ----------------------------

-- ----------------------------
-- Table structure for pay_in_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `pay_in_order_detail`;
CREATE TABLE `pay_in_order_detail` (
  `pay_in_order_detail_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pay_in_order_id` int(10) DEFAULT NULL,
  `product_id` varchar(50) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `product_price` decimal(10,0) DEFAULT NULL,
  `product_count` int(10) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`pay_in_order_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收入订单明细表';

-- ----------------------------
-- Records of pay_in_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for pay_mch
-- ----------------------------
DROP TABLE IF EXISTS `pay_mch`;
CREATE TABLE `pay_mch` (
  `pay_mch_id` int(10) NOT NULL AUTO_INCREMENT,
  `mch_id` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `reqKey` varchar(50) DEFAULT NULL,
  `resKey` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pay_mch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付中心商户管理表';

-- ----------------------------
-- Records of pay_mch
-- ----------------------------

-- ----------------------------
-- Table structure for pay_out_order
-- ----------------------------
DROP TABLE IF EXISTS `pay_out_order`;
CREATE TABLE `pay_out_order` (
  `pay_out_order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pay_mch_id` int(10) DEFAULT NULL,
  `pay_vendor_id` int(10) DEFAULT NULL,
  `amount` decimal(10,0) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `ctime` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`pay_out_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支出订单表';

-- ----------------------------
-- Records of pay_out_order
-- ----------------------------

-- ----------------------------
-- Table structure for pay_out_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `pay_out_order_detail`;
CREATE TABLE `pay_out_order_detail` (
  `pay_out_order_detail_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pay_out_order_id` int(10) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`pay_out_order_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支出订单明细表';

-- ----------------------------
-- Records of pay_out_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for pay_pay
-- ----------------------------
DROP TABLE IF EXISTS `pay_pay`;
CREATE TABLE `pay_pay` (
  `pay_pay_id` int(10) NOT NULL AUTO_INCREMENT,
  `pay_type_id` int(10) DEFAULT NULL,
  `param` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`pay_pay_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付参数配置表';

-- ----------------------------
-- Records of pay_pay
-- ----------------------------

-- ----------------------------
-- Table structure for pay_type
-- ----------------------------
DROP TABLE IF EXISTS `pay_type`;
CREATE TABLE `pay_type` (
  `pay_type_id` int(10) NOT NULL AUTO_INCREMENT,
  `pay_vendor_id` int(10) DEFAULT NULL,
  `pay_mch_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`pay_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商户支持支付类型表';

-- ----------------------------
-- Records of pay_type
-- ----------------------------

-- ----------------------------
-- Table structure for pay_vendor
-- ----------------------------
DROP TABLE IF EXISTS `pay_vendor`;
CREATE TABLE `pay_vendor` (
  `pay_vendor_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `appid` varchar(50) DEFAULT NULL,
  `appsecret` varchar(150) DEFAULT NULL,
  `config` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`pay_vendor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='第三方支付标识表';

-- ----------------------------
-- Records of pay_vendor
-- ----------------------------

-- ----------------------------
-- Table structure for pay_vest
-- ----------------------------
DROP TABLE IF EXISTS `pay_vest`;
CREATE TABLE `pay_vest` (
  `pay_vest_id` int(10) NOT NULL AUTO_INCREMENT,
  `pay_type_id` int(10) DEFAULT NULL,
  `prefix` varchar(20) DEFAULT NULL,
  `param` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`pay_vest_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='马甲支付参数配置表';

-- ----------------------------
-- Records of pay_vest
-- ----------------------------

-- ----------------------------
-- Table structure for upms_organization
-- ----------------------------
DROP TABLE IF EXISTS `upms_organization`;
CREATE TABLE `upms_organization` (
  `organization_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) DEFAULT NULL COMMENT '组织名称',
  `description` varchar(1000) DEFAULT NULL COMMENT '组织描述',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`organization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织';

-- ----------------------------
-- Records of upms_organization
-- ----------------------------

-- ----------------------------
-- Table structure for upms_permission
-- ----------------------------
DROP TABLE IF EXISTS `upms_permission`;
CREATE TABLE `upms_permission` (
  `permission_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `system_id` int(10) unsigned NOT NULL COMMENT '所属系统',
  `pid` int(10) DEFAULT NULL COMMENT '所属上级',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型(1:菜单,2:按钮)',
  `permission_value` varchar(20) DEFAULT NULL COMMENT '权限值',
  `icon` varchar(20) DEFAULT NULL COMMENT '图标',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `orders` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限';

-- ----------------------------
-- Records of upms_permission
-- ----------------------------

-- ----------------------------
-- Table structure for upms_role
-- ----------------------------
DROP TABLE IF EXISTS `upms_role`;
CREATE TABLE `upms_role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `ctime` bigint(20) NOT NULL,
  `orders` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Records of upms_role
-- ----------------------------

-- ----------------------------
-- Table structure for upms_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `upms_role_permission`;
CREATE TABLE `upms_role_permission` (
  `role_permission_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(10) unsigned NOT NULL,
  `permission_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`role_permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

-- ----------------------------
-- Records of upms_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for upms_system
-- ----------------------------
DROP TABLE IF EXISTS `upms_system`;
CREATE TABLE `upms_system` (
  `system_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `icon` varchar(20) DEFAULT NULL COMMENT '图标',
  `basepath` varchar(100) DEFAULT NULL COMMENT '根目录',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态(-1:黑名单,1:正常)',
  `name` varchar(20) DEFAULT NULL COMMENT '系统名称',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `orders` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`system_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='系统';

-- ----------------------------
-- Records of upms_system
-- ----------------------------
INSERT INTO `upms_system` VALUES ('1', '', 'http://upms.zhangshuzheng.cn:1113', '1', 'zheng-upms-app1', '1', '1');
INSERT INTO `upms_system` VALUES ('2', '', 'http://upms.zhangshuzheng.cn:1114', '1', 'zheng-upms-app2', '2', '2');
INSERT INTO `upms_system` VALUES ('3', '', 'http://cms.zhangshuzheng.cn:2222', '1', 'zheng-cms-admin', '3', '3');
INSERT INTO `upms_system` VALUES ('4', '', 'http://upms.zhangshuzheng.cn:1111', '1', 'zheng-upms-server', '4', '4');

-- ----------------------------
-- Table structure for upms_user
-- ----------------------------
DROP TABLE IF EXISTS `upms_user`;
CREATE TABLE `upms_user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(20) NOT NULL COMMENT '帐号',
  `password` varchar(32) NOT NULL COMMENT '密码MD5(密码+盐)',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐',
  `realname` varchar(20) DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(50) DEFAULT NULL COMMENT '头像',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别',
  `locked` tinyint(4) DEFAULT NULL COMMENT '状态(0:正常,1:锁定)',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of upms_user
-- ----------------------------
INSERT INTO `upms_user` VALUES ('1', 'admin', '3038D9CB63B3152A79B8153FB06C02F7', '66f1b370c660445a8657bf8bf1794486', '管理员', null, null, null, null, '0', '1');

-- ----------------------------
-- Table structure for upms_user_organization
-- ----------------------------
DROP TABLE IF EXISTS `upms_user_organization`;
CREATE TABLE `upms_user_organization` (
  `user_organization_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `organization_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_organization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组织关联表';

-- ----------------------------
-- Records of upms_user_organization
-- ----------------------------

-- ----------------------------
-- Table structure for upms_user_permission
-- ----------------------------
DROP TABLE IF EXISTS `upms_user_permission`;
CREATE TABLE `upms_user_permission` (
  `user_permission_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `permission_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户权限关联表';

-- ----------------------------
-- Records of upms_user_permission
-- ----------------------------

-- ----------------------------
-- Table structure for upms_user_role
-- ----------------------------
DROP TABLE IF EXISTS `upms_user_role`;
CREATE TABLE `upms_user_role` (
  `user_role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `role_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of upms_user_role
-- ----------------------------
