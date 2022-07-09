  CREATE TABLE `schedule`.`setting`
  (	`Id` INT UNSIGNED NOT NULL AUTO_INCREMENT
  ,	`SettingName` VARCHAR(100) NOT NULL DEFAULT ' '
  ,	`Description` VARCHAR(1000) NULL
  ,	PRIMARY KEY (`Id`)
,	UNIQUE INDEX `SettingName_UNIQUE` (`SettingName` ASC) VISIBLE
,	UNIQUE INDEX `Id_UNIQUE` (`Id` ASC) VISIBLE)
COMMENT = 'This holds a single setting - the key if you will for the Settings.  It will be foreign key referenced to indicate a certain setting is assigned a value';
