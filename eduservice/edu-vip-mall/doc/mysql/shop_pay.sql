/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.10.131-MySQL
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : 192.168.10.131:3306
 Source Schema         : shop_pay

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 26/07/2022 23:12:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pay_log
-- ----------------------------
DROP TABLE IF EXISTS `pay_log`;
CREATE TABLE `pay_log`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` int NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pay_id` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '支付日志表';

-- ----------------------------
-- Records of pay_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
