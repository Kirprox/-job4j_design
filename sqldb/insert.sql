insert into users(roles_id, description)
values (1, 'Иван Иванов');

insert into roles(description)
values ('Администратор');

insert into rules(description)
values ('Чтение');

insert into roles_rules(rules_id, roles_id)
values (1, 1);

insert into items(user_id, categories_id, states_id, description)
values (1, 1, 1, 'Заявка №1');

insert into categories(description)
values ('технические проблемы');

insert into states(description)
values ('новая');

insert into attachs(items_id, description)
values (1, 'фото');

insert into comments(item_id, description)
values (1,'Проблема подтверждена');
