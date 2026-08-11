use  gla_db;

Create Table student (
student_id INT PRIMARY KEY auto_increment,
roll_number VARCHAR(20) UNIQUE NOT Null,
first_name VARCHAR (50) NOT NULL,
last_name VARCHAR(50),
gender VARCHAR(10),
email VARCHAR(50) UNIQUE,
address VARCHAR(100) ,
city VARCHAR(50),
state VARCHAR(50),
course VARCHAR (100),
department VARCHAR(100),
admission_year Year,
created_at Timestamp
);

desc student;

INSERT INTO student (roll_number, first_name, last_name, gender, email, address, city, state, course, department, admission_year, created_at)
VALUES ('2215000546', 'Deepak', 'Rajput', 'Male', 'deppak@gmail.com', 'Satwas', 'Kaman', 'Rajasthan', 'B.Tech',
 'Computer Science Engineering', 2026, CURRENT_TIMESTAMP);
 SELECT * FROM student;
 
create table faculty (
faculty_id int primary key auto_increment,
first_name varchar(50) not null,
last_name varchar(50) not null,
gender varchar(10) not null,
email varchar(50) unique,
address varchar(100),
city varchar(20),
state varchar(20),
department varchar(50),
designation varchar(100),
joining_year year,
created_at timestamp default current_timestamp
);

desc faculty;

insert into faculty (faculty_id, first_name, last_name, gender, email, address, city, state, department, designation, joining_year)
values ('101', 'bruce', 'banner', 'Male', 'bruce@gla.ac.in', 'lucknow UP', 'lucknow', 'UP', 'CS', 'Professor', 2024);

select * from faculty;
INSERT INTO faculty (first_name, last_name, gender, email, address, city, state, department, designation, joining_year) VALUES
('Tony', 'Stark', 'Male', 'tony@gla.ac.in', 'Malibu Point', 'Malibu', 'CA', 'ME', 'HOD', 2018),
('Natasha', 'Romanoff', 'Female', 'natasha@gla.ac.in', 'Red Room Lane', 'Moscow', 'MH', 'CS', 'Associate Professor', 2020),
('Steve', 'Rogers', 'Male', 'steve@gla.ac.in', 'Brooklyn Heights', 'Mathura', 'UP', 'EC', 'Assistant Professor', 2022),
('Wanda', 'Maximoff', 'Female', 'wanda@gla.ac.in', 'Westview Colony', 'Agra', 'UP', 'IT', 'Professor', 2019),
('Peter', 'Parker', 'Male', 'peter@gla.ac.in', 'Queens Street', 'Noida', 'UP', 'CS', 'Assistant Professor', 2023);

-- Additional Student records
INSERT INTO student (roll_number, first_name, last_name, gender, email, address, city, state, course, department, admission_year) VALUES
('GLA0102', 'Steve', 'Rogers', 'Male', 'steve.s@gmail.com', 'Sector 15', 'Noida', 'Uttarpradesh', 'btech', 'ME', 2022),
('GLA0103', 'Wanda', 'Maximoff', 'Female', 'wanda.m@gmail.com', 'Civil Lines', 'Mathura', 'Uttarpradesh', 'btech', 'CSE', 2023),
('GLA0104', 'Peter', 'Parker', 'Male', 'peter.p@gmail.com', 'MG Road', 'Agra', 'Uttarpradesh', 'btech', 'IT', 2021),
('GLA0105', 'Carol', 'Danvers', 'Female', 'carol.d@gmail.com', 'Air Force Colony', 'Lucknow', 'Uttarpradesh', 'btech', 'EC', 2022),
('GLA0106', 'Scott', 'Lang', 'Male', 'scott.l@gmail.com', 'Quantum Street', 'Kanpur', 'Uttarpradesh', 'btech', 'ME', 2023);

SELECT * FROM faculty;
SELECT * FROM student;
alter table faculty
ADD column salary decimal(10,2);

select * from faculty;


SET SQL_SAFE_UPDATES = 0;
update faculty
set salary = 7500000.00
select * from faculty;