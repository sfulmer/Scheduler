CREATE TABLE `schedule`.`events`
(	`id` INT NOT NULL AUTO_INCREMENT
,	`Title` VARCHAR(50) NULL DEFAULT ' '
,	`Description` VARCHAR(1000) NOT NULL DEFAULT ' '
,	`start` DATETIME NOT NULL
,	`end` DATETIME NOT NULL
,	`all_day` TINYINT NULL DEFAULT 1
,	user int unsigned not null
,	type int unsigned not null
,	PRIMARY KEY (Id)
, 	CONSTRAINT `Event_Type`
  FOREIGN KEY (type)
  REFERENCES `schedule`.`types` (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, 	CONSTRAINT `Event_User`
  FOREIGN KEY (User)
  REFERENCES `schedule`.`user` (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
  );
