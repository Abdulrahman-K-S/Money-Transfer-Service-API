# Money-Transaction-Service

Money-Transaction-Service is a Spring Boot application designed for transferring money between accounts. It leverages various Spring Boot starters and other dependencies to provide a robust and secure service.

## Table of Contents

- [Description](#description)
- [Features](#features)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Technologies Used](#technologies-used)
- [Future Improvements](#future-improvements)

## Description
Money-Transaction-Service is a comprehensive application designed to facilitate secure and efficient money transfers between accounts. Built using Spring Boot, it employs a variety of technologies to ensure robust functionality, including JWT-based authentication, MySQL for persistent storage, H2 for in-memory testing, and Redis for caching. The application also includes data validation, detailed API documentation through OpenAPI, and an intuitive RESTful API design. This service aims to provide a reliable and scalable solution for handling financial transactions in a secure manner.

## Features

* Secure money transactions
* RESTful API
* JWT-based authentication
* MySQL, H2, and Redis integration
* Data validation
* API documentation with OpenAPI

## Installation

### Prerequisites

* Java 21
* Maven 3.6+
* MySQL database
* H2 database
* Redis server

### Steps

1. **Clone the repository:**

    ```bash
    git clone https://github.com/your-repo/Money-Transaction-Service.git
    cd Money-Transaction-Service
    ```

2. **Set up the MySQL database:**

    ```sql
    CREATE DATABASE money_transaction_service;
    ```

3. **Configure the `application-mysql-prod.properties` file:**

    Update the `src/main/resources/application-mysql-prod.properties` file with your MySQL configurations.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/money_transaction_service
    spring.datasource.username=your_mysql_username
    spring.datasource.password=your_mysql_password

    jwt.expiration_ms=your_milliseconds
    jwt.secret=your_secret_key
    ```

4. **Configure the `application-h2-prod.properties` file:**

   Update the `src/main/resources/application-h2-prod.properties` file with your H2 configurations.

   ```properties
   spring.datasource.url=jdbc:h2:mem:money_transaction_service
   spring.datasource.username=your_root_password
   spring.datasource.password=your_h2_password
   
   spring.h2.console.path=your_path
   
   jwt.expiration_ms=your_milliseconds
   jwt.secret=your_secret_key
   ```

4. **Build and run the application:**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## Configuration

The main configuration settings are in the `application.properties` file. Ensure you set the correct values for database connection, H2 and JWT secret in their respective prod property files.

## Usage

Once the application is up and running, you can use a tool like Postman to interact with the API.


## API Endpoints

Here are some of the main API endpoints exposed by the application:

* `POST /api/register` - Register a new user
* `POST /api/login` - Authenticate a user and get a JWT token
* `Post /api/logout` - Logout the user from the system
* `Get /api/balance` - Get user Account Balance
* `POST /api/transfer` - Transfer money to another account and create a transaction ticket
* `Get /api/transactions` - Get user Transactions history
* `POST /api/favorites` - Add recipient to favorites
* `Get /api/favorites` - Retrieve all the favorite recipients
* `Delete /api//favorites/{id}` - Deletes a recipient from the favorite's list
* `Get /api/ratetoEGP` - Change from any currency to EGP
* `Get /api/ratefromEGP` - Change from EGP to any currency
* `Get /api/rate` - Change from any currency to any currency

For a complete list of endpoints and their usage, refer to the OpenAPI documentation available at `/swagger-ui/index.html` after running the spring-boot application.

## Technologies Used

* **Spring Boot 3.3.2**
* **Spring Data JPA**
* **Spring Data Redis**
* **Spring Security**
* **Spring Validation**
* **Spring Web**
* **MySQL**
* **H2**
* **Lombok**
* **JUnit**
* **Spring Security Test**
* **SpringDoc OpenAPI**
* **JJWT**

## Future Improvements
1. **Internationalization and Localization:**
   - Support multiple languages and currencies to cater to a global user base.
   - Implement localization features to provide a customized user experience based on geographical locations.
2. **Advanced Analytics and Reporting:**
   - Introduce a comprehensive analytics dashboard for users to monitor transaction patterns and financial health.
   - Provide detailed reporting capabilities for transaction histories and account activities.

## Authors
Abdulrahman Khaled [Github](https://github.com/Abdulrahman-K-S).

Mohamed ElSawy [Github](https://github.com/Sawy03).

<a href = "https://github.com/Abdulrahman-K-S/Money-Transaction-Service-Backend/graphs/contributors">
   <img src = "https://contrib.rocks/image?repo=https://github.com/Abdulrahman-K-S/Money-Transaction-Service-Backend"/>
 </a>