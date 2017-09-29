/*
SQLyog Ultimate v9.60 
MySQL - 5.6.11 : Database - zy_db_manager
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zy_db_manager` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zy_db_manager`;

/*Table structure for table `voice_merchant_balance_monitor` */

DROP TABLE IF EXISTS `voice_merchant_balance_monitor`;

CREATE TABLE `voice_merchant_balance_monitor` (
  `ID` int(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `MERCHANT_ACCOUNT` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `API_ACCOUNT` varchar(64) NOT NULL COMMENT 'apiAcount',
  `MONITOR_MIN_BALANCE` int(20) NOT NULL DEFAULT '10000' COMMENT '监控最小余额，单位:分100=1F',
  `MONITOR_TIME_RANGE` varchar(100) DEFAULT NULL COMMENT '监控时间段',
  `NOTICE_TIME_RANGE1` varchar(50) DEFAULT NULL COMMENT '报警时段设置1',
  `NOTICE_WAY1` varchar(10) DEFAULT NULL COMMENT '报警方式设置0短信1邮箱2语音',
  `NOTICE_EMAIL1` varchar(100) DEFAULT NULL COMMENT '通知邮箱,多个邮箱号用","隔开',
  `NOTICE_PHONE1` varchar(100) DEFAULT NULL COMMENT '通知手机号,多个手机号用","隔开',
  `NOTICE_TIME_RANGE2` varchar(50) DEFAULT NULL COMMENT '报警时段设置2',
  `NOTICE_WAY2` varchar(10) DEFAULT NULL COMMENT '报警方式设置0短信1邮箱2语音',
  `NOTICE_EMAIL2` varchar(100) DEFAULT NULL COMMENT '通知邮箱,多个邮箱号用","隔开',
  `NOTICE_PHONE2` varchar(100) DEFAULT NULL COMMENT '通知手机号,多个手机号用","隔开',
  `NOTICE_TIME_RANGE3` varchar(50) DEFAULT NULL COMMENT '报警时段设置3',
  `NOTICE_WAY3` varchar(10) DEFAULT NULL COMMENT '报警方式设置0短信1邮箱2语音',
  `NOTICE_EMAIL3` varchar(100) DEFAULT NULL COMMENT '通知邮箱,多个邮箱号用","隔开',
  `NOTICE_PHONE3` varchar(100) DEFAULT NULL COMMENT '通知手机号,多个手机号用","隔开',
  `NOTICE_FLAG` int(1) DEFAULT '0' COMMENT '0:正常用户1 已通知',
  `NOTICE_TENTH_FLAG` int(1) DEFAULT '0' COMMENT '0:正常用户1 已通知',
  `UPDATE_TIME` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `START_FLAG` int(1) DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*!50106 set global event_scheduler = 1*/;

/* Event structure for event `reset_voice_balance_monitor_event` */

/*!50106 DROP EVENT IF EXISTS `reset_voice_balance_monitor_event`*/;

DELIMITER $$

/*!50106 CREATE DEFINER=`zy_dev`@`218.18.114.122` EVENT `reset_voice_balance_monitor_event` ON SCHEDULE EVERY 1 DAY STARTS '2016-11-04 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
        CALL reset_voice_balance_monitor();
  END */$$
DELIMITER ;

/* Procedure structure for procedure `reset_voice_balance_monitor` */

/*!50003 DROP PROCEDURE IF EXISTS  `reset_voice_balance_monitor` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`zy_dev`@`218.18.114.122` PROCEDURE `reset_voice_balance_monitor`()
BEGIN
	UPDATE VOICE_MERCHANT_BALANCE_MONITOR SET NOTICE_FLAG=0,NOTICE_TENTH_FLAG=0 WHERE NOTICE_FLAG=1 AND START_FLAG=1;
    END */$$
DELIMITER ;

/*Table structure for table `account_balance_monitor_view` */

DROP TABLE IF EXISTS `account_balance_monitor_view`;

/*!50001 DROP VIEW IF EXISTS `account_balance_monitor_view` */;
/*!50001 DROP TABLE IF EXISTS `account_balance_monitor_view` */;

/*!50001 CREATE TABLE  `account_balance_monitor_view`(
 `ID` int(12) ,
 `MERCHANT_ACCOUNT` varchar(50) ,
 `MERCHANT_PHONE` varchar(12) ,
 `MERCHANT_EMAIL` varchar(50) ,
 `API_ACCOUNT` varchar(64) ,
 `BALANCE` int(11) ,
 `MONITOR_MIN_BALANCE` int(20) ,
 `MONITOR_TIME_RANGE` varchar(100) ,
 `NOTICE_TIME_RANGE1` varchar(50) ,
 `NOTICE_WAY1` varchar(10) ,
 `NOTICE_EMAIL1` varchar(100) ,
 `NOTICE_PHONE1` varchar(100) ,
 `NOTICE_TIME_RANGE2` varchar(50) ,
 `NOTICE_WAY2` varchar(10) ,
 `NOTICE_EMAIL2` varchar(100) ,
 `NOTICE_PHONE2` varchar(100) ,
 `NOTICE_TIME_RANGE3` varchar(50) ,
 `NOTICE_WAY3` varchar(10) ,
 `NOTICE_EMAIL3` varchar(100) ,
 `NOTICE_PHONE3` varchar(100) ,
 `NOTICE_FLAG` int(1) ,
 `NOTICE_TENTH_FLAG` int(1) ,
 `UPDATE_TIME` timestamp ,
 `CREATE_TIME` timestamp ,
 `START_FLAG` int(1) 
)*/;

/*Table structure for table `account_balance_veiw` */

DROP TABLE IF EXISTS `account_balance_veiw`;

/*!50001 DROP VIEW IF EXISTS `account_balance_veiw` */;
/*!50001 DROP TABLE IF EXISTS `account_balance_veiw` */;

/*!50001 CREATE TABLE  `account_balance_veiw`(
 `API_ACCOUNT` varchar(64) ,
 `API_KEY` varchar(64) ,
 `MERCHANT_ACCOUNT` varchar(50) ,
 `MERCHANT_PHONE` varchar(12) ,
 `MERCHANT_EMAIL` varchar(50) ,
 `MERCHANT_PWD` varchar(50) ,
 `BUSINESS_NAME` varchar(50) ,
 `MERCHANT_TYPE` int(2) ,
 `AUTH_FLAG` int(2) ,
 `IS_LOCKED` smallint(3) ,
 `UPDATE_TIME` timestamp ,
 `CREATE_TIME` timestamp ,
 `BALANCE` int(11) 
)*/;

/*View structure for view account_balance_monitor_view */

/*!50001 DROP TABLE IF EXISTS `account_balance_monitor_view` */;
/*!50001 DROP VIEW IF EXISTS `account_balance_monitor_view` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`zy_dev`@`218.18.114.122` SQL SECURITY DEFINER VIEW `account_balance_monitor_view` AS (select `b`.`ID` AS `ID`,`a`.`MERCHANT_ACCOUNT` AS `MERCHANT_ACCOUNT`,`a`.`MERCHANT_PHONE` AS `MERCHANT_PHONE`,`a`.`MERCHANT_EMAIL` AS `MERCHANT_EMAIL`,`a`.`API_ACCOUNT` AS `API_ACCOUNT`,`a`.`BALANCE` AS `BALANCE`,`b`.`MONITOR_MIN_BALANCE` AS `MONITOR_MIN_BALANCE`,`b`.`MONITOR_TIME_RANGE` AS `MONITOR_TIME_RANGE`,`b`.`NOTICE_TIME_RANGE1` AS `NOTICE_TIME_RANGE1`,`b`.`NOTICE_WAY1` AS `NOTICE_WAY1`,`b`.`NOTICE_EMAIL1` AS `NOTICE_EMAIL1`,`b`.`NOTICE_PHONE1` AS `NOTICE_PHONE1`,`b`.`NOTICE_TIME_RANGE2` AS `NOTICE_TIME_RANGE2`,`b`.`NOTICE_WAY2` AS `NOTICE_WAY2`,`b`.`NOTICE_EMAIL2` AS `NOTICE_EMAIL2`,`b`.`NOTICE_PHONE2` AS `NOTICE_PHONE2`,`b`.`NOTICE_TIME_RANGE3` AS `NOTICE_TIME_RANGE3`,`b`.`NOTICE_WAY3` AS `NOTICE_WAY3`,`b`.`NOTICE_EMAIL3` AS `NOTICE_EMAIL3`,`b`.`NOTICE_PHONE3` AS `NOTICE_PHONE3`,`b`.`NOTICE_FLAG` AS `NOTICE_FLAG`,`b`.`NOTICE_TENTH_FLAG` AS `NOTICE_TENTH_FLAG`,`b`.`UPDATE_TIME` AS `UPDATE_TIME`,`b`.`CREATE_TIME` AS `CREATE_TIME`,`b`.`START_FLAG` AS `START_FLAG` from (`zy_db_manager`.`account_balance_veiw` `a` left join `zy_db_manager`.`voice_merchant_balance_monitor` `b` on((`a`.`API_ACCOUNT` = `b`.`API_ACCOUNT`)))) */;

/*View structure for view account_balance_veiw */

/*!50001 DROP TABLE IF EXISTS `account_balance_veiw` */;
/*!50001 DROP VIEW IF EXISTS `account_balance_veiw` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`zy_dev`@`218.18.114.122` SQL SECURITY DEFINER VIEW `account_balance_veiw` AS (select `a`.`API_ACCOUNT` AS `API_ACCOUNT`,`a`.`API_KEY` AS `API_KEY`,`a`.`MERCHANT_ACCOUNT` AS `MERCHANT_ACCOUNT`,`a`.`MERCHANT_PHONE` AS `MERCHANT_PHONE`,`a`.`MERCHANT_EMAIL` AS `MERCHANT_EMAIL`,`a`.`MERCHANT_PWD` AS `MERCHANT_PWD`,`a`.`BUSINESS_NAME` AS `BUSINESS_NAME`,`a`.`MERCHANT_TYPE` AS `MERCHANT_TYPE`,`a`.`AUTH_FLAG` AS `AUTH_FLAG`,`a`.`IS_LOCKED` AS `IS_LOCKED`,`a`.`UPDATE_TIME` AS `UPDATE_TIME`,`a`.`CREATE_TIME` AS `CREATE_TIME`,`b`.`BALANCE` AS `BALANCE` from (`zy_db_paas`.`voice_merchant_account` `a` join `zy_db_paas`.`voice_merchant_account_balance` `b`) where (`a`.`API_ACCOUNT` = `b`.`API_ACCOUNT`)) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
