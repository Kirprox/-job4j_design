create table departments(
    id   serial primary key,
    name varchar(255)
);

create table employees(
    id             serial primary key,
    name           varchar(255),
    departments_id int references departments(id)
);

create table teens(
    id     serial primary key,
    name   varchar(255),
    gender varchar(255)
);

insert into departments(name)
values ('Отдел разработки'),
       ('Отдел маркетинга'),
       ('Отдел продаж');

insert into employees(name,departments_id)
values('Иван Иванов', 1),
      ('Петр Петров', 2),
      ('Мария Смирнова', 2),
      ('Анна Кузнецова', 1),
      ('Алексей Сирдоров', 1),

insert into teens(name, gender)
values ('Вася', 'М'),
       ('Петя', 'М'),
       ('Коля', 'М'),
       ('Маша', 'F'),
       ('Аня', 'F'),
       ('Оля', 'F');

select * from departments
left join employees on departments.id = employees.departments_id;

select * from departments
right join employees on departments.id = employees.departments_id;

select * from departments
full join employees on departments.id = employees.departments_id;

select * from departments
cross join employees;

select d.name from departments d
left join employees e on d.id = e.departments_id
where e.id is null;

select d.name, e.name,e.departments_id from employees e
left join departments d on e.departments_id = d.id;

select d.name, e.name,e.departments_id from departments d
right join employees e on e.departments_id = d.id;

select t.name, t2.name from teens t
cross join teens t2
where t.gender != t2.gender and t.name < t2.name


