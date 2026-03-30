# 🏭 Purchase System (MRP Workflow)

## 📌 Project Overview

This project is a role-based Material Requirement Planning (MRP) system built using Spring Boot, Thymeleaf, and MySQL. It simulates real-world workflow between Production, Warehouse, Purchase, and Finance departments.

---

## 🚀 Features

* 🔐 Role-based login system
* 🏭 Production creates material requirements
* 📦 Warehouse verifies available stock
* 🧾 Automatic shortage calculation
* 🛒 Purchase generates procurement requests
* 💰 Finance approves/rejects requests
* 🧠 Smart logic to avoid unnecessary purchases
* 📊 Audit tracking (who performed each action)

---

## 🧱 Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA (Hibernate)
* Thymeleaf
* MySQL

---

## 🗄️ Database Tables

* Users
* Material
* MRP

---

## 🔄 Workflow

Production → Warehouse → Purchase → Finance

---

## ▶️ How to Run

1. Clone the repository
2. Create MySQL database: `purchase_system`
3. Update `application.properties`
4. Run Spring Boot application
5. Open: http://localhost:8080

---

## 👨‍💻 Author

Dars
