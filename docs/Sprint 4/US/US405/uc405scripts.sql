CREATE OR REPLACE FUNCTION Average_Occupancy_Rate(idTrip trip.id_trip%type, imo_Ship ship.imo_code%type, startDate IN Date, endDate IN date) RETURN real
AS
    cursor cargomanifests 
    IS
    SELECT id_cargo_manifest 
    FROM cargo_manifest cm, trip t
    WHERE t.ship_imo = imo_Ship and cm.id_trip = idTrip and t.date_time_start >= startDate and t.date_time_end <= endDate;
    
manifestId cargo_manifest.id_cargo_manifest%type;
soma real;
manifestsNumber number := 0;
average real;
tmp number;
capacityShip number;

BEGIN

SELECT capacity_ship INTO capacityShip
FROM ship s
WHERE s.imo_code = imo_Ship;

OPEN cargomanifests;
LOOP
    EXIT when cargomanifests%notfound;
    SELECT count(*) INTO tmp
    FROM cargo_manifest cm
    WHERE cm.id_trip = idTrip;
    
    manifestsNumber := manifestsNumber+1;
    soma := soma + (tmp / capacityShip);

END LOOP;

    average := soma / manifestsNumber;
    return average*100;
    
END;

 




    
