# Purchase System (MRP Workflow)

## Project Overview

This project is a role-based Material Requirement Planning (MRP) system built using Spring Boot, Thymeleaf, and MySQL. It simulates a real-world enterprise workflow across Production, Warehouse, Purchase, and Finance departments.

---

## Features

* Role-based login system
* Production creates material requirements
* Warehouse verifies available stock
* Automatic shortage calculation
* Purchase generates procurement requests
* Finance approves or rejects requests
* Business logic to avoid unnecessary purchases
* Audit tracking to record user actions at each stage

---

## Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA (Hibernate)
* Thymeleaf
* MySQL

---

## Database Tables

* Users
* Material
* MRP

---

## Workflow

Production → Warehouse → Purchase → Finance

---

## How to Run

1. Clone the repository
2. Create MySQL database: purchase_system
3. Update application.properties with your database credentials
4. Run the Spring Boot application
5. Open browser and go to: http://localhost:8080

---

## Project Highlights

* Implemented role-based workflow system
* Designed relational database with multiple foreign key mappings
* Applied conditional logic for stock validation and procurement
* Built audit tracking for user actions across departments
* Developed dynamic UI using Thymeleaf

---

## ER Diagram

<img width="591" height="464" alt="er-diagram" src="https://github.com/user-attachments/assets/7f704dc9-276d-4bb8-bffd-48e22ca2ca37" />


## Author

Dars
