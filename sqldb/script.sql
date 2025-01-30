create table book(
    book_id serial primary key,
    title varchar(50),
    price decimal(8,2),
    amount int
);

insert into book (title, price, amount) values ('мир и война', 1080.14, 2);

update book set title = 'война и мир';

delete from book;

select * from book;




