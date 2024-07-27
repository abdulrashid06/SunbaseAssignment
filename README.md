# Customer Management System

This project is a Customer Management System built with a Spring Boot backend, MySQL database, and a basic HTML/CSS/JavaScript frontend. The application allows you to create, read, update, and delete customer records. It also includes JWT authentication and a sync feature to fetch customer data from a remote API and update the local database.

## Features

- Create a customer
- Update a customer
- Get a list of customers with pagination, sorting, and searching
- Get a single customer based on ID
- Delete a customer
- JWT Authentication
- Sync customer data from a remote API

## Technologies Used

- Backend: Spring Boot, JPA, Hibernate
- Frontend: HTML, CSS, JavaScript
- Database: MySQL

## Prerequisites

- Java 8 or later
- Maven
- MySQL

## Getting Started

### Backend Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/customer-management-system.git
   cd customer-management-system ```

1. **Configure MySQL database:**

   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_database_username
   spring.datasource.password=your_database_password
   spring.jpa.hibernate.ddl-auto=update ```
