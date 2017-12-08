/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : db_wgt_simple_v13

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2012-07-11 07:19:58
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `tbl_answers`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_answers`;
CREATE TABLE `tbl_answers` (
  `id` int(11) NOT NULL auto_increment,
  `anwsers` tinytext,
  `dest_addr` tinytext,
  `org_addr` tinytext,
  `qid` int(11) default NULL,
  `receive_date` tinytext,
  `area_id` int(11) default NULL,
  `company_id` tinytext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_answers
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_my_send_msg`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_my_send_msg`;
CREATE TABLE `tbl_my_send_msg` (
  `id` int(11) NOT NULL,
  `approval_person` tinytext,
  `approval_time` datetime default NULL,
  `creatorid` tinytext,
  `dest_add_string` tinytext,
  `dest_addr` tinytext,
  `fs_status` int(11) default NULL,
  `is_del` int(11) default NULL,
  `send_time` datetime default NULL,
  `sm_content` tinytext,
  `sms_num` int(11) NOT NULL,
  `sms_type` int(11) default NULL,
  `sub_time` datetime default NULL,
  `user_name` tinytext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_my_send_msg
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_sms_send_bak`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sms_send_bak`;
CREATE TABLE `tbl_sms_send_bak` (
  `id` int(11) NOT NULL auto_increment,
  `approval_person` tinytext,
  `approval_time` datetime default NULL,
  `creatorid` tinytext,
  `dest_add_string` longtext,
  `dest_addr` longtext,
  `fs_status` int(11) default NULL,
  `is_del` int(11) default '0',
  `qusetion_id` tinytext,
  `send_time` datetime default NULL,
  `sm_content` longtext,
  `sms_num` int(11) NOT NULL,
  `sms_type` int(11) default NULL,
  `sub_time` datetime default NULL,
  `user_name` tinytext,
  `company_id` varchar(10) default NULL,
  `company_name` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_sms_send_bak
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_basedata_area`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_area`;
CREATE TABLE `tb_basedata_area` (
  `id` int(11) NOT NULL,
  `area_name` varchar(30) default NULL,
  `areaplace` tinytext,
  `build` tinytext,
  `build_num` double default NULL,
  `developer` tinytext,
  `flatlet_num` int(11) default NULL,
  `is_checked` int(11) default NULL,
  `occupy_num` double default NULL,
  `order_by_area` int(11) default NULL,
  `population_num` int(11) default NULL,
  `seat_num` int(11) default NULL,
  `use_num` double default NULL,
  `disk_time` datetime default NULL,
  `area_manager` tinytext,
  `mobile` tinytext,
  `pool_ratio` decimal(10,2) default NULL,
  `company_id` varchar(10) default NULL,
  `company_name` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_basedata_area
-- ----------------------------
INSERT INTO tb_basedata_area VALUES ('1001', '大溪地一期', '', '', null, '', null, null, null, null, null, null, null, null, '', '', '0.00', 'b10001', '物业');
INSERT INTO tb_basedata_area VALUES ('1002', '大溪地二期', '', '', null, '', null, null, null, null, null, null, null, null, '', '', '0.00', 'b10001', '物业');
INSERT INTO tb_basedata_area VALUES ('1003', '大溪地三期', '', '', null, '', null, null, null, null, null, null, null, null, '', '', '0.00', 'b10001', '物业');

-- ----------------------------
-- Table structure for `tb_basedata_carport`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_carport`;
CREATE TABLE `tb_basedata_carport` (
  `id` int(11) NOT NULL auto_increment,
  `car_code` varchar(50) default NULL,
  `local` varchar(50) default NULL,
  `mianji` decimal(5,2) default NULL,
  `state` varchar(50) default NULL,
  `area` int(11) default NULL,
  `is_del` tinyint(3) unsigned default NULL,
  `type` char(1) default NULL,
  `carport_lease_id` int(11) default NULL,
  `type2` varchar(50) default NULL,
  `is_fill` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_basedata_carport
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_basedata_carport_lease`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_carport_lease`;
CREATE TABLE `tb_basedata_carport_lease` (
  `id` int(11) NOT NULL auto_increment,
  `car_nums` varchar(50) default NULL,
  `in_date` datetime default NULL,
  `out_date` datetime default NULL,
  `carport` int(11) default NULL,
  `state` varchar(50) default NULL,
  `big_type` varchar(50) default NULL,
  `record_month` date default NULL,
  `type` varchar(50) default NULL,
  `local` varchar(50) default NULL,
  `mianji` double default NULL,
  `owner_name` varchar(50) default NULL,
  `car_code` varchar(50) default NULL,
  `charge_id` int(11) default NULL,
  `edifice_id` varchar(50) default NULL,
  `house_id` varchar(50) default NULL,
  `is_now` tinytext,
  `fact_money` decimal(8,2) default NULL,
  `area_id` int(11) default NULL,
  `electric` varchar(10) default NULL,
  `begin_date` datetime default NULL,
  `end_date` datetime default NULL,
  `car_color` varchar(50) default NULL,
  `card_num` varchar(50) default NULL,
  `phone` varchar(50) default NULL,
  `remark` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_basedata_carport_lease
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_basedata_carport_lease_detail`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_carport_lease_detail`;
CREATE TABLE `tb_basedata_carport_lease_detail` (
  `id` int(11) NOT NULL auto_increment,
  `big_type` varchar(50) default NULL,
  `type` varchar(50) default NULL,
  `record_month` datetime default NULL,
  `local` varchar(50) default NULL,
  `carport_id` int(11) default NULL,
  `carport_lease_id` int(11) default NULL,
  `state` varchar(50) default NULL,
  `mianji` decimal(8,2) default NULL,
  `charge_id` int(11) default NULL,
  `charge_big_type` varchar(50) default NULL,
  `charge_name` varchar(50) default NULL,
  `owner_name` varchar(50) default NULL,
  `house_id` varchar(50) default NULL,
  `area_id` int(11) default NULL,
  `ought_money` double default NULL,
  `fact_money` double default NULL,
  `pay_money` double default NULL,
  `arrearage_money` double default NULL,
  `gathering_date` datetime default NULL,
  `other_name` varchar(50) default NULL,
  `car_code` varchar(50) default NULL,
  `charge_house_detail_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_basedata_carport_lease_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_basedata_customer_repair`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_customer_repair`;
CREATE TABLE `tb_basedata_customer_repair` (
  `id` int(11) NOT NULL auto_increment,
  `accepted_date` varchar(20) default NULL,
  `achieve` datetime default NULL,
  `content` varchar(500) default NULL,
  `dispatcher_num` varchar(10) default NULL,
  `reciprocal` varchar(20) default NULL,
  `record_month` datetime default NULL,
  `repair_context` varchar(200) default NULL,
  `repair_result` varchar(200) default NULL,
  `reservation` varchar(200) default NULL,
  `visit_result` varchar(200) default NULL,
  `wxname` varchar(50) default NULL,
  `area_id` int(11) default NULL,
  `area_name` varchar(20) default NULL,
  `accept_hour` int(11) default NULL,
  `accept_min` int(11) default NULL,
  `accept_second` int(11) default NULL,
  `house_id` varchar(20) default NULL,
  `reserve_hour` int(11) default NULL,
  `reserve_min` int(11) default NULL,
  `reserve_second` int(11) default NULL,
  `achieve_hour` int(11) default NULL,
  `achieve_min` int(11) default NULL,
  `achieve_second` int(11) default NULL,
  `repair_status` varchar(20) default NULL,
  `paid_service` varchar(20) default NULL,
  `achieve_date` varchar(20) default NULL,
  `reserve_date` varchar(20) default NULL,
  `comaddress` varchar(50) default NULL,
  `end_month` datetime default NULL,
  `flow_state` char(1) default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_basedata_customer_repair
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_basedata_developers_repair`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_developers_repair`;
CREATE TABLE `tb_basedata_developers_repair` (
  `id` int(11) NOT NULL auto_increment,
  `a_siguature` varchar(20) default NULL,
  `acceptance_people` varchar(20) default NULL,
  `acceptance_time` varchar(20) default NULL,
  `asign_people` varchar(20) default NULL,
  `asignrecord_date` datetime default NULL,
  `dispat_day` int(11) default NULL,
  `dispat_hours` int(11) default NULL,
  `dispat_minute` int(11) default NULL,
  `dispat_month` int(11) default NULL,
  `dispat_year` int(11) default NULL,
  `explain` tinytext,
  `number` varchar(20) default NULL,
  `owner_name` varchar(20) default NULL,
  `owner_phone` varchar(20) default NULL,
  `property_person` varchar(20) default NULL,
  `repair_address` varchar(50) default NULL,
  `repair_content` tinytext,
  `repair_of` tinytext,
  `repair_people` tinytext,
  `supervision` tinytext,
  `area_id` int(11) default NULL,
  `repair_status` varchar(20) default NULL,
  `house_id` varchar(20) default NULL,
  `dispat_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_basedata_developers_repair
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_basedata_edifice`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_edifice`;
CREATE TABLE `tb_basedata_edifice` (
  `id` varchar(50) NOT NULL,
  `build_num` double default NULL,
  `cell_num` tinytext,
  `edifice_name` tinytext,
  `generalsituation` tinytext,
  `layer_num` tinytext,
  `remark` tinytext,
  `area` int(11) default NULL,
  `edifice_use` tinytext,
  `house_orientation` tinytext,
  `manager_name` varchar(25) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_basedata_edifice
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_basedata_habitation_type`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_habitation_type`;
CREATE TABLE `tb_basedata_habitation_type` (
  `id` int(11) NOT NULL,
  `coefficient` tinytext,
  `type` tinytext
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_basedata_habitation_type
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_basedata_house`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_house`;
CREATE TABLE `tb_basedata_house` (
  `id` varchar(20) NOT NULL default '',
  `area_id` int(11) default NULL,
  `area_name` varchar(100) default NULL,
  `edifice_id` varchar(20) default NULL,
  `edifice_name` varchar(20) default NULL,
  `cell` char(1) default NULL,
  `layer` int(11) default NULL,
  `house_name` varchar(20) default NULL,
  `house_address` varchar(100) default NULL,
  `house_type` varchar(10) default NULL,
  `buildnum` decimal(10,2) default NULL,
  `house_orientation` varchar(20) default NULL,
  `pool_area` decimal(10,2) default NULL,
  `layer_type` varchar(50) default NULL,
  `owner_name` varchar(50) default NULL,
  `sex` char(2) default NULL,
  `age` varchar(10) default NULL,
  `job` varchar(200) default NULL,
  `birthday` varchar(20) default NULL,
  `paper_num` varchar(100) default NULL,
  `paper_type` varchar(100) default NULL,
  `phone` varchar(50) default NULL,
  `mob_tel` varchar(50) default NULL,
  `familymember` varchar(200) default NULL,
  `is_sale` varchar(50) default NULL,
  `occupancy_type` varchar(50) default NULL,
  `housetype_sub2` varchar(50) default NULL,
  `in_date` datetime default NULL,
  `remark` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_basedata_house
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_basedata_housemeter`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_housemeter`;
CREATE TABLE `tb_basedata_housemeter` (
  `id` int(11) NOT NULL auto_increment,
  `owner_name` varchar(10) default NULL,
  `meter_type` varchar(10) default NULL,
  `now_record` int(11) default NULL,
  `is_now` tinyint(3) unsigned default NULL,
  `begin_time` datetime default NULL,
  `end_time` datetime default NULL,
  `change_name` varchar(30) default NULL,
  `change_reason` varchar(500) default NULL,
  `change_time` datetime default NULL,
  `house` tinytext,
  `init_num` int(11) default NULL,
  `back_num` int(11) default NULL,
  `back_record` int(11) default NULL,
  `times` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_basedata_housemeter
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_basedata_houseproblem`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_houseproblem`;
CREATE TABLE `tb_basedata_houseproblem` (
  `id` int(11) NOT NULL auto_increment,
  `charge_name` tinytext,
  `detail` tinytext,
  `owe_money` double NOT NULL,
  `owner_name` tinytext,
  `pay_money` int(11) default NULL,
  `problem_type` tinytext,
  `resolve_name` tinytext,
  `resolve_time` tinytext,
  `submit_name` tinytext,
  `submit_time` datetime default NULL,
  `total_money` int(11) default NULL,
  `house` tinytext,
  `is_resolve` tinytext,
  `house_charge_quarter_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_basedata_houseproblem
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_basedata_houseproperty`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_houseproperty`;
CREATE TABLE `tb_basedata_houseproperty` (
  `id` int(11) NOT NULL auto_increment,
  `area_name` tinytext,
  `build_num` tinytext,
  `house_name` tinytext,
  `house_place` tinytext,
  `property_owner` tinytext,
  `use_for` tinytext,
  `user_name` tinytext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_basedata_houseproperty
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_basedata_owner`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_owner`;
CREATE TABLE `tb_basedata_owner` (
  `id` int(11) NOT NULL auto_increment,
  `account` double default NULL,
  `age` tinytext,
  `ammeter` int(11) default NULL,
  `birthday` tinytext,
  `in_date` datetime default NULL,
  `job` tinytext,
  `mob_tel` tinytext,
  `mob_tel1` varchar(50) default NULL,
  `occupancy_type` varchar(50) default NULL,
  `fee_date` datetime default NULL,
  `out_date` datetime default NULL,
  `owner_name` tinytext,
  `paper_num` tinytext,
  `paper_type` tinytext,
  `phone` tinytext,
  `remark` tinytext,
  `sex` tinytext,
  `water_meter` int(11) default NULL,
  `house` tinytext,
  `is_enter` tinyint(3) unsigned default NULL,
  `dtype` varchar(31) default NULL,
  `is_del` tinyint(3) unsigned default NULL,
  `is_sale` tinytext,
  `housetype_sub2` tinytext,
  `buildnum` double default NULL,
  `detail` tinytext,
  `house_use` tinytext,
  `pay_type` tinytext,
  `per` double default NULL,
  `quite_date` datetime default NULL,
  `housetype` tinytext,
  `new_owner_name` tinytext,
  `price` double default NULL,
  `next_name` tinytext,
  `new_in_date` datetime default NULL,
  `newout_date` datetime default NULL,
  `business_license` varchar(50) default NULL,
  `registration_certificate` varchar(50) default NULL,
  `valid` datetime default NULL,
  `familymember` tinytext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_basedata_owner
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_basedata_owner_derorate`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_owner_derorate`;
CREATE TABLE `tb_basedata_owner_derorate` (
  `id` int(11) NOT NULL auto_increment,
  `decoratecompany` tinytext,
  `decorateindate` datetime default NULL,
  `decorateoutdate` datetime default NULL,
  `in_date` datetime default NULL,
  `owner_name` tinytext,
  `house` tinytext,
  `decoratetel` varchar(50) default NULL,
  `decoratesxbl` tinytext,
  `decoratecontributions` varchar(50) default NULL,
  `decoratenotes` tinytext,
  `decorate_company` tinytext,
  `decoratein_date` datetime default NULL,
  `decorateout_date` datetime default NULL,
  `decorate_contributions` tinytext,
  `decorate_notes` tinytext,
  `decorate_sxbl` tinytext,
  `decorate_tel` tinytext,
  `housedz` tinytext,
  `area_id` int(11) default NULL,
  `house_id` varchar(50) default NULL,
  `deposit` varchar(50) default NULL,
  `pass_fee` varchar(50) default NULL,
  `is_return` varchar(50) default NULL,
  `status` varchar(50) default NULL,
  `is_damage` varchar(50) default NULL,
  `compensation` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_basedata_owner_derorate
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_basedata_owner_move`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_owner_move`;
CREATE TABLE `tb_basedata_owner_move` (
  `id` int(11) NOT NULL auto_increment,
  `carry_date` tinytext,
  `carry_name` tinytext,
  `carry_number` tinytext,
  `carry_phone` tinytext,
  `house_address` tinytext,
  `overdue` tinytext,
  `owner_name` tinytext,
  `owner_opinion` tinytext,
  `release_pass` tinytext,
  `house_id` tinytext,
  `area_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_basedata_owner_move
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_basedata_owner_move_apply`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_owner_move_apply`;
CREATE TABLE `tb_basedata_owner_move_apply` (
  `id` int(11) NOT NULL auto_increment,
  `build_num` tinytext,
  `carry_date` tinytext,
  `carry_time` tinytext,
  `cash_before` tinytext,
  `cash_current` tinytext,
  `cash_current_car_fee` tinytext,
  `cash_current_else_fee` tinytext,
  `cash_current_manage_fee` tinytext,
  `cash_current_meter_fee` tinytext,
  `cash_current_water_fee` tinytext,
  `cash_total_fee` tinytext,
  `coordinatewith` tinytext,
  `handle1` tinytext,
  `handle2` tinytext,
  `handle3` tinytext,
  `handle4` tinytext,
  `handle5` tinytext,
  `house_address` tinytext,
  `house_id` tinytext,
  `materials` tinytext,
  `meter_degree` tinytext,
  `meter_num` tinytext,
  `nature` tinytext,
  `owner_name` tinytext,
  `owner_opinion` tinytext,
  `owner_phone` tinytext,
  `safe_center_opinion` tinytext,
  `service_center_opinion` tinytext,
  `water_degree` tinytext,
  `water_num` tinytext,
  `carry_time_hour` tinytext,
  `area_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_basedata_owner_move_apply
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_basedata_projectsituation`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_projectsituation`;
CREATE TABLE `tb_basedata_projectsituation` (
  `id` int(11) NOT NULL auto_increment,
  `area_name` tinytext,
  `level` tinytext,
  `report_num` tinytext,
  `item_name` tinytext,
  `report_num_add` tinytext,
  `report_num_authorized` tinytext,
  `report_num_sub` tinytext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_basedata_projectsituation
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_basedata_tenancy_agreement`
-- ----------------------------
DROP TABLE IF EXISTS `tb_basedata_tenancy_agreement`;
CREATE TABLE `tb_basedata_tenancy_agreement` (
  `id` int(11) NOT NULL auto_increment,
  `enter_date` datetime default NULL,
  `house_use` tinytext,
  `housetype` tinytext,
  `in_date` datetime default NULL,
  `out_date` datetime default NULL,
  `owner_name` tinytext,
  `quit_date` datetime default NULL,
  `tenancy_name` tinytext,
  `house` tinytext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_basedata_tenancy_agreement
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chgelseaccount`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chgelseaccount`;
CREATE TABLE `tb_chgelseaccount` (
  `id` int(11) NOT NULL auto_increment,
  `area_name` tinytext,
  `charge_name` tinytext,
  `closing_balance` double NOT NULL,
  `current_fact_balance` double NOT NULL,
  `current_ought_balance` double NOT NULL,
  `gathering_date` datetime default NULL,
  `initial_balance` double NOT NULL,
  `record_month` tinytext,
  `submit_date` datetime default NULL,
  `submit_person` tinytext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chgelseaccount
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_advance`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_advance`;
CREATE TABLE `tb_chg_advance` (
  `id` int(11) NOT NULL auto_increment,
  `antimoney` decimal(10,2) NOT NULL,
  `money` decimal(10,2) NOT NULL,
  `record_time` datetime default NULL,
  `user_name` tinytext,
  `charge_base_data` int(11) default NULL,
  `house` tinytext,
  `use_money` decimal(10,2) default NULL,
  `record_year` varchar(50) default NULL,
  `begin_time` datetime default NULL,
  `end_time` datetime default NULL,
  `type` varchar(50) default NULL,
  `remark` longtext,
  `house_id` tinytext,
  `owner_name` tinytext,
  `big_type` tinytext,
  `area_id` int(11) default NULL,
  `charge_id` int(11) default NULL,
  `charge_name` varchar(20) default NULL,
  `other_name` varchar(20) default NULL,
  `bh` varchar(30) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_advance
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_advance_detail`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_advance_detail`;
CREATE TABLE `tb_chg_advance_detail` (
  `id` int(11) NOT NULL auto_increment,
  `area_id` int(11) default NULL,
  `house_id` varchar(50) default NULL,
  `owner_name` varchar(20) default NULL,
  `ought_money` decimal(8,1) default NULL,
  `antimoney` decimal(8,1) default NULL,
  `record_month` datetime default NULL,
  `charge_id` int(11) default NULL,
  `charge_name` varchar(20) default NULL,
  `big_type` varchar(20) default NULL,
  `remark` varchar(200) default NULL,
  `user_name` varchar(20) default NULL,
  `submit_time` datetime default NULL,
  `bh` varchar(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_advance_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_allmeter`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_allmeter`;
CREATE TABLE `tb_chg_allmeter` (
  `id` int(11) NOT NULL auto_increment,
  `init_record` int(11) default NULL,
  `is_all` varchar(10) default NULL,
  `last_record` int(11) default NULL,
  `local` varchar(200) default NULL,
  `meter_code` varchar(20) default NULL,
  `meter_codelocal` varchar(20) default NULL,
  `chargebasedata_id` varchar(20) default NULL,
  `meter_name` varchar(30) default NULL,
  `meter_type` varchar(10) default NULL,
  `assign_area` mediumtext,
  `assign_area_name` mediumtext,
  `assign_type` char(1) default NULL,
  `price_value` decimal(10,4) NOT NULL,
  `state` varchar(50) default NULL,
  `times` double NOT NULL,
  `unit` varchar(50) default NULL,
  `use_meter_type` tinytext,
  `area_id` int(11) default NULL,
  `area_name` varchar(50) default NULL,
  `change_name` varchar(50) default NULL,
  `change_time` datetime default NULL,
  `change_reason` varchar(200) default NULL,
  `begin_time` datetime default NULL,
  `change_id` int(11) default NULL,
  `gather_type` varchar(50) default NULL,
  `remark` mediumtext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_allmeter
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_allmeter_record`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_allmeter_record`;
CREATE TABLE `tb_chg_allmeter_record` (
  `id` int(11) NOT NULL auto_increment,
  `check_status` varchar(50) default NULL,
  `is_all` varchar(10) default NULL,
  `last_record` int(11) default NULL,
  `local` varchar(200) default NULL,
  `allmeter_id` int(11) default NULL,
  `meter_code` varchar(20) default NULL,
  `meter_codelocal` varchar(20) default NULL,
  `meter_name` varchar(20) default NULL,
  `meter_type` varchar(10) default NULL,
  `now_record` int(11) default NULL,
  `assign_area` longtext,
  `assign_type` char(1) default NULL,
  `price_value` decimal(10,4) NOT NULL,
  `record_month` datetime default NULL,
  `record_name` varchar(20) default NULL,
  `record_time` datetime default NULL,
  `submit_name` varchar(20) default NULL,
  `submit_time` datetime default NULL,
  `times` double NOT NULL,
  `total_money` decimal(10,2) NOT NULL,
  `unit` varchar(30) default NULL,
  `use_meter_type` varchar(50) default NULL,
  `use_nums` int(11) default NULL,
  `is_now` tinyint(3) unsigned default NULL,
  `gather_type` varchar(50) default NULL,
  `area_id` int(11) default NULL,
  `area_name` varchar(50) default NULL,
  `change_nums` int(11) default NULL,
  `chargebasedata_id` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_allmeter_record
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_assign`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_assign`;
CREATE TABLE `tb_chg_assign` (
  `id` smallint(6) NOT NULL auto_increment,
  `charge_name` varchar(50) default NULL,
  `area_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_assign
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_audit`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_audit`;
CREATE TABLE `tb_chg_audit` (
  `id` int(11) NOT NULL auto_increment,
  `area_id` int(11) default NULL,
  `begin_date` datetime default NULL,
  `end_date` datetime default NULL,
  `sum_money` varchar(20) default NULL,
  `audit_status` varchar(20) default NULL,
  `user_name` varchar(20) default NULL,
  `record_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_audit
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_basedata`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_basedata`;
CREATE TABLE `tb_chg_basedata` (
  `id` int(11) NOT NULL,
  `charge_expression` varchar(50) default NULL,
  `charge_name` varchar(30) default NULL,
  `charge_type` varchar(30) default NULL,
  `exp_explain` varchar(50) default NULL,
  `is_user` varchar(20) default NULL,
  `meter_type` varchar(10) default NULL,
  `other_name` varchar(30) default NULL,
  `price_unit` varchar(30) default NULL,
  `price_value` decimal(10,4) NOT NULL,
  `explain` varchar(300) default NULL,
  `charge_explain` varchar(200) default NULL,
  `begin_time` datetime default NULL,
  `end_time` datetime default NULL,
  `change_id` int(11) default NULL,
  `area` int(11) default NULL,
  `area_name` varchar(50) default NULL,
  `area_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_basedata
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_begindata`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_begindata`;
CREATE TABLE `tb_chg_begindata` (
  `id` int(11) NOT NULL auto_increment,
  `area_id` int(11) default NULL,
  `arrearage_money` decimal(9,2) default NULL,
  `charge_name` tinytext,
  `charge_price` decimal(5,2) default NULL,
  `house_type` tinytext,
  `ought_money` decimal(9,2) default NULL,
  `ought_pay_date` datetime default NULL,
  `pay_money` decimal(9,2) default NULL,
  `record_month_begin` datetime default NULL,
  `record_month_end` datetime default NULL,
  `months` int(11) default NULL,
  `house_id` varchar(50) default NULL,
  `owner_name` varchar(50) default NULL,
  `charge_id` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_begindata
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_deferredtax`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_deferredtax`;
CREATE TABLE `tb_chg_deferredtax` (
  `id` int(11) NOT NULL auto_increment,
  `deferred_beg` decimal(9,2) default NULL,
  `deferred_end` decimal(9,2) default NULL,
  `deferredcredits_beg` decimal(9,2) default NULL,
  `deferredcredits_end` decimal(9,2) default NULL,
  `account_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_deferredtax
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_elseaccount`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_elseaccount`;
CREATE TABLE `tb_chg_elseaccount` (
  `id` int(11) NOT NULL auto_increment,
  `area_name` varchar(50) default NULL,
  `charge_name` varchar(50) default NULL,
  `initial_balance` decimal(9,2) default NULL,
  `current_ought_balance` decimal(9,2) default NULL,
  `current_fact_balance` decimal(9,2) default NULL,
  `closing_balance` decimal(9,2) default NULL,
  `gathering_date` datetime default NULL,
  `submit_date` datetime default NULL,
  `submit_person` varchar(50) default NULL,
  `accounting_subjects` varchar(50) NOT NULL,
  `record_month` tinytext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_elseaccount
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_equity`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_equity`;
CREATE TABLE `tb_chg_equity` (
  `id` int(11) NOT NULL auto_increment,
  `equity_beg` decimal(9,2) default NULL,
  `equity_end` decimal(9,2) default NULL,
  `paidincapital_beg` decimal(9,2) default NULL,
  `paidincapital_end` decimal(9,2) default NULL,
  `capitalsurplus_beg` decimal(9,2) default NULL,
  `capitalsurplus_end` decimal(9,2) default NULL,
  `earnedsurplus_beg` decimal(9,2) default NULL,
  `earnedsurplus_end` decimal(9,2) default NULL,
  `chest_beg` decimal(9,2) default NULL,
  `chest_end` decimal(9,2) default NULL,
  `undistributedprofits_beg` decimal(9,2) default NULL,
  `undistributedprofits_end` decimal(9,2) default NULL,
  `account_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_equity
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_express`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_express`;
CREATE TABLE `tb_chg_express` (
  `id` int(11) NOT NULL auto_increment,
  `recordMonth` varchar(50) default NULL,
  `chargeName` varchar(50) default NULL,
  `chargeId` varchar(50) default NULL,
  `calexpress` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_express
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_foreign_rent`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_foreign_rent`;
CREATE TABLE `tb_chg_foreign_rent` (
  `id` int(11) NOT NULL auto_increment,
  `area_id` int(11) default NULL,
  `area_name` tinytext,
  `arrearage_money` double NOT NULL,
  `contract_begin_date` datetime default NULL,
  `contract_end_date` datetime default NULL,
  `contract_id` tinytext,
  `contract_name` tinytext,
  `explanation` tinytext,
  `ought_money` decimal(8,2) NOT NULL,
  `pay_money` decimal(8,2) NOT NULL,
  `fact_money` decimal(8,2) default NULL,
  `water_ought_money` decimal(8,2) default NULL,
  `water_pay_money` decimal(8,2) default NULL,
  `water_fact_money` decimal(8,2) default NULL,
  `ammeter_ought_money` decimal(8,2) default NULL,
  `ammeter_pay_money` decimal(8,2) default NULL,
  `ammeter_fact_money` decimal(8,2) default NULL,
  `year` tinytext,
  `pay_name` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_foreign_rent
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_housecharge`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_housecharge`;
CREATE TABLE `tb_chg_housecharge` (
  `id` int(11) NOT NULL auto_increment,
  `back_record` int(11) default NULL,
  `now_record` int(11) default NULL,
  `charge_basedata` int(11) default NULL,
  `house` tinytext,
  `coefficient` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_housecharge
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_house_detail`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_house_detail`;
CREATE TABLE `tb_chg_house_detail` (
  `id` int(11) NOT NULL auto_increment,
  `area_id` int(11) default NULL,
  `charge_name` varchar(50) NOT NULL,
  `charge_price` decimal(10,4) NOT NULL,
  `charge_type` varchar(50) default NULL,
  `charge_unit` varchar(50) default NULL,
  `gathering_date` datetime default NULL,
  `last_record` int(11) default NULL,
  `now_record` int(11) default NULL,
  `ought_money` decimal(8,2) NOT NULL,
  `fact_money` decimal(8,2) NOT NULL,
  `arrearage_money` decimal(8,2) NOT NULL,
  `privilege_money` decimal(8,2) NOT NULL default '0.00',
  `privilege_reason` varchar(200) default NULL,
  `record_month` datetime NOT NULL,
  `user_num` int(11) default NULL,
  `house` varchar(20) default NULL,
  `house_type` varchar(20) default NULL,
  `owner_name` varchar(50) default NULL,
  `charge_id` varchar(15) default NULL,
  `pay_id` varchar(10) default NULL,
  `factlate_fee` double default '0',
  `late_fee` decimal(10,1) default '0.0',
  `manager_name` varchar(25) default NULL,
  `begin_date` datetime default NULL,
  `end_date` datetime default NULL,
  `carport_lease_id` int(11) default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_house_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_house_oncepay`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_house_oncepay`;
CREATE TABLE `tb_chg_house_oncepay` (
  `id` int(11) NOT NULL auto_increment,
  `house_id` varchar(50) default NULL,
  `owner_name` varchar(50) default NULL,
  `build_num` double default NULL,
  `charge_id` int(11) default NULL,
  `charge_name` varchar(50) default NULL,
  `other_name` varchar(50) default NULL,
  `price_value_unit` varchar(50) default NULL,
  `ought_money` decimal(10,2) default NULL,
  `fact_money` decimal(10,2) default NULL,
  `pay_money` decimal(10,2) default NULL,
  `arrearage_money` decimal(10,2) default NULL,
  `gathering_date` datetime default NULL,
  `remark` varchar(200) default NULL,
  `area_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_house_oncepay
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_intangibles`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_intangibles`;
CREATE TABLE `tb_chg_intangibles` (
  `id` int(11) NOT NULL auto_increment,
  `intangiblesassets_beg` decimal(9,2) default NULL,
  `intangiblesassets_end` decimal(9,2) default NULL,
  `intangibles_beg` decimal(9,2) default NULL,
  `intangibles_end` decimal(9,2) default NULL,
  `deferredassets_beg` decimal(9,2) default NULL,
  `deferredassets_end` decimal(9,2) default NULL,
  `account_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_intangibles
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_late_fee_rate`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_late_fee_rate`;
CREATE TABLE `tb_chg_late_fee_rate` (
  `id` int(11) NOT NULL auto_increment,
  `area_id` int(11) NOT NULL,
  `area_name` tinytext,
  `rate` decimal(9,4) NOT NULL,
  `houseid` varchar(50) default NULL,
  `houseaddress` varchar(150) default NULL,
  `owner` varchar(50) default NULL,
  `datetime` datetime default NULL,
  `money` decimal(18,4) default NULL,
  `allmoney` decimal(18,4) default NULL,
  `charge_big_type` varchar(50) default NULL,
  `charge_id` varchar(50) default NULL,
  `addmoney` decimal(18,4) default NULL,
  `editmoney` decimal(18,4) default NULL,
  `rq` varchar(50) default NULL,
  `chg_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_late_fee_rate
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_liabilities`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_liabilities`;
CREATE TABLE `tb_chg_liabilities` (
  `id` int(11) NOT NULL auto_increment,
  `liabilities_beg` decimal(9,2) default NULL,
  `liabilities_end` decimal(9,2) default NULL,
  `shorttermborrowings_beg` decimal(9,2) default NULL,
  `shorttermborrowings_end` decimal(9,2) default NULL,
  `notespayable_beg` decimal(9,2) default NULL,
  `notespayable_end` decimal(9,2) default NULL,
  `payable_beg` decimal(9,2) default NULL,
  `payable_end` decimal(9,2) default NULL,
  `accountsreceivedadvance_beg` decimal(9,2) default NULL,
  `accountsreceivedadvance_end` decimal(9,2) default NULL,
  `otherpayables_beg` decimal(9,2) default NULL,
  `otherpayables_end` decimal(9,2) default NULL,
  `wagespayable_beg` decimal(9,2) default NULL,
  `wagespayable_end` decimal(9,2) default NULL,
  `welfarecosts_beg` decimal(9,2) default NULL,
  `welfarecosts_end` decimal(9,2) default NULL,
  `taxpayable_beg` decimal(9,2) default NULL,
  `taxpayable_end` decimal(9,2) default NULL,
  `unpaidprofit_beg` decimal(9,2) default NULL,
  `unpaidprofit_end` decimal(9,2) default NULL,
  `othercontributions_beg` decimal(9,2) default NULL,
  `othercontributions_end` decimal(9,2) default NULL,
  `accruedexpenses_beg` decimal(9,2) default NULL,
  `accruedexpenses_end` decimal(9,2) default NULL,
  `yearlongtermliabilities_beg` decimal(9,2) default NULL,
  `yearlongtermliabilities_end` decimal(9,2) default NULL,
  `account_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_liabilities
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_liquidity`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_liquidity`;
CREATE TABLE `tb_chg_liquidity` (
  `id` int(11) NOT NULL auto_increment,
  `liquidity_beg` decimal(9,2) default NULL,
  `liquidity_end` decimal(9,2) default NULL,
  `monetarycapital_beg` decimal(9,2) default NULL,
  `monetarycapital_end` decimal(9,2) default NULL,
  `shortterminvestments_beg` decimal(9,2) default NULL,
  `shortterminvestments_end` decimal(9,2) default NULL,
  `notesreceivable_beg` decimal(9,2) default NULL,
  `notesreceivable_end` decimal(9,2) default NULL,
  `receivables_beg` decimal(9,2) default NULL,
  `receivables_end` decimal(9,2) default NULL,
  `bad_beg` decimal(9,2) default NULL,
  `bad_end` decimal(9,2) default NULL,
  `netaccountsreceivable_beg` decimal(9,2) default NULL,
  `netaccountsreceivable_end` decimal(9,2) default NULL,
  `prepaidaccounts_beg` decimal(9,2) default NULL,
  `prepaidaccounts_end` decimal(9,2) default NULL,
  `exporttaxrefundreceivable_beg` decimal(9,2) default NULL,
  `exporttaxrefundreceivable_end` decimal(9,2) default NULL,
  `subsidypaymentsreceivable_beg` decimal(9,2) default NULL,
  `subsidypaymentsreceivable_end` decimal(9,2) default NULL,
  `otherreceivables_beg` decimal(9,2) default NULL,
  `otherreceivables_end` decimal(9,2) default NULL,
  `inventory_beg` decimal(9,2) default NULL,
  `inventory_end` decimal(9,2) default NULL,
  `deferredexpenses_beg` decimal(9,2) default NULL,
  `deferredexpenses_end` decimal(9,2) default NULL,
  `losscurrentassets_beg` decimal(9,2) default NULL,
  `losscurrentassets_end` decimal(9,2) default NULL,
  `longtermbondinvestments_beg` decimal(9,2) default NULL,
  `longtermbondinvestments_end` decimal(9,2) default NULL,
  `othercurrentassets_beg` decimal(9,2) default NULL,
  `othercurrentassets_end` decimal(9,2) default NULL,
  `account_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_liquidity
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_longterm_liabilities`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_longterm_liabilities`;
CREATE TABLE `tb_chg_longterm_liabilities` (
  `id` int(11) NOT NULL auto_increment,
  `longtermliabilities_beg` decimal(9,2) default NULL,
  `longtermliabilities_end` decimal(9,2) default NULL,
  `longtermborrowings_beg` decimal(9,2) default NULL,
  `longtermborrowings_end` decimal(9,2) default NULL,
  `tocopewithdebt_beg` decimal(9,2) default NULL,
  `tocopewithdebt_end` decimal(9,2) default NULL,
  `longtermpayables_beg` decimal(9,2) default NULL,
  `longtermpayables_end` decimal(9,2) default NULL,
  `otherlongtermliabilities_beg` decimal(9,2) default NULL,
  `otherlongtermliabilities_end` decimal(9,2) default NULL,
  `revolvingfund_beg` decimal(9,2) default NULL,
  `revolvingfund_end` decimal(9,2) default NULL,
  `account_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_longterm_liabilities
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_loss`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_loss`;
CREATE TABLE `tb_chg_loss` (
  `id` int(11) NOT NULL auto_increment,
  `business_fees` double default NULL,
  `finance_costs` double default NULL,
  `investment_income` double default NULL,
  `loss_month` int(11) default NULL,
  `loss_year` int(11) default NULL,
  `main_cost` double default NULL,
  `main_revenue` double default NULL,
  `operating_expenses` double default NULL,
  `operating_income` double default NULL,
  `operating_profit` double default NULL,
  `other_revenue` double default NULL,
  `overhead` double default NULL,
  `profit` double default NULL,
  `profit_adjustment` double default NULL,
  `subsidy_income` double default NULL,
  `taxes` double default NULL,
  `total_profit` double default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_loss
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_management_fee`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_management_fee`;
CREATE TABLE `tb_chg_management_fee` (
  `id` int(11) NOT NULL auto_increment,
  `car` decimal(9,2) default NULL,
  `communications` decimal(8,2) default NULL,
  `community` decimal(9,2) default NULL,
  `depreciation` decimal(9,2) default NULL,
  `finance` decimal(9,2) default NULL,
  `garbage` decimal(9,2) default NULL,
  `intangibles` decimal(9,2) default NULL,
  `labor` decimal(9,2) default NULL,
  `main_business` decimal(9,2) default NULL,
  `maintenance` decimal(9,2) default NULL,
  `manager_month` int(11) default NULL,
  `manager_year` int(11) default NULL,
  `material_consumption` decimal(9,2) default NULL,
  `office_expenses` decimal(9,2) default NULL,
  `operating` decimal(9,2) default NULL,
  `other` decimal(9,2) default NULL,
  `pension` decimal(9,2) default NULL,
  `taxes` decimal(9,2) default NULL,
  `travel` decimal(9,2) default NULL,
  `wage` decimal(9,2) default NULL,
  `water_electric` decimal(9,2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_management_fee
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_ownermeter_record`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_ownermeter_record`;
CREATE TABLE `tb_chg_ownermeter_record` (
  `id` int(11) NOT NULL auto_increment,
  `check_name` tinytext,
  `check_status` tinytext,
  `check_time` datetime default NULL,
  `house_id` varchar(50) default NULL,
  `last_record` int(11) default NULL,
  `now_record` int(11) default NULL,
  `back_record` int(11) default NULL,
  `times` int(11) NOT NULL,
  `use_nums` int(11) default NULL,
  `unit` tinytext,
  `total_money` decimal(8,2) NOT NULL,
  `meter_code` tinytext,
  `meter_name` tinytext,
  `meter_type` tinytext,
  `price_value` decimal(8,4) NOT NULL,
  `record_month` date default NULL,
  `record_name` tinytext,
  `record_time` datetime default NULL,
  `submit_name` tinytext,
  `submit_time` datetime default NULL,
  `is_now` tinyint(3) unsigned default NULL,
  `area_id` int(11) default NULL,
  `buildnum` decimal(8,2) default NULL,
  `cell` varchar(10) default NULL,
  `edifice_id` varchar(50) default NULL,
  `house_address` tinytext,
  `owner_name` tinytext,
  `charge_id` int(11) default NULL,
  `house_name` tinytext,
  `manager_men` tinytext,
  `change_num` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_ownermeter_record
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_paid_services`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_paid_services`;
CREATE TABLE `tb_chg_paid_services` (
  `id` int(11) NOT NULL auto_increment,
  `charge_id` int(11) default NULL,
  `price_value` decimal(10,2) NOT NULL,
  `is_use` tinytext,
  `explain` tinytext,
  `area_id` int(11) default NULL,
  `other_name` tinytext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_paid_services
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_pay_detail`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_pay_detail`;
CREATE TABLE `tb_chg_pay_detail` (
  `id` int(11) NOT NULL auto_increment,
  `charge_house_detail_id` int(11) default NULL,
  `house_id` varchar(20) default NULL,
  `pay_type` varchar(20) default NULL,
  `pay_money` decimal(8,2) default NULL,
  `record_month` datetime default NULL,
  `area_id` int(11) default NULL,
  `charge_id` int(11) default NULL,
  `gathering_time` datetime default NULL,
  `privilege_reason` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_pay_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_preview`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_preview`;
CREATE TABLE `tb_chg_preview` (
  `id` int(11) NOT NULL auto_increment,
  `record_month` datetime default NULL,
  `charge_big_type` varchar(50) default NULL,
  `charge_name` varchar(50) default NULL,
  `other_name` varchar(50) default NULL,
  `charge_price` decimal(10,4) default NULL,
  `charge_unit` varchar(50) default NULL,
  `last_record` int(11) default NULL,
  `now_record` int(11) default NULL,
  `user_num` int(11) default NULL,
  `ought_money` decimal(10,4) default NULL,
  `fact_money` decimal(10,4) default NULL,
  `pay_money` decimal(10,4) default NULL,
  `arrearage_money` decimal(10,4) default NULL,
  `gathering_date` datetime default NULL,
  `detail` varchar(50) default NULL,
  `charge_type` varchar(50) default NULL,
  `house_type` varchar(50) default NULL,
  `habitation_type` varchar(50) default NULL,
  `manager_men` varchar(50) default NULL,
  `arrearge_reason` varchar(50) default NULL,
  `is_realease` varchar(50) default NULL,
  `owner_name` varchar(50) default NULL,
  `house` varchar(50) default NULL,
  `area_id` int(11) default NULL,
  `charge_id` int(11) default NULL,
  `late_fee` decimal(10,4) default NULL,
  `is_release` tinytext,
  `manager_name` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_preview
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_release`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_release`;
CREATE TABLE `tb_chg_release` (
  `id` int(11) NOT NULL auto_increment,
  `count` int(11) default NULL,
  `delay_month` datetime default NULL,
  `delay_month_end` datetime default NULL,
  `explain` tinytext,
  `release_month` datetime default NULL,
  `release_month_end` datetime default NULL,
  `release_state` tinytext,
  `submit_name` tinytext,
  `submit_time` datetime default NULL,
  `charge_basedata` int(11) default NULL,
  `house` tinytext,
  `release_money` decimal(10,2) default NULL,
  `charge_name` varchar(50) default NULL,
  `type` varchar(50) default NULL,
  `mode` varchar(50) default NULL,
  `owner_name` varchar(50) default NULL,
  `big_type` tinytext,
  `remain_money` decimal(10,2) default NULL,
  `use_count` int(11) default NULL,
  `use_money` decimal(10,2) default NULL,
  `area_id` int(11) default NULL,
  `house_id` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_release
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_chg_user_pay_record`
-- ----------------------------
DROP TABLE IF EXISTS `tb_chg_user_pay_record`;
CREATE TABLE `tb_chg_user_pay_record` (
  `id` int(11) NOT NULL auto_increment,
  `area_id` int(11) default NULL,
  `bh` tinytext,
  `charge_house_detail_id` int(11) default NULL,
  `charge_name` tinytext,
  `fact_money` decimal(8,2) default NULL,
  `pay_type` tinytext,
  `record_month` datetime default NULL,
  `submit_time` datetime default NULL,
  `tax_num` tinytext,
  `user_name` tinytext,
  `house` varchar(20) default NULL,
  `pay_name` varchar(50) default NULL,
  `banci` varchar(50) default NULL,
  `reason` tinytext,
  `type` tinytext,
  `gathering_time` datetime default NULL,
  `charge_id` int(11) default NULL,
  `is_back` tinyint(3) unsigned default NULL,
  `return_money` decimal(9,2) default '0.00',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_chg_user_pay_record
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_community_culture`
-- ----------------------------
DROP TABLE IF EXISTS `tb_community_culture`;
CREATE TABLE `tb_community_culture` (
  `id` int(11) NOT NULL auto_increment,
  `eventtitle` tinytext,
  `type` varchar(50) default NULL,
  `place` tinytext,
  `jbtime` datetime default NULL,
  `organizers` tinytext,
  `sponsors` tinytext,
  `countdsj` tinytext,
  `xbdw` tinytext,
  `xbdwsm` varchar(50) default NULL,
  `staff` tinytext,
  `countpeople` varchar(50) default NULL,
  `yjnumber` varchar(50) default NULL,
  `sjnumber` varchar(50) default NULL,
  `yjfees` tinytext,
  `sjfees` tinytext,
  `equipment` tinytext,
  `people` tinytext,
  `creative` varchar(50) default NULL,
  `quality` varchar(50) default NULL,
  `influence` varchar(50) default NULL,
  `jlname` tinytext,
  `area_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_community_culture
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_company_events`
-- ----------------------------
DROP TABLE IF EXISTS `tb_company_events`;
CREATE TABLE `tb_company_events` (
  `id` int(11) NOT NULL auto_increment,
  `begin_time` varchar(50) default NULL,
  `events_name` longtext,
  `attend_content` varchar(200) default NULL,
  `address` varchar(200) default NULL,
  `company_id` varchar(50) default NULL,
  `company_name` varchar(100) default NULL,
  `areaId` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_company_events
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_comp_employee`
-- ----------------------------
DROP TABLE IF EXISTS `tb_comp_employee`;
CREATE TABLE `tb_comp_employee` (
  `id` int(11) NOT NULL auto_increment,
  `area_id` int(11) NOT NULL,
  `name` tinytext,
  `sjh` tinytext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_comp_employee
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_dx_recv_init`
-- ----------------------------
DROP TABLE IF EXISTS `tb_dx_recv_init`;
CREATE TABLE `tb_dx_recv_init` (
  `id` int(11) NOT NULL auto_increment,
  `area_id` int(11) default NULL,
  `code` tinytext,
  `detail` tinytext,
  `is_enable` tinytext,
  `recv_name` tinytext,
  `sjh` tinytext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_dx_recv_init
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_dx_send_init`
-- ----------------------------
DROP TABLE IF EXISTS `tb_dx_send_init`;
CREATE TABLE `tb_dx_send_init` (
  `id` int(11) NOT NULL auto_increment,
  `area_id` int(11) default NULL,
  `code` tinytext,
  `detail` tinytext,
  `is_enable` tinytext,
  `recv_name` tinytext,
  `sjh` tinytext,
  `company_id` varchar(10) default NULL,
  `company_name` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_dx_send_init
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_houseroompeper_area`
-- ----------------------------
DROP TABLE IF EXISTS `tb_houseroompeper_area`;
CREATE TABLE `tb_houseroompeper_area` (
  `id` int(11) NOT NULL auto_increment,
  `zxid` int(11) default NULL,
  `roomdz` tinytext,
  `rs` int(11) default NULL,
  `sgrq` datetime default NULL,
  `sgts` int(11) default NULL,
  `sgfzr` varchar(50) default NULL,
  `lxdh1` varchar(50) default NULL,
  `afry` tinytext,
  `lxdh2` varchar(50) default NULL,
  `name` varchar(50) default NULL,
  `gz` varchar(50) default NULL,
  `czzh` tinytext,
  `crzh` tinytext,
  `zhzh` tinytext,
  `sfzh` tinytext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_houseroompeper_area
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_individualsmreceived`
-- ----------------------------
DROP TABLE IF EXISTS `tb_individualsmreceived`;
CREATE TABLE `tb_individualsmreceived` (
  `sm_id` int(11) NOT NULL,
  `creatorid` tinytext,
  `dest_addr` tinytext,
  `droped` int(11) default NULL,
  `org_addr` tinytext,
  `org_addr_type` int(11) default NULL,
  `protocol_type` tinytext,
  `readed` int(11) default NULL,
  `recv_time` tinytext,
  `smtype` tinytext,
  `sm_content` tinytext,
  `serviceid` tinytext,
  PRIMARY KEY  (`sm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_individualsmreceived
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_long_messger`
-- ----------------------------
DROP TABLE IF EXISTS `tb_long_messger`;
CREATE TABLE `tb_long_messger` (
  `id` int(11) NOT NULL auto_increment,
  `area_name` tinytext,
  `edifice_name` tinytext,
  `group_id` tinytext,
  `house_name` tinytext,
  `meter` tinytext,
  `mob_tel` tinytext,
  `owner_name` tinytext,
  `wgf` tinytext,
  `worter` tinytext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_long_messger
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_long_messger_group`
-- ----------------------------
DROP TABLE IF EXISTS `tb_long_messger_group`;
CREATE TABLE `tb_long_messger_group` (
  `id` int(11) NOT NULL auto_increment,
  `is_enter` tinytext,
  `messger_name` tinytext,
  `read_time` datetime default NULL,
  `areaid` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_long_messger_group
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_payadvicerecord`
-- ----------------------------
DROP TABLE IF EXISTS `tb_payadvicerecord`;
CREATE TABLE `tb_payadvicerecord` (
  `id` int(11) NOT NULL auto_increment,
  `advicecount` int(11) default NULL,
  `area_id` int(11) default NULL,
  `area_name` tinytext,
  `cell` int(11) default NULL,
  `edifice_id` tinytext,
  `edifice_name` tinytext,
  `oughtpay_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_payadvicerecord
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_rate`
-- ----------------------------
DROP TABLE IF EXISTS `tb_rate`;
CREATE TABLE `tb_rate` (
  `id` int(11) NOT NULL auto_increment,
  `coefficient` decimal(10,5) default NULL,
  `cycle` varchar(50) default NULL,
  `type` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_rate
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_sms_recv`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sms_recv`;
CREATE TABLE `tb_sms_recv` (
  `id` int(11) NOT NULL auto_increment,
  `recv_time` datetime default NULL,
  `mobile` varchar(20) default NULL,
  `content` varchar(500) default NULL,
  `owner_name` varchar(20) default NULL,
  `house_id` varchar(20) default NULL,
  `is_del` char(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sms_recv
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_sms_send`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sms_send`;
CREATE TABLE `tb_sms_send` (
  `id` int(11) NOT NULL auto_increment,
  `send_time` datetime default NULL,
  `send_address` varchar(50) default NULL,
  `send_name` varchar(8000) default NULL,
  `content` varchar(2000) default NULL,
  `submit_time` datetime default NULL,
  `user_name` varchar(50) default NULL,
  `subid` varchar(10) default NULL,
  `state` varchar(50) default NULL,
  `is_del` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sms_send
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_suit_check`
-- ----------------------------
DROP TABLE IF EXISTS `tb_suit_check`;
CREATE TABLE `tb_suit_check` (
  `id` int(11) NOT NULL auto_increment,
  `check_name` varchar(50) default NULL,
  `edificeid` varchar(20) default NULL,
  `investigation_state` varchar(50) default NULL,
  `process_state` varchar(500) default NULL,
  `suit_date` date default NULL,
  `suit_department` varchar(80) default NULL,
  `suit_mess` varchar(800) default NULL,
  `suit_name` varchar(100) default NULL,
  `suit_type` varchar(100) default NULL,
  `suit_way` varchar(100) default NULL,
  `suitkid` varchar(20) default NULL,
  `visit_state` varchar(500) default NULL,
  `remark` varchar(600) default NULL,
  `area_id` int(11) default NULL,
  `house_id` varchar(20) default NULL,
  `suit_time` tinytext,
  `suit_date1` date default NULL,
  `suit_date2` date default NULL,
  `suit_date3` date default NULL,
  `suit_date4` date default NULL,
  `suittel` varchar(500) default NULL,
  `name1` varchar(500) default NULL,
  `name2` varchar(500) default NULL,
  `name3` varchar(500) default NULL,
  `name4` varchar(500) default NULL,
  `flow_state` char(1) default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_suit_check
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_suit_investigation`
-- ----------------------------
DROP TABLE IF EXISTS `tb_suit_investigation`;
CREATE TABLE `tb_suit_investigation` (
  `id` int(11) NOT NULL auto_increment,
  `invdate` tinytext,
  `invname` tinytext,
  `remark` tinytext,
  `suit_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_suit_investigation
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_suit_process`
-- ----------------------------
DROP TABLE IF EXISTS `tb_suit_process`;
CREATE TABLE `tb_suit_process` (
  `id` int(11) NOT NULL auto_increment,
  `pro_department` tinytext,
  `prodate` tinytext,
  `proname` tinytext,
  `remark` tinytext,
  `suit_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_suit_process
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_suit_visit`
-- ----------------------------
DROP TABLE IF EXISTS `tb_suit_visit`;
CREATE TABLE `tb_suit_visit` (
  `id` int(11) NOT NULL auto_increment,
  `satis` tinytext,
  `visitdate` tinytext,
  `visitname` tinytext,
  `visittype` tinytext,
  `remark` tinytext,
  `suit_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_suit_visit
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_sys_backup`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_backup`;
CREATE TABLE `tb_sys_backup` (
  `id` int(11) NOT NULL auto_increment,
  `backuptime` datetime default NULL,
  `description` tinytext,
  `filepath` tinytext,
  `operator` tinytext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sys_backup
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_sys_back_up`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_back_up`;
CREATE TABLE `tb_sys_back_up` (
  `id` int(11) NOT NULL auto_increment,
  `back_up_time` datetime default NULL,
  `description` tinytext,
  `file_path` tinytext,
  `operator` tinytext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sys_back_up
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_log`;
CREATE TABLE `tb_sys_log` (
  `id` int(11) NOT NULL auto_increment,
  `module_name` tinytext,
  `operator` tinytext,
  `work_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_sys_model`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_model`;
CREATE TABLE `tb_sys_model` (
  `id` int(11) NOT NULL,
  `belong_id` int(11) default NULL,
  `explain1` tinytext,
  `grade` int(11) default NULL,
  `icon_name` tinytext,
  `name` tinytext,
  `url` tinytext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sys_model
-- ----------------------------
INSERT INTO tb_sys_model VALUES ('110000', '-1', null, '0', null, '物业资源', '');
INSERT INTO tb_sys_model VALUES ('110100', '110000', null, '1', null, '小区资料', 'jsp/basedata/area-main.jsp');
INSERT INTO tb_sys_model VALUES ('110300', '110000', null, '1', null, '房间资料', 'jsp/basedata/cell-main.jsp');
INSERT INTO tb_sys_model VALUES ('110400', '110000', null, '1', null, '出入管理', 'jsp/basedata/ownermanager-main.jsp');
INSERT INTO tb_sys_model VALUES ('110500', '110000', null, '1', null, '车库管理', 'jsp/basedata/garage-main.jsp');
INSERT INTO tb_sys_model VALUES ('120000', '-1', null, '0', null, '收款管理', '');
INSERT INTO tb_sys_model VALUES ('120100', '120000', null, '1', null, '基础资料', '');
INSERT INTO tb_sys_model VALUES ('120101', '120100', null, '2', null, '收费项目设置', 'chargemanager/basedata!listquery.action');
INSERT INTO tb_sys_model VALUES ('120102', '120100', null, '2', null, '业户表资料设定', 'jsp/chargemanager/ownermeter/ownermeter-main.jsp');
INSERT INTO tb_sys_model VALUES ('120104', '120100', null, '2', null, '总表资料设定', 'jsp/chargemanager/allmeter/allmeter-main.jsp');
INSERT INTO tb_sys_model VALUES ('120105', '120100', null, '2', null, '收费客户档案', 'jsp/chargeowner/owner-main.jsp');
INSERT INTO tb_sys_model VALUES ('120108', '120100', null, '2', null, '费用导入', 'jsp/chargemanager/generate/manualgenerate-main.jsp');
INSERT INTO tb_sys_model VALUES ('120200', '120000', null, '1', null, '生成应收', '');
INSERT INTO tb_sys_model VALUES ('120202', '120200', null, '2', null, '业户表行度录入', 'chargemanager/ownermeter/ownermeterrecord!inputquery.action');
INSERT INTO tb_sys_model VALUES ('120203', '120200', null, '2', null, '业户表历史查询', 'chargemanager/ownermeter/ownermeterrecord!historyquery.action');
INSERT INTO tb_sys_model VALUES ('120205', '120200', null, '2', null, '总表行度录入', 'chargemanager/allmeter/allmeterrecord!inputquery.action');
INSERT INTO tb_sys_model VALUES ('120206', '120200', null, '2', null, '总表历史查询', 'chargemanager/allmeter/allmeterrecord!historyquery.action');
INSERT INTO tb_sys_model VALUES ('120207', '120200', null, '2', null, '生成常规类费用', 'chargemanager/generate/generate!resultFixedMoneyIndex.action');
INSERT INTO tb_sys_model VALUES ('120208', '120200', null, '2', null, '生成公摊类费用', 'chargemanager/generate/pubgenerate!resultMoneyIndex.action');
INSERT INTO tb_sys_model VALUES ('120300', '120000', null, '1', null, '实际收费', '');
INSERT INTO tb_sys_model VALUES ('120301', '120300', null, '2', null, '账务中心', 'jsp/chg/pay-main.jsp');
INSERT INTO tb_sys_model VALUES ('120303', '120300', null, '2', null, '指定费用录入', 'jsp/chg/assign-main.jsp');
INSERT INTO tb_sys_model VALUES ('120306', '120300', null, '2', null, '费用调整', 'jsp/chg/changemoney-main.jsp');
INSERT INTO tb_sys_model VALUES ('120400', '120000', null, '1', null, '查询统计', '');
INSERT INTO tb_sys_model VALUES ('120401', '124000', null, '2', null, '物业费年度统计', 'jsp/chg/report-yearMain.jsp');
INSERT INTO tb_sys_model VALUES ('120402', '120400', null, '2', null, '物业费月度统计', 'jsp/chg/report-monthMain.jsp');
INSERT INTO tb_sys_model VALUES ('120403', '120400', null, '2', null, '物业费明细', 'chg/report!perreport.action');
INSERT INTO tb_sys_model VALUES ('120404', '120400', null, '2', null, '物业费欠费统计', 'jsp/chg/report-arrearageMain.jsp');
INSERT INTO tb_sys_model VALUES ('120405', '120400', null, '2', null, '常规收费统计', 'chg/report!payyearlist.action');
INSERT INTO tb_sys_model VALUES ('120406', '120400', null, '2', null, '常规收费明细', 'chg/report!paydetail.action');
INSERT INTO tb_sys_model VALUES ('120407', '120400', null, '2', null, '前台收费统计', 'chg/report!collectreport.action');
INSERT INTO tb_sys_model VALUES ('130000', '-1', null, '0', null, '业主服务', null);
INSERT INTO tb_sys_model VALUES ('130100', '130000', null, '1', null, '公告发布', 'ser/bulletin-main.action');
INSERT INTO tb_sys_model VALUES ('130600', '130000', null, '1', null, '接收列表', 'ser/recvinit-main.action');
INSERT INTO tb_sys_model VALUES ('130700', '130000', null, '1', null, '批量发送', 'ser/bulletin-allmain.action');
INSERT INTO tb_sys_model VALUES ('131000', '130000', null, '1', null, '发送统计', 'ser/recvinit-fsmain.action');
INSERT INTO tb_sys_model VALUES ('210000', '-1', null, '0', null, '报修管理', '');
INSERT INTO tb_sys_model VALUES ('210200', '210000', null, '1', null, '新报修登记', 'repair/repair!add.action');
INSERT INTO tb_sys_model VALUES ('210300', '210000', null, '1', null, '待报修处理', 'repair/repair!list1.action');
INSERT INTO tb_sys_model VALUES ('210400', '210000', null, '1', null, '已完成处理', 'repair/repair!list2.action');
INSERT INTO tb_sys_model VALUES ('230000', '-1', null, '0', null, '投诉管理', '');
INSERT INTO tb_sys_model VALUES ('230100', '230000', null, '1', null, '新投诉登记', 'suit/suit!add.action');
INSERT INTO tb_sys_model VALUES ('230101', '230000', null, '1', null, '待处理投诉', 'suit/suit!list1.action');
INSERT INTO tb_sys_model VALUES ('230102', '230000', null, '1', null, '已处理投诉', 'suit/suit!list2.action');
INSERT INTO tb_sys_model VALUES ('230103', '230000', null, '1', null, '投诉统计', 'suit/suit!tsstat.action');
INSERT INTO tb_sys_model VALUES ('300000', '-1', null, '0', null, '统计报表', null);
INSERT INTO tb_sys_model VALUES ('300100', '300000', null, '1', null, '财务报表', null);
INSERT INTO tb_sys_model VALUES ('300101', '300100', null, '2', null, '物管费统计', 'report/jsp/charge/wgf_report.jsp');
INSERT INTO tb_sys_model VALUES ('300102', '300100', null, '2', null, '实收情况统计', 'report/jsp/charge/fact_report.jsp');
INSERT INTO tb_sys_model VALUES ('300103', '300100', null, '2', null, '欠费情况统计', 'report/jsp/charge/arrearage_report.jsp');
INSERT INTO tb_sys_model VALUES ('300200', '3000000', null, '1', null, '其他报表', null);
INSERT INTO tb_sys_model VALUES ('300201', '300200', null, '2', null, '投诉统计报表', 'report/jsp/complain/complain_report.jsp');
INSERT INTO tb_sys_model VALUES ('300202', '300200', null, '2', null, '报修统计报表', 'report/jsp/repair/repair_report.jsp');

-- ----------------------------
-- Table structure for `tb_sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_role`;
CREATE TABLE `tb_sys_role` (
  `id` int(11) NOT NULL auto_increment,
  `description` tinytext,
  `name` tinytext,
  `company_id` varchar(10) default NULL,
  `company_name` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sys_role
-- ----------------------------
INSERT INTO tb_sys_role VALUES ('1', '', '客服', 'b10001', '物业');

-- ----------------------------
-- Table structure for `tb_sys_role_models`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_role_models`;
CREATE TABLE `tb_sys_role_models` (
  `tb_sys_role` int(11) NOT NULL,
  `models` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sys_role_models
-- ----------------------------
INSERT INTO tb_sys_role_models VALUES ('1', '110000');
INSERT INTO tb_sys_role_models VALUES ('1', '110100');
INSERT INTO tb_sys_role_models VALUES ('1', '110300');
INSERT INTO tb_sys_role_models VALUES ('1', '110400');
INSERT INTO tb_sys_role_models VALUES ('1', '110500');
INSERT INTO tb_sys_role_models VALUES ('1', '120000');
INSERT INTO tb_sys_role_models VALUES ('1', '120100');
INSERT INTO tb_sys_role_models VALUES ('1', '120101');
INSERT INTO tb_sys_role_models VALUES ('1', '120105');
INSERT INTO tb_sys_role_models VALUES ('1', '120108');
INSERT INTO tb_sys_role_models VALUES ('1', '120200');
INSERT INTO tb_sys_role_models VALUES ('1', '120207');
INSERT INTO tb_sys_role_models VALUES ('1', '120300');
INSERT INTO tb_sys_role_models VALUES ('1', '120301');
INSERT INTO tb_sys_role_models VALUES ('1', '120303');
INSERT INTO tb_sys_role_models VALUES ('1', '120306');
INSERT INTO tb_sys_role_models VALUES ('1', '120400');
INSERT INTO tb_sys_role_models VALUES ('1', '120401');
INSERT INTO tb_sys_role_models VALUES ('1', '120402');
INSERT INTO tb_sys_role_models VALUES ('1', '120403');
INSERT INTO tb_sys_role_models VALUES ('1', '120404');
INSERT INTO tb_sys_role_models VALUES ('1', '120405');
INSERT INTO tb_sys_role_models VALUES ('1', '120406');
INSERT INTO tb_sys_role_models VALUES ('1', '210000');
INSERT INTO tb_sys_role_models VALUES ('1', '210200');
INSERT INTO tb_sys_role_models VALUES ('1', '210300');
INSERT INTO tb_sys_role_models VALUES ('1', '210400');
INSERT INTO tb_sys_role_models VALUES ('1', '230000');
INSERT INTO tb_sys_role_models VALUES ('1', '230100');
INSERT INTO tb_sys_role_models VALUES ('1', '230101');
INSERT INTO tb_sys_role_models VALUES ('1', '230102');
INSERT INTO tb_sys_role_models VALUES ('1', '230103');

-- ----------------------------
-- Table structure for `tb_sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user`;
CREATE TABLE `tb_sys_user` (
  `id` int(11) NOT NULL auto_increment,
  `department` tinytext,
  `password` tinytext,
  `user_code` tinytext,
  `user_description` tinytext,
  `user_name` tinytext,
  `role_id` int(11) default NULL,
  `managerarea` tinytext,
  `admin_type` char(1) default NULL,
  `company_id` varchar(10) default NULL,
  `company_name` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sys_user
-- ----------------------------
INSERT INTO tb_sys_user VALUES ('1', null, 'admin888', '0000', null, 'ffadmin', null, null, '0', 'b10001', '物业');

-- ----------------------------
-- Table structure for `tb_violation`
-- ----------------------------
DROP TABLE IF EXISTS `tb_violation`;
CREATE TABLE `tb_violation` (
  `id` int(11) NOT NULL auto_increment,
  `edificeid` int(11) default NULL,
  `follow` tinytext,
  `permission` tinytext,
  `vio_date` tinytext,
  `vio_mess` tinytext,
  `vio_name` tinytext,
  `vio_way` tinytext,
  `vioid` tinytext,
  `area_id` int(11) default NULL,
  `houseid` tinytext,
  `vio_type` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_violation
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_violation_type`
-- ----------------------------
DROP TABLE IF EXISTS `tb_violation_type`;
CREATE TABLE `tb_violation_type` (
  `id` int(11) NOT NULL auto_increment,
  `vio_type_name` tinytext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_violation_type
-- ----------------------------
