USE jtdatabase;
DROP TABLE flyway_shema_history;

CREATE TABLE chapters (
                          id INT AUTO_INCREMENT,
                          name TEXT,
                          PRIMARY KEY (id)
);

CREATE TABLE questions (
                           id INT AUTO_INCREMENT,
                           question TEXT,
                           chapter INT,
                           chapter_id INT NULL,
                           PRIMARY KEY (id),
                           FOREIGN KEY (chapter) REFERENCES chapters (id)
);


CREATE TABLE answers (
                         id INT AUTO_INCREMENT,
                         answer TEXT,
                         question INT,
                         correct_answer BIT,
                         question_id INT NULL,
                         PRIMARY KEY (id),
                         FOREIGN KEY (question) REFERENCES questions (id)
);





