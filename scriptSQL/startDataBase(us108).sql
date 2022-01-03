drop table Container cascade constraints purge;
drop table Owner cascade constraints purge;
drop table Equipment_Identifier cascade constraints purge;
drop table Container_Length cascade constraints purge;
drop table Width_Height cascade constraints purge;
drop table Container_Type cascade constraints purge;
drop table ISO cascade constraints purge;
drop table Role cascade constraints purge;
drop table Employee cascade constraints purge;
drop table Truck cascade constraints purge;
drop table Ship cascade constraints purge;
drop table Position_Ship cascade constraints purge;
drop table Port cascade constraints purge;
drop table Warehouse cascade constraints purge;
drop table Cargo_Manifest cascade constraints purge;
drop table Unloading_Cargo_Manifest cascade constraints purge;
drop table Loading_Cargo_Manifest cascade constraints purge;
drop table Trip cascade constraints purge;
drop table Client cascade constraints purge;
drop table Country cascade constraints purge;
drop table Border cascade constraints purge;
drop table Sea_Distance cascade constraints purge;
drop table Container_Operation cascade constraints purge;
drop table Type_Operation cascade constraints purge;


create table Owner(
    id_owner                  char(3) constraint pk_owner Primary Key,
    name                      varChar(40)
);

create table Equipment_Identifier(
    id_equipment              char(1) constraint pk_equipment_identifier Primary Key,
    description               varChar(100)
);

create table Container_Length(
    length_code               char(1) constraint pk_container_length Primary Key,
    value_length              integer constraint ck_value_length_positive check (value_length > 0)
);

create table Width_Height(
    width_height_code         char(1) constraint pk_width_height Primary Key,
    value_height              integer constraint ck_value_height_positive check (value_height > 0),
    value_width               integer constraint ck_value_width_positive check (value_width > 0)
);

create table Container_Type(
    container_type_code       char(2) constraint pk_container_type Primary Key,
    container_type_description varChar(200)
);

create table ISO(
    iso_code                  char(4) constraint pk_iso Primary Key,
    length_code               char(1), constraint fk_length_iso Foreign Key (length_code) references Container_Length(length_code),
    width_height_code         char(1), constraint fk_width_height_iso Foreign Key (width_height_code) references Width_Height(width_height_code),
    container_type_code       char(2), constraint fk_container_type_iso Foreign Key (container_type_code) references Container_Type(container_type_code)
);

create table Container(
    id_container              char(11) constraint pk_container Primary Key,
    iso_code                  char(4) constraint fk_iso_container references ISO(iso_code),
    tare                      integer constraint ck_tare_positive check (tare > 0),
    max_weight_incl_container integer constraint ck_max_weight_incl_container_positive check (max_weight_incl_container > 0),
    max_weight                integer constraint ck_max_weight_positive check (max_weight > 0),
    max_volume                number(3,1) constraint ck_max_volume_positive check (max_volume > 0),
    id_owner                  char(3) constraint fk_owner_id_container references Owner(id_owner),
    id_equipment              char(1) constraint fk_equipment_id_container references Equipment_Identifier(id_equipment),
    serial_number             integer constraint un_serial_number_id_container unique,
    check_digit               integer,
    constraint ck_tare_lesser_max_weight check (tare < max_weight),
    constraint ck_max_weight_lesser_incl_container check (max_weight < max_weight_incl_container)
);

create table Role(
    role_id       			  integer constraint pk_role Primary Key,
    designation			      varChar(30)
);

create table Employee(
    id_employee		    	  integer constraint pk_employee Primary Key,
    role_id		    	      integer, constraint fk_role_id_employee Foreign Key (role_id) references Role(role_id),
    name_employee			  varChar(30),
    user_password			  varChar(15),
    address_employee		  varChar(30),
    phone_employee			  integer constraint ck_phone_employee_nine_digits check (phone_employee > 99999999 and phone_employee < 1000000000)
);

create table Truck(
    id_truck                  integer constraint pk_truck Primary Key,
    id_employee               integer, constraint fk_id_employee_truck Foreign Key (id_employee) references Employee(id_employee)
);

