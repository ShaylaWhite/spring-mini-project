# Spring Mini Project

## Project Title and Description

**Title**: Spring Mini Project

**Description**: This Spring-based REST API project is designed to showcase the development of a secure and functional RESTful API using Spring Boot. It incorporates Spring Security with JWT token authentication, utilizes the H2 database with three models (User, Genre, Book), and implements CRUD operations while adhering to REST conventions. The project follows the MVC design pattern, separates controllers and services, and emphasizes the principles of KISS (Keep It Simple, Stupid) and DRY (Don't Repeat Yourself).

## Tools and Technologies Used

- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- H2 Database
- JavaDoc
- Git and GitHub
- Spring Profiles

## General Approach

The project follows a structured approach to building a secure and functional REST API. It incorporates the use of Spring Boot to create a web application, Spring Security for user authentication, JWT tokens for secure API endpoints, and H2 database for data storage. The application follows the principles of MVC (Model-View-Controller) design pattern, separating controllers and services for better code organization.

## Unsolved Problems and Hurdles

Throughout the development of this project, we encountered and overcame several challenges. One significant hurdle was implementing JWT token authentication correctly to secure the API endpoints while ensuring ease of use for authorized users. Additionally, handling exceptions and providing meaningful error messages to users was a complex task that required careful consideration and testing.

## User Stories

You can find the user stories for this project [here](link-to-user-stories).

## Entity-Relationship Diagram (ERD)

The Entity-Relationship Diagram (ERD) for this project can be found [here](link-to-erd-diagram).

## Planning Documentation

We broke down this project into deliverables and created a detailed scope and schedule. You can view our planning documentation on the [GitHub Project](link-to-github-project).

## Installation Instructions

To run this project locally, follow these steps:

1. Clone the GitHub repository:

   ```
   git clone https://github.com/your-username/spring-mini-project.git
   ```

2. Navigate to the project directory:

   ```
   cd spring-mini-project
   ```

3. Build and run the project using Maven:

   ```
   mvn clean install
   mvn spring-boot:run
   ```

4. Access the API endpoints using your preferred API client or tools such as Postman or cURL.

## Documentation

To understand the functionality of the REST API and its endpoints, refer to the documentation provided in the code using JavaDoc comments. Additionally, we have documented the REST API endpoints for user awareness:

| Request Type | URL                               | Functionality        | Access  |
|--------------|-----------------------------------|----------------------|---------|
| POST         | /auth/users/login/                | User login           | Public  |
| GET          | /api/categories/                  | Get all categories   | Private |

## Acknowledgments

We would like to give credit to the following sources and individuals who contributed to the success of this project:

- [Link to Instructor's GitHub Profile](instructor-github-profile)
- [Link to Classmate's GitHub Profile](classmate-github-profile)
- [Link to Helpful Website 1](helpful-website-1)
- [Link to Helpful Website 2](helpful-website-2)

Thank you for your support and guidance during the development of this project. Your contributions and resources were invaluable.

---

