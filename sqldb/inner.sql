create table book(
    id        serial primary key,
    title     varchar(255),
    author_id int references author(id),
    price     decimal(10, 2),
    amount    int
);

create table author(
    id          serial primary key,
    name_author varchar(255)
);

insert into author(name_author)
values ('Булгаков М.А.'),
       ('Достоевский Ф.М.'),
       ('Есенин С.А.'),
       ('Пастернак Б.Л.');

insert into book(title, author_id, price, amount)
values ('Мастер и Маргарита', 1, 670.99, 3),
       ('Мастер и Маргарита', 1, 540.50, 5),
       ('Мастер и Маргарита', 2, 460.00, 10),
       ('Мастер и Маргарита', 2, 799.01, 3),
       ('Мастер и Маргарита', 2, 480.50, 10),
       ('Мастер и Маргарита', 3, 650.00, 15),
       ('Мастер и Маргарита', 3, 570.20, 6),
       ('Мастер и Маргарита', 4, 580.99, 2)

select book.title название, author.name_author автор,
    book.price цена, book.amount количество
from book
inner join author on book.author_id = author.id;

select distinct author.name_author автор
from author
inner join book on author.id = book.author_id
where book.price < 600;

select author.name_author автор, book.title название, book.amount количество
from author
inner join book on author.id = book.author_id
order by количество desc ;






