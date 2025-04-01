CREATE DATABASE IF NOT EXISTS people;
USE people;
 
DROP TABLE IF EXISTS users;
CREATE TABLE users (
  ID int(11) NOT NULL AUTO_INCREMENT,
  NAME varchar(50) DEFAULT NULL,
  AGE int(11) DEFAULT NULL,
  CITY varchar(50) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=4;
 
 
INSERT INTO users (ID,NAME,AGE,CITY) VALUES 
 (1,'Luis',25,'Madrid'),
 (2,'Juan',25,'Madrid'),
 (3,'Pedro',25,'Barcelona'),
 (4,'Rosa',25,'Madrid'),
 (5,'Candela',25,'Sevilla'),
 (6,'Marta',25,'Cádiz')
 
 
 ;