drop database if exists CybersoftCRM;
create database CybersoftCRM;
USE CybersoftCRM;

CREATE TABLE IF NOT EXISTS Role (
    id INT NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(50) NOT NULL,
    description TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS User (
    code INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    address VARCHAR(100),
    phone VARCHAR(100),
    avatar VARCHAR(100),
    role_id INT NOT NULL,
    PRIMARY KEY (code)
);
CREATE TABLE IF NOT EXISTS Status(
    id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Project (
    id INT NOT NULL AUTO_INCREMENT,
    project_name VARCHAR(50) NOT NULL,
    project_description TEXT,
    start_date DATE,
    end_date DATE,
    user_code int,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Task(
    id INT NOT NULL AUTO_INCREMENT,
    task_name VARCHAR(50) NOT NULL,
	task_description TEXT,
    start_date DATE,
    end_date DATE,
    user_code INT NOT NULL,
    project_id INT NOT NULL,
    status_id INT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE User ADD FOREIGN KEY (role_id) REFERENCES Role(id)  ON DELETE CASCADE;
ALTER TABLE Task ADD FOREIGN KEY (user_code) REFERENCES User (code)  ON DELETE CASCADE;
ALTER TABLE Task ADD FOREIGN KEY (project_id) REFERENCES Project(id)  ON DELETE CASCADE;
ALTER TABLE Task ADD FOREIGN KEY (status_id) REFERENCES Status(id)  ON DELETE CASCADE;
ALTER TABLE Project ADD FOREIGN KEY (user_code) REFERENCES User(code)  ON DELETE CASCADE;

INSERT INTO Role (role_name,description) values ('admin','Quản trị viên hệ thống');
INSERT INTO Role (role_name,description) values ('leader','Quản lý dự án');
INSERT INTO Role (role_name,description) values ('user','Nhân viên');

INSERT INTO `CybersoftCRM`.`User` (`email`, `password`, `fullname`, `address`, `phone`, `avatar`, `role_id`) VALUES ('khoa@gmail.com', '123', 'Khoa Nguyen', 'ad 1', '0123', 'avt1', '1');
INSERT INTO `CybersoftCRM`.`User` (`email`, `password`, `fullname`, `address`, `phone`, `avatar`, `role_id`) VALUES ('nhi@gmail.com', '123', 'Nhi Thai', 'ad 2', '0938', 'avt2', '2');

INSERT INTO `CybersoftCRM`.`Project` (`project_name`, `project_description`, `start_date`, `end_date`, `user_code`) VALUES ('Dự án CRM', 'Dự án CRM Cybersoft', '2022-08-18', '2022-08-30', '1');
INSERT INTO `CybersoftCRM`.`Project` (`project_name`, `project_description`, `start_date`, `end_date`, `user_code`) VALUES ('Dự án AI', 'Dự án cuối khoá', '2022-08-10', '2022-08-25', '2');


INSERT INTO `CybersoftCRM`.`Status` (`id`, `name`) VALUES ('1', 'Chưa bắt đầu');
INSERT INTO `CybersoftCRM`.`Status` (`id`, `name`) VALUES ('2', 'Đang thực hiện');
INSERT INTO `CybersoftCRM`.`Status` (`id`, `name`) VALUES ('3', 'Đã hoàn thành');

INSERT INTO `CybersoftCRM`.`Task` (`task_name`, `task_description`, `start_date`, `end_date`, `user_code`, `project_id`, `status_id`) VALUES ('Thiết kế DB Game', 'Thiết kế DB game', '2022-08-17', '2022-08-25', '1', '2', '2');
INSERT INTO `CybersoftCRM`.`Task` (`task_name`, `task_description`, `start_date`, `end_date`, `user_code`, `project_id`, `status_id`) VALUES ('Thiết kế DB Game', 'Thiết kế DB game', '2022-08-17', '2022-08-25', '2', '2', '2');
INSERT INTO `CybersoftCRM`.`Task` (`task_name`, `task_description`, `start_date`, `end_date`, `user_code`, `project_id`, `status_id`) VALUES ('Làm giao diện', 'Làm giao diện game', '2022-08-10', '2022-08-16', '1', '2', '3');
INSERT INTO `CybersoftCRM`.`Task` (`task_name`, `task_description`, `start_date`, `end_date`, `user_code`, `project_id`, `status_id`) VALUES ('DB CRM', 'Làm DB CRM', '2022-08-20', '2022-08-30', '2', '3', '1');
