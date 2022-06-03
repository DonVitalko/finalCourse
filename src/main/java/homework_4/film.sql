CREATE TABLE films
(
    id           bigserial primary key,
    title        varchar(255) not null,
    duration     int not null
);

CREATE TABLE sessions
(
    id           bigserial primary key,
    film_id      bigint not null references films (id),
    ticket_price numeric(8, 2) not null,
    start_time   timestamp(0) not null
);

CREATE TABLE tickets
(
    id           bigserial primary key,
    session_id   bigint not null references sessions (id)
);

INSERT INTO films (title, duration)
VALUES ('Avengers', 120),
       ('Venom', 90),
       ('Spider-Man', 60),
       ('Harry Potter', 120),
       ('Dune', 90);

INSERT INTO sessions (film_id, ticket_price, start_time)
VALUES (1, 300.00, '2022-05-16 09:00:00'),
       (2, 300.00, '2022-05-16 11:20:00'),
       (3, 300.00, '2022-05-16 12:00:00'),
       (4, 300.00, '2022-05-16 14:00:00'),
       (5, 300.00, '2022-05-16 16:10:00'),
       (1, 600.00, '2022-05-16 19:00:00'),
       (2, 600.00, '2022-05-16 21:10:00'),
       (3, 600.00, '2022-05-16 22:50:00');

INSERT INTO tickets (session_id)
VALUES (1),(1),(1),
       (2),(2),(2),(2),(2),(2),
       (3),(3),(3),(3),
       (4),(4),(4),(4),(4),(4),(4),(4),
       (5),(5),(5),(5),(5),(5),(5),
       (6),(6),(6),(6),(6),(6),(6),
       (7),(7),(7),(7),(7),(7),(7),(7),
       (8),(8),(8),(8),(8),(8),(8),(8);

WITH select_break_duration AS (SELECT f.title AS first_film_title,
                                      s.start_time AS first_film_start_time,
                                      f.duration AS first_film_duration,
                                      lead(f.title) OVER(ORDER BY s.id) AS second_film_title,
                                       lead(s.start_time) OVER(ORDER BY s.id) AS second_film_start_time,
                                       lead(f.duration) OVER(ORDER BY s.id) AS second_film_duration,
                                       age(lead(s.start_time) OVER(ORDER BY s.id),
                                           (SELECT s.start_time + make_interval(mins => f.duration))) AS break_duration
                               FROM films f
                                        LEFT JOIN sessions s
                                                  ON f.id = s.film_id
                               ORDER BY break_duration DESC)
SELECT * FROM select_break_duration WHERE break_duration < interval '00:00:00';



WITH select_break_duration AS (SELECT f.title AS first_film_title,
                                      s.start_time AS first_film_start_time,
                                      f.duration AS first_film_duration,
                                      lead(f.title) OVER(ORDER BY s.id) AS second_film_title,
                                       lead(s.start_time) OVER(ORDER BY s.id) AS second_film_start_time,
                                       lead(f.duration) OVER(ORDER BY s.id) AS second_film_duration
                               FROM films f
                                        LEFT JOIN sessions s
                                                  ON f.id = s.film_id)
SELECT * FROM select_break_duration
WHERE (SELECT (first_film_start_time, first_film_start_time + make_interval(mins => first_film_duration))
                  overlaps
              (second_film_start_time, second_film_start_time + make_interval(mins => second_film_duration)));



WITH select_break_duration AS (SELECT f.title AS film_title,
                                      s.start_time AS first_film_start_time,
                                      f.duration AS film_duration,
                                      lead(s.start_time) OVER(ORDER BY s.id) AS second_film_start_time,
                                       age(lead(s.start_time) OVER(ORDER BY s.id),
                                           (SELECT s.start_time + make_interval(mins => f.duration))) AS break_duration
                               FROM films f
                                        LEFT JOIN sessions s
                                                  ON f.id = s.film_id
                               ORDER BY break_duration DESC)
SELECT * FROM select_break_duration WHERE break_duration >= interval '00:30:00';

WITH current_film as (SELECT f.title as film_title,
                             count(t.id) as total_visitors,
                             (SELECT avg(count_visitors) as avg_visitors_per_session FROM
                                 (SELECT count(t.id) as count_visitors
                                  FROM tickets t
                                           LEFT JOIN sessions s
                                                     ON s.id = t.session_id
                                  WHERE s.film_id = f.id
                                  GROUP BY s.id) Z),
                             sum(s.ticket_price) as total_revenue
                      FROM films f
                               LEFT JOIN sessions s
                                         ON f.id = s.film_id
                               LEFT JOIN tickets t
                                         ON s.id = t.session_id
                      GROUP BY f.id
                      ORDER BY total_revenue DESC)
    (SELECT * FROM current_film)
UNION ALL
(SELECT 'Total: ',
        sum(current_film.total_visitors),
        round(sum(current_film.total_visitors)/(SELECT count(s.id) FROM sessions s)),
        sum(current_film.total_revenue)
 FROM current_film);


SELECT CASE
           WHEN start_time BETWEEN '2022-05-16 09:00:00' AND '2022-05-16 15:00:00' THEN '9 - 15'
           WHEN start_time BETWEEN '2022-05-16 15:00:00' AND '2022-05-16 18:00:00' THEN '15 - 18'
           WHEN start_time BETWEEN '2022-05-16 18:00:00' AND '2022-05-16 21:00:00' THEN '18 - 21'
           ELSE '21 - 00'
           END AS time_range,
       count(t.id) as visitors,
       sum(s.ticket_price) as total_revenue
FROM sessions s
         LEFT JOIN tickets t
                   ON t.session_id = s.id
GROUP BY time_range;