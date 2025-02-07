create table type(
    id   serial primary key,
    name varchar(255)
);

create table product(
    id           serial primary key,
    name         varchar(255),
    type_id      int references type(id),
    expired_date date,
    price        numeric(8, 2)
);

insert into type(name)
values ('СЫР'),
       ('МОЛОКО'),
       ('МОРОЖЕНОЕ'),
       ('ХЛЕБ'),
       ('ФРУКТЫ');

insert into product(type_id,name, expired_date, price)
values (1, 'сыр маасдам', '2025-05-01', 500),
       (1, 'сыр чеддер', '2025-06-10', 450),
       (1, 'сыр бри', '2024-12-15', 600),
       (2, 'молоко 3.2%', '2025-02-20', 100),
       (2, 'молоко козье', '2025-02-25', 120),
       (3, 'мороженое пломбир', '2025-08-01', 200),
       (3, 'мороженое шоколадное', '2025-07-01', 180),
       (4, 'хлеб белый', '2025-02-08', 50),
       (4, 'хлеб ржаной', '2025-02-07', 45),
       (5, 'яблоко', '2025-03-01', 30),
       (5, 'банан', '2025-03-05', 40);

select * from product
join type on type.id = product.type_id
where type.name = 'СЫР';

select * from product
where name like '%мороженое%';

select * from product
where expired_date <  current_date;

select type.name, max(price) from product
join type on type.id = product.type_id
group by type.name;

select type.name тип, count(product.name) количество from product
join type on type.id = product.type_id
group by type.name;

select product.name from product
join type on type.id = product.type_id
where type.name = 'СЫР' or type.name = 'МОЛОКО';

select type.name from type
join product on type.id = product.type_id
group by type.name
having count(product.id) < 10;

select product.name продукт, type.name тип from product
join type on type.id = product.type_id;













