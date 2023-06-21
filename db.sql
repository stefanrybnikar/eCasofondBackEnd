DROP TABLE IF EXISTS entries;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS role_types;
DROP TABLE IF EXISTS profession_types_entry_types;
DROP TABLE IF EXISTS entry_types;
DROP TABLE IF EXISTS profession_types;

CREATE TABLE role_types
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255) NOT NULL UNIQUE,
    level INT          NOT NULL UNIQUE,
    write BOOLEAN      NOT NULL
);

CREATE TABLE entry_types
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE profession_types
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE companies
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL UNIQUE
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

INSERT INTO role_types (name, level, write)
VALUES ('ADVISOR', 0, TRUE),
       ('ADMIN', 1, TRUE),
       ('USER', 2, FALSE);

INSERT INTO entry_types (name)
VALUES ('WORK'),
       ('VACATION'),
       ('SICK'),
       ('OTHER');

INSERT INTO profession_types (name)
VALUES ('IT'),
       ('HR'),
       ('SALES'),
       ('MANAGEMENT'),
       ('OTHER');

INSERT INTO companies (name)
VALUES ('COMPANY A'),
       ('COMPANY B'),
       ('COMPANY C'),
       ('COMPANY D'),
       ('COMPANY E');

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

INSERT INTO entries (user_id, type_id, description, hour_count, day)
VALUES (11, 1, 'Doing stuff', 8, '2023-06-23'),
       (11, 2, 'Vacation', 8, '2023-06-26'),
       (11, 2, 'Vacation', 8, '2023-06-27'),
       (11, 2, 'Vacation', 8, '2023-06-28'),
       (11, 3, 'Sick', 8, '2023-06-29'),
       (11, 3, 'Sick', 8, '2023-06-30'),
       (11, 3, 'Sick', 8, '2023-07-03'),
       (11, 4, 'Other', 8, '2023-07-04');