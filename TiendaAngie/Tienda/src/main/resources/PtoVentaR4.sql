DROP DATABASE IF EXISTS ptoventa ;
CREATE DATABASE ptoventa ;
USE ptoventa ;

-- -----------------------------------------------------
-- Table ptoventa.tienda
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ptoventa.tienda (
  nombre VARCHAR(16) PRIMARY KEY,
  direccion VARCHAR(45) NOT NULL,
  max_articulos INT NULL DEFAULT '100',
  max_vendedores INT NULL DEFAULT '10'
)
;
 
insert into ptoventa.tienda values('Drinks4Geeks','Wall Street,2457 789 NY',300,10);
insert into ptoventa.tienda values('FishAndChips','Wall Street,1024 668 NY',150,10);
-- -----------------------------------------------------
-- Table ptoventa.articulos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ptoventa.articulos (

  nombre VARCHAR(45) NOT NULL,
  marca VARCHAR(45) NOT NULL,
  tipo VARCHAR(24)  NOT NULL,
  stock INT NULL DEFAULT 0,
  precio DECIMAL(15,2) DEFAULT 0.00,
  idtienda VARCHAR(16) ,
  PRIMARY KEY (marca, nombre),
  CONSTRAINT fke1
    FOREIGN KEY (idtienda)
    REFERENCES ptoventa.tienda (nombre))
;

insert into ptoventa.articulos
  values('MO-Tropic','Monster','Bebidas',20,2.00,'Drinks4Geeks'),
		('MO-Flash','Monster','Bebidas',21,2.10,'Drinks4Geeks'),
		('RB-Classic','RedBull','Bebidas',22,2.20,'Drinks4Geeks'),
		('RB-Zero','RedBull','Bebidas',23,2.30,'Drinks4Geeks')  ,
		('CO-Light Coke','COKE','Bebidas',10,1.80,'FishAndChips'),
		('YE-Red Storm','YETI','Bebidas',24,2.20,'Drinks4Geeks'),
		('CO-Classic Coke','COKE','Bebidas',11,1.80,'FishAndChips'),
		('YE-Blue Sea','YETI','Bebidas',25,2.20,'Drinks4Geeks'),
		('CO-Cherry Coke','COKE','Bebidas',12,1.80,'FishAndChips'),
		('YE-White Cold','YETI','Bebidas',26,2.20,'Drinks4Geeks');

-- -----------------------------------------------------
-- Table ptoventa.vendedor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ptoventa.vendedor (
  matricula INT NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  apellidos VARCHAR(45) NULL DEFAULT NULL,
  ventas DOUBLE NULL DEFAULT '0',
  idtienda VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (matricula),
  CONSTRAINT fkv1
    FOREIGN KEY (idtienda)
    REFERENCES ptoventa.tienda (nombre))
;


