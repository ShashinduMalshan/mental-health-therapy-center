# Serenity Mental Health Therapy Center System

## Project Overview
The Serenity Mental Health Therapy Center System is a comprehensive digital solution designed to streamline the operations of a mental health therapy center that serves approximately 3,000 patients annually. This system replaces manual paper-based processes with an efficient digital platform for managing therapy programs, patient registrations, scheduling, and payments.

## Features and Functionalities

### User Role Management
- **Admin Role**: Manages therapists and therapy programs
- **Receptionist Role**: Handles patients, scheduling, and payments
- **Secure Authentication**: Password encryption using BCrypt

### Therapist Management (Admin only)
- Create, read, update, and delete therapist profiles
- Assign therapists to therapy programs
- Track therapist schedules and availability

### Therapy Program Management (Admin only)
- Create, read, update, and delete therapy programs
- Define program details (name, duration, fees)
- Link programs with qualified therapists

### Patient Management
- Comprehensive patient profile management
- Medical history tracking
- Multi-program enrollment capability
- Advanced search and filtering functionality

### Session Scheduling
- Appointment booking with therapist assignment
- Rescheduling and cancellation options
- Schedule conflict prevention

### Payment Processing
- Upfront payment requirement enforcement
- Automated invoice generation
- Transaction tracking and history

### Reporting
- Therapist performance metrics
- Financial reports and analytics
- Patient therapy history (read-only)

## Technical Architecture

### Technology Stack
- **Frontend**: JavaFX for the user interface
- **Backend**: Java
- **Database**: MySQL
- **ORM**: Hibernate
- **Security**: BCrypt password encryption

### Architecture Design
- **Layered Architecture**:
  - Presentation Layer (JavaFX Controllers)
  - Business Logic Layer (BO - Business Objects)
  - Data Access Layer (DAO - Data Access Objects)
  - Entity Layer (Hibernate Entities)

### Design Patterns
- **Factory Pattern**: For creating DAO and BO instances
- **Singleton Pattern**: For database connection management
- **DTO Pattern**: For data transfer between layers

### Database Relationships
- **One-to-Many**: 
  - Patient to TherapySessions
  - Patient to Payments
  - Patient to Registrations
  - Therapist to TherapySessions
  - TherapyProgram to Therapists
  - TherapyProgram to Registrations
  - TherapyProgram to Payments
  - Payment to TherapySessions
- **Many-to-One**:
  - TherapySession to Patient
  - TherapySession to Therapist
  - TherapySession to Payment
  - Therapist to TherapyProgram
  - Registration to Patient
  - Registration to TherapyProgram
  - Payment to Patient
  - Payment to TherapyProgram

## Setup Instructions

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- MySQL 8.0 or higher
- Maven

### Installation Steps
1. Clone the repository
2. Configure the database connection in `src/main/resources/application.properties`
3. Run `mvn clean install` to build the project
4. Execute the application using `mvn javafx:run` or run the generated JAR file

### Database Configuration
The application uses Hibernate to automatically create and update the database schema. The default configuration is:
```properties
hibernate.connection.driver_class=com.mysql.cj.jdbc.Driver
hibernate.connection.url=jdbc:mysql://localhost:3306/mental_health_therapy_center?createDatabaseIfNotExist=true
hibernate.connection.username=root
hibernate.connection.password=Ijse@1234
hibernate.dialect=org.hibernate.dialect.MySQLDialect
hibernate.show_sql=true
hibernate.hbm2ddl.auto=update
```

## Usage Instructions

### Login
- Use your assigned username and password
- The system will automatically direct you to the appropriate dashboard based on your role

### Admin Dashboard
- Manage therapists and therapy programs
- View system reports and analytics

### Receptionist Dashboard
- Register new patients
- Schedule therapy sessions
- Process payments
- Generate invoices

### Data Validation
The system implements comprehensive validation for all input fields:
- Email validation using regex patterns
- Phone number format validation
- Required field validation
- Data type validation

### Security Features
- **Password Encryption**: All passwords are encrypted using BCrypt
- **Role-Based Access Control**: Different functionalities are available based on user roles
- **Input Validation**: All user inputs are validated to prevent injection attacks
- **Session Management**: Secure session handling

## Project Structure
- `src/main/java/com/service/mental_health_therapy_center/`
  - `Bo/`: Business Objects for business logic
  - `Dao/`: Data Access Objects for database operations
  - `configuration/`: Hibernate configuration
  - `controller/`: JavaFX controllers
  - `dto/`: Data Transfer Objects
  - `entity/`: Hibernate entity classes
  - `Exceptions/`: Custom exceptions
  - `Utill/`: Utility classes including password encryption

## License

© 2025 All rights reserved. This project is proprietary software developed by
Shasidu Malshan.

## Contact

For any inquiries or support, please contact:
- Email: shasidumalshan@gmail.com
