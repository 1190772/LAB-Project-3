CREATE or replace function availableShipNextMonday return SYS_REFCURSOR
is
result SYS_REFCURSOR;
list sys_refcursor;
ship char(10);

begin

    open list for
        select s.imo_code
            from ship s, trip t, cargo_manifest cm, unloading_cargo_manifest ucm
            where s.imo_code = t.ship_imo
                and t.id_trip = cm.id_trip
                and cm.id_cargo_manifest = ucm.id_cargo_manifest
                and cm.id_container <> ucm.id_container;


    loop
        fetch list  into ship;
        exit when list%NOTFOUND;

      open result for select s.imo_code imo, sp.latitude lat, sp.longitude
            from ship s, position_ship sp
            where s.imo_code = sp.id_ship
                and s.imo_code=ship
                and sp.base_date_time = (select max(sp1.base_date_time) from position_ship sp1
                                            where s.imo_code = sp.id_ship);

    end loop;
return result;

end;
/