create table Ship(
    imo_code                  char(10) constraint pk_ship Primary Key,
    mmsi_code                 integer constraint un_mmsi_code_ship unique
                                      constraint ck_mmsi_code_nine_digits check (mmsi_code between 99999999 and 1000000000),
    name_ship                 varChar(30),
    number_generators         integer constraint ck_number_generators_not_negative check (number_generators > -1),
    power_out_generator       integer constraint ck_power_out_generator_not_negative check (power_out_generator > -1),
    call_sign                 varChar(9) constraint un_call_sign_ship unique,
    vessel_type               integer constraint ck_vessel_type_positive check (vessel_type > 0),
    length_ship               integer constraint ck_length_ship_positive check (length_ship > 0),
    width_ship                integer constraint ck_width_ship_positive check (width_ship > 0),
    draft                     number(3,1),
    capacity_ship             integer constraint ck_capacity_ship_positive check (capacity_ship > 0),
    id_employee_captain       integer constraint fk_ship_Employee references Employee(id_employee)
);

create table Position_Ship(
    id_ship                   char(10), constraint fk_ship_position_ship Foreign Key (id_ship) references Ship(imo_code),
    base_date_time  	      timestamp,
    latitude			      number(12,9),
    longitude			      number(12,9),
    sog	    			      number(4,1),
    cog 			          number(4,1),
    heading			          integer constraint ck_heading_valid check (heading between 0 and 359 or heading = 511),
    transceiver_class         char(1),
    Constraint pk_position_ship Primary Key (id_ship, base_date_time)
);

create table Country (
	alpha2_code	char(2) constraint pk_country Primary Key,
	alpha3_code	char(3) constraint un_alpha3_code_country unique,
	country		varChar(20) constraint un_name_country unique,
	capital		varchar(30) constraint un_capital_country unique,
	continent	varChar(10),
	population	number constraint ck_population_country_positive check (population > 0),
	latitude	number(12,9),
	longitude	number(12,9)
);

create table Port(
    id_port			          char(6) constraint pk_port Primary Key,
    name				      varChar(20),
    country_code	          char(2),
    latitude			      number(12,9),
    longitude			      number(12,9),
    capacity                  integer
);

create table Warehouse(
    id_warehouse     		  integer constraint pk_warehouse Primary Key,
    name    	   			  varChar(30),
    country_code	          char(2),
    latitude		    	  number(12,9),
    longitude		    	  number(12,9),
    capacity                  integer,
    id_port			          char(6)
);

create table Client(
    id_client                 integer constraint pk_client Primary Key,
    name_client               varchar(30),
    address_client            varChar(40),
    phone_client              integer constraint ck_phone_client_nine_digits check (phone_client > 99999999 and phone_client < 1000000000)
);

create table Trip(
    id_trip                   integer constraint pk_trip Primary Key,
    ship_imo                  char(10) constraint fk_trip_ship references Ship(imo_code),
    id_truck                  integer constraint fk_trip_truck references Truck(id_truck),
    id_start_port             char(6) constraint fk_trip_start_port references Port(id_port),
    id_destination_port       char(6) constraint fk_trip_destination_port references Port(id_port),
    id_start_warehouse        integer constraint fk_trip_start_warehouse references Warehouse(id_warehouse),
    id_destination_warehouse  integer constraint fk_trip_destination_warehouse references Warehouse(id_warehouse),
    date_time_start           timestamp,
    date_time_end             timestamp
);


create table Cargo_Manifest(
    id_cargo_manifest         integer,
    id_container              char(11), constraint fk_container_cargo_manifest Foreign Key (id_container) references Container(id_container),
    id_trip                   integer, constraint fk_trip_cargo_manifest Foreign Key (id_trip) references Trip(id_trip),
    position_code             number(6,0) constraint ck_position_code_positive check (position_code > 0),
    cargo_weight              integer,
    refrigeration_temperature number(3,1),
    id_client                 integer constraint fk_container_shipping_client references Client(id_client),
    Constraint pk_Cargo_Manifest Primary Key (id_cargo_manifest, id_container)
);



