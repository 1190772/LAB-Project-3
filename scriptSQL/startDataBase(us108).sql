drop table Container;
drop table Owner;
drop table Cargo_Manifesto;
drop table Truck;
drop table Ship;
drop table Port;
drop table Warehouse;
drop table Employee;
drop table Role;
drop table Container_Length;
drop table Width_Height;
drop table Container_Type;
drop table Reduced_Strength;
drop table ISO;
drop table ID_Container;
drop table Equipment_Identifier;
drop table Position_Ship;

create table Container(
    id_container              char(11) constraint pk_container Primary Key,
    tare                      integer,
    iso_code                  char(4),
    refrigeration_temperature number(3,1),
    max_weight_incl_container integer,
    max_weight                integer,
    max_volume                integer,
--    csc_plate                 char(?),
    acep                      integer,
    pes_date                  date);

create table Owner(
    id_owner                  char(3) constraint pk_owner Primary Key,
    name                      varChar(30));

create table Equipment_Identifier(
    id_equipment              char(1) constraint pk_equipment_identifier Primary Key,
    description               varChar(100));

create table ID_Container(
    id_container              char(11), constraint fk_id_container_id_container Foreign Key (id_container) references Container(id_container), constraint pk_id_container Primary Key,
    id_owner                  char(3), constraint fk_owner_id_container Foreign Key (id_owner) references Owner(id_owner),
    id_equipment              char(1), constraint fk_equipment_id_container Foreign Key (id_equipment) references Equipment_Identifier(id_equipment),
    serial_number             integer, constraint un_serial_number_id_container unique,
    check_digit               integer);

create table Container_Length(
    length_code               char(1) constraint pk_container_length Primary Key,
    value_length              integer);

create table Width_Height(
    width_height_code         char(1) constraint pk_width_height Primary Key,
    value_height              integer,
    value_width               integer);

create table Container_Type(
    container_type_code       char(1) constraint pk_container_type Primary Key,
    value_container_type      integer);

create table Reduced_Strength(
    reduced_strength_code     char(1) constraint pk_reduced_strength Primary Key,
    value_reduced_strength    integer);

create table ISO(
    iso_code                  char(4) constraint pk_iso Primary Key,
    length_code               char(1), constraint fk_length_iso Foreign Key (length_code) references Container_Length(length_code),
    width_height_code         char(1), constraint fk_width_height_iso Foreign Key (width_height_code) references Width_Height(width_height_code),
    container_type_code       char(1), constraint fk_container_type_iso Foreign Key container_type_code references Container_Type(container_type_code),
    reduced_strength_code     char(1), constraint fk_reduced_strength_iso Foreign Key (reduced_strength_code) references Reduced_Strength(reduced_strength_code));

create table Role(
    role_id       			  integer constraint pk_role Primary Key,
    designation			      varChar(30));

create table Employee(
    id_employee		    	  integer constraint pk_employee Primary Key,
    role_id		    	      integer, constraint fk_role_id_employee Foreign Key (role_id) references Role(role_id),
    name_employee			  varChar(30),
    user_password			  varChar(15),
    address_employee		  varChar(30),
    phone_employee			  integer);

create table Truck(
    id_truck                  integer constraint pk_truck Primary Key,
    id_employee               integer, constraint fk_id_employee_truck references Employee(id_employee));

create table Ship(
    imo_code                  char(10) constraint pk_ship Primary Key,
    mmsi_code                 integer, constraint un_mmsi_code_ship unique, constraint ck_mmsi_code_ship check between 100000000 and 999999999,
    name_ship                 varChar(30),
    number_generators         integer,
    power_out_generator       integer,
    call_sign                 char(5), constraint un_call_sign_ship unique,
    vessel_type               integer,
    Length_ship               integer,
    width_ship                integer,
    draft                     number(3,1),
    capacity_ship             integer null);

create table Position_Ship(
    id_ship                   char(10), constraint fk_ship_position_ship Foreign Key (id_ship) references Ship(imo_code),
    base_date_time  	      date,
    latitude			      number(7,5),
    longitude			      number(7,5),
    sog	    			      number(3,1),
    cog 			          number(3,1),
    heading			          integer,
    transceiver_class         char(1),
    Constraint pk_position_ship Primary Key (id_ship, base_date_time));

create table Port(
    id_port			          char(5) constraint pk_port Primary Key,
    name				      varChar(20),
    continent			      varChar(10),
    country			          varChar(20),
    latitude			      number(11,9),
    longitude			      number(11,9));

create table Warehouse(
    id_warehouse     		  integer constraint pk_warehouse Primary Key,
    name    	   			  varChar(30),
    continent		    	  varChar(20),
    country		         	  varChar(20),
    latitude		    	  number(4,2),
    longitude		    	  number(4,2));

create table Cargo_Manifesto(
    id_container              char(11), constraint fk_id_container_cargo_manifesto Foreign Key (id_container) references Container(id_container),
    date_time                 date,
    id_truck                  integer, constraint fk_truck_cargo_manifesto Foreign Key (id_truck) references Truck(id_truck),
    id_ship                   char(10), constraint fk_ship_cargo_manifesto Foreign Key (id_ship) references Ship(imo_code),
    id_port                   char(5), constraint fk_id_port_cargo_manifesto Foreign Key (id_port) references Port(id_port),
    id_warehouse              integer, constraint fk_id_warehouse_cargo_manifesto Foreign Key (id_warehouse) references Warehouse(id_warehouse),
    position_code             integer(6),
    payload                   number(5,1),
    Constraint pk_cargo_manifesto Primary Key Cargo_Manifesto(id_container, date_time));
