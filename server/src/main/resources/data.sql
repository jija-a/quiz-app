INSERT INTO users(created_at, first_name, is_active, last_name, updated_at, password, patronymic, username)
VALUES (NOW(), 'Александр', true, 'Игоревич', NOW(), '$2a$12$YAElJJd2E0fKt9xlRDsaFuWOmILXF5zuCqwd6YJ9Wo2R0QcNs.qC6',
        'Штарев', 'alex');

INSERT INTO categories(name)
VALUES ('10 Класс'),
       ('11 Класс');

INSERT INTO quizzes(duration, end_date, start_date, title, category_id)
VALUES (100, NOW(), NOW(), 'Алгоритмы обработки массивов', 1);

INSERT INTO files(name, data, type)
VALUES ('т1з1в1', '/Users/alexnered/Desktop/quiz_img/т1в1.png', 'image/jpeg'),
       ('т1з1в2', '/Users/alexnered/Desktop/quiz_img/т1в2.png', 'image/jpeg'),
       ('т1з1в2о1', '/Users/alexnered/Desktop/quiz_img/т1в2о1.png', 'image/jpeg'),
       ('т1з1в2о2', '/Users/alexnered/Desktop/quiz_img/т1в2о2.png', 'image/jpeg'),
       ('т1з1в2о3', '/Users/alexnered/Desktop/quiz_img/т1в2о3.png', 'image/jpeg'),
       ('т1з1в2о4', '/Users/alexnered/Desktop/quiz_img/т1в2о4.png', 'image/jpeg'),
       ('т1з1в3', '/Users/alexnered/Desktop/quiz_img/т1в2о4.png', 'image/jpeg');

INSERT INTO questions(title, type, image_id, quiz_id)
VALUES ('Олег, Игорь, Оля и Катя написали программы для вычисления своего суммарного балла по результатам сдачи репетиционного тестирования по 4 предметам. Кто из ребят, в привиденных фрагментах кода, неправильно записал команды программы?',
        'RADIO', 1, 1),
       ('Для определения роста самого высокого учащегося класса в программу вводятся значения роста для каждого из них. Какой из привиденных в табл. 1 вариантов фрагмента кода программы позволяет организовывать ввод данных в виде, представленном ниже?',
        'RADIO', 2, 1),
       ('Установите соответствие между командами из программы и действиями, которые они выполняют, ответи запишите в виде: "1-А, 2-Б, 3-В, 4-Г"', 'INPUT', NULL, 1);

INSERT INTO options(is_right, title, question_id, image_id)
VALUES (true, 'Олег', 1, NULL),
       (false, 'Катя', 1, NULL),
       (false, 'Коля', 1, NULL),
       (false, 'Игорь', 1, NULL),
       (false, '1', 2, 3),
       (false, '2', 2, 4),
       (true, '3', 2, 5),
       (false, '4', 2, 6),
       (true, '1-Б, 2-А, 3-В, 4-Г', 3, NULL);


