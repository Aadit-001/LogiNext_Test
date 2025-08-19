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

-- then we run the program
java MainApp