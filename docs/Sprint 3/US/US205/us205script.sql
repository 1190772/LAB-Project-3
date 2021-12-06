CREATE OR REPLACE FUNCTION Unloaded_container_next_port (idEmployee employee.id_employee%type) RETURN unloaded_container
IS
c_count integer;
invalidID exception;
unloaded_container char;

BEGIN

SELECT COUNT(*) into c_count
    FROM employee e
    WHERE e.id_employee = idEmployee
    AND e.role_id = (Select r.role_id
                     FROM role r
                     WHERE r.designation = 'Ship captain');

IF c_count = 0 THEN
    raise invalidID;
    end if;

SELECT Cargo_Manifest.id_container,Cargo_Manifest.position_code,Container.id_equipment INTO unloaded_container
    FROM Employee, Trip, Cargo_Manifest,Ship,Unloading_Cargo_Manifest,Container
    WHERE Employee.id_employee = idEmployee
    AND Employee.role_id = (Select Role.role_id
                            FROM Role
                            WHERE Role.designation = 'Ship captain')
    AND Employee.id_employee = Ship.id_employee_captain
    AND Ship.imo_code = Trip.ship_imo
    AND Trip.id_trip = Cargo_Manifest.id_trip
    And Cargo_Manifest.id_container = Container.id_container;
    AND Cargo_Manifest.id_container = Unloading_Cargo_Manifest.id_container
    And   Trip.date_time_end is null;
END;







