insert into user_details (id, name, birth_date)
values (10001, 'AAA', current_date());

insert into user_details (id, name, birth_date)
values (10002, 'BBB', current_date());

insert into user_details (id, name, birth_date)
values (10003, 'CCC', current_date());

insert into post (id, description, user_id)
values (20001, 'POST A', 10001);

insert into post (id, description, user_id)
values (20002, 'POST B', 10002);

insert into post (id, description, user_id)
values (20003, 'POST C', 10003);

insert into post (id, description, user_id)
values (20004, 'POST BB', 10002);
