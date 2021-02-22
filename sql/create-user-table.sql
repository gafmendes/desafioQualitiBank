CREATE DATABASE 'demo';
USE demo;

create table client (
	id  int(3) NOT NULL AUTO_INCREMENT,
	name varchar(120) NOT NULL,
	email varchar(220) NOT NULL,
	PRIMARY KEY (id)
);

