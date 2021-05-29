


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
