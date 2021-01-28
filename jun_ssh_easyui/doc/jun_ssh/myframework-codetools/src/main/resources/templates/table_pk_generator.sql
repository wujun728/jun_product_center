--<ScriptOptions statementTerminator=";"/>

CREATE TABLE table_pk_generator (
	Id INT NOT NULL,
	TABLE_NAME VARCHAR(255),
	PK_VALUE VARCHAR(20),
	PRIMARY KEY (Id)
) ENGINE=MyISAM;

