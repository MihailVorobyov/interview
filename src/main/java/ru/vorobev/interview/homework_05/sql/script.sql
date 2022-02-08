# Создать базу данных Student с полями id, name, mark.
DROP SCHEMA IF EXISTS Students;
CREATE SCHEMA Students;
USE Students;

DROP TABLE IF EXISTS students;
CREATE TABLE students (
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL,
    mark INT,
    INDEX id_idx (id ASC) VISIBLE
);

