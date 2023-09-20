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
My biggest hurdle was the found in my Author and Book model classes. I forgot to include the @JsonIgnore so I encountered an issue related to missing @JsonIgnore annotations in the Author and Book model classes. @JsonIgnore is used to indicate that a field or property of an entity should be ignored during serialization and deserialization, typically when converting objects to JSON and vice versa.

I learned that if you have relationships between entities (such as between Author and Book) and you want to avoid circular references or unnecessary data being serialized, you can use @JsonIgnore strategically on fields to control which parts of your object graph are included in the JSON representation.

## User Stories


User Stories

-As a Librarian, I want to be able to add new books to the library so that they can be borrowed by library members. Authentication for librarians will be performed using JWT.

-As a Library Member, I want to search for books by title, author, or category so that I can find the books I'm interested in. Authentication for library members will be performed using JWT.

-As a Library Member, I want to borrow books from the library and return them when I'm done reading. Authentication for library members will be performed using JWT.

## Entity-Relationship Diagram (ERD)

The Entity-Relationship Diagram (ERD) for this project can be found here  [ERD](https://github.com/ShaylaWhite/spring-mini-project/blob/main/project/Project%20Outline/ERD.sql)

# Library Management System Entities

## Book
- Attributes: id, title, isbn, available
- Relationships:
    - author_id (Foreign Key to Author)
    - category_id (Foreign Key to Category)
    - borrower_id (Foreign Key to User)

## Author
- Attributes: id, name
- Relationships: books (One-to-Many relationship with Book)

## Category
- Attributes: id, name

## User
- Attributes: id, username, password, emailAddress
- Relationships:
    - userProfile (One-to-One relationship with UserProfile)
    - borrowedBooks (One-to-Many relationship with Book)

## UserProfile
- Attributes: id, firstName, lastName, dateOfBirth
- Relationships: user (One-to-One relationship with User)


## Planning Documentation

## Pre Project Planning 
- [ ] Document API endpoints and usage.
- [ ] Create User Stories.
- [ ] Create ERD diagrams and establish relationships. 
- 
### Phase 1: Security and JWT
- [ ] Implement User and UserProfile entities with necessary attributes.
- [ ] Create a user registration functionality.
- [ ] Develop a login system with JWT token generation.
- [ ] Secure endpoints and routes based on user roles.
- [ ] Test and verify security measures.
- [ ] Document security features and procedures.
- - [ ] Implement authentication and authorization filters.
- [ ] Handle exceptions and error responses.

### Phase 2: CRUD and Controller Mapping
#### Books
- [ ] Create a Book entity with necessary attributes.
- [ ] Implement CRUD operations for books.
- [ ] Develop controller mappings for book-related actions.
- [ ] Test book-related functionality.

#### Authors
- [ ] Create an Author entity with necessary attributes.
- [ ] Implement CRUD operations for authors.
- [ ] Develop controller mappings for author-related actions.
- [ ] Test author-related functionality.

#### Categories
- [ ] Create a Category entity with necessary attributes.
- [ ] Implement CRUD operations for categories.
- [ ] Develop controller mappings for category-related actions.
- [ ] Test category-related functionality.

### Additional Tasks
- [ ] Write unit and integration tests.
- [ ] Perform code reviews and optimization.
- [ ] Deploy the application (Phase 2 or later).

    #### (**Additional Class Borrowers if time permits.)
  - [ ] Create a Borrower entity with necessary attributes.
   - [ ] Implement CRUD operations for borrowers.
   - [ ] Develop controller mappings for borrower-related actions.
   - [ ] Test borrower-related functionality.

## Installation Instructions

To run this project locally, follow these steps:

1. Clone the GitHub repository:

   ```
   git clone https://github.com/ShaylaWhite/spring-mini-project
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

I would like to give credit to the following sources and individuals who contributed to the success of this project:

- [Link to Instructor's GitHub Profile](https://git.generalassemb.ly/java-interapt-7-31/sprintboot-mini-project/blob/main/README.md)
- [Link to Helpful Website 1](https://www.baeldung.com/start-here)
- [Spring Boot Tutorial | Full Course [2023] [NEW]](https://youtu.be/9SGDpanrc8U)

Thank you for your support and guidance during the development of this project. Your contributions and resources were invaluable.

---

