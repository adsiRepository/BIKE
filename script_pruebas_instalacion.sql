/* FUENTES -> http://agcapa.es/mysql-comandos-basicos-consola-en-gnulinux/ */
show databases; /*mostrar todas las bases*/

use mysql;/*mysql es la base de datos de configuraciones. En ella se guardan datos como los usuarios del servidor mysql.*/
select user from user;
/*CONFIGURACION DE USUARIOS Y ACCESO*/
create user 'user_storebike'@'localhost' identified by 'user_storebike'; /*CREAMOS EL USUARIO POR DEFECTO DEL PROGRAMA*/
grant all privileges on storebike.* to 'user_storebike'@'localhost'; /*LE OTORGAMOS TODAS LAS CREDENCIALES UNICAMENTE EN LA BASE DE DATOS QUE LE CONCIERNE A ESTE PROGRAMA*/
/*PARA OTORGAR PERMISOS DE ACCESO A USUARIOS DESDE OTRO EQUIPO DEBEMOS HACERLO CON LA SGTE LINEA:*/
grant all privileges on storebike.* to 'user_storebike'@'nombre_equipo_desde_donde_conecta'; /*EL USUARIO ES 'user_storebike' SIEMPRE PUES ES EL USUARIO DEFINIDO PARA Y EN EL PROGRAMA Y NO TIENE NECESIDAD DE ESTARSE CAMBIANDO*/
drop user 'user_storebike'@'localhost';
/*----*/

use world;
select * from city;

use storebike;
show tables;
drop database storebike;
drop table configuraciones;

SET SQL_SAFE_UPDATES=0;/*hay que poner esta linea en el sql del programa*/
set foreign_key_checks = 1;

desc ensambladores;
insert into ensambladores values 
('1107057722','Miguel','González','3173547440','Cra 29 no. 38-25','2015-01-07',null),
('1190375460','Jose','Ortiz','3114445566','Calle falsa 123','1990-07-10',null);
delete from ensambladores;
select * from ensambladores;

