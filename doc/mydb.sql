-- MySQL Script generated by MySQL Workbench
-- 11/12/16 18:49:11
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`java_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`java_user` ;

CREATE TABLE IF NOT EXISTS `mydb`.`java_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(100) NOT NULL,
  `user_password` VARCHAR(255) NOT NULL,
  `user_email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`java_tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`java_tag` ;

CREATE TABLE IF NOT EXISTS `mydb`.`java_tag` (
  `tag_id` INT NOT NULL AUTO_INCREMENT,
  `tag_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tag_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`java_site`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`java_site` ;

CREATE TABLE IF NOT EXISTS `mydb`.`java_site` (
  `site_id` INT NOT NULL AUTO_INCREMENT,
  `site_name` VARCHAR(255) NOT NULL,
  `site_username` VARCHAR(255) NOT NULL,
  `site_password` VARCHAR(255) NOT NULL,
  `java_user_id` INT NOT NULL,
  `java_tag_tag_id` INT NOT NULL,
  PRIMARY KEY (`site_id`, `java_user_id`, `java_tag_tag_id`),
  INDEX `fk_java_site_java_user_idx` (`java_user_id` ASC),
  INDEX `fk_java_site_java_tag1_idx` (`java_tag_tag_id` ASC),
  CONSTRAINT `fk_java_site_java_user`
    FOREIGN KEY (`java_user_id`)
    REFERENCES `mydb`.`java_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_java_site_java_tag1`
    FOREIGN KEY (`java_tag_tag_id`)
    REFERENCES `mydb`.`java_tag` (`tag_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
