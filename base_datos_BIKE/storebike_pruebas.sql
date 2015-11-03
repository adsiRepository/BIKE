USE `storebike`;

insert into ensambladores values 
('1107057722','CC','Miguel','González','3173547440','Cra 29 no. 38-25','2015-01-07',null),
('1190375460','CC','Jose','Ortiz','3114445566','Calle falsa 123','1990-07-10',null);

desc familia_componente;
insert into familia_componente values
('001','Marco/Cuadro',			'Componente principal de una Bicicleta.', false),
('002','Horquilla/Tenedor',		null,false),
('003','Manubrio',				null,false),
('004','Potencia/Codo',			'Hay dos tipos bÃ¡sicos de potencia segÃºn la forma de unirla al cuadro:
 las de tipo roscado y las no roscadas, siendo este Ãºltimo tipo conocido como sistema Â«aheadÂ».',false),
('005','Rodamiento Frontal',	null,false),
('006','Tija/Caña',				null,false),
('007','Seguro Caña',			null,false),
('008','Sillín',				null,false),
('009','Centro',				null,false),
('010','Relación',				null,false),
('011','Pedal',					null,true),
('012','Piñón',					null,false),
('013','Cadena',				null,false),
('014','Frenos',				null,false),
('015','Cambios',				null,false),
('016','Rines/Ruedas',			'Este componente consiste en un par de articulos no identicos.',true),
('017','Neumáticos',			null,true),
('018','Llantas',				null,true),
('019','Suplementos',			'Tornilleria, balineras; elementos suplementarios.',false),
('020','Accesorios',			null,false)
;

insert into componentes (id_comp, componente, familia) values 
('081','Cuadro Suspension','001'),	('001','Cuadro Tradicional','001'),		('883','Aro Sencillo','016'),				('015','Radios','016'),		
('557','Cuadro Ahead','001'),		('088','Cuadro BMX','001'),				('057','Cuadro Playero','001'),				('016','CaÃ±a/Tija 22.2','006'),
('020','Horquilla Roscada','002'),	('003','Manubrio MTB','003'),			('151','Manubrio Playero','003'),			('795','Manubrio Ahead','003'),
('244','Manubrio BMX','003'),		('095','Horquilla BMX','002'),			('019','Horquilla Ahead SuspensiÃ³n','002'),
('004','Espigo MTB','004'),			('423','Copas Direccion Rosca','005'),	('222','Copas Direccion Ahead','005'),		('745','Horquilla Ahead','002'),
('005','Poste Delantero','003'),	('056','Codo MTB','003'),				('061','Codo BMX','003'),					('366','Abrazadera Marco','007'),
('043','Pedal 9/16','011'),			('009','Compact Disc','010'),			('010','Piñon','012'),						('023','Pacha','012'),
('045','Pedal 1/2','011'),			('038','Juego Freno Disco','014'),		('013','Juego de Cambios','015'),			('076','Horquilla de SuspensiÃ³n Roscada','002'),
('084','Espigo BMX','003'),			('127','Juego de Centro BMX','009'),	('613','Desviador Delantero','015'),			
('011','Cadena','013'),				('021','Cadenilla','013'),				('037','Sillin','008'),						('361','Abrazadera Marco Ahead','007'),							
('099','Tensor','015'),				('856','Eje Centro CuÃ±a','009'),		('885','Aro Doble Pared','016'),
('006','Eje Centro MTB','009'),		('018','Copas de Centro','009'),		('331','Mangos','019'),						('030','Tornillo Marco','007'),
('026','Biela Enteriza','009'),		('022','Juego Manzanas','016'),			('024','Manzana Delantera','016'),			('031','Manzana Trasera','016'),
('048','Triple-plato','010'),		('049','Relacion Cuadrante','010'),		('051','Relacion Cuña','010'),				('332','Cuña Plato','010'),
('014','NeumÃ¡ticos','017'),			('046','Protector Rin','019'),			('050','Llanta','018')
;
insert into componentes values 
('844','Freno CÃ¡liper','Toda las clases de freno de \'Herradura\'.','014'),
('975','Rin Sencillo','Rin sencillo para Bicicleta completamente ensamblado y listo para ser parte de una.','016'),
('976','Rin Doble Pared','Rines de Doble Pared.','016'),
('843','Freno Cantilever','Todo tipo de freno de doble Pivote, entre ellos el V-brake.','014')
;

insert into articulos (id_articulo, articulo, descripcion) values 
/*('ARJ','Juego de Rines',		'Par de Ruedas o Rines con componentes agrupados.','016'),*/
('RIN',	'Par Rines Sencillos',	'Pareja de rines de hoja sencilla.'),
('RDP',	'Par Rines Doble Pared','Rines con Aros doble pared, trasero y delantero.'),
('BSC',	'Bicicleta S/Cambios',	'Bicicleta de Relacion Fija, Cualquier tipo de Modelo y Tamaño.'),
('MTB',	'MTB/Todo-Terreno',		'Bicicleta con Cambios, amplio rango, su categoria se basa en sus componentes.'),
('MTS',	'MTB Suspension',		'Mountain Bike con Cuadro de SuspesiÃ³n, esta clasificacion engloba cualquier tamaÃ±o.'),
('AMT',	'MTB Ahead',			'Downhill, Profesional o Semiprofesional; Bicicletas Ahead con componentes de Alta Gama.'),
('BMX',	'BMX o Cross',			'Bicicleta #20 o #16 de tipo BMX o Cross, Economica o Especial.'),
('PLY',	'Playera',				'Direccion amplia o playera, postura erguida, por lo general para el género Femenino, utilitaria, con canasta y/o parrilla.'),
('TUR',	'Turismo',				'Bicicleta Monomarcha o de Cambios Internos, ruedas de 28", frenos mediante accionamiento de varillas, muy utilitaria.'),
('IMP',	'Importado',			'Articulo pre-ensamblado.'),
('A01',	'Otro Articulo',		'Articulo ensamblado sin mucha Regularidad.');

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

insert into tallas values
('12'), ('16'), ('20'), ('24'), ('26'), ('28')
;

insert into repuestos (cod_rep, repuesto, componente, cant_disp) values 
('305337','CD MXR 12" 28D Negro Raleigh','009',12),
('305140','28x89 Negra Cuadrante','049',16),
('305309','BMX Aluminio PRO4-A530 Profnal Negro GW','009',21),
('305126','Relacion 28x89mm Cuña, Biela Reforzada','051',37),
('305451','Acero 24/34/42T-Biela Alum','048',42),
('305334','Acero 36T 314-1 (Estrella)','009',16),
('305438','Biela Acero Plastificada S/P','048',14),
('305408','Biela Acero Plastificada C/P','048',8),
('305469','Acero XCC-T208 28/38/48','048',27),
('104106','3 Piezas Negro','018',19),
('104104','3 Piezas Cromo','018',27),
('104204','3 Piezas Rosca Inglesa','018',33),
('104416','AHead Acero 1-1/8 CC845 Negro','222',52),
('105306','Acero Negro','423',43),
('105107','Acero FP501 Cromado','423',53),
('105101','Acero CC800 Cromado','423',23),
('301101','1/2 X 1/8 114L','011',32),
('301121','1/2 X 1/8 114L C410','011',23),
('301219','1/2 X 3/32 C30','021',12),
('301206','1/2 X 3/32 HP20','021',43),
('302104','Centro Y-5 36-54-46','856',23),
('302209','Eje Cuadrante Tuercas','006',43),
('302210','Eje Cuadrante Tornillos','006',54),
('302411','Cartucho Acero Negro 910BK','006',54),
('303326','Niño PVC Negro','043',53),
('303323','BMX PVC FP-808 Negro','045',24),
('303321','BMX FreeStyle Alum FP-965 Natural','045',34),
('303414','MTB PVC F-815 Negro','043',52),
('303402','MTB Aluminio','043',42),
('304444','6 Vel 14-28 MTB Marron','023',23),
('304452','6 Vel 14-34 MTB MegaRange','023',43),
('304131','16 Dientes Marron','010',56),
('304107','18 Dientes Marron','010',32),
('304124','20 Dientes Marron','010',5),
('304119','16 Dientes Cromado','010',34),
('305133','OPC-101 89x16mm Negra','026',43),
('305136','OPC-101 114x16mm Negra','026',9),
('306424','MTB Palanca Resina','013',76),
('306423','MTB Palanca Resina','013',43),
('307425','MTB RD-25','013',54),
('403436','Acero C/Eje C/Espaciador','022',32),
('403401','Acero C/Puntilla C/Espaciador','022',43),
('403439','Acero Parallex C/Eje S/Espaciador','031',21),
('403438','Acero Parallex C/Puntilla C/Espaciador','031',32),
('403343','BMX A209 Eje 3/8 Alum Natural','022',21),
('403431','BMX A222 Eje 3/8 Alum Negro Balinera','022',43),
('403435','Acero Parallex C/Eje Cromadas','022',54),
('403334','MTB A201 Eje 3/8 Alum Natural','022',65),
('403345','MTB A203 C/Puntilla Alum Natural','031',54),
('311104','Cuña Relacion Hierro 9.3mm','332',64)
/*las llantas se referenciran por tamaÃ±o, que necesariamente debe ir escrito de primero en el campo de descripcion. Al usuario debera indicarsele esta precuacion */
;
insert into repuestos (cod_rep, repuesto, componente, talla, cant_disp) values 
('407528','24 x 2.125 Negra F125','050','24',7),
('407533','16 x 2.125 Negra Pistera F116','050','16',64),
('407547','12-1 x 2-1/4 Negra F146','050','12',75),
('690733','Aro Piramide M30 GW','885','26',64),
('600382','Acero Lancer FreeStyle Disc/V-Brake','088','20',53);

insert into repuestos values 
('800843','Pareja Rines Doble Pared GW ','976','RDP','20',42),
('800445','Pareja de rines de hoja sencilla.','975','RIN','20',52)
;

insert into talla_articulo values
('AMT','24'), 	('RIN','12'),					/*('ARJ','12'),*/	('BMX','16'),	('BSC', '12'),	('MTB', '20'),	('MTS', '16'),	('PLY', '16'), ('TUR', '28'),
('AMT','26'),	('RIN','16'),	('RDP','16'),	/*('ARJ','16'),*/	('BMX','20'),	('BSC', '16'),	('MTB', '24'),	('MTS', '20'),	('PLY', '20'),
				('RIN','20'),	('RDP','20'),	/*('ARJ','20'),*/					('BSC', '20'),	('MTB', '26'),	('MTS', '24'),	('PLY', '24'),
                ('RIN','24'),	('RDP','24'),	/*('ARJ','24'),*/					('BSC', '24'),					('MTS', '26'),	('PLY', '26'),
                ('RIN','26'),	('RDP','26'),	/*('ARJ','26'),*/					('BSC', '26'),
				('RIN','28')					/*('ARJ','28')*/	
;


DELIMITER $$

USE `storebike`$$
drop function if exists numeroNuevaOrden$$
SHOW WARNINGS$$
USE `storebike`$$
create function numeroNuevaOrden(ensamblador varchar(15), momento varchar(20)) 
returns int
begin
	declare ultima_orden int(11);
	declare nueva_orden int(11);
    select max(no_ord) from ordenes_produccion into ultima_orden;
    if (ultima_orden is null) then
		set nueva_orden = 1;
    else
		set nueva_orden = ultima_orden + 1;
    end if;
    insert into ordenes_produccion (no_ord, ensamblador, hora_despacho) values (nueva_orden, ensamblador, momento);
    return nueva_orden;
end;
$$

SHOW WARNINGS$$

USE `storebike`$$
DROP TRIGGER IF EXISTS `storebike`.`ordenes_produccion_BEFORE_DELETE` $$
SHOW WARNINGS$$
USE `storebike`$$
CREATE DEFINER = CURRENT_USER TRIGGER `storebike`.`ordenes_produccion_BEFORE_DELETE` BEFORE DELETE ON `ordenes_produccion` FOR EACH ROW
BEGIN
 declare r_cod_rep varchar(6);
 declare r_cant_desp int(11);
 declare fin boolean;
 
 /* http://www.hermosaprogramacion.com/2014/06/mysql-cursores/ */
 declare reps cursor for 
 select d.repuesto, d.cant_desp from detalle_despacho d where d.orden = old.no_ord;
 declare continue handler for not found set fin = true;
 open reps;
 loop_ : loop
  fetch reps into r_cod_rep, r_cant_desp;
  if fin then
   leave loop_;
  end if;
  update repuestos set cant_disp = cant_disp + r_cant_desp where cod_rep = r_cod_rep;
 end loop loop_;
 close reps;
 
END
$$

SHOW WARNINGS$$

USE `storebike`$$
DROP TRIGGER IF EXISTS `storebike`.`detalle_despacho_AFTER_INSERT` $$
SHOW WARNINGS$$
USE `storebike`$$
CREATE DEFINER = CURRENT_USER TRIGGER `storebike`.`detalle_despacho_AFTER_INSERT` AFTER INSERT ON `detalle_despacho` FOR EACH ROW
BEGIN
 
	update repuestos set cant_disp = (cant_disp - new.cant_desp) where cod_rep = new.repuesto;
END
$$

SHOW WARNINGS$$

USE `storebike`$$
DROP TRIGGER IF EXISTS `storebike`.`detalle_despacho_AFTER_UPDATE` $$
SHOW WARNINGS$$
USE `storebike`$$
CREATE DEFINER = CURRENT_USER TRIGGER `storebike`.`detalle_despacho_AFTER_UPDATE` AFTER UPDATE ON `detalle_despacho` FOR EACH ROW
BEGIN
	declare cant_actual int(11);
    declare acumulado int(11);
	if (old.repuesto <> new.repuesto) then
		update repuestos 
		set cant_disp = (cant_disp + old.cant_desp) 
		where cod_rep = old.repuesto;
        
        if (old.cant_desp <> new.cant_desp)then
			update repuestos 
			set cant_disp = (cant_disp - new.cant_desp) 
			where cod_rep = new.repuesto;
		else
			update repuestos 
			set cant_disp = (cant_disp - old.cant_desp) 
			where cod_rep = new.repuesto;
        end if;
        
	elseif (old.cant_desp <> new.cant_desp) then
		select cant_disp from repuestos where cod_rep = old.repuesto into cant_actual;
        set acumulado = cant_actual + old.cant_desp;
		update repuestos 
		set cant_disp = (acumulado - new.cant_desp) 
		where cod_rep = old.repuesto;
	end if;
END
$$

SHOW WARNINGS$$

USE `storebike`$$
DROP TRIGGER IF EXISTS `storebike`.`detalle_despacho_AFTER_DELETE` $$
SHOW WARNINGS$$
USE `storebike`$$
CREATE DEFINER = CURRENT_USER TRIGGER `storebike`.`detalle_despacho_AFTER_DELETE` AFTER DELETE ON `detalle_despacho` FOR EACH ROW
BEGIN
 update repuestos 
		set cant_disp = (cant_disp + old.cant_desp) 
		where cod_rep = old.repuesto;
END
$$

SHOW WARNINGS$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
