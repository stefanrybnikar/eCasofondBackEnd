DROP TABLE IF EXISTS entries;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS profession_types;
DROP TABLE IF EXISTS entry_types;
DROP TABLE IF EXISTS role_types;

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
    created     TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated     TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (type_id) REFERENCES entry_types (id)
);

INSERT INTO role_types (name, level, write)
VALUES ('ADVISOR', 0, TRUE),
       ('ADMIN', 1, TRUE),
       ('USER', 2, FALSE);