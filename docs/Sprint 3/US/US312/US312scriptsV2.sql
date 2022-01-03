-- ** erro de compilação , us incompleta** 

CREATE OR REPLACE FUNCTION Container_situation(id container.id_container%type, owner container.id_owner%type) RETURN varchar 
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
NULL_ID exception;
containerID varchar(255);
ownerID varchar(255);
INVALID_CLIENT exception;

BEGIN 

SELECT id_container INTO containerID
FROM CONTAINER 
WHERE id_container = container.id_container;

IF containerID IS NULL THEN
raise NULL_ID;
end if;

SELECT id_owner INTO ownerID
FROM CONTAINER
WHERE id_owner = container.id_owner;

IF ownerID = container.id_owner THEN
 
 
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
    
ELSE 
RAISE INVALID_CLIENT;
END IF;




    EXCEPTION 
    WHEN NO_DATA_FOUND THEN 
 
        DBMS_OUTPUT.PUT_LINE('No data found'); 
        RETURN null; 
        
    WHEN NULL_ID THEN
       
        DBMS_OUTPUT.PUT_LINE(' 10 - INVALID CONTAINER ID'); 
        RETURN null;
        
    WHEN INVALID_CLIENT THEN
        DBMS_OUTPUT.PUT_LINE(' 11 - CONTAINER IS NOT LEASED BY CLIENT'); 
        RETURN null;
        
        
        
END; 
/
SELECT Container_situation('BICU1234561', 'BIC') as "Container´s current situation:"
from dual;

