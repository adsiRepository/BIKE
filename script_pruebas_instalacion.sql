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
insert into componentes values ('001','Marco/Cuadro'),('002','Aro/Rin'),('015','Radios'),
('020','Horquilla'),		('003','Manubrio'),				('151','Manubrio Playa'),	('004','Espigo'),
('005','Poste Delantero'),	('056','Codo'),					('037','Sillin'),			('016','Caña'),
('043','Pedales'),			('009','Compact Disc'),			('010','Piñon'),			('023','Pacha'),
('011','Cadena'),			('021','Cadenilla'),			('012','Freno'),			('013','Cambios'),
('099','Tensor'),			('014','Neumaticos'),			('046','Protectores'),		('050','Llantas'),
('006','Eje Centro'),		('018','Copas Centro'),			('423','Juego Frente'),		('030','Seguro Sillin'),
('026','Biela BMX'),		('022','Juego Manzanas'),		('024','Manzana Delantera'),('031','Manzana Trasera'),
('048','Triplato'),			('049','Relacion Cuadrante'),	('051','Relacion Cuña');
select * from componentes;

SET SQL_SAFE_UPDATES=0;/*hay que poner esta linea en el sql del programa*/

desc repuestos;
alter table repuestos change marca marca char(2) not null;
/*pagina catalogo => http://habicicletas.com/iframe.php?pag=bicicletas2.html*/
insert into repuestos values ('305337','CD MXR 12" 28D Negro','009',15,'R'),
('305140','28x89 Negra Cuadrante','049',15,'SH'),
('305309','BMX Aluminio PRO4-A530 Profnal Negro','009',15,'GW'),
('305126','Relacion 28x89mm Cuña, Biela Reforzada','051',15,'GW'),
('305451','Acero 24/34/42T-Biela Alum','048',15,'MI'),
('305334','Acero 36T 314-1 (Estrella)','009',15,'CH'),
('305438','Biela Acero Plastificada S/P','048',15,'MI'),
('305408','Biela Acero Plastificada C/P','048',15,'MI'),
('305469','Acero XCC-T208 28/38/48','048',15,'ST'),
('104106','3 Piezas Negro','018',15,'ES'),
('104104','3 Piezas Cromo','018',15,'PH'),
('104204','3 Piezas Rosca Inglesa','018',15,'NE'),
('104416','AHead Acero 1-1/8 CC845 Negro','423',15,'NE'),
('105306','Acero Negro','423',15,'MI'),
('105107','Acero FP501 Cromado','423',15,'MI'),
('105101','Acero CC800 Cromado','423',15,'NE'),
('301101','1/2 X 1/8 114L','011',15,'FV'),
('301121','1/2 X 1/8 114L C410','011',15,'MY'),
('301219','1/2 X 3/32 C30','021',15,'KT'),
('301206','1/2 X 3/32 HP20','021',15,'KT'),
('302104','Centro Y-5 36-54-46','006',15,'CH'),
('302209','Eje Cuadrante Tuercas','006',15,'CH'),
('302210','Eje Cuadrante Tornillos','006',15,'CH'),
('302411','Cartucho Acero Negro 910BK','006',15,'NE'),
('303326','Niño PVC Negro','043',15,'MI'),
('303323','BMX PVC FP-808 Negro','043',15,'FN'),
('303321','BMX FreeStyle Alum FP-965 Natural','043',15,'FN'),
('303414','MTB PVC F-815 Negro','043',15,'FN'),
('303402','MTB Aluminio','043',15,'FN'),
('304444','6 Vel 14-28 MTB Marron','023',15,'MI'),
('304452','6 Vel 14-34 MTB MegaRange','023',15,'SH'),
('304131','16 Dientes Marron','010',15,'PW'),
('304107','18 Dientes Marron','010',15,'PW'),
('304124','20 Dientes Marron','010',15,'PW'),
('304119','16 Dientes Cromado','010',15,'MI'),
('600382','Acero Lancer FreeStyle Disc/V-Brake','001',15,'GW'),
('305133','OPC-101 89x16mm Negra','026',15,''),
('305136','OPC-101 114x16mm Negra','026',15,''),
('306424','MTB Palanca Resina','013',15,'MI'),
('306423','MTB Palanca Resina','013',15,'PW'),
('307425','MTB RD-25','013',15,'PW'),
('403436','Acero C/Eje C/Espaciador','022',15,'PW'),
('403401','Acero C/Puntilla C/Espaciador','022',15,'CH'),
('403439','Acero Parallex C/Eje S/Espaciador','031',15,'PW'),
('403438','Acero Parallex C/Puntilla C/Espaciador','031',15,'CH'),
('403343','BMX A209 Eje 3/8 Alum Natural','022',15,'GW'),
('403431','BMX A222 Eje 3/8 Alum Negro Balinera','022',15,'GW'),
('403435','Acero Parallex C/Eje Cromadas','022',15,'PW'),
('403334','MTB A201 Eje 3/8 Alum Natural','022',15,'PW'),
('403345','MTB A203 C/Puntilla Alum Natural','031',15,'GW');
select * from repuestos;


