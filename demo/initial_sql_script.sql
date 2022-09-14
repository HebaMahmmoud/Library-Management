CREATE DATABASE "Library_management"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
\c users_management;

CREATE TABLE IF NOT EXISTS book
(
	id serial not null
		constraint book_pk
			primary key,
	title varchar(50),
	author varchar(50)

);
