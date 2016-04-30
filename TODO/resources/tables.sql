DROP TABLE if exists list;
DROP TABLE if exists users;

CREATE TABLE users(
	login VARCHAR(20) PRIMARY KEY,
    password VARCHAR(30) NOT NULL,
    itemCount INT(5) DEFAULT 0
);

CREATE TABLE list(
	iditem INT(5) NOT NULL,
    item VARCHAR(255) NOT NULL,
    checked TINYINT(4) DEFAULT 0,
    login VARCHAR(20),
	PRIMARY KEY (iditem, login),
	FOREIGN KEY (login) REFERENCES users(login)
);

INSERT INTO users VALUES('admin', 'admin', DEFAULT);

SELECT * FROM list ORDER BY login;
SELECT * FROM users;