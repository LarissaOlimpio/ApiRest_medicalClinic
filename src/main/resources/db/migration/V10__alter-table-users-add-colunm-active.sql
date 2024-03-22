alter table users add active tinyint;
update users set active = 1;
ALTER TABLE users MODIFY active TINYINT NOT NULL;