drop table product;
create table product(
    id int generated by default as identity,
    name varchar(100) not null,
    brand varchar(100) not null,
    price real not null,
    quantity int not null
);

insert into product(name,brand,price,quantity) values('T-shirt', 'US POLO', 4000.00, 6);
insert into product(name,brand,price,quantity) values('T-shirt', 'Tommy Hilfiger', 3500.00, 4);