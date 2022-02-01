# ===================================================================================== #
#                               Создаём схему и таблицы                                 #
# ===================================================================================== #

# Создаём схему 
DROP SCHEMA IF EXISTS cinema;
CREATE SCHEMA cinema ;

# Работаем со схемой 
USE cinema;

# Создаём таблицу дней недели.
DROP TABLE IF EXISTS days;
CREATE TABLE days (
  id INT NOT NULL,
  title VARCHAR(20) NOT NULL,
  add_price INT NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE INDEX title_UNIQUE (title ASC) VISIBLE
  );
  
# Создаём таблицу длительностей фильмов.
DROP TABLE IF EXISTS films_length;
CREATE TABLE films_length (
  id INT NOT NULL AUTO_INCREMENT,
  length INT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX length_UNIQUE (length ASC) VISIBLE
  );

# Создаём таблицу фильмов.
DROP TABLE IF EXISTS films;
CREATE TABLE films (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(150) NOT NULL,
  length_id INT NOT NULL,
  base_price INT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX title_UNIQUE (title ASC) VISIBLE,
  INDEX `films.length_id_fk_idx` (length_id ASC) VISIBLE,
  CONSTRAINT `films.length_id_fk`
  FOREIGN KEY (length_id)
  REFERENCES cinema.films_length (id)
  ON DELETE NO ACTION
  ON UPDATE CASCADE
  );
  
# Создаём таблицу фильмов.
DROP TABLE IF EXISTS sessions;
CREATE TABLE sessions (
  id INT NOT NULL AUTO_INCREMENT,
  start_time_from TIME NOT NULL,
  start_time_to TIME NOT NULL,
  add_price INT NOT NULL,
  PRIMARY KEY (id)
  );

