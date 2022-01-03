-- **primeira versão da us **

CREATE OR REPLACE TRIGGER Accomodate_Manifest BEFORE INSERT OR UPDATE ON UNLOADING_CARGO_MANIFEST
                                                                   for each row

declare
warehouse_capacity integer;
number_containers integer;

BEGIN

SELECT capacity INTO warehouse_capacity
FROM warehouse h
WHERE h.id_warehouse = :new.id_warehouse;

SELECT count(*) INTO number_containers
FROM UNLOADING_CARGO_MANIFEST
WHERE id_cargo_manifest = :new.id_cargo_manifest;

IF number_containers > warehouse_capacity THEN
raise_application_error(-20111, 'You have reached the warehouse capacity');
end if;

END Accomodate_Manifest;
/

INSERT INTO WAREHOUSE VALUES(11225, 'Sitio', 'PT', 40, 40, 2 , 'PT345');


INSERT INTO Cargo_Manifest VALUES (98765,'BICU1234561',1231, 120703, 23,  7, 8884441);
INSERT INTO Cargo_Manifest VALUES (98765,'NIKR1352462',1232, 110300, 14,  3, 8884442);
INSERT INTO Cargo_Manifest VALUES (98765,'ADIJ6543223',1233, 100601, 15, -5, 8884443);
INSERT INTO Loading_Cargo_Manifest VALUES (98765,'BICU1234561',11225);
INSERT INTO Loading_Cargo_Manifest VALUES (98765,'NIKR1352462',11225);
INSERT INTO Loading_Cargo_Manifest VALUES (98765,'ADIJ6543223',11225);
INSERT INTO Unloading_Cargo_Manifest VALUES (98765,'BICU1234561',11225);
INSERT INTO Unloading_Cargo_Manifest VALUES (98765,'NIKR1352462',11225);
INSERT INTO Unloading_Cargo_Manifest VALUES (98765,'ADIJ6543223',11225);
    
    