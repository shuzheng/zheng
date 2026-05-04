-- =============================================================
-- 秒杀服务数据库表结构
-- 数据库：zheng
-- 描述：用于秒杀商品管理、秒杀订单等功能
-- =============================================================

-- -------------------------------------------------------------
-- 秒杀商品表
-- -------------------------------------------------------------
DROP TABLE IF EXISTS `seckill_goods`;
CREATE TABLE `seckill_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `goods_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品ID',
  `goods_name` varchar(200) NOT NULL DEFAULT '' COMMENT '商品名称',
  `goods_image` varchar(500) DEFAULT '' COMMENT '商品图片',
  `original_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品原价',
  `seckill_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '秒杀价格',
  `stock_count` int(11) NOT NULL DEFAULT '0' COMMENT '库存数量',
  `start_time` datetime NOT NULL COMMENT '秒杀开始时间',
  `end_time` datetime NOT NULL COMMENT '秒杀结束时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态：0-下架，1-上架',
  `description` varchar(1000) DEFAULT '' COMMENT '秒杀描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_goods_id` (`goods_id`),
  KEY `idx_status` (`status`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_status_time` (`status`, `start_time`, `end_time`),
  KEY `idx_stock_count` (`stock_count`),
  KEY `idx_status_stock` (`status`, `stock_count`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀商品表';

-- -------------------------------------------------------------
-- 秒杀订单表
-- -------------------------------------------------------------
DROP TABLE IF EXISTS `seckill_order`;
CREATE TABLE `seckill_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_no` varchar(64) NOT NULL DEFAULT '' COMMENT '订单号',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `goods_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品ID',
  `seckill_goods_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '秒杀商品ID',
  `goods_name` varchar(200) NOT NULL DEFAULT '' COMMENT '商品名称',
  `goods_image` varchar(500) DEFAULT '' COMMENT '商品图片',
  `seckill_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '秒杀价格',
  `quantity` int(11) NOT NULL DEFAULT '1' COMMENT '购买数量',
  `total_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
  `pay_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '支付方式：1-支付宝，2-微信，3-其他',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_order_no` varchar(128) DEFAULT '' COMMENT '支付平台订单号（支付宝/微信交易号）',
  `pay_in_order_id` int(11) DEFAULT NULL COMMENT '支付中心订单ID',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单状态：0-待支付，1-已支付，2-已取消，3-已退款',
  `address_id` bigint(20) DEFAULT NULL COMMENT '收货地址ID',
  `receiver_name` varchar(50) DEFAULT '' COMMENT '收货人姓名',
  `receiver_phone` varchar(20) DEFAULT '' COMMENT '收货人电话',
  `receiver_address` varchar(500) DEFAULT '' COMMENT '收货人地址',
  `remark` varchar(500) DEFAULT '' COMMENT '订单备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_goods_id` (`goods_id`),
  KEY `idx_user_goods` (`user_id`, `goods_id`),
  KEY `idx_status` (`status`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_pay_in_order_id` (`pay_in_order_id`),
  KEY `idx_pay_order_no` (`pay_order_no`),
  KEY `idx_status_create_time` (`status`, `create_time`),
  KEY `idx_user_status` (`user_id`, `status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀订单表';

-- -------------------------------------------------------------
-- 秒杀消息记录表（用于消息重试和幂等性）
-- -------------------------------------------------------------
DROP TABLE IF EXISTS `seckill_message`;
CREATE TABLE `seckill_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `message_id` varchar(64) NOT NULL DEFAULT '' COMMENT '消息ID',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `goods_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品ID',
  `message_content` text COMMENT '消息内容（JSON格式）',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '消息状态：0-待处理，1-处理中，2-处理成功，3-处理失败',
  `retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '重试次数',
  `error_msg` varchar(500) DEFAULT '' COMMENT '错误信息',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_message_id` (`message_id`),
  KEY `idx_user_goods` (`user_id`, `goods_id`),
  KEY `idx_status` (`status`),
  KEY `idx_status_retry` (`status`, `retry_count`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀消息记录表';

-- -------------------------------------------------------------
-- 插入测试数据
-- -------------------------------------------------------------

INSERT INTO `seckill_goods` (`goods_id`, `goods_name`, `goods_image`, `original_price`, `seckill_price`, `stock_count`, `start_time`, `end_time`, `status`, `description`)
VALUES
(1, '测试秒杀商品1', 'https://example.com/image1.jpg', 100.00, 10.00, 100, '2026-01-01 00:00:00', '2026-12-31 23:59:59', 1, '测试秒杀商品1，限量100件'),
(2, '测试秒杀商品2', 'https://example.com/image2.jpg', 200.00, 20.00, 200, '2026-01-01 00:00:00', '2026-12-31 23:59:59', 1, '测试秒杀商品2，限量200件'),
(3, '测试秒杀商品3', 'https://example.com/image3.jpg', 300.00, 30.00, 50, '2026-05-01 10:00:00', '2026-05-01 12:00:00', 1, '限时秒杀商品，只有2小时');

-- =============================================================
-- 数据库表结构说明
-- =============================================================
-- 
-- 1. seckill_goods 表索引说明：
--    - uk_goods_id: 唯一索引，用于根据商品ID快速查询
--    - idx_status: 普通索引，用于查询上架/下架商品
--    - idx_start_time / idx_end_time: 时间索引，用于秒杀时间范围查询
--    - idx_status_time: 联合索引，用于查询指定状态和时间范围内的秒杀商品
--    - idx_stock_count: 库存索引，用于库存相关查询
--    - idx_status_stock: 联合索引，用于查询指定状态且库存充足的商品
-- 
-- 2. seckill_order 表索引说明：
--    - uk_order_no: 唯一索引，订单号唯一
--    - idx_user_id: 用户ID索引，查询用户订单
--    - idx_goods_id: 商品ID索引，查询商品订单
--    - idx_user_goods: 联合索引，快速查询用户是否秒杀过某商品（防重复秒杀）
--    - idx_status: 状态索引，查询待支付/已支付/已取消订单
--    - idx_create_time: 创建时间索引，用于时间范围查询和超时订单检查
--    - idx_pay_in_order_id: 支付中心订单ID索引，支付回调时快速查找订单
--    - idx_pay_order_no: 支付平台订单号索引，用于对账
--    - idx_status_create_time: 联合索引，用于查询指定状态且在指定时间内的订单（超时订单检查）
--    - idx_user_status: 联合索引，查询用户指定状态的订单
-- 
-- 3. seckill_message 表索引说明：
--    - uk_message_id: 唯一索引，消息ID唯一（幂等性保障）
--    - idx_user_goods: 联合索引，查询用户商品相关消息
--    - idx_status: 状态索引，查询待处理/处理中/成功/失败消息
--    - idx_status_retry: 联合索引，查询指定状态且重试次数未超限的消息
--    - idx_create_time: 创建时间索引，用于消息清理和时间范围查询
-- 
-- =============================================================
