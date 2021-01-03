create table users
(
    id       int primary key auto_increment,
    username varchar(255) not null unique,
    password varchar(255) not null,
    email    varchar(255) not null unique,
    active   int
);

create table roles
(
    id   int primary key auto_increment,
    name varchar(50) not null unique
);

create table users_roles
(
    id      int primary key auto_increment,
    user_id int,
    role_id int,
    constraint user_fk
        foreign key (user_id)
            references users (id),
    constraint role_fk
        foreign key (role_id)
            references roles (id),
    constraint unique_user_role
        unique (user_id, role_id)
);

insert users(username, password, email, active)
values ('admin', '$2a$04$06tfmJjiialvpszQzTWMxOdjhBLah1e1IOJo4NBLHWFBtHT2DKria', 'admin@mail.com', 1),
       ('manager', '$2a$04$9Z5VQHfwYDph/avhfZG9Ne92ilK2pzfDQELj6xsgZ7CIeSexrmveG', 'manager@mail.com', 1),
       ('user', '$2a$04$aQEYDZ9iNVaAwp/g8SXroORLzMFXo1f1.X1twZQ40u8a3fEWJ1v1u', 'user@mail.com', 1);

insert roles(name)
values ('ROLE_ADMIN'),
       ('ROLE_MANAGER'),
       ('ROLE_USER');

insert users_roles(user_id, role_id)
values ((select users.id from users where users.username = 'admin'),
        (select roles.id from roles where roles.name = 'ROLE_ADMIN')),
       ((select users.id from users where users.username = 'manager'),
        (select roles.id from roles where roles.name = 'ROLE_MANAGER')),
       ((select users.id from users where users.username = 'user'),
        (select roles.id from roles where roles.name = 'ROLE_USER'));
