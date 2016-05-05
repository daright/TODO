DROP TABLE if exists sublist;
DROP TABLE if exists list;
DROP TABLE if exists users;

CREATE TABLE users(
	login VARCHAR(20) PRIMARY KEY,
	hash BLOB NOT NULL,
    salt BLOB NOT NULL,
    itemCount INT(5) DEFAULT 0
);

CREATE TABLE list(
	iditem INT(5) NOT NULL,
    item TEXT NOT NULL,
    checked TINYINT(4) DEFAULT 0,
    login VARCHAR(20), 
	PRIMARY KEY (iditem, login),
	FOREIGN KEY (login) REFERENCES users(login)
);

CREATE TABLE sublist(
	iditem INT(3) NOT NULL,
    idparent INT (5) NOT NULL,
    login VARCHAR(20) NOT NULL,
    item TEXT NOT NULL,
    checked TINYINT(4) DEFAULT 0,
    PRIMARY KEY (iditem, idparent, login),
    FOREIGN KEY (idparent, login) REFERENCES list(iditem, login)
);
