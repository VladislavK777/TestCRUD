create sequence product_id_seq;

drop table product;
create table product(
    id int not null default product_id_seq.nextval,
    name varchar(100) not null,
    brand varchar(100) not null,
    price real not null,
    quantity int not null
);

insert into product(name,brand,price,quantity) values('T-shift', 'US POLO', 4000.00, 6);
insert into product(name,brand,price,quantity) values('T-shift', 'Tommy Hilfiger', 3500.00, 4);

create table users (
	username varchar(50) not null,
	password varchar(100) not null,
	enabled bool not null
);

/*pass = 123*/
insert into users(username,password,enabled) values('admin', '$2a$10$ixkF5Da3aaqo3fJ.dTn8ruuDX10GnKM9O5b4C506bA8nlJY9ImT.e',true);
insert into users(username,password,enabled) values('user', '$2a$10$ixkF5Da3aaqo3fJ.dTn8ruuDX10GnKM9O5b4C506bA8nlJY9ImT.e',true);

create table roles (
	username varchar(50) not null,
	role varchar(50) not null,
	foreign key (username) references users(username)
);

insert into roles(username,role) values('admin', 'ROLE_ADMIN');
insert into roles(username,role) values('user', 'ROLE_USER');




