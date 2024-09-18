# Employee Management System

## Description

The Employee Management System is a Java-based application built using Spring Boot that allows for the management of employee data. This system provides functionalities for creating, updating, retrieving, and deleting employee records, as well as generating reports related to attendance and salary. It incorporates security measures using Spring Security and handles exceptions gracefully with a global exception handler.

## Features

- **CRUD Operations**: Create, Read, Update, and Delete employee records.
- **Authentication**: Secure access using Spring Security and BCrypt password encoding.
- **Reporting**: Generate reports for employee attendance and salary.
- **Global Exception Handling**: Manage exceptions effectively and return appropriate HTTP responses.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- JPA (Java Persistence API)
- Hibernate
- MySQL/PostgreSQL (for database)
- Lombok (for boilerplate code reduction)

## Installation

To set up the Employee Management System on your local machine, follow these steps:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Harish-Palaspagar/employee-management-application
   ```
2. **Navigate to the Project Directory**:
   ```bash
   cd employee-management-application
   ```
3. **Build the Project: Make sure you have Maven installed, then run:**
   ```bash
   mvn clean install
   ```
4. **Run the Application: Use the following command to run the Spring Boot application:**
   ```bash
   mvn spring-boot:run
   ```
5. **Clone the Repository**:
   ```bash
   git clone https://github.com/Harish-Palaspagar/employee-management-application
   ```
   
6. **Sample `application.properties` Configuration**:

Below is the configuration for the `application.properties` file for this project. Ensure that you replace the placeholder values (like `your_username` and `your_password`) with actual values relevant to your environment.

```properties
# Database Configuration

spring.datasource.url=jdbc:postgresql://localhost:5432/employee_management_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# Hibernate Configuration

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Logging

logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

# Server Configuration

server.port=8080

# Swagger Configuration

springdoc.api-docs.enabled=true
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/v3/api-docs
```

## API Endpoints

- **Create Employee:**  
  `POST /employees`  
  Create a new employee by providing the required fields.

- **Retrieve Employee by ID:**  
  `GET /employees/{id}`  
  Retrieve details of an employee by their ID.

- **Update Employee:**  
  `PUT /employees/{id}`  
  Update the details of an existing employee by their ID.

- **Delete Employee:**  
  `DELETE /employees/{id}`  
  Delete an employee by their ID.

- **Retrieve All Employees:**  
  `GET /employees`  
  Get a list of all employees.

- **Generate Attendance Report:**  
  `GET /reports/attendance`  
  Generate a report showing the attendance of employees.

- **Generate Salary Report:**  
  `GET /reports/salary`  
  Generate a report showing the salary distribution of employees.

- **Get Department-wise Employee Count:**  
  `GET /reports/department`  
  Retrieve the count of employees per department.

## Database Schema

Ensure that you have a running instance of MySQL or PostgreSQL with a database named `employee_db`. The schema will be automatically updated by Hibernate based on your entity classes.

## Testing

- Use [Postman](https://www.postman.com/) or any REST client to test the API endpoints.
- Swagger UI will be available at:  
  `http://localhost:8080/swagger-ui.html`  
  for API documentation and testing.