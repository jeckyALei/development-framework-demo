/*
Navicat MySQL Data Transfer

Source Server         : kaifa
Source Server Version : 50626
Source Host           : 198.216.10.201:3306
Source Database       : asset

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-11-13 15:37:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bureau_dictioanry
-- ----------------------------
DROP TABLE IF EXISTS `bureau_dictioanry`;
CREATE TABLE `bureau_dictioanry` (
  `bureau_code` varchar(1) NOT NULL COMMENT '局码',
  `bureau_name` varchar(8) DEFAULT NULL COMMENT '局名',
  `bureau_short_name` varchar(2) DEFAULT NULL COMMENT '局名简称',
  `start_date` varchar(8) DEFAULT NULL COMMENT '起始日期',
  `stop_date` varchar(8) DEFAULT NULL COMMENT '停止日期',
  PRIMARY KEY (`bureau_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bureau_dictioanry
-- ----------------------------
INSERT INTO `bureau_dictioanry` VALUES ('B', '哈尔滨', '哈', '19990101', '20991231');
INSERT INTO `bureau_dictioanry` VALUES ('C', '呼和浩特', '呼', '19990101', '20991231');
INSERT INTO `bureau_dictioanry` VALUES ('F', '郑州', '郑', '19990101', '20991231');
INSERT INTO `bureau_dictioanry` VALUES ('G', '南昌', '昌', '19990101', '20991231');
INSERT INTO `bureau_dictioanry` VALUES ('H', '上海', '上', '19990101', '20991231');
INSERT INTO `bureau_dictioanry` VALUES ('J', '兰州', '兰', '19990101', '20991231');
INSERT INTO `bureau_dictioanry` VALUES ('K', '济南', '济', '19990101', '20991231');
INSERT INTO `bureau_dictioanry` VALUES ('M', '昆明', '昆', '19990101', '20991231');
INSERT INTO `bureau_dictioanry` VALUES ('N', '武汉', '武', '20050325', '20991231');
INSERT INTO `bureau_dictioanry` VALUES ('O', '青藏公司', '青', '19990101', '20991231');
INSERT INTO `bureau_dictioanry` VALUES ('P', '北京', '京', '19990101', '20991231');
INSERT INTO `bureau_dictioanry` VALUES ('Q', '广州', '广', '19990101', '20991231');
INSERT INTO `bureau_dictioanry` VALUES ('R', '乌鲁木齐', '乌', '19990101', '20991231');
INSERT INTO `bureau_dictioanry` VALUES ('T', '沈阳', '沈', '19990101', '20991231');
INSERT INTO `bureau_dictioanry` VALUES ('V', '太原', '太', '20050325', '20991231');
INSERT INTO `bureau_dictioanry` VALUES ('W', '成都', '成', '19990101', '20991231');
INSERT INTO `bureau_dictioanry` VALUES ('Y', '西安', '西', '20050325', '20991231');
INSERT INTO `bureau_dictioanry` VALUES ('Z', '南宁', '宁', '19990101', '20991231');
