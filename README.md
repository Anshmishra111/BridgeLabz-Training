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
| Day 13 | Spring Security & JWT | Fundoo Notes User Management |
| Day 14 | Authorization & JPA Relationships | Fundoo Notes Notes Management |
| Day 15 | Organisation, Search & Tags | Fundoo Notes Organisation |
| Day 16 | JMS & Redis Caching | Fundoo Notes Async + Caching |

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

# 📅 Day 13 — Spring Security & JWT Authentication

## Overview

Started advanced backend security with the **Fundoo Notes Application**.

### Key Topics

- Spring Security
- Authentication
- Authorization
- SecurityFilterChain
- JWT
- BCrypt
- User Registration
- User Login
- Stateless Authentication

### Authentication

Authentication answers:

> Who are you?

Example:

Email + Password → User Identity

### Authorization

Authorization answers:

> What are you allowed to access?

---

## Spring Security Filter Chain

Request flow:

Client → Security Filter Chain → Controller

Protected requests are checked before reaching the Controller.

### Important Security Configuration

- `SecurityFilterChain`
- `HttpSecurity`
- `permitAll()`
- `authenticated()`
- `SessionCreationPolicy.STATELESS`

### Public Endpoints

```text
/auth/register
/auth/login

JWT

JWT = JSON Web Token

Flow:

Login → Verify Credentials → Generate JWT → Client Stores JWT → JWT Sent with Future Requests

JWT structure:

HEADER.PAYLOAD.SIGNATURE
Header

Contains token metadata such as algorithm and token type.

Payload

Contains claims such as:

User ID
Email
Issued time
Expiration
Signature

Verifies token integrity.

Important

JWT is signed, not encrypted.

Sensitive information should not be stored in the payload.

BCrypt

Passwords should never be stored as plain text.

Flow:

Password → BCrypt → Hash → Database

Password verification:

Entered Password
       ↓
BCrypt.matches()
       ↓
Stored Hash
User Registration Flow
POST /auth/register
        ↓
Controller
        ↓
Service
        ↓
Check Email
        ↓
BCrypt Password
        ↓
Save User
        ↓
Generate JWT
        ↓
Return Response
User Login Flow
POST /auth/login
        ↓
Controller
        ↓
Service
        ↓
Find User
        ↓
BCrypt.matches()
        ↓
Generate JWT
        ↓
Return JWT
Technologies

Java Spring Boot Spring Security JWT BCrypt Spring Data JPA MySQL/H2 Maven

📅 Day 14 — Authorization & JPA Relationships
Overview

Extended the Fundoo Notes application with JWT validation, authorization, ownership security and JPA relationships.

Key Topics
JWT Validation
OncePerRequestFilter
SecurityContextHolder
Ownership Authorization
IDOR Prevention
@ManyToOne
@OneToMany
@JoinColumn
mappedBy
LAZY Fetching
EAGER Fetching
LazyInitializationException
Notes Management
JWT Validation

Every protected request contains:

Authorization: Bearer <JWT>

Flow:

Request
   ↓
JWT Filter
   ↓
Validate Token
   ↓
Extract User ID
   ↓
SecurityContextHolder
   ↓
Controller
OncePerRequestFilter

Used to run JWT validation once for each HTTP request.

HTTP Request
     ↓
OncePerRequestFilter
     ↓
JWT Validation
     ↓
SecurityContext
     ↓
Controller
SecurityContextHolder

Stores authentication information for the current request.

Example:

JWT
 ↓
Filter
 ↓
SecurityContextHolder
 ↓
Authenticated User
Ownership Authorization

A user should only access their own notes.

Bad approach:

GET /notes?userId=2

A malicious user could change the ID.

Better approach:

JWT
 ↓
Authenticated User ID
 ↓
Ownership Query
 ↓
User's Notes Only
IDOR

IDOR = Insecure Direct Object Reference

Example:

User A → /notes/10
User A changes URL → /notes/20

If Note 20 belongs to User B, the API must reject the request.

Ownership should be verified in the backend.

User → Note Relationship

One user can have many notes.

User 1
   |
   +---- Note 1
   +---- Note 2
   +---- Note 3

Relationship:

User 1 : N Note
@ManyToOne

Each Note belongs to one User.

@ManyToOne
@JoinColumn(name = "user_id")
private User owner;
@OneToMany

One User can have many Notes.

@OneToMany(
    mappedBy = "owner",
    cascade = CascadeType.ALL,
    orphanRemoval = true
)
private List<Note> notes;
@JoinColumn

Defines the foreign key column.

@JoinColumn(name = "user_id")

Database:

NOTE
----------------
note_id
title
content
user_id
mappedBy

Indicates that another entity owns the relationship.

mappedBy = "owner"

The Note.owner field owns the foreign key.

LAZY vs EAGER
LAZY

Related data loads only when accessed.

User loaded
   ↓
Notes not loaded
   ↓
getNotes()
   ↓
Notes loaded
EAGER

Related data loads immediately.

LazyInitializationException

Can occur when a LAZY relationship is accessed after the persistence context/session is closed.

Solutions include:

Accessing data within the transaction
Using suitable JOIN FETCH queries
Notes Management

Implemented ownership-based Notes CRUD.

Example repository concept:

findByNoteIdAndOwner(noteId, owner)

This ensures both:

Note exists
AND
Note belongs to authenticated user
Technologies

Java Spring Boot Spring Security JWT Spring Data JPA Hibernate MySQL/H2 Maven

📅 Day 15 — Organisation, Search & Tags
Overview

Extended Fundoo Notes with note organization and advanced search features.

Key Topics
Active Notes
Archived Notes
Trashed Notes
Pinned Notes
Note State
Enum
Search
Filters
Tags
Specifications
Dynamic Queries
Many-to-Many Relationships
Note State

Instead of multiple independent booleans, note status can be represented using an enum.

ACTIVE
ARCHIVED
TRASHED

Example:

public enum NoteState {
    ACTIVE,
    ARCHIVED,
    TRASHED
}
@Enumerated(EnumType.STRING)

Stores enum values as readable strings.

ACTIVE
ARCHIVED
TRASHED

This is preferable to ordinal values such as:

0
1
2

because changing enum order does not change the meaning of existing database values.

Note Operations
Archive
ACTIVE
  ↓
ARCHIVED
Trash
ACTIVE
  ↓
TRASHED
Restore
ARCHIVED / TRASHED
        ↓
      ACTIVE
Pin
ACTIVE
  ↓
PINNED

Trashed notes should not be pinnable.

PATCH

PATCH is suitable when only part of a resource changes.

Examples:

PATCH /notes/{id}/archive
PATCH /notes/{id}/trash
PATCH /notes/{id}/restore
PATCH /notes/{id}/pin
Search & Filtering

Examples:

GET /notes
GET /notes?state=archived
GET /notes?state=trashed
GET /notes?pinned=true

Search can support:

Title
State
Tag
Multiple filters together
JPA Specification

When many optional search parameters exist, creating a repository method for every combination becomes difficult.

Example combinations:

Title
Tag
State

Title + Tag
Title + State
Tag + State

Title + Tag + State

Specifications allow dynamic query construction.

Repository:

public interface NoteRepository
        extends JpaRepository<Note, Integer>,
        JpaSpecificationExecutor<Note> {
}

Flow:

Search Request
      ↓
Optional Filters
      ↓
Specification
      ↓
Predicates
      ↓
Database Query
Owner Predicate

Every Notes query should be scoped to the authenticated user.

Current User
      +
Search Filters
      ↓
Specification
      ↓
User's Notes Only

This prevents users from seeing another user's notes.

Note & Tag Relationship

One Note can have many Tags.

One Tag can belong to many Notes.

Therefore:

Note M : N Tag
@ManyToMany

Example:

@ManyToMany
@JoinTable(
    name = "note_tags",
    joinColumns =
        @JoinColumn(name = "note_id"),
    inverseJoinColumns =
        @JoinColumn(name = "tag_id")
)
private Set<Tag> tags;
Junction Table

The relationship can be represented using:

note_tags
----------------
note_id
tag_id

Example:

Note 1 → Tag 1
Note 1 → Tag 2
Note 2 → Tag 1
Tag Entity
@Entity
public class Tag {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int tagId;

    @Column(
        nullable = false,
        unique = true
    )
    private String name;
}
Tag Search

Example:

GET /notes?tag=urgent

The query should also include the authenticated user.

Technologies

Java Spring Boot Spring Data JPA Hibernate Specification MySQL/H2 Maven

📅 Day 16 — JMS Asynchronous Messaging & Redis Caching
Overview

Day 16 introduced two advanced backend concepts:

JMS Asynchronous Messaging
Redis Caching

These solve two different backend problems.

Problem 1 — Slow Background Operations

Example:

Forgot Password
      ↓
Generate Reset Token
      ↓
Send Email
      ↓
Response

Email can take significant time.

The HTTP request should not always wait for the external email operation.

Solution

Use asynchronous messaging.

Producer
   ↓
JMS Queue
   ↓
Consumer
JMS

JMS = Java Message Service

JMS provides a standard way to send and receive messages asynchronously.

Basic architecture:

Producer
    ↓
Queue
    ↓
Consumer
Producer

Producer sends the message.

Example concept:

jmsTemplate.convertAndSend(
    "password-reset-queue",
    message
);
JmsTemplate

Used to send messages to a JMS destination.

Application
    ↓
JmsTemplate
    ↓
JMS Queue
Consumer

Consumer receives messages.

@JmsListener(
    destination = "password-reset-queue"
)
public void handleMessage(
        String message) {
    
    // Process message
}
@JmsListener

Marks a method as a JMS message listener.

It listens for messages from a queue.

Asynchronous Forgot Password Flow
POST /auth/forgot-password
          ↓
Generate Reset Token
          ↓
JMS Producer
          ↓
Password Reset Queue
          ↓
HTTP Response

Meanwhile:

Password Reset Queue
          ↓
JMS Consumer
          ↓
Email Service
          ↓
Email Sent
Synchronous vs Asynchronous
Synchronous
Request
 ↓
Generate Token
 ↓
Send Email
 ↓
Wait
 ↓
Response
Asynchronous
Request
 ↓
Generate Token
 ↓
Queue Message
 ↓
Response

Background:
Queue
 ↓
Consumer
 ↓
Send Email
When to Use JMS

Good use cases:

Email
Notifications
Reminders
Background Reports
Long-running background tasks

Avoid asynchronous processing when the original request immediately needs the result.

Redis

Redis is an in-memory key-value data store.

In Day 16 it is used as a shared cache for JWT validation.

Why Redis?

JWT validation may happen repeatedly:

Request 1 → Validate JWT
Request 2 → Validate JWT
Request 3 → Validate JWT
Request 4 → Validate JWT

Caching can reduce repeated validation work.

Local Cache Problem

A local Java cache belongs to one server.

Example:

Load Balancer
     ↓
Server A
Server B
Server C

Server A's local cache is not automatically available to Server B.

Redis Shared Cache
             Redis
           /   |   \
          /    |    \
     Server A Server B Server C

All application instances can access the shared cache.

Redis Cache Flow
Request
   ↓
Check Redis
   ↓
Cache HIT?
   ├── YES → Use Cached Result
   │
   └── NO
         ↓
    Validate JWT
         ↓
    Store Result
         ↓
    Return Result
Cache Hit
Request
 ↓
Redis
 ↓
Found
 ↓
Use Cached Result
Cache Miss
Request
 ↓
Redis
 ↓
Not Found
 ↓
JWT Validation
 ↓
Store Result
 ↓
Return Result
TTL

TTL = Time To Live

Defines how long cached data remains valid.

Example:

JWT lifetime = 60 seconds
Cache TTL = 60 seconds or less

Important:

Cache TTL <= JWT remaining lifetime

The cache must not outlive the token.

Redis Configuration

Example:

spring.data.redis.host=localhost
spring.data.redis.port=6379
Redis Dependency
spring-boot-starter-data-redis
Day 16 Fundoo Notes Architecture
Client
   ↓
Spring Security
   ↓
JWT Filter
   ↓
Redis Cache
   ↓
JWT Validation
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Database

For asynchronous work:

Controller
   ↓
Service
   ↓
JMS Producer
   ↓
JMS Queue
   ↓
JMS Consumer
   ↓
Email / Background Task
Technologies

Java Spring Boot Spring Security JWT JMS Redis Spring Data JPA Hibernate Maven

🗂️ Repository Structure

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

├── Day-12/
│   ├── Employee-Payroll-App/
│   └── Address-Book-App/

├── Day-13/
│   └── Fundoo-Notes-Authentication/

├── Day-14/
│   └── Fundoo-Notes-Authorization/

├── Day-15/
│   └── Fundoo-Notes-Organisation/

└── Day-16/
    └── Fundoo-Notes-JMS-Redis/
🛠️ Technologies & Tools
Programming
Java
SQL
Database
MySQL
H2
Redis
Backend & Web
JDBC
Servlets
Spring
Spring Boot
Spring MVC
Spring Security
Spring Data JPA
Hibernate
REST API
JSP
Thymeleaf
JMS
Authentication & Security
JWT
BCrypt
Spring Security
SecurityFilterChain
OncePerRequestFilter
SecurityContextHolder
Messaging & Caching
JMS
JmsTemplate
JMS Listener
Redis
Redis Cache
TTL
Testing & API
REST Assured
JUnit
JSON Server
Postman
Build & Server
Maven
Apache Tomcat
HikariCP
JdbcTemplate
SLF4J
Logback
Development Tools
IntelliJ IDEA
VS Code
MySQL Workbench
Git
GitHub
🎯 Learning Progress

Day 1 ✅ DBMS & SQL

Day 2 ✅ Database Design

Day 3 ✅ Advanced SQL

Day 4 ✅ JDBC Health Clinic Application

Day 5 ✅ Servlet & Spring

Day 6 ✅ Greetings CRUD Application

Day 7 ✅ ContactApp REST API

Day 8 ✅ REST Assured, JSON Server & SDLC

Day 9 ✅ Spring Boot Fundamentals & Contacts App

Day 10 ✅ Dependency Injection, H2 & Employee Payroll App

Day 11 ✅ Spring Services, JPA & Spring JDBC

Day 12 ✅ Bean Scopes, Logging, Maven & Postman

Day 13 ✅ Spring Security & JWT Authentication

Day 14 ✅ Authorization & JPA Relationships

Day 15 ✅ Organisation, Search & Tags

Day 16 ✅ JMS Asynchronous Messaging & Redis Caching

