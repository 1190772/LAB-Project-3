create or replace FUNCTION nCargoManifestsPerYear(idEmployee employee.id_employee%type, year integer) return Integer
is
    v_count integer;
    invalidID exception;

Begin
    SELECT COUNT(*) into v_count from employee e
        where e.id_employee = idEmployee
            and e.role_id = (select r.role_id from role r
                                where r.designation = 'Ship captain');
    if v_count = 0 then
        raise invalidID;
    end if;


    SELECT count(*) into v_count from
    (select distinct cm.id_cargo_manifest from ship s, trip t, cargo_manifest cm
        where s.id_employee_captain = idEmployee
            and s.imo_code = t.ship_imo
            and t.id_trip=cm.id_trip
            and year = (select extract(year from t.date_time_start) from trip t1
                        where t1.id_trip=cm.id_trip));

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

Begin
    SELECT COUNT(*) into v_count from employee e
            where e.id_employee = idEmployee
                and e.role_id = (select r.role_id from role r
                                    where r.designation = 'Ship captain');
        if v_count = 0 then
            raise invalidID;
        end if;

        SELECT count(*) into v_count from ship s, trip t, cargo_manifest cm
                 where s.id_employee_captain = idEmployee
                     and s.imo_code = t.ship_imo
                     and t.id_trip=cm.id_trip
                     and year = (select extract(year from t.date_time_start) from trip t1
                                 where t1.id_trip=cm.id_trip);


    return v_count/nCargoManifestsPerYear(idEmployee, year);
EXCEPTION
    when invalidID then
        return null;

end;
/