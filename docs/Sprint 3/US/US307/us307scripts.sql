CREATE OR REPLACE TRIGGER Accomodate_Manifest AFTER INSERT OR UPDATE OR DELETE ON TRIP
for each row

declare
numberContainers integer;
warehouse_capacity integer;
ERRO EXCEPTION;


BEGIN

SELECT count(*) into numberContainers
FROM cargo_manifest c
WHERE c.id_cargo_manifest = :new.id_cargo_manifest; 

SELECT capacity into warehouse_capacity
FROM warehouse w
WHERE w.id_warehouse = 

if (numberContainers
END;
    
    