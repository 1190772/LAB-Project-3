insert into Container(id_container, tare) values('abcdefghijk', -100);
insert into Container(id_container, max_weight_incl_container) values('abcdefghijk', -500);
insert into Container(id_container, max_weight) values('abcdefghijk', -400);
insert into Container(id_container, max_volume) values('abcdefghijk', -250);
insert into Container(id_container, max_weight_incl_container, max_weight) values('abcdefghijk', 100, 400);

insert into Container_Length values('a', -5);

insert into Width_Height(width_height_code, value_height) values('a', -5);
insert into Width_Height(width_height_code, value_width) values('a', -5);

insert into Reduced_Strength values('a', -50);

insert into Employee(id_employee, phone_employee) values(12345, 91086974);
insert into Employee(id_employee, phone_employee) values(12345, 9108697439);

insert into Ship(imo_code, mmsi_code) values('IMO9395044', 34796154);
insert into Ship(imo_code, mmsi_code) values('IMO9395044', 3479615428);
insert into Ship(imo_code, number_generators) values(12345, -10);
insert into Ship(imo_code, power_out_generator) values(12345, -75);
insert into Ship(imo_code, vessel_type) values(12345, 0);
insert into Ship(imo_code, length_ship) values(12345, -500);
insert into Ship(imo_code, width_ship) values(12345, -200);
insert into Ship(imo_code, capacity_ship) values(12345, -800);

insert into Position_Ship(id_ship, base_date_time, heading) values(1234567890,TO_DATE('22/04/2013','DD/MM/YY'), -30);
insert into Position_Ship(id_ship, base_date_time, heading) values(1234567890,TO_DATE('22/04/2013','DD/MM/YY'), 360);

insert into Cargo_Manifesto(id_container, date_time, position_code) values('12345678910',TO_DATE('22/04/2013','DD/MM/YY'), -124358);
insert into Cargo_Manifesto(id_container, date_time, payload) values('12345678910',TO_DATE('22/04/2013','DD/MM/YY'), -2463.9);