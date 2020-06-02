insert into usr (id, username, password, active)
values (1, 'admin', '$2a$08$ytj5FUgIAtU2NcPrX6nAcePekTR71Pg8H.cC9hPM2WgDsxUIus0s6', true);

insert into user_role (user_id, roles)
values (1, 'USER'),
       (1, 'ADMIN');