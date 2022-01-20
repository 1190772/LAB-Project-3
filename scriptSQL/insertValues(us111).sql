-- **tabela type_operation **

INSERT INTO Type_Operation VALUES ('U','Update');
INSERT INTO Type_Operation VALUES ('I','Insert');
INSERT INTO Type_Operation VALUES ('D','Delete');


-- ** tabela Owner **

INSERT INTO Owner VALUES ('NIK','Nike');
INSERT INTO Owner VALUES ('BIC','Bureau pf International Containers');
INSERT INTO Owner VALUES ('ADI','Adidas');
INSERT INTO Owner VALUES ('FIL','Fila');


-- ** tabela Equipment_Identifier **

INSERT INTO Equipment_Identifier VALUES ('U','freight containers');
INSERT INTO Equipment_Identifier VALUES ('J','detachable freight container related equipment');
INSERT INTO Equipment_Identifier VALUES ('Z','trailers and chassis');
INSERT INTO Equipment_Identifier VALUES ('R','refridgerated containers');



-- ** tabela Container_Length **
-- **values in cm **

INSERT INTO Container_Length VALUES ('2',610);


-- ** tabela Width_Height **
-- ** values in cm **

INSERT INTO Width_Height VALUES ('0',244 , 244);
INSERT INTO Width_Height VALUES ('2',259 , 244);
INSERT INTO Width_Height VALUES ('5',274 , 244);



-- **tabela Container_Type **

INSERT INTO Container_Type VALUES ('G0','General purpose container without ventilation with openings at one or both ends');
INSERT INTO Container_Type VALUES ('G1','General purpose container without ventilation with passive vents at upper part of cargo space');
INSERT INTO Container_Type VALUES ('G2','General purpose container without ventilation with opening(s) at one or both ends plus “full” opening(s) on one or both sides');
INSERT INTO Container_Type VALUES ('G3','General purpose container without ventilation with Opening(s) at one or both ends plus “partial” opening(s) on one or both sides');

INSERT INTO Container_Type VALUES ('V0','General purpose container without ventilation with a nonmechanical system, vents at lower and upper parts of cargo space');
INSERT INTO Container_Type VALUES ('V2','General purpose container without ventilation with mechanical ventilation system, located internally');
INSERT INTO Container_Type VALUES ('V4','General purpose container without ventilation with mechanical ventilation system, located externally');

INSERT INTO Container_Type VALUES ('B0','Dry bulk container with mechanical ventilation system, located externally');
INSERT INTO Container_Type VALUES ('B2','Dry bulk container with nonpresurized, box type, airtight');
INSERT INTO Container_Type VALUES ('B3','Dry bulk container with pressurized, horizontal discharge, test pressure 150kPa1');
INSERT INTO Container_Type VALUES ('B4','Dry bulk container with pressurized, horizontal discharge, test pressure 265kPa');
INSERT INTO Container_Type VALUES ('B5','Dry bulk container with pressurized, horizontal discharge, test pressure 265kPa');
INSERT INTO Container_Type VALUES ('B6','Dry bulk container with pressurized, tipping discharge, test pressure 265kPa');

INSERT INTO Container_Type VALUES ('S0','Livestock carrier with livestock carrier');
INSERT INTO Container_Type VALUES ('S1','Livestock carrier with automobile carrier');
INSERT INTO Container_Type VALUES ('S2','Livestock carrier with live fish carrier');

INSERT INTO Container_Type VALUES ('R0','Thermal container with refrigerated, mechanically refrigerated	');
INSERT INTO Container_Type VALUES ('R1','Thermal container with refrigerated and heated, mechanically refrigerated and heated	');
INSERT INTO Container_Type VALUES ('R2','Thermal container with self-powered refrigerated/heated, mechanically refrigerated	');
INSERT INTO Container_Type VALUES ('R3','Thermal container with mechanically refrigerated and heated	');

