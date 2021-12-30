create or replace PROCEDURE ViewOccupancyRate(id_warehouse warehouse.id_warehouse%type, currendDate IN date, containersIn OUT INTEGER, containersOut OUT INTEGER, containersInB OUT INTEGER, containersOutB OUT INTEGER) 
AS

currentDate DATE;
minDate DATE:= TRUNC(currentDate);
maxDate DATE:= TRUNC(currentDate)+30;
warehouse_capacity integer;
fill integer;
occupancy_rate number;
    
BEGIN

    SELECT count(*) into containersIn  
        FROM CARGO_MANIFEST JOIN TRIP ON cargo_manifest.id_cargo_manifest = trip.id_trip RIGHT JOIN LOADING_CARGO_MANIFEST on cargo_manifest.id_cargo_manifest = loading_cargo_manifest.id_cargo_manifest
    WHERE trip.id_destination_port = id_warehouse AND trip.date_time_end>minDate and trip.date_time_end<maxDate;
    
    SELECT count(*) into containersOut  
        FROM CARGO_MANIFEST JOIN TRIP ON cargo_manifest.id_cargo_manifest = trip.id_trip RIGHT JOIN UNLOADING_CARGO_MANIFEST on cargo_manifest.id_cargo_manifest = unloading_cargo_manifest.id_cargo_manifest
    WHERE trip.id_destination_warehouse = id_warehouse AND trip.date_time_end>minDate and trip.date_time_end<maxDate;
    
    SELECT count(*) into containersInB  
        FROM CARGO_MANIFEST JOIN TRIP ON cargo_manifest.id_cargo_manifest = trip.id_trip RIGHT JOIN LOADING_CARGO_MANIFEST on cargo_manifest.id_cargo_manifest = loading_cargo_manifest.id_cargo_manifest
    WHERE trip.id_destination_warehouse = id_warehouse AND trip.date_time_end>minDate and trip.date_time_end<maxDate;
    
    SELECT count(*) into containersOutB  
        FROM CARGO_MANIFEST JOIN TRIP ON cargo_manifest.id_cargo_manifest = trip.id_trip RIGHT JOIN LOADING_CARGO_MANIFEST on cargo_manifest.id_cargo_manifest = loading_cargo_manifest.id_cargo_manifest
    WHERE trip.id_destination_warehouse = id_warehouse AND trip.date_time_end>minDate and trip.date_time_end<maxDate;
    
    fill := containersInB - containersOutB + containersIn - containersOut;

    SELECT capacity INTO warehouse_capacity 
    FROM warehouse h
    WHERE h.id_warehouse = id_warehouse;
    
    occupancy_rate := (fill/warehouse_capacity)*100;
END;
    
    