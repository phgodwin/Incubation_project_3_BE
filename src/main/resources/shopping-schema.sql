DROP TABLE `item`; 
DROP TABLE `cart`; 
CREATE TABLE `cart`(
	`id` INT PRIMARY KEY AUTO_INCREMENT, 
	`customer` VARCHAR 
	
);

CREATE TABLE `item` (
	`id` INT PRIMARY KEY AUTO_INCREMENT, 
	`name` VARCHAR, 
	`price` DOUBLE, 
	`quantity` INT, 
	FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
	



); 