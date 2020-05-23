use `chat`;

create table `messages` (
    `id` int auto_increment NOT NULL,
    `text` varchar(128) NOT NULL,
    `user_id` int NOT NULL,
    `date_time` TIMESTAMP NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    PRIMARY KEY (`id`)
)