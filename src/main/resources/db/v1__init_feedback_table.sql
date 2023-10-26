
CREATE TABLE `feedback`(
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `created_time` DATETIME,
    `delivery_time` DATETIME,
    `update_time` DATETIME,
    `comment` VARCHAR(255),
    `rate` INTEGER,
    `delivery_id` INTEGER,
    `customer_id` INTEGER,
    `biker_id` INTEGER
    PRIMARY KEY(`id`)
);