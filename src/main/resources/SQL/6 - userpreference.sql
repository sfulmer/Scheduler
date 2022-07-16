CREATE TABLE `schedule`.`userpreference` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `UserId` INT UNSIGNED NOT NULL,
  `TypeId` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC) VISIBLE,
  INDEX `UserPreference_User_idx` (`UserId` ASC) VISIBLE,
  INDEX `UserPreference_Type_idx` (`TypeId` ASC) VISIBLE,
  CONSTRAINT `UserPreference_User`
    FOREIGN KEY (`UserId`)
    REFERENCES `schedule`.`user` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `UserPreference_Type`
    FOREIGN KEY (`TypeId`)
    REFERENCES `schedule`.`types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
