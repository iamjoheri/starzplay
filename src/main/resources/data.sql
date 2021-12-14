-- CREATE DATABASE /*!32312 IF NOT EXISTS*/`starzplay` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
--
-- USE `starzplay`;

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values
    (16);

/*Table structure for table `st_payment_method` */

DROP TABLE IF EXISTS `st_payment_method`;

CREATE TABLE `st_payment_method` (
                                     `payment_method_id` bigint(20) NOT NULL,
                                     `display_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                                     `payment_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                                     `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                                     PRIMARY KEY (`payment_method_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `st_payment_method` */

insert  into `st_payment_method`(`payment_method_id`,`display_name`,`payment_type`,`name`) values
                                                                                               (1,'credit card','CREDIT_CARD','credit card'),
                                                                                               (3,'Alfa Lebanon','MOBILE_CARRIER','_lb'),
                                                                                               (11,'Alfa Lebanon','MOBILE_CARRIER','_lb'),
                                                                                               (9,'Alberta','LAPTOP_CARRIER','_lbcc');

/*Table structure for table `st_payment_plan` */

DROP TABLE IF EXISTS `st_payment_plan`;

CREATE TABLE `st_payment_plan` (
                                   `payment_plan_id` bigint(20) NOT NULL,
                                   `currency` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                                   `duration` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                                   `gross_amount` double DEFAULT NULL,
                                   `net_amount` double DEFAULT NULL,
                                   `payment_method_id` bigint(20) DEFAULT NULL,
                                   `tax_amount` double DEFAULT NULL,
                                   PRIMARY KEY (`payment_plan_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `st_payment_plan` */

insert  into `st_payment_plan`(`payment_plan_id`,`currency`,`duration`,`gross_amount`,`net_amount`,`payment_method_id`,`tax_amount`) values
                                                                                                                                         (2,'USD','Month',5.99,5.99,1,0),
                                                                                                                                         (4,'USD','Month',5.99,5.99,3,0),
                                                                                                                                         (10,'USD','Month',5.99,5.99,9,0),
                                                                                                                                         (12,'USD','Month',5.99,5.99,11,0),
                                                                                                                                         (15,'EURO','Yearly',8.99,8.99,9,3);


