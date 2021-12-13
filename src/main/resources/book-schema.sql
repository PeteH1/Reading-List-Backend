DROP TABLE IF EXISTS `book` CASCADE;
 
CREATE TABLE `book` (
	`id` INTEGER PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(255),
	`author` VARCHAR(255),
	`is_fiction` BOOL,
	`genre` VARCHAR(50),
	`isbn` VARCHAR(50)
	);