INSERT INTO Container_Type VALUES ('H0','Thermal container with refrigerated and/or heated, with removable equipment located externally; heat transfer coefficient K=0,4W/(m2*K)');
INSERT INTO Container_Type VALUES ('H1','Thermal container with refrigerated and/or heated, with removable equipment located externally; heat transfer coefficient K=0,4W/(m2*K)');
INSERT INTO Container_Type VALUES ('H2','Thermal container with refrigerated and/or heated with removable equipment located externally; heat transfer coefficient K =0,7W/(m2*K)');
INSERT INTO Container_Type VALUES ('H5','Thermal container with insulated; heat transfer coefficient K= 0,4W/(m2*K)');
INSERT INTO Container_Type VALUES ('H6','Thermal container with insulated; heat transfer coefficient K= 0,7W/(m2*K)');

INSERT INTO Container_Type VALUES ('U0','Open-top container with opening(s) at one or both ends');
INSERT INTO Container_Type VALUES ('U1','Open-top container with opening(s) at one or both ends, plus removable top member(s) in end frame(s)');
INSERT INTO Container_Type VALUES ('U2','Open-top container with opening(s) at one or both ends, plus removable top member(s) in end frame(s)');
INSERT INTO Container_Type VALUES ('U3','Open-top container with opening(s) at one or both ends, plus opening(s) on one or both  sides plus removable top  member(s) in end frame(s)');
INSERT INTO Container_Type VALUES ('U4','Open-top container with opening(s) at one or both ends, plus partial opening on one side and full opening on the other side');
INSERT INTO Container_Type VALUES ('U5','Open-top container with complete, fixed side and end walls (no doors)');

INSERT INTO Container_Type VALUES ('P0','Platform (container)');
INSERT INTO Container_Type VALUES ('P1','Platform (container) platform with two complete, fixed end walls');
INSERT INTO Container_Type VALUES ('P2','Platform (container) platform with fixed posts, either free-standing or with removable top members');
INSERT INTO Container_Type VALUES ('P3','Platform (container) platform with fixed posts, either free-standing or with removable top members');
INSERT INTO Container_Type VALUES ('P4','Platform (container) platform with fixed posts, either free-standing or with removable top members');
INSERT INTO Container_Type VALUES ('P5','Platform (container) platform with fixed posts, either free-standing or with removable top members');

INSERT INTO Container_Type VALUES ('T0','Tank container	for non dangerous liquids, minimum pressure 45kPa');
INSERT INTO Container_Type VALUES ('T1','Tank container	for non dangerous liquids, minimum pressure 150kPa');
INSERT INTO Container_Type VALUES ('T2','Tank container	for non dangerous liquids, minimum pressure 265kPa');
INSERT INTO Container_Type VALUES ('T3','Tank container	for non dangerous liquids, minimum pressure 265kPa');
INSERT INTO Container_Type VALUES ('T4','Tank container	for dangerous liquids, minimum pressure 265kPa');
INSERT INTO Container_Type VALUES ('T5','Tank container	for dangerous liquids, minimum pressure 40kPa');
INSERT INTO Container_Type VALUES ('T6','Tank container	for dangerous liquids, minimum pressure 60kPa');
INSERT INTO Container_Type VALUES ('T7','Tank container	for gases, minimum pressure 910kPa');
INSERT INTO Container_Type VALUES ('T8','Tank container	for gases, minimum pressure 910kPa');
INSERT INTO Container_Type VALUES ('T9','Tank container	for gases, minimum pressure 910kPa');




-- ** tabela de ISO / reduced stregth problem resolved**

INSERT INTO ISO VALUES ('20G1','2','0','G1');
INSERT INTO ISO VALUES ('22G2','2','2','G2');
INSERT INTO ISO VALUES ('25G3','2','5','G3');
INSERT INTO ISO VALUES ('20R0','2','0','R0');
INSERT INTO ISO VALUES ('22B2','2','2','B2');
INSERT INTO ISO VALUES ('25U2','2','5','U2');
INSERT INTO ISO VALUES ('22T8','2','2','T8');
INSERT INTO ISO VALUES ('22H1','2','2','H1');
INSERT INTO ISO VALUES ('20P1','2','0','P1');
INSERT INTO ISO VALUES ('25U3','2','5','U3');
INSERT INTO ISO VALUES ('20S1','2','0','S1');

