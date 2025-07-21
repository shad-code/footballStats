# ⚽ Football Match Stats

This project is a full-stack application for managing football match statistics. The backend is built using Spring Boot, and the frontend is built using React.js with the Vite framework. It allows users to create football matches, add match statistics for both teams, and view detailed match data.

---

## 📚 Table of Contents

- [Prerequisites](#prerequisites)
- [Setup Backend (Spring Boot)](#setup-backend-spring-boot)
- [Setup Frontend (React.js with Vite)](#setup-frontend-reactjs-with-vite)
- [Running the Application](#running-the-application)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)

---

## ✅ Prerequisites

Before running the project, ensure you have the following installed:

### Java Development Kit (JDK)
- **Version:** JDK 17 or higher  
- **Download:** Oracle JDK or OpenJDK

### Node.js
- **Version:** 16.x or higher  
- **Download:** [Node.js](https://nodejs.org/)

### MySQL Database
- **Version:** 8.x or higher  
- **Download:** [MySQL](https://dev.mysql.com/downloads/)

### IDE
- **Backend:** IntelliJ IDEA or Eclipse  
- **Frontend:** Visual Studio Code

### Postman *(Optional)*
- For testing backend APIs  
- **Download:** [Postman](https://www.postman.com/)

---

## ⚙️ Setup Backend (Spring Boot)

### Step 1: Clone the Repository

```bash
git clone https://github.com/shad-code/footballStats.git
cd footballStats/backend
```

### Step 2: Create MySQL Database

Open MySQL and create a database named `football`:

```sql
CREATE DATABASE football2;
```

Update the database configuration in the `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/football
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
```

### Step 3: Build and Run the Backend

Open the backend folder in your IDE (e.g., IntelliJ IDEA).  
Build the project using Maven:

```bash
mvn clean install
```

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

The backend will start at:  
📍 `http://localhost:8080`

---

## 💻 Setup Frontend (React.js with Vite)

### Step 1: Navigate to the Frontend Folder

```bash
cd ../frontend
```

### Step 2: Install Dependencies

```bash
npm install
```

### Step 3: Run the Frontend

```bash
npm run dev
```

The frontend will start at:  
📍 `http://localhost:5173`

---

## ▶️ Running the Application

### 1. Start the Backend

Ensure the Spring Boot application is running at `http://localhost:8080`

### 2. Start the Frontend

Ensure the React.js application is running at `http://localhost:5173`

### 3. Access the Application

Open your browser and go to:

```text
http://localhost:5173
```

---

## 📂 Project Structure

### Backend (Spring Boot)

```
backend/
├── com/example/demo/
│   ├── Match.java                  # Entity for Match
│   ├── MatchRepository.java        # JPA Repository for Match
│   ├── MatchStats.java             # Entity for Match Stats
│   ├── MatchStatsRepository.java   # JPA Repository for Stats
│   ├── MatchStatsController.java   # REST Controller
│   └── ProjectApplication.java     # Main Spring Boot app
└── resources/
    └── application.properties      # MySQL DB config
```

### Frontend (React + Vite)

```
frontend/
├── App.jsx                         # Main React component
├── App.css                         # Component styles
├── index.css                       # Global styles
├── main.jsx                        # React root renderer
├── assets/
│   └── soccer-1678992.jpg          # Background image
└── vite.config.js                  # Vite config file
```

---

## 🛠️ Technologies Used

### Backend
- Spring Boot: Framework for Java backend development
- Spring Data JPA: ORM layer for interacting with MySQL
- MySQL: Relational database to store match data
- Maven: Build tool for Java projects

### Frontend
- React.js: JavaScript library for UI
- Vite: Lightning-fast build tool for React
- CSS: Custom component and global styling
- Fetch API: For API calls to backend

---

## 📬 API Endpoints

| Method | Endpoint              | Description             |
|--------|-----------------------|-------------------------|
| POST   | `/createMatch`        | Create a new match      |
| POST   | `/addMatchStats`      | Add stats to a match    |
| GET    | `/matches`            | Get list of all matches |
| GET    | `/matchStats/{name}`  | Get stats for a match   |

---

## 🙋‍♂️ Author

Created by **@shad-code**  
GitHub: [https://github.com/shad-code](https://github.com/shad-code)

EmailId : shadan.ahmad247@gmail.com
---