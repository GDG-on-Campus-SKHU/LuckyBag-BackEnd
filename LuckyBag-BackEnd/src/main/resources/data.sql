use luckybag;
insert into color(id, color_name)
values (1,'빨강'),(2,'파랑'),(3,'초록');
insert into member(has_lucky_bag, member_id, member_name, member_password, nickname)
values ('Y','test123', '김자경', 'test123','자경공주'),('N','test234', '전홍영', 'test234','홍홍홍');
insert into luckybag(create_date, last_modified_date, color_id, comment, like_count, member_id)
values (CURRENT_DATE,CURRENT_DATE,1,'안녕하세요',2,1);
