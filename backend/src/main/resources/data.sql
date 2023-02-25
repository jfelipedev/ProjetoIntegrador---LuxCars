INSERT INTO `users` (`ID`, `email`, `password`, `first_name`, `surname`, `roles`) VALUES
(1, 'joao@mail.com', '$2a$10$qhs4u7Ng2sdaPSv8gIzNKOVpjFJCKwEnd3/GGIBDvz/H/GGh.dDBK', 'João', 'Silva', 0);
INSERT INTO `users` (`ID`, `email`, `password`, `first_name`, `surname`, `roles`) VALUES
(2, 'joao2@mail.com', '$2a$10$qhs4u7Ng2sdaPSv8gIzNKOVpjFJCKwEnd3/GGIBDvz/H/GGh.dDBK', 'João', 'Silva', 1);
INSERT INTO `categories` (`ID`, `descritpion`, `url_image`, `qualification`) VALUES
(1, 'Carros sem o teto', 'http://teste.com/teste.png', 'Conversivel');
INSERT INTO `car` (`ID`, `name_car`, `category_id`) VALUES
(1, 'Audi', 1);