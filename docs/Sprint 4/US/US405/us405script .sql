CREATE OR REPLACE FUNCTION Average_Occupancy_Rate(imo_Ship ship.imo_code%type, startDate IN Date, endDate IN date) RETURN real
AS
    cursor trips 
    IS
    SELECT id_trip
    FROM Trip
    WHERE ship_imo = imo_Ship and date_time_start >= startDate and date_time_end <= endDate;

tripID integer;
manifestId cargo_manifest.id_cargo_manifest%type;
soma real := 0;
manifestsNumber number := 0;
average real;
tmp number;
capacityShip number;

BEGIN

SELECT capacity_ship INTO capacityShip
FROM ship s
WHERE s.imo_code = imo_Ship;

OPEN trips;
LOOP
    FETCH trips into tripID;
    EXIT when trips%notfound;
    
    SELECT count(*) INTO tmp
    FROM Cargo_Manifest
    WHERE id_trip = tripID;
    
    manifestsNumber := manifestsNumber+1;
    soma := soma + (tmp / capacityShip);

END LOOP;

    average := soma / manifestsNumber;
    average := average * 100;
    average := ROUND( average , 2 );
    return average;
    
END;
/

select Average_Occupancy_Rate('IMO1234567', timestamp '2020-05-10 10:30:00', timestamp '2020-05-26 22:30:00') from dual;