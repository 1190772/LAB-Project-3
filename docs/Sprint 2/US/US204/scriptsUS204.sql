CREATE OR REPLACE FUNCTION Container_situation (id container.id_container%type) RETURN varchar 
is 
max_date date; 
manifest_type number; 
id_cargo_manifest integer;
id_trip integer;
NO_DATA_FOUND exception;
id_ship char(10);
id_port char(5);
name_ship varchar(30);
name_port varchar(20);

BEGIN 
 
SELECT max(t.date_time_start) INTO max_date 
FROM cargo_manifest cm , trip t
WHERE cm.id_container=id and cm.id_trip=t.id_trip;

IF max_date = null
    THEN
        RAISE NO_DATA_FOUND;
END IF;

SELECT cm.id_cargo_manifest INTO id_cargo_manifest
FROM cargo_manifest cm, trip t
WHERE cm.id_trip=t.id_trip and t.date_time_start=max_date;

SELECT count() INTO manifest_type
FROM unloading_cargo_manifest uc
WHERE id_cargo_manifest=uc.id_cargo_manifest and id=uc.id_container;



IF manifest_type = 0 
    THEN 
        -- Loading or Cargo, which means you need to know the ship that the cargo is in   
        SELECT t.ship_imo INTO id_ship
        FROM cargo_manifest cm, trip t
        WHERE cm.id_trip=t.id_trip and t.date_time_start=max_date;

        SELECT ship.name_ship INTO name_ship
        FROM ship
        WHERE ship.imo_code=id_ship;
        
        RETURN name_ship;
        
    ELSE 
        -- Unloading, which means you need to know the dock that the cargo is in  
        SELECT t.id_destination_port INTO id_port
        FROM cargo_manifest cm, trip t
        WHERE cm.id_trip=t.id_trip and t.date_time_start=max_date;
    
        SELECT port.name INTO name_port
        FROM port
        WHERE port.id_port = id_port;
        
        RETURN name_port;
        
    END IF; 
    
    
    EXCEPTION 
    WHEN NO_DATA_FOUND THEN 
 
        DBMS_OUTPUT.PUT_LINE('No data found'); 
        RETURN null; 
        
END; 


SET SERVEROUTPUT ON;
DECLARE
result varchar(100);
BEGIN 

SELECT count() INTO manifest_type
FROM unloading_cargo_manifest uc
WHERE id_cargo_manifest=uc.id_cargo_manifest and id=uc.id_container;

result = Container_situation('BICU1234561');
DBMS_OUTPUT.PUT_LINE(result);

END;



 

