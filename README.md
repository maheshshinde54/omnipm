# OmniPM
[![Ask DeepWiki](https://devin.ai/assets/askdeepwiki.png)](https://deepwiki.com/maheshshinde54/omnipm)

OmniPM is a backend service for a project management application, built with Java and the Spring Boot framework. It provides a RESTful API for managing users and their timesheets.

## Features

- **User Management**: Perform full CRUD (Create, Read, Update, Delete) operations on user data.
- **Timesheet Tracking**: Log, view, update, and delete work timesheets associated with projects or tasks.
- **Custom Validations**: Implements custom validation rules for user roles and age constraints.
- **Centralized Exception Handling**: Provides consistent and clear error responses across the API.
- **DTO Pattern**: Uses Data Transfer Objects (DTOs) for clean data exchange between the client and server.
- **JPA Auditing**: Automatically tracks creation and modification timestamps for timesheet entries.

## Technology Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Web**
- **Hibernate**
- **MySQL**
- **Maven**
- **Lombok**
- **ModelMapper**

## Getting Started

Follow these instructions to get a local copy up and running for development and testing purposes.

### Prerequisites

- JDK 17 or later
- Maven
- A running MySQL instance

### Configuration

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/maheshshinde54/omnipm.git
    cd omnipm
    ```

2.  **Configure the database:**
    Open the `src/main/resources/application.properties` file.

    -   Ensure your MySQL server is running.
    -   The application will attempt to create a database named `omnipm` if it doesn't exist (`createDatabaseIfNotExist=true`). You can create it manually if you prefer.
    -   Update the database credentials to match your local setup:
        ```properties
        spring.datasource.username=<your-mysql-username>
        spring.datasource.password=<your-mysql-password>
        ```

### Running the Application

You can run the application using the Maven wrapper included in the project.

-   On macOS/Linux:
    ```sh
    ./mvnw spring-boot:run
    ```
-   On Windows:
    ```sh
    mvnw.cmd spring-boot:run
    ```

The application will start on `http://localhost:8080`.

## API Endpoints

The following are the available API endpoints.

### User API

Base Path: `/user`

| Method | Endpoint               | Description              |
| :----- | :--------------------- | :----------------------- |
| `GET`  | `/all`                 | Get all users.           |
| `GET`  | `/{userId}`            | Get a user by their ID.  |
| `POST` | `/add`                 | Add a new user.          |
| `PUT`  | `/update/{userId}`     | Update an existing user. |
| `DELETE`| `/delete/{userId}`     | Delete a user by their ID.|

**Example `POST /user/add` Request Body:**

```json
{
    "name": "John Doe",
    "email": "johndoe@example.com",
    "age": 30,
    "dataOfJoining": "2023-01-15",
    "isActive": true,
    "role": "USER"
}
```

### Timesheet API

Base Path: `/timesheet`

| Method | Endpoint                  | Description                 |
| :----- | :------------------------ | :-------------------------- |
| `GET`  | `/getalltimesheet`        | Get all timesheets.         |
| `POST` | `/addtimesheet`           | Add a new timesheet.        |
| `PUT`  | `/{timesheetId}`          | Update an existing timesheet.|
| `DELETE`| `/{timesheetId}`          | Delete a timesheet by its ID.|

**Example `POST /timesheet/addtimesheet` Request Body:**

```json
{
    "workDate": "2024-05-20",
    "minutesLogged": 240,
    "description": "Implemented user authentication feature.",
    "issueKey": "PROJ-123",
    "category": "DEVELOPMENT",
    "billable": true,
    "status": "PENDING"
}
```
*Note: `TimesheetStatus` can be `DRAFT`, `PENDING`, `APPROVED`, `DECLINED`.* 

*Note: `WorkCategory` can be `DEVELOPMENT`, `TESTING`, `PRODUCTION`, `MEETING`, `DOCUMENTATION`.*
