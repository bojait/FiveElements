/*
 Navicat Premium Data Transfer

 Source Server         : database
 Source Server Type    : MySQL
 Source Server Version : 50627
 Source Host           : localhost
 Source Database       : five_db

 Target Server Type    : MySQL
 Target Server Version : 50627
 File Encoding         : utf-8

 Date: 10/13/2015 18:30:30 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `element_kind_tbs`
-- ----------------------------
DROP TABLE IF EXISTS `element_kind_tbs`;
CREATE TABLE `element_kind_tbs` (
	`id` int(11) NOT NULL,
	`name_en` varchar(20) DEFAULT NULL,
	`name_vn` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
	`creation_id` int(11) DEFAULT NULL,
	`destruction_id` int(11) DEFAULT NULL,
	`color` varchar(10) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ROW_FORMAT=COMPACT COMMENT='' CHECKSUM=0 DELAY_KEY_WRITE=0;

-- ----------------------------
--  Records of `element_kind_tbs`
-- ----------------------------
BEGIN;
INSERT INTO `element_kind_tbs` VALUES ('-1', 'none', 'none', '-1', '-1', '#ffffff'), ('0', 'metal', 'hệ kim', '1', '3', '#f7e54e'), ('1', 'water', 'hệ kim', '2', '4', '#4e82f7'), ('2', 'wood', 'hệ mộc', '3', '0', '#6ef74e'), ('3', 'fire', 'hệ hoả', '4', '1', '#ff3a3a'), ('4', 'earth', 'hệ thổ', '0', '2', '#7a7a7a');
COMMIT;

-- ----------------------------
--  Table structure for `equipment_kind_tbs`
-- ----------------------------
DROP TABLE IF EXISTS `equipment_kind_tbs`;
CREATE TABLE `equipment_kind_tbs` (
	`id` int(11) NOT NULL,
	`name_en` varchar(20) DEFAULT NULL,
	`name_vn` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
	`creation_1_id` int(11) DEFAULT NULL,
	`creation_2_id` int(11) DEFAULT NULL,
	`cls` int(11) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ROW_FORMAT=COMPACT COMMENT='' CHECKSUM=0 DELAY_KEY_WRITE=0;

-- ----------------------------
--  Records of `equipment_kind_tbs`
-- ----------------------------
BEGIN;
INSERT INTO `equipment_kind_tbs` VALUES ('-1', 'none', 'none', '-1', '-1', '0'), ('0', 'weapons', 'Vũ khí', '9', '3', '0'), ('1', 'hat', 'Đỉnh mạo', '3', '9', '1'), ('2', 'glove', 'Hộ uyển', '7', '5', '0'), ('3', 'shirt', 'Y phục', '4', '6', '1'), ('4', 'belt', 'Yên đái', '8', '2', '1'), ('5', 'shoes', 'Hài', '1', '0', '1'), ('6', 'rings down', 'Nhẫn dưới', '2', '8', '0'), ('7', 'ring up', 'Nhẫn trên', '0', '1', '0'), ('8', 'pearl', 'Ngọc bội', '5', '7', '1'), ('9', 'chain', 'Hạng liên', '6', '4', '0');
COMMIT;

-- ----------------------------
--  Table structure for `equipment_tbs`
-- ----------------------------
DROP TABLE IF EXISTS `equipment_tbs`;
CREATE TABLE `equipment_tbs` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
	`equipment_kind_id` int(11) DEFAULT NULL,
	`equipment_type_id` int(11) DEFAULT NULL,
	`element_id` int(11) DEFAULT NULL,
	`properties_id_0` int(11) DEFAULT NULL,
	`value_0` int(11) DEFAULT NULL,
	`properties_id_1` int(11) DEFAULT NULL,
	`value_1` int(11) DEFAULT NULL,
	`properties_id_2` int(11) DEFAULT NULL,
	`value_2` int(11) DEFAULT NULL,
	`properties_id_3` int(11) DEFAULT NULL,
	`value_3` int(11) DEFAULT NULL,
	`properties_id_4` int(11) DEFAULT NULL,
	`value_4` int(11) DEFAULT NULL,
	`properties_id_5` int(11) DEFAULT NULL,
	`value_5` int(11) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=`InnoDB` AUTO_INCREMENT=1 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ROW_FORMAT=COMPACT COMMENT='' CHECKSUM=0 DELAY_KEY_WRITE=0;

-- ----------------------------
--  Table structure for `equipment_type_tbs`
-- ----------------------------
DROP TABLE IF EXISTS `equipment_type_tbs`;
CREATE TABLE `equipment_type_tbs` (
	`id` int(11) NOT NULL,
	`name_en` varchar(10) DEFAULT NULL,
	`name_vn` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
	`color` varchar(10) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ROW_FORMAT=COMPACT COMMENT='' CHECKSUM=0 DELAY_KEY_WRITE=0;

-- ----------------------------
--  Records of `equipment_type_tbs`
-- ----------------------------
BEGIN;
INSERT INTO `equipment_type_tbs` VALUES ('0', 'white', 'trang bị trắng', '#ffffff'), ('1', 'blue', 'trang bị xanh', '#4e82f7'), ('2', 'gold', 'trang bị hoàng kim', '#f7e54e');
COMMIT;

-- ----------------------------
--  Table structure for `properties_kind_tbs`
-- ----------------------------
DROP TABLE IF EXISTS `properties_kind_tbs`;
CREATE TABLE `properties_kind_tbs` (
	`id` int(11) NOT NULL,
	`name_vn` varchar(128) CHARACTER SET utf8 DEFAULT NULL,
	`equip_id` varchar(20) DEFAULT NULL,
	`hide` int(11) DEFAULT 0,
	`element_id` int(11) DEFAULT -1,
	`score` int(11) DEFAULT 0,
	PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ROW_FORMAT=COMPACT COMMENT='' CHECKSUM=0 DELAY_KEY_WRITE=0;

-- ----------------------------
--  Records of `properties_kind_tbs`
-- ----------------------------
BEGIN;
INSERT INTO `properties_kind_tbs` VALUES ('-1', 'none', null, '0', '-1', '1'), ('0', 'kháng tất cả', '9', '0', '-1', '0'), ('1', 'sinh lực tối đa', null, '0', '-1', '1'), ('2', 'phục hồi sinh lực mỗi nữa giây', '1,2,3,4,5', '0', '-1', '1'), ('3', 'nội lực tối đa', null, '0', '-1', '1'), ('4', 'phục hồi nội lực mỗi nữa giây', '8', '0', '-1', '1'), ('5', 'thể lực tối đa\",\"equip_id\"', null, '0', '-1', '1'), ('6', 'phục hồi thể lực  mỗi nữa giây', '6,7,9', '0', '-1', '1'), ('7', 'sức mạnh', '6,7,8,9', '1', '0', '1'), ('8', 'thân pháp', null, '1', '3', '1'), ('9', 'sinh khí', null, '1', '1', '1'), ('10', 'nội công', null, '1', '-1', '1'), ('11', 'phòng thủ vật lý', '1,2,3,4,5', '1', '3', '0'), ('12', 'kháng độc', null, '1', '0', '0'), ('13', 'kháng hoả', null, '1', '1', '0'), ('14', 'kháng lôi', null, '1', '2', '0'), ('15', 'kháng băng', null, '1', '4', '0'), ('16', 'thời gian làm chậm', null, '1', '3', '0'), ('17', 'thời gian trúng độc', null, '1', '4', '0'), ('18', 'thời gian choáng', null, '1', '1', '0'), ('19', 'thời gian phục hồi', '3', '0', '-1', '-1'), ('20', 'tốc độ di chuyển', '5', '0', '-1', '0'), ('21', 'tốc độ đánh - ngoại công', '0', '0', '-1', '0'), ('22', 'tốc độ đánh - nội công', '0', '0', '-1', '0'), ('23', 'sát thương vật lý ngoại công điểm', '0', '1', '0', '1'), ('24', 'hoả sát - ngoại công', '0', '1', '3', '1'), ('25', 'băng sát - ngoại công', '0', '1', '1', '1'), ('26', 'lôi sát - ngoại công', '0', '1', '4', '1'), ('27', 'độc sát - ngoại công', '0', '1', '2', '1'), ('28', 'sát thương vật lý - ngoại công %', '0', '0', '-1', '0'), ('29', 'sát thương vật lý - nội công', '0', '1', '0', '1'), ('30', 'hoả sát - nội công', '0', '1', '3', '1'), ('31', 'băng sát - nội công', '0', '1', '1', '1'), ('32', 'lôi sát - nội công', '0', '1', '4', '1'), ('33', 'độc sát - nội công', '0', '1', '2', '1'), ('34', 'tỉ lệ công kich chính xác', '0', '0', '-1', '1'), ('35', 'chuyển hoá sát thương thành nội lực', null, '1', '-1', '0'), ('36', 'may mắn', '6,7,8,9', '1', '2', '0'), ('37', 'phản đòn cận chiến', '3', '0', '-1', '0'), ('38', 'bỏ qua né tránh', '0', '0', '-1', '0'), ('39', 'hút sinh lực', '0', '0', '-1', '0'), ('40', 'bỏ qua né tránh', '0', '0', '-1', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