create table Unloading_Cargo_Manifest(
    id_cargo_manifest         integer,
    id_container              char(11),
    id_warehouse              integer constraint fk_warehouse_unloading_cargo_manifest references Warehouse(id_warehouse),
    Constraint fk_cargo_manifest_unloading_cargo_manifest Foreign Key (id_cargo_manifest, id_container) references Cargo_Manifest(id_cargo_manifest, id_container),
    Constraint pk_Unloading_Cargo_Manifest Primary Key (id_cargo_manifest, id_container)
);

create table Loading_Cargo_Manifest(
    id_cargo_manifest         integer,
    id_container              char(11),
    id_warehouse              integer constraint fk_warehouse_loading_cargo_manifest references Warehouse(id_warehouse),
    Constraint fk_cargo_manifest_loading_cargo_manifest Foreign Key (id_cargo_manifest, id_container) references Cargo_Manifest(id_cargo_manifest, id_container),
    Constraint pk_Loading_Cargo_Manifest Primary Key (id_cargo_manifest, id_container)
);

create table Border (
	id_country1	              char(2) constraint fk_id_country1_border  references Country(alpha2_code),
	id_country2	              char(2) constraint fk_id_country2_border  references Country(alpha2_code),
	Constraint pk_border Primary Key (id_country1, id_country2)
);

create table Sea_Distance (
	id_port1        	      char(6),
	id_port2	              char(6),
	distance	              integer constraint ck_distance_sea_distance_positive check (distance > 0),
	Constraint pk_sea_distance Primary Key (id_port1, id_port2)
);

create table Type_Operation (
    code                      char(1) constraint pk_type_operation Primary Key,
    description               char(6)
);

create table Container_Operation (
    id_cargo_manifest         integer,
	id_container	          char(11),
	base_date_time            timestamp,
	type_operation            char(1) constraint fk_type_operation_container_operation references Type_Operation(code),
	employee                  varchar(50),
	Constraint pk_container_operation Primary Key (id_cargo_manifest, id_container, base_date_time)
);

create or replace trigger Register_Operation AFTER INSERT or UPDATE or DELETE on Cargo_Manifest
for each row

BEGIN

    IF INSERTING THEN
        INSERT INTO Container_Operation VALUES(:new.id_cargo_manifest, :new.id_container, LOCALTIMESTAMP, 'I', USER);
    ELSIF UPDATING THEN
        INSERT INTO Container_Operation VALUES(:old.id_cargo_manifest, :old.id_container, LOCALTIMESTAMP, 'U', USER);
    ELSE
        INSERT INTO Container_Operation VALUES(:old.id_cargo_manifest, :old.id_container, LOCALTIMESTAMP, 'D', USER);
    END IF;

END;
/
create or replace trigger Valida_CM
before insert or update
on Cargo_Manifest
for each row

Declare
var_qtd_containers integer;
var_ship_capacity integer;

Begin

select Count(*)
into var_qtd_containers
from Cargo_Manifest;

if var_qtd_containers <> 0 then

select Count(*)
into var_qtd_containers
from Cargo_Manifest, Trip
where Cargo_Manifest.id_trip = Trip.id_trip
And Trip.id_trip = :new.id_trip;

select Ship.capacity_ship
into var_ship_capacity
from Ship,Trip
where Ship.imo_code = Trip.ship_imo
And Trip.id_trip = :new.id_trip;

if var_qtd_containers >= var_ship_capacity then
 raise_application_error(-20111,'You have reached the ship capacity');
  end if;
end if;

End Valida_CM;
/
create or replace trigger Valida_Trip
before insert or update
on Trip
for each row

Declare
var_trip_minimum integer;

Begin

select Count (*)
into var_trip_minimum
from  Trip
where Trip.ship_imo = :new.ship_imo;

if var_trip_minimum >= 1 then

select Count(*)
into var_trip_minimum
from  Trip
where Trip.ship_imo = :new.ship_imo
and ((trip.date_time_start <= :new.date_time_start
and trip.date_time_end >= :new.date_time_start)
or (trip.date_time_start <= :new.date_time_end
and trip.date_time_end >= :new.date_time_end));

if var_trip_minimum > 0 then
 raise_application_error(-20112,'The ship is already occupied');
  end if;
end if;

End Valida_Trip;
/

