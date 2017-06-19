/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : zheng

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-06-03 17:43:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `article_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章编号',
  `topic_id` int(11) DEFAULT NULL COMMENT '所属专题',
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
  `top` int(11) NOT NULL DEFAULT '0' COMMENT '置顶等级',
  `system_id` int(11) DEFAULT NULL COMMENT '所属系统',
  `ctime` bigint(20) unsigned NOT NULL COMMENT '创建时间',
  `orders` bigint(20) unsigned NOT NULL COMMENT '排序',
  PRIMARY KEY (`article_id`),
  KEY `cms_article_orders` (`orders`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

-- ----------------------------
-- Records of cms_article
-- ----------------------------
INSERT INTO `cms_article` VALUES ('3', '0', '中国经济标题1', '1', '1', '1', '1', '1', '1', '1', '0', '资讯内容', '1', '0', '0', '1', '1489827019528', '1489827019528');
INSERT INTO `cms_article` VALUES ('4', '0', '中国经济标题2', '2', '2', '2', '2', '2', '1', '1', '-1', '资讯内容', '1', '0', '0', '1', '1489827340870', '1489827340870');
INSERT INTO `cms_article` VALUES ('5', '0', '中国经济标题3', '3', '3', '3', '3', '3', '1', '1', '1', '资讯内容', '1', '0', '0', '1', '1489827581414', '1489827581414');
INSERT INTO `cms_article` VALUES ('8', '1', '日本从地球消失，我们倍(喜)感(大)心(普)痛(奔)', 'shuzheng', '网络', '', '日本消失,日本地震', '日本从地震中消失', '1', '1', '1', '好悲(gao)伤(xing)啊', '1', '12', '0', '1', '1489845594355', '1489845594355');
INSERT INTO `cms_article` VALUES ('9', '0', '中国经济标题4', '4', '4', '4', '4', '4', '1', '1', '1', '资讯内容', '1', '0', '0', '1', '1490460546198', '1490460546198');
INSERT INTO `cms_article` VALUES ('10', '0', '中国经济标题5', '5', '5', '5', '5', '5', '1', '1', '1', '资讯内容', '1', '0', '0', '1', '1490460567137', '1490460567137');
INSERT INTO `cms_article` VALUES ('11', '0', '中国经济标题6', '6', '6', '6', '6', '6', '1', '1', '1', '资讯内容', '1', '0', '0', '1', '1490460575304', '1490460575304');
INSERT INTO `cms_article` VALUES ('12', '0', '中国经济标题7', '7', '7', '7', '7', '7', '1', '1', '1', '资讯内容', '1', '0', '0', '1', '1490460582004', '1490460582004');
INSERT INTO `cms_article` VALUES ('13', '0', '中国经济标题8', '8', '8', '8', '8', '8', '1', '1', '1', '资讯内容', '1', '0', '0', '1', '1490460588840', '1490460588840');
INSERT INTO `cms_article` VALUES ('14', '0', '中国经济标题9', '9', '9', '9', '9', '9', '1', '1', '1', '资讯内容', '1', '0', '0', '1', '1490460596394', '1490460596394');
INSERT INTO `cms_article` VALUES ('15', '0', '中国经济标题10', '10', '10', '10', '10', '10', '1', '1', '1', '资讯内容', '1', '0', '0', '1', '1490460612370', '1490460612370');
INSERT INTO `cms_article` VALUES ('16', '0', '中国经济标题11', '11', '11', '11', '11', '11', '1', '1', '1', '资讯内容', '1', '0', '0', '1', '1490460782767', '1490460782767');
INSERT INTO `cms_article` VALUES ('17', '0', '中国经济标题12', '12', '12', '12', '12', '12', '1', '1', '1', '资讯内容', '1', '0', '0', '1', '1490460792501', '1490460792501');
INSERT INTO `cms_article` VALUES ('18', '0', '中国经济标题13', '13', '13', '13', '13', '13', '1', '1', '1', '资讯内容', '1', '0', '0', '1', '1490460800634', '1490460800634');
INSERT INTO `cms_article` VALUES ('19', '0', '人为什么谈恋爱？', 'test', 'test', 'test', 'test', 'test', '1', '1', '1', '人为什么谈恋爱？', '1', '0', '0', '2', '1490460800635', '1490460800635');
INSERT INTO `cms_article` VALUES ('20', '0', 'java开发笔记', 'test', 'test', 'test', 'test', 'test', '1', '1', '1', '好记性不如烂笔头', '1', '0', '0', '3', '1490460800636', '1490460800636');

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='文章类目关联表';

-- ----------------------------
-- Records of cms_article_category
-- ----------------------------
INSERT INTO `cms_article_category` VALUES ('1', '3', '6');
INSERT INTO `cms_article_category` VALUES ('2', '4', '6');
INSERT INTO `cms_article_category` VALUES ('3', '5', '6');
INSERT INTO `cms_article_category` VALUES ('4', '9', '6');
INSERT INTO `cms_article_category` VALUES ('5', '10', '6');
INSERT INTO `cms_article_category` VALUES ('6', '11', '6');
INSERT INTO `cms_article_category` VALUES ('7', '12', '6');
INSERT INTO `cms_article_category` VALUES ('8', '12', '6');
INSERT INTO `cms_article_category` VALUES ('9', '13', '6');
INSERT INTO `cms_article_category` VALUES ('10', '14', '6');
INSERT INTO `cms_article_category` VALUES ('11', '15', '6');
INSERT INTO `cms_article_category` VALUES ('12', '16', '6');
INSERT INTO `cms_article_category` VALUES ('13', '17', '6');
INSERT INTO `cms_article_category` VALUES ('14', '18', '6');
INSERT INTO `cms_article_category` VALUES ('15', '8', '7');
INSERT INTO `cms_article_category` VALUES ('16', '19', '8');
INSERT INTO `cms_article_category` VALUES ('17', '20', '9');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='文章标签关联表';

-- ----------------------------
-- Records of cms_article_tag
-- ----------------------------
INSERT INTO `cms_article_tag` VALUES ('1', '19', '4');
INSERT INTO `cms_article_tag` VALUES ('2', '20', '5');

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
  `system_id` int(11) DEFAULT NULL COMMENT '所属系统',
  `ctime` bigint(20) unsigned NOT NULL COMMENT '创建时间',
  `orders` bigint(255) unsigned NOT NULL COMMENT '排序',
  PRIMARY KEY (`category_id`),
  KEY `cms_category_orders` (`orders`),
  KEY `cms_category_pid` (`pid`),
  KEY `cms_category_alias` (`alias`),
  KEY `cms_category_level` (`level`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='类目表';

-- ----------------------------
-- Records of cms_category
-- ----------------------------
INSERT INTO `cms_category` VALUES ('5', null, '1', '经济', '经济类目', '', '1', 'economic', '1', '1489590733919', '1489590733919');
INSERT INTO `cms_category` VALUES ('6', '5', '2', '中国经济', '中国经济类目', '', '1', 'chinaeconomic', '1', '1489590768989', '1489590768989');
INSERT INTO `cms_category` VALUES ('7', '5', '2', '日本经济', '日本经济类目', '', '1', 'japaneconomic', '1', '1491636586316', '1491636586316');
INSERT INTO `cms_category` VALUES ('8', null, '1', '人类', '人类问题', '', '1', 'people', '2', '1491636586317', '1491636586317');
INSERT INTO `cms_category` VALUES ('9', null, '1', '技术', '技术博文', null, '1', 'technic', '3', '1491636586318', '1491636586318');

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
  `system_id` int(11) DEFAULT NULL COMMENT '所属系统',
  `ctime` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`comment_id`),
  KEY `cms_comment_article_id` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

-- ----------------------------
-- Records of cms_comment
-- ----------------------------
INSERT INTO `cms_comment` VALUES ('1', null, '8', '1', '1', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490535749413');
INSERT INTO `cms_comment` VALUES ('2', null, '8', '1', '2', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536186447');
INSERT INTO `cms_comment` VALUES ('3', null, '8', '1', '3', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536192205');
INSERT INTO `cms_comment` VALUES ('4', null, '8', '1', '4', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536460544');
INSERT INTO `cms_comment` VALUES ('5', null, '8', '1', '5', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536462182');
INSERT INTO `cms_comment` VALUES ('6', null, '8', '1', '6', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536463614');
INSERT INTO `cms_comment` VALUES ('7', null, '8', '1', '7', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536464870');
INSERT INTO `cms_comment` VALUES ('8', null, '8', '1', '8', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536465926');
INSERT INTO `cms_comment` VALUES ('9', null, '8', '1', '9', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536466853');
INSERT INTO `cms_comment` VALUES ('10', null, '8', '1', '10', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536467821');
INSERT INTO `cms_comment` VALUES ('11', null, '19', '1', '1', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '2', '1491737900448');
INSERT INTO `cms_comment` VALUES ('12', null, '19', '1', '3', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '2', '1491737902517');
INSERT INTO `cms_comment` VALUES ('13', null, '19', '1', '4', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '2', '1491737903420');
INSERT INTO `cms_comment` VALUES ('14', null, '19', '1', '5', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '2', '1491737904428');
INSERT INTO `cms_comment` VALUES ('15', null, '19', '1', '6', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '2', '1491737905236');
INSERT INTO `cms_comment` VALUES ('16', null, '19', '1', '7', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '2', '1491737905980');

-- ----------------------------
-- Table structure for cms_menu
-- ----------------------------
DROP TABLE IF EXISTS `cms_menu`;
CREATE TABLE `cms_menu` (
  `menu_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pid` int(11) DEFAULT NULL COMMENT '父菜单',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `url` varchar(100) DEFAULT NULL COMMENT '链接',
  `target` varchar(10) DEFAULT NULL COMMENT '打开方式',
  `orders` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='菜单';

-- ----------------------------
-- Records of cms_menu
-- ----------------------------
INSERT INTO `cms_menu` VALUES ('1', null, '首页', '/', '_self', '1489847080380');
INSERT INTO `cms_menu` VALUES ('2', null, '问答', '/qa', '_self', '1489847186644');
INSERT INTO `cms_menu` VALUES ('3', null, '博客', '/blog', '_self', '1489847186645');
INSERT INTO `cms_menu` VALUES ('4', null, '资讯', '/news', '_self', '1489847080381');
INSERT INTO `cms_menu` VALUES ('5', null, '专题', '/topic/list', '_self', '1489847186646');
INSERT INTO `cms_menu` VALUES ('6', null, '关于', '/page/about', '_self', '1489847186647');

-- ----------------------------
-- Table structure for cms_page
-- ----------------------------
DROP TABLE IF EXISTS `cms_page`;
CREATE TABLE `cms_page` (
  `page_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编码',
  `pid` int(10) DEFAULT NULL COMMENT '父页面',
  `title` varchar(20) DEFAULT NULL COMMENT '标题',
  `alias` varchar(20) DEFAULT NULL COMMENT '别名',
  `content` mediumtext COMMENT '页面内容',
  `keywords` varchar(100) DEFAULT NULL COMMENT '关键字',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `orders` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`page_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='页面';

-- ----------------------------
-- Records of cms_page
-- ----------------------------
INSERT INTO `cms_page` VALUES ('1', null, '关于', 'about', '作者：张恕征', '单页关键字', '单页描述', '1489839705049', '1489839705049');

-- ----------------------------
-- Table structure for cms_setting
-- ----------------------------
DROP TABLE IF EXISTS `cms_setting`;
CREATE TABLE `cms_setting` (
  `setting_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `setting_key` varchar(10) DEFAULT NULL,
  `setting_value` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`setting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='网站配置';

-- ----------------------------
-- Records of cms_setting
-- ----------------------------
INSERT INTO `cms_setting` VALUES ('1', 'copyright', '© 2017 Zhang Shuzheng');

-- ----------------------------
-- Table structure for cms_system
-- ----------------------------
DROP TABLE IF EXISTS `cms_system`;
CREATE TABLE `cms_system` (
  `system_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) NOT NULL COMMENT '系统名称',
  `code` varchar(20) DEFAULT NULL COMMENT '别名',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `orders` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`system_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='系统管理';

-- ----------------------------
-- Records of cms_system
-- ----------------------------
INSERT INTO `cms_system` VALUES ('1', '资讯', 'news', '资讯', '1', '1');
INSERT INTO `cms_system` VALUES ('2', '问答', 'qa', '问答', '2', '2');
INSERT INTO `cms_system` VALUES ('3', '博客', 'blog', '博客', '3', '3');

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
  `system_id` int(11) DEFAULT NULL COMMENT '所属系统',
  `ctime` bigint(20) unsigned NOT NULL COMMENT '创建时间',
  `orders` bigint(20) unsigned NOT NULL COMMENT '排序',
  PRIMARY KEY (`tag_id`),
  KEY `cms_tag_orders` (`orders`),
  KEY `cms_tag_alias` (`alias`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='标签表';

-- ----------------------------
-- Records of cms_tag
-- ----------------------------
INSERT INTO `cms_tag` VALUES ('1', 'JAVA', 'java标签', '', '1', 'java', '1', '1489585694864', '1489585694864');
INSERT INTO `cms_tag` VALUES ('2', 'Android', 'android标签', '', '1', 'android', '1', '1489585720382', '1489585720382');
INSERT INTO `cms_tag` VALUES ('3', 'zheng', 'zheng标签', '', '2', 'zheng', '1', '1489585815042', '1489585815042');
INSERT INTO `cms_tag` VALUES ('4', '谈恋爱', '谈恋爱标签', '', '1', 'love', '2', '1489585815043', '1489585815043');
INSERT INTO `cms_tag` VALUES ('5', 'java', 'java标签', '', '1', 'java', '3', '1489585815044', '1489585815044');

-- ----------------------------
-- Table structure for cms_topic
-- ----------------------------
DROP TABLE IF EXISTS `cms_topic`;
CREATE TABLE `cms_topic` (
  `topic_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  `url` varchar(100) DEFAULT NULL COMMENT '链接',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='专题';

