CREATE DATABASE IF NOT EXISTS comexport;

USE comexport;

CREATE TABLE IF NOT EXISTS role (
	Id smallint unsigned NOT NULL AUTO_INCREMENT,
    Description varchar(255),
	CreatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	UpdatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	Enabled boolean,
    PRIMARY KEY (Id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS User (
	Id smallint unsigned NOT NULL AUTO_INCREMENT,
	Name varchar(255) unsigned NOT NULL,
	Email varchar(200) unsigned NOT NULL,
	Birthdate timestamp unsigned NOT NULL,
	CreatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	UpdateAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	Enabled boolean,
    primary key (Id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS Reputation_User (
	Id_User smallint unsigned NOT NULL AUTO_INCREMENT,
    Score double,
	CreatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UpdatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	primary key (Id_User)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS Flags(
	Id smallint unsigned NOT NULL AUTO_INCREMENT,
	Description varchar(255),
	CreatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	UpdatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	Enabled boolean,
    constraint primary key(Id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS Question (
	Id smallint unsigned NOT NULL AUTO_INCREMENT,
	Id_User smallint,
	Id_Flag smallint,
	Comment varchar(255),
	Resolved boolean,
	CreatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	UpdatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(Id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS Answer(
	Id smallint unsigned NOT NULL AUTO_INCREMENT,
	Id_User smallint,
	Id_Question smallint,
	Comment varchar(255),
	CreatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	UpdatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(Id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS Vote_Answer (
	Id_Question smallint unsigned NOT NULL,
	Id_Answer smallint unsigned NOT NULL,
	Id_User smallint unsigned NOT NULL,
	Score double,
	CreatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	UpdatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;