INSERT INTO ISO VALUES ('20T1','2','0','T1');
INSERT INTO ISO VALUES ('22S2','2','2','S2');
INSERT INTO ISO VALUES ('20R3','2','0','R3');
INSERT INTO ISO VALUES ('25H6','2','5','H6');



-- ** tabela Container / espaço a mais no id container **
-- ** weights in kg **

INSERT INTO Container VALUES ('BICU1234561', '20G1', 2000, 30000 , 28000 , 33.1 , 'BIC' , 'U' , 123456 , 1);
INSERT INTO Container VALUES ('NIKR1352462', '22G2', 2500, 29000 , 26500 , 31.2 , 'NIK' , 'R' , 135246 , 2);
INSERT INTO Container VALUES ('ADIJ6543223', '25G3', 2300, 28000 , 25700 , 32.1 , 'ADI' , 'J' , 654322 , 3);
INSERT INTO Container VALUES ('FILZ6543214', '20R0', 2200, 30000 , 27800 , 28.9 , 'FIL' , 'Z' , 654321 , 4);
INSERT INTO Container VALUES ('BICZ1231235', '22B2', 2000, 29000 , 27000 , 29.7 , 'BIC' , 'Z' , 123123 , 5);
INSERT INTO Container VALUES ('NIKJ1111116', '25U2', 2100, 29500 , 27400 , 28.5 , 'NIK' , 'J' , 111111 , 6);
INSERT INTO Container VALUES ('ADIJ2222227', '22T8', 2400, 28500 , 26100 , 35   , 'ADI' , 'J' , 222222 , 7);
INSERT INTO Container VALUES ('FILR4564568', '22H1', 2600, 26500 , 23900 , 36   , 'FIL' , 'R' , 456456 , 8);
INSERT INTO Container VALUES ('BICJ1212129', '20P1', 2200, 26000 , 23800 , 34   , 'BIC' , 'J' , 121212 , 9);

INSERT INTO Container VALUES ('BICJ4444445', '20S1', 2400, 30000 , 27600 , 37.7 , 'BIC' , 'J' , 444444 , 5);
INSERT INTO Container VALUES ('NIKJ5555556', '25U3', 1900, 28000 , 26100 , 34.5 , 'NIK' , 'J' , 555555 , 6);
INSERT INTO Container VALUES ('ADIZ6666667', '20R3', 2300, 26000 , 23700 , 34   , 'ADI' , 'Z' , 666666 , 7);
INSERT INTO Container VALUES ('FILR7777778', '25H6', 2500, 29000 , 26500 , 35   , 'FIL' , 'R' , 777777 , 8);
INSERT INTO Container VALUES ('BICR8888889', '22S2', 2200, 29300 , 27100 , 37   , 'BIC' , 'R' , 888888 , 9);



-- ** tabela role **

INSERT INTO Role VALUES (2222,'Fleet Manager');
INSERT INTO Role VALUES (3333,'Traffic Manager');
INSERT INTO Role VALUES (4444,'Warehouse manager');
INSERT INTO Role VALUES (5555,'Warehouse staff');
INSERT INTO Role VALUES (6666,'Port staff');
INSERT INTO Role VALUES (7777,'Port manager');
INSERT INTO Role VALUES (8888,'Ship captain');
INSERT INTO Role VALUES (9999,'Ship chief eletrical engineer');
INSERT INTO Role VALUES (1010,'Truck driver');



-- ** tabela Employee

