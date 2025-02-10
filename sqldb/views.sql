create table students (
    id serial primary key,
    name varchar(100) not null
);

create table authors (
    id serial primary key,
    name varchar(100) not null
);

create table books (
    id serial primary key,
    title varchar(255) not null ,
    author_id int references authors(id)
);

create table orders (
    id serial primary key,
    student_id int references students(id),
    book_id int references books(id),
    order_date date not null
);

insert into students (name) values
('Иван Петров'),
('Анна Смирнова'),
('Сергей Кузнецов'),
('Мария Иванова');

insert into authors (name) values
('Лев Толстой'),
('Фёдор Достоевский'),
('Антон Чехов'),
('Александр Пушкин');

insert into books (title, author_id) values
('Война и мир', 1),
('Анна Каренина', 1),
('Преступление и наказание', 2),
('Идиот', 2),
('Вишнёвый сад', 3),
('Человек в футляре', 3),
('Евгений Онегин', 4);

insert into orders (student_id, book_id, order_date) values
(1, 1, '2024-01-10'),
(1, 2, '2024-01-15'),
(2, 3, '2024-01-12'),
(2, 4, '2024-01-18'),
(3, 5, '2024-02-05'),
(3, 6, '2024-02-10'),
(4, 7, '2024-02-15');

create view show_students_with_multiple_books as
select s.name as student_name,a.name as author_name,
    count(b.id) as book_count, string_agg(b.title, ', ') as book_titles,
    min(o.order_date) as first_order_date, max(o.order_date) as last_order_date
from students as s
join orders as o on s.id = o.student_id
join books as b on o.book_id = b.id
join authors as a on b.author_id = a.id
group by s.name, a.name
having count(b.id) >= 2;

select * from show_students_with_multiple_books




