
CREATE TABLE `biker`(
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `created_time` DATETIME,
    `update_time` DATETIME,
    `biker_name` VARCHAR(255),
    `review_numbers` INTEGER,
    `review_sum` INTEGER
    PRIMARY KEY(`id`)
);