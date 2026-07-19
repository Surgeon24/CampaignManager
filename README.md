# Campaign Manager - Campaign Management System

A full-stack web application for managing advertising campaigns. This project allows sellers to create, read, update, and delete (CRUD) campaigns for their products, with integrated fund management linked to an internal "Emerald Account".

## 🚀 Technologies Used
*   **Backend:** Java, Spring Boot (Web, Data JPA, Validation)
*   **Database:** H2 Database (In-Memory)
*   **Frontend:** Angular 18, Bootstrap 5, HTML/CSS
*   **Documentation:** OpenAPI 3.0 (Swagger UI)
*   **Build Tool:** Gradle

## ⚙️ Core Features & Business Logic
*   **Full CRUD Operations:** Manage campaigns with dedicated RESTful endpoints.
*   **Transactional Fund Management:** 
    *   Campaign funds are strictly deducted from the user's Emerald Account balance upon creation.
    *   Funds are refunded automatically upon campaign deletion.
    *   Updates to campaign funds are dynamically recalculated, preventing operations if the account balance is insufficient (custom `InsufficientFundsException`).
*   **Data Validation:** Both backend (Hibernate Validator) and frontend enforce mandatory fields, minimum bid amounts ($0.01), and minimum radius values (1km).
*   **Dynamic UI:** The frontend leverages Angular's reactivity (`ChangeDetectorRef`) to instantly update the Emerald Account balance badge without page reloads.

## 🛠️ How to Run the Project

### 1. Start the Backend (Spring Boot)
1. Navigate to the root directory of the project.
2. Build and run the application using Gradle:
   ```bash
   ./gradlew bootRun
   ```
3. The server will start on http://localhost:8080.

### API Documentation: 
Access the Swagger UI at http://localhost:8080/swagger-ui.html.

### 2. Start the Frontend (Angular)
1. Ensure you have Node.js installed (v22+).
2. Navigate to the frontend directory:
    ```bash
    cd frontend
    ```
3. Install dependencies:
    ```bash
    npm install
    ```
4. Start the development server:
    ```bash
    ng serve
    ```
5. Open your browser and navigate to http://localhost:4200.
