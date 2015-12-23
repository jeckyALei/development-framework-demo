/*
Navicat MySQL Data Transfer

Source Server         : kaifa
Source Server Version : 50626
Source Host           : 198.216.10.201:3306
Source Database       : asset

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-11-13 15:38:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for b_dept_dict_tmp
-- ----------------------------
DROP TABLE IF EXISTS `b_dept_dict_tmp`;
CREATE TABLE `b_dept_dict_tmp` (
  `S_DEPTCODE` varchar(10) NOT NULL COMMENT '编码',
  `S_DEPTNAME` varchar(100) DEFAULT NULL COMMENT '名称',
  `S_DEPTABBR` varchar(30) DEFAULT NULL COMMENT '简称',
  `C_DEPTTYPECODE` varchar(2) DEFAULT NULL COMMENT '类型，03：段；04：运用所；06：检修车间（基地）',
  `S_FATHERDEPTCODE` varchar(10) DEFAULT NULL COMMENT '上级单位',
  `C_ABLEFLAG` varchar(1) DEFAULT NULL COMMENT '可用标识，0：可用；1：不可用''',
  `C_BUREACODE` varchar(1) DEFAULT NULL COMMENT '所属路局',
  PRIMARY KEY (`S_DEPTCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_dept_dict_tmp
-- ----------------------------
INSERT INTO `b_dept_dict_tmp` VALUES ('001', '上海南动车组运用所', '上海南所', '04', '00595', '0', 'H');
INSERT INTO `b_dept_dict_tmp` VALUES ('002', '沈阳动车组运用所', '沈阳所', '04', '00509', '0', 'T');
INSERT INTO `b_dept_dict_tmp` VALUES ('003', '北京动车组运用所', '北京所', '04', '00555', '0', 'P');
INSERT INTO `b_dept_dict_tmp` VALUES ('004', '北京西动车组运用所', '北京西所', '04', '00555', '0', 'P');
INSERT INTO `b_dept_dict_tmp` VALUES ('005', '青岛动车组运用所', '青岛所', '04', '00562', '0', 'K');
INSERT INTO `b_dept_dict_tmp` VALUES ('00506', '长春车辆段', '长辆段', '03', null, '0', 'T');
INSERT INTO `b_dept_dict_tmp` VALUES ('00508', '沈阳车辆段', '沈辆段', '03', null, '0', 'T');
INSERT INTO `b_dept_dict_tmp` VALUES ('00509', '沈阳动车段', '沈动段', '03', null, '0', 'T');
INSERT INTO `b_dept_dict_tmp` VALUES ('00523', '哈尔滨动车段', '哈动段', '03', null, '0', 'B');
INSERT INTO `b_dept_dict_tmp` VALUES ('00524', '齐齐哈尔车辆段', '齐辆段', '03', null, '0', 'B');
INSERT INTO `b_dept_dict_tmp` VALUES ('00554', '北京客车段', '京客段', '03', null, '0', 'P');
INSERT INTO `b_dept_dict_tmp` VALUES ('00555', '北京动车段', '京动段', '03', null, '0', 'P');
INSERT INTO `b_dept_dict_tmp` VALUES ('00556', '天津客车车辆段', '天津段', '03', null, '0', 'P');
INSERT INTO `b_dept_dict_tmp` VALUES ('00561', '济南车辆段', '济南段', '03', null, '0', 'K');
INSERT INTO `b_dept_dict_tmp` VALUES ('00562', '青岛动车段', '青动段', '03', null, '0', 'K');
INSERT INTO `b_dept_dict_tmp` VALUES ('00571', '郑州动车段', '郑动段', '03', null, '0', 'F');
INSERT INTO `b_dept_dict_tmp` VALUES ('00573', '西安动车段', '西动段', '03', null, '0', 'Y');
INSERT INTO `b_dept_dict_tmp` VALUES ('00575', '武昌客车车辆段', '武客段', '03', null, '0', 'N');
INSERT INTO `b_dept_dict_tmp` VALUES ('00576', '武汉动车段', '武动段', '03', null, '0', 'N');
INSERT INTO `b_dept_dict_tmp` VALUES ('00581', '太原车辆段', '原段', '03', null, '0', 'V');
INSERT INTO `b_dept_dict_tmp` VALUES ('00594', '南昌车辆段', '昌段', '03', null, '0', 'G');
INSERT INTO `b_dept_dict_tmp` VALUES ('00595', '上海动车段', '上动段', '03', null, '0', 'H');
INSERT INTO `b_dept_dict_tmp` VALUES ('006', '广州东动车组运用所', '广州东所', '04', '00605', '0', 'Q');
INSERT INTO `b_dept_dict_tmp` VALUES ('00604', '广州车辆段', '广辆段', '03', null, '0', 'Q');
INSERT INTO `b_dept_dict_tmp` VALUES ('00605', '广州动车段', '广动段', '03', null, '0', 'Q');
INSERT INTO `b_dept_dict_tmp` VALUES ('00607', '南宁车辆段', '南宁段', '03', null, '0', 'Z');
INSERT INTO `b_dept_dict_tmp` VALUES ('00615', '兰州车辆段', '兰州段', '03', null, '0', 'J');
INSERT INTO `b_dept_dict_tmp` VALUES ('00616', '西宁车辆段', '西宁段', '03', null, '0', 'O');
INSERT INTO `b_dept_dict_tmp` VALUES ('00621', '重庆车辆段', '渝段', '03', null, '0', 'W');
INSERT INTO `b_dept_dict_tmp` VALUES ('00627', '成都车辆段', '成段', '03', null, '0', 'W');
INSERT INTO `b_dept_dict_tmp` VALUES ('00634', '昆明车辆段', '昆明段', '03', null, '0', 'M');
INSERT INTO `b_dept_dict_tmp` VALUES ('00643', '包头车辆段', '包头段', '03', null, '0', 'C');
INSERT INTO `b_dept_dict_tmp` VALUES ('00653', '乌鲁木齐车辆段', '乌鲁木齐段', '03', null, '0', 'R');
INSERT INTO `b_dept_dict_tmp` VALUES ('00669', '贵阳车辆段', '贵段', '03', null, '0', 'W');
INSERT INTO `b_dept_dict_tmp` VALUES ('00671', '福州车辆段', '福辆段', '03', null, '0', 'G');
INSERT INTO `b_dept_dict_tmp` VALUES ('00672', '福州动车段', '福动段', '03', null, '0', 'G');
INSERT INTO `b_dept_dict_tmp` VALUES ('007', '汉口动车组运用所', '汉口所', '04', '00576', '0', 'N');
INSERT INTO `b_dept_dict_tmp` VALUES ('008', '郑州动车组运用所', '郑州所', '04', '00571', '0', 'F');
INSERT INTO `b_dept_dict_tmp` VALUES ('009', '南昌动车组运用所', '南昌所', '04', '00594', '0', 'G');
INSERT INTO `b_dept_dict_tmp` VALUES ('010', '杭州动车组运用所', '杭州所', '04', '00595', '0', 'H');
INSERT INTO `b_dept_dict_tmp` VALUES ('011', '西安动车组运用所', '西安所', '04', '00573', '1', 'Y');
INSERT INTO `b_dept_dict_tmp` VALUES ('012', '哈尔滨动车组运用所', '哈尔滨所', '04', '00523', '1', 'B');
INSERT INTO `b_dept_dict_tmp` VALUES ('013', '太原动车组运用所', '太原所', '04', '00581', '0', 'V');
INSERT INTO `b_dept_dict_tmp` VALUES ('014', '北京南动车组运用所', '北京南所', '04', '00555', '0', 'P');
INSERT INTO `b_dept_dict_tmp` VALUES ('015', '南翔动车组运用所', '南翔所', '04', '00595', '0', 'H');
INSERT INTO `b_dept_dict_tmp` VALUES ('016', '福州动车组运用所', '福州所', '04', '00672', '0', 'G');
INSERT INTO `b_dept_dict_tmp` VALUES ('017', '重庆北动车组运用所', '重庆北所', '04', '00621', '0', 'W');
INSERT INTO `b_dept_dict_tmp` VALUES ('018', '武汉动车组运用所', '武汉所', '04', '00576', '0', 'N');
INSERT INTO `b_dept_dict_tmp` VALUES ('019', '广州南动车组运用所', '广州南所', '04', '00605', '0', 'Q');
INSERT INTO `b_dept_dict_tmp` VALUES ('020', '长沙动车组运用所', '长沙所', '04', '00605', '0', 'Q');
INSERT INTO `b_dept_dict_tmp` VALUES ('021', '成都东动车组运用所', '成都东所', '04', '00627', '0', 'W');
INSERT INTO `b_dept_dict_tmp` VALUES ('022', '福州南动车组运用所', '福州南所', '04', '00672', '0', 'G');
INSERT INTO `b_dept_dict_tmp` VALUES ('023', '西安北动车组运用所', '西安北所', '04', '00573', '0', 'Y');
INSERT INTO `b_dept_dict_tmp` VALUES ('024', '南京动车组运用所', '南京所', '04', '00595', '0', 'H');
INSERT INTO `b_dept_dict_tmp` VALUES ('025', '虹桥动车组运用所', '虹桥所', '04', '00595', '0', 'H');
INSERT INTO `b_dept_dict_tmp` VALUES ('026', '三亚动车组运用所', '三亚所', '04', '00605', '0', 'Q');
INSERT INTO `b_dept_dict_tmp` VALUES ('028', '广珠动车组运用所', '广珠所', '04', '00605', '0', 'Q');
INSERT INTO `b_dept_dict_tmp` VALUES ('029', '南京南动车组运用所', '南京南所', '04', '00595', '0', 'H');
INSERT INTO `b_dept_dict_tmp` VALUES ('030', '济南动车组运用所', '济南所', '04', '00562', '0', 'K');
INSERT INTO `b_dept_dict_tmp` VALUES ('031', '沈阳北动车组运用所', '沈阳北所', '04', '00509', '0', 'T');
INSERT INTO `b_dept_dict_tmp` VALUES ('032', '长春动车组运用所', '长春所', '04', '00509', '0', 'T');
INSERT INTO `b_dept_dict_tmp` VALUES ('033', '郑州东动车组运用所', '郑州东所', '04', '00571', '0', 'F');
INSERT INTO `b_dept_dict_tmp` VALUES ('034', '大连动车组运用所', '大连所', '04', '00509', '0', 'T');
INSERT INTO `b_dept_dict_tmp` VALUES ('035', '石家庄动车组运用所', '石家庄所', '04', '00555', '0', 'P');
INSERT INTO `b_dept_dict_tmp` VALUES ('036', '哈尔滨西动车组运用所', '哈西所', '04', '00523', '0', 'B');
INSERT INTO `b_dept_dict_tmp` VALUES ('037', '深圳动车组运用所', '深圳所', '04', '00605', '0', 'Q');
INSERT INTO `b_dept_dict_tmp` VALUES ('038', '南昌西动车组运用所', '南昌西所', '04', '00594', '0', 'G');
INSERT INTO `b_dept_dict_tmp` VALUES ('039', '南宁动车组运用所', '南宁所', '04', '00607', '0', 'Z');
INSERT INTO `b_dept_dict_tmp` VALUES ('040', '天津动车组运用所', '天津所', '04', '00556', '0', 'P');
INSERT INTO `b_dept_dict_tmp` VALUES ('041', '青岛北动车组运用所', '青岛北所', '04', '00562', '0', 'K');
INSERT INTO `b_dept_dict_tmp` VALUES ('042', '乌鲁木齐动车组运用所', '乌鲁木齐所', '04', '00653', '0', 'R');
INSERT INTO `b_dept_dict_tmp` VALUES ('043', '西宁动车组运用所', '西宁所', '04', '00616', '0', 'O');
INSERT INTO `b_dept_dict_tmp` VALUES ('044', '合肥南动车组运用所', '合肥南所', '04', '00595', '0', 'H');
INSERT INTO `b_dept_dict_tmp` VALUES ('045', '贵阳北动车组运用所', '贵阳北所', '04', '00669', '0', 'W');
INSERT INTO `b_dept_dict_tmp` VALUES ('046', '兰州西动车组运用所', '兰州西所', '04', '00615', '0', 'J');
INSERT INTO `b_dept_dict_tmp` VALUES ('047', '呼和浩特动车组运用所', '呼和浩特所', '04', '00643', '0', 'C');
INSERT INTO `b_dept_dict_tmp` VALUES ('048', '沈阳南动车组运用所', '沈阳南所', '04', '00509', '0', 'T');
INSERT INTO `b_dept_dict_tmp` VALUES ('098', '铁科院', '铁科院', '04', null, '0', '8');
INSERT INTO `b_dept_dict_tmp` VALUES ('099', '铁道部专运处', '专运', '04', null, '0', '9');
INSERT INTO `b_dept_dict_tmp` VALUES ('222', '22', null, null, null, null, null);
INSERT INTO `b_dept_dict_tmp` VALUES ('301', '北京动车检修车间', '北京车间', '06', '00555', '0', 'P');
INSERT INTO `b_dept_dict_tmp` VALUES ('302', '上海动车检修车间', '上海车间', '06', '00595', '0', 'H');
INSERT INTO `b_dept_dict_tmp` VALUES ('303', '武汉动车检修车间', '武汉车间', '06', '00576', '0', 'N');
INSERT INTO `b_dept_dict_tmp` VALUES ('304', '广州动车检修车间', '广州车间', '06', '00605', '0', 'Q');
INSERT INTO `b_dept_dict_tmp` VALUES ('305', '沈阳动车检修车间', '沈阳车间', '06', '00509', '0', 'T');