INSERT INTO Employee VALUES (121212, 2222,'Pedro Ferreira' ,'123pp', 'rua x, Porto' ,911222221 );
INSERT INTO Employee VALUES (212121, 3333,'Manuel Ferreira','123bb', 'rua y, Porto' ,911222222 );
INSERT INTO Employee VALUES (232323, 4444,'Carlos Silva'   ,'123dd', 'rua z, Porto' ,911222223 );
INSERT INTO Employee VALUES (323232, 5555,'Carla Silva'    ,'123ee', 'rua i, Porto' ,911222224 );
INSERT INTO Employee VALUES (343434, 6666,'Jorge Neto'     ,'123ss', 'rua t, Porto' ,911222225 );
INSERT INTO Employee VALUES (434343, 7777,'Marcos Neto'    ,'123ww', 'rua l, Porto' ,911222226 );
INSERT INTO Employee VALUES (454545, 8888,'Bruno Lopes'    ,'123gg', 'rua p, Porto' ,911222227 );
INSERT INTO Employee VALUES (111111, 8888,'Nikolas Tesla'  ,'123gb', 'rua pt,Porto' ,911222277 );
INSERT INTO Employee VALUES (222222, 8888,'Nuno Mendes'    ,'123gx', 'rua pp,Porto' ,911222287 );
INSERT INTO Employee VALUES (333333, 8888,'Mike Tyson'     ,'123gy', 'rua pb,Porto' ,911222297 );
INSERT INTO Employee VALUES (333344, 8888,'Albert Einstein','123ge', 'rua pbb,Porto',911222277 );
INSERT INTO Employee VALUES (333355, 8888,'Pedro Cruz'     ,'123gm', 'rua pbd,Porto',911222788 );
INSERT INTO Employee VALUES (333366, 8888,'Ruben Silva'    ,'123gp', 'rua pbe,Porto',911222299 );
INSERT INTO Employee VALUES (333377, 8888,'Jorge Mendes'   ,'123gq', 'rua pbx,Porto',911222211 );
INSERT INTO Employee VALUES (333388, 8888,'Kendrick Lamar' ,'123gt', 'rua pby,Porto',911222233 );

INSERT INTO Employee VALUES (343381, 8888,'Jermain Cole' , '123ggg', 'rua pbyx,Porto',991222233 );
INSERT INTO Employee VALUES (343382, 8888,'Sergio Ramos' , '123gtg', 'rua pbyy,Porto',991222233 );
INSERT INTO Employee VALUES (343383, 8888,'Bruna Meiga'  , '123gtg', 'rua pbyz,Porto',991222233 );
INSERT INTO Employee VALUES (343384, 8888,'Tyson Fury'   , '123gtg', 'rua pbyd,Porto',991222233 );
INSERT INTO Employee VALUES (343385, 8888,'Manuel Fatela', '123gtg', 'rua pbye,Porto',991222233 );

INSERT INTO Employee VALUES (565656, 9999,'Bruna Lopes'    ,'123vv', 'rua d, Porto' ,911222228 );
INSERT INTO Employee VALUES (656565, 1010,'Lionel Neto'    ,'123nn', 'rua r, Porto' ,911222229 );
INSERT INTO Employee VALUES (676767, 1010,'Marcos Lopes'   ,'123kk', 'rua q, Porto' ,911222211 );
INSERT INTO Employee VALUES (767676, 1010,'Marta Lima'     ,'123oo', 'rua nn,Porto' ,911222222 );
INSERT INTO Employee VALUES (787878, 1010,'Mariana Lima'   ,'123uu', 'rua vv,Porto' ,911222233 );



-- ** tabela truck **

INSERT INTO Truck VALUES (12345,656565);
INSERT INTO Truck VALUES (12346,676767);
INSERT INTO Truck VALUES (12347,767676);
INSERT INTO Truck VALUES (12348,787878);


-- ** tabela ship **

INSERT INTO Ship VALUES ('IMO1234567',111122221, 'Santa Maria' ,2, 400, 'SDH6F' ,22, 400, 16 ,9.2  ,300 ,454545);
INSERT INTO Ship VALUES ('IMO1212121',111122222, 'Mayflower'   ,2, 400, 'SMM2F' ,23, 450, 15 ,8.3  ,250 ,111111);
INSERT INTO Ship VALUES ('IMO2121212',111122223, 'Victory'     ,3, 400, 'SEE5B' ,44, 390, 12 ,10.5 ,200 ,222222);
INSERT INTO Ship VALUES ('IMO2222222',111122224, 'Constitution',3, 400, 'SEE6C' ,34, 460, 30 ,9.6  ,213 ,333333);
INSERT INTO Ship VALUES ('IMO1111111',111122225, 'Beagle'      ,2, 400, 'SEE6K' ,56, 420, 28 ,8.3  ,400 ,333344);
INSERT INTO Ship VALUES ('IMO3333333',111122226, 'Titanic'     ,2, 400, 'YH4FF' ,43, 320, 12 ,10.8 ,500 ,333355);
INSERT INTO Ship VALUES ('IMO4444444',111122227, 'Monica'      ,3, 400, 'KWQ8K' ,54, 360, 15 ,8.8  ,234 ,333366);
INSERT INTO Ship VALUES ('IMO5555555',111122228, 'Fasty'       ,3, 400, 'CEE8N' ,32, 430, 18 ,6.8  ,560 ,333377);
INSERT INTO Ship VALUES ('IMO6666666',111122229, 'Caribbean'   ,2, 400, 'UEE6V' ,33, 402, 15 ,8.8  ,540 ,333388);

