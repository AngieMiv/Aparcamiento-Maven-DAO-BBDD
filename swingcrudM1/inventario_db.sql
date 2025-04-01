DROP DATABASE IF EXISTS inventario;
CREATE DATABASE inventario;

CREATE TABLE puesto_trabajo(
id 			int primary key auto_increment,
tipo 		int , 				-- Tipo : Sombremesa, Portatil, Tablet
label 		varchar(8),
sn 			varchar(32),
pn 			varchar(32),
monsn 		varchar(32),
keysn 		varchar(32),
mousn 		varchar(32),
modelo 		varchar(32),
monres		varchar(16),		-- Resolucion 1024x768 , 1920x1080
monint 		varchar(8),		-- Tipo de conexión VGA, HDMI, DPORT
ram 		int default 4,
hd1 		int default 0,
tipo1 		varchar(8),
hd2 		int default 0,
tipo2 		varchar(8),
ip  		varchar(24),
mask 		varchar(24),
os 			varchar(16),
lic 		varchar(32),
con 		varchar(1), 		-- C = cable , W = Wifi
ssid 		varchar(32)
);

CREATE TABLE usuario(
id 			int primary key auto_increment,
nummat 		varchar(16),
nombre 		varchar(16),
apellidos 	varchar(32),
dpto 		varchar(32)
);

-- Ubicaciones físicas en el centro
CREATE TABLE ubicacion(
id 			int primary key auto_increment,
planta 		int,
sala		int,
codsala 	varchar(8),
numEq		int default 0	-- Número de puestos
);

-- Ubicación física de los recursos 
CREATE TABLE puestos_ubicaciones(
id 			int primary key auto_increment,
idpuesto 	int,
idubicacion int,
fechaini	timestamp,
fechafin	timestamp

);
-- Asignación de recursos a personas
CREATE TABLE asginaciones(
id int primary key auto_increment,
idpuesto 	int,
idusuario 	int,
fechaini	timestamp,
fechafin	timestamp
);

-- Periféricos  para repuesto y asignados a equipos
CREATE TABLE perifericos (
id int primary key auto_increment,
tipo 		int,			-- 		0= teclado, 1=raton, 2=monitor, 3 impresora, 4 scanner
sn 			varchar(32),
marca		varchar(32),
ip			varchar(24) default null,
estado		int				-- 	0= sin asignar (almacén/laboratorio) , 1= asignado 
);