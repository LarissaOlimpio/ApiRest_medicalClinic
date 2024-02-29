alter table patients add active tinyint;
update patients set active = 1;
ALTER TABLE patients MODIFY active TINYINT NOT NULL;