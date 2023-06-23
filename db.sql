DROP TABLE IF EXISTS entries;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS role_types;
DROP TABLE IF EXISTS profession_types_entry_types;
DROP TABLE IF EXISTS entry_types;
DROP TABLE IF EXISTS profession_types;
DROP TABLE IF EXISTS companies;

CREATE TABLE role_types
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255) NOT NULL UNIQUE,
    level INT          NOT NULL UNIQUE,
    write BOOLEAN      NOT NULL
);

CREATE TABLE companies
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL UNIQUE
);

CREATE TABLE entry_types
(
    id         SERIAL PRIMARY KEY,
    company_id BIGINT       NOT NULL,
    name       VARCHAR(255) NOT NULL,
    FOREIGN KEY (company_id) REFERENCES companies (id)
);

CREATE TABLE profession_types
(
    id         SERIAL PRIMARY KEY,
    company_id BIGINT       NOT NULL,
    name       VARCHAR(255) NOT NULL,
    FOREIGN KEY (company_id) REFERENCES companies (id)
);

CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    username      VARCHAR(64)  NOT NULL UNIQUE,
    password      VARCHAR(255) NOT NULL,
    email         VARCHAR(128) NOT NULL UNIQUE,
    display_name  VARCHAR(128) NOT NULL,
    company_id    BIGINT,
    role_id       BIGINT       NOT NULL,
    profession_id BIGINT,
    FOREIGN KEY (company_id) REFERENCES companies (id),
    FOREIGN KEY (role_id) REFERENCES role_types (id),
    FOREIGN KEY (profession_id) REFERENCES profession_types (id)
);

CREATE TABLE entries
(
    id          SERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL,
    type_id     BIGINT NOT NULL,
    description VARCHAR(255),
    hour_count  INT    NOT NULL,
    created     TIMESTAMPTZ     DEFAULT CURRENT_TIMESTAMP,
    updated     TIMESTAMPTZ     DEFAULT CURRENT_TIMESTAMP,
    day         DATE   NOT NULL DEFAULT CURRENT_DATE,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (type_id) REFERENCES entry_types (id)
);

CREATE TABLE profession_types_entry_types
(
    id                 SERIAL PRIMARY KEY,
    profession_type_id BIGINT NOT NULL,
    entry_type_id      BIGINT NOT NULL,
    FOREIGN KEY (profession_type_id) REFERENCES profession_types (id) ON DELETE CASCADE,
    FOREIGN KEY (entry_type_id) REFERENCES entry_types (id) ON DELETE CASCADE
);

INSERT INTO companies (name)
VALUES ('Apple'),
       ('Microsoft'),
       ('IBM'),
       ('Amazon'),
       ('Google');

INSERT INTO profession_types (company_id, name)
VALUES (1, 'IT'),
       (1, 'HR'),
       (1, 'SALES'),
       (1, 'MANAGEMENT'),
       (1, 'OTHER');

INSERT INTO profession_types (company_id, name)
VALUES (2, 'IT'),
       (2, 'HR'),
       (2, 'SALES'),
       (2, 'MANAGEMENT'),
       (2, 'OTHER');



INSERT INTO entry_types (company_id, name)
VALUES (1, 'WORK'),
       (1, 'VACATION'),
       (1, 'SICK'),
       (1, 'OTHER');

INSERT INTO entry_types (company_id, name)
VALUES (2, 'WORK'),
       (2, 'VACATION'),
       (2, 'SICK'),
       (2, 'OTHER');


INSERT INTO profession_types_entry_types (profession_type_id, entry_type_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (3, 1),
       (3, 2),
       (3, 3),
       (3, 4),
       (4, 1),
       (4, 2),
       (4, 3),
       (4, 4);

INSERT INTO role_types (name, level, write)
VALUES ('ADVISOR', 0, TRUE),
       ('ADMIN', 1, TRUE),
       ('USER', 2, FALSE);

DO
$$
    DECLARE
        userid BIGINT;
    BEGIN
        userid := (SELECT id FROM users WHERE username = 'user');

        INSERT INTO entries (user_id, type_id, description, hour_count, day)
        VALUES (userid, 1, 'Doing stuff', 8, '2023-06-14'),
               (userid, 1, 'Doing stuff', 6, '2023-06-15'),
               (userid, 1, 'Doing stuff', 7, '2023-06-16'),
               (userid, 1, 'Doing stuff', 5, '2023-06-19'),
               (userid, 1, 'Doing stuff', 8, '2023-06-20'),
               (userid, 1, 'Doing stuff', 8, '2023-06-21'),
               (userid, 1, 'Doing stuff', 5, '2023-06-22'),
               (userid, 1, 'Doing stuff', 8, '2023-06-23'),
               (userid, 2, 'Vacation', 8, '2023-06-26'),
               (userid, 2, 'Vacation', 8, '2023-06-27'),
               (userid, 2, 'Vacation', 8, '2023-06-28'),
               (userid, 3, 'Sick', 8, '2023-06-29'),
               (userid, 3, 'Sick', 8, '2023-06-30'),
               (userid, 3, 'Sick', 8, '2023-07-03'),
               (userid, 4, 'Other', 8, '2023-07-04'),
               (userid, 1, 'Doing stuff', 7, '2023-07-05'),
               (userid, 1, 'Doing stuff', 8, '2023-07-06'),
               (userid, 1, 'Doing stuff', 5, '2023-07-07'),
               (userid, 1, 'Doing stuff', 6, '2023-07-10'),
               (userid, 2, 'Vacation', 8, '2023-07-11'),
               (userid, 2, 'Vacation', 8, '2023-07-12'),
               (userid, 2, 'Vacation', 8, '2023-07-13'),
               (userid, 2, 'Vacation', 8, '2023-07-14'),
               (userid, 4, 'Other', 8, '2023-07-17'),
               (userid, 4, 'Other', 8, '2023-07-18'),
               (userid, 4, 'Other', 8, '2023-07-19');
    END
$$;