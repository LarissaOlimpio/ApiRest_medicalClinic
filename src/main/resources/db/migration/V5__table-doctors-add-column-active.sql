alter table doctors add active tinyint;
update doctors set active = 1;
ALTER TABLE doctors MODIFY active TINYINT NOT NULL;