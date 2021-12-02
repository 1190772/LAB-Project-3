create or replace FUNCTION nCargoManifestsPerYear(idEmployee employee.id_employee%type, year integer) return Integer
is
    v_count integer;
    invalidID exception;

Begin
    SELECT COUNT(*) into v_count from employee e
        where e.id_employee = idEmployee
            and e.role_id = (select r.role_id from role r
                                where r.designation = 'Ship Captain');
    if v_count = 0 then
        raise invalidID;
    end if;

    SELECT count(*) into v_count from ship s, trip t, trip_manifests tm, cargo_manifest cm
        where s.id_employee_captain = idEmployee
            and s.imo_code = t.ship_imo
            and t.id_trip=tm.id_trip
            and tm.id_cargo_manifest=cm.id_cargo_manifest
            and year = (select extract(year from cm.date_time_start) from cargo_manifest cm1
                        where tm.id_cargo_manifest=cm1.id_cargo_manifest);

    return v_count;
EXCEPTION
    when invalidID then
        return null;

end;
/

create or replace FUNCTION averageContainersPerCargoPerYear(idEmployee employee.id_employee%type, year integer) return number
is
    v_count integer;
    invalidID exception;
    result number(7,2);

Begin
    SELECT COUNT(*) into v_count from employee e
            where e.id_employee = idEmployee
                and e.role_id = (select r.role_id from role r
                                    where r.designation = 'Ship Captain');
        if v_count = 0 then
            raise invalidID;
        end if;

        SELECT avg(count(*)) into result from ship s, trip t, trip_manifests tm, cargo_manifest cm
                where s.id_employee_captain = idEmployee
                    and s.imo_code = t.ship_imo
                    and t.id_trip=tm.id_trip
                    and tm.id_cargo_manifest=cm.id_cargo_manifest
                    and year = (select extract(year from cm.date_time_start) from cargo_manifest cm1
                                where tm.id_cargo_manifest=cm1.id_cargo_manifest)
                group by cm.id_cargo_manifest;

    return result;
EXCEPTION
    when invalidID then
        return null;

end;
/