-- Create database if not exists
CREATE DATABASE IF NOT EXISTS retail_store_database;
USE retail_store_database;

-- Create tables
CREATE TABLE IF NOT EXISTS `employees` (
  `emp_id` varchar(10) NOT NULL,
  `emp_name` text,
  `emp_job` text,
  `emp_passw` text,
  PRIMARY KEY (`emp_id`)
);

CREATE TABLE IF NOT EXISTS  `initial_stocks` (
 `stock_id` varchar(10) NOT NULL,
  `stock_name` text,
  `stock_qntity` int DEFAULT NULL,
  `stock_price` int DEFAULT NULL,
  PRIMARY KEY (`stock_id`)
);

CREATE TABLE IF NOT EXISTS  `customers` (
 `cus_id` varchar(10) NOT NULL,
  `cus_name` text,
  `cus_phone` varchar(14) DEFAULT NULL,
  `cus_loyal` double DEFAULT NULL,
  PRIMARY KEY (`cus_id`)
);

CREATE TABLE IF NOT EXISTS  `staff_work_hours` (
 `nth_use` int NOT NULL,
  `cashier_id` varchar(10) DEFAULT NULL,
  `cashier_name` text,
  `date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `num_of_tra` int DEFAULT NULL,
  PRIMARY KEY (`nth_use`)
);

CREATE TABLE IF NOT EXISTS  `stocks` (
 `stock_id` varchar(10) NOT NULL,
  `stock_name` text,
  `stock_qntity` int DEFAULT NULL,
  `stock_price` int DEFAULT NULL,
  PRIMARY KEY (`stock_id`)
);

CREATE TABLE IF NOT EXISTS  `transactions` (
 `tra_number` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `bill_num` varchar(20) DEFAULT NULL,
  `tra_date` date DEFAULT NULL,
  `tra_time` time DEFAULT NULL,
  `cashier_id` varchar(10) DEFAULT NULL,
  `cashier_name` text,
  `tra_total_qty` int DEFAULT NULL,
  `cus_id` varchar(10) DEFAULT NULL,
  `cus_name` text,
  `sub_total` double DEFAULT NULL,
  `pay_method` varchar(7) DEFAULT NULL,
  `paid_amount` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`tra_number`)
);
