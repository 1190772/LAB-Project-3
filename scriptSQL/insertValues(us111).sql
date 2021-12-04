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

INSERT INTO Container_Length VALUES ('2',61);


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


-- ** tabela position ship**

INSERT INTO Position_Ship VALUES ('IMO1234567' , timestamp '2020-02-12  01:11:12', -56.14751  ,   111.78123 , 22.3  , 33.2 , 11  , 'A');
INSERT INTO Position_Ship VALUES ('IMO1212121' , timestamp '2020-01-23  02:22:21', -29.65593  ,   155.52546 , 16.4  , 22.4 , 223 , 'B');
INSERT INTO Position_Ship VALUES ('IMO2121212' , timestamp '2021-06-30  13:33:32',  22.83719  ,   119.43955 , 33.4  , 11.1 , 333 , 'A');
INSERT INTO Position_Ship VALUES ('IMO2222222' , timestamp '2021-12-02  14:44:41', -66.09539  ,  -56.96413  , 44.5  , 42.2 , 1   , 'B');
INSERT INTO Position_Ship VALUES ('IMO1111111' , timestamp '2021-12-05  05:55:52',  59.84337  ,  -35.55917  , 53.4  , 53.3 , 23  , 'A');
INSERT INTO Position_Ship VALUES ('IMO3333333' , timestamp '2020-04-06  06:16:11', -40.87156  ,   1.65903   , 25.6  , 64.4 , 344 , 'B');
INSERT INTO Position_Ship VALUES ('IMO4444444' , timestamp '2020-02-13  17:27:22', -8.93165   ,   -15.55008 , 17.6  , 75.5 , 0   , 'A');
INSERT INTO Position_Ship VALUES ('IMO5555555' , timestamp '2021-11-16  18:38:32', -15.55008  ,  -15.55008  , 45.9  , 86.6 , 13  , 'B');
INSERT INTO Position_Ship VALUES ('IMO6666666' , timestamp '2021-10-15  09:49:41', -42.220168 , -42.22016   , 13.4  , 57.7 , 233 , 'A');

-- ** tabela Port**

INSERT INTO Port VALUES ('PT345', 'Portuguelas', 'Europa', 'Portugal', 34.11492 ,  26.38083 );
INSERT INTO Port VALUES ('AN345', 'Porto Mar'  , 'África', 'Angola'  , 9.63635  ,  22.68017 );
INSERT INTO Port VALUES ('IN233', 'Dalil'      , 'Ásia'  , 'India'   , 22.68017,  25.42360);
INSERT INTO Port VALUES ('ES456', 'MariBela'   , 'Europa', 'Espanha' , 26.97284 ,  26.97284 );

-- **tabela warehouse **

INSERT INTO Warehouse VALUES (11221, 'Warehouse Port'  , 'Europa', 'Portugal', 34.11492 , 26.38083 );
INSERT INTO Warehouse VALUES (11222, 'The warehouse'   , 'África', 'Angola'  , 19.63635  , 22.68017 );
INSERT INTO Warehouse VALUES (11223, 'Port Warehouse'  , 'Ásia'  , 'India'   , 22.68017  , 25.42360);
INSERT INTO Warehouse VALUES (11224, 'Cruz warehouse'  , 'Europa', 'Espanha' , 26.97284 ,  26.97284 );

-- ** tabela trip **

INSERT INTO Trip VALUES (1231,'IMO1234567','PT345', 'ES456', timestamp '2020-01-01 02:02:11', timestamp '2021-05-23 11:24:33');
INSERT INTO Trip VALUES (1232,'IMO1212121','AN345', 'IN233', timestamp '2020-02-12 03:13:22', timestamp '2021-12-06 21:24:33');
INSERT INTO Trip VALUES (1233,'IMO2121212','IN233', 'AN345', timestamp '2021-11-23 11:24:33', timestamp '2021-12-25 22:23:33');
INSERT INTO Trip VALUES (1234,'IMO2222222','ES456', 'PT345', timestamp '2021-06-20 19:35:33', timestamp '2021-07-22 04:36:33');
INSERT INTO Trip VALUES (1235,'IMO1111111','PT345', 'AN345', timestamp '2021-05-18 19:35:33', timestamp '2021-06-02 05:36:33');
INSERT INTO Trip VALUES (1236,'IMO3333333','AN345', 'IN233', timestamp '2021-04-25 19:35:33', timestamp '2021-05-02 06:36:33');
INSERT INTO Trip VALUES (1237,'IMO4444444','IN233', 'ES456', timestamp '2021-03-13 19:35:33', timestamp '2021-04-22 07:36:33');
INSERT INTO Trip VALUES (1238,'IMO5555555','ES456', 'PT345', timestamp '2021-09-01 19:35:33', timestamp '2021-10-12 08:36:33');
INSERT INTO Trip VALUES (1239,'IMO6666666','PT345', 'IN233', timestamp '2021-08-20 19:35:33', timestamp '2021-09-22 09:36:33');

-- ** tabela Client **

INSERT INTO Client VALUES (8884441, 'Manuel Ferreira', 'Rua xx,Porto', 993399331 );
INSERT INTO Client VALUES (8884442, 'Pedro Lopes'    , 'Rua xy,Porto', 993399332 );
INSERT INTO Client VALUES (8884443, 'David Silva'    , 'Rua xz,Porto', 993399333 );
INSERT INTO Client VALUES (8884444, 'Bruno Furtado'  , 'Rua xd,Porto', 993399334 );


-- ** tabela cargo_manifest **

INSERT INTO Cargo_Manifest VALUES (22111,'BICU1234561',1231,120703,23, 7,8884441);
INSERT INTO Cargo_Manifest VALUES (22112,'NIKR1352462',1232,110300,14, 3,8884442);
INSERT INTO Cargo_Manifest VALUES (22121,'ADIJ6543223',1233,100601,15,-5,8884443);
INSERT INTO Cargo_Manifest VALUES (22122,'FILZ6543214',1234,130400,26,-5,8884444);


-- ** tabela Unloading_Cargo_Manifest**

INSERT INTO Unloading_Cargo_Manifest VALUES (22111,'BICU1234561');
INSERT INTO Unloading_Cargo_Manifest VALUES (22112,'NIKR1352462');

-- ** tabela Loading_Cargo_Manifest**

INSERT INTO Loading_Cargo_Manifest VALUES (22121,'ADIJ6543223');
INSERT INTO Loading_Cargo_Manifest VALUES (22122,'FILZ6543214');

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
