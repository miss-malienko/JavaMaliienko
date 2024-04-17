CREATE DATABASE IF NOT EXISTS student_data;
USE student_data;
DROP TABLE IF EXISTS student;

CREATE TABLE student (
id int AUTO_INCREMENT NOT NULL,
first_name varchar(60) NOT NULL,
last_name varchar(60) NOT NULL,
middle_name varchar(60) NOT NULL,
birth_date date NOT NULL,
gradebook_number varchar(12) UNIQUE NOT NULL,
PRIMARY KEY(id)
);

INSERT INTO student VALUES (null, 'Анастасія', 'Малієнко', 'Аndriivna', '2003-01-05', '167847');
INSERT INTO student VALUES (null, 'Яна', 'Кодола', 'Олегівна', '2003-05-01', '157829');
INSERT INTO student VALUES (null, 'Катерина', 'Беспалова', 'Юріївна', '2003-05-11', '135579');
INSERT INTO student VALUES (null, 'Олександра', 'Цибульська', 'Вадимівна', '2003-02-16', '267299');
INSERT INTO student VALUES (null, 'Ксенія', 'Прилипко', 'Геннадіївна', '2002-03-12', '538789');

INSERT INTO student VALUES (null, 'Максим', 'Гончаров', 'Сергійович', '2000-04-20', '297934');
INSERT INTO student VALUES (null, 'Богдан', 'Манін', 'Максимович', '2002-05-30', '245378');
INSERT INTO student VALUES (null, 'Дарина', 'Шрамко', 'Ігорівна', '1998-07-09', '337758');
INSERT INTO student VALUES (null, 'Андрій', 'Худолій', 'Сергійович', '2003-08-21', '467578');
INSERT INTO student VALUES (null, 'Томаш', 'Кутнич ', 'Томашович', '1998-09-05', '238968');

INSERT INTO student VALUES (null, 'Сніжана', 'Кізякова', 'Олегівна', '2002-12-26', '538903');
INSERT INTO student VALUES (null, 'Микола', 'Ємець', 'Олександрович', '1997-10-12', '309269');
INSERT INTO student VALUES (null, 'Надія', 'Кукла', 'Денисівнв', '2001-03-04', '539748');
INSERT INTO student VALUES (null, 'Надія', 'Рябоконь', 'Іванівна', '2003-11-03', '394537');
INSERT INTO student VALUES (null, 'Єлизавета', 'Таран', 'Сергіївна', '2003-12-01', '125468');

INSERT INTO student VALUES (null, 'Кирило', 'Проценко', 'Віталійович', '1997-03-15', '103873');
INSERT INTO student VALUES (null, 'Катерина', 'Шинкаренко', 'Андріївна', '2000-10-06', '154793');
INSERT INTO student VALUES (null, 'Софія', 'Сирота', 'Дмитрівна', '1999-08-06', '809378');
INSERT INTO student VALUES (null, 'Олександр', 'Могилевський', 'Євгенович', '2001-04-21', '268485');
INSERT INTO student VALUES (null, 'Марія', 'Самарець', 'Олегівна', '1995-02-03', '537967');
