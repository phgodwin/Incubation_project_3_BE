DROP TABLE IF EXISTS `item` CASCADE; 
DROP TABLE IF EXISTS `cart` CASCADE; 
DROP TABLE IF EXISTS `past_order` CASCADE;

CREATE TABLE `cart`(
	`id` INT PRIMARY KEY AUTO_INCREMENT, 
	`customer` VARCHAR(255) 
);

CREATE TABLE `item` (
	`id` INT PRIMARY KEY AUTO_INCREMENT, 
	`name` VARCHAR(255), 
	`price` DOUBLE, 
	`quantity` INT, 
	`cart_id` INT,
	FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
	);

CREATE TABLE `past_order`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`customer` VARCHAR(255),
	`purchased` VARCHAR(255)
); 