/*
create sequence product_id_seq;
drop table product;
create table product(
    id int not null default product_id_seq.nextval,
    name varchar(100) not null,
    brand varchar(100) not null,
    price real not null,
    quantity int not null
);
*/
