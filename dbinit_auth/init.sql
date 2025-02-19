CREATE TABLE role (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(100) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      token VARCHAR(255),
                      role_id BIGINT DEFAULT 3,
                      is_confirmed BOOLEAN DEFAULT FALSE,
                      FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE SET NULL
);

INSERT INTO role (name) VALUES
                            ('Администратор'),
                            ('Модератор'),
                            ('Пользователь');
INSERT INTO user (username, password, email, token, role_id, is_confirmed)
VALUES ('admin', '123', 'admin@example.com', NULL, 1, 1);
