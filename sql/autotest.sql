/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50163
 Source Host           : localhost
 Source Database       : autotest

 Target Server Type    : MySQL
 Target Server Version : 50163
 File Encoding         : utf-8

 Date: 09/16/2015 13:41:17 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `at_users`
-- ----------------------------
DROP TABLE IF EXISTS `at_users`;
CREATE TABLE `at_users` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `atu_name` varchar(255) NOT NULL,
  `atu_email` varchar(255) NOT NULL,
  `atu_uid` varchar(255) NOT NULL,
  `atu_pwd` varchar(255) NOT NULL,
  `atu_status` int(11) NOT NULL,
  `atu_purview` int(11) NOT NULL,
  `atu_createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `atu_updatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `at_users`
-- ----------------------------
BEGIN;
INSERT INTO `at_users` VALUES ('1', 'rock', 'rock@rock.com', '', '', '0', '0', '2015-09-16 13:33:51', '2015-09-16 11:47:34');
COMMIT;

