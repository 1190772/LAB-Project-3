CREATE OR REPLACE FUNCTION occupancy_Rate(imoShip ship.imo_code%type, cargo_manifest_id cargo_manifest.id_cargo_manifest%type) RETURN number
IS
c_count integer;
invalidShip exception;
id_cargo_manifest integer;
id_ship char(10);
ship_capacity integer;
number_containers integer;
occupancy_rate number;

BEGIN

SELECT COUNT(*) into c_count 
    FROM ship s
    WHERE s.imo_code = imoShip;
                     
IF c_count = 0 THEN
    raise invalidShip;
    end if;
    
SELECT capacity_ship INTO ship_capacity
FROM ship s
 WHERE s.imo_code = imoShip;
     
SELECT count(*) INTO number_containers
FROM cargo_manifest cm
WHERE cm.id_cargo_manifest = cargo_manifest_id;

SELECT (number_containers / ship_capacity) * 100 INTO occupancy_rate
FROM dual;

return occupancy_rate;

EXCEPTION
WHEN invalidShip
THEN return null;

END;
/

SELECT round( occupancy_Rate('IMO1234567', 22111), 2) as "Occupancy Rate (%)"
from dual;


 