-- ----------------------------
-- Records of cms_topic
-- ----------------------------
INSERT INTO `cms_topic` VALUES ('1', '日本地震专题', '日本经历灭国性地震，彻底沉入海底', '/topic/1', '1489843484448');

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
-- Table structure for ucenter_oauth
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_oauth`;
CREATE TABLE `ucenter_oauth` (
  `oauth_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) DEFAULT NULL COMMENT '认证方式名称',
  PRIMARY KEY (`oauth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='认证方式表';

-- ----------------------------
-- Records of ucenter_oauth
-- ----------------------------
INSERT INTO `ucenter_oauth` VALUES ('1', '手机');
INSERT INTO `ucenter_oauth` VALUES ('2', '微信');
INSERT INTO `ucenter_oauth` VALUES ('3', 'QQ');
INSERT INTO `ucenter_oauth` VALUES ('4', '微博');

-- ----------------------------
-- Table structure for ucenter_user
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_user`;
CREATE TABLE `ucenter_user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `password` varchar(32) DEFAULT NULL COMMENT '密码(MD5(密码+盐))',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别(0:未知,1:男,2:女)',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `create_ip` varchar(50) DEFAULT NULL COMMENT '注册IP地址',
  `last_login_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后登录时间',
  `last_login_ip` varchar(50) DEFAULT NULL COMMENT '最后登录IP地址',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of ucenter_user
-- ----------------------------

-- ----------------------------
-- Table structure for ucenter_user_details
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_user_details`;
CREATE TABLE `ucenter_user_details` (
  `user_id` int(10) unsigned NOT NULL COMMENT '编号',
  `signature` varchar(300) DEFAULT NULL COMMENT '个性签名',
  `real_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `birthday` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '出生日期',
  `question` varchar(100) DEFAULT NULL COMMENT '帐号安全问题',
  `answer` varchar(100) DEFAULT NULL COMMENT '帐号安全答案',
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FK_Reference_41` FOREIGN KEY (`user_id`) REFERENCES `ucenter_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户详情表';

-- ----------------------------
-- Records of ucenter_user_details
-- ----------------------------

-- ----------------------------
-- Table structure for ucenter_user_log
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_user_log`;
CREATE TABLE `ucenter_user_log` (
  `user_log_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(10) unsigned DEFAULT NULL COMMENT '用户编号',
  `content` varbinary(100) DEFAULT NULL COMMENT '内容',
  `ip` varchar(20) DEFAULT NULL COMMENT '操作IP地址',
  `agent` varbinary(200) DEFAULT NULL COMMENT '操作环境',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`user_log_id`),
  KEY `FK_Reference_44` (`user_id`),
  CONSTRAINT `FK_Reference_44` FOREIGN KEY (`user_id`) REFERENCES `ucenter_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户操作日志表';

-- ----------------------------
-- Records of ucenter_user_log
-- ----------------------------

-- ----------------------------
-- Table structure for ucenter_user_oauth
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_user_oauth`;
CREATE TABLE `ucenter_user_oauth` (
  `user_oauth_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(10) unsigned NOT NULL COMMENT '帐号编号',
  `oauth_id` int(10) unsigned NOT NULL COMMENT '认证方式编号',
  `open_id` varbinary(50) NOT NULL COMMENT '第三方ID',
  `status` tinyint(4) unsigned DEFAULT NULL COMMENT '绑定状态(0:解绑,1:绑定)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_oauth_id`),
  KEY `FK_Reference_42` (`user_id`),
  KEY `FK_Reference_43` (`oauth_id`),
  CONSTRAINT `FK_Reference_42` FOREIGN KEY (`user_id`) REFERENCES `ucenter_user` (`user_id`),
  CONSTRAINT `FK_Reference_43` FOREIGN KEY (`oauth_id`) REFERENCES `ucenter_oauth` (`oauth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户认证方式表';

-- ----------------------------
-- Records of ucenter_user_oauth
-- ----------------------------

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
  `base_path` varchar(500) DEFAULT NULL COMMENT '根路径',
  `uri` varchar(500) DEFAULT NULL COMMENT 'URI',
  `url` varchar(500) DEFAULT NULL COMMENT 'URL',
  `method` varchar(10) DEFAULT NULL COMMENT '请求类型',
  `parameter` mediumtext,
  `user_agent` varchar(500) DEFAULT NULL COMMENT '用户标识',
  `ip` varchar(30) DEFAULT NULL COMMENT 'IP地址',
  `result` mediumtext,
  `permissions` varchar(100) DEFAULT NULL COMMENT '权限值',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=647 DEFAULT CHARSET=utf8mb4 COMMENT='操作日志';

-- ----------------------------
-- Records of upms_log
-- ----------------------------
INSERT INTO `upms_log` VALUES ('638', '修改系统', 'admin', '1496482968582', '46', 'http://upms.zhangshuzheng.cn:1111', '/manage/system/update/6', 'http://upms.zhangshuzheng.cn:1111/manage/system/update/6', 'POST', '{basepath=[6],icon=[1],name=[test],banner=[http://shuzheng.oss-cn-shanghai.aliyuncs.com/20170603/K9SeEgAb4WaMzv2UNtMKaDDEJPdtinPQ.png],description=[5],theme=[#408080],title=[test],status=[1]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36', '127.0.0.1', 'com.zheng.upms.common.constant.UpmsResult@709962ff', 'upms:system:update');
INSERT INTO `upms_log` VALUES ('639', '系统列表', 'admin', '1496482968704', '7', 'http://upms.zhangshuzheng.cn:1111', '/manage/system/list', 'http://upms.zhangshuzheng.cn:1111/manage/system/list', 'GET', 'order=asc&offset=0&limit=10', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36', '127.0.0.1', '{total=7, rows=[UpmsSystem [Hash = 1641885411, systemId=1, icon=zmdi zmdi-shield-security, banner=/resources/zheng-admin/images/zheng-upms.png, theme=#29A176, basepath=http://upms.zhangshuzheng.cn:1111, status=1, name=zheng-upms-server, title=权限管理系统, description=用户权限管理系统（RBAC细粒度用户权限、统一后台、单点登录、会话管理）, ctime=1, orders=1], UpmsSystem [Hash = 2020796001, systemId=2, icon=zmdi zmdi-wikipedia, banner=/resources/zheng-admin/images/zheng-cms.png, theme=#455EC5, basepath=http://cms.zhangshuzheng.cn:2222, status=1, name=zheng-cms-admin, title=内容管理系统, description=内容管理系统（门户、博客、论坛、问答等）, ctime=2, orders=2], UpmsSystem [Hash = 744121144, systemId=3, icon=zmdi zmdi-paypal-alt, banner=/resources/zheng-admin/images/zheng-pay.png, theme=#F06292, basepath=http://pay.zhangshuzheng.cn:3331, status=1, name=zheng-pay-admin, title=支付管理系统, description=支付管理系统, ctime=3, orders=3], UpmsSystem [Hash = -2076287598, systemId=4, icon=zmdi zmdi-account, banner=/resources/zheng-admin/images/zheng-ucenter.png, theme=#6539B4, basepath=http://ucenter.zhangshuzheng.cn:4441, status=1, name=zheng-ucenter-home, title=用户管理系统, description=用户管理系统, ctime=4, orders=4], UpmsSystem [Hash = -1546531115, systemId=5, icon=zmdi zmdi-cloud, banner=/resources/zheng-admin/images/zheng-oss.png, theme=#0B8DE5, basepath=http://oss.zhangshuzheng.cn:7771, status=1, name=zheng-oss-web, title=存储管理系统, description=存储管理系统, ctime=5, orders=5], UpmsSystem [Hash = -22665516, systemId=6, icon=1, banner=http://shuzheng.oss-cn-shanghai.aliyuncs.com/20170603/K9SeEgAb4WaMzv2UNtMKaDDEJPdtinPQ.png, theme=#408080, basepath=6, status=1, name=test, title=test, description=5, ctime=1496463790282, orders=1496463790282], UpmsSystem [Hash = 344597582, systemId=7, icon=, banner=,,,, theme=#ff0000, basepath=, status=1, name=add, title=add, description=, ctime=1496465063224, orders=1496465063224]]}', 'upms:system:read');
INSERT INTO `upms_log` VALUES ('640', '修改系统', 'admin', '1496482971351', '3', 'http://upms.zhangshuzheng.cn:1111', '/manage/system/update/6', 'http://upms.zhangshuzheng.cn:1111/manage/system/update/6', 'GET', null, 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36', '127.0.0.1', '/manage/system/update.jsp', 'upms:system:update');
INSERT INTO `upms_log` VALUES ('641', '修改系统', 'admin', '1496482976830', '3', 'http://upms.zhangshuzheng.cn:1111', '/manage/system/update/7', 'http://upms.zhangshuzheng.cn:1111/manage/system/update/7', 'GET', null, 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36', '127.0.0.1', '/manage/system/update.jsp', 'upms:system:update');
INSERT INTO `upms_log` VALUES ('642', '修改系统', 'admin', '1496482987575', '41', 'http://upms.zhangshuzheng.cn:1111', '/manage/system/update/7', 'http://upms.zhangshuzheng.cn:1111/manage/system/update/7', 'POST', '{basepath=[],icon=[],name=[add],banner=[http://shuzheng.oss-cn-shanghai.aliyuncs.com/20170603/b8VbzvSHqfsvVXrn9L5aG5ARrsD0UMDt.jpg],description=[],theme=[#ff0000],title=[add],status=[1]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36', '127.0.0.1', 'com.zheng.upms.common.constant.UpmsResult@563fe43a', 'upms:system:update');
INSERT INTO `upms_log` VALUES ('643', '系统列表', 'admin', '1496482987695', '6', 'http://upms.zhangshuzheng.cn:1111', '/manage/system/list', 'http://upms.zhangshuzheng.cn:1111/manage/system/list', 'GET', 'order=asc&offset=0&limit=10', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36', '127.0.0.1', '{total=7, rows=[UpmsSystem [Hash = 1641885411, systemId=1, icon=zmdi zmdi-shield-security, banner=/resources/zheng-admin/images/zheng-upms.png, theme=#29A176, basepath=http://upms.zhangshuzheng.cn:1111, status=1, name=zheng-upms-server, title=权限管理系统, description=用户权限管理系统（RBAC细粒度用户权限、统一后台、单点登录、会话管理）, ctime=1, orders=1], UpmsSystem [Hash = 2020796001, systemId=2, icon=zmdi zmdi-wikipedia, banner=/resources/zheng-admin/images/zheng-cms.png, theme=#455EC5, basepath=http://cms.zhangshuzheng.cn:2222, status=1, name=zheng-cms-admin, title=内容管理系统, description=内容管理系统（门户、博客、论坛、问答等）, ctime=2, orders=2], UpmsSystem [Hash = 744121144, systemId=3, icon=zmdi zmdi-paypal-alt, banner=/resources/zheng-admin/images/zheng-pay.png, theme=#F06292, basepath=http://pay.zhangshuzheng.cn:3331, status=1, name=zheng-pay-admin, title=支付管理系统, description=支付管理系统, ctime=3, orders=3], UpmsSystem [Hash = -2076287598, systemId=4, icon=zmdi zmdi-account, banner=/resources/zheng-admin/images/zheng-ucenter.png, theme=#6539B4, basepath=http://ucenter.zhangshuzheng.cn:4441, status=1, name=zheng-ucenter-home, title=用户管理系统, description=用户管理系统, ctime=4, orders=4], UpmsSystem [Hash = -1546531115, systemId=5, icon=zmdi zmdi-cloud, banner=/resources/zheng-admin/images/zheng-oss.png, theme=#0B8DE5, basepath=http://oss.zhangshuzheng.cn:7771, status=1, name=zheng-oss-web, title=存储管理系统, description=存储管理系统, ctime=5, orders=5], UpmsSystem [Hash = -22665516, systemId=6, icon=1, banner=http://shuzheng.oss-cn-shanghai.aliyuncs.com/20170603/K9SeEgAb4WaMzv2UNtMKaDDEJPdtinPQ.png, theme=#408080, basepath=6, status=1, name=test, title=test, description=5, ctime=1496463790282, orders=1496463790282], UpmsSystem [Hash = 992448646, systemId=7, icon=, banner=http://shuzheng.oss-cn-shanghai.aliyuncs.com/20170603/b8VbzvSHqfsvVXrn9L5aG5ARrsD0UMDt.jpg, theme=#ff0000, basepath=, status=1, name=add, title=add, description=, ctime=1496465063224, orders=1496465063224]]}', 'upms:system:read');
INSERT INTO `upms_log` VALUES ('644', '后台首页', 'admin', '1496482990223', '22', 'http://upms.zhangshuzheng.cn:1111', '/manage/index', 'http://upms.zhangshuzheng.cn:1111/manage/index', 'GET', null, 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36', '127.0.0.1', '/manage/index.jsp', null);
INSERT INTO `upms_log` VALUES ('645', '后台首页', 'admin', '1496482993183', '23', 'http://upms.zhangshuzheng.cn:1111', '/manage/index', 'http://upms.zhangshuzheng.cn:1111/manage/index', 'GET', null, 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36', '127.0.0.1', '/manage/index.jsp', null);
INSERT INTO `upms_log` VALUES ('646', '后台首页', 'admin', '1496482995340', '22', 'http://upms.zhangshuzheng.cn:1111', '/manage/index', 'http://upms.zhangshuzheng.cn:1111/manage/index', 'GET', null, 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36', '127.0.0.1', '/manage/index.jsp', null);

-- ----------------------------
-- Table structure for upms_organization
-- ----------------------------
DROP TABLE IF EXISTS `upms_organization`;
CREATE TABLE `upms_organization` (
  `organization_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pid` int(10) DEFAULT NULL COMMENT '所属上级',
  `name` varchar(20) DEFAULT NULL COMMENT '组织名称',
  `description` varchar(1000) DEFAULT NULL COMMENT '组织描述',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='组织';

-- ----------------------------
-- Records of upms_organization
-- ----------------------------
INSERT INTO `upms_organization` VALUES ('1', null, '总部', '北京总部', '1');
INSERT INTO `upms_organization` VALUES ('4', null, '河北分部', '河北石家庄', '1488122466236');
INSERT INTO `upms_organization` VALUES ('5', null, '河南分部', '河南郑州', '1488122480265');
INSERT INTO `upms_organization` VALUES ('6', null, '湖北分部', '湖北武汉', '1488122493265');
INSERT INTO `upms_organization` VALUES ('7', null, '湖南分部', '湖南长沙', '1488122502752');

-- ----------------------------
-- Table structure for upms_permission
-- ----------------------------
DROP TABLE IF EXISTS `upms_permission`;
CREATE TABLE `upms_permission` (
  `permission_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `system_id` int(10) unsigned NOT NULL COMMENT '所属系统',
  `pid` int(10) DEFAULT NULL COMMENT '所属上级',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型(1:目录,2:菜单,3:按钮)',
  `permission_value` varchar(50) DEFAULT NULL COMMENT '权限值',
  `uri` varchar(100) DEFAULT NULL COMMENT '路径',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态(0:禁止,1:正常)',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `orders` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COMMENT='权限';

-- ----------------------------
-- Records of upms_permission
-- ----------------------------
INSERT INTO `upms_permission` VALUES ('1', '1', '0', '系统组织管理', '1', '', '', 'zmdi zmdi-accounts-list', '1', '1', '1');
INSERT INTO `upms_permission` VALUES ('2', '1', '1', '系统管理', '2', 'upms:system:read', '/manage/system/index', '', '1', '2', '2');
INSERT INTO `upms_permission` VALUES ('3', '1', '1', '组织管理', '2', 'upms:organization:read', '/manage/organization/index', '', '1', '3', '3');
INSERT INTO `upms_permission` VALUES ('4', '1', '0', '角色用户管理', '1', '', '', 'zmdi zmdi-accounts', '1', '4', '4');
INSERT INTO `upms_permission` VALUES ('5', '1', '4', '角色管理', '2', 'upms:role:read', '/manage/role/index', '', '1', '6', '6');
INSERT INTO `upms_permission` VALUES ('6', '1', '4', '用户管理', '2', 'upms:user:read', '/manage/user/index', '', '1', '5', '5');
INSERT INTO `upms_permission` VALUES ('7', '1', '0', '权限资源管理', '1', '', '', 'zmdi zmdi-lock-outline', '1', '7', '7');
INSERT INTO `upms_permission` VALUES ('12', '1', '0', '其他数据管理', '1', '', '', 'zmdi zmdi-more', '1', '12', '12');
INSERT INTO `upms_permission` VALUES ('14', '1', '12', '会话管理', '2', 'upms:session:read', '/manage/session/index', '', '1', '14', '14');
INSERT INTO `upms_permission` VALUES ('15', '1', '12', '日志记录', '2', 'upms:log:read', '/manage/log/index', '', '1', '15', '15');
INSERT INTO `upms_permission` VALUES ('17', '2', '0', '标签类目管理', '1', null, null, 'zmdi zmdi-menu', '1', '17', '17');
INSERT INTO `upms_permission` VALUES ('18', '2', '17', '标签管理', '2', 'cms:tag:read', '/manage/tag/index', null, '1', '18', '18');
INSERT INTO `upms_permission` VALUES ('19', '2', '17', '类目管理', '2', 'cms:category:read', '/manage/category/index', null, '1', '19', '19');
INSERT INTO `upms_permission` VALUES ('20', '2', '0', '文章评论管理', '1', null, null, 'zmdi zmdi-collection-text', '1', '20', '20');
INSERT INTO `upms_permission` VALUES ('21', '2', '20', '文章管理', '2', 'cms:article:read', '/manage/article/index', null, '1', '21', '21');
INSERT INTO `upms_permission` VALUES ('22', '2', '20', '回收管理', '2', 'cms:article:read', '/manage/article/recycle', null, '1', '22', '22');
INSERT INTO `upms_permission` VALUES ('24', '1', '2', '新增系统', '3', 'upms:system:create', '/manage/system/create', 'zmdi zmdi-plus', '1', '24', '24');
INSERT INTO `upms_permission` VALUES ('25', '1', '2', '编辑系统', '3', 'upms:system:update', '/manage/system/update', 'zmdi zmdi-edit', '1', '25', '25');
INSERT INTO `upms_permission` VALUES ('26', '1', '2', '删除系统', '3', 'upms:system:delete', '/manage/system/delete', 'zmdi zmdi-close', '1', '26', '26');
INSERT INTO `upms_permission` VALUES ('27', '1', '3', '新增组织', '3', 'upms:organization:create', '/manage/organization/create', 'zmdi zmdi-plus', '1', '27', '27');
INSERT INTO `upms_permission` VALUES ('28', '1', '3', '编辑组织', '3', 'upms:organization:update', '/manage/organization/update', 'zmdi zmdi-edit', '1', '28', '28');
INSERT INTO `upms_permission` VALUES ('29', '1', '3', '删除组织', '3', 'upms:organization:delete', '/manage/organization/delete', 'zmdi zmdi-close', '1', '29', '29');
INSERT INTO `upms_permission` VALUES ('30', '1', '6', '新增用户', '3', 'upms:user:create', '/manage/user/create', 'zmdi zmdi-plus', '1', '30', '30');
INSERT INTO `upms_permission` VALUES ('31', '1', '6', '编辑用户', '3', 'upms:user:update', '/manage/user/update', 'zmdi zmdi-edit', '1', '31', '31');
INSERT INTO `upms_permission` VALUES ('32', '1', '6', '删除用户', '3', 'upms:user:delete', '/manage/user/delete', 'zmdi zmdi-close', '1', '32', '32');
INSERT INTO `upms_permission` VALUES ('33', '1', '5', '新增角色', '3', 'upms:role:create', '/manage/role/create', 'zmdi zmdi-plus', '1', '33', '33');
INSERT INTO `upms_permission` VALUES ('34', '1', '5', '编辑角色', '3', 'upms:role:update', '/manage/role/update', 'zmdi zmdi-edit', '1', '34', '34');
INSERT INTO `upms_permission` VALUES ('35', '1', '5', '删除角色', '3', 'upms:role:delete', '/manage/role/delete', 'zmdi zmdi-close', '1', '35', '35');
INSERT INTO `upms_permission` VALUES ('36', '1', '39', '新增权限', '3', 'upms:permission:create', '/manage/permission/create', 'zmdi zmdi-plus', '1', '36', '36');
INSERT INTO `upms_permission` VALUES ('37', '1', '39', '编辑权限', '3', 'upms:permission:update', '/manage/permission/update', 'zmdi zmdi-edit', '1', '37', '37');
INSERT INTO `upms_permission` VALUES ('38', '1', '39', '删除权限', '3', 'upms:permission:delete', '/manage/permission/delete', 'zmdi zmdi-close', '1', '38', '38');
INSERT INTO `upms_permission` VALUES ('39', '1', '7', '权限管理', '2', 'upms:permission:read', '/manage/permission/index', null, '1', '39', '39');
INSERT INTO `upms_permission` VALUES ('46', '1', '5', '角色权限', '3', 'upms:role:permission', '/manage/role/permission', 'zmdi zmdi-key', '1', '1488091928257', '1488091928257');
INSERT INTO `upms_permission` VALUES ('48', '1', '6', '用户组织', '3', 'upms:user:organization', '/manage/user/organization', 'zmdi zmdi-accounts-list', '1', '1488120011165', '1488120011165');
INSERT INTO `upms_permission` VALUES ('50', '1', '6', '用户角色', '3', 'upms:user:role', '/manage/user/role', 'zmdi zmdi-accounts', '1', '1488120554175', '1488120554175');
INSERT INTO `upms_permission` VALUES ('51', '1', '6', '用户权限', '3', 'upms:user:permission', '/manage/user/permission', 'zmdi zmdi-key', '1', '1488092013302', '1488092013302');
INSERT INTO `upms_permission` VALUES ('53', '1', '14', '强制退出', '3', 'upms:session:forceout', '/manage/session/forceout', 'zmdi zmdi-run', '1', '1488379514715', '1488379514715');
INSERT INTO `upms_permission` VALUES ('54', '2', '18', '新增标签', '3', 'cms:tag:create', '/manage/tag/create', 'zmdi zmdi-plus', '1', '1489417315159', '1489417315159');
INSERT INTO `upms_permission` VALUES ('55', '2', '18', '编辑标签', '3', 'cms:tag:update', 'zmdi zmdi-edit', 'zmdi zmdi-widgets', '1', '1489417344931', '1489417344931');
INSERT INTO `upms_permission` VALUES ('56', '2', '18', '删除标签', '3', 'cms:tag:delete', '/manage/tag/delete', 'zmdi zmdi-close', '1', '1489417372114', '1489417372114');
INSERT INTO `upms_permission` VALUES ('57', '1', '15', '删除权限', '3', 'upms:log:delete', '/manage/log/delete', 'zmdi zmdi-close', '1', '1489503867909', '1489503867909');
INSERT INTO `upms_permission` VALUES ('58', '2', '19', '编辑类目', '3', 'cms:category:update', '/manage/category/update', 'zmdi zmdi-edit', '1', '1489586600462', '1489586600462');
INSERT INTO `upms_permission` VALUES ('59', '2', '19', '删除类目', '3', 'cms:category:delete', '/manage/category/delete', 'zmdi zmdi-close', '1', '1489586633059', '1489586633059');
INSERT INTO `upms_permission` VALUES ('60', '2', '19', '新增类目', '3', 'cms:category:create', '/manage/category/create', 'zmdi zmdi-plus', '1', '1489590342089', '1489590342089');
INSERT INTO `upms_permission` VALUES ('61', '2', '0', '其他数据管理', '1', '', '', 'zmdi zmdi-more', '1', '1489835455359', '1489835455359');
INSERT INTO `upms_permission` VALUES ('62', '2', '20', '评论管理', '2', 'cms:comment:read', '/manage/comment/index', '', '1', '1489591408224', '1489591408224');
INSERT INTO `upms_permission` VALUES ('63', '2', '62', '删除评论', '3', 'cms:comment:delete', '/manage/comment/delete', 'zmdi zmdi-close', '1', '1489591449614', '1489591449614');
INSERT INTO `upms_permission` VALUES ('64', '2', '79', '单页管理', '2', 'cms:page:read', '/manage/page/index', '', '1', '1489591332779', '1489591332779');
INSERT INTO `upms_permission` VALUES ('65', '2', '64', '新增单页', '3', 'cms:page:create', '/manage/page/create', 'zmdi zmdi-plus', '1', '1489591614473', '1489591614473');
INSERT INTO `upms_permission` VALUES ('66', '2', '64', '编辑单页', '3', 'cms:page:update', '/manage/page/update', 'zmdi zmdi-edit', '1', '1489591653000', '1489591653000');
INSERT INTO `upms_permission` VALUES ('67', '2', '64', '删除单页', '3', 'cms:page:delete', '/manage/page/delete', 'zmdi zmdi-close', '1', '1489591683552', '1489591683552');
INSERT INTO `upms_permission` VALUES ('68', '2', '61', '菜单管理', '2', 'cms:menu:read', '/manage/menu/index', 'zmdi zmdi-widgets', '1', '1489591746846', '1489591746846');
INSERT INTO `upms_permission` VALUES ('69', '2', '68', '新增菜单', '3', 'cms:menu:create', '/manage/menu/create', 'zmdi zmdi-plus', '1', '1489591791747', '1489591791747');
INSERT INTO `upms_permission` VALUES ('70', '2', '68', '编辑菜单', '3', 'cms:menu:update', '/manage/menu/update', 'zmdi zmdi-edit', '1', '1489591831878', '1489591831878');
INSERT INTO `upms_permission` VALUES ('71', '2', '68', '删除菜单', '3', 'cms:menu:delete', '/manage/menu/delete', 'zmdi zmdi-close', '1', '1489591865454', '1489591865454');
INSERT INTO `upms_permission` VALUES ('72', '2', '61', '系统设置', '2', 'cms:setting:read', '/manage/setting/index', 'zmdi zmdi-widgets', '1', '1489591981165', '1489591981165');
INSERT INTO `upms_permission` VALUES ('73', '2', '72', '新增设置', '3', 'cms:setting:create', '/manage/setting/create', 'zmdi zmdi-plus', '1', '1489592024762', '1489592024762');
INSERT INTO `upms_permission` VALUES ('74', '2', '72', '编辑设置', '3', 'cms:setting:update', '/manage/setting/update', 'zmdi zmdi-edit', '1', '1489592052582', '1489592052582');
INSERT INTO `upms_permission` VALUES ('75', '2', '72', '删除设置', '3', 'cms:setting:delete', '/manage/setting/delete', 'zmdi zmdi-close', '1', '1489592081426', '1489592081426');
INSERT INTO `upms_permission` VALUES ('76', '2', '21', '新增文章', '3', 'cms:article:create', '/manage/article/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('77', '2', '21', '编辑文章', '3', 'cms:article:update', '/manage/article/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('78', '2', '21', '删除文章', '3', 'cms:article:delete', '/manage/article/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');
INSERT INTO `upms_permission` VALUES ('79', '2', '0', '单页专题管理', '1', '', '', 'zmdi zmdi-view-web', '1', '1489835320327', '1489835320327');
INSERT INTO `upms_permission` VALUES ('80', '2', '79', '专题管理', '2', 'cms:topic:read', '/manage/topic/index', 'zmdi zmdi-widgets', '1', '1489591507566', '1489591507566');
INSERT INTO `upms_permission` VALUES ('81', '2', '80', '新增专题', '3', 'cms:topic:create', '/manage/topic/create', 'zmdi zmdi-plus', '1', '1489843327028', '1489843327028');
INSERT INTO `upms_permission` VALUES ('82', '2', '80', '编辑专题', '3', 'cms:topic:update', '/manage/topic/update', 'zmdi zmdi-edit', '1', '1489843351513', '1489843351513');
INSERT INTO `upms_permission` VALUES ('83', '2', '80', '删除专题', '3', 'cms:topic:delete', '/manage/topic/delete', 'zmdi zmdi-close', '1', '1489843379953', '1489843379953');
INSERT INTO `upms_permission` VALUES ('84', '2', '68', '上移菜单', '3', 'cms:menu:up', '/manage/menu/up', 'zmdi zmdi-long-arrow-up', '1', '1489846486548', '1489846486548');
INSERT INTO `upms_permission` VALUES ('85', '2', '68', '下移菜单', '3', 'cms:menu:down', '/manage/menu/down', 'zmdi zmdi-long-arrow-down', '1', '1489846578051', '1489846578051');

-- ----------------------------
-- Table structure for upms_role
-- ----------------------------
DROP TABLE IF EXISTS `upms_role`;
CREATE TABLE `upms_role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `title` varchar(20) DEFAULT NULL COMMENT '角色标题',
  `description` varchar(1000) DEFAULT NULL COMMENT '角色描述',
  `ctime` bigint(20) NOT NULL COMMENT '创建时间',
  `orders` bigint(20) NOT NULL COMMENT '排序',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Records of upms_role
-- ----------------------------
INSERT INTO `upms_role` VALUES ('1', 'super', '超级管理员', '拥有所有权限', '1', '1');
INSERT INTO `upms_role` VALUES ('2', 'admin', '管理员', '拥有除权限管理系统外的所有权限', '1487471013117', '1487471013117');

-- ----------------------------
-- Table structure for upms_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `upms_role_permission`;
CREATE TABLE `upms_role_permission` (
  `role_permission_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` int(10) unsigned NOT NULL COMMENT '角色编号',
  `permission_id` int(10) unsigned NOT NULL COMMENT '权限编号',
  PRIMARY KEY (`role_permission_id`),
  KEY `FK_Reference_23` (`role_id`),
  CONSTRAINT `FK_Reference_23` FOREIGN KEY (`role_id`) REFERENCES `upms_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

-- ----------------------------
-- Records of upms_role_permission
-- ----------------------------
INSERT INTO `upms_role_permission` VALUES ('1', '1', '1');
INSERT INTO `upms_role_permission` VALUES ('2', '1', '2');
INSERT INTO `upms_role_permission` VALUES ('3', '1', '3');
INSERT INTO `upms_role_permission` VALUES ('4', '1', '4');
INSERT INTO `upms_role_permission` VALUES ('5', '1', '5');
INSERT INTO `upms_role_permission` VALUES ('6', '1', '6');
INSERT INTO `upms_role_permission` VALUES ('7', '1', '7');
INSERT INTO `upms_role_permission` VALUES ('8', '1', '39');
INSERT INTO `upms_role_permission` VALUES ('12', '1', '12');
INSERT INTO `upms_role_permission` VALUES ('14', '1', '14');
INSERT INTO `upms_role_permission` VALUES ('15', '1', '15');
INSERT INTO `upms_role_permission` VALUES ('17', '1', '17');
INSERT INTO `upms_role_permission` VALUES ('19', '1', '19');
INSERT INTO `upms_role_permission` VALUES ('20', '1', '20');
INSERT INTO `upms_role_permission` VALUES ('21', '1', '21');
INSERT INTO `upms_role_permission` VALUES ('24', '1', '24');
INSERT INTO `upms_role_permission` VALUES ('27', '1', '27');
INSERT INTO `upms_role_permission` VALUES ('28', '1', '28');
INSERT INTO `upms_role_permission` VALUES ('29', '1', '29');
INSERT INTO `upms_role_permission` VALUES ('30', '1', '30');
INSERT INTO `upms_role_permission` VALUES ('31', '1', '31');
INSERT INTO `upms_role_permission` VALUES ('32', '1', '32');
INSERT INTO `upms_role_permission` VALUES ('33', '1', '33');
INSERT INTO `upms_role_permission` VALUES ('34', '1', '34');
INSERT INTO `upms_role_permission` VALUES ('35', '1', '35');
INSERT INTO `upms_role_permission` VALUES ('36', '1', '36');
INSERT INTO `upms_role_permission` VALUES ('37', '1', '37');
INSERT INTO `upms_role_permission` VALUES ('38', '1', '38');
INSERT INTO `upms_role_permission` VALUES ('39', '1', '46');
INSERT INTO `upms_role_permission` VALUES ('40', '1', '51');
INSERT INTO `upms_role_permission` VALUES ('44', '1', '48');
INSERT INTO `upms_role_permission` VALUES ('45', '1', '50');
INSERT INTO `upms_role_permission` VALUES ('47', '1', '53');
INSERT INTO `upms_role_permission` VALUES ('48', '1', '18');
INSERT INTO `upms_role_permission` VALUES ('49', '1', '54');
INSERT INTO `upms_role_permission` VALUES ('50', '1', '54');
INSERT INTO `upms_role_permission` VALUES ('51', '1', '55');
INSERT INTO `upms_role_permission` VALUES ('52', '1', '54');
INSERT INTO `upms_role_permission` VALUES ('53', '1', '55');
INSERT INTO `upms_role_permission` VALUES ('54', '1', '56');
INSERT INTO `upms_role_permission` VALUES ('55', '1', '57');
INSERT INTO `upms_role_permission` VALUES ('56', '1', '58');
INSERT INTO `upms_role_permission` VALUES ('57', '1', '58');
INSERT INTO `upms_role_permission` VALUES ('58', '1', '59');
INSERT INTO `upms_role_permission` VALUES ('59', '1', '60');
INSERT INTO `upms_role_permission` VALUES ('60', '1', '61');
INSERT INTO `upms_role_permission` VALUES ('61', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('62', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('63', '1', '63');
INSERT INTO `upms_role_permission` VALUES ('64', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('65', '1', '63');
INSERT INTO `upms_role_permission` VALUES ('66', '1', '64');
INSERT INTO `upms_role_permission` VALUES ('67', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('68', '1', '63');
INSERT INTO `upms_role_permission` VALUES ('69', '1', '64');
INSERT INTO `upms_role_permission` VALUES ('70', '1', '65');
INSERT INTO `upms_role_permission` VALUES ('71', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('72', '1', '63');
INSERT INTO `upms_role_permission` VALUES ('73', '1', '64');
INSERT INTO `upms_role_permission` VALUES ('74', '1', '65');
INSERT INTO `upms_role_permission` VALUES ('75', '1', '66');
INSERT INTO `upms_role_permission` VALUES ('76', '1', '62');
INSERT INTO `upms_role_permission` VALUES ('77', '1', '63');
INSERT INTO `upms_role_permission` VALUES ('78', '1', '64');
INSERT INTO `upms_role_permission` VALUES ('79', '1', '65');
INSERT INTO `upms_role_permission` VALUES ('80', '1', '66');
INSERT INTO `upms_role_permission` VALUES ('81', '1', '67');
INSERT INTO `upms_role_permission` VALUES ('82', '1', '68');
INSERT INTO `upms_role_permission` VALUES ('83', '1', '69');
INSERT INTO `upms_role_permission` VALUES ('84', '1', '69');
INSERT INTO `upms_role_permission` VALUES ('85', '1', '70');
INSERT INTO `upms_role_permission` VALUES ('86', '1', '69');
INSERT INTO `upms_role_permission` VALUES ('87', '1', '70');
INSERT INTO `upms_role_permission` VALUES ('88', '1', '71');
INSERT INTO `upms_role_permission` VALUES ('89', '1', '72');
INSERT INTO `upms_role_permission` VALUES ('90', '1', '72');
INSERT INTO `upms_role_permission` VALUES ('91', '1', '73');
INSERT INTO `upms_role_permission` VALUES ('92', '1', '72');
INSERT INTO `upms_role_permission` VALUES ('93', '1', '73');
INSERT INTO `upms_role_permission` VALUES ('94', '1', '74');
INSERT INTO `upms_role_permission` VALUES ('95', '1', '72');
INSERT INTO `upms_role_permission` VALUES ('96', '1', '73');
INSERT INTO `upms_role_permission` VALUES ('97', '1', '74');
INSERT INTO `upms_role_permission` VALUES ('98', '1', '75');
INSERT INTO `upms_role_permission` VALUES ('99', '1', '76');
INSERT INTO `upms_role_permission` VALUES ('100', '1', '76');
INSERT INTO `upms_role_permission` VALUES ('101', '1', '77');
INSERT INTO `upms_role_permission` VALUES ('102', '1', '76');
INSERT INTO `upms_role_permission` VALUES ('103', '1', '77');
INSERT INTO `upms_role_permission` VALUES ('105', '1', '79');
INSERT INTO `upms_role_permission` VALUES ('106', '1', '80');
INSERT INTO `upms_role_permission` VALUES ('107', '1', '81');
INSERT INTO `upms_role_permission` VALUES ('108', '1', '81');
INSERT INTO `upms_role_permission` VALUES ('109', '1', '82');
INSERT INTO `upms_role_permission` VALUES ('110', '1', '81');
INSERT INTO `upms_role_permission` VALUES ('111', '1', '82');
INSERT INTO `upms_role_permission` VALUES ('112', '1', '83');
INSERT INTO `upms_role_permission` VALUES ('113', '1', '84');
INSERT INTO `upms_role_permission` VALUES ('114', '1', '84');
INSERT INTO `upms_role_permission` VALUES ('115', '1', '85');
INSERT INTO `upms_role_permission` VALUES ('121', '1', '78');
INSERT INTO `upms_role_permission` VALUES ('122', '1', '78');
INSERT INTO `upms_role_permission` VALUES ('124', '1', '25');
INSERT INTO `upms_role_permission` VALUES ('125', '1', '26');

-- ----------------------------
-- Table structure for upms_system
-- ----------------------------
DROP TABLE IF EXISTS `upms_system`;
CREATE TABLE `upms_system` (
  `system_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `banner` varchar(150) DEFAULT NULL COMMENT '背景',
  `theme` varchar(50) DEFAULT NULL COMMENT '主题',
  `basepath` varchar(100) DEFAULT NULL COMMENT '根目录',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态(-1:黑名单,1:正常)',
  `name` varchar(20) DEFAULT NULL COMMENT '系统名称',
  `title` varchar(20) DEFAULT NULL COMMENT '系统标题',
  `description` varchar(300) DEFAULT NULL COMMENT '系统描述',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `orders` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`system_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='系统';

-- ----------------------------
-- Records of upms_system
-- ----------------------------
INSERT INTO `upms_system` VALUES ('1', 'zmdi zmdi-shield-security', '/resources/zheng-admin/images/zheng-upms.png', '#29A176', 'http://upms.zhangshuzheng.cn:1111', '1', 'zheng-upms-server', '权限管理系统', '用户权限管理系统（RBAC细粒度用户权限、统一后台、单点登录、会话管理）', '1', '1');
INSERT INTO `upms_system` VALUES ('2', 'zmdi zmdi-wikipedia', '/resources/zheng-admin/images/zheng-cms.png', '#455EC5', 'http://cms.zhangshuzheng.cn:2222', '1', 'zheng-cms-admin', '内容管理系统', '内容管理系统（门户、博客、论坛、问答等）', '2', '2');
INSERT INTO `upms_system` VALUES ('3', 'zmdi zmdi-paypal-alt', '/resources/zheng-admin/images/zheng-pay.png', '#F06292', 'http://pay.zhangshuzheng.cn:3331', '1', 'zheng-pay-admin', '支付管理系统', '支付管理系统', '3', '3');
INSERT INTO `upms_system` VALUES ('4', 'zmdi zmdi-account', '/resources/zheng-admin/images/zheng-ucenter.png', '#6539B4', 'http://ucenter.zhangshuzheng.cn:4441', '1', 'zheng-ucenter-home', '用户管理系统', '用户管理系统', '4', '4');
INSERT INTO `upms_system` VALUES ('5', 'zmdi zmdi-cloud', '/resources/zheng-admin/images/zheng-oss.png', '#0B8DE5', 'http://oss.zhangshuzheng.cn:7771', '1', 'zheng-oss-web', '存储管理系统', '存储管理系统', '5', '5');

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
  `avatar` varchar(150) DEFAULT NULL COMMENT '头像',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别',
  `locked` tinyint(4) DEFAULT NULL COMMENT '状态(0:正常,1:锁定)',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of upms_user
-- ----------------------------
INSERT INTO `upms_user` VALUES ('1', 'admin', '3038D9CB63B3152A79B8153FB06C02F7', '66f1b370c660445a8657bf8bf1794486', '张恕征', '/resources/zheng-admin/images/avatar.jpg', '', '469741414@qq.com', '1', '0', '1');
INSERT INTO `upms_user` VALUES ('2', 'test', '285C9762F5F9046F5893F752DFAF3476', 'd2d0d03310444ad388a8b290b0fe8564', '张恕征', '/resources/zheng-admin/images/avatar.jpg', '', '469741414@qq.com', '1', '0', '1493394720495');

-- ----------------------------
-- Table structure for upms_user_organization
-- ----------------------------
DROP TABLE IF EXISTS `upms_user_organization`;
CREATE TABLE `upms_user_organization` (
  `user_organization_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户编号',
  `organization_id` int(10) unsigned NOT NULL COMMENT '组织编号',
  PRIMARY KEY (`user_organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='用户组织关联表';

-- ----------------------------
-- Records of upms_user_organization
-- ----------------------------
INSERT INTO `upms_user_organization` VALUES ('19', '1', '1');
INSERT INTO `upms_user_organization` VALUES ('20', '1', '4');
INSERT INTO `upms_user_organization` VALUES ('21', '1', '5');
INSERT INTO `upms_user_organization` VALUES ('22', '1', '6');
INSERT INTO `upms_user_organization` VALUES ('23', '1', '7');

-- ----------------------------
-- Table structure for upms_user_permission
-- ----------------------------
DROP TABLE IF EXISTS `upms_user_permission`;
CREATE TABLE `upms_user_permission` (
  `user_permission_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户编号',
  `permission_id` int(10) unsigned NOT NULL COMMENT '权限编号',
  `type` tinyint(4) NOT NULL COMMENT '权限类型(-1:减权限,1:增权限)',
  PRIMARY KEY (`user_permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COMMENT='用户权限关联表';

-- ----------------------------
-- Records of upms_user_permission
-- ----------------------------
INSERT INTO `upms_user_permission` VALUES ('3', '1', '22', '-1');
INSERT INTO `upms_user_permission` VALUES ('4', '1', '22', '1');
INSERT INTO `upms_user_permission` VALUES ('5', '2', '24', '-1');
INSERT INTO `upms_user_permission` VALUES ('6', '2', '26', '-1');
INSERT INTO `upms_user_permission` VALUES ('7', '2', '27', '-1');
INSERT INTO `upms_user_permission` VALUES ('8', '2', '29', '-1');
INSERT INTO `upms_user_permission` VALUES ('9', '2', '32', '-1');
INSERT INTO `upms_user_permission` VALUES ('10', '2', '51', '-1');
INSERT INTO `upms_user_permission` VALUES ('11', '2', '48', '-1');
INSERT INTO `upms_user_permission` VALUES ('12', '2', '50', '-1');
INSERT INTO `upms_user_permission` VALUES ('13', '2', '35', '-1');
INSERT INTO `upms_user_permission` VALUES ('14', '2', '46', '-1');
INSERT INTO `upms_user_permission` VALUES ('15', '2', '37', '-1');
INSERT INTO `upms_user_permission` VALUES ('16', '2', '38', '-1');
INSERT INTO `upms_user_permission` VALUES ('17', '2', '57', '-1');
INSERT INTO `upms_user_permission` VALUES ('18', '2', '56', '-1');
INSERT INTO `upms_user_permission` VALUES ('19', '2', '59', '-1');
INSERT INTO `upms_user_permission` VALUES ('20', '2', '78', '-1');
INSERT INTO `upms_user_permission` VALUES ('21', '2', '67', '-1');
INSERT INTO `upms_user_permission` VALUES ('22', '2', '83', '-1');
INSERT INTO `upms_user_permission` VALUES ('23', '2', '71', '-1');
INSERT INTO `upms_user_permission` VALUES ('24', '2', '75', '-1');

-- ----------------------------
-- Table structure for upms_user_role
-- ----------------------------
DROP TABLE IF EXISTS `upms_user_role`;
CREATE TABLE `upms_user_role` (
  `user_role_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户编号',
  `role_id` int(10) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of upms_user_role
-- ----------------------------
INSERT INTO `upms_user_role` VALUES ('4', '1', '1');
INSERT INTO `upms_user_role` VALUES ('5', '1', '2');
INSERT INTO `upms_user_role` VALUES ('6', '2', '1');
INSERT INTO `upms_user_role` VALUES ('7', '2', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '20', 'zhangsan');
INSERT INTO `user` VALUES ('2', '21', 'lisi');
