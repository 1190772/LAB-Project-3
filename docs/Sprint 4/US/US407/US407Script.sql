create or replace function getNextMonday return timestamp
is
    day_of_week char(3);
begin

    SELECT TO_CHAR(current_timestamp, 'D') into day_of_week
    FROM DUAL;--Get today's day of the week

    if day_of_week = 2 then return trunc(current_timestamp) + 7;--Monday
        elsif day_of_week = 3 then return trunc(current_timestamp) + 6;--Tuesday
        elsif day_of_week = 4 then return trunc(current_timestamp) + 5;--Wednesday
        elsif day_of_week = 6 then return trunc(current_timestamp) + 3;--Friday
        elsif day_of_week = 5 then return trunc(current_timestamp) + 4;--Thursday
        elsif day_of_week = 7 then return trunc(current_timestamp) + 2;--Saturday
        elsif day_of_week = 1 then return trunc(current_timestamp) + 1;--Sunday
        else raise_application_error(-20001, 'Something went wrong!');
    end if;
end;
/


create or replace function getContainersToLoadInVehicleNextWeek(portID Port.id_port%type) return sys_refcursor
is
    result sys_refcursor;

    next_monday timestamp;
    next_sunday timestamp;


BEGIN

    next_monday := getNextMonday();
    next_sunday := next_monday + 6 + 23/24 + 59/24/60 + 59/24/60/60;

    open result for select t.date_time_start, t.ship_imo, t.id_truck, cm.id_container, cm.position_code, cm.cargo_weight, cm.refrigeration_temperature, c.tare, l.value_length, wh.value_width, wh.value_height
        from Trip t, Cargo_Manifest cm, Container C , ISO iso, Container_Length l, Width_Height wh, Loading_Cargo_Manifest lcm
            where t.id_start_port = portID
                and t.date_time_start>=next_monday
                and t.date_time_start<=next_sunday
                and t.id_trip = cm.id_trip
                and cm.id_container = c.id_container
                and c.iso_code = iso.iso_code
                and iso.length_code = l.length_code
                and iso.width_height_code = wh.width_height_code
                and lcm.id_cargo_manifest = cm.id_cargo_manifest
                and lcm.id_container = cm.id_container
            order by t.date_time_start;

    return result;

END;
/

create or replace function getContainersToUnloadInVehicleNextWeek(portID Port.id_port%type) return sys_refcursor
is
    result sys_refcursor;

    next_monday timestamp;
    next_sunday timestamp;


BEGIN

    next_monday := getNextMonday();
    next_sunday := next_monday + 6 + 23/24 + 59/24/60 + 59/24/60/60;

    open result for select t.date_time_end, t.ship_imo, t.id_truck, cm.id_container, cm.position_code, cm.cargo_weight, cm.refrigeration_temperature, c.tare, l.value_length, wh.value_width, wh.value_height
        from Trip t, Cargo_Manifest cm, Container C , ISO iso, Container_Length l, Width_Height wh, Unloading_Cargo_Manifest ucm
            where t.id_destination_port = portID
                and t.date_time_end>=next_monday
                and t.date_time_end<=next_sunday
                and t.id_trip = cm.id_trip
                and cm.id_container = c.id_container
                and c.iso_code = iso.iso_code
                and iso.length_code = l.length_code
                and iso.width_height_code = wh.width_height_code
                and ucm.id_cargo_manifest = cm.id_cargo_manifest
                and ucm.id_container = cm.id_container
            order by t.date_time_end;

    return result;

END;
/
--Test in java application
--    System.out.println(DatabaseFunctions.getResourcesOfPortNextWeek(App.getInstance().getCompany().getDatabaseConnection(), "IN233"));
--Load 3 containers
--Unload 2 containers

--In case the from the java application is not working
--select getContainersToLoadInVehicleNextWeek('IN233') from dual;
--select getContainersToUnloadInVehicleNextWeek('IN233') from dual;