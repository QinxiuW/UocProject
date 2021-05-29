DROP SCHEMA IF EXISTS course_score CASCADE;
CREATE SCHEMA course_score;
SET search_path TO course_score;

CREATE table tb_student
(
    id           BIGSERIAL PRIMARY KEY NOT NULL,
    name         VARCHAR(20)           NOT NULL,
    surname    VARCHAR(20)           NOT NULL
);

CREATE table tb_teacher
(
    id           BIGSERIAL PRIMARY KEY NOT NULL,
    name         VARCHAR(20)           NOT NULL,
    surname    VARCHAR(20)           NOT NULL
);

CREATE table tb_course
(
    id           BIGSERIAL PRIMARY KEY NOT NULL,
    name         VARCHAR(20)           NOT NULL,
    teacher_id   BIGINT                ,
    FOREIGN KEY (teacher_id) REFERENCES tb_teacher (id)
);

CREATE table tb_qualification
(
    id           BIGSERIAL PRIMARY KEY NOT NULL,
    score        INT          NOT NULL default -1,
    course_id    BIGINT                NOT NULL,
    student_id   BIGINT                NOT NULL,
    FOREIGN KEY (course_id) REFERENCES tb_course (id),
    FOREIGN KEY (student_id) REFERENCES tb_student (id)
);