INSERT INTO Ship VALUES ('IMO7777777',211122229, 'References'          ,3, 400, 'ESE6V' ,33, 390, 12 ,9    ,440 ,343381);
INSERT INTO Ship VALUES ('IMO8888888',611122221, 'Black Pearl'         ,4, 400, 'PTE6V' ,43, 480, 20 ,11.8 ,600 ,343382);
INSERT INTO Ship VALUES ('IMO9999999',611122222, 'Queen Anne Revenge'  ,2, 400, 'BRE6V' ,45, 440, 14 ,8.7  ,560 ,343383);
INSERT INTO Ship VALUES ('IMO6161616',611122223, 'The Fair Wind'       ,3, 400, 'USE6V' ,53, 395, 15 ,9.8  ,440 ,343384);

INSERT INTO Ship VALUES ('IMO6262626',611122224, 'Royal Fortune'       ,2, 400, 'CVE6V' ,35, 400, 16 ,8.8  ,2,343385);


-- ** tabela position ship**

INSERT INTO Position_Ship VALUES ('IMO1234567' , timestamp '2020-02-12  01:11:12', -56.14751  ,   111.78123 , 22.3  , 33.2 , 11  , 'A');
INSERT INTO Position_Ship VALUES ('IMO1212121' , timestamp '2020-01-23  02:22:21', -29.65593  ,   155.52546 , 16.4  , 22.4 , 223 , 'B');
INSERT INTO Position_Ship VALUES ('IMO2121212' , timestamp '2021-06-30  13:33:32',  22.83719  ,   119.43955 , 33.4  , 11.1 , 333 , 'A');
INSERT INTO Position_Ship VALUES ('IMO5555555' , timestamp '2021-11-16  18:38:32', -15.55008  ,  -15.55008  , 45.9  , 86.6 , 13  , 'B');
INSERT INTO Position_Ship VALUES ('IMO6666666' , timestamp '2021-10-15  09:49:41', -42.220168 , -42.22016   , 13.4  , 57.7 , 233 , 'A');
INSERT INTO Position_Ship VALUES ('IMO2222222' , timestamp '2021-12-02  14:44:41', -66.09539  ,  -56.96413  , 44.5  , 42.2 , 1   , 'B');
INSERT INTO Position_Ship VALUES ('IMO1111111' , timestamp '2021-12-05  05:55:52',  59.84337  ,  -35.55917  , 53.4  , 53.3 , 23  , 'A');
INSERT INTO Position_Ship VALUES ('IMO4444444' , timestamp '2020-02-13  17:27:22', -8.93165   ,   -15.55008 , 17.6  , 75.5 , 0   , 'A');
INSERT INTO Position_Ship VALUES ('IMO3333333' , timestamp '2020-04-06  06:16:11', -40.87156  ,   1.65903   , 25.6  , 64.4 , 344 , 'B');

-- ** tabela Country **

INSERT INTO Country VALUES ('PT','PRT','Portugal','Lisbon','Europe',10.31,38.71666667,-9.133333, 17);
INSERT INTO Country VALUES ('ES','ESP','Spain','Madrid','Europe',46.53,40.4,-3.683333, 15);
INSERT INTO Country VALUES ('BR','BRA','Brazil','Brasilia','America',206.12,-15.78333333,-47.916667, 22);
INSERT INTO Country VALUES ('AO','AGO','Angola','Luanda','Africa',32.87,-8.838333,13.234444, 15.6);
INSERT INTO Country VALUES ('IN','IND','India','Nova Delhi','Asia',1393.41,28.644800,28.644800, 25.78);

