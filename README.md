# 🚀 BridgeLabz Refresher Training

Welcome to my **BridgeLabz Refresher Training Repository**.

This repository contains my daily learning, SQL practice, Java development, database projects, web development, Spring applications, and hands-on assignments completed throughout the training.

The goal of this training is to strengthen my fundamentals and build practical applications using **Java, SQL, JDBC, Servlets, Spring MVC, and Spring Boot**.

---

## 📚 Training Journey

| Day | Topic | Project / Practical |
|-----|-------|---------------------|
| Day 1 | DBMS & SQL Fundamentals | Health Clinic Database |
| Day 2 | ER Diagram, Indexing & Normalization | Database Design |
| Day 3 | SQL Joins, Procedures & Triggers | Advanced SQL |
| Day 4 | JDBC & Layered Architecture | Health Clinic Application |
| Day 5 | Servlets & Spring | Servlet + Spring Applications |
| Day 6 | Spring MVC & CRUD | Greetings App |
| Day 7 | Spring Boot | ContactApp |

---

# 📅 Day 1 — DBMS Fundamentals & SQL

## Overview

Learned the fundamentals of **DBMS, RDBMS, SQL and MySQL** and applied them to a Health Clinic database.

### Key Topics

- DBMS & RDBMS
- SQL vs NoSQL
- MySQL
- DDL, DML, TCL & DCL
- Database and table creation
- CRUD operations
- MySQL users and privileges

### Practical Work

Created:

- `health_clinic_db`
- Patients table
- Doctors table
- Specializations table
- Appointments table

Practiced:

```sql
INSERT
SELECT
UPDATE
DELETE
ALTER TABLE
```

Also created a MySQL application user with limited database privileges.

---

# 📅 Day 2 — ER Diagram, Indexing & Normalization

## Overview

Focused on designing an efficient relational database and understanding how database structure affects performance.

### Key Topics

- ER Diagram
- Entities & Relationships
- Primary Key & Foreign Key
- Cardinality
- Indexing
- B+ Tree
- Query Optimization
- Database Normalization
- 1NF, 2NF, 3NF & BCNF

### Practical Work

- Designed the Health Clinic ER Diagram
- Applied database normalization
- Created indexes
- Practiced query optimization using `EXPLAIN`

---

# 📅 Day 3 — SQL Joins, Stored Procedures & Triggers

## Overview

Advanced SQL concepts were practiced using multiple related tables.

### Key Topics

- INNER JOIN
- LEFT JOIN
- RIGHT JOIN
- CROSS JOIN
- SELF JOIN
- Stored Procedures
- IN, OUT & INOUT parameters
- SQL Triggers

### Practical Work

- Created multi-table JOIN queries
- Implemented Stored Procedures
- Practiced procedure parameters
- Created database Triggers
- Automated database operations using triggers

---

# 📅 Day 4 — Health Clinic Application

## Project: Health Clinic Management System

Built a console-based **Health Clinic Application using Java and JDBC**.

The application manages:

- Patients
- Doctors
- Specializations
- Appointments
- Billing
- Visit History

### Architecture

```text
Console UI
     ↓
Service Layer
     ↓
DAO Layer
     ↓
DTO Layer
     ↓
MySQL Database
```

### Key Concepts

- JDBC
- DAO Pattern
- DTO Pattern
- Layered Architecture
- CRUD Operations
- PreparedStatement
- Transactions
- Commit & Rollback
- HikariCP Connection Pool

### Technologies

`Java` `JDBC` `MySQL` `Maven` `HikariCP`

---

# 📅 Day 5 — Servlet & Spring Application Development

## Project 1: Servlet Application

Built Servlet applications using **Maven and Apache Tomcat**.

### Implemented

- First Servlet
- Servlet deployment with Tomcat
- Web Container concepts
- Login with predefined user
- Name validation
- Password validation
- JSP integration

### Technologies

`Java` `Servlet` `Maven` `Tomcat` `JSP` `HTML`

---

## Project 2: Spring Application

Created basic Spring and Spring Boot applications.

### Implemented

- Hello World application
- REST Controller
- MVC Controller
- Thymeleaf
- Spring Annotations
- IoC Container
- Dependency Injection
- SLF4J Logging

### Technologies

`Java` `Spring Boot` `Spring MVC` `REST API` `Thymeleaf` `SLF4J`

---

# 📅 Day 6 — Greetings App CRUD

## Project: Greetings Application

Completed the **CRUD operations of the Greetings App using Spring MVC and Tomcat Server**.

### CRUD Operations

| Operation | Description |
|-----------|-------------|
| Create | Add a new greeting |
| Read | View greetings |
| Update | Modify an existing greeting |
| Delete | Remove a greeting |

### Architecture

```text
Client
  ↓
Spring MVC Controller
  ↓
Service Layer
  ↓
Repository / DAO
  ↓
Database
```

### Technologies

`Java` `Spring MVC` `Tomcat` `Maven` `JSP` `MySQL`

---

# 📅 Day 7 — ContactApp

## Project: Contact Management Application

Developed a **ContactApp** for managing contact information.

### Features

- Add Contact
- View Contacts
- Update Contact
- Delete Contact
- Search Contact

### Technologies

`Java` `Spring Boot` `Spring MVC` `Maven` `MySQL`

---

# 🗂️ Repository Structure

```text
BridgeLabz-Training/
│
└── Refresher-Training/
    │
    ├── Day-1/
    │   └── SQL & DBMS
    │
    ├── Day-2/
    │   └── ER Diagram, Indexing & Normalization
    │
    ├── Day-3/
    │   └── Joins, Procedures & Triggers
    │
    ├── Day-4/
    │   └── HealthClinicApp/
    │
    ├── Day-5/
    │   ├── Servlet-Project/
    │   └── Spring-Project/
    │
    ├── Day-6/
    │   └── Greetings-CRUD-Spring-MVC/
    │
    └── Day-7/
        └── ContactApp/
```

---

# 🛠️ Technologies & Tools

### Programming
- Java
- SQL

### Database
- MySQL
- H2

### Backend & Web
- JDBC
- Servlets
- Spring
- Spring Boot
- Spring MVC
- REST API
- JSP
- Thymeleaf

### Build & Server
- Maven
- Apache Tomcat
- HikariCP

### Development Tools
- IntelliJ IDEA
- VS Code
- MySQL Workbench
- Git
- GitHub

---

# 🎯 Learning Progress

```text
Day 1  ✅  DBMS & SQL
Day 2  ✅  Database Design
Day 3  ✅  Advanced SQL
Day 4  ✅  JDBC Health Clinic Application
Day 5  ✅  Servlet & Spring
Day 6  ✅  Greetings CRUD Application
Day 7  ✅  ContactApp
```

---

# 👨‍💻 Author

### Himanshu Mishra

**BridgeLabz Refresher Training**

> Learning → Building → Testing → Improving 🚀