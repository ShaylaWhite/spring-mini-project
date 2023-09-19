# Spring Mini Project

## Project Title and Description

**Title**: Library Management System API

**Description**: The Library Management System API is designed to facilitate the management of books, authors, and categories in a library. It allows users to add, search, borrow, and return books efficiently.

## Tools and Technologies Used

- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- H2 Database
- JavaDoc
- Git and GitHub
- Spring Profiles
- Lucidchart: Lucidchart is a cloud-based diagramming tool that offers a wide range of diagram templates, including ERDs. It provides an intuitive interface for creating and collaborating on diagrams.
- Website: Lucidchart

## General Approach

The project follows a structured approach to building a secure and functional REST API for library management. It utilizes Spring Boot for creating a web application, integrates Spring Security with JWT token authentication for user access, and utilizes the H2 database to store book, author, and category data. The application adheres to the principles of the MVC (Model-View-Controller) design pattern by separating controllers and services for improved code organization.

## Unsolved Problems and Hurdles


## User Stories

You can find the user stories for this project [here](link-to-user-stories).

## Entity-Relationship Diagram (ERD)
The Entity-Relationship Diagram (ERD) for this project can be found [here](link-to-erd-diagram).

Entity-Relationship Diagram (ERD)
Entities:

User

-Attributes: ID (Primary Key), Username, Password
-Relationships: User can have many Books (for tracking who borrowed books).

Book

-Attributes: ID (Primary Key), Title, Author, Category, ISBN, Available (boolean), Borrower (foreign key to User)
-Relationships: Book belongs to an Author and a Category.

Author

-Attributes: ID (Primary Key), Name
-Relationships: Author can have many Books.

Category

-Attributes: ID (Primary Key), Name
-Relationships: Category can have many Books.

## Planning Documentation

Broke down this project into deliverables and created a detailed scope and schedule. You can view our planning documentation on the [GitHub Project](link-to-github-project).

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
## API Endpoints

| Request Type | URL                               | Functionality        | Access  |
|--------------|-----------------------------------|----------------------|---------|
| POST         | /auth/users/register/              | Register a new user with a username and password (Public) | Public |
| POST         | /auth/users/login/                 | Log in and obtain a JWT token with a username and password (Public) | Public |
| POST         | /api/books/                        | Create a new book (Admin, requires JWT) | Admin |
| GET          | /api/books/                        | Get a list of all books (Public, does not require JWT) | Public |
| GET          | /api/books/{id}                    | Get details of a book by ID (Public, does not require JWT) | Public |
| PUT          | /api/books/{id}                    | Update book details (Admin, requires JWT) | Admin |
| DELETE       | /api/books/{id}                    | Delete a book (Admin, requires JWT) | Admin |
| POST         | /api/authors/                      | Create a new author (Admin, requires JWT) | Admin |
| GET          | /api/authors/                      | Get a list of all authors (Public, does not require JWT) | Public |
| GET          | /api/authors/{id}                  | Get details of an author by ID (Public, does not require JWT) | Public |
| PUT          | /api/authors/{id}                  | Update author details (Admin, requires JWT) | Admin |
| DELETE       | /api/authors/{id}                  | Delete an author (Admin, requires JWT) | Admin |
| POST         | /api/categories/                   | Create a new category (Admin, requires JWT) | Admin |
| GET          | /api/categories/                   | Get a list of all categories (Public, does not require JWT) | Public |
| GET          | /api/categories/{id}               | Get details of a category by ID (Public, does not require JWT) | Public |
| PUT          | /api/categories/{id}               | Update category details (Admin, requires JWT) | Admin |
| DELETE       | /api/categories/{id}               | Delete a category (Admin, requires JWT) | Admin |
| POST         | /api/borrow/{bookId}               | Borrow a book (Library Member, requires JWT) | Library Member |
| POST         | /api/return/{bookId}               | Return a book (Library Member, requires JWT) | Library Member |

## Acknowledgments

We would like to give credit to the following sources and individuals who contributed to the success of this project:

- [Link to Instructor's GitHub Profile](instructor-github-profile)
- [Link to Classmate's GitHub Profile](classmate-github-profile)
- [Link to Helpful Website 1](helpful-website-1)
- [Link to Helpful Website 2](helpful-website-2)

Thank you for your support and guidance during the development of this project. Your contributions and resources were invaluable.

---

