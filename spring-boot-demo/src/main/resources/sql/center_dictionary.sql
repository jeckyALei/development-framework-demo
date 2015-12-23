/*
Navicat MySQL Data Transfer

Source Server         : bureau
Source Server Version : 50528
Source Host           : 198.216.10.202:3306
Source Database       : bureau

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-11-26 17:50:42
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `center_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `center_dictionary`;
CREATE TABLE `center_dictionary` (
  `code` varchar(3) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of center_dictionary
-- ----------------------------
INSERT INTO center_dictionary VALUES ('1', '总公司');
