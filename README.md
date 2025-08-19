# LogiNext Food Delivery Network Assignment

This repository contains my solution to the LogiNext technical assessment for the Food Delivery Network problem. The application is a simulation of a food delivery system that efficiently assigns customer orders to available drivers based on a set of specified rules.

The solution is built with a focus on enterprise-level best practices, including:
* **Object-Oriented Design (OOP):** The code is structured into separate, logical classes for clear separation of concerns.
* **Database Integration:** The application connects to a **MySQL database** to handle all customer and driver data.
* **Security:** Sensitive information like the database password is not hardcoded and is securely handled using a `.env` file.

## Technical Requirements

### Prerequisites

* **Java Development Kit (JDK):** Version 8 or higher.
* **MySQL Server:** A local instance of MySQL server.
* **IDE:** An IDE like VS Code, Eclipse, or IntelliJ IDEA.
* **External Libraries:**
    * **MySQL Connector/J:** For database connectivity.
    * **Java-dotenv:** For securely loading environment variables from a `.env` file.
    * **Kotlin Standard Library:** A required dependency for Java-dotenv.

## Setup & Running Instructions

### 1. Database Setup

First, we create the database and tables in the local MySQL server using MySQL Workbench or the command line.

```sql
CREATE DATABASE rooftop_delivery;
USE rooftop_delivery;

CREATE TABLE drivers (
    driver_id INT PRIMARY KEY,
    availability_time BIGINT
);

CREATE TABLE customers (
    customer_id INT PRIMARY KEY,
    order_time INT,
    travel_time INT,
    assigned_driver_id INT,
    FOREIGN KEY (assigned_driver_id) REFERENCES drivers(driver_id)
);

-- then we insert sample data
INSERT INTO drivers (driver_id) VALUES (1), (2);

INSERT INTO customers (customer_id, order_time, travel_time) VALUES
(1, 1, 10),
(2, 4, 20),
(3, 15, 5),
(4, 22, 20),
(5, 24, 10),
(6, 25, 10);
```

### 2. Configure Your Project in the IDE
Clone the Repository:

git clone [https://github.com/YourGitHubUsername/LogiNext_Test.git](https://github.com/YourGitHubUsername/LogiNext_Test.git)
cd LogiNext_Test

Add External JARs:
Download the .jar files provided in the github or from these links for MySQL Connector/J, Java-dotenv, and Kotlin Standard Library.
## kotlin : https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-stdlib/2.2.20-Beta2
## java_dotenv : https://mvnrepository.com/artifact/io.github.cdimascio/java-dotenv/5.2.2
## mysql connector : https://dev.mysql.com/downloads/connector/j/

In your IDE's project settings (e.g., VS Code's Java Projects panel), add these three .jar files to your project's Referenced Libraries.

Create .env File:

In the project's root directory, create a new file named .env.

Add your MySQL password to this file in the format DB_PASS=Your_Password.


### 3. Run the Application
Open the project in your IDE.

Run the MainApp.java file. The application will connect to the database, process the orders, and display the results in the console. No manual input is required during runtime.

Expected Output
Connected to the database successfully!
C1 - D1
C2 - D2
C3 - D1
C4 - D1
C5 - D2
C6 - No Food :-(
