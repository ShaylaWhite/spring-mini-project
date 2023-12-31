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

### User Stories

#### User Story 1: User Registration

- **As a Guest User**, I want to be able to register for an account with a username and password, so I can access the library's features.
- **Acceptance Criteria**:
    - I can access the `/auth/users/register/` endpoint.
    - I can provide a unique username and a secure password for registration.
    - Upon successful registration, I receive a confirmation message.

#### User Story 2: User Login

- **As a Registered User**, I want to log in and obtain a JWT token with my username and password, so I can access authenticated features.
- **Acceptance Criteria**:
    - I can access the `/auth/users/login/` endpoint.
    - I can provide my registered username and password.
    - Upon successful login, I receive a JWT token that I can use for authentication in subsequent requests.

#### User Story 3: Browse Books

- **As a Library Member**, I want to browse the list of available books by title, author, or category, so I can find books I'm interested in.
- **Acceptance Criteria**:
    - I can access the `/api/books/` endpoint.
    - I can view a paginated list of all available books.
    - I can filter books by title, author, or category.
    - I can see book details, including title, author, ISBN, and availability.

#### User Story 4: Borrow a Book

- **As a Library Member**, I want to borrow books from the library, so I can read them.
- **Acceptance Criteria**:
    - I can access the `/api/borrow/{bookId}` endpoint to borrow a specific book.
    - I must provide my JWT token for authentication.
    - I can only borrow books that are available.
    - Upon successful borrowing, the book's availability status changes, and it's associated with me as the borrower.

#### User Story 5: Return a Book

- **As a Library Member**, I want to return books to the library when I'm done reading them.
- **Acceptance Criteria**:
    - I can access the `/api/return/{bookId}` endpoint to return a specific book.
    - I must provide my JWT token for authentication.
    - I can only return books that I have previously borrowed.
    - Upon successful return, the book's availability status changes back to "available," and it's no longer associated with me.

#### User Story 6: Admin Manage Books

- **As a Librarian/Administrator**, I want to manage books in the library, including adding, updating, and deleting books.
- **Acceptance Criteria**:
    - I can access the `/api/books/` endpoint for CRUD operations (Create, Read, Update, Delete) on books.
    - I must provide my JWT token for authentication.
    - I can create a new book with title, author, ISBN, and availability details.
    - I can update book details.
    - I can delete books from the library.

#### User Story 7: Admin Manage Authors

- **As a Librarian/Administrator**, I want to manage authors in the library, including adding, updating, and deleting authors.
- **Acceptance Criteria**:
    - I can access the `/api/authors/` endpoint for CRUD operations (Create, Read, Update, Delete) on authors.
    - I must provide my JWT token for authentication.
    - I can create a new author with a name.
    - I can update author details.
    - I can delete authors from the library.

#### User Story 8: Admin Manage Categories

- **As a Librarian/Administrator**, I want to manage book categories in the library, including adding, updating, and deleting categories.
- **Acceptance Criteria**:
    - I can access the `/api/categories/` endpoint for CRUD operations (Create, Read, Update, Delete) on categories.
    - I must provide my JWT token for authentication.
    - I can create a new category with a name.
    - I can update category details.
    - I can delete categories from the library.


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




## Pre Project Planning 
- [ ] Document API endpoints and usage.
- [ ] Create User Stories.
- [ ] Create ERD diagrams and establish relationships. 

### Phase 1: Security and JWT
- [ ] Implement User and UserProfile entities with necessary attributes.
- [ ] Create a user registration functionality.
- [ ] Develop a login system with JWT token generation.
- [ ] Secure endpoints and routes based on user roles.
- [ ] Test and verify security measures.
- [ ] Document security features and procedures.
- [ ] Implement authentication and authorization filters.
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



## Dependency Breakdown 

## Getting Started with Dependencies 

To start a new Spring Boot project, you can follow these steps:

