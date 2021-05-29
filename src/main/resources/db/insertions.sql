


-- teacher
insert into tb_teacher(id,name,surname)
values (1, 'Juan','teacher'),
       (2, 'Miguel','teacher'),
       (3, 'Miguel','teacher'),
       (4, 'Maria','teacher');

-- student
insert into tb_student(id,name,surname)
values (1, 'Xiaomi','student'),
       (2, 'Huawei','student'),
       (3, 'Oppo','student'),
       (4, 'OnePlus','student');

-- course
insert into tb_course(id,name,teacher_id)
values (1, 'Math',1),
       (2, 'Music',2),
       (3, 'Chinese Culture',3),
       (4, 'Spanish Culture',4),
       (5, 'Science',1);

-- qualification
insert into tb_qualification(id,score,course_id,student_id)
values (1, 9,1,1),
       (2, 8,1,2),
       (3, 7,1,3),
       (4, 6,1,4),

       (5, 9,2,1),
       (6, 8,2,2),
       (7, 7,2,3),
       (8, 6,2,4),

       (9, 9,3,1),
       (10, 8,3,2),
       (11, 7,3,3),
       (12, 6,3,4),

       (13, 9,4,1),
       (14, 8,4,2),
       (15, 7,4,3),
       (16, 6,4,4),

       (17, 9,5,1),
       (18, 8,5,2),
       (19, 7,5,3),
       (20, 6,5,4);
