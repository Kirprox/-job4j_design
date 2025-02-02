--Many to many
create table buy(
    buy_id serial primary key,
    buy_description varchar(255)
);

create table book(
    id serial primary key,
    title varchar(255),
    price int,
    amount int
);

create table buy_book(
    buy_book_id serial primary key,
    buy_id int references buy(buy_id),
    book_id int references book(id)
);

--One to many

create table genre (
    genre_id serial primary key,
    name_genre varchar(255)
);

create table book(
    book_id serial primary key,
    genre_id int references genre(genre_id)
);

--One to one

create table car(
    car_id serial primary key,
    model varchar(255),
    year date
);

create table tech_passport(
    passport_id serial primary key,
    data varchar(255)
);

create table car_tech_passport(
    id serial primary key,
    passport_id int references car(car_id) unique,
    car_id int references car(car_id) unique
);


