CREATE DATABASE health_clinic_db;
USE health_clinic_db;
CREATE TABLE specializations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255)
);
CREATE TABLE appointments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    doctor_id INT,
    appointment_date DATETIME
);
INSERT INTO specializations (name, description)
VALUES
('Cardiology', 'Heart related diseases'),
('Dermatology', 'Skin related treatment'),
('Orthopedics', 'Bone and joint treatment');
SELECT * FROM specializations;

INSERT INTO appointments (patient_id, doctor_id, appointment_date)
VALUES
(1, 101, '2026-08-02 10:00:00'),
(2, 102, '2026-08-03 11:30:00'),
(3, 103, '2026-08-04 09:15:00');
SELECT * FROM appointments;

ALTER TABLE specializations
ADD COLUMN experience_years INT;
DESC specializations;
ALTER TABLE specializations
DROP COLUMN experience_years;
DESC specializations;
SELECT * FROM specializations;
UPDATE specializations
SET description = 'Heart and Blood Vessel Treatment'
WHERE id = 1;
SELECT * FROM specializations;
SELECT * FROM appointments;
DELETE FROM appointments
WHERE id = 3;
SELECT * FROM appointments;
CREATE USER 'clinic_app_user'@'localhost'
IDENTIFIED BY 'Clinic@123';
GRANT SELECT, INSERT, UPDATE, DELETE
ON health_clinic_db.*
TO 'clinic_app_user'@'localhost';
FLUSH PRIVILEGES;
SHOW GRANTS FOR 'clinic_app_user'@'localhost';
