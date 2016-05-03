DROP TABLE if exists sublist;
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

CREATE TABLE sublist(
	iditem INT(3) NOT NULL,
    idparent INT (5) NOT NULL,
    login VARCHAR(20) NOT NULL,
    item VARCHAR(255) NOT NULL,
    checked TINYINT(4) DEFAULT 0,
    PRIMARY KEY (iditem, idparent, login),
    FOREIGN KEY (idparent, login) REFERENCES list(iditem, login)
);


INSERT INTO users VALUES('admin', 'admin', DEFAULT);

SELECT * FROM list ORDER BY login;
SELECT * FROM sublist;
SELECT * FROM users;
SELECT COUNT(*) FROM sublist s JOIN list l ON s.login = l.login AND s.idparent = l.iditem;
SELECT * FROM sublist WHERE idparent = 1 AND login = 'asd';
SELECT COUNT(*) FROM sublist WHERE idparent = 1 AND login = 'asd';
SELECT COUNT(*) FROM sublist WHERE idparent = 1 AND login = 'asd' AND checked = 1;
SELECT * FROM sublist ;
UPDATE sublist SET checked = 1 WHERE iditem = 1 AND idparent = 1 AND login = 'asd';