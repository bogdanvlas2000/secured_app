create table users
(
    id       int primary key auto_increment,
    username varchar(255) not null unique,
    password varchar(255) not null,
    email    varchar(255) not null unique
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
            references roles (id)
);

insert users(username, password, email)
values ('admin', '$2y$12$vALseyIrXN6a7Iqnpf8rHewBTiAGlIM/EKW0NpZohQq6SJ3xgqn4a', 'admin@mail.com'),
       ('manager', '$2y$12$SDKWCJWx16T5M59NQSZLUuoNgX2SAQYneoXM11eMpbvXY/Vv1ImJ6', 'manager@mail.com'),
       ('user', '$2y$12$liCyowPYRUa6Uf2PIei6AeUONzaWzW1TnpxpkJm21ci44Vd9cBMNa', 'user@mail.com');

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
