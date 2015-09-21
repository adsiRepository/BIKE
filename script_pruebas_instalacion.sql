/* FUENTES -> http://agcapa.es/mysql-comandos-basicos-consola-en-gnulinux/ */
show databases; /*mostrar todas las bases*/
use mysql;
show tables;

desc ensamblador;
use storebike;
desc ensambladores;
use pruebas;
drop database storebike;
insert into ensambladores values ('AQ4530','117657732','Sebastian','Muñoz','3173547440',
'Cra 29 no. 38-25','2015-01-07',null);
select * from ensambladores;

desc componentes;
alter table componentes change componente componente varchar(20) not null;
delete from componentes;
insert into componentes values ('001','Marco/Cuadro'),('002','Horquilla'),('003','Manubrio'),
('004','Espigo'),('005','Poste Delantero'),('006','Codo'),('007','Sillin'),('008','Caña'),('043','Pedales'),
('009','Plato/Relacion'),('010','Piñon'),('023','Pacha'),('011','Cadena'),('021','Cadenilla'),('012','Freno'),
('013','Cambios'),('014','Neumaticos'),('015','Llantas'),('006','Eje Centro'),('016','Copas Centro'),
('017','Copas Direccion'),('030','Seguro Sillin');
select * from componentes;

SET SQL_SAFE_UPDATES=0;/*hay que poner esta linea en el sql del programa*/

desc repuestos;
alter table repuestos change marca marca char(2) not null;
insert into repuestos values /*('305337','CD MXR 12" 28D Negro','009',15,'R'),
('305140','28x89 Negra Cuadrante','009',15,'SH'),
('305309','BMX Aluminio PRO4-A530 Profnal Negro','009',15,'GW'),
('305451','Acero 24/34/42T-Biela Alum','009',15,'MI'),
('305438','Biela Acero Plastificada S/P','009',15,'MI'),
('305408','Biela Acero Plastificada C/P','009',15,'MI'),
('305469','Acero XCC-T208 28/38/48','009',15,'ST'),
('104106','3 Piezas Negro','016',15,'ES'),
('104104','3 Piezas Cromo','016',15,'PH'),
('104204','3 Piezas Rosca Inglesa','016',15,'NE'),
('104416','AHead Acero 1-1/8 CC845 Negro','017',15,'NE'),
('105306','Acero Negro','017',15,'MI'),
('105107','Acero FP501 Cromado','017',15,'MI'),
('105101','Acero CC800 Cromado','017',15,'NE'),
('301101','1/2 X 1/8 114L','011',15,'FV'),
('301121','1/2 X 1/8 114L C410','011',15,'MY'),
('301219','1/2 X 3/32 C30','021',15,'KM'),
('301206','1/2 X 3/32 HP20','021',15,'KM'),*/
('302104','Centro Y-5 36-54-46','006',15,'CH'),
('302209','Eje Cuadrante Tuercas','006',15,'CH'),
('302210','Eje Cuadrante Tornillos','006',15,'CH'),
('302411','Cartucho Acero Negro 910BK','006',15,'NE'),
('303326','Niño PVC Negro','043',15,'MI'),
('303323','BMX PVC FP-808 Negro','043',15,'FN'),
('303321','BMX FreeStyle Alum FP-965 Natural','043',15,'FN'),
('303321','BMX FreeStyle Alum FP-965 Natural','043',15,'FN');

select * from repuestos;

desc artefactos;
insert into artefactos values ('A03','Ruedas/Rines'),('AB4','Tradicional S/C'),('AF7','Tradicional C/C'),
('A09','BMX'),('A14','MTB'),('A13','Turismo'),('A16','Semi-Carrera'),('AZ1','Carrera'),('A18','Importado'),('A20','Otro Articulo');
rename table artefacto to artefactos;
select * from artefactos;

desc marcas;
alter table marcas change id_marca id_marca char(2);
insert into marcas values ('MI','MILLENIUM',null),('NE','NECO',null),('HI','SHIMANO',null),('R','RALEIGH',null),('SH','SH',null),
('GW','GW',null),('PH','PHILIPS',null),('ES','EASTMAN',null),('ST','SUNTOUR',null),('FN','FEIMIN',null),('FV','FIVESTAR',null),
('MY','MAYA',null),('KM','KMC-TEC',null),('CH','CHINO',null);
select * from marcas;
