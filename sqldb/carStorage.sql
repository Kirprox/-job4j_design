create table car_bodies(
    id   serial primary key,
    name varchar(255)
);

create table car_engines(
    id   serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id   serial primary key,
    name varchar(255)
);

create table cars(
    id              serial primary key,
    name            varchar(255),
    body_id         int references car_bodies(id),
    engine_id       int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name)
values ('Saloon'),
       ('Hatchback'),
       ('Estate'),
       ('Coupe'),
       ('Pickup'),
       ('CUV'),
       ('Van'),
       ('Truck '),
       ('Roadster');

INSERT INTO car_engines(name) VALUES
    ('1.6L Petrol'),
    ('2.0L Petrol'),
    ('3.0L Diesel'),
    ('Electric'),
    ('Hybrid'),
    ('4.0L V8'),
    ('1.5L Turbo'),
    ('5.0L V12');

INSERT INTO car_transmissions(name) VALUES
    ('Manual'),
    ('Automatic'),
    ('CVT'),
    ('Dual-Clutch'),
    ('Sequential'),
    ('Tiptronic'),
    ('8-Speed Auto'),
    ('9-Speed Auto');

INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES
    ('Toyota Corolla', 1, 1, 1),
    ('Honda Civic', 2, 2, 2),
    ('Ford Focus', 3, 3, 3),
    ('BMW M4', 4, 6, 4),
    ('Chevrolet Silverado', 5, 4, 5),
    ('Audi Q5', 6, 5, 6),
    ('Mercedes Sprinter', 7, 3, 7),
    ('Volvo FH', 8, 3, 1),
    ('Ford Focus', NULL, 2, 1),
    ('BMW 3 Series', 1, NULL, 4),
    ('Tesla Model 3', 3, 4, NULL),
    ('Ford Ranger', NULL, NULL, 6),
    ('Honda CR-V', 6, NULL, NULL),
    ('Nissan Juke', NULL, 6, NULL);

select c.id, c.name model, ct.name transmission, ce.name engine, cb.name body from cars c
    left join car_bodies cb on c.body_id = cb.id
    left join car_engines ce on c.engine_id = ce.id
    left join car_transmissions ct on c.transmission_id = ct.id;

select cb.name from car_bodies cb
left join cars c on cb.id = c.body_id
where c.name is null;

select ce.name from car_engines ce
left join cars c on ce.id = c.engine_id
where c.name is null;

select ct.name from car_transmissions ct
left join cars c on ct.id = c.transmission_id
where c.name is null;