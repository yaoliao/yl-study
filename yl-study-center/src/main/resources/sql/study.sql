/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50717
Source Host           : 116.196.120.187:3306
Source Database       : study

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-03 14:14:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_Biz_Node
-- ----------------------------
DROP TABLE IF EXISTS `t_Biz_Node`;
CREATE TABLE `t_Biz_Node` (
  `FID` int(11) NOT NULL AUTO_INCREMENT COMMENT '业务节点定义表ID',
  `FName` varchar(100) DEFAULT NULL COMMENT '业务节点名称',
  `FModuleID` int(11) NOT NULL COMMENT '所属业务模块id',
  `FPriority` int(11) DEFAULT NULL COMMENT '优先级c',
  `FUniqueKey` varchar(255) DEFAULT NULL COMMENT '业务节点标识（唯一）',
  `FIsEnabled` int(11) DEFAULT NULL COMMENT '是否启用',
  `FEnableTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '启用时间',
  `FOperatorName` varchar(50) DEFAULT NULL COMMENT '操作人',
  `FCreateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `FUpdateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `FIsDelete` int(11) DEFAULT '0' COMMENT '是否已删除((0))',
  `FOperatorID` int(11) DEFAULT NULL COMMENT '操作人ID',
  `FModuleName` varchar(255) DEFAULT NULL COMMENT '业务模块名称',
  `FModuleCode` varchar(255) DEFAULT NULL COMMENT '模块code',
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_Biz_Node
-- ----------------------------
INSERT INTO `t_Biz_Node` VALUES ('4', '销售审核通过，上架', '40', null, 'car-saleAuditPass', '1', '2016-08-24 16:21:18', '123', '2016-08-25 10:30:06', null, '0', '3', null, 'car');
INSERT INTO `t_Biz_Node` VALUES ('5', 'student测试', '1', null, 'student-test', '1', '2017-11-02 18:53:24', '233', '2017-11-02 18:53:33', null, '0', '3', null, 'student');

-- ----------------------------
-- Table structure for t_Evt_EventDefine
-- ----------------------------
DROP TABLE IF EXISTS `t_Evt_EventDefine`;
CREATE TABLE `t_Evt_EventDefine` (
  `FID` int(11) NOT NULL AUTO_INCREMENT,
  `FName` varchar(100) DEFAULT NULL COMMENT '事件名称',
  `FCode` varchar(100) DEFAULT NULL COMMENT '事件code',
  `FBizDefineId` int(11) DEFAULT NULL COMMENT '业务定义ID',
  `FBizDefineName` varchar(100) DEFAULT NULL COMMENT '业务名称',
  `FBizDefineCode` varchar(100) DEFAULT NULL COMMENT '业务定义code',
  `FNodeDefineId` int(11) DEFAULT NULL COMMENT 'node定义id',
  `FNodeDefineName` varchar(100) DEFAULT NULL COMMENT '节点名称',
  `FNodeDefineCode` varchar(100) DEFAULT NULL COMMENT 'node定义code',
  `FTopic` varchar(100) DEFAULT NULL COMMENT 'mq指定的topic',
  `FSubscribe` varchar(100) DEFAULT NULL COMMENT 'mq指定subscribe',
  `FProducerGroupName` varchar(100) DEFAULT NULL COMMENT '生产者组名称',
  `FConsumerGroupName` varchar(100) DEFAULT NULL COMMENT '消费者组名称',
  `FIsEnabled` int(1) DEFAULT NULL COMMENT '是否启用((1))',
  `FStep` int(11) DEFAULT NULL COMMENT '步骤',
  `FDelayType` int(11) DEFAULT NULL COMMENT '延时消费类型0不延迟 1固定延迟 2自定义延迟 3活动延时（主要用于收车宝延时审核）',
  `FDelayLevel` int(11) DEFAULT NULL COMMENT '固定延时级别',
  `FDelayCalculatorCode` varchar(255) DEFAULT NULL COMMENT '自定义延时器code',
  `FLastModifyTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `FRetry` int(11) DEFAULT NULL COMMENT '重试次数((3))',
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_Evt_EventDefine
-- ----------------------------
INSERT INTO `t_Evt_EventDefine` VALUES ('1', '降价模块后台触发', 'car-saleAuditPass-event', '40', '降价模块', 'car', '76', '销售审核通过-上架', 'car-saleAuditPass', 'car-topic', 'car-saleAuditPass-event-subscribe', 'car-saleAuditPass-event-producerGroup', 'car-saleAuditPass-event-consumerGroup', '1', null, '0', null, null, null, '3');
INSERT INTO `t_Evt_EventDefine` VALUES ('2', 'student测试', 'student-event', '1', '学生模块', 'student', '1', '学生测试', 'student-test', 'study-student-topic', 'study-student-tag', 'study-student-group-producerGroup', 'study-student-group-name', '1', null, '0', null, null, '2017-11-02 10:58:06', '3');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `FID` int(11) NOT NULL AUTO_INCREMENT,
  `FName` varchar(50) DEFAULT NULL,
  `FAge` int(11) DEFAULT NULL,
  `FMobile` varchar(11) DEFAULT NULL,
  `FDescription` varchar(255) DEFAULT NULL,
  `FPosition` int(11) DEFAULT NULL COMMENT '职位 (0:无职位，1:班长，2:副班长)',
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('1', 'Jack', '22', '18333333333', 'hello i am jack', '1');
INSERT INTO `t_student` VALUES ('2', 'Rose', '21', '18222222222', 'i am Rose', '2');
INSERT INTO `t_student` VALUES ('3', 'Tom', '22', '18365478524', 'Tom Tom Tom', '0');
INSERT INTO `t_student` VALUES ('4', '李梅', '22', '15478954250', '我就是大名鼎鼎的李梅', '0');
INSERT INTO `t_student` VALUES ('5', '韩磊磊', '23', '17777777771', '我是韩磊磊，不是李梅', null);
