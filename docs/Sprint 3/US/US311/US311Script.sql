create or replace function getContainersOfShip(shipIMO ship.imo_code%type) return sys_refcursor
is
    result sys_refcursor;

BEGIN
    open result for select cm.id_container, cm.refrigeration_temperature, cm.position_code, cm.cargo_weight
                    from Cargo_Manifest cm, Trip t
                    where cm.id_trip = t.id_trip
                        and  t.ship_imo= shipIMO
                        and t.date_time_start <= CURRENT_TIMESTAMP
                        and t.date_time_end >= CURRENT_TIMESTAMP;
    return result;

END;
/

--Test in java aplication
--    System.out.println(DatabaseFunctions.getContainersOfShip(App.getInstance().getSql().getDatabaseConnection(), "IMO1111111")); 4 containers
--    System.out.println(DatabaseFunctions.getContainersOfShip(App.getInstance().getSql().getDatabaseConnection(), "IMO1234567")); 0 containers
