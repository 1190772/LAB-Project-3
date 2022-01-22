CREATE OR REPLACE FUNCTION occupancy_Rate_Per_Manifest(id_Trip trip.id_trip%type, imo_Ship ship.imo_code%type, cargo_manifest_id cargo_manifest.id_cargo_manifest%type) RETURN number
IS
ship_capacity integer;
cargoManifest_count integer;
c_count integer;
invalidShip exception;
idTrip integer;
n_row integer;
counter integer;
n_row integer;
BEGIN


SELECT COUNT(*) into c_count 
    FROM ship s
    WHERE s.imo_code = imo_Ship;
                     
IF c_count = 0 THEN
    raise invalidShip;
    end if;
    
    
    
SELECT capacity_ship INTO ship_capacity
    FROM ship s
    WHERE s.imo_code = imo_Ship;
    
    
SELECT count(*) INTO cargoManifest_count
    FROM cargo_manifest cm, trip t
    WHERE cm.id_trip = id_Trip and t.ship_imo = imo_Ship;
    
    counter = 0;
    n_row = 1;
    

WHILE counter < cargoManifest_count

BEGIN
declare id_cargo_manifest integer;
declare containers_count integer;
declare occupancyRate number;


SELECT count(n_row) INTO id_cargo_manifest
FROM cargo_manifest cm, trip t
WHERE cm.id_trip = id_Trip and t.shipImo = imoShip;

SELECT count(*) INTO containers_count
FROM cargo_manifest cm
WHERE cm.id_cargo_manifest = id_cargo_manifest

SELECT (containers_count / shipCapacity) * 100 INTO occupancyRate
FROM dual;

counter = counter+1;
n_row = n_row+1;

return occupancy_rate;

END;

END;



    
