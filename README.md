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
| Day 7 | Spring REST API & H2 | ContactApp REST API |
| Day 8 | API Testing & SDLC | REST Assured + JSON Server |
| Day 9 | Spring Boot Fundamentals | Contacts App + Spring Boot |
| Day 10 | Dependency Injection & H2 | Contacts App + Employee Payroll App |
| Day 11 | Spring Services, JPA & JDBC | Employee Payroll App |
| Day 12 | Spring Bean Scopes, Logging, Maven & Postman | Employee Payroll App + Address Book App |

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

Practiced SQL operations:

- INSERT
- SELECT
- UPDATE
- DELETE
- ALTER TABLE

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

Console UI → Service Layer → DAO Layer → DTO Layer → MySQL Database

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

Client → Spring MVC Controller → Service Layer → Repository / DAO → Database

### Technologies

`Java` `Spring MVC` `Tomcat` `Maven` `JSP` `MySQL`

---

# 📅 Day 7 — ContactApp REST API

## Project: ContactApp

Worked on **Spring REST API and request handling** for the Contacts App, focusing on REST conventions, validation, error handling, H2, and distributed architecture concepts.

### REST API

- GET `/contacts`
- GET `/contacts/{id}`
- POST `/contacts`
- PUT `/contacts/{id}`
- PATCH `/contacts/{id}`
- DELETE `/contacts/{id}`

### Key Topics

- RESTful API Design
- HTTP Methods
- HTTP Status Codes
- `ResponseEntity`
- Request Validation
- `@Valid`
- `@ExceptionHandler`
- `@ControllerAdvice`
- H2 In-Memory Database
- Distributed Architecture Overview

### Validation

Contact data was validated using:

- `@NotBlank`
- `@Email`
- `@Pattern`

### HTTP Status Codes

- 200 OK
- 201 Created
- 204 No Content
- 400 Bad Request
- 404 Not Found
- 409 Conflict
- 500 Internal Server Error

### H2 Database

Used H2 for fast development and testing with an in-memory database.

H2 Console:

`http://localhost:8080/h2-console`

### Technologies

`Java` `Spring Boot` `Spring REST` `H2` `Maven` `MySQL` `Git` `GitHub`

---

# 📅 Day 8 — API Testing Tools & SDLC

## Project: ContactApp API Testing

Day 8 focused on **automated REST API testing**, mock REST APIs, and understanding the **Software Development Life Cycle (SDLC)**.

### REST Assured

Used **REST Assured** to automate API testing instead of relying only on manual Postman/curl testing.

### Key Topics

- REST Assured
- Automated API Testing
- Given / When / Then
- Status Code Assertions
- Response Body Assertions
- Positive & Negative Test Cases
- JSON Server
- SDLC

### REST Assured Flow

Given → When → Then → Assertions

Tested:

- GET `/contacts`
- POST `/contacts`
- GET non-existent contact
- Invalid contact data
- Response status codes
- Response fields

### JSON Server

Used JSON Server as a lightweight mock REST API for frontend development and API prototyping.

Supported:

- GET
- POST
- PUT
- DELETE

### SDLC

Learned the major SDLC stages:

Requirement Gathering → Design → Implementation → Testing → Deployment → Maintenance

Also compared:

- Waterfall
- Agile

### Final API Testing

Created `ContactApiTest.java` with automated tests covering:

- Get all contacts
- Create contact
- Get contact by ID
- Non-existent contact
- Blank name validation
- Invalid phone validation

### Technologies

`Java` `Spring Boot` `REST Assured` `JUnit` `JSON Server` `H2` `Maven`

---

# 📅 Day 9 — Spring Boot Fundamentals

## Overview

Focused on understanding **Spring Boot**, how it builds on Spring, and how it reduces manual configuration through auto-configuration, starters, and embedded servers.

### Key Topics

- Spring vs Spring Boot
- Auto-Configuration
- Starter Dependencies
- Embedded Tomcat
- Spring MVC
- Spring Initializr
- Contacts App

### Spring Boot vs Spring

Spring Boot simplifies the configuration required for Spring applications by providing:

- `@SpringBootApplication`
- Auto-Configuration
- Starter Dependencies
- Embedded Tomcat
- Automatic DispatcherServlet configuration

### Auto-Configuration

Learned how Spring Boot uses the **classpath and application properties** to automatically configure required components such as:

- DataSource
- DispatcherServlet
- Embedded Tomcat

### Spring Boot Starters

Learned how starters group commonly required dependencies.

Example dependency:

`spring-boot-starter-web`

### Embedded Tomcat

Compared traditional external Tomcat deployment with Spring Boot's embedded Tomcat.

Traditional:

Application → WAR → External Tomcat

Spring Boot:

Application → Executable JAR → Embedded Tomcat

