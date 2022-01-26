/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : tuopan

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 10/01/2022 14:35:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'EMail',
  `is_valid` tinyint(1) DEFAULT 1 COMMENT '是否有效',
  `last_login_ip` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '上次登录IP',
  `last_login_time` datetime(0) DEFAULT NULL COMMENT '上次登录时间',
  `nickname` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `user_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `role` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限',
  `mtime` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `ctime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`) USING BTREE,
  UNIQUE INDEX `idx_user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'www.vv@qq.com', 1, '', '1970-01-01 08:00:00', '超级管理员', 'e10adc3949ba59abbe56e057f20f883e', '123456789', 'admin', '[\"1\"]', '2021-11-26 15:32:35', NULL);

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '车牌号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '司机',
  `contact` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联系方式',
  `created_at` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `deleted_at` datetime(0) DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '车辆' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `enname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '英文名',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系人',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `roles` json COMMENT '角色',
  `money` decimal(11, 2) DEFAULT 0.00 COMMENT '余额',
  `supername` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '超管名',
  `superpwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '超管密码',
  `status` tinyint(4) DEFAULT 1 COMMENT '状态',
  `created_at` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `deleted_at` datetime(0) DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('C00001', 'hxyx', '华新阳新工厂', '', '', '[\"FACTORY\"]', 0.00, 'hxyx', 'e10adc3949ba59abbe56e057f20f883e', 1, '2021-11-26 15:04:29', NULL, NULL);

-- ----------------------------
-- Table structure for client_user
-- ----------------------------
DROP TABLE IF EXISTS `client_user`;
CREATE TABLE `client_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `client_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '所属商户',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '昵称',
  `mobile` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机',
  `status` tinyint(4) DEFAULT 1 COMMENT '状态',
  `role` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '角色',
  `ctime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_client_user`(`client_id`, `username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for client_user_node
-- ----------------------------
DROP TABLE IF EXISTS `client_user_node`;
CREATE TABLE `client_user_node`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '名称',
  `group` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '分组',
  `controller` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '控制器',
  `action` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '方法',
  `param` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '参数',
  `ctime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_controller_action`(`controller`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '节点' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for client_user_role
-- ----------------------------
DROP TABLE IF EXISTS `client_user_role`;
CREATE TABLE `client_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `client_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商户',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '名称',
  `node` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '节点',
  `ctime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `fee_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '费用类型',
  `fee_money` decimal(11, 2) DEFAULT NULL COMMENT '金额',
  `pay_client_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付方',
  `collect_client_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收款方',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `created_at` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `deleted_at` datetime(0) DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资金流水' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pallet
-- ----------------------------
DROP TABLE IF EXISTS `pallet`;
CREATE TABLE `pallet`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '编号',
  `client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '提供者',
  `cur_client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '当前所在地',
  `status` tinyint(4) DEFAULT 0 COMMENT '状态',
  `created_at` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `deleted_at` datetime(0) DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '托盘' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pallet
-- ----------------------------
INSERT INTO `pallet` VALUES ('1464139170082590720', '123', 'C00001', 'C00001', 0, '2021-11-26 15:49:15', '2021-11-26 15:51:15', NULL);
INSERT INTO `pallet` VALUES ('1466771266173276160', '123', 'C00001', 'C00001', 0, '2021-12-03 22:08:15', '2021-11-26 15:51:15', NULL);
INSERT INTO `pallet` VALUES ('1466771330971078656', '123', 'C00001', 'C00001', 0, '2021-12-03 22:08:31', '2021-11-26 15:51:15', NULL);
INSERT INTO `pallet` VALUES ('1466771334557208576', '123', 'C00001', 'C00001', 0, '2021-12-03 22:08:32', '2021-11-26 15:51:15', NULL);
INSERT INTO `pallet` VALUES ('1466771336947961856', '123', 'C00001', 'C00001', 0, '2021-12-03 22:08:32', '2021-11-26 15:51:15', NULL);
INSERT INTO `pallet` VALUES ('1466773778850779136', '123', 'C00001', 'C00001', 0, '2021-12-03 22:18:14', '2021-11-26 15:51:15', NULL);
INSERT INTO `pallet` VALUES ('1466773781497384960', '123', 'C00001', 'C00001', 0, '2021-12-03 22:18:15', '2021-11-26 15:51:15', NULL);
INSERT INTO `pallet` VALUES ('1466773783661645824', '123', 'C00001', 'C00001', 0, '2021-12-03 22:18:16', '2021-11-26 15:51:15', NULL);
INSERT INTO `pallet` VALUES ('1466793067473408000', '1', 'C00001', 'C00001', 1, '2021-12-03 23:34:53', '2021-12-03 23:35:01', NULL);

-- ----------------------------
-- Table structure for pallet_log
-- ----------------------------
DROP TABLE IF EXISTS `pallet_log`;
CREATE TABLE `pallet_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pallet_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '托盘',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户',
  `from_client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '起点',
  `to_client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '终点',
  `trans_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '流转类型',
  `car_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '车牌号',
  `longitude` double(12, 7) DEFAULT NULL COMMENT '经度',
  `latitude` double(12, 7) DEFAULT NULL COMMENT '维度',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '地址',
  `created_at` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `deleted_at` datetime(0) DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '托盘流转日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pay_rule
-- ----------------------------
DROP TABLE IF EXISTS `pay_rule`;
CREATE TABLE `pay_rule`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pallet_client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '托盘方',
  `fee_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '费用类型',
  `from_client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '起点',
  `to_client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '终点',
  `pay_client_id` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '支付方',
  `pay_money` decimal(11, 2) DEFAULT NULL COMMENT '支付金额',
  `created_at` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `deleted_at` datetime(0) DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '支付规则' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
