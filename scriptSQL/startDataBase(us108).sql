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
    call_sign                 char(5) constraint un_call_sign_ship unique,
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
    latitude			      number(7,5),
    longitude			      number(8,5),
    sog	    			      number(3,1),
    cog 			          number(3,1),
    heading			          integer constraint ck_heading_valid check (heading between 0 and 359),
    latitude			      number(12,9),
    longitude			      number(12,9),
    sog	    			      number(4,1),
    cog 			          number(4,1),
    heading			          integer constraint ck_heading_valid check (heading between 0 and 359 or heading = 511),
    transceiver_class         char(1),
    Constraint pk_position_ship Primary Key (id_ship, base_date_time)
);

create table Port(
    id_port			          char(5) constraint pk_port Primary Key,
    name				      varChar(20),
    continent			      varChar(10),
    country			          varChar(20),
    latitude			      number(12,9),
    longitude			      number(12,9)
);

create table Warehouse(
    id_warehouse     		  integer constraint pk_warehouse Primary Key,
    name    	   			  varChar(30),
    continent		    	  varChar(20),
    country		         	  varChar(20),
    latitude		    	  number(12,9),
    longitude		    	  number(12,9)
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
    id_start_port             char(5) constraint fk_trip_start_port references Port(id_port),
    id_destination_port       char(5) constraint fk_trip_destination_port references Port(id_port),
    date_time_start               timestamp,
    date_time_end                 timestamp
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
    Constraint fk_cargo_manifest_unloading_cargo_manifest Foreign Key (id_cargo_manifest, id_container) references Cargo_Manifest(id_cargo_manifest, id_container),
    Constraint pk_Unloading_Cargo_Manifest Primary Key (id_cargo_manifest, id_container)
);

create table Loading_Cargo_Manifest(
    id_cargo_manifest         integer,
    id_container              char(11),
    Constraint fk_cargo_manifest_loading_cargo_manifest Foreign Key (id_cargo_manifest, id_container) references Cargo_Manifest(id_cargo_manifest, id_container),
    Constraint pk_Loading_Cargo_Manifest Primary Key (id_cargo_manifest, id_container)
);