### Spring Initializr

Created Spring Boot projects using:

- Maven
- Java 17
- Spring Web
- Validation
- H2 Database

### Contacts App — Search Feature

Extended the Contacts App with a search-by-name endpoint:

`GET /contacts/search?name=Rav`

The search follows:

Controller → Service → DAO

### Technologies

`Java` `Spring Boot` `Spring MVC` `Spring Initializr` `Maven` `H2` `MySQL`

---

# 📅 Day 10 — Dependency Injection & H2 Database

## Overview

Deepened understanding of **Dependency Injection** and applied Spring Boot's H2 auto-configuration concepts to practical applications.

### Key Topics

- Dependency Injection
- Multiple Bean Implementations
- `@Primary`
- `@Qualifier`
- Circular Dependencies
- Constructor Injection
- Constructor Resolution
- H2 In-Memory Database
- H2 File-Persistent Database
- H2 Console
- Spring Boot Auto-Configuration

### Dependency Injection

Handled multiple implementations of the same interface using:

- `@Primary`
- `@Qualifier`

### Circular Dependencies

Learned how circular dependencies occur when two services depend directly on each other.

AppointmentService → BillingService → AppointmentService

The preferred solution is to redesign responsibilities rather than simply using `@Lazy` to hide the dependency cycle.

### H2 Database

Practiced both H2 modes:

In-Memory:

`jdbc:h2:mem:contactsdb`

File-Persistent:

`jdbc:h2:file:./data/contactsdb`

### H2 Console

Configured the H2 Console:

`spring.h2.console.enabled=true`

`spring.h2.console.path=/h2-console`

### Project 1 — Contacts App

Configured the Contacts App with **file-persistent H2** and verified that contacts remain available after restarting the application.

### Project 2 — Employee Payroll App

Created a second Spring Boot application using the reusable:

DTO → DAO → Service → Controller

architecture.

Implemented:

- Employee DTO
- Employee DAO
- Employee Service
- Employee Controller
- H2 Database
- Employee validation
- Employee REST endpoints

### Employee API

- GET `/employees`
- GET `/employees/{id}`
- POST `/employees`

### Technologies

`Java` `Spring Boot` `Spring MVC` `H2` `Maven` `REST API` `Validation`

---

# 📅 Day 11 — Spring Services, Spring JPA & Spring JDBC

## Overview

Focused on formalizing the **three-layer architecture** and understanding three different ways to access a relational database: hand-written JDBC, `JdbcTemplate`, and Spring Data JPA.

### Key Topics

- Spring REST API Service Layer
- Controller, Service & DAO/Repository responsibilities
- Spring Data JPA
- JPA Entity Mapping
- `JpaRepository`
- Derived Query Methods
- `JdbcTemplate`
- `RowMapper`
- Hand-written JDBC vs JdbcTemplate vs JPA

### Three-Layer Architecture

Controller → Service → DAO / Repository → Database

### Layer Responsibilities

**Controller**
- Handles HTTP input
- Calls Service methods
- Returns HTTP responses

**Service**
- Contains business logic
- Handles validation
- Coordinates repositories
- Owns transaction boundaries

**DAO / Repository**
- Handles database access
- Contains SQL or JPA logic

### Spring Data JPA

Used `@Entity` to map Java classes to database tables and `JpaRepository` to provide CRUD operations without writing an implementation class.

Example:

`EmployeeRepository extends JpaRepository<Employee, Integer>`

Common operations:

- `save()`
- `findById()`
- `findAll()`
- `deleteById()`
- `count()`

### Derived Query Methods

Practiced queries such as:

- `findByDepartment(String department)`
- `findByNameContaining(String namePart)`
- `findBySalaryGreaterThan(BigDecimal minSalary)`

### Spring JDBC

Learned `JdbcTemplate` as a middle ground between hand-written JDBC and JPA.

It reduces:

- Connection handling
- Statement setup
- Resource cleanup
- SQL exception boilerplate

while still allowing direct SQL control.

### JDBC vs JdbcTemplate vs JPA

| Feature | JDBC | JdbcTemplate | Spring Data JPA |
|---------|------|--------------|-----------------|
| SQL | Manual | Manual | Usually Generated |
| Mapping | Manual | RowMapper | Hibernate |
| Boilerplate | High | Medium | Low |
| SQL Control | Full | Full | Reduced |
| Best For | Custom SQL | SQL Control + Less Boilerplate | CRUD Applications |

### Project — Employee Payroll App

Rebuilt the **Employee Payroll App using Spring Data JPA**.

Implemented:

- `Employee` Entity
- `EmployeeRepository`
- `EmployeeService`
- CRUD Operations
- Derived Query Methods
- Salary Validation
- JPA Configuration

### Technologies

