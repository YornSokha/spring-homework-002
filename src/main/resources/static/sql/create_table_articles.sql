DROP TABLE IF EXISTS articles_tb;
create table articles_tb(
id serial primary key,
category int,
title varchar(25),
author varchar(25),
description varchar(250),
image varchar(100),
foreign key(category) references categories_tb(id) on delete cascade on update cascade
);