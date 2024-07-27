### Customer Management System

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

2. **Configure MySQL database:**

   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_database_username
   spring.datasource.password=your_database_password
   spring.jpa.hibernate.ddl-auto=update ```


### FrontEnd Snapshots
1. **Login page**
   ![image](https://github.com/user-attachments/assets/5d255886-0a5d-4065-b5e1-03f8b7914e0f)

2. **Dashboard**
   ![image](https://github.com/user-attachments/assets/307125ae-b872-4b48-b4d1-3af781c4cc40)

3. **Add Customer**
   ![image](https://github.com/user-attachments/assets/48096322-0d71-481f-95d0-812f7eb50cf6)

4. **Update Customer**
   ![image](https://github.com/user-attachments/assets/e70e9aa6-a252-4096-805c-4ab7d450bc45)

5. **Dashboard** (login as username=test@sunbase.com & password=Test@123)
   ![image](https://github.com/user-attachments/assets/fd3dcbde-29dd-482b-8dbf-9e5f988f591a)









### API Endpoints

**Authentication**
![image](https://github.com/user-attachments/assets/cb0b2f0a-633d-45ae-9f30-1715193d298d)

**Customer Management**
![image](https://github.com/user-attachments/assets/8e9f4b49-2406-4d64-9ec3-87a7798df2b0)

**Update Customer**
![image](https://github.com/user-attachments/assets/8708ebfb-cd1c-40a7-bd0f-d04399d67ea1)

**Get Customer List (with pagination, sorting, and searching)**
GET /api/getCustomerListWithPagination

Request Parameters:
- page: The page number (default is 0)
- size: The page size (default is 10)
- sort: The sorting criteria (e.g., first_name,asc)
- searchType: The field to search by (e.g., first_name)
- searchValue: The value to search for (e.g., Jane)

**Get Single Customer:**
GET /api/customer/{id}

**Delete Customer**
DELETE /api/delete/{id}


**Sync Customers**
![image](https://github.com/user-attachments/assets/6714337a-3e88-44c2-b3db-3a617ed23685)



### How to Use
**Login**:

Open the index.html file in your browser and log in with the provided credentials. The token received from the login API will be used for subsequent authenticated API calls.

**Customer List:**

After logging in, you will be redirected to the Customer List screen where you can view, add, update, and delete customers.

**Add a New Customer:**

Click the "Add Customer" button to open the customer form. Fill in the customer details and submit the form to add a new customer.

**Sync Customers:**

Click the "Sync" button to fetch customer data from the remote API and update the local database.

### How to Use
**Login:**

Open the index.html file in your browser and log in with the provided credentials. The token received from the login API will be used for subsequent authenticated API calls.

**Customer List:**

After logging in, you will be redirected to the Customer List screen where you can view, add, update, and delete customers.

**Add a New Customer:**

Click the "Add Customer" button to open the customer form. Fill in the customer details and submit the form to add a new customer.

**Sync Customers:**

Click the "Sync" button to fetch customer data from the remote API and update the local database.

**Notes**
Ensure that the MySQL server is running and the database is configured correctly.
Do not hardcode the login credentials in the code. Use environment variables or a configuration file to manage sensitive information.

</br>
**License**
This project is licensed under the MIT License. See the LICENSE file for details.
Ensure that the MySQL server is running and the database is configured correctly.
Do not hardcode the login credentials in the code. Use environment variables or a configuration file to manage sensitive information.





