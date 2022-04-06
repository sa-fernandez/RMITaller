-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema test
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `test` ;

-- -----------------------------------------------------
-- Table `test`.`profesor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`profesor` (
  `idProfesor` VARCHAR(30) NOT NULL,
  `contrasena` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`idProfesor`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test`.`asignatura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`asignatura` (
  `idAsignatura` VARCHAR(30) NOT NULL,
  `profesor_idProfesor` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`idAsignatura`, `profesor_idProfesor`),
  INDEX `fk_asignatura_profesor1_idx` (`profesor_idProfesor` ASC) VISIBLE,
  CONSTRAINT `fk_asignatura_profesor1`
    FOREIGN KEY (`profesor_idProfesor`)
    REFERENCES `test`.`profesor` (`idProfesor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test`.`estudiante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`estudiante` (
  `idEstudiante` VARCHAR(30) NOT NULL,
  `contrasena` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`idEstudiante`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test`.`estudiantexasignatura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`estudiantexasignatura` (
  `Estudiante_idEstudiante` VARCHAR(30) NOT NULL,
  `Asignatura_idAsignatura` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`Estudiante_idEstudiante`, `Asignatura_idAsignatura`),
  INDEX `fk_Estudiante_has_Asignatura_Asignatura1_idx` (`Asignatura_idAsignatura` ASC) VISIBLE,
  INDEX `fk_Estudiante_has_Asignatura_Estudiante_idx` (`Estudiante_idEstudiante` ASC) VISIBLE,
  CONSTRAINT `fk_Estudiante_has_Asignatura_Asignatura1`
    FOREIGN KEY (`Asignatura_idAsignatura`)
    REFERENCES `test`.`asignatura` (`idAsignatura`),
  CONSTRAINT `fk_Estudiante_has_Asignatura_Estudiante`
    FOREIGN KEY (`Estudiante_idEstudiante`)
    REFERENCES `test`.`estudiante` (`idEstudiante`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test`.`nota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`nota` (
  `idNota` VARCHAR(30) NOT NULL,
  `valor` DOUBLE NULL,
  `estudiantexasignatura_Estudiante_idEstudiante` VARCHAR(30) NOT NULL,
  `estudiantexasignatura_Asignatura_idAsignatura` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`idNota`, `estudiantexasignatura_Estudiante_idEstudiante`, `estudiantexasignatura_Asignatura_idAsignatura`),
  INDEX `fk_nota_estudiantexasignatura1_idx` (`estudiantexasignatura_Estudiante_idEstudiante` ASC, `estudiantexasignatura_Asignatura_idAsignatura` ASC) VISIBLE,
  CONSTRAINT `fk_nota_estudiantexasignatura1`
    FOREIGN KEY (`estudiantexasignatura_Estudiante_idEstudiante` , `estudiantexasignatura_Asignatura_idAsignatura`)
    REFERENCES `test`.`estudiantexasignatura` (`Estudiante_idEstudiante` , `Asignatura_idAsignatura`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;