-- ** tabela Port**

INSERT INTO Port VALUES ('PT345', 'Portuguelas', 'PT', 34.11492 ,  26.38083, 60);
INSERT INTO Port VALUES ('AN345', 'Porto Mar'  , 'ES', 9.63635  ,  22.68017, 70);
INSERT INTO Port VALUES ('IN233', 'Dalil'      , 'AO', 22.68017 ,  25.42360, 80);
INSERT INTO Port VALUES ('ES456', 'MariBela'   , 'IN', 26.97284 ,  26.97284, 90);

-- **tabela warehouse **

INSERT INTO Warehouse VALUES (11221, 'Warehouse Port'  , 'PT', 34.11492 , 26.38083,100, 'PT345' );
INSERT INTO Warehouse VALUES (11222, 'The warehouse'   , 'ES', 19.63635 , 22.68017,110, 'AN345' );
INSERT INTO Warehouse VALUES (11223, 'Port Warehouse'  , 'AO', 22.68017 , 25.42360,120, 'IN233' );
INSERT INTO Warehouse VALUES (11224, 'Cruz warehouse'  , 'IN', 26.97284 , 26.97284,130, 'ES456' );

-- ** tabela trip **

INSERT INTO Trip VALUES (1231,'IMO1234567',null,'PT345', 'ES456',null, null, timestamp '2020-01-01 02:02:11',timestamp '2020-03-03 02:02:11');
INSERT INTO Trip VALUES (1232,'IMO1212121',null, 'AN345',  null ,null  ,11221,timestamp '2020-02-12 03:13:22', timestamp '2021-03-02 02:36:33');
INSERT INTO Trip VALUES (1233,'IMO2121212',null, 'IN233',  null ,null  ,11222,timestamp '2021-11-23 11:24:33', timestamp '2022-04-02 03:36:33');
INSERT INTO Trip VALUES (1234,'IMO2222222',null, 'ES456',  null ,null  ,11223,timestamp '2021-06-20 19:35:33', timestamp '2022-05-02 04:36:33');
INSERT INTO Trip VALUES (1235,'IMO1111111',null,    null, 'AN345',11224,null ,timestamp '2021-05-18 19:35:33', timestamp '2022-06-02 05:36:33');
INSERT INTO Trip VALUES (1236,'IMO1111111',null,    null, 'IN233',11221,null , timestamp '2021-04-25 19:35:33', timestamp '2021-05-02 06:36:33');
INSERT INTO Trip VALUES (1237,'IMO4444444',null,    null, 'ES456',11222,null , timestamp '2021-03-13 19:35:33', timestamp '2021-04-22 07:36:33');
INSERT INTO Trip VALUES (1238,'IMO5555555',null,    null, 'PT345',11223,null , timestamp '2021-09-01 19:35:33', timestamp '2021-10-12 08:36:33');
INSERT INTO Trip VALUES (1239,'IMO6666666',null,    null, 'IN233',11224,null , timestamp '2021-08-20 19:35:33', timestamp '2021-09-22 09:36:33');
INSERT INTO Trip VALUES (1240,'IMO6666666',12345,'IN233',    null,null ,11224, timestamp '2020-01-01 02:02:11',timestamp '2020-03-03 02:02:11');

INSERT INTO Trip VALUES (2235,'IMO6666666',null,'PT345', null, null, 11221, timestamp '2021-02-08 19:35:33', timestamp '2021-03-01 06:13:23');
INSERT INTO Trip VALUES (2236,'IMO5555555',null,'AN345', null, null, 11222, timestamp '2021-03-15 19:35:33', timestamp '2021-04-22 07:26:13');
INSERT INTO Trip VALUES (2237,'IMO5555555',null,'IN233', null, null, 11223, timestamp '2021-04-23 19:35:33', timestamp '2022-04-22 08:26:13');
INSERT INTO Trip VALUES (2238,'IMO6161616',null, 'PT345',null, null,11221, timestamp '2020-05-21 19:35:33', timestamp '2022-05-22 08:26:13');
INSERT INTO Trip VALUES (2239,'IMO6262626',null, 'IN233',null, null,11222, timestamp '2020-06-10 19:35:33', timestamp '2022-06-22 08:26:13');

