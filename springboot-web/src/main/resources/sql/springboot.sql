/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.24 : Database - springboot
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`springboot` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `springboot`;

/*Table structure for table `spring_resource` */

DROP TABLE IF EXISTS `spring_resource`;

CREATE TABLE `spring_resource` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                   `name` varchar(50) NOT NULL COMMENT '资源名称',
                                   `url` varchar(50) NOT NULL COMMENT 'url',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `spring_resource` */

/*Table structure for table `spring_role` */

DROP TABLE IF EXISTS `spring_role`;

CREATE TABLE `spring_role` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `name` varchar(50) NOT NULL COMMENT '名称',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

/*Data for the table `spring_role` */

/*Table structure for table `spring_role_resource` */

DROP TABLE IF EXISTS `spring_role_resource`;

CREATE TABLE `spring_role_resource` (
                                        `role_id` bigint(20) NOT NULL COMMENT '角色主键',
                                        `resource_id` bigint(20) NOT NULL COMMENT '资源主键',
                                        PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源关系';

/*Data for the table `spring_role_resource` */

/*Table structure for table `spring_use_time_log` */

DROP TABLE IF EXISTS `spring_use_time_log`;

CREATE TABLE `spring_use_time_log` (
                                       `id` bigint(20) unsigned NOT NULL COMMENT '主键',
                                       `p_id` bigint(20) unsigned DEFAULT '0' COMMENT '父id',
                                       `class_name` varchar(200) DEFAULT NULL COMMENT '类名',
                                       `method_name` varchar(50) DEFAULT NULL COMMENT '方法名',
                                       `modifiers` int(10) unsigned DEFAULT NULL COMMENT '修饰符',
                                       `parameters` varchar(200) DEFAULT NULL COMMENT '方法参数',
                                       `return_type` varchar(100) DEFAULT NULL COMMENT '返回值类型',
                                       `start_time` datetime DEFAULT NULL COMMENT '开始时间',
                                       `use_time` int(11) unsigned DEFAULT NULL COMMENT '耗时(毫秒)',
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `spring_use_time_log` */

insert  into `spring_use_time_log`(`id`,`p_id`,`class_name`,`method_name`,`modifiers`,`parameters`,`return_type`,`start_time`,`use_time`) values (1586524551145032,1586524551145912,'com.ultra.service.impl.UserServiceImpl','list',1,'com.baomidou.mybatisplus.core.conditions.Wrapper<T> queryWrapper','java.util.List','2020-04-10 13:15:51',16),(1586524551145912,0,'com.ultra.web.UserController','list',1,NULL,'java.util.List','2020-04-10 13:15:51',16),(1586524551145976,1586524551145032,'com.ultra.dao.UserMapper','selectList',1025,'com.baomidou.mybatisplus.core.conditions.Wrapper<T> queryWrapper','java.util.List','2020-04-10 13:15:51',16),(1586524569507390,1586524551145976,NULL,NULL,NULL,NULL,NULL,'2020-04-10 13:16:10',NULL);

/*Table structure for table `spring_user` */

DROP TABLE IF EXISTS `spring_user`;

CREATE TABLE `spring_user` (
                               `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `username` varchar(50) NOT NULL COMMENT '用户名',
                               `password` varchar(218) NOT NULL COMMENT '密码',
                               `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
                               `address` varchar(100) DEFAULT NULL COMMENT '地址',
                               `age` int(11) unsigned DEFAULT NULL COMMENT '年龄',
                               `is_expired` tinyint(1) unsigned DEFAULT NULL COMMENT '是否过期 0否 1是',
                               `is_disabled` tinyint(1) unsigned DEFAULT NULL COMMENT '是否禁用 0否 1是',
                               `is_locked` tinyint(1) unsigned DEFAULT NULL COMMENT '是否锁定 0否 1是',
                               `note` varchar(100) DEFAULT NULL COMMENT '备注',
                               `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                               `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户';

/*Data for the table `spring_user` */

insert  into `spring_user`(`id`,`username`,`password`,`email`,`address`,`age`,`is_expired`,`is_disabled`,`is_locked`,`note`,`gmt_create`,`gmt_modified`) values (1,'admin','202cb962ac59075b964b07152d234b70','admin@foxmail.com','广州天河',24,0,0,0,'123',NULL,NULL),(2,'zhangsan','25d55ad283aa400af464c76d713c07ad','zhangsan@foxmail.com','广州越秀',26,0,0,0,'12345678',NULL,'2020-04-08 15:00:53'),(3,'zhaosi','81dc9bdb52d04dc20036dbd8313ed055','zhaosi@foxmail.com','广州海珠',25,0,1,0,'1234',NULL,NULL),(4,'wangwu','827ccb0eea8a706c4c34a16891f84e7b','wangwu@foxmail.com','广州番禺',27,1,0,0,'12345',NULL,NULL),(5,'boss','202cb962ac59075b964b07152d234b70','boss@foxmail.com','深圳',30,0,0,1,'123',NULL,NULL),(6,'admin_en','bfb194d5bd84a5fc77c1d303aefd36c3','huang.wenbin@foxmail.com','江门蓬江',24,0,0,0,'123',NULL,NULL),(7,'zhangsan_en','68ae075edf004353a0403ee681e45056','zhangsan@foxmail.com','深圳宝安',21,0,0,0,'12345678',NULL,NULL),(8,'wangwu_en','44b907d6fee23a552348eabf5fcf1ac7','wangwu@foxmail.com','佛山顺德',19,1,0,0,'1234',NULL,NULL),(9,'god','123','qwq','北京朝阳',10,0,0,0,'test','2020-04-08 15:31:11','2020-04-08 15:31:11');

/*Table structure for table `spring_user_role` */

DROP TABLE IF EXISTS `spring_user_role`;

CREATE TABLE `spring_user_role` (
                                    `user_id` bigint(20) NOT NULL COMMENT '用户主键',
                                    `role_id` bigint(20) NOT NULL COMMENT '角色主键',
                                    PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系';

/*Data for the table `spring_user_role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
