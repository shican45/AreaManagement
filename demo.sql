/*
 Navicat Premium Dump SQL

 Source Server         : demo
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40)
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40)
 File Encoding         : 65001

 Date: 08/09/2025 14:28:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `city_id` int NOT NULL,
  `area_id` int NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifed_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id` DESC) USING BTREE,
  INDEX `city_id`(`city_id` ASC) USING BTREE,
  CONSTRAINT `city_id` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (54, '厦门市和顺县青城镇杏林巷62号', 19, 3, '2025-09-08 14:19:58', NULL);
INSERT INTO `address` VALUES (53, '泉州市宝塔街东风路17号', 18, 3, '2025-09-08 14:19:58', NULL);
INSERT INTO `address` VALUES (52, '泉州市合水镇龙凤路48号', 18, 3, '2025-09-08 14:19:58', NULL);
INSERT INTO `address` VALUES (6, '杭州-杭州市上城区万松岭路81号', 4, 2, '2025-09-04 20:51:07', '2025-09-07 07:34:03');
INSERT INTO `address` VALUES (5, '上海-上海市浦东新区锦绣路1001号', 3, 2, '2025-09-04 20:50:14', '2025-09-07 10:26:00');
INSERT INTO `address` VALUES (4, '广州-广州市越秀区东湖路123号', 2, 1, '2025-09-04 20:49:12', '2025-09-07 07:33:58');
INSERT INTO `address` VALUES (3, '深圳-深圳市桥天区北环大道逢业大厦南塔七层703', 1, 1, '2025-09-04 20:42:45', '2025-09-07 07:34:00');
INSERT INTO `address` VALUES (2, '深圳-深圳市桥天区北环大道逢业大厦南塔六层602', 1, 1, '2025-09-04 20:42:22', '2025-09-07 07:34:01');
INSERT INTO `address` VALUES (1, '深圳-深圳市桥天区北环大道逢业大厦南塔五层501', 1, 1, '2025-09-04 20:42:03', '2025-09-07 07:34:02');

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area`  (
  `id` int NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifed_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES (1, '华南区域', '2025-09-04 20:16:01', '2025-09-04 20:51:42');
INSERT INTO `area` VALUES (2, '华东区域', '2025-09-04 20:17:30', '2025-09-04 20:51:51');
INSERT INTO `area` VALUES (3, '华西区域', '2025-09-08 13:20:02', '2025-09-08 14:19:58');

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `area_id` int NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifed_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES (1, '深圳', 1, '2025-09-04 20:19:42', '2025-09-08 05:22:42');
INSERT INTO `city` VALUES (2, '广州', 1, '2025-09-04 20:35:29', '2025-09-07 07:54:24');
INSERT INTO `city` VALUES (3, '上海', 2, '2025-09-04 20:36:15', '2025-09-07 07:54:25');
INSERT INTO `city` VALUES (4, '杭州', 2, '2025-09-04 20:43:52', '2025-09-08 10:09:58');
INSERT INTO `city` VALUES (18, '泉州', 3, '2025-09-08 13:15:49', '2025-09-08 14:19:58');
INSERT INTO `city` VALUES (19, '厦门', 3, '2025-09-08 13:16:13', '2025-09-08 14:19:58');
INSERT INTO `city` VALUES (20, '福州', NULL, '2025-09-08 14:03:47', '2025-09-08 14:19:58');

SET FOREIGN_KEY_CHECKS = 1;