1. Go to the [Spring Initializr website](https://start.spring.io/).

2. Configure your project by selecting the desired options such as project type, language, and packaging. You can also add dependencies by searching and selecting them.

3. Once you've configured your project, click the "Generate" button.

4. Download the generated project ZIP file.

5. Extract the contents of the ZIP file to your preferred location on your computer.

6. Open the project in your favorite Integrated Development Environment (IDE).

7. Follow the installation instructions provided in the [Dependencies](#dependencies) section of this README to add the required dependencies to your project.

8. Start coding and building your Spring Boot application!

This README assumes that you've already created a Spring Boot project using the Spring Initializr website. If you're looking for instructions on how to add specific dependencies to your project, please refer to the [Dependencies](#dependencies) section for details.

## Dependencies 

- **Spring Boot Starter Web for RESTful APIs**
    - [Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-developing-web-applications)
    -   This dependency provides the necessary libraries and configurations for developing RESTful APIs with Spring Boot.
    - To include it in your project, add the following to your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle):

      ```xml
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
      ```

- **Spring Boot Starter Data JPA for database interactions**
    - [Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-jpa-and-spring-data)
    - To include it in your project, add the following to your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle):

      ```xml
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-data-jpa</artifactId>
      </dependency>
      ```

- **H2 Database for testing (you can replace it with your preferred database)**
    - [Documentation](https://www.h2database.com/html/main.html)
    - To include it in your project, add the following to your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle):

      ```xml
      <dependency>
          <groupId>com.h2database</groupId>
          <artifactId>h2</artifactId>
          <scope>runtime</scope>
      </dependency>
      ```

- **JUnit Jupiter for testing**
    - [Documentation](https://junit.org/junit5/docs/current/user-guide/)
    - To include it in your project, add the following to your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle):

      ```xml
      <dependency>
          <groupId>org.junit.jupiter</groupId>
          <artifactId>junit-jupiter-api</artifactId>
          <version>5.8.1</version>
          <scope>test</scope>
      </dependency>
      <dependency>
          <groupId>org.junit.jupiter</groupId>
          <artifactId>junit-jupiter-engine</artifactId>
          <version>5.8.1</version>
          <scope>test</scope>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-test</artifactId>
          <scope>test</scope>
      </dependency>
      ```

- **Spring Boot Starter Security**
    - [Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-security)
    - To include it in your project, add the following to your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle):

      ```xml
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-security</artifactId>
      </dependency>
      ```

- **JSON Web Token (JWT) Dependencies**
    - **jjwt-api**
        - [Documentation](https://github.com/jwtk/jjwt)
        - To include it in your project, add the following to your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle):

          ```xml
          <dependency>
              <groupId>io.jsonwebtoken</groupId>
              <artifactId>jjwt-api</artifactId>
              <version>0.11.5</version>
          </dependency>
          ```

    - **jjwt-impl**
        - [Documentation](https://github.com/jwtk/jjwt)
        - To include it in your project, add the following to your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle):

          ```xml
          <dependency>
              <groupId>io.jsonwebtoken</groupId>
              <artifactId>jjwt-impl</artifactId>
              <version>0.11.5</version>
              <scope>runtime</scope>
          </dependency>
          ```

    - **jjwt-jackson**
        - [Documentation](https://github.com/jwtk/jjwt)
        - To include it in your project, add the following to your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle):

          ```xml
          <dependency>
              <groupId>io.jsonwebtoken</groupId>
              <artifactId>jjwt-jackson</artifactId>
              <version>0.11.5</version>
              <scope>runtime</scope>
          </dependency>
          ```





## API Endpoints
To understand the functionality of the REST API and its endpoints, refer to the documentation provided in the code using JavaDoc comments. Additionally, I have documented the REST API endpoints for user awareness:

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

- [Link to Instructor's GitHub Profile with Instructions](https://git.generalassemb.ly/java-interapt-7-31/sprintboot-mini-project/blob/main/README.md)
- [Link to Helpful Website ](https://www.baeldung.com/start-here)
- [Spring Boot Tutorial | Full Course [2023] [NEW]](https://youtu.be/9SGDpanrc8U)
 
I would like to express my gratitude to the following individuals for their invaluable assistance and guidance throughout this project:

- [Julian's GitHub Profile](https://git.generalassemb.ly/juliansmith94) - Julian provided essential support during the initial project setup and explained the correct implementation of dependencies.

- [Marco's GitHub Profile](https://git.generalassemb.ly/marcog81) - Marco offered valuable insights into configuring the project folder structure and provided assistance with troubleshooting.

- [Rick's GitHub Profile](https://git.generalassemb.ly/rickm) - Rick played a crucial role in helping me understand the intricacies of Spring Boot and Spring Security.

- Thank you for your support and guidance during the development of this project. Your contributions and resources were invaluable.

---

