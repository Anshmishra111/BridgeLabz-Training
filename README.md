# 🚀 BridgeLabz Refresher Training - SQL & DBMS

This repository contains my daily learning, hands-on practice, assignments, SQL scripts, and project implementations completed during the **BridgeLabz Refresher Training**. The training covers **DBMS, MySQL, SQL, JDBC, Java Design Patterns, and building a real-world Health Clinic Management System**.

---

# 📅 Day 1 - DBMS Fundamentals & RDBMS Basics

## 📚 Topics Covered

- Introduction to Data, Database & DBMS
- File System vs DBMS
- Advantages of DBMS
- Types of DBMS
- RDBMS
- Features of RDBMS
- SQL vs NoSQL
- Introduction to MySQL
- MySQL Architecture
- Creating Database
- Creating Tables
- DDL Commands
- DML Commands
- TCL Basics
- DCL Basics

---

## 💻 Practical Work

- Created **health_clinic_db**
- Created Patients Table
- Created Doctors Table
- Created Specializations Table
- Created Appointments Table
- Inserted Sample Records
- Practiced ALTER TABLE
- Executed UPDATE Queries
- Executed DELETE Queries
- Created MySQL User
- Granted Database Privileges

---

## 📁 Folder

```
Refresher-Training/
└── Day-1
```

---

# 📅 Day 2 - ER Diagram, Indexing & Database Normalization

## 📚 Topics Covered

### ER Diagram

- Entity
- Attribute
- Relationship
- Cardinality
- Participation
- Primary Key
- Foreign Key
- Designing ER Diagram

### Indexing

- What is Index
- Why Index
- Internal Working
- B+ Tree
- Clustered Index
- Non-Clustered Index
- Composite Index
- Unique Index
- Covering Index
- Performance Analysis using EXPLAIN

### Database Normalization

- Data Redundancy
- Functional Dependency
- First Normal Form (1NF)
- Second Normal Form (2NF)
- Third Normal Form (3NF)
- Boyce-Codd Normal Form (BCNF)
- Denormalization

---

## 💻 Practical Work

- Designed ER Diagram
- Implemented Primary Keys
- Implemented Foreign Keys
- Created Indexes
- Used EXPLAIN for Query Optimization
- Normalized Database up to 3NF

---

## 📁 Folder

```
Refresher-Training/
└── Day-2
```

---

# 📅 Day 3 - SQL Joins, Stored Procedures & Triggers

## 📚 Topics Covered

### SQL Joins

- INNER JOIN
- LEFT JOIN
- RIGHT JOIN
- FULL OUTER JOIN
- SELF JOIN
- CROSS JOIN
- Multiple Table JOIN

### Stored Procedures

- Why Stored Procedures
- Creating Procedures
- Calling Procedures
- IN Parameters
- OUT Parameters
- INOUT Parameters
- Error Handling

### Triggers

- BEFORE INSERT
- AFTER INSERT
- BEFORE UPDATE
- AFTER UPDATE
- BEFORE DELETE
- AFTER DELETE

---

## 💻 Practical Work

- Implemented SQL JOIN Queries
- Multi-table Joins
- Stored Procedures
- Parameterized Procedures
- Trigger Creation
- Audit Trigger
- Appointment Trigger

---

## 📁 Folder

```
Refresher-Training/
└── Day-3
```

---

# 📅 Day 4 - Health Clinic Application using JDBC

## 📖 Project Overview

Developed a **Health Clinic Console Application** using **Java, JDBC, MySQL, Maven, and HikariCP** following a clean layered architecture. The application enables clinic staff to manage patients, doctors, appointments, billing, and visit history efficiently.

---

## 🎯 Project Features

- Register Patients
- Register Doctors
- Manage Doctor Specializations
- Book Appointments
- Complete Appointments
- Generate Bills
- Maintain Visit History
- CRUD Operations
- Transaction Management
- Menu Driven Console Application

---

## 🏗 Layered Architecture

```
                Console UI
                     │
                     ▼
             Service Layer
                     │
                     ▼
                DAO Layer
                     │
                     ▼
                DTO Layer
                     │
                     ▼
               MySQL Database
```

---

## 📂 Project Structure

