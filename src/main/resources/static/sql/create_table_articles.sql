DROP TABLE IF EXISTS tb_articles;
create table tb_articles(
id serial primary key,
category_id int,
title varchar(25),
author varchar(25),
description varchar(1000),
image varchar(100),
foreign key(category_id) references tb_categories(id) on delete cascade on update cascade
);