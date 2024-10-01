CREATE DATABASE IF NOT EXISTS `roadmap_project` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `roadmap_project`;

-- Schema DDL

CREATE TABLE IF NOT EXISTS users
(
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    enabled  BOOLEAN     NOT NULL
);
CREATE TABLE IF NOT EXISTS students
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(36) NOT NULL UNIQUE,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    otp VARCHAR(6),
    enabled BOOLEAN NOT NULL DEFAULT true
);
CREATE TABLE IF NOT EXISTS authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username),
    UNIQUE INDEX ix_auth_username (username, authority)
);

CREATE TABLE IF NOT EXISTS roadmap
(
    id       VARCHAR(36) NOT NULL PRIMARY KEY,
    username VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS roadmap_resources
(
    id                   VARCHAR(36) NOT NULL PRIMARY KEY,
    roadmap_id           VARCHAR(36) NOT NULL,
    revision_resource_id VARCHAR(36) NOT NULL
);
CREATE TABLE IF NOT EXISTS revision_resource
(
    id                 VARCHAR(36)  NOT NULL PRIMARY KEY,
    topic_id           VARCHAR(36)  NOT NULL,
    resource_name      VARCHAR(20)  NOT NULL,
    description        VARCHAR(100) NOT NULL,
    where_to_access    VARCHAR(100) NOT NULL,
    level_of_expertise varchar(12)  NOT NULL
);

CREATE TABLE IF NOT EXISTS topics
(
    id         VARCHAR(36) NOT NULL PRIMARY KEY,
    subject_id VARCHAR(36),
    topic_name VARCHAR(20) NOT NULL

);

CREATE TABLE IF NOT EXISTS subjects
(
    id           VARCHAR(36) NOT NULL PRIMARY KEY,
    subject_name VARCHAR(20) NOT NULL

);

CREATE TABLE IF NOT EXISTS user_topics
(
    username           VARCHAR(50) NOT NULL,
    topic_id           VARCHAR(36) NOT NULL,
    level_of_expertise VARCHAR(12) NOT NULL,
    PRIMARY KEY (username, topic_id)
);

INSERT INTO users (username, password, enabled)
VALUES ('renas', 'renas', true),
       ('admin', 'admin', true);



INSERT INTO students (username, email, password, enabled, otp)
VALUES
    ('John Doe', 'john@example.com', '$2a$10$8pd1G1G3CzavAVkf7Ys9VeI7.rAaE4De7bhDXyphU87DDu7qLFx9u', true, NULL),
    ('Jane Smith', 'jane@example.com', '$2a$10$8pd1G1G3CzavAVkf7Ys9VeI7.rAaE4De7bhDXyphU87DDu7qLFx9u', true, NULL);


INSERT INTO authorities (username, authority)
VALUES ('renas', 'ROLE_USER'),
       ('admin', 'ROLE_USER'),
       ('admin', 'ROLE_ADMIN');

INSERT INTO roadmap (id, username)
VALUES ('8894517b-539a-4b89-b0a0-849e84329181', 'renas');

INSERT INTO revision_resource (id, topic_id, resource_name, description, where_to_access, level_of_expertise)
VALUES ('4c059778-5c0e-40f0-ae0b-85bf0ce8b6cc', '22be771a-7803-445f-b88f-732fd6170f56', 'book',
        'read this book and become good at algebra :)', 'MathsBook101 page 10', 'NOVICE'),
       ('2d927d0a-d2a1-46d7-826d-e6783da22169', '22be771a-7803-445f-b88f-732fd6170f56', 'website',
        'complete the tasks on this website and you will be an expert algebration', 'https://www.coolmathgames.com/',
        'NOVICE'),
       ('389f80b5-2c02-4e20-bc16-3b3e7e6aee1f', '1915b4be-7f11-48bb-97ff-88f9297104f8', 'online course',
        'this is a good course', 'https://www.coolmathgames.com/', 'NOVICE'),
       ('1fbbbed5-5044-4285-8574-39978c01f916', '24fb37ab-18ed-42c7-9bc3-09b750aca27a', 'Shakespeare',
        'to be or not to be', 'https://www.coolmathgamesss.com/', 'NOVICE'),
       ('2b922630-2499-4f73-bf7b-6d0d8e99591a', '24fb37ab-18ed-42c7-9bc3-09b750aca27a', 'filler',
        'this is all from backend btw', 'https://www.coolmathgamesss.com/', 'NOVICE'),
       ('0ac85513-8025-40d8-9960-ee52ba76529d', '1915b4be-7f11-48bb-97ff-88f9297104f8', 'filler',
        'this is all from backend btw', 'https://www.coolmathgamesss.com/', 'NOVICE'),
       ('45926cd7-18bd-44ec-b2dd-674dff192a23', 'c431c80b-31c6-41c2-99e2-ab6690d4da85', 'filler',
        'this is all from backend btw', 'https://www.coolmathgamesss.com/', 'NOVICE'),
       ('6ba67204-923d-4087-9685-b3d2ee759ab2', 'c431c80b-31c6-41c2-99e2-ab6690d4da85', 'filler',
        'this is all from backend btw', 'https://www.coolmathgamesss.com/', 'NOVICE');


INSERT INTO roadmap_resources (id, roadmap_id, revision_resource_id)
VALUES ('0dbabf29-a60f-459c-8419-3628a56c2e68', '8894517b-539a-4b89-b0a0-849e84329181',
        '4c059778-5c0e-40f0-ae0b-85bf0ce8b6cc'),
       ('6033160c-c5f5-4459-96c5-5c21f4d34048', '8894517b-539a-4b89-b0a0-849e84329181',
        '2d927d0a-d2a1-46d7-826d-e6783da22169');

INSERT INTO subjects(id, subject_name)
VALUES ('30d00a6f-9577-418f-9f62-04d940379102', 'Maths'),
       ('4921cbae-589d-43f6-b069-cbca45a8b40c', 'English'),
       ('482f9580-e72b-41d8-b1c3-d8e2162fbe20', 'CompSci'),
       ('7b301086-ead1-45ab-86a3-11eafc4820c7', 'Biology'),
       ('ae5a154e-2957-489c-b9b3-7f1046cae743', 'sexEd'),
       ('8830e1d8-47fb-4260-bbcc-1f05a437f5ed', 'howToTrainADragon');

INSERT INTO topics(id, subject_id, topic_name)
VALUES ('22be771a-7803-445f-b88f-732fd6170f56', '30d00a6f-9577-418f-9f62-04d940379102', 'algebra'),
       ('1915b4be-7f11-48bb-97ff-88f9297104f8', '30d00a6f-9577-418f-9f62-04d940379102', 'calculus'),
       ('24fb37ab-18ed-42c7-9bc3-09b750aca27a', '4921cbae-589d-43f6-b069-cbca45a8b40c', 'EnglishLit'),
       ('c431c80b-31c6-41c2-99e2-ab6690d4da85', '4921cbae-589d-43f6-b069-cbca45a8b40c', 'EnglishLanguage');