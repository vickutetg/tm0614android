/*
Navicat MySQL Data Transfer

Source Server         : HoangHiep
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : testjava

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2014-10-17 13:32:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_product
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product`;
CREATE TABLE `tbl_product` (
  `id` bigint(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` int(10) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_product
-- ----------------------------
INSERT INTO `tbl_product` VALUES ('1', 'Plasma TV 32 inches', '1000', '1');
INSERT INTO `tbl_product` VALUES ('2', 'Macbook Pro 18', '1500', '1');
INSERT INTO `tbl_product` VALUES ('3', 'Mitsumi mouse', '5', '1');
INSERT INTO `tbl_product` VALUES ('4', 'Elead computer', '500', '1');
INSERT INTO `tbl_product` VALUES ('5', 'Bkav antivirus', '30', '1');

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('1', 'HoangHiep0901@gmail.com', 'abcabc', 'Tran Hoang Hiep');
INSERT INTO `tbl_user` VALUES ('2', 'hiep@it.vn', 'abcabc', 'Tran Hoang Hiep');

-- ----------------------------
-- Procedure structure for getData
-- ----------------------------
DROP PROCEDURE IF EXISTS `getData`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getData`()
BEGIN
	SELECT * from tbl_user;
END
;;
DELIMITER ;