INSERT INTO Trip VALUES (4444,'IMO1234567',null,'PT345', 'IN233',null, null, timestamp '2020-05-10 10:30:00',timestamp '2020-05-26 22:30:00');



-- ** tabela Client **

INSERT INTO Client VALUES (8884441, 'Manuel Ferreira', 'Rua xx,Porto' , 993399331 );
INSERT INTO Client VALUES (8884442, 'Pedro Lopes'    , 'Rua xy,Porto' , 993399332 );
INSERT INTO Client VALUES (8884443, 'David Silva'    , 'Rua xz,Porto' , 993399333 );
INSERT INTO Client VALUES (8884444, 'Bruno Furtado'  , 'Rua xd,Porto' , 993399334 );
INSERT INTO Client VALUES (8884445, 'Manuel Ferreira', 'Rua xxd,Porto', 993399335 );
INSERT INTO Client VALUES (8884446, 'Pedro Lopes'    , 'Rua xye,Porto', 993399336 );
INSERT INTO Client VALUES (8884447, 'David Silva'    , 'Rua xzr,Porto', 993399337 );
INSERT INTO Client VALUES (8884448, 'Bruno Furtado'  , 'Rua xdb,Porto', 993399338 );

INSERT INTO Client VALUES (1884441, 'Bruna Guimarães', 'Rua xdbs,Porto', 999399331 );
INSERT INTO Client VALUES (1884442, 'Jõao Rodrigues' , 'Rua xdbd,Porto', 999399332 );
INSERT INTO Client VALUES (1884443, 'Mike Limeiro'   , 'Rua xdbg,Porto', 999399333 );
INSERT INTO Client VALUES (1884444, 'Nuno Santo'     , 'Rua xdbh,Porto', 999399334 );
INSERT INTO Client VALUES (1884445, 'Pedro Gonçalves', 'Rua xdbl,Porto', 999399335 );


-- ** tabela cargo_manifest **

INSERT INTO Cargo_Manifest VALUES (22111,'BICU1234561',1231, 120703, 23,  7, 8884441);
INSERT INTO Cargo_Manifest VALUES (22112,'NIKR1352462',1232, 110300, 14,  3, 8884442);
INSERT INTO Cargo_Manifest VALUES (22121,'ADIJ6543223',1233, 100601, 15, -5, 8884443);
INSERT INTO Cargo_Manifest VALUES (22122,'FILZ6543214',1234, 130400, 20, -5, 8884444);
INSERT INTO Cargo_Manifest VALUES (12122,'BICZ1231235',1235, 020510, 21,  7, 8884445);
INSERT INTO Cargo_Manifest VALUES (12122,'NIKJ1111116',1235, 051001, 15,  null, 8884446);
INSERT INTO Cargo_Manifest VALUES (12122,'ADIJ2222227',1235, 070300, 16, -5, 8884447);
INSERT INTO Cargo_Manifest VALUES (12122,'FILR4564568',1235, 101012, 18,  7, 8884448);
INSERT INTO Cargo_Manifest VALUES (12623,'FILR4564568',1236, 101012, 18,  6, 8884448);

INSERT INTO Cargo_Manifest VALUES (32122,'BICJ4444445' ,1239, 120000, 100000, 2, 1884441);
INSERT INTO Cargo_Manifest VALUES (32123,'BICJ4444445' ,1240, 120000, 100000, 2, 1884441);
INSERT INTO Cargo_Manifest VALUES (32122,'NIKJ5555556' ,2235, 110201, 80000,  3, 1884442);
INSERT INTO Cargo_Manifest VALUES (32122,'ADIZ6666667' ,2236, 031102, 90000,  4, 1884443);
INSERT INTO Cargo_Manifest VALUES (32122,'FILR7777778' ,2237, 011103, 120000,-5, 1884444);
INSERT INTO Cargo_Manifest VALUES (12122,'BICR8888889' ,2238, 100705, 100000,-5, 1884445);

