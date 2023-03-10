INSERT INTO `users` (`ID`, `email`, `password`, `first_name`, `surname`, `roles`) VALUES
(1, 'joao@mail.com', '$2a$10$qhs4u7Ng2sdaPSv8gIzNKOVpjFJCKwEnd3/GGIBDvz/H/GGh.dDBK', 'João', 'Silva', 0);
INSERT INTO `users` (`ID`, `email`, `password`, `first_name`, `surname`, `roles`) VALUES
(2, 'joao2@mail.com', '$2a$10$qhs4u7Ng2sdaPSv8gIzNKOVpjFJCKwEnd3/GGIBDvz/H/GGh.dDBK', 'João', 'Silva', 1);
INSERT INTO `categories` (`ID`, `descritpion`, `url_image`, `qualification`) VALUES
(1, 'Carros sem o teto', 'http://teste.com/teste.png', 'Conversivel');
INSERT INTO cities(`ID`, `name_city`, `country`) values
(1, 'Napoli', 'Italia');
INSERT INTO `cars` (`ID`, `name_car`, `descritpion`, `highlight`, `category_id`, `city_id`) VALUES
(1, 'Audi', 'Eu sou um audi', 1, 1, 1);
INSERT INTO caracteristics(`name_caracteristcs`, `icon`) values ('potência de 340 cv e torque de 44,1 m', 'testeICon');
INSERT INTO car_caracteristics(`car_id`, `caracteristics_id`) values (1,1);
INSERT INTO images(`title`, `url`, `car_id`) values ('imagem da ferrari gt 400', 'urldaimagem',1);