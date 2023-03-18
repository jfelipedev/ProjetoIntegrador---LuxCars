INSERT INTO `users` (`email`, `password`, `first_name`, `surname`, `roles`) VALUES
('joao@mail.com', '$2a$10$qhs4u7Ng2sdaPSv8gIzNKOVpjFJCKwEnd3/GGIBDvz/H/GGh.dDBK', 'João', 'Silva', 0);
INSERT INTO `users` (`email`, `password`, `first_name`, `surname`, `roles`) VALUES
('joao2@mail.com', '$2a$10$qhs4u7Ng2sdaPSv8gIzNKOVpjFJCKwEnd3/GGIBDvz/H/GGh.dDBK', 'João', 'Silva', 1);
INSERT INTO `categories` (`descritpion`, `url_image`, `qualification`) VALUES
('Carros sem o teto', 'http://teste.com/teste.png', 'Conversivel');
INSERT INTO `categories` (`descritpion`, `url_image`, `qualification`) VALUES
('São carros sedans', 'http://teste.com/sedans.png', 'Sedans');
INSERT INTO cities(`name_city`, `country`) values
('Napoli', 'Italia');
INSERT INTO cities(`name_city`, `country`) values
('São Paulo', 'Brasil');
INSERT INTO `cars` (`name_car`, `descritpion`, `highlight`, `category_id`, `city_id`) VALUES
('Audi', 'Eu sou um audi', 1, 1, 1);
INSERT INTO caracteristics(`name_caracteristcs`, `icon`) values ('potência de 340 cv e torque de 44,1 m', 'testeICon');
INSERT INTO car_caracteristics(`car_id`, `caracteristics_id`) values (1,1);
INSERT INTO images(`title`, `url`, `car_id`) values ('Imagem de um audi', 'http://img.carlux.com/audi.png',1);