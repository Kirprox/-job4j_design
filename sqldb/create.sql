create table roles(
    id serial,
    description text
);

create table rules(
    id serial,
    description text
);

create table users(
    id serial,
    roles_id int references roles(id),
    description text
);


create table roles_rules(
    id serial,
    rules_id int references rules(id),
    roles_id int references roles(id)
);

create table items(
    id serial,
    users_id int references users(id),
    categories_id int references categories(id),
    states_id int references states(id),
    description text
);

create table comments(
    id serial,
    items_id int references items(id),
    description text
);

create table attachs(
    id serial,
    items_id int references items(id),
    description text
);

create table categories(
    id serial,
    description text
);

create table states(
    id serial,
    description text
);