DROP TABLE IF EXISTS student CASCADE;
CREATE TABLE student
(
    id   bigserial PRIMARY KEY,
    name VARCHAR(255),
    mark varchar(255)
);
INSERT INTO student (name, mark)
VALUES ('vova', 'California'),
       ('bob', 'Atlanta'),
       ('viktor', 'New York'),
       ('vika', 'New York');
