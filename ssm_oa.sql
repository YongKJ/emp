/*
 Navicat Premium Data Transfer

 Source Server         : 我的阿里云
 Source Server Type    : MySQL
 Source Server Version : 100038
 Source Host           : 47.106.102.217:3306
 Source Schema         : ssm_oa

 Target Server Type    : MySQL
 Target Server Version : 100038
 File Encoding         : 65001

 Date: 07/02/2021 13:39:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class`  (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `major_id` int(6) NULL DEFAULT NULL,
  `class_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class_date` date NULL DEFAULT NULL,
  `class_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class_address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class_delete` int(5) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_major_id_01`(`major_id`) USING BTREE,
  CONSTRAINT `FK_major_id_01` FOREIGN KEY (`major_id`) REFERENCES `t_major` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_class
-- ----------------------------
INSERT INTO `t_class` VALUES (1, 1, '物联网01班', '2019-08-08', '50', '19楼1教室', 0);
INSERT INTO `t_class` VALUES (2, 2, '商务外语1班', '2019-08-07', '60', '19楼2教室', 0);

-- ----------------------------
-- Table structure for t_depart
-- ----------------------------
DROP TABLE IF EXISTS `t_depart`;
CREATE TABLE `t_depart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createtime` date NULL DEFAULT NULL,
  `del` int(14) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_depart
-- ----------------------------
INSERT INTO `t_depart` VALUES (1, '教学部', '2018-06-27', 0);
INSERT INTO `t_depart` VALUES (2, '就业部', '2018-06-27', 0);
INSERT INTO `t_depart` VALUES (3, '后勤部', '2020-12-02', 0);

-- ----------------------------
-- Table structure for t_emp
-- ----------------------------
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pass` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `did` int(11) NULL DEFAULT NULL COMMENT '外键  部门ID',
  `flag` int(11) NULL DEFAULT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createdate` date NULL DEFAULT NULL,
  `photo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `del` int(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_DID`(`did`) USING BTREE,
  CONSTRAINT `FK_DID` FOREIGN KEY (`did`) REFERENCES `t_depart` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_emp
-- ----------------------------
INSERT INTO `t_emp` VALUES (1, 'qf000001', '1', '张三', 1, 1, '男', 'zhansgan@163.com', '222321', '110', '2018-03-03', '53281624f0b043aba2a6db6fcfe4b726-57rD2oDZquc.jpg', 0);
INSERT INTO `t_emp` VALUES (2, 'admin', '111', 'admin', 1, 1, '男', 'admin@qq.com', '222321', '110', '2018-03-03', 'f32965a9605e4276873ae2f7dae74fb4-78619704_p0.jpg', 0);
INSERT INTO `t_emp` VALUES (3, 'dxj', '123456', 'dxj', 3, 1, '男', 'dxj@qq.com', '1314520', '1314520', '2020-12-05', 'd0b41885a9ec46e7accdb0fe2c5316df4354.jpg', 0);
INSERT INTO `t_emp` VALUES (4, 'D Lady', '123456', 'D Lady', 3, 1, '男', '1314520@qq.com', '1314520', '1314520', '2020-12-16', '89cc05209fa844c9b228ece66b4123724354.jpg', 0);
INSERT INTO `t_emp` VALUES (5, 'guest', 'guest', '宾客', 3, 1, '男', 'guest@qq.com', '5201314', '1314520', '2020-12-21', '05e1c0400457497dbe35a8c0b7135325-20_21.jpg', NULL);

-- ----------------------------
-- Table structure for t_item
-- ----------------------------
DROP TABLE IF EXISTS `t_item`;
CREATE TABLE `t_item`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` int(11) NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uint` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_item
-- ----------------------------
INSERT INTO `t_item` VALUES (1, 1, '老王', '隔壁的', '一个');
INSERT INTO `t_item` VALUES (2, 1, '老王', '隔壁的', '一个');
INSERT INTO `t_item` VALUES (3, 1, '老王', '隔壁的', '一个');
INSERT INTO `t_item` VALUES (4, 1, '老王', '隔壁的', '一个');
INSERT INTO `t_item` VALUES (5, 123, '测试1', '隔壁的', '一个');
INSERT INTO `t_item` VALUES (6, 2342, '小白', '隔壁的', '一个');
INSERT INTO `t_item` VALUES (7, 11, '111', '隔壁的', '一个');
INSERT INTO `t_item` VALUES (8, 11, '111', '隔壁的', '一个');
INSERT INTO `t_item` VALUES (9, 11, '111', '隔壁的', '一个');

-- ----------------------------
-- Table structure for t_loginlog
-- ----------------------------
DROP TABLE IF EXISTS `t_loginlog`;
CREATE TABLE `t_loginlog`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createtime` datetime(0) NULL DEFAULT NULL,
  `location` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 126 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_loginlog
-- ----------------------------
INSERT INTO `t_loginlog` VALUES (1, '117.159.15.221', 'admin', '2018-07-12 14:04:49', '河南省信阳市');
INSERT INTO `t_loginlog` VALUES (2, '117.159.15.221', 'admin', '2018-07-12 16:02:08', '河南省信阳市');
INSERT INTO `t_loginlog` VALUES (3, '117.159.15.221', 'admin', '2018-07-12 16:05:00', '河南省信阳市');
INSERT INTO `t_loginlog` VALUES (4, '117.159.15.221', 'admin', '2018-07-12 16:13:30', '河南省信阳市');
INSERT INTO `t_loginlog` VALUES (5, '117.159.15.221', 'admin', '2018-07-12 16:14:36', '河南省信阳市');
INSERT INTO `t_loginlog` VALUES (6, '117.159.15.221', 'admin', '2018-07-12 16:23:53', '河南省信阳市');
INSERT INTO `t_loginlog` VALUES (7, '117.159.15.221', 'admin', '2018-07-12 16:25:51', '河南省信阳市');
INSERT INTO `t_loginlog` VALUES (8, '117.159.15.221', 'admin', '2018-07-12 16:27:00', '河南省信阳市');
INSERT INTO `t_loginlog` VALUES (9, '117.159.15.221', 'admin', '2018-07-12 16:53:44', '河南省信阳市');
INSERT INTO `t_loginlog` VALUES (10, '117.159.15.221', 'admin', '2018-07-12 17:01:38', '河南省信阳市');
INSERT INTO `t_loginlog` VALUES (11, '117.159.15.221', 'admin', '2018-07-12 17:04:31', '河南省信阳市');
INSERT INTO `t_loginlog` VALUES (55, '117.159.15.221', 'admin', '2018-07-14 16:03:21', '河南省信阳市');
INSERT INTO `t_loginlog` VALUES (56, '219.129.252.60', 'admin', '2020-12-03 10:53:37', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (57, '219.129.252.60', 'admin', '2020-12-03 10:55:32', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (58, '219.129.252.60', 'admin', '2020-12-03 10:55:50', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (59, '219.129.252.60', 'admin', '2020-12-03 10:56:55', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (60, '219.129.252.60', 'admin', '2020-12-03 10:59:43', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (61, '219.129.252.60', 'admin', '2020-12-03 11:02:16', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (62, '219.129.252.60', 'admin', '2020-12-03 11:04:14', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (63, '219.129.252.60', 'admin', '2020-12-03 11:08:43', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (64, '219.129.252.60', 'admin', '2020-12-03 11:09:38', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (65, '219.129.252.60', 'admin', '2020-12-03 11:12:10', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (66, '219.129.252.60', 'admin', '2020-12-03 11:12:43', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (67, '219.129.252.60', 'admin', '2020-12-03 11:14:27', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (68, '219.129.252.60', 'admin', '2020-12-03 11:16:25', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (69, '219.129.252.60', 'admin', '2020-12-03 11:17:47', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (70, '219.129.252.60', 'admin', '2020-12-03 11:18:39', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (71, '219.129.252.60', 'admin', '2020-12-03 11:19:49', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (72, '219.129.252.60', 'admin', '2020-12-03 11:20:56', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (73, '219.129.252.60', 'admin', '2020-12-03 11:23:37', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (74, '219.129.252.60', 'admin', '2020-12-03 11:30:33', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (75, '219.129.252.60', 'admin', '2020-12-03 11:32:10', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (76, '219.129.252.60', 'admin', '2020-12-03 14:55:38', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (77, '219.129.252.60', 'admin', '2020-12-03 15:02:41', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (78, '219.129.252.60', 'admin', '2020-12-03 15:35:33', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (79, '219.129.252.60', 'admin', '2020-12-03 16:55:17', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (80, '219.129.252.60', 'admin', '2020-12-14 10:00:02', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (81, '219.129.252.60', 'admin', '2020-12-14 10:02:36', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (82, '219.129.252.60', 'admin', '2020-12-14 10:03:26', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (83, '219.129.252.60', 'admin', '2020-12-14 10:06:19', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (84, '219.129.252.60', 'admin', '2020-12-14 10:08:10', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (85, '219.129.252.60', 'admin', '2020-12-14 10:08:43', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (86, '219.129.252.60', 'admin', '2020-12-14 10:09:50', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (87, '219.129.252.60', 'admin', '2020-12-14 10:10:11', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (88, '219.129.252.60', 'admin', '2020-12-14 10:12:52', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (89, '120.197.196.41', 'admin', '2020-12-21 01:24:21', '广东省,深圳市');
INSERT INTO `t_loginlog` VALUES (90, '120.197.196.41', 'admin', '2020-12-21 01:36:41', '广东省,深圳市');
INSERT INTO `t_loginlog` VALUES (91, '120.197.196.41', 'guest', '2020-12-21 01:39:07', '广东省,深圳市');
INSERT INTO `t_loginlog` VALUES (92, '120.197.196.41', 'guest', '2020-12-21 08:32:32', '广东省,深圳市');
INSERT INTO `t_loginlog` VALUES (93, '183.53.17.219', 'admin', '2020-12-21 09:36:49', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (94, '183.53.17.219', 'guest', '2020-12-21 09:37:11', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (95, '219.129.252.60', 'guest', '2020-12-21 09:55:44', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (96, '219.129.252.60', 'guest', '2020-12-21 09:59:25', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (97, '219.129.252.60', 'guest', '2020-12-21 10:41:42', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (98, '219.129.252.60', 'guest', '2020-12-21 11:01:31', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (99, '113.75.163.174', 'guest', '2020-12-21 12:26:43', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (100, '120.197.197.38', 'guest', '2020-12-21 12:58:28', '广东省,东莞市');
INSERT INTO `t_loginlog` VALUES (101, '120.197.197.38', 'guest', '2020-12-21 13:00:10', '广东省,东莞市');
INSERT INTO `t_loginlog` VALUES (102, '113.75.163.159', 'guest', '2020-12-21 13:28:55', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (103, '219.129.252.60', 'guest', '2020-12-21 16:19:46', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (104, '219.129.252.60', 'guest', '2020-12-21 16:26:52', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (105, '120.197.198.79', 'guest', '2020-12-21 16:31:57', '');
INSERT INTO `t_loginlog` VALUES (106, '120.197.198.79', 'admin', '2020-12-21 17:32:32', '');
INSERT INTO `t_loginlog` VALUES (107, '120.197.198.79', 'admin', '2020-12-21 17:44:40', '');
INSERT INTO `t_loginlog` VALUES (108, '120.197.196.41', 'guest', '2020-12-21 22:21:24', '广东省,深圳市');
INSERT INTO `t_loginlog` VALUES (109, '120.197.196.41', 'guest', '2020-12-22 08:55:20', '广东省,深圳市');
INSERT INTO `t_loginlog` VALUES (110, '120.197.198.19', 'guest', '2020-12-22 10:09:46', '');
INSERT INTO `t_loginlog` VALUES (111, '223.104.1.96', 'guest', '2020-12-22 15:05:06', '');
INSERT INTO `t_loginlog` VALUES (112, '120.197.198.58', 'guest', '2020-12-23 08:22:06', '广东省,广州市');
INSERT INTO `t_loginlog` VALUES (113, '120.197.198.39', 'guest', '2020-12-23 10:10:20', '');
INSERT INTO `t_loginlog` VALUES (114, '120.197.196.79', 'guest', '2020-12-23 17:09:49', '');
INSERT INTO `t_loginlog` VALUES (115, '120.197.196.77', 'guest', '2020-12-25 09:49:59', '广东省,深圳市');
INSERT INTO `t_loginlog` VALUES (116, '113.75.163.226', 'guest', '2020-12-25 22:25:25', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (117, '120.197.196.87', 'guest', '2020-12-26 12:30:17', '广东省,深圳市');
INSERT INTO `t_loginlog` VALUES (118, '120.238.217.35', 'guest', '2020-12-26 18:19:36', '广东省,佛山市');
INSERT INTO `t_loginlog` VALUES (119, '120.197.196.87', 'guest', '2020-12-26 18:23:20', '广东省,深圳市');
INSERT INTO `t_loginlog` VALUES (120, '223.104.66.141', 'guest', '2020-12-26 19:29:23', '广东省,');
INSERT INTO `t_loginlog` VALUES (121, '78.142.192.16', 'guest', '2020-12-28 08:25:25', '');
INSERT INTO `t_loginlog` VALUES (122, '120.197.198.74', 'guest', '2020-12-30 13:44:31', '广东省,广州市');
INSERT INTO `t_loginlog` VALUES (123, '183.53.18.147', 'guest', '2021-01-01 23:16:20', '广东省,韶关市');
INSERT INTO `t_loginlog` VALUES (124, '78.142.192.107', 'guest', '2021-01-22 15:14:50', '');
INSERT INTO `t_loginlog` VALUES (125, '78.142.192.107', 'guest', '2021-01-22 15:15:30', '');

-- ----------------------------
-- Table structure for t_major
-- ----------------------------
DROP TABLE IF EXISTS `t_major`;
CREATE TABLE `t_major`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `major_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `major_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `major_date` date NULL DEFAULT NULL,
  `major_type` int(6) NULL DEFAULT NULL,
  `major_delete` int(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_major_id`(`major_type`) USING BTREE,
  CONSTRAINT `FK_major_id` FOREIGN KEY (`major_type`) REFERENCES `t_majortype` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_major
-- ----------------------------
INSERT INTO `t_major` VALUES (1, '物联网', '40', '2019-08-02', 1, 0);
INSERT INTO `t_major` VALUES (2, '商务外语', '200', '2019-08-08', 2, 0);

-- ----------------------------
-- Table structure for t_majortype
-- ----------------------------
DROP TABLE IF EXISTS `t_majortype`;
CREATE TABLE `t_majortype`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `majortype` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_majortype
-- ----------------------------
INSERT INTO `t_majortype` VALUES (1, '精品');
INSERT INTO `t_majortype` VALUES (2, '业余');
INSERT INTO `t_majortype` VALUES (3, '普通');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `cardno` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `school` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `education` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class_id` int(11) NULL DEFAULT NULL COMMENT '外键 和班级主键有关系',
  `flag` int(11) NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createdate` date NULL DEFAULT NULL,
  `photo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `del` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_student_class_id`(`class_id`) USING BTREE,
  CONSTRAINT `FK_student_class_id` FOREIGN KEY (`class_id`) REFERENCES `t_class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES (1, 'qf000002', '小王', '男', '1998-03-20', '321721199803203212', '郑州大学', '本科', 1, 1, 'zhansgan@163.com', '222321', '110', '2018-03-03', 'photos\\e49c64f2-0df8-464c-93ad-7ab95fb7867e_cw1.jpg', NULL);
INSERT INTO `t_student` VALUES (2, 'qf000003', '小君', '女', '1998-06-10', '321721199803203213', '超神学院', '博士', 2, 0, 'xiaojun@163.com', '1718874198', '15218480260', '2012-12-12', 'photos\\e49c64f2-0df8-464c-93ad-7ab95fb7867e_cw1.jpg', NULL);

SET FOREIGN_KEY_CHECKS = 1;
