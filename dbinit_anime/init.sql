-- Создание таблицы `anime`
CREATE TABLE IF NOT EXISTS anime (
                                     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                     title VARCHAR(255) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    description TEXT,
    year INT DEFAULT NULL,
    genre VARCHAR(255) DEFAULT NULL,
    youtube_url VARCHAR(255) DEFAULT NULL
    );

-- Вставка данных в таблицу `anime`
INSERT INTO anime (title, image_url, description, year, genre, youtube_url) VALUES
                                                                                ('Атака титанов', 'https://avatars.mds.yandex.net/get-kinopoisk-image/9784475/1658bd9d-06d0-429c-b8bd-421a82f111c8/600x900', 'Уже многие годы человечество ведёт борьбу с титанами — огромными существами, которые не обладают особым интеллектом, зато едят людей и получают от этого удовольствие...', 2013, 'аниме, мультфильм, фантастика, драма, боевик, фэнтези', 'https://www.youtube.com/watch?v=EPc8ccHLIZs'),
                                                                                ('Наруто', 'https://avatars.mds.yandex.net/get-kinopoisk-image/1704946/e63beb56-0433-4bbf-ae70-5d85a5ed8945/600x900', 'Однажды демон напал на деревню, в которой родился мальчик Наруто...', 2002, 'аниме, мультфильм, фэнтези, боевик, комедия', 'https://www.youtube.com/watch?v=-G9BqkgZXRA');
