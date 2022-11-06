DROP TABLE IF EXISTS Task;
CREATE TABLE Task(
                     id SERIAL NOT NULL,
                     task_name VARCHAR(50) NOT NULL,
                     task_description TEXT,
                     start_date DATE,
                     end_date DATE,
                     user_code INT NOT NULL,
                     project_id INT NOT NULL,
                     status_id INT NOT NULL,
                     PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Status;
CREATE TABLE Status(
                       id INT NOT NULL,
                       name VARCHAR(50) NOT NULL,
                       PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Project;
CREATE TABLE Project (
                         id SERIAL NOT NULL,
                         project_name VARCHAR(50) NOT NULL,
                         project_description TEXT,
                         start_date DATE,
                         end_date DATE,
                         user_code int,
                         PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
                       code SERIAL NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       fullname VARCHAR(100) NOT NULL,
                       address VARCHAR(100),
                       phone VARCHAR(100),
                       avatar VARCHAR(100),
                       role_id INT NOT NULL,
                       PRIMARY KEY (code)
);

DROP TABLE IF EXISTS Role;
CREATE TABLE Role (
                      id SERIAL NOT NULL,
                      role_name VARCHAR(50) NOT NULL,
                      description TEXT,
                      PRIMARY KEY (id)
);


ALTER TABLE Users ADD FOREIGN KEY (role_id) REFERENCES Role(id)  ON DELETE CASCADE;
ALTER TABLE Task ADD FOREIGN KEY (user_code) REFERENCES Users (code)  ON DELETE CASCADE;
ALTER TABLE Task ADD FOREIGN KEY (project_id) REFERENCES Project(id)  ON DELETE CASCADE;
ALTER TABLE Task ADD FOREIGN KEY (status_id) REFERENCES Status(id)  ON DELETE CASCADE;
ALTER TABLE Project ADD FOREIGN KEY (user_code) REFERENCES Users(code)  ON DELETE CASCADE;

INSERT INTO Role (role_name,description) values ('admin','Quản trị viên hệ thống');
INSERT INTO Role (role_name,description) values ('leader','Quản lý dự án');
INSERT INTO Role (role_name,description) values ('user','Nhân viên');

INSERT INTO Users (email, password, fullname, address, phone, avatar, role_id) VALUES ('khoa@gmail.com', '123', 'Khoa Nguyen', 'ad 1', '0123', 'https://res.cloudinary.com/dxjbg114a/image/upload/v1661893008/cyw07lhssguyueydfo8r.jpg', '1');
INSERT INTO Users (email, password, fullname, address, phone, avatar, role_id) VALUES ('nhi@gmail.com', '123', 'Nhi Thai', 'ad 2', '0938', 'https://res.cloudinary.com/dxjbg114a/image/upload/v1661893909/avt-cute_i9v4do.jpg', '2');

INSERT INTO Project (project_name, project_description, start_date, end_date, user_code) VALUES ('Dự án CRM', 'Dự án CRM Cybersoft', '2022-08-18', '2022-08-30', '1');
INSERT INTO Project (project_name, project_description, start_date, end_date, user_code) VALUES ('Dự án AI', 'Dự án cuối khoá', '2022-08-10', '2022-08-25', '2');
INSERT INTO Project (project_name, project_description, start_date, end_date, user_code) VALUES ('Dự án Game đoán số', 'Game đoán số', '2022-08-10', '2022-08-25', '2');


INSERT INTO Status (id, name) VALUES ('1', 'Chưa bắt đầu');
INSERT INTO Status (id, name) VALUES ('2', 'Đang thực hiện');
INSERT INTO Status (id, name) VALUES ('3', 'Đã hoàn thành');

INSERT INTO Task (task_name, task_description, start_date, end_date, user_code, project_id, status_id) VALUES ('Thiết kế DB Game', 'Thiết kế DB game', '2022-08-17', '2022-08-25', '1', '2', '2');
INSERT INTO Task (task_name, task_description, start_date, end_date, user_code, project_id, status_id) VALUES ('Thiết kế DB Game', 'Thiết kế DB game', '2022-08-17', '2022-08-25', '2', '2', '2');
INSERT INTO Task (task_name, task_description, start_date, end_date, user_code, project_id, status_id) VALUES ('Làm giao diện', 'Làm giao diện game', '2022-08-10', '2022-08-16', '1', '2', '3');
INSERT INTO Task (task_name, task_description, start_date, end_date,user_code, project_id, status_id) VALUES ('DB CRM', 'Làm DB CRM', '2022-08-20', '2022-08-30', '2', '3', '1');
