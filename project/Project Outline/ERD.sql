   +-----------------+       +---------------+       +--------------+
   |     Book        |       |    Author     |       |   Category   |
   +-----------------+       +---------------+       +--------------+
   | id              |       | id            |       | id           |
   | title           |       | name          |       | name         |
   | author_id (FK)  |-------| books         |       +--------------+
   | category_id (FK)|       +---------------+
   | isbn            |
   | available       |
   | borrower_id (FK)|
   +-----------------+

 +----------------+
|     User       |
+----------------+
| id             |
| username       |
| email          |
| password       |
| roles          |
| ...            |
+----------------+
