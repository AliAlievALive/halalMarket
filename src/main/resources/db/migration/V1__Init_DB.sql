create table if not exists usr
(
    id       bigint       not null,
    active   bit          not null,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
) engine = InnoDB;

create table if not exists user_role
(
    user_id bigint not null,
    roles   varchar(255)
) engine = InnoDB;

create table if not exists product
(
    id        bigint           not null,
    count     integer          not null,
    filename  varchar(255),
    is_sale   bit              not null,
    made_date datetime(6),
    name      varchar(255),
    price     double precision not null,
    primary key (id)
) engine = InnoDB;

create table if not exists garbage
(
    id                          bigint           not null,
    number                      varchar(255)     not null,
    orders_date                 datetime(6)      not null,
    price                       double precision not null,
    time_to_order_must_be_ready datetime(6)      not null,
    buyer_id                    bigint           not null,
    done                        bit              not null,
    primary key (id)
) engine = InnoDB;

create table if not exists item_in_garbage
(
    id         bigint  not null,
    count      integer not null,
    product_id bigint  not null,
    garbage_id bigint  not null,
    primary key (id)
) engine = InnoDB;

alter table garbage
    add constraint purchase_buyer_fk foreign key (buyer_id) references usr (id);

alter table user_role
    add constraint role_user_fk foreign key (user_id) references usr (id);

alter table item_in_garbage
    add constraint item_garbage_fk foreign key (garbage_id) references garbage (id);

alter table item_in_garbage
    add constraint item_product_fk foreign key (product_id) references product (id);

create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB;

insert into hibernate_sequence
values (1);

insert into hibernate_sequence
values (1);

insert into hibernate_sequence
values (1);

insert into hibernate_sequence
values (1);