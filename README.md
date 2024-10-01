# Quran Learning Platform

This is a Quran learning platform that helps users pronounce Quranic words correctly by presenting them with a partially completed Aya and asking them to pronounce the correct missing word, using - openai/whisper-large-v3 (speech-to-text) API.

## Table of Contents

- [Getting Started](#getting-started)
- [Backend (Java Spring Boot)](#backend)
- [Frontend (React)](#frontend)

## Getting Started

Follow the steps below to set up and run both the backend and frontend of the application.

### Prerequisites

Before you begin, ensure you have the following installed on your machine:

- Java 17 or later
- Maven
- Node.js & npm

### Backend

#### Running the Backend

1. Clone the repository:
   ```bash
   git clone https://github.com/AshrafEzzeldin/Quran-App.git
    cd Quran-App
2. Navigate to the backend directory

3. add application.properties 
   
4. Make sure to install any required dependencies using Maven:
   ```bash
   mvn clean install
   ```

5. Run the application:
   ```bash
   mvn spring-boot:run
   ```

   Alternatively, you can run the application from the `QuranApplication.java` file directly using your preferred IDE (e.g., IntelliJ, Eclipse). Simply run the `main` method in `QuranApplication.java`.

6. The backend should now be running on `http://localhost:8080`.

### Frontend

#### Running the Frontend

1. Navigate to the frontend directory:

2. Install the frontend dependencies:
   ```bash
   npm install
   ```

3. Run the frontend development server:
   ```bash
   npm start
   ```

4. The frontend will be running on `http://localhost:3000`.

### Accessing the Application

Once both the backend and frontend are running, you can access the full application by visiting `http://localhost:3000` in your browser. The frontend will communicate with the backend via the provided API.
