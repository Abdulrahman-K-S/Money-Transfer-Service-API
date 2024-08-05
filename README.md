# Money-Transaction-Service

Money-Transaction-Service is a Spring Boot application designed for transferring money between accounts. It leverages various Spring Boot starters and other dependencies to provide a robust and secure service.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Technologies Used](#technologies-used)

## Features

* Secure money transactions
* RESTful API
* JWT-based authentication
* MySQL and Redis integration
* Data validation
* API documentation with OpenAPI

## Installation

### Prerequisites

* Java 21
* Maven 3.6+
* MySQL database
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

3. **Configure the `application.properties` file:**

    Update the `src/main/resources/application.properties` file with your MySQL and Redis configurations.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/money_transaction_service
    spring.datasource.username=your_mysql_username
    spring.datasource.password=your_mysql_password

    spring.redis.host=localhost
    spring.redis.port=6379

    spring.security.jwt.secret=your_jwt_secret
    ```

4. **Build and run the application:**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## Configuration

The main configuration settings are in the `application.properties` file. Ensure you set the correct values for database connection, Redis, and JWT secret.

## Usage

Once the application is up and running, you can use a tool like Postman to interact with the API.


## API Endpoints

Here are some of the main API endpoints exposed by the application:

* `POST /api/register` - Register a new user
* `POST /api/login` - Authenticate a user and get a JWT token
* `Post /api/logout` - Logout the user from the system
* `POST /api/transfer` - Transfer money between accounts
* `Get /api/balance` - Get user Account Balance
* `POST /api/transfer` - Transfer money to another account and create a transaction ticket
* `Get /api/transactions` - Get user Transactions history
* `POST /api/favorites` - Add recipient to favorites
* `Get /api/favorites` - Retrieve all the favorite recipients
* `Delete /api//favorites/{id}` - Deletes a recipient from the favorite's list
* `Get /api/favorites` - Retrieve all the favorite recipients
* `Get /api/ratetoEGP` - Change from any currency to EGP
* `Get /api/ratefromEGP` - Change from EGP to any currency
* `Get /api/rate` - Change from any currency to any currency



For a complete list of endpoints and their usage, refer to the OpenAPI documentation available at `/swagger-ui.html`.

## Technologies Used

* **Spring Boot 3.3.2**
* **Spring Data JPA**
* **Spring Data Redis**
* **Spring Security**
* **Spring Validation**
* **Spring Web**
* **MySQL**
* **Redis**
* **Lombok**
* **JUnit**
* **Spring Security Test**
* **SpringDoc OpenAPI**
* **JJWT**

