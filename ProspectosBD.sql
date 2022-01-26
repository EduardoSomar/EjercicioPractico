create database ProspectosSistema;

use ProspectosSistema;

 CREATE TABLE prospecto (
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(45),
primerApellido VARCHAR(45) NOT NULL,
segundoApellido VARCHAR(45),
calle VARCHAR(50) NOT NULL,
numero VARCHAR(5) NOT NULL,
colonia varchar(20) NOT NULL,
codigoPostal VARCHAR(6) NOT NULL,
telefono VARCHAR(11) NOT NULL,
rfc VARCHAR(45) NOT NULL,
estatus VARCHAR(11),
observaciones varchar(100),
PRIMARY KEY(id) 
); 