# Создаём таблицу со расписанием.
DROP TABLE IF EXISTS schedules;
CREATE TABLE schedules (
  id INT NOT NULL AUTO_INCREMENT,
  session_date_time DATETIME NOT NULL,
  film_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX id_idx (id ASC) VISIBLE,
  INDEX `schedules.film_id_idx` (film_id ASC) VISIBLE,
  CONSTRAINT `schedules.film_id`
  FOREIGN KEY (film_id)
  REFERENCES cinema.films (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
  );
 
 # Создаём таблицу с проданными билетами.
 DROP TABLE IF EXISTS tickets;
 CREATE TABLE tickets (
  id INT NOT NULL AUTO_INCREMENT,
  film_id INT NOT NULL,
  session_date_time DATETIME NOT NULL,
  cost INT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  INDEX `tickets.film_id_fk_idx` (`film_id` ASC) VISIBLE,
  CONSTRAINT `tickets.film_id_fk`
  FOREIGN KEY (`film_id`)
  REFERENCES `cinema`.`films` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION
    );

  
# ===================================================================================== #
#                               Заполняем таблицы данными                               #
# ===================================================================================== #

INSERT INTO days (id, title, add_price) VALUES (0, 'Понедельник', 0), (1, 'Вторник', 0), (2, 'Среда', 0), (3, 'Четверг', 0), (4, 'Пятница', 50), (5, 'Суббота', 100), (6, 'Воскресенье', 100);

INSERT INTO films_length (length) VALUES (60), (90), (120);

INSERT INTO sessions (start_time_from, start_time_to, add_price) 
VALUES ('08:00:00', '15:30:00', 0), 
		('16:00:00', '17:30:00', 20), 
        ('18:00:00', '19:30:00', 40),
        ('20:00:00', '22:30:00', 60),
        ('23:00:00', '23:30:00', 20);

INSERT INTO films (title, length_id, base_price) 
VALUES ('Терминатор', 3, 150), ('Терминатор 2: Судный день', 3, 160), ('Терминатор 3: Восстание машин', 3, 190),
		('Терминатор: Да придёт спаситель', 3, 210), ('Терминатор: Генезис', 3, 260), ('Терминатор: Тёмные судьбы', 3, 300);
            
INSERT INTO schedules (session_date_time, film_id) 
VALUES ('2022-01-29 08:00:00', 1),
		('2022-01-29 11:00:00', 2),
		('2022-01-29 14:00:00', 1),
		('2022-01-29 16:30:00', 2),
		('2022-01-29 18:00:00', 3),
		('2022-01-29 20:00:00', 4),
		('2022-01-29 22:00:00', 5),
		('2022-01-29 23:30:00', 6);

INSERT INTO tickets (film_id, session_date_time, cost) 
VALUES (1, '2022-01-29 08:00:00', 350), (1, '2022-01-29 08:00:00', 350), 
		(1, '2022-01-29 14:00:00', 350), (2, '2022-01-29 16:30:00', 370),
        (3, '2022-01-29 18:00:00', 390), (4, '2022-01-29 20:00:00', 410);

# SELECT f.base_price + d.add_price + s.add_price FROM films AS f
# INNER JOIN days d ON WEEKDAY(session_date_time) = d.id
# INNER JOIN sessions s ON TIME(session_date_time) BETWEEN s.start_time_from AND s.start_time_to
# WHERE f.id = film_id;

# ===================================================================================== #
#                               Выводим отчёты			                                #
# ===================================================================================== #

# ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени. 
# Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;

WITH intervals AS (
	SELECT 
		sh1.id AS id,
		f1.title AS film_title,
		sh1.session_date_time AS start_time,
		(SELECT length FROM films_length WHERE films_length.id = f1.length_id) AS length
	FROM schedules sh1
	INNER JOIN films f1 ON sh1.film_id = f1.id )
SELECT i1.film_title, TIME(i1.start_time) AS start_time, i1.length, 
		i2.film_title, TIME(i2.start_time)  AS start_time, i2.length
FROM intervals i1
INNER JOIN intervals i2 ON i2.id = i1.id + 1
WHERE DATE(i1.start_time) = '2022-01-29' AND TIMEDIFF(i2.start_time, TIMESTAMPADD(MINUTE, i1.length, i1.start_time)) < 0
ORDER BY i1.start_time;
    
    
# перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва. 
# Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;

WITH intervals AS (
	SELECT 
		sh1.id AS id,
		f1.title AS film_title,
		sh1.session_date_time AS start_time,
		(SELECT length FROM films_length WHERE films_length.id = f1.length_id) AS length
	FROM schedules sh1
	INNER JOIN films f1 ON sh1.film_id = f1.id )
SELECT i1.film_title, TIME(i1.start_time) AS film_1_start_time, i1.length, 
		TIME(i2.start_time)  AS film_2_start_time,
        TIMESTAMPDIFF(MINUTE, TIMESTAMPADD(MINUTE, i1.length, i1.start_time), i2.start_time) AS interrupt
FROM intervals i1
INNER JOIN intervals i2 ON i2.id = i1.id + 1
WHERE DATE(i1.start_time) = '2022-01-29'
HAVING interrupt >= 30
ORDER BY interrupt DESC;

# список фильмов, для каждого — с указанием общего числа посетителей за все время, 
# среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли). 
# Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;

SELECT 
	f.id, 
	f.title,
    COUNT(t.id) total_peoples,
    SUM(t.cost) total_money 
FROM films f
LEFT JOIN tickets t ON f.id = t.film_id
LEFT JOIN (SELECT id, film_id, COUNT(t.session_date_time) avg_people_per_session FROM tickets t GROUP BY t.session_date_time) avg_tabl ON avg_tabl.film_id = f.id
GROUP BY t.film_id
ORDER BY total_money DESC;


# число посетителей и кассовые сборы, сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).

SELECT 'с 9 до 15', COUNT(t.id), SUM(cost) FROM tickets t WHERE HOUR(session_date_time) BETWEEN 9 AND 15
UNION ALL
SELECT 'с 15 до 18', COUNT(t.id), SUM(cost) FROM tickets t WHERE HOUR(session_date_time) BETWEEN 15 AND 18
UNION ALL
SELECT 'с 18 до 21', COUNT(t.id), SUM(cost) FROM tickets t WHERE HOUR(session_date_time) BETWEEN 18 AND 21
UNION ALL
SELECT 'с 21 до 00:00', COUNT(t.id), SUM(cost) FROM tickets t WHERE HOUR(session_date_time) BETWEEN 21 AND 0;