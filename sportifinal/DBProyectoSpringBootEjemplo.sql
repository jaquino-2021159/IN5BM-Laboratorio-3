DROP DATABASE IF EXISTS DBProyectoSpringBootEjemplo;
CREATE DATABASE DBProyectoSpringBootEjemplo;
USE DBProyectoSpringBootEjemplo;

CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    PRIMARY KEY pk_id(id)
);

SELECT * FROM users;