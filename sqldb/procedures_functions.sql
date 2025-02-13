create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace procedure delete_data(u_id integer)
language 'plpgsql'
as $$
    BEGIN
        delete products
        where id = u_id and
        count = 0;
    END;
$$;

create
or replace function f_delete_data(u_id integer)
returns void
language 'plpgsql'
as $$
    BEGIN
        delete products
        where id = u_id and
        count < 1;
    END;
$$;
