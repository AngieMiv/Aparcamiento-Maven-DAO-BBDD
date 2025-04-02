DROP DATABASE IF EXISTS dbcoches;
CREATE DATABASE dbcoches;
USE dbcoches;
DROP TABLE IF EXISTS dbcoches;
CREATE TABLE `dbcoches`.`coche` (
  `matricula` VARCHAR(16) NOT NULL,
  `modelo` VARCHAR(45) NULL,
  `color` VARCHAR(45) NULL,
  `year` INT NULL,
  PRIMARY KEY (`matricula`));
