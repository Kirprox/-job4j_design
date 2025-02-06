create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices (name, price)
values ('смартфон', 10000.00),
       ('велосипед', 25000.22),
       ('настольная лампа', 1000.00),
       ('часы', 500.55),
       ('датчик co2', 5891.11),
       ('моноколесо', 56800.15),
       ('ноутбук', 18990.99),
       ('наушники', 5200.84)

insert into people(name)
values ('Василий'),
       ('Николай'),
       ('Александр'),
       ('Алексей'),
       ('Владимир'),
       ('Юрий')

insert into devices_people(device_id, people_id)
values (1, 1),
       (2, 1),
       (1, 2),
       (4, 2),
       (1, 3),
       (4, 3),
       (1, 4),
       (5, 4),
       (1, 5),

select avg(price) from devices

select avg(price) from devices
join devices_people on devices.id = devices_people.device_id
join people on devices_people.people_id = people.id
group by people.name

select avg(price) from devices
join devices_people on devices.id = devices_people.device_id
join people on devices_people.people_id = people.id
group by people.name
having avg(price) > 5000