desc familia_componente;
insert into familia_componente values
('001','Marco/Cuadro',			'Componente principal de una Bicicleta.', false, true),
('002','Horquilla/Tenedor',		null,false, true),
('003','Manubrio',				null,false, true),
('004','Potencia/Codo',			'Hay dos tipos básicos de potencia según la forma de unirla al cuadro:
 las de tipo roscado y las no roscadas, siendo este último tipo conocido como sistema «ahead».',false, true),
('005','Rodamiento Frontal',	null,false, true),
('006','Tija/Caña',				null,false, true),
('007','Seguro Caña',			null,false, true),
('008','Sillín',				null,false, true),
('009','Centro',				null,false, true),
('010','Relación',				null,false, true),
('011','Pedal',					null,true,  true),
('012','Piñón',					null,false, true),
('013','Cadena',				null,false, true),
('014','Frenos',				null,false, true),
('015','Cambios',				null,false, true),
('016','Rines/Ruedas',			'Este componente consiste en un par de articulos no identicos.',true, true),
('017','Neumáticos',			null,true, true),
('018','Llantas',				null,true, true),
('019','Suplementos',			'Tornilleria, balineras; elementos suplementarios.',false, false),
('020','Accesorios',			null,false, false)
;
delete from familia_componente;
select * from familia_componente;

/** http://labicikleta.com/11-tipos-de-freno-para-bicicleta/ */
desc componentes;
/**la sigte linea sirve para cambiar el tipo de dato de un campo nada mas*/
alter table componentes change desc_comp desc_comp varchar(100);
delete from componentes;
insert into componentes (id_comp, componente, familia) values 
('081','Cuadro Suspensión','001'),	('001','Cuadro Tradicional','001'),		('883','Aro Sencillo','016'),				('015','Radios','016'),		
('557','Cuadro Ahead','001'),		('088','Cuadro BMX','001'),				('057','Cuadro Playero','001'),				('016','Caña/Tija 22.2','006'),
('020','Horquilla Roscada','002'),	('003','Manubrio MTB','003'),			('151','Manubrio Playero','003'),			('795','Manubrio Ahead','003'),
('244','Manubrio BMX','003'),		('095','Horquilla BMX','002'),			('019','Horquilla Ahead Suspensión','002'),
('004','Espigo MTB','004'),			('423','Copas Direccion Rosca','005'),	('222','Copas Direccion Ahead','005'),		('745','Horquilla Ahead','002'),
('005','Poste Delantero','003'),	('056','Codo MTB','003'),				('061','Codo BMX','003'),					('366','Abrazadera Marco','007'),
('043','Pedal 9/16','011'),			('009','Compact Disc','010'),			('010','Piñón','012'),						('023','Pacha','012'),
('045','Pedal 1/2','011'),			('038','Juego Freno Disco','014'),		('013','Juego de Cambios','015'),			('076','Horquilla de Suspensión Roscada','002'),
('084','Espigo BMX','003'),			('127','Juego de Centro BMX','009'),	('613','Desviador Delantero','015'),			
('011','Cadena','013'),				('021','Cadenilla','013'),				('037','Sillin','008'),						('361','Abrazadera Marco Ahead','007'),							
('099','Tensor','015'),				('856','Eje Centro Cuña','009'),		('885','Aro Doble Pared','016'),
('006','Eje Centro MTB','009'),		('018','Copas de Centro','009'),		('331','Mangos','019'),						('030','Tornillo Marco','007'),
('026','Biela Enteriza','009'),		('022','Juego Manzanas','016'),			('024','Manzana Delantera','016'),			('031','Manzana Trasera','016'),
('048','Triple-plato','010'),		('049','Relacion Cuadrante','010'),		('051','Relacion Cuña','010'),				('332','Cuña Plato','010'),
('014','Neumáticos','017'),			('046','Protector Rin','019'),			('050','Llanta','018')
;
insert into componentes values 
('844','Freno Cáliper','Toda las clases de freno de \'Herradura\'.','014'),
('975','Rin Sencillo','Rin sencillo para Bicicleta completamente ensamblado y listo para ser parte de una.','016'),
('976','Rin Doble Pared','','016'),
('843','Freno Cantilever','Todo tipo de freno de doble Pivote, entre ellos el V-brake.','014')
;
update componentes set desc_comp = 'Rines de Doble Pared.' where id_comp = '976';
delete from componentes;
select * from componentes c order by c.familia;

select c.id_comp, c.componente from componentes c inner join componente_articulo ca 
where ca.componente = c.id_comp and ca.articulo = 'MTB' order by c.familia;


desc articulos;
insert into articulos (id_articulo, articulo, descripcion) values 
/*('ARJ','Juego de Rines',		'Par de Ruedas o Rines con componentes agrupados.','016'),*/
('RIN',	'Par Rines Sencillos',	'Pareja de rines de hoja sencilla.'),
('RDP',	'Par Rines Doble Pared','Rines con Aros doble pared, trasero y delantero.'),
('BSC',	'Bicicleta S/Cambios',	'Bicicleta de Relacion Fija, Cualquier tipo de Modelo y Tamaño.'),
('MTB',	'MTB/Todo-Terreno',		'Bicicleta con Cambios, amplio rango, su categoria se basa en sus componentes.'),
('MTS',	'MTB Suspensión',		'Mountain Bike con Cuadro de Suspesión, esta clasificacion engloba cualquier tamaño.'),
('AMT',	'MTB Ahead',			'Downhill, Profesional o Semiprofesional; Bicicletas Ahead con componentes de Alta Gama.'),
('BMX',	'BMX o Cross',			'Bicicleta #20 o #16 de tipo BMX o Cross, Economica o Especial.'),
('PLY',	'Playera',				'Direccion amplia o playera, postura erguida, por lo general para el género Femenino, utilitaria, con canasta y/o parrilla.'),
('TUR',	'Turismo',				'Bicicleta Monomarcha o de Cambios Internos, ruedas de 28", frenos mediante accionamiento de varillas, muy utilitaria.'),
('IMP',	'Importado',			'Articulo pre-ensamblado.'),
('A01',	'Otro Articulo',		'Articulo ensamblado sin mucha Regularidad.');
rename table artefacto to articulos;
delete from articulos; /*where id_articulo = 'A01';*/
select * from articulos;


desc componente_articulo;
insert into componente_articulo values
('RIN','883'), 	('BSC','020'),	('MTB','001'),	('MTS','081'),	('AMT','557'),	('BMX','088'),
('RIN','015'),	('BSC','001'),	('MTB','076'),	('MTS','076'),	('AMT','795'),	('BMX','095'),
('RIN','024'),	('BSC','003'),	('MTB','081'),	('MTS','003'),	('AMT','019'),	('BMX','244'),
('RIN','022'),	('BSC','151'),	('MTB','020'),	('MTS','151'),	('AMT','004'),	('BMX','222'),
('RIN','031'),	('BSC','056'),	('MTB','003'),	('MTS','423'),	('AMT','222'),	('BMX','005'),
				('BSC','037'),	('MTB','423'),	('MTS','056'),	('AMT','745'),	('BMX','061'),
('RDP','885'),	('BSC','016'),	('MTB','056'),	('MTS','043'),	('AMT','043'),	('BMX','366'),
('RDP','015'),					('MTB','043'),	('MTS','023'),	('AMT','023'),	('BMX','009'),
('RDP','024'),  ('BSC','011'),	('MTB','023'),	('MTS','038'),	('AMT','038'),	('BMX','010'),
('RDP','022'),  ('BSC','843'),	('MTB','013'),	('MTS','843'),	('AMT','013'),	('BMX','045'),
('RDP','031'),	('BSC','014'),	('MTB','021'),	('MTS','013'),					('BMX','038'),
				('BSC','006'),	('MTB','037'),	('MTS','021'),	('AMT','021'),	('BMX','076'),
								('MTB','016'),	('MTS','037'),	('AMT','037'),	('BMX','843'),
                ('BSC','018'),	('MTB','014'),	('MTS','016'),	('AMT','014'),	('BMX','016'),
                ('BSC','030'),	('MTB','046'),	('MTS','014'),	('AMT','046'),	('BMX','084'),
                ('BSC','049'),	('MTB','050'),	('MTS','046'),	('AMT','050'),	('BMX','011'),
                ('BSC','051'),	('MTB','006'),	('MTS','050'),	('AMT','006'),	('BMX','037'),
                ('BSC','332'),	('MTB','018'),	('MTS','006'),	('AMT','018'),	('BMX','014'),
                ('BSC','010'),	('MTB','331'),	('MTS','018'),	('AMT','331'),	('BMX','046'),
				('BSC','423'),	('MTB','030'),	('MTS','331'),	('AMT','361'),	('BMX','050'),
				('BSC','975'),	('MTB','151'),	('MTS','030'),	('AMT','048'),	('BMX','127'),
				('BSC','976'),	('MTB','005'),	('MTS','005'),	('AMT','843'),	('BMX','331'),
                                ('MTB','004'),	('MTS','004'),	('AMT','975'),	('BMX','026'),
								('MTB','048'),	('MTS','048'),	('AMT','976'),	('BMX','423'),
                                ('MTB','975'),	('MTS','975'),					('BMX','975'),
                                ('MTB','976'),	('MTS','976'),					('BMX','976')
;
select * from componente_articulo;

desc tallas;
insert into tallas values
/*('001','12'), ('002','16'), ('003','20'), ('004','24'), ('005','26'), ('006','28')*/
('12'), ('16'), ('20'), ('24'), ('26'), ('28')
;
delete from tallas where 1;
select * from tallas where 1;
select * from tallas;

desc repuestos;
alter table repuestos change marca marca char(2) not null;
/*pagina catalogo => http://habicicletas.com/iframe..php?pag=bicicletas2.html*/
insert into repuestos (cod_rep, repuesto, componente, cant_disp) values 
('305337','CD MXR 12" 28D Negro Raleigh','009',15),
('305140','28x89 Negra Cuadrante','049',15),
('305309','BMX Aluminio PRO4-A530 Profnal Negro GW','009',15),
('305126','Relacion 28x89mm Cuña, Biela Reforzada','051',15),
('305451','Acero 24/34/42T-Biela Alum','048',15),
('305334','Acero 36T 314-1 (Estrella)','009',15),
('305438','Biela Acero Plastificada S/P','048',15),
('305408','Biela Acero Plastificada C/P','048',15),
('305469','Acero XCC-T208 28/38/48','048',15),
('104106','3 Piezas Negro','018',15),
('104104','3 Piezas Cromo','018',15),
('104204','3 Piezas Rosca Inglesa','018',15),
('104416','AHead Acero 1-1/8 CC845 Negro','222',15),
('105306','Acero Negro','423',15),
('105107','Acero FP501 Cromado','423',15),
('105101','Acero CC800 Cromado','423',15),
('301101','1/2 X 1/8 114L','011',15),
('301121','1/2 X 1/8 114L C410','011',15),
('301219','1/2 X 3/32 C30','021',15),
('301206','1/2 X 3/32 HP20','021',15),
('302104','Centro Y-5 36-54-46','856',15),
('302209','Eje Cuadrante Tuercas','006',15),
('302210','Eje Cuadrante Tornillos','006',15),
('302411','Cartucho Acero Negro 910BK','006',15),
('303326','Niño PVC Negro','043',15),
('303323','BMX PVC FP-808 Negro','045',15),
('303321','BMX FreeStyle Alum FP-965 Natural','045',15),
('303414','MTB PVC F-815 Negro','043',15),
('303402','MTB Aluminio','043',15),
('304444','6 Vel 14-28 MTB Marron','023',15),
('304452','6 Vel 14-34 MTB MegaRange','023',15),
('304131','16 Dientes Marron','010',15),
('304107','18 Dientes Marron','010',15),
('304124','20 Dientes Marron','010',15),
('304119','16 Dientes Cromado','010',15),
('305133','OPC-101 89x16mm Negra','026',15),
('305136','OPC-101 114x16mm Negra','026',15),
('306424','MTB Palanca Resina','013',15),
('306423','MTB Palanca Resina','013',15),
('307425','MTB RD-25','013',15),
('403436','Acero C/Eje C/Espaciador','022',15),
('403401','Acero C/Puntilla C/Espaciador','022',15),
('403439','Acero Parallex C/Eje S/Espaciador','031',15),
('403438','Acero Parallex C/Puntilla C/Espaciador','031',15),
('403343','BMX A209 Eje 3/8 Alum Natural','022',15),
('403431','BMX A222 Eje 3/8 Alum Negro Balinera','022',15),
('403435','Acero Parallex C/Eje Cromadas','022',15),
('403334','MTB A201 Eje 3/8 Alum Natural','022',15),
('403345','MTB A203 C/Puntilla Alum Natural','031',15),

('311104','Cuña Relacion Hierro 9.3mm','332',15)
/*las llantas se referenciran por tamaño, que necesariamente debe ir escrito de primero en el campo de descripcion. Al usuario debera indicarsele esta precuacion */
;
insert into repuestos (cod_rep, repuesto, componente, talla, cant_disp) values 
('407528','24 x 2.125 Negra F125','050','24',15),
('407533','16 x 2.125 Negra Pistera F116','050','16',15),
('407547','12-1 x 2-1/4 Negra F146','050','12',15),
('690733','Aro Piramide M30 GW','885','26',15),
('600382','Acero Lancer FreeStyle Disc/V-Brake','088','20',15);

insert into repuestos values 
('800843','Pareja Rines Doble Pared GW ','976','RDP','20',15),
('800445','Pareja de rines de hoja sencilla.','975','RIN','20',15)
;

alter table repuestos change repuesto repuesto varchar(80);
update repuestos set repuesto = 'Rines con Aros doble pared, trasero y delantero.' where cod_rep = '800843'; 
delete from repuestos; where cod_rep = '610382';
select * from repuestos;
select cod_rep, repuesto from repuestos where componente = '010';


/**Seleccionar los componentes por familia y por articulo*/
select c.id_comp, c.componente from componentes c inner join componente_articulo ca 
where c.id_comp = ca.componente and c.familia = '002' and ca.articulo = 'MTB';

/**Seleccionar repuestos por componentes*/
select r.cod_rep, r.repuesto from repuestos r 
where r.componente = '009';

select a.articulo from articulos a 
where a.componente = '975';

desc tallas_articulos;
insert into talla_articulo values
/*('AMT','004'), 	('ARO','001'),	('BMX','002'),	('BSC', '001'),	('MTB', '003'),	('MTS', '002'),	('PLY', '002'), ('TUR', '28'),
('AMT','005'),	('ARO','002'),	('BMX','003'),	('BSC', '002'),	('MTB', '004'),	('MTS', '003'),	('PLY', '003'),
				('ARO','003'),					('BSC', '003'),	('MTB', '005'),	('MTS', '004'),	('PLY', '004'),
                ('ARO','004'),					('BSC', '004'),					('MTS', '005'),	('PLY', '005'),
                ('ARO','005'),					('BSC', '005'),
				('ARO','006')*/

('AMT','24'), 	('RIN','12'),					/*('ARJ','12'),*/	('BMX','16'),	('BSC', '12'),	('MTB', '20'),	('MTS', '16'),	('PLY', '16'), ('TUR', '28'),
('AMT','26'),	('RIN','16'),	('RDP','16'),	/*('ARJ','16'),*/	('BMX','20'),	('BSC', '16'),	('MTB', '24'),	('MTS', '20'),	('PLY', '20'),
				('RIN','20'),	('RDP','20'),	/*('ARJ','20'),*/					('BSC', '20'),	('MTB', '26'),	('MTS', '24'),	('PLY', '24'),
                ('RIN','24'),	('RDP','24'),	/*('ARJ','24'),*/					('BSC', '24'),					('MTS', '26'),	('PLY', '26'),
                ('RIN','26'),	('RDP','26'),	/*('ARJ','26'),*/					('BSC', '26'),
				('RIN','28')					/*('ARJ','28')*/	
;
delete from talla_articulo; 
select * from talla_articulo;

desc articulos;
select a.id_articulo, a.articulo, a.descripcion, ta.talla from articulos a left join talla_articulo ta
on ta.articulo = a.id_articulo;


select cod_rep, repuesto, cant_disp from repuestos 
where componente = '050' and talla is null 
or componente = '050' and talla = '24';


select c.componente from componentes c inner join componente_articulo ca 
where c.id_comp = ca.componente and ca.articulo = 'MTB';


desc ordenes_produccion;
insert into ordenes_produccion (ensamblador, hora_despacho, hora_entrega) values 
('1190375460', '2015-04-07 13:52:44', '2015-04-10 10:10:22')
;
delete from ordenes_produccion;
select * from ordenes_produccion;

desc detalle_despacho;
insert into detalle_despacho values
(3, '403436', 5),
(3, '690733', 10)
;
delete from detalle_despacho where repuesto = '690733';
select * from detalle_despacho;

desc produccion;
insert into produccion values
(3,'RDP','26',5);

select * from familia_componente;

update familia_componente set comp_x_par = true where cod_fam = '046';

select c.id_comp, c.componente, fc.comp_x_par from componentes c inner join familia_componente fc inner join componente_articulo ca
where c.familia = fc.cod_fam and c.id_comp = ca.componente and ca.articulo = 'MTB' order by c.familia;


/*desc componente_talla;
insert into componente_talla values
('081','16'),	('001','12'),	('002','12'),	('015','12'),	('088','16'),	('057','16'),	('557','26'),	('095','16'),
('081','20'),	('001','16'),	('002','16'),	('015','16'),	('088','20'),	('057','20'),					('095','20'),
('081','24'),	('001','20'),	('002','20'),	('015','20'),					('057','24'),
('081','26'),	('001','24'),	('002','24'),	('015','24'),					('057','26'),	('014','12'),	('050','12'),
				('001','26'),	('002','26'),	('015','26'),	('020','12'),					('014','16'),	('050','16'),
								('002','28'),	('015','28'),	('020','16'),	('076','16'),	('014','20'),	('050','20'),
																('020','20'),	('076','20'),	('014','24'),	('050','24'),
('244','16'),   ('019','24'),   ('745','24'),   				('020','24'),   ('076','24'),	('014','26'),	('050','26'),
('244','20'),	('019','26'),	('745','26'), 					('020','26'),	('076','26'),	('014','28'),	('050','28'),
												('026','16'),
												('026','20'),
																('046','16'),
                                                                ('046','20'),
                                                                ('046','24'),
                                                                ('046','26'),
                                                                
('016','nt'),	('003','nt'),	('795','nt'),	('222','nt'),	('037','nt'),										
('004','nt'),	('151','nt'),	('423','nt'),	('056','nt'),	('361','nt'),
('005','nt'),	('061','nt'),	('366','nt'),	('023','nt'),	('021','nt'),	
('010','nt'),	('009','nt'),	('038','nt'),	('013','nt'),	('011','nt'),		
('045','nt'),	('084','nt'),	('127','nt'),	('613','nt'),	('856','nt'),						
('331','nt'),	('099','nt'),	('018','nt'),	('006','nt'),	('030','nt'),								
('031','nt'),	('022','nt'),	('024','nt'),	('051','nt'),	('332','nt'), 				
('844','nt'),	('049','nt'),	('048','nt'),	('843','nt'),	('043','nt')
;
delete from componente_talla;
select * from componente_talla;*/



/*CONSULTAS EFECTIVAS*/

select a.articulo from articulos a inner join componentes c 
where c.id_comp = a.componente and c.id_comp = '975';

/**Obtener los componentes por familia
select c.componente from componentes c inner join familia_componente fc 
where c.familia = fc.cod_fam and fc.cod_fam = '016';

select a.articulo from articulos a inner join familia_componente fc 
where a.familia_componente = fc.cod_fam and fc.cod_fam = '016';
/***/

select r.cod_rep, r.repuesto from repuestos r inner join componentes c inner join articulos a inner join familia_componente 
where  


/*desc talla_repuesto;
insert into talla_repuesto values 
('407533','16'),	('407547','12'),	('303326','12'),	('540007','20'),	
('407528','24'),						('303326','16')
;
*/

/***/
select c.nom_comp as Componente, r.repuesto as Repuesto from repuestos r inner join componentes c inner join componente_articulo ca
where ca.componente = r.componente and ca.componente = c.id_comp and ca.articulo = 'MTB';
/***/

/*desc marcas;
alter table marcas change id_marca id_marca char(2);
insert into marcas values ('MI','MILLENIUM',null),('NE','NECO',null),('HI','SHIMANO',null),('R','RALEIGH',null),('SH','SH',null),
('GW','GW',null),('PH','PHILIPS',null),('ES','EASTMAN',null),('ST','SUNTOUR',null),('FN','FEIMIN',null),('FV','FIVESTAR',null),
('MY','MAYA',null),('KT','KMC-TEC',null),('KM','KMC',null),('CH','CHINO',null),('PW','POWER',null),('KY','KYLIN',null);
delete from marcas;
select * from marcas;*/

/*FUENTES RESTRICCIONES Y RELACIONES
http://blog.openalfa.com/como-trabajar-con-restricciones-de-clave-externa-en-mysql
https://tomatoma.wordpress.com/manual-de-php/14-trabajar-con-mas-de-una-tabla/
https://www.youtube.com/watch?v=nAH1pjCBVnI
*/

/*desc repuesto_articulo;
insert into repuesto_articulo (articulo, plu_repuesto) values /*el siguiente orden en los registros no implica nada, solo es por visualizacion/
('BMX','600382'),	('BSC','305140'),	('MTB','305451'),	('AMT','104416'),
('BMX','305337'),						('MTB','302411'),	('AMT','302411'),
('BMX','305309'),						('MTB','301206'),	('AMT','301206'),
('BMX','303321'),						('MTB','403401'),	('AMT','403401'),
('BMX','305334'),                       ('MTB','403334'),	('AMT','403334'),
                                        ('MTB','403345'),	('AMT','403345'),
                                        ('MTB','407528'),	('AMT','407528'),
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
					('BSC','311104'),	('MTB','303402'),
('BMX','305133'),   ('BSC','407533'),   ('MTB','304444'),
('BMX','305136'),   ('BSC','407547'),   ('MTB','304452'),
('BMX','403343'),						('MTB','306424'),
('BMX','403431'),                       ('MTB','306423'),
                                        ('MTB','307425'),
										('MTB','303414')
                                        
;

delete from repuesto_articulo where 1;
select * from repuesto_articulo;
select articulo from repuesto_articulo where plu_repuesto = '600381';*/

/**CODIGO PARA BUSCAR REPUESTOS POR FAMILIA O COMPONENTE Y POR ARTICULO*/
select r.descripcion as Descripcion_Repuesto, c.componente as Tipo_de_Componente from repuestos r inner join repuesto_articulo ra inner join componentes c 
where r.cod_rep = ra.plu_repuesto and r.componente = c.id_comp and ra.articulo = 'MTB' and c.id_comp = '026';
/***/

/**CONSULTA PARA EXTRAER REPUESTOS POR COMPONENTES Y POR ARTICULO*/
select c.componente as Componente, r.descripcion as Articulo from repuestos r inner join componentes c where r.componente = c.id_comp and c.id_comp = '018';

select descripcion from repuestos where descripcion like '24%';

