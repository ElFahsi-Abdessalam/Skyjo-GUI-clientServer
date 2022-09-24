----------------------
-- Créer les tables
----------------------

CREATE TABLE USERS (
	id	BIGINT			NOT NULL,
        login	VARCHAR(30)		NOT NULL,
	name	VARCHAR(30)		NOT NULL,
	PRIMARY KEY (id)
	);

CREATE TABLE SEQUENCES (
        id      VARCHAR(50)             NOT NULL,
        sValue  numeric(10)             NOT NULL,
        constraint IDSEQUENCE primary key (id)
        );
