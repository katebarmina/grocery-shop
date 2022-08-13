create database shop;
use shop;


create table category (
id int auto_increment,
name varchar (40),
primary key(id)
);

create table products (
id int auto_increment,name varchar(300) not null,
price double not null,
brand varchar(300),
category_id int not null,
image varchar(300),
primary key(id),
foreign key(category_id) references category(id)
);

create table users (
id int auto_increment not null,
email varchar(40)not null,
password varchar(300) not null,
role varchar(100) not null,
salt blob not null,
primary key(id)
);

create table orders (
id int auto_increment,
user_id int not null,
order_status varchar(100) not null,
primary key(id),
foreign key(user_id) references users(id)
);

create table shopping_cart (
id int auto_increment,
user_id int not null,
primary key(id),
foreign key(user_id) references users(id)
);

create table cart_item (
id int auto_increment,
cart_id int not null,
product_id int not null,
quantity int not null,
primary key(id),
foreign key(cart_id) references shopping_cart(id),
foreign key(product_id) references products(id)
);
