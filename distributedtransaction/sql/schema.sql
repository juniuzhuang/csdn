/*
Navicat MySQL Data Transfer
Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-12-27 14:40:37
*/



SET FOREIGN_KEY_CHECKS=0;
CREATE DATABASE `user_integral` /*!40100 DEFAULT CHARACTER SET utf8 */;
user_integral;
CREATE TABLE `user_integral` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `user_integral` decimal(10,0) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_integral
-- ----------------------------
INSERT INTO `user_integral` VALUES ('1', 'tuntxun', '20000', '2019-12-27 14:36:42', '2019-12-27 14:36:46');



/*
Navicat MySQL Data Transfer
Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-12-27 14:40:37
*/

CREATE DATABASE `user_redpacket` /*!40100 DEFAULT CHARACTER SET utf8 */;
user_redpacket;
-- ----------------------------
-- Table structure for `user_redpacket`
-- ----------------------------
DROP TABLE IF EXISTS `user_redpacket`;
CREATE TABLE `user_redpacket` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `user_id` varchar(30) NOT NULL,
  `balance_redpacket` decimal(10,2) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_redpacket
-- ----------------------------
INSERT INTO `user_redpacket` VALUES ('1', 'tunxun', '10000.00', '2019-12-27 14:32:52', '2019-12-27 14:33:54');
