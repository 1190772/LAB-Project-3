CREATE OR REPLACE FUNCTION occupancy_Rate_GivenMoment(imoShip ship.imo_code%type) RETURN number
IS
max_date TIMESTAMP;
cargo_manifest integer;

BEGIN

SELECT max(t.date_time_start) INTO max_date 
FROM  trip t
WHERE t.ship_imo=imoShip;

SELECT cm.id_cargo_manifest INTO cargo_manifest
FROM trip t, cargo_manifest cm
WHERE t.id_trip = cm.id_trip AND t.date_time_start = max_date;

return occupancy_Rate(imoShip,cargo_manifest);

END;
/
SELECT round( occupancy_Rate_GivenMoment('IMO4444444'),2 ) as "Occupancy Rate at Given Moment (%):"
from dual;