`Java` `Spring Boot` `Spring Data JPA` `Hibernate` `Spring JDBC` `JdbcTemplate` `H2` `Maven`

---

# 📅 Day 12 — Spring Bean Scopes, Logging, Maven & Postman

## Overview

Focused on **Spring Bean Scopes, production-grade logging, Maven build and dependency management, and structured API testing with Postman**.

### Key Topics

- Spring Bean Scopes
- Singleton, Prototype, Request & Session
- SLF4J & Logback
- Logging Levels
- Maven Dependency Resolution
- Maven Build Lifecycle
- Postman Collections
- Environment Variables
- Request Chaining
- Test Assertions

### Spring Bean Scopes

Learned the four main Spring bean scopes:

- Singleton → ONE instance for the entire application
- Prototype → NEW instance every time requested
- Request → ONE instance per HTTP request
- Session → ONE instance per user session

Singleton is the default scope for Spring beans, while Prototype creates a new instance whenever the bean is requested.

### Logging

Used **SLF4J + Logback** for application logging.

Standard log levels:

- TRACE
- DEBUG
- INFO
- WARN
- ERROR

Replaced `System.out` and `System.err` with structured logging and configured logging levels through `application.properties`.

### Maven

Learned Maven dependency resolution and the build lifecycle.

clean → validate → compile → test → package

Practiced:

- `mvn dependency:tree`
- `mvn package`
- `mvn package -DskipTests`

### Postman

Created structured API testing workflows using:

- Collections
- Environments
- Variables
- Request Chaining
- Test Assertions
- Collection Runner

Example:

`{{baseUrl}}/contacts`

`{{baseUrl}}/contacts/{{contactId}}`

### Project 1 — Employee Payroll App

Worked on the Employee Payroll App by:

- Completing CRUD endpoints
- Replacing `System.out/System.err` with logging
- Creating a Postman collection
- Adding environment variables
- Adding chained requests
- Adding API assertions
- Running the API collection

### Project 2 — Address Book App

Created an Address Book App using:

Controller → Service → Repository → H2 Database

Implemented:

- Address Entity
- Address Repository
- Address Service
- Address Controller
- Validation
- City search
- Contact name search
- SLF4J logging
- REST APIs

### Address API

- GET `/addresses`
- GET `/addresses/{id}`
- POST `/addresses`

### Technologies

`Java` `Spring Boot` `Spring Data JPA` `H2` `SLF4J` `Logback` `Maven` `Postman` `REST API`

---

# 🗂️ Repository Structure

BridgeLabz-Training/

└── Refresher-Training/

    ├── Day-1/
    │   └── SQL & DBMS

    ├── Day-2/
    │   └── ER Diagram, Indexing & Normalization

    ├── Day-3/
    │   └── Joins, Procedures & Triggers

    ├── Day-4/
    │   └── HealthClinicApp/

    ├── Day-5/
    │   ├── Servlet-Project/
    │   └── Spring-Project/

    ├── Day-6/
    │   └── Greetings-CRUD-Spring-MVC/

    ├── Day-7/
    │   └── ContactApp/

    ├── Day-8/
    │   ├── REST-Assured-Tests/
    │   └── JSON-Server/

    ├── Day-9/
    │   └── Spring-Boot-Contacts-App/

    ├── Day-10/
    │   ├── Contacts-App/
    │   └── Employee-Payroll-App/

    ├── Day-11/
    │   └── Employee-Payroll-App-JPA/

    └── Day-12/
        ├── Employee-Payroll-App/
        └── Address-Book-App/

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
- Spring Data JPA
- Hibernate
- REST API
- JSP
- Thymeleaf

### Testing & API

- REST Assured
- JUnit
- JSON Server
- Postman

### Build & Server

- Maven
- Apache Tomcat
- HikariCP
- JdbcTemplate
- SLF4J
- Logback

### Development Tools

- IntelliJ IDEA
- VS Code
- MySQL Workbench
- Git
- GitHub

---

# 🎯 Learning Progress

Day 1  ✅  DBMS & SQL

Day 2  ✅  Database Design

Day 3  ✅  Advanced SQL

Day 4  ✅  JDBC Health Clinic Application

Day 5  ✅  Servlet & Spring

Day 6  ✅  Greetings CRUD Application

Day 7  ✅  ContactApp REST API

Day 8  ✅  REST Assured, JSON Server & SDLC

Day 9  ✅  Spring Boot Fundamentals & Contacts App

Day 10 ✅  Dependency Injection, H2 & Employee Payroll App

Day 11 ✅  Spring Services, JPA & Spring JDBC

Day 12 ✅  Bean Scopes, Logging, Maven & Postman

---

# 👨‍💻 Author

### Himanshu Mishra

**BridgeLabz Refresher Training**

> Learning → Building → Testing → Improving 🚀