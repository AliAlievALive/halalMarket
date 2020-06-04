create table if not exists product
(
    id        bigint       not null,
    count     integer      not null,
    made_date date         not null,
    name      varchar(255),
    price     double       not null,
    filename  varchar(255) not null,
    sale      bit          not null,
    primary key (id)
) engine = InnoDB;

create table if not exists usr
(
    id       bigint not null,
    active   bit    not null,
    password varchar(64),
    username varchar(255),
    primary key (id)
) engine = InnoDB;

create table if not exists user_role
(
    user_id bigint not null,
    roles   varchar(255)
) engine = InnoDB;

alter table user_role
    add constraint user_role_user_fk foreign key (user_id) references usr (id);

create table if not exists hibernate_sequence
(
    next_val bigint
) engine = InnoDB;

insert into hibernate_sequence
values (1);

insert into hibernate_sequence
values (1)