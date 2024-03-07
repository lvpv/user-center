/*
 Navicat Premium Data Transfer

 Source Server         : Tencent
 Source Server Type    : MySQL
 Source Server Version : 50743 (5.7.43)
 Source Host           : 192.144.238.26:3306
 Source Schema         : user-center

 Target Server Type    : MySQL
 Target Server Version : 50743 (5.7.43)
 File Encoding         : 65001

 Date: 08/03/2024 00:44:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名(账号)',
  `password` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `nickname` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像地址',
  `phone` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `gender` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '性别(0:未知,1:男,2:女)',
  `role` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '角色(0:普通用户,1:管理员)',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态(0:正常,1:禁用)',
  `deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标志(0:正常,1:删除)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_id` bigint(20) unsigned DEFAULT NULL COMMENT '创建人编号',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_id` bigint(20) unsigned DEFAULT NULL COMMENT '更新人编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_username` (`username`) USING BTREE COMMENT '用户名唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统用户表';

-- ----------------------------
-- Records of system_user
-- ----------------------------
BEGIN;
INSERT INTO `system_user` (`id`, `username`, `password`, `nickname`, `avatar`, `phone`, `email`, `gender`, `role`, `status`, `deleted`, `create_time`, `create_id`, `update_time`, `update_id`) VALUES (1, 'lvpb', '06dd4f9b122de91c2a6a999e2d0bca13', 'lvpb', 'https://foruda.gitee.com/avatar/1676995015311506473/1912137_lvpb_1578962256.png', '18999231234', 'lv941226@gmail.com', 0, 0, 0, 0, '2024-03-07 15:17:23', 1, '2024-03-07 15:49:05', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
