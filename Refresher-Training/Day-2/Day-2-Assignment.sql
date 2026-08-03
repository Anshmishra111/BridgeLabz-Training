-- Assignment 1
USE health_clinic_db;
-- Create Rooms Table
CREATE TABLE rooms (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_number VARCHAR(20) NOT NULL UNIQUE,
    floor INT,
    room_type VARCHAR(50)
);

-- Junction Table (Many Doctors ↔ Many Rooms)
CREATE TABLE doctor_room (
    doctor_id INT,
    room_id INT,
    PRIMARY KEY (doctor_id, room_id),
    FOREIGN KEY (doctor_id)
        REFERENCES doctors(doctor_id)
        ON DELETE CASCADE,
    FOREIGN KEY (room_id)
        REFERENCES rooms(room_id)
        ON DELETE CASCADE
);

-- Sample Data

INSERT INTO rooms(room_number, floor, room_type)
VALUES
('R101',1,'General Consultation'),
('R102',1,'Cardiology'),
('R201',2,'Pediatrics');

INSERT INTO doctor_room(doctor_id, room_id)
VALUES
(1,1),
(1,2);

-- Assignment 2
EXPLAIN
SELECT *
FROM appointments
WHERE status='Scheduled';

CREATE INDEX idx_patient_id
ON appointments(patient_id);
EXPLAIN
SELECT *
FROM appointments
WHERE patient_id=1;

EXPLAIN
SELECT *
FROM appointments
WHERE doctor_id=1
AND appointment_date='2026-08-05 10:00:00';

-- Assignment 4
CREATE INDEX idx_covering
ON appointments
(
doctor_id,
appointment_date,
status
);
EXPLAIN
SELECT
doctor_id,
appointment_date,
status
FROM appointments
WHERE doctor_id=1;