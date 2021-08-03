select * from course_student;
select * from student;

update student set status = "A" where status="";
select * from course;
select * from users;
select * from authorities;
select * from payment_student;
DELETE FROM `instituto_ingles`.`payment_student`;
    
    DELETE FROM `instituto_ingles`.`student` ;
    
    DELETE FROM `instituto_ingles`.`course`;
    
        DELETE FROM `instituto_ingles`.`payment_student`;
    
    show variables like 'event_scheduler';
    show processlist;
    
  SHOW EVENTS FROM classicmodels;
  
  
  
  CREATE EVENT reset_students_bad_pay
ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 1 MONTH
ON COMPLETION PRESERVE
DO
	UPDATE `instituto_ingles`.`student`
	SET
	`status` = 'M'
	WHERE `student_id` = 'A';
    
  CREATE EVENT reset_students_good_pay
ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 1 MONTH
ON COMPLETION PRESERVE
DO
	UPDATE `instituto_ingles`.`student`
	SET
	`status` = 'A'
	WHERE `student_id` = 'P';



   
   
   

