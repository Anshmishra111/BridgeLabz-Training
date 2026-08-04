USE health_clinic_db;
SELECT p.name AS patient_name, a.appointment_date, d.name AS doctor_name
FROM Appointments a
INNER JOIN Patients p ON a.patient_id = p.patient_id
INNER JOIN Doctors d ON a.doctor_id = d.doctor_id;
SELECT p.name, a.appointment_date
FROM Patients p
LEFT JOIN Appointments a ON p.patient_id = a.patient_id;
SELECT d.name AS doctor, a.appointment_date
FROM Appointments a
RIGHT JOIN Doctors d ON a.doctor_id = d.doctor_id;
SELECT p.name, a.appointment_date
FROM Patients p
LEFT JOIN Appointments a ON p.patient_id = a.patient_id
UNION
SELECT p.name, a.appointment_date
FROM Patients p
RIGHT JOIN Appointments a ON p.patient_id = a.patient_id;
SELECT d.name AS doctor, m.name AS mentor
FROM Doctors d
JOIN Doctors m ON d.mentor_id = m.doctor_id;
SELECT d.name, ts.slot_time
FROM Doctors d
CROSS JOIN TimeSlots ts;
SELECT p.name AS patient, d.name AS doctor, s.name AS specialization,
a.appointment_date, b.amount, b.payment_status
FROM Appointments a
JOIN Patients p ON a.patient_id = p.patient_id
JOIN Doctors d ON a.doctor_id = d.doctor_id
JOIN Specializations s ON d.specialization_id = s.specialization_id
LEFT JOIN Billing b ON a.appointment_id = b.appointment_id;

DELIMITER //
CREATE PROCEDURE GetPatientAppointments(IN pid INT)
BEGIN
SELECT a.appointment_date, d.name AS doctor
FROM Appointments a
JOIN Doctors d ON a.doctor_id = d.doctor_id
WHERE a.patient_id = pid;
END //
DELIMITER ;

CALL GetPatientAppointments(101);

DELIMITER //
CREATE PROCEDURE AddPatient(IN p_name VARCHAR(100), IN p_phone VARCHAR(15))
BEGIN
INSERT INTO Patients(name, phone) VALUES (p_name, p_phone);
END //
DELIMITER ;
CALL GetPatientCount(@count);
SELECT @count;

DELIMITER //
CREATE PROCEDURE ApplyDiscount(INOUT amount DECIMAL(10,2))
BEGIN
SET amount = amount - (amount * 0.10);
END //
DELIMITER ;
SET @bill = 1000;
CALL ApplyDiscount(@bill);
SELECT @bill; -- 900

DELIMITER //
CREATE PROCEDURE SafeInsertPatient(IN p_name VARCHAR(100), IN p_phone VARCHAR(15))
BEGIN
DECLARE EXIT HANDLER FOR SQLEXCEPTION
BEGIN
ROLLBACK;
SELECT 'Error: Could not insert patient' AS message;
END;
START TRANSACTION;
INSERT INTO Patients(name, phone) VALUES (p_name, p_phone);
COMMIT;
END //
DELIMITER ;

-- Before Insert
DELIMITER //
CREATE TRIGGER before_appointment_insert
BEFORE INSERT ON Appointments
FOR EACH ROW
BEGIN
IF NEW.appointment_date < CURDATE() THEN
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Cannot book an appointment in the past';
END IF;
END //
DELIMITER ;

-- After insert
DELIMITER //
CREATE TRIGGER after_appointment_insert
AFTER INSERT ON Appointments
FOR EACH ROW
BEGIN
INSERT INTO AuditLog(action, table_name, record_id, action_time)
VALUES ('INSERT', 'Appointments', NEW.appointment_id, NOW());
END //
DELIMITER ;

-- Before update
DELIMITER //
CREATE TRIGGER before_billing_update
BEFORE UPDATE ON Billing
FOR EACH ROW
BEGIN
IF NEW.payment_status = 'Paid' AND OLD.payment_status != 'Paid' THEN
SET NEW.bill_date = NOW();
END IF;
END //
DELIMITER ;

-- After Update
DELIMITER //
CREATE TRIGGER after_appointment_update
AFTER UPDATE ON Appointments
FOR EACH ROW
BEGIN
IF NEW.status = 'Completed' AND OLD.status != 'Completed' THEN
INSERT INTO VisitHistory(appointment_id, visit_date)
VALUES (NEW.appointment_id, NOW());
END IF;
END //
DELIMITER ;

-- Before Delete
DELIMITER //
CREATE TRIGGER before_patient_delete
BEFORE DELETE ON Patients
FOR EACH ROW
BEGIN
IF EXISTS (SELECT 1 FROM Appointments WHERE patient_id = OLD.patient_id AND
status='Confirmed') THEN
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Cannot delete a patient with active appointments';
END IF;
END //
DELIMITER ;

-- After Delete
DELIMITER //
CREATE TRIGGER after_patient_delete
AFTER DELETE ON Patients
FOR EACH ROW
BEGIN
INSERT INTO DeletedPatientsArchive(patient_id, name, deleted_at)
VALUES (OLD.patient_id, OLD.name, NOW());
END //
DELIMITER ;





