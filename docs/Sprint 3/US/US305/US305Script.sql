create or replace function getContainersRoute(clientID Client.id_client%type, containerID Container.id_container%type) return sys_refcursor
is
    result sys_refcursor;
    v_count integer;

BEGIN
    SELECT COUNT(*) into v_count from client c
            where c.id_client = clientID;
        if v_count = 0 then
            raise_application_error(-20001, 'The Client ID is not valid!');
        end if;

    SELECT COUNT(*) into v_count from Container c
                where c.id_container = containerID;
            if v_count = 0 then
                raise_application_error(-20002, 'The Container ID is not valid!');
            end if;

    SELECT COUNT(*) into v_count from cargo_manifest cm
                where cm.id_container = containerID
                    and cm.id_client = clientID;
            if v_count = 0 then
                raise_application_error(-20003, 'The Client ID and Container ID combination is not valid!');
            end if;



    open result for select t.ship_imo, t.id_truck, t.id_start_port, t.id_destination_port, t.id_start_warehouse, t.id_destination_warehouse,
                            t.date_time_start, t.date_time_end from trip t, cargo_manifest cm
                            where t.id_trip = cm.id_trip
                                and cm.id_container=containerID
                                and cm.id_client=clientID
                            order by t.date_time_start;
    return result;

END;
/

--Test in java aplication
--    System.out.println(DatabaseFunctions.getContainersRoute(App.getInstance().getCompany().getDatabaseConnection(), "1884441", "BICJ4444445")); 2 stops