INSERT INTO Cargo_Manifest VALUES (55555,'BICU1234561',4444, 201001, 23,  7, 8884441);
INSERT INTO Cargo_Manifest VALUES (55555,'NIKR1352462',4444, 211001, 23,  -5, 8884441);
INSERT INTO Cargo_Manifest VALUES (55555,'ADIJ6543223',4444, 221001, 23,  -5, 8884441);
INSERT INTO Cargo_Manifest VALUES (55555,'FILZ6543214',4444, 231001, 23,  7, 8884441);
INSERT INTO Cargo_Manifest VALUES (55555,'BICZ1231235',4444, 241001, 23,  7, 8884441);
INSERT INTO Cargo_Manifest VALUES (55555,'NIKJ1111116',4444, 251001, 23,  7, 8884441);
INSERT INTO Cargo_Manifest VALUES (55555,'ADIJ2222227',4444, 261001, 23,  -5, 8884441);
INSERT INTO Cargo_Manifest VALUES (55555,'FILR4564568',4444, 271001, 23,  -5, 8884441);
INSERT INTO Cargo_Manifest VALUES (55555,'BICJ1212129',4444, 281001, 23,  -5, 8884441);
INSERT INTO Cargo_Manifest VALUES (55555,'BICJ4444445',4444, 291001, 23,  null, 8884441);
INSERT INTO Cargo_Manifest VALUES (55555,'NIKJ5555556',4444, 301001, 23,  -5, 8884441);
INSERT INTO Cargo_Manifest VALUES (55555,'ADIZ6666667',4444, 201002, 23,  -5, 8884441);
INSERT INTO Cargo_Manifest VALUES (55555,'FILR7777778',4444, 211002, 23,  -5, 8884441);
INSERT INTO Cargo_Manifest VALUES (55555,'BICR8888889',4444, 221002, 23,  7, 8884441);




-- ** tabela Unloading_Cargo_Manifest**

INSERT INTO Unloading_Cargo_Manifest VALUES (22111,'BICU1234561',11221);
INSERT INTO Unloading_Cargo_Manifest VALUES (22112,'NIKR1352462',11222);
INSERT INTO Unloading_Cargo_Manifest VALUES (22121,'ADIJ6543223',11223);
INSERT INTO Unloading_Cargo_Manifest VALUES (22122,'FILZ6543214',11224);

INSERT INTO Unloading_Cargo_Manifest VALUES (32122,'ADIZ6666667',11221);
INSERT INTO Unloading_Cargo_Manifest VALUES (32122,'FILR7777778',11222);
INSERT INTO Unloading_Cargo_Manifest VALUES (12122,'BICR8888889',11223);

-- ** tabela Loading_Cargo_Manifest**

INSERT INTO Loading_Cargo_Manifest VALUES (12122,'BICZ1231235',11221);
INSERT INTO Loading_Cargo_Manifest VALUES (12122,'NIKJ1111116',11222);
INSERT INTO Loading_Cargo_Manifest VALUES (12122,'ADIJ2222227',11223);
INSERT INTO Loading_Cargo_Manifest VALUES (12122,'FILR4564568',11224);

INSERT INTO Loading_Cargo_Manifest VALUES (32122,'FILR7777778',11221);
INSERT INTO Loading_Cargo_Manifest VALUES (12122,'BICR8888889',11222);

-- ** tabela Border **

INSERT INTO Border VALUES ('PT','ES');
INSERT INTO Border VALUES ('ES','PT');

commit;


select * from Owner;
select * from Equipment_Identifier;
select * from Length;
select * from Width_Height;
select * from Container_Type;
select * from ISO;
select * from Container;
select * from Role;
select * from Employee;
select * from Truck;
select * from Ship;
select * from Position_ship;
select * from Port;
select * from Warehouse;
select * from Trip;
select * from Client;
select * from Cargo_Manifest;
select * from Unloading_Cargo_Manifest;
select * from Loading_Cargo_Manifest;
select * from Border;
select * from type_operation;
select * from Country;

