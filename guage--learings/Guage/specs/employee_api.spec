# Employee API Test


## Create Multiple Employees
@createEmployee
* Create employees with data
  | Name         | phoneNumber    | Email              | city      | department | salary | experience |
  | Mohit Patel  | 9876543210     | mohit@example.com  | Mumbai    | IT         | 100000 | 5          |
  | Jane Doe     | 9123456780     | jane@example.com   | Delhi     | HR         | 90000  | 3          |
  | John Smith   | 9988776655     | john@example.com   | Bangalore | Sales      | 120000 | 7          |
* Verify all employees are created successfully
    | Name           | Email              |
    | Mohit Patel    | mohit@example.com  |
    | Jane Doe       | jane@example.com   |
    | John Smith     | john@example.com   |
 * update employees with data
   | Name    | phoneNumber    | Email              | city    | department | salary | experience |
   | Mohit   | 9876543220     | mohit@example.com  | Muai    | IT         | 100    | 7          |
   | Jane    | 9123456730     | jane@example.com   | Dei     | HR         | 900    | 4          |
   | John    | 9988776665     | john@example.com   | Balore  | Sales      | 1200   | 1          |
* Delete all employees
       | Email              |
       | mohit@example.com  |
       | jane@example.com   |
       | john@example.com   |
