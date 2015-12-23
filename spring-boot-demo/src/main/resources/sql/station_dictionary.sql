/*
Navicat MySQL Data Transfer

Source Server         : kaifa
Source Server Version : 50626
Source Host           : 198.216.10.201:3306
Source Database       : asset

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-11-13 15:38:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for station_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `station_dictionary`;
CREATE TABLE `station_dictionary` (
  `station_code` varchar(10) DEFAULT NULL COMMENT '站码',
  `station_name` varchar(20) DEFAULT NULL COMMENT '站名',
  `station_shortcode` varchar(3) DEFAULT NULL COMMENT '车站简拼',
  `station_telecode` varchar(3) NOT NULL COMMENT '车站内码',
  `statistics_code` varchar(5) DEFAULT NULL COMMENT '统计码',
  `subbureau_code` varchar(2) DEFAULT NULL COMMENT '分局码',
  `bureau_code` varchar(1) DEFAULT NULL COMMENT '局码',
  `cwd_code` varchar(5) DEFAULT NULL COMMENT '车务段码',
  `station_shortname` varchar(2) DEFAULT NULL COMMENT '站名简称',
  `station_class` varchar(1) DEFAULT NULL COMMENT '车站等级',
  `station_state` varchar(1) DEFAULT NULL COMMENT '车站状态',
  `start_date` varchar(8) NOT NULL COMMENT '起始日期',
  `stop_date` varchar(8) NOT NULL COMMENT '截止日期',
  `station_id` varchar(3) DEFAULT NULL COMMENT '实际电报码',
  `city_code` varchar(4) DEFAULT NULL COMMENT '所在城市代码',
  `station_pycode` varchar(20) DEFAULT NULL COMMENT '车站拼音码',
  `province_code` varchar(2) DEFAULT NULL COMMENT '所属省份代码',
  `district_code` varchar(1) DEFAULT NULL COMMENT '所属区域代码',
  PRIMARY KEY (`station_telecode`,`start_date`,`stop_date`),
  UNIQUE KEY `UK_cmx81ats0li6auudlipyn3u3k` (`station_code`) USING BTREE,
  UNIQUE KEY `UK_3ct2xc4lo71dw7s0om5b0dg3d` (`station_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of station_dictionary
-- ----------------------------
INSERT INTO `station_dictionary` VALUES ('66100', '上海虹桥  ', 'SHQ', 'AOH', null, '5l', 'H', null, '上', '8', ' ', '19990101', '20991231', 'AOH', '0712', 'Shanghaihongqiao    ', '33', ' ');
INSERT INTO `station_dictionary` VALUES ('66833', '蚌埠南    ', 'BBN', 'BMH', null, '5l', 'H', null, '蚌', '5', ' ', '19990101', '20991231', 'BMH', '0807', 'Bengbunan           ', '09', ' ');
INSERT INTO `station_dictionary` VALUES ('66806', '沧州西    ', 'CZX', 'CBP', null, '2d', 'P', null, '沧', '5', ' ', '19990101', '20991231', 'CBP', '0334', 'Cangzhouxi          ', '01', ' ');
INSERT INTO `station_dictionary` VALUES ('66839', '滁州      ', 'CZH', 'CXH', null, '5l', 'H', null, '滁', '5', ' ', '19990101', '20991231', 'CXH', '0808', 'Chuzhou             ', '09', ' ');
INSERT INTO `station_dictionary` VALUES ('66809', '德州东    ', 'DZD', 'DIP', null, '2d', 'P', null, '德', '5', ' ', '19990101', '20991231', 'DIP', '0601', 'Dezhoudong          ', '12', ' ');
INSERT INTO `station_dictionary` VALUES ('66848', '常州北    ', 'CZB', 'ESH', null, '5l', 'H', null, '常', '5', ' ', '19990101', '20991231', 'ESH', '0708', 'Changzhoubei        ', '07', ' ');
INSERT INTO `station_dictionary` VALUES ('66836', '定远      ', 'DYU', 'EWH', null, '5l', 'H', null, '定', '5', ' ', '19990101', '20991231', 'EWH', '0808', 'Dingyuan            ', '09', ' ');
INSERT INTO `station_dictionary` VALUES ('66845', '丹阳北    ', 'DYB', 'EXH', null, '5l', 'H', null, '丹', '5', ' ', '19990101', '20991231', 'EXH', '0707', 'Danyangbei          ', '07', ' ');
INSERT INTO `station_dictionary` VALUES ('66812', '济南西    ', 'JNX', 'JGK', null, '4p', 'K', null, '济', '7', ' ', '19990101', '20991231', 'JGK', '0602', 'Jinanxi             ', '12', ' ');
INSERT INTO `station_dictionary` VALUES ('66334', '昆山南    ', 'KSN', 'KNH', null, '5k', 'H', null, '昆', '6', ' ', '19990101', '20991231', 'KNH', '0711', 'Kunshannan          ', '07', ' ');
INSERT INTO `station_dictionary` VALUES ('66800', '廊坊      ', 'LFA', 'LJP', null, '2d', 'P', null, '廊', '5', ' ', '19990101', '20991231', 'LJP', '0358', 'Langfang            ', '01', ' ');
INSERT INTO `station_dictionary` VALUES ('16804', '南京南    ', 'NJN', 'NKH', null, '5l', 'H', null, '南', '7', ' ', '19990101', '20991231', 'NKH', '0705', 'Nanjingnan          ', '07', ' ');
INSERT INTO `station_dictionary` VALUES ('66854', '苏州北    ', 'SZB', 'OHH', null, '5l', 'H', null, '苏', '5', ' ', '19990101', '20991231', 'OHH', '0710', 'Suzhoubei           ', '07', ' ');
INSERT INTO `station_dictionary` VALUES ('66818', '曲阜东    ', 'QFD', 'QAK', null, '4p', 'K', null, '曲', '5', ' ', '19990101', '20991231', 'QAK', '0626', 'Qufudong            ', '12', ' ');
INSERT INTO `station_dictionary` VALUES ('66830', '宿州东    ', 'SZD', 'SRH', null, '5l', 'H', null, '宿', '5', ' ', '19990101', '20991231', 'SRH', '0806', 'Suzhoudong          ', '09', ' ');
INSERT INTO `station_dictionary` VALUES ('66824', '滕州东    ', 'TZD', 'TEK', null, '4p', 'K', null, '滕', '5', ' ', '19990101', '20991231', 'TBK', '0625', 'Tengzhoudong        ', '12', ' ');
INSERT INTO `station_dictionary` VALUES ('66803', '天津南    ', 'TJN', 'TIP', null, '2d', 'P', null, '天', '8', ' ', '19990101', '20991231', 'TIP', '0359', 'Tianjinnan          ', '32', ' ');
INSERT INTO `station_dictionary` VALUES ('66815', '泰安      ', 'TAN', 'TMK', null, '4p', 'K', null, '泰', '5', ' ', '19990101', '20991231', 'TAK', '0616', 'Tai\'an              ', '12', ' ');
INSERT INTO `station_dictionary` VALUES ('66827', '徐州东    ', 'XZD', 'UUH', null, '5l', 'H', null, '徐', '5', ' ', '19990101', '20991231', 'UUH', '0701', 'Xuzhoudong          ', '07', ' ');
INSERT INTO `station_dictionary` VALUES ('10010', '北京南    ', 'BJN', 'VNP', null, '24', 'P', null, '京', '9', '1', '19990101', '20991231', 'VNP', '0357', 'Beijingnan          ', '31', '1');
INSERT INTO `station_dictionary` VALUES ('66851', '无锡东    ', 'WXD', 'WGH', null, '5l', 'H', null, '无', '5', ' ', '19990101', '20991231', 'WGH', '0709', 'Wuxidong            ', '07', ' ');
INSERT INTO `station_dictionary` VALUES ('66842', '镇江南    ', 'ZJN', 'ZEH', null, '5l', 'H', null, '镇', '5', ' ', '19990101', '20991231', 'ZEH', '0706', 'Zhenjiangnan        ', '07', ' ');
INSERT INTO `station_dictionary` VALUES ('66821', '枣庄      ', 'ZZH', 'ZEK', null, '4p', 'K', null, '枣', '6', ' ', '19990101', '20991231', 'ZEK', '0628', 'Zaozhuang           ', '12', ' ');
