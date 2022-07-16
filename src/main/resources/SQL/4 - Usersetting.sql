CREATE TABLE `schedule`.`usersetting` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `UserId` INT UNSIGNED NOT NULL,
  `SettingId` INT UNSIGNED NOT NULL,
  `Value` VARCHAR(100) NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC) VISIBLE,
  UNIQUE INDEX `UserSetting_UNIQUE` (`UserId` ASC, `SettingId` ASC) VISIBLE,
  INDEX `UserSetting_Setting_idx` (`SettingId` ASC) VISIBLE,
  CONSTRAINT `UserSetting_User`
    FOREIGN KEY (`UserId`)
    REFERENCES `schedule`.`user` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `UserSetting_Setting`
    FOREIGN KEY (`SettingId`)
    REFERENCES `schedule`.`setting` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
