CREATE OR REPLACE FUNCTION Container_situation(id container.id_container%type) RETURN varchar 
is 
max_date1 TIMESTAMP; 
manifest_type1 integer; 
id_cargo_manifest1 integer;
id_trip1 integer;
id_ship1 char(10);
id_port1 char(5);
name_ship1 varchar(30);
name_port1 varchar(20);
NO_DATA_FOUND exception;

BEGIN 
 
SELECT max(t.date_time_start) INTO max_date1 
FROM cargo_manifest cm , trip t
WHERE cm.id_container=id and cm.id_trip=t.id_trip;

IF max_date1 = null
    THEN
        RAISE NO_DATA_FOUND;
END IF;

SELECT cm.id_cargo_manifest INTO id_cargo_manifest1
FROM cargo_manifest cm, trip t
WHERE cm.id_trip=t.id_trip and t.date_time_start=max_date1;

SELECT count(id_cargo_manifest1) INTO manifest_type1
FROM unloading_cargo_manifest uc
WHERE id_cargo_manifest1=uc.id_cargo_manifest and id=uc.id_container;


IF manifest_type1 = 0
    THEN 
       
        SELECT t.ship_imo INTO id_ship1
        FROM cargo_manifest cm, trip t
        WHERE cm.id_trip=t.id_trip and t.date_time_start=max_date1;

        SELECT ship.name_ship INTO name_ship1
        FROM ship
        WHERE ship.imo_code=id_ship1;
        
        RETURN CONCAT ('Ship: ' ,name_ship1);
        
    ELSE 
        
        SELECT t.id_destination_port INTO id_port1
        FROM cargo_manifest cm, trip t
        WHERE cm.id_trip=t.id_trip and t.date_time_start=max_date1;
    
        SELECT name INTO name_port1
        FROM port p
        WHERE p.id_port = id_port1;
        
        RETURN CONCAT ('Port: ' ,name_port1);
        
    END IF; 
    
    
    EXCEPTION 
    WHEN NO_DATA_FOUND THEN 
 
        DBMS_OUTPUT.PUT_LINE('No data found'); 
        RETURN null; 
        
END; 
/

SELECT Container_situation('BICU1234561') as "Container´s current situation:"
from dual;



 