```
HealthClinicApp
│
├── pom.xml
│
├── database
│   └── health_clinic_schema.sql
│
└── src
    └── main
        └── java
            └── com.clinic
                │
                ├── Main.java
                │
                ├── config
                │   └── HikariConnectionPool.java
                │
                ├── dto
                │   ├── Patient.java
                │   ├── Doctor.java
                │   ├── Specialization.java
                │   ├── Appointment.java
                │   ├── Billing.java
                │   └── VisitHistory.java
                │
                ├── dao
                │   ├── PatientDAO.java
                │   ├── PatientDAOImpl.java
                │   ├── DoctorDAO.java
                │   ├── DoctorDAOImpl.java
                │   ├── SpecializationDAO.java
                │   ├── SpecializationDAOImpl.java
                │   ├── AppointmentDAO.java
                │   ├── AppointmentDAOImpl.java
                │   ├── BillingDAO.java
                │   ├── BillingDAOImpl.java
                │   ├── VisitHistoryDAO.java
                │   └── VisitHistoryDAOImpl.java
                │
                ├── service
                │   └── AppointmentService.java
                │
                └── ui
                    └── ConsoleMenu.java
```

---

## 🔄 Application Workflow

```
User
 │
 ▼
Console Menu
 │
 ▼
Service Layer
 │
 ▼
DAO Layer
 │
 ▼
MySQL Database
 │
 ▼
Response
```

---

## ⚙ Development Process

### Step 1

- Designed Health Clinic Database

### Step 2

- Created DTO Classes

### Step 3

- Configured Database Connection using HikariCP

### Step 4

- Developed DAO Interfaces and Implementations

### Step 5

- Built Service Layer

### Step 6

- Developed Console Menu

### Step 7

- Connected all Layers

### Step 8

- Tested Complete Application

---

## 💻 Modules

### Patient Module

- Add Patient
- Update Patient
- Delete Patient
- Search Patient
- View All Patients

### Doctor Module

- Add Doctor
- Update Doctor
- Delete Doctor
- Search Doctor
- View All Doctors

### Specialization Module

- Add Specialization
- Assign Doctors

### Appointment Module

- Book Appointment
- Update Appointment
- Cancel Appointment
- Complete Appointment

### Billing Module

- Generate Bill
- Update Payment Status

### Visit History Module

- Record Diagnosis
- Store Prescription
- View Visit History

---

## 🛢 Database Tables

- Patients
- Doctors
- Specializations
- Appointments
- Billing
- VisitHistory

---

## 🔑 JDBC Concepts Used

- JDBC Architecture
- DriverManager
- HikariCP Connection Pool
- PreparedStatement
- CallableStatement
- ResultSet
- Transactions
- Commit
- Rollback
- Try-With-Resources
- Exception Handling

---

## 🚀 Technologies Used

- Java 17
- JDBC
- Maven
- MySQL
- HikariCP
- MySQL Workbench
- Git
- GitHub

---

# 📂 Repository Structure

```
BridgeLabz-Training
│
└── Refresher-Training
    │
    ├── Day-1
    │   ├── SQL Scripts
    │   ├── README.md
    │   └── Screenshots
    │
    ├── Day-2
    │   ├── ER Diagram
    │   ├── Indexing
    │   ├── Database Normalization
    │   ├── README.md
    │   └── Screenshots
    │
    ├── Day-3
    │   ├── SQL Joins
    │   ├── Stored Procedures
    │   ├── Triggers
    │   ├── README.md
    │   └── Screenshots
    │
    └── Day-4
        ├── HealthClinicApp
        ├── database
        │   └── health_clinic_schema.sql
        ├── src
        │   ├── config
        │   ├── dto
        │   ├── dao
        │   ├── service
        │   ├── ui
        │   └── Main.java
        ├── pom.xml
        ├── README.md
        └── Screenshots
```

---

# 🎯 Learning Progress

- ✅ Day 1 – DBMS Fundamentals & SQL Basics
- ✅ Day 2 – ER Diagram, Indexing & Database Normalization
- ✅ Day 3 – SQL Joins, Stored Procedures & Triggers
- ✅ Day 4 – Health Clinic Application using JDBC
- ⏳ More days will be added as the training progresses.

---

# 🎓 Key Skills Gained

- Database Design
- SQL Programming
- ER Diagram Design
- Database Normalization
- Indexing & Query Optimization
- SQL Joins
- Stored Procedures
- Database Triggers
- JDBC Programming
- DAO Design Pattern
- DTO Design Pattern
- Layered Architecture
- Transaction Management
- Connection Pooling using HikariCP
- CRUD Operations
- Exception Handling
- Console-Based Application Development
- Git & GitHub

---

# 👨‍💻 Author

**Himanshu Mishra**

BridgeLabz Refresher Training

Java | JDBC | MySQL | SQL | HikariCP | Maven | Git | GitHub