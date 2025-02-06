create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('mammals', 12150, '1758-01-01'),
       ('birds', 3650, '1751-01-01'),
       ('reptiles', 11531, null),
       ('amphibians', 1825, '1898-01-01'),
       ('fish', 3650, '1698-01-01');

select * from fauna where name like '%fish%';

select * from fauna where avg_age between 10000 and 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < ' 1950-01-01';



