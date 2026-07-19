# Campaign Manager - Campaign Management System

A full-stack web application for managing advertising campaigns. This project allows sellers to create, read, update, and delete (CRUD) campaigns for their products, with integrated fund management linked to an internal "Emerald Account".

## 🌐 Live Demo
The application is fully deployed in the cloud and can be tested live without any local setup:
* **Frontend (Live UI):** [https://campaignmanagerfuturum.netlify.app/](https://campaignmanagerfuturum.netlify.app/)
* **Backend (Swagger API Docs):** [https://campaignmanager-9akn.onrender.com/swagger-ui/index.html](https://campaignmanager-9akn.onrender.com/swagger-ui/index.html)

*(Note: The backend is hosted on Render's free tier. It may take 40-50 seconds to wake up for the first request.)*

## 🚀 Technologies Used
*   **Backend:** Java, Spring Boot 3.2 (Web, Data JPA, Validation)
*   **Database:** H2 Database (In-Memory)
*   **Frontend:** Angular 18, Bootstrap 5, HTML/CSS
*   **Documentation:** OpenAPI 3.0 (Swagger UI)
*   **Build Tool:** Gradle
*   **DevOps & Deployment:** Docker, Render (Backend Hosting), Netlify (Frontend Hosting)

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
