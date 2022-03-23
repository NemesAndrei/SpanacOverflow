-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema spanacoverflow
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema spanacoverflow
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `spanacoverflow` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `spanacoverflow` ;

-- -----------------------------------------------------
-- Table `spanacoverflow`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spanacoverflow`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `privilege` INT NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `spanacoverflow`.`questions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spanacoverflow`.`questions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `body` VARCHAR(255) NULL DEFAULT NULL,
  `created` DATETIME(6) NULL DEFAULT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `votes` INT NULL DEFAULT '0',
  `userid` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKdymr3kl8xn50w0coai7t10ol4` (`userid` ASC) VISIBLE,
  CONSTRAINT `FKdymr3kl8xn50w0coai7t10ol4`
    FOREIGN KEY (`userid`)
    REFERENCES `spanacoverflow`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `spanacoverflow`.`answers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spanacoverflow`.`answers` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `body` VARCHAR(255) NULL DEFAULT NULL,
  `created` DATETIME(6) NULL DEFAULT NULL,
  `votes` INT NULL DEFAULT '0',
  `questionid` BIGINT NULL DEFAULT NULL,
  `userid` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKd24h2220v89v6vfr9tworifas` (`questionid` ASC) VISIBLE,
  INDEX `FKdqkxed4yteub1faangn302wu8` (`userid` ASC) VISIBLE,
  CONSTRAINT `FKd24h2220v89v6vfr9tworifas`
    FOREIGN KEY (`questionid`)
    REFERENCES `spanacoverflow`.`questions` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FKdqkxed4yteub1faangn302wu8`
    FOREIGN KEY (`userid`)
    REFERENCES `spanacoverflow`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `spanacoverflow`.`answer_votes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spanacoverflow`.`answer_votes` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `vote` INT NULL DEFAULT NULL,
  `answerid` BIGINT NULL DEFAULT NULL,
  `userid` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK4yacfrina2wd61qdenbjuibpq` (`userid` ASC) VISIBLE,
  INDEX `FKli6uwpc61xnt1qngrdq2cuskh` (`answerid` ASC) VISIBLE,
  CONSTRAINT `FK4yacfrina2wd61qdenbjuibpq`
    FOREIGN KEY (`userid`)
    REFERENCES `spanacoverflow`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FKli6uwpc61xnt1qngrdq2cuskh`
    FOREIGN KEY (`answerid`)
    REFERENCES `spanacoverflow`.`answers` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `spanacoverflow`.`tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spanacoverflow`.`tags` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `spanacoverflow`.`question_tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spanacoverflow`.`question_tag` (
  `questionid` BIGINT NOT NULL,
  `tagid` BIGINT NOT NULL,
  PRIMARY KEY (`questionid`, `tagid`),
  INDEX `FKjw6donkhox29a67julkply0vr` (`tagid` ASC) VISIBLE,
  CONSTRAINT `FK32ufutm0jvuecgm32mtq2tm8c`
    FOREIGN KEY (`questionid`)
    REFERENCES `spanacoverflow`.`questions` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FKjw6donkhox29a67julkply0vr`
    FOREIGN KEY (`tagid`)
    REFERENCES `spanacoverflow`.`tags` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `spanacoverflow`.`question_votes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spanacoverflow`.`question_votes` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `vote` INT NULL DEFAULT NULL,
  `questionid` BIGINT NULL DEFAULT NULL,
  `userid` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK93pusfm53rx9x1jhijor5miqa` (`userid` ASC) VISIBLE,
  INDEX `FKqqy499mmxfueihts1hjhn4ke` (`questionid` ASC) VISIBLE,
  CONSTRAINT `FK93pusfm53rx9x1jhijor5miqa`
    FOREIGN KEY (`userid`)
    REFERENCES `spanacoverflow`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FKqqy499mmxfueihts1hjhn4ke`
    FOREIGN KEY (`questionid`)
    REFERENCES `spanacoverflow`.`questions` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