desc repuesto_articulo;
insert into repuesto_articulo (articulo, plu_repuesto) values /*el siguiente orden en los registros no implica nada, solo es por visualizacion*/
('BMX','600382'),	('BSC','305140'),	('MTB','305451'),	('AMT','104416'),
('BMX','305337'),						('MTB','302411'),	('AMT','302411'),
('BMX','305309'),						('MTB','301206'),	('AMT','301206'),
('BMX','303321'),						('MTB','403401'),	('AMT','403401'),
('BMX','305334'),                       ('MTB','403334'),	('AMT','403334'),
                                        ('MTB','403345'),	('AMT','403345'),
					('BSC','305126'),	('MTB','305438'),					
					('BSC','104106'),	('MTB','104106'),
					('BSC','104104'),	('MTB','104104'),
					('BSC','104204'),	('MTB','104204'),
('BMX','105306'),	('BSC','105306'),	('MTB','105306'),
('BMX','105107'),   ('BSC','105107'),	('MTB','105107'),
('BMX','105101'),   ('BSC','105101'),	('MTB','105101'),
					('BSC','302209'),	('MTB','302209'),
                    ('BSC','302210'),	('MTB','302210'),
										('MTB','403436'),
                                        ('MTB','403438'),
('BMX','301101'),   ('BSC','301101'),
('BMX','301121'),   ('BSC','301121'),
('BMX','304131'),   ('BSC','304131'),
('BMX','304107'),   ('BSC','304107'),
('BMX','304124'),   ('BSC','304124'),
('BMX','304119'),   ('BSC','304119'),
('BMX','403435'),   ('BSC','403435'),
('BMX','403439'),   ('BSC','403439'),
					('BSC','302104'),	
										('MTB','305408'),
('BMX','303323'),						('MTB','305469'),
                    ('BSC','303326'),   ('MTB','301219'),
('BMX','600382'),						('MTB','303402'),
('BMX','305133'),                       ('MTB','304444'),
('BMX','305136'),                       ('MTB','304452'),
('BMX','403343'),						('MTB','306424'),
('BMX','403431'),                       ('MTB','306423'),
                                        ('MTB','307425'),
										('MTB','303414')
                                        
;


desc articulos;
insert into articulos (id_articulo, articulo, descripcion) values
('ARO','Ruedas/Rines','Componentes: Manzana, Aro, Radios.'),
('BSC','Bicicleta S/Cambios','Bicicleta de Relacion Fija, Cualquier tipo de Modelo y Tamaño'),
('MTB','MTB/Todo-Terreno','Bicicleta con Cambios, amplio rango, su categoria se basa en sus componentes.'),
('AMT','MTB Profesional o Semiprofesional, Bicicletas con componentes de Alta Gama.'),
('BMX','BMX','Bicicleta #20 o #16 de tipo BMX o Cross, Economica o Alta Gama.'),
('PLY','Playera','Direccion amplia o playera, postura erguida, por lo general para el género Femenino, utilitaria, con canasta y/o parrilla.'),
('TUR','Turismo','Bicicleta Monomarcha o de Cambios Internos, ruedas de 28", frenos mediante accionamiento de varillas, muy utilitaria.'),
('IMP','Importado','Articulo pre-ensamblado.'),
('A01','Otro Articulo');
rename table artefacto to articulos;
select * from articulos;


desc marcas;
alter table marcas change id_marca id_marca char(2);
insert into marcas values ('MI','MILLENIUM',null),('NE','NECO',null),('HI','SHIMANO',null),('R','RALEIGH',null),('SH','SH',null),
('GW','GW',null),('PH','PHILIPS',null),('ES','EASTMAN',null),('ST','SUNTOUR',null),('FN','FEIMIN',null),('FV','FIVESTAR',null),
('MY','MAYA',null),('KT','KMC-TEC',null),('KM','KMC',null),('CH','CHINO',null),('PW','POWER',null);
select * from marcas;
