create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted)
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure tax();

create trigger tax_trigger_before
    before insert
    on products
    for each row
    execute procedure tax();

    create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create
or replace function log_products()
    returns trigger as
$$
    BEGIN
        INSERT INTO history_of_price (name, price, date)
        VALUES (NEW.name, NEW.price, NOW());
        RETURN NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger log_price_trigger
after insert on products
for each row
execute function log_price_change();









