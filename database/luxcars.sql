CREATE DATABASE luxcars;

USE luxcars;

CREATE TABLE customers (

id_customer BIGINT (20) AUTO_INCREMENT NOT NULL PRIMARY KEY,
email VARCHAR (50) NOT NULL,
password VARCHAR (50) NOT NULL, 
first_name VARCHAR (50) NOT NULL,
surname VARCHAR (50) NOT NULL

);

CREATE TABLE categories (

id_category BIGINT (20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
descritpion VARCHAR (200) NOT NULL

);

CREATE TABLE car (

id_car BIGINT (20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
name_car VARCHAR (100) NOT NULL,
description VARCHAR (100) NOT NULL,
category_id BIGINT,
FOREIGN KEY (category_id) REFERENCES categories(